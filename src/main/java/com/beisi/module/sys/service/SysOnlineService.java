package com.beisi.module.sys.service;

import java.util.List;
import java.util.Map;


public interface SysOnlineService {
	/**
	 * 获取当前在线列表
	 * @return
	 */
	List<Map<String, Object>> listOnline(String selfId);

	/**
	 * * 踢出用户
	 * @param sessionIds
	 */
	void offOnline(String[] sessionIds,String selfId);


}
