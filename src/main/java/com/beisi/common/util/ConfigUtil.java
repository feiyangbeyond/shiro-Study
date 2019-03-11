package com.beisi.common.util;

import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 */
public class ConfigUtil {

	private ConfigUtil() {

	}

	/**
	 * 通过静态代码块读取上传文件的验证格式配置文件，静态代码块只执行一次（单例）
	 */
	private static Properties properties = new Properties();

	// 通过类装载器装载进来
	static {
		try {
			// 从类路径下获取属性文件
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据key获取value
	 * 
	 * @param keyName
	 * @return
	 */
	public static String getProperty(String keyName) {
		return getProperty(keyName, "");
	}

	/**
	 * 根据key返回value,key为空，返回默认值
	 * 
	 * @param keyName
	 * @param defaultValue(defaultValue是在获取对应keyName的值失败时作为替补返回的)
	 * @return
	 */
	public static String getProperty(String keyName, String defaultValue) {
		return properties.getProperty(keyName, defaultValue);
	}

}
