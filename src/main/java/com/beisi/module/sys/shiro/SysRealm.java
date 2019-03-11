package com.beisi.module.sys.shiro;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beisi.common.exception.CustomException;
import com.beisi.common.util.Constant;
import com.beisi.module.sys.entity.SysMenu;
import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysMenuService;
import com.beisi.module.sys.service.SysUserService;
/**
 * Shiro
 *
 */
public class SysRealm extends AuthorizingRealm {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 对用户进行授权和授予权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		String cUsrId = user.getcUsrId();
		List<String> permsList = null;
		if (cUsrId.equals(Constant.SUPER_ADMIN)) {
			List<SysMenu> menuList = sysMenuService.listMenu(Constant.SUPER_ADMIN);
			permsList = new ArrayList<>(menuList.size());
			for (SysMenu sysMenu : menuList) {
				permsList.add(sysMenu.getMenuPerms());
			}
		} else {
			permsList = sysUserService.listAllperms(cUsrId);
		}
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(permsSet);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		SysUser user = sysUserService.getByUserName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		if (user.getcUsrStatus() == Constant.UserStatus.STATUS_LOCKED) {
			throw new LockedAccountException();
		}
		user.setLastLoginTime(new Date());//更新登陆时间
		try {
			sysUserService.updateLoginTime(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//判断过期时间是否在当前时间之前,为空则说明不限制账号有效时间
		if (user.getExpirationTime() != null && user.getExpirationTime().before(new Date())) {
			throw new CustomException("您的账号已到期！");
		}
		ByteSource slat = ByteSource.Util.bytes(user.getcUsrName());
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getcUsrPassword(), slat, getName());
		return info;
	}
	
	/**
	 * 验证方式
	 */
//	@Override
//	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
//		
//		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
//		hashedCredentialsMatcher.setHashIterations(ShiroUtil.hashInterations);
//		super.setCredentialsMatcher(hashedCredentialsMatcher);
//	}
	

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Object salt = "4s3X1Auhexa8TJQGD/CeHw==";
		String credentials = "admin";

		// long begin = System.currentTimeMillis();
		String salt = ShiroUtil.randomSalt();
		System.out.println("盐值：" + salt);
		Object simpleHash = ShiroUtil.shiroMD5(credentials, salt);
		// long end = System.currentTimeMillis();
		// System.out.println("加密花费时间： " + (end-begin));
		System.out.println("加密前的值：" + credentials);
		System.out.println("加密后的值：" + simpleHash);


		//使用AES生成RememberMe的密钥 
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			SecretKey deskey = keygen.generateKey();
			System.out.println(Base64.encodeToString(deskey.getEncoded()));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
