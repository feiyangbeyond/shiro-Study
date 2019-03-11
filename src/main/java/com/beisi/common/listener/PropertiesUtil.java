package com.beisi.common.listener;

import java.util.HashMap;
import java.util.Map;

/**
 * 私有配置文件项目运行缓存
 *
 */
public class PropertiesUtil {
	private Map<String,String> map = new HashMap<>();
	
	//私有化构造方法
	private PropertiesUtil() {}
	
	private static PropertiesUtil instance = null;
	
	public static PropertiesUtil getInstance() {
		if(instance == null) {
			instance = new PropertiesUtil();
		}
		return instance;
	}
	
	public void addProperty(String key,String value) {
		map.put(key, value);
	}
	
	public String getProperty(String key) {
		return map.get(key);
	}
}
