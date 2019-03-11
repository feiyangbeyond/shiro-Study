package com.beisi.module.job.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 * 
 */
public class ScheduleJobLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志id
	 */
	private String logId;

	/**
	 * 任务id
	 */
	private String jobId;

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
	 * 任务状态 0：成功 1：失败
	 */
	private Integer jobStatus;

	/**
	 * 失败信息
	 */
	private String errorMsg;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer theTimes;
	

	/**
	 * 创建时间
	 */
	private Date createTime;


	public String getLogId() {
		return logId;
	}


	public void setLogId(String logId) {
		this.logId = logId;
	}


	public String getJobId() {
		return jobId;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
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


	public Integer getJobStatus() {
		return jobStatus;
	}


	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}


	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public Integer getTheTimes() {
		return theTimes;
	}


	public void setTheTimes(Integer theTimes) {
		this.theTimes = theTimes;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "ScheduleJobLog [logId=" + logId + ", jobId=" + jobId + ", beanName=" + beanName + ", methodName="
				+ methodName + ", theParams=" + theParams + ", jobStatus=" + jobStatus + ", errorMsg=" + errorMsg
				+ ", theTimes=" + theTimes + ", createTime=" + createTime + "]";
	}

}
