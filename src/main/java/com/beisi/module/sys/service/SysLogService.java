package com.beisi.module.sys.service;

import java.util.List;
import java.util.Map;

import com.beisi.module.sys.entity.SysLog;


/**
 * 系统日志
 * 
 */
public interface SysLogService {

	
	/**
	 * 日志列表
	 * @param map
	 * @return
	 */
	List<SysLog> listLog(Map<String, Object> map);

	/**
	 * 日志数量
	 * @param map
	 * @return
	 */
	int countLog(Map<String, Object> map);

	/**
	 * 保存日志
	 * @param sysLog
	 */
	void saveLog(SysLog sysLog);

	
	
}
