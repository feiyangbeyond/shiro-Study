package com.beisi.common.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 * 跟随项目启动读取配置文件
 *
 */
@WebListener
public class PropertiesInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Properties properties = new Properties();
		String osName = System.getProperties().getProperty("os.name");
		//web服务器启动的时间执行		
		try {
			// 读取属性文件paperPath.properties
			InputStream in = getClass().getClassLoader().getResourceAsStream("/globalProfile.properties");
			// 加载属性列表
			properties.load(in);
			//拿到配置文件内容
			for(Object o:properties.keySet()) {//遍历每个键
				String key = (String)o;//键
				String value = properties.getProperty(key);//值
				if(key.equals("WindowsFilepath") || key.equals("LinuxFilepath")) {
					if(osName.indexOf("indows") != -1) {
						PropertiesUtil.getInstance().addProperty("uploadFilePath", value);
					}else {
						PropertiesUtil.getInstance().addProperty("uploadFilePath", value);
					}
					continue;
				}
				PropertiesUtil.getInstance().addProperty(key, value);
			}	
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
