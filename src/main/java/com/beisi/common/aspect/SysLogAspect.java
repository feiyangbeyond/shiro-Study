package com.beisi.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.beisi.common.annotation.SysLogAnno;
import com.beisi.common.util.GenerateUUID;
import com.beisi.common.util.HttpContextUtil;
import com.beisi.module.sys.entity.SysLog;
import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysLogService;

/**
 * 
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogService sysLogService;
	

	@Pointcut(value = "@annotation(com.beisi.common.annotation.SysLogAnno)")
	public void logPointcut(){
		
	}
	
	@Around("logPointcut()")
	public Object logAround(ProceedingJoinPoint point) throws Throwable{
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		try {//防止日志记录发生异常影响正常流程运行
			saveSysLog(point, time);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint point, long time) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		SysLog sysLog = new SysLog();
		sysLog.setLogId(GenerateUUID.getUUID());
		SysLogAnno syslog = method.getAnnotation(SysLogAnno.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setcUsrOperation(syslog.value());
		}
		//请求的方法名
		String className = point.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethodName(className + "." + methodName + "()");
		//请求的参数
		Object[] args = point.getArgs();
		try{
			String params = JSON.toJSONString(args[0]);
			sysLog.setTheParams(params);
		}catch (Exception e){

		}
		//获取request
		HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
		//设置IP地址
		//sysLog.setIp(IPUtils.getIpAddr(request));
		sysLog.setTheIp(request.getRemoteAddr());
		//用户名
		SysUser user = ((SysUser) SecurityUtils.getSubject().getPrincipal());
		sysLog.setcUsrName(user.getcUsrName());
		sysLog.setTheTimes((int)time);
		sysLog.setCreateUser(user.getcUsrId());
		sysLog.setCreateTime(new Date());
		//保存系统日志
		sysLogService.saveLog(sysLog);
	}
	
}
