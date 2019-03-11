package com.beisi.module.job.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 * 
 */
public class ScheduleJobList implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务调度参数key
	 */
	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	/**
	 * 任务id
	 */
	private String jobId;
	
	/**
	 * 任务编号
	 */
	private Integer jobNum;

	/**
	 * spring bean名称
	 */
	private String beanName;

	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 参数
	 */
	private String theParams;

	/**
	 * cron表达式
	 */

	private String cronExpression;

	/**
	 * 任务状态
	 */
	private Integer jobStatus;

	/**
	 * 备注
	 */
	private String jobRemark;

	/**
	 * 创建人
	 */
	private String createUser;
	
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String updateUser;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Integer getJobNum() {
		return jobNum;
	}

	public void setJobNum(Integer jobNum) {
		this.jobNum = jobNum;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getTheParams() {
		return theParams;
	}

	public void setTheParams(String theParams) {
		this.theParams = theParams;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobRemark() {
		return jobRemark;
	}

	public void setJobRemark(String jobRemark) {
		this.jobRemark = jobRemark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ScheduleJobList [jobId=" + jobId + ", jobNum=" + jobNum + ", beanName=" + beanName + ", methodName="
				+ methodName + ", theParams=" + theParams + ", cronExpression=" + cronExpression + ", jobStatus="
				+ jobStatus + ", jobRemark=" + jobRemark + ", createUser=" + createUser + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}
}
