package com.beisi.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
/**
 * request上下文工具类
 * 
 */
public class HttpContextUtil {
	
	//获取请求对象request
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	//获取请求域名
	public static String getDomain() {
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

//	public static String getOrigin() {
//		HttpServletRequest request = getHttpServletRequest();
//		return request.getHeader("Origin");
//	}
	
	//获取来访者地址
	//注意：getHeader("referer")要走http协议时才有值，
	//也就是说要通过<a href="url">a</a>标记才能获得那个值，
	//而通过改变location或是<a href="javascript:location='url'">a</a>都是得不到那个值的
	public static String getReferer() {
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("referer");
	}
}
