package com.beisi.module.sys.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.beisi.module.sys.entity.SysUser;

/**
 * Shiro工具类
 */
public class ShiroUtil {

	/**
	 * 加密算法
	 */
	public static final String hashAlgorithmName = "MD5";
	
	/**
	 * 加密次数
	 */
	public static final int hashInterations = 7;
	
	/**
	 * MD5方式加密
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String shiroMD5(String password,String salt){
		return new SimpleHash(hashAlgorithmName, password, salt, hashInterations).toString();
	}
	
	/**
	 * 生成盐值
	 * @return
	 */
	public static String randomSalt(){
		return new SecureRandomNumberGenerator().nextBytes().toString();
	}
	
	/**
	 * 当前主体对象
	 * @return
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 返回当前用户对象
	 * @return
	 */
	public static SysUser getSysUser(){
		return (SysUser) getSubject().getPrincipal();
	}
	
	/**
	 * 获取Shiro对象
	 * @return
	 */
	public static Session getSession(){
		return getSubject().getSession();
	}
	
	/**
	 * 添加Session
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key,Object value){
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 获取Session
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key){
		return getSession().getAttribute(key);
	}
	
//	public static void main(String[] args) {
//		System.out.println(shiroMD5("123","4s3X1Auhexa8TJQGD/CeHw=="));
//	}
}
