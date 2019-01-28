package com.beisi.shiro.service.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.beisi.shiro.dao.sys.PermissionDao;
import com.beisi.shiro.dao.sys.RoleDao;
import com.beisi.shiro.dao.sys.UserDao;
import com.beisi.shiro.model.sys.User;

public class ShiroRealm
		/* implements Realm */ extends AuthorizingRealm/* AuthorizingRealm做登录和权限认证，AuthenticatingRealm只做登录认证 */ {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;
	/*
	 * @Override public AuthenticationInfo getAuthenticationInfo(AuthenticationToken
	 * token) throws AuthenticationException { // TODO Auto-generated method stub
	 * return null; }
	 * 
	 * @Override public String getName() { // TODO Auto-generated method stub return
	 * null; }
	 * 
	 * @Override public boolean supports(AuthenticationToken arg0) {
	 * UsernamePasswordToken token = (UsernamePasswordToken) arg0;
	 * System.out.println(token.getUsername()); return false; }
	 */

	/**
	 * 真正在做项目的时候，登陆的验证工作，doGetAuthenticationInfo方法中来实现的
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		// 从token中获取到表单中的用户名
		String username = token.getUsername();
//		System.out.println(token.hashCode());
		// 从数据库中查询有没有username的用户记录
		User user = this.userDao.getUserByName(username);
		// 根据user对象情况，可以抛出shiro定义的异常
		if (user == null)
			throw new UnknownAccountException("没有此用户");
		if (user.getEnable() == 0)
			throw new LockedAccountException("用户已被管理员禁用");
		// 进一步使用shiro判断密码是否对的
		// principal：可以是用户的名称，可以使用户对象
		// hashedCredentials：从数据库中获取的对象密码
		// credentialsSalt：密码加密的盐值
		// realmName:ShiroRealm类的名字
		// 盐值
		ByteSource slat = ByteSource.Util.bytes(username);
//		AuthenticationInfo info = new SimpleAuthenticationInfo(principals, hashedCredentials, credentialsSalt, realmName)
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), slat, getName());
		// 剩下密码的对比，shiro框架内部框架来做的
		System.out.println("在shiro中进行了登录认证");
		return info;
	}

	/**
	 * 在shiro中专门做权限控制（授权认证）的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//处理授权认证传入的参数PrincipalCollection principals
		//1.传来的是登陆成功的用户信息（用户名或用户对象），是做登录认证（doGetAuthenticationInfo）方法传来的第一个参数
		//2.登录认证可能是多realm对象，所以穿过来多个用户信息，传入参数本质是一个集合，可能有多个元素
		//3.参数的集合里边元素的顺序，是Realm在spring.xml中核心配置器中的配置顺序
//		System.out.println(principals);
//		System.out.println(principals.getPrimaryPrincipal());//获取第一个
//		System.out.println(principals.oneByType(User.class));//根据type获取
//		System.out.println(principals.asList());//转成list，取得所有
		
		
		//从参数中获取当前登录成功的用户信息（用户名或用户对象）
		User user = principals.oneByType(User.class);
		//根据获取的用户信息，（用户信息中已经包含了角色/权限信息，直接去除）没包含则从数据库中查询用户的角色/权限信息，查角色信息
		Set<String> roles = roleDao.getRolesByUid(user.getId());
		//通过关联的角色信息，查询到关联的权限信息permission
		Set<String> permissions = permissionDao.getPermission(user.getId());

		Set<String> newPsrmission = new HashSet<>();
		for(String per:permissions) {		
			if(per.contains("p:")) {
				newPsrmission.add(per.replaceAll("p:", ""));
			}else {
				newPsrmission.add(per);
			}
		}
		//把获取到的用户角色role的set集合注入到AuthorizationInfo对象中
//		AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
//		或者
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);
		info.addStringPermissions(newPsrmission);
		return info;
	}

//	public static void main(String[] args) {
//		//1.algorithmName:加密算法名称MD5
//		String algorithmName = "MD5";
//		//2.source:要加密的原内容
//		String source = "202cb962ac59075b964b07152d234b70";
//		//3.salt:盐值
//		String salt= "admin";
//		//4.hashIterations:加密次数
//		int hashIterations = 1024;
//		Object rs = new SimpleHash(algorithmName,source,salt,hashIterations);
//		System.out.println(rs);
//	}

}
