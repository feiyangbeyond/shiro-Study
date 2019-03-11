package com.beisi.module.job.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.beisi.common.exception.CustomException;
import com.beisi.common.util.Constant;
import com.beisi.module.job.entity.ScheduleJobList;

/**
 * 定时任务管理类
 * 
 */
public class QuartzUtil {
	
    private final static String JOB_NAME = "TASK_";
    
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }
    
    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new CustomException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobList scheduleJobList) {
 /*   	try {
        	//任务实例(JobDetail)
        	JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
        			.withIdentity(getJobKey(scheduleJobList.getJobId()))//参数1：任务名称（唯一实例） 参数2：任务组名称，如果没有指定组名，则使用默认DEFAULT
        			.build();
        	
        	//表达式调度构建器
            CronScheduleBuilder scheduleBuilder = 
            		CronScheduleBuilder.cronSchedule(scheduleJobList.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();
        	
        	//触发器(Trigger)
        	CronTrigger trigger = TriggerBuilder.newTrigger()
        			.withIdentity(getTriggerKey(scheduleJobList.getJobId()))//参数1：触发器名称（唯一实例） 参数2：触发器组的名称
        			//.startNow()//马上启动触发器
        			.withSchedule(scheduleBuilder)//scheduleBuilder: 日历"0/5 * * * * ?"
        			.build();
        	
        	//触发器与任务关联(让调度器关联任务和触发器，保证按照触发器定义的条件执行任务)
        	scheduler.scheduleJob(jobDetail, trigger);
        	
        	//根据用户需求判断是否 暂停任务
            if(scheduleJobList.getJobStatus() == Constant.ScheduleStatus.STATUS_PAUSE){
            	pauseJob(scheduler, scheduleJobList.getJobId());
            }
    	}catch (Exception e) {
    		throw new CustomException("创建定时任务失败", e);
		}*/
        try {
        	//构建job信息
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(getJobKey(scheduleJobList.getJobId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobList.getCronExpression()).withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJobList.getJobId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJobList.JOB_PARAM_KEY, scheduleJobList);

            scheduler.scheduleJob(jobDetail, trigger);
            
            //暂停任务
//            这里前台修改时，不会修改状态，则获取状态会失败，空指针异常！
            if(scheduleJobList.getJobStatus() == Constant.ScheduleStatus.STATUS_PAUSE){
            	pauseJob(scheduler, scheduleJobList.getJobId());
            }
        } catch (SchedulerException e) {
            throw new CustomException("创建定时任务失败", e);
        }
    }
    
    /**
     * 更新定时任务
     * @param scheduler
     * @param scheduleJobList
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobList scheduleJobList) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJobList.getJobId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobList.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJobList.getJobId());
            
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            
            //参数
            trigger.getJobDataMap().put(ScheduleJobList.JOB_PARAM_KEY, scheduleJobList);
            
            scheduler.rescheduleJob(triggerKey, trigger);
            
            //暂停任务
            if(scheduleJobList.getJobStatus() == Constant.ScheduleStatus.STATUS_PAUSE){
            	pauseJob(scheduler, scheduleJobList.getJobId());
            }
            
        } catch (SchedulerException e) {
            throw new CustomException("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, ScheduleJobList scheduleJobList) {
        try {
        	//参数
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(ScheduleJobList.JOB_PARAM_KEY, scheduleJobList);
            scheduler.triggerJob(getJobKey(scheduleJobList.getJobId()), dataMap);
        } catch (SchedulerException e) {
            throw new CustomException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new CustomException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new CustomException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new CustomException("删除定时任务失败", e);
        }
    }
}
