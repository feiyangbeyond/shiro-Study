package com.beisi.module.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色
 *
 */
public class SysMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  菜单行ID
	 */
	private String permissionsId;
	
	/**
	 * 菜单ID
	 */
	private Integer menuId;
	
	/**
	 * 父菜单ID
	 */
	private Integer parentId;
	
	
	/**
	 * 菜单名称
	 */
	private String menuName;
	
	/**
	 * 父菜单名
	 */
	private String parentName;
	
	/**
	 * 菜单URL
	 */
	private String menuUrl;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String menuPerms;

	/**
	 * 类型     0：目录   1：菜单   2：按钮
	 */
	private Integer menuType;

	/**
	 * 菜单图标
	 */
	private String menuIcon;

	/**
	 * 排序
	 */
	private Integer orderNum;
	
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
	 * 子菜单
	 */
	private List<?> subList;

	public String getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuPerms() {
		return menuPerms;
	}

	public void setMenuPerms(String menuPerms) {
		this.menuPerms = menuPerms;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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

	public List<?> getSubList() {
		return subList;
	}

	public void setSubList(List<?> subList) {
		this.subList = subList;
	}

	@Override
	public String toString() {
		return "SysMenu [permissionsId=" + permissionsId + ", menuId=" + menuId + ", parentId=" + parentId
				+ ", menuName=" + menuName + ", parentName=" + parentName + ", menuUrl=" + menuUrl + ", menuPerms="
				+ menuPerms + ", menuType=" + menuType + ", menuIcon=" + menuIcon + ", orderNum=" + orderNum
				+ ", createUser=" + createUser + ", createTime=" + createTime + ", updateUser=" + updateUser
				+ ", updateTime=" + updateTime + ", subList=" + subList + "]";
	}

	
}
