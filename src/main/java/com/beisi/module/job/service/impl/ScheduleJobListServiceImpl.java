package com.beisi.module.job.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beisi.common.util.Constant;
import com.beisi.module.job.dao.ScheduleJobListDao;
import com.beisi.module.job.entity.ScheduleJobList;
import com.beisi.module.job.quartz.QuartzUtil;
import com.beisi.module.job.service.ScheduleJobListService;

@Service("scheduleJobListService")
public class ScheduleJobListServiceImpl implements ScheduleJobListService {
	
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private ScheduleJobListDao scheduleJobListDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobList> scheduleJobLists = scheduleJobListDao.listJob(new HashMap<String, Object>());
		for(ScheduleJobList scheduleJobList : scheduleJobLists){
			CronTrigger cronTrigger = QuartzUtil.getCronTrigger(scheduler, scheduleJobList.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
            	QuartzUtil.createScheduleJob(scheduler, scheduleJobList);
            }else {
            	QuartzUtil.updateScheduleJob(scheduler, scheduleJobList);
            }
		}
	}
	
	@Override
	public ScheduleJobList getByJobId(String jobId) {
		return scheduleJobListDao.getByJobId(jobId);
	}

	@Override
	public List<ScheduleJobList> listJob(Map<String, Object> map) {
		return scheduleJobListDao.listJob(map);
	}

	@Override
	public int countJob(Map<String, Object> map) {
		return scheduleJobListDao.countJob(map);
	}

	/**
	 * 保存定时任务
	 */
	@Override
	public void saveJob(ScheduleJobList scheduleJobList) {
		scheduleJobList.setJobStatus(Constant.ScheduleStatus.STATUS_NORMAL);
		int nowNum = scheduleJobListDao.getMaxNumNow();
		scheduleJobList.setJobNum(nowNum+1);
		scheduleJobListDao.saveJob(scheduleJobList);
		QuartzUtil.createScheduleJob(scheduler, scheduleJobList);//不需要对创建是否成功进行判断，因为如果失败，save方法会回滚
    }
	
	@Override
	public void updateJob(ScheduleJobList scheduleJobList) {
		QuartzUtil.updateScheduleJob(scheduler, scheduleJobList);
		scheduleJobListDao.updateJob(scheduleJobList);
    }

	@Override
    public void deleteBatchByJobIds(String[] jobIds) {
    	for(String jobId : jobIds){
    		QuartzUtil.deleteScheduleJob(scheduler, jobId);
    	}
    	//删除数据
    	scheduleJobListDao.deleteBatchByJobIds(jobIds);
	}

	@Override
    public int updateBatchJob(String[] jobIds,String cUsrId, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("jobIds", jobIds);
    	map.put("updateUser", cUsrId);
    	map.put("updateTime", new Date());
    	map.put("jobStatus", status);
    	return scheduleJobListDao.updateBatchJob(map);
    }
    
	@Override
	@Transactional
    public void run(String[] jobIds) {
    	for(String jobId : jobIds){
    		QuartzUtil.run(scheduler, getByJobId(jobId));
    	}
    }

	@Override
	@Transactional
    public void pause(String[] jobIds,String cUsrId) {
        for(String jobId : jobIds){
        	QuartzUtil.pauseJob(scheduler, jobId);
    	}
        updateBatchJob(jobIds,cUsrId,Constant.ScheduleStatus.STATUS_PAUSE);
    }

	@Override
	@Transactional
    public void resume(String[] jobIds,String cUsrId) {
    	for(String jobId : jobIds){
    		QuartzUtil.resumeJob(scheduler, jobId);
    	}
    	updateBatchJob(jobIds,cUsrId, Constant.ScheduleStatus.STATUS_NORMAL);
    }
    
}
