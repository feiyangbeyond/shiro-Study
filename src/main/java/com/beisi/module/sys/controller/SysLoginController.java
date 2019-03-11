package com.beisi.module.sys.controller;


import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.controller.BaseController;
import com.beisi.common.exception.CustomException;
import com.beisi.common.util.Result;
import com.beisi.module.sys.entity.SysMenu;
import com.beisi.module.sys.service.SysMenuService;

/**
 * 登录视图控制器
 * 
 */
@Controller
@RequestMapping("/sys")
public class SysLoginController extends BaseController {
	@Autowired
	SysMenuService sysMenuService;
	
	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(String userName, String password,String captcha, boolean rememberMe) {
		Subject curUser = SecurityUtils.getSubject();
		logger.info("----userName = " + userName);
		logger.info("----password = " + password);
		logger.info("----captcha = " + captcha); 
		logger.info("----rememberMe = " + rememberMe);
		if (curUser.isRemembered()) {
			logger.info("rememberMe:" + "用户已经记住");
			return Result.success();
		}
		UsernamePasswordToken passwordToken = new UsernamePasswordToken(userName, password,rememberMe);
		String errMsg = "";
		String logMsg = "";
		try{
			curUser.login(passwordToken);
			logMsg = "登录成功: ";
			Session session = curUser.getSession();
			logger.info("sessionId:" + session.getId());
			logger.info("sessionHost:" + session.getHost());
			logger.info("sessionTimeout:" + session.getTimeout()); 
		}catch (UnknownAccountException uae ) {
			logMsg = "账户不存在";
			errMsg = "账户/密码不匹配！";
		}catch (IncorrectCredentialsException ice) {
			logMsg = "密码不匹配！";
			errMsg = "账户/密码不匹配！";
        }catch (LockedAccountException lae) {
        	logMsg = "账户已被冻结！ ";
        	errMsg = "账户已被冻结！";
        }catch(ExcessiveAttemptsException eae){
        	logMsg = "账户验证未通过,错误次数大于5次,账户已锁定";
        	errMsg = "账户验证未通过,错误次数大于5次,账户已锁定";
        }catch (DisabledAccountException sae){
        	logMsg = "账户验证未通过,帐号已经禁止登录";
        	errMsg = "账户验证未通过,帐号已经禁止登录";
        }catch (SecurityException re) {
        	logMsg = "受到攻击！";
			errMsg = "受到了重放攻击！";
        }catch (AuthenticationException ae) {
        	logMsg = "登录失败:" + userName;
        	errMsg = "登录失败";
        }catch (CustomException ce) {
        	logMsg = "您的账号已到期:" + userName;
        	errMsg = "抱歉！您的账号已到期!";
        }
		logger.info(logMsg +"---用户名："+ userName);
		//验证是否登录成功
        if(curUser.isAuthenticated()){
        	return Result.success();
        }else{
        	passwordToken.clear();
        	return Result.error(errMsg);
        }
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(Model model,HttpSession session){
		Subject curUser = SecurityUtils.getSubject();
		if (curUser.isAuthenticated()||curUser.isRemembered()) {
			return redirect("/sys/index");
		}
		// 生成一组16位的随机数
		int hashcodeV = UUID.randomUUID().hashCode();
		if (hashcodeV < 0)
			hashcodeV = -hashcodeV;
		String uuidsalt = String.format("%016d", hashcodeV);
		// 吧uuid盐值同时保存到前台和后台
		model.addAttribute("uuidsalt", uuidsalt);
		session.setAttribute("uuidsalt", uuidsalt);
		return "login";
	}

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
        logger.info("退出账号");
		return redirect("/sys/login");
	}
	
	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@RequestMapping(value = {"","/","/index"})
	public String sysIndex(ModelMap modelMap) {
		if (getSysUser() == null) {
			return "login";
		}
		List<SysMenu> menuList = null;
		menuList = sysMenuService.listUserMenu(getSysUserId());
		modelMap.addAttribute("menuList",menuList);
    	modelMap.addAttribute("sysUser",getSysUser());
		logger.info("跳转到后台首页");
		return "sys/index";
	}
	
	/**
	 * 测试入口
	 */
	@RequestMapping("/test")
	@ResponseBody
	public void test(){
		
	}
}