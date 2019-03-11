package com.beisi.module.sys.entity;


import java.io.Serializable;
import java.util.Date;


/**
 * 用户与角色对应关系		
 *
 */
public class SysUserRole implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 行ID
	 */
	private String rowId;

	/**
	 * 用户ID
	 */
	private String cUsrId;

	/**
	 * 角色ID
	 */
	private String roleId;
	
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

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getcUsrId() {
		return cUsrId;
	}

	public void setcUsrId(String cUsrId) {
		this.cUsrId = cUsrId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
		return "SysUserRole [rowId=" + rowId + ", cUsrId=" + cUsrId + ", roleId=" + roleId + ", createUser="
				+ createUser + ", createTime=" + createTime + ", updateUser=" + updateUser + ", updateTime="
				+ updateTime + "]";
	}
}
