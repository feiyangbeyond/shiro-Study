package com.beisi.shiro.service.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

public class QQRealm extends AuthenticatingRealm {

	/**
	 * 这个方法就是专门用来处理登录认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("这个QQRealm类里的doGetAuthenticationInfo，就是专门用来实现QQ第三方登录！");
		AuthenticationInfo info = new SimpleAuthenticationInfo("qq","123",null,getName());
		return info;
	}

}
