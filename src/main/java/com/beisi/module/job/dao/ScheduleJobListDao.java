package com.beisi.module.job.dao;

import java.util.List;
import java.util.Map;

import com.beisi.module.job.entity.ScheduleJobList;

/**
 * 定时任务
 * 
 */
public interface ScheduleJobListDao {

	/**
	 * 获取任务信息
	 * 
	 * @param jobId
	 * @return
	 */
	ScheduleJobList getByJobId(String jobId);

	/**
	 * 获取任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ScheduleJobList> listJob(Map<String, Object> map);

	/**
	 * 获取任务数量
	 * 
	 * @param map
	 * @return
	 */
	int countJob(Map<String, Object> map);

	/**
	 * 保存任务
	 * 
	 * @param scheduleJob
	 */
	void saveJob(ScheduleJobList scheduleJobList);

	/**
	 * 更新任务
	 * 
	 * @param scheduleJob
	 */
	void updateJob(ScheduleJobList scheduleJobList);

	/**
	 * 批量更新状态
	 * 
	 * @param map
	 * @return
	 */
	int updateBatchJob(Map<String, Object> map);

	/**
	 * 批量删除
	 * 
	 * @param jobIds
	 */
	void deleteBatchByJobIds(String[] jobIds);

	/**
	 * 获取当前最大任务编号
	 * @return
	 */
	int getMaxNumNow();
}
