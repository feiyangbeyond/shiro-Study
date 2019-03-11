package com.beisi.module.job.service;

import java.util.List;
import java.util.Map;

import com.beisi.module.job.entity.ScheduleJobList;


/**
 * 定时任务
 * 
 */
public interface ScheduleJobListService {

	/**
	 * 根据ID，查询定时任务
	 */
	ScheduleJobList getByJobId(String jobId);

	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJobList> listJob(Map<String, Object> map);

	/**
	 * 查询总数
	 */
	int countJob(Map<String, Object> map);

	/**
	 * 保存定时任务
	 */
	void saveJob(ScheduleJobList scheduleJobList);

	/**
	 * 更新定时任务
	 */
	void updateJob(ScheduleJobList scheduleJobList);

	/**
	 * 批量删除定时任务
	 */
	void deleteBatchByJobIds(String[] jobIds);

	/**
	 * 批量更新定时任务状态
	 */
	int updateBatchJob(String[] jobIds,String cUsrId, int status);

	/**
	 * 立即执行
	 */
	void run(String[] jobIds);

	/**
	 * 暂停运行
	 */
	void pause(String[] jobIds,String cUsrId);

	/**
	 * 恢复运行
	 */
	void resume(String[] jobIds,String cUsrId);
}
