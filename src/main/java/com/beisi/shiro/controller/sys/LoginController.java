package com.beisi.shiro.controller.sys;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	/**
	 * 登录页面显示出来的方法
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
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

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String loginPost(String userInfo, String password, boolean remeberme, HttpSession session) {
		// 使用shiro登录认证
		// 1.认证核心组件，subject，获取Subject对象
		Subject subject = SecurityUtils.getSubject();
		// 2.登录验证第二部，将表单提交过来的用户名和密码封装到token对象
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo, password);
		// 3.调用subject对象中的login方法，进行登录验证
		// 开启记住我功能
		if (remeberme) {
			token.setRememberMe(true);
		}
		try {
			subject.login(token);// 让shiro框架帮助我们进行登录验证
		} catch (Exception e) {
			e.printStackTrace();
			return "loginError";
		}
		return "redirect:/admin/main.html";
	}

}
