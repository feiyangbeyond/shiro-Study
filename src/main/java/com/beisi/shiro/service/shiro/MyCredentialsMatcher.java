package com.beisi.shiro.service.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;

import com.beisi.shiro.utils.AesEncryptUtil;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken arg0, AuthenticationInfo info) {
		//完全由自定义用户输入的密码，和数据库中的密码比对规则
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String pwd = new String(token.getPassword());
		// 把密码中的去uuidSalt(将前台传入的密码解密)
		Session session = SecurityUtils.getSubject().getSession();
		String key = (String) session.getAttribute("uuidsalt");

		try {
			pwd = AesEncryptUtil.desEncrypt(pwd, key, key);
			//密码解码成功后，立即将session的盐值删除
			session.removeAttribute("uuidsalt");
			if(pwd == null)throw new IncorrectCredentialsException("受到了重放攻击");
		} catch (Exception e) {			
			throw new IncorrectCredentialsException("受到了重放攻击");
		}	
		//pwd再进行md5加密
		String formPasswordpwd = (new SimpleHash("MD5",pwd,token.getUsername(),1024)).toString();
		//从info中获取数据库中的密码
		String accountCredentials = (String) getCredentials(info);
		return formPasswordpwd.equals(accountCredentials);
	}
	
//	public static void main(String[] args) {
//		String formPasswordpwd = (new SimpleHash("MD5","202cb962ac59075b964b07152d234b70","admin",1024)).toString();
//		System.out.println(formPasswordpwd);
//	}

}
