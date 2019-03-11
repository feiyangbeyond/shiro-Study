package com.beisi.module.job.service;

import java.util.List;
import java.util.Map;

import com.beisi.module.job.entity.ScheduleJobLog;


/**
 * 定时任务日志
 * 
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLog getByJobLogId(String jobId);

	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLog> listJobLog(Map<String, Object> map);

	/**
	 * 查询总数
	 */
	int countJobLog(Map<String, Object> map);

	/**
	 * 保存定时任务日志
	 */
	void saveJobLog(ScheduleJobLog log);

}
