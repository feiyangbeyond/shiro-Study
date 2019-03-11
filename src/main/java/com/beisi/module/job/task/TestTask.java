package com.beisi.module.job.task;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysUserService;


/**
 * 定时任务测试类
 *
 */
@Component("testTask")
public class TestTask {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserService sysUserService;

	public void test(String params) {
		logger.info("我是带参数的test方法,正在被执行,参数为：" + params);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SysUser user = sysUserService.getByUserId("1");
		System.out.println(ToStringBuilder.reflectionToString(user));
	}

	public void test2() {
		logger.info("我是不带参数的test2方法,正在被执行");
	}

}
