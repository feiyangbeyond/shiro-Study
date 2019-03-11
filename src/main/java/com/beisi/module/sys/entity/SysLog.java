package com.beisi.module.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志
 * 
 */
public class SysLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日志ID
	 */
	private String logId;

	/**
	 * 用户名
	 */
	private String cUsrName;

	/**
	 * 用户操作
	 */
	private String cUsrOperation;

	/**
	 * 请求方法
	 */
	private String methodName;

	/**
	 * 请求参数
	 */
	private String theParams;

	/**
	 * 执行耗时(单位：毫秒)
	 */
	private Integer theTimes;

	/**
	 * 请求IP
	 */
	private String theIp;

	/**
	 * 创建人
	 */
	private String createUser;

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

	public String getcUsrName() {
		return cUsrName;
	}

	public void setcUsrName(String cUsrName) {
		this.cUsrName = cUsrName;
	}

	public String getcUsrOperation() {
		return cUsrOperation;
	}

	public void setcUsrOperation(String cUsrOperation) {
		this.cUsrOperation = cUsrOperation;
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

	public Integer getTheTimes() {
		return theTimes;
	}

	public void setTheTimes(Integer theTimes) {
		this.theTimes = theTimes;
	}

	public String getTheIp() {
		return theIp;
	}

	public void setTheIp(String theIp) {
		this.theIp = theIp;
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

	@Override
	public String toString() {
		return "SysLog [logId=" + logId + ", cUsrName=" + cUsrName + ", cUsrOperation=" + cUsrOperation
				+ ", methodName=" + methodName + ", theParams=" + theParams + ", theTimes=" + theTimes + ", theIp="
				+ theIp + ", createUser=" + createUser + ", createTime=" + createTime + "]";
	}

}
