package com.beisi.module.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色菜单
 * 
 */
public class SysRoleMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 行ID
	 */
	private String rowId;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 权限ID
	 */
	private String permissionsId;

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
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
		return "SysRoleMenu [rowId=" + rowId + ", roleId=" + roleId + ", permissionsId=" + permissionsId
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + "]";
	}

}
