package com.beisi.module.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 系统用户
 *
 */
public class SysUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String cUsrId;

	/**
	 * 用户名
	 */
	private String cUsrName;

	/**
	 * 密码
	 */
	private String cUsrPassword;
	
	/**
	 * 真实姓名
	 */
	private String cUsrRealName;
	
	/**
	 * 邮箱
	 */
	private String cUsrEmail;

	/**
	 * 手机号
	 */
	private String cUsrPhone;
	
	/**
	 * 状态 0：禁用	1：正常
	 */
	private Integer cUsrStatus;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 账号过期时间
	 */
	private Date expirationTime;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建时间
	 */
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
	
	/**
	 * 修改人
	 */
	private String updateUser;
	
	/**
	 * 修改时间
	 */
	//@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
	
	/**
	 * 角色ID列表
	 */
	private List<String> roleIdList;

	public String getcUsrId() {
		return cUsrId;
	}

	public void setcUsrId(String cUsrId) {
		this.cUsrId = cUsrId;
	}

	public String getcUsrName() {
		return cUsrName;
	}

	public void setcUsrName(String cUsrName) {
		this.cUsrName = cUsrName;
	}

	public String getcUsrPassword() {
		return cUsrPassword;
	}

	public void setcUsrPassword(String cUsrPassword) {
		this.cUsrPassword = cUsrPassword;
	}

	public String getcUsrRealName() {
		return cUsrRealName;
	}

	public void setcUsrRealName(String cUsrRealName) {
		this.cUsrRealName = cUsrRealName;
	}

	public String getcUsrEmail() {
		return cUsrEmail;
	}

	public void setcUsrEmail(String cUsrEmail) {
		this.cUsrEmail = cUsrEmail;
	}

	public String getcUsrPhone() {
		return cUsrPhone;
	}

	public void setcUsrPhone(String cUsrPhone) {
		this.cUsrPhone = cUsrPhone;
	}

	public Integer getcUsrStatus() {
		return cUsrStatus;
	}

	public void setcUsrStatus(Integer cUsrStatus) {
		this.cUsrStatus = cUsrStatus;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
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

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	@Override
	public String toString() {
		return "SysUser [cUsrId=" + cUsrId + ", cUsrName=" + cUsrName + ", cUsrPassword=" + cUsrPassword
				+ ", cUsrRealName=" + cUsrRealName + ", cUsrEmail=" + cUsrEmail + ", cUsrPhone=" + cUsrPhone
				+ ", cUsrStatus=" + cUsrStatus + ", lastLoginTime=" + lastLoginTime + ", expirationTime="
				+ expirationTime + ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser="
				+ updateUser + ", updateTime=" + updateTime + ", roleIdList=" + roleIdList + "]";
	}

}
