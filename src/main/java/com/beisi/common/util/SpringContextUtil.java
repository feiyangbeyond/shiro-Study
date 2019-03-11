package com.beisi.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContext 工具类 
 *
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware的上下文注入函数
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 根据名称获取bean，byName
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		checkApplicationContext();
		return applicationContext.getBean(name);
	}

	/**
	 * 获取bean
	 * @param name，byName and class
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		checkApplicationContext();
		return applicationContext.getBean(name, clazz);
	}
	
	/**
	 * 获取bean， byClass
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {  
		checkApplicationContext();  
		return (T) applicationContext.getBean(clazz);
	}
	

	/**
	 * 是否包含bean
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name) {
		checkApplicationContext();
		return applicationContext.containsBean(name);
	}

	/**
	 * 是否为单例模式
	 * @param name
	 * @return
	 */
	public static boolean isSingleton(String name) {
		checkApplicationContext();
		return applicationContext.isSingleton(name);
	}

	/**
	 * 获取bean类型
	 * @param name
	 * @return
	 */
	public static Class<? extends Object> getType(String name) {
		checkApplicationContext();
		return applicationContext.getType(name);
	}
	
	
	/** 
	* 清除applicationContext静态变量. 
	*/  
	public static void cleanApplicationContext() {
		applicationContext = null;  
	} 

	/**
	 * application不为空
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException( "applicaitonContext未注入,请注入SpringContextUtil");
		}
	}

}