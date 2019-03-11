package com.beisi.module.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色
 *
 */
public class SysRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 备注
	 */
	private String roleRemark;
	
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
	
	/**
	 * 菜单ID列表
	 */
	private List<String> permissionsIdList;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
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

	public List<String> getPermissionsIdList() {
		return permissionsIdList;
	}

	public void setPermissionsIdList(List<String> permissionsIdList) {
		this.permissionsIdList = permissionsIdList;
	}

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", roleRemark=" + roleRemark + ", createUser="
				+ createUser + ", createTime=" + createTime + ", updateUser=" + updateUser + ", updateTime="
				+ updateTime + ", permissionsIdList=" + permissionsIdList + "]";
	}

}
