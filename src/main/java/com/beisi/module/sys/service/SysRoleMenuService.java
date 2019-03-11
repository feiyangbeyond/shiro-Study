package com.beisi.module.sys.service;

import java.util.List;

import com.beisi.module.sys.entity.SysRole;

/**
 * 角色与菜单的对应关系
 * 
 */
public interface SysRoleMenuService {

	/**
	 * 保存角色与菜单的对应关系
	 * @param roleId
	 * @param menuList
	 */
	void saveRoleMenu(SysRole role);
	
	/**
	 * 根据角色ID，获取权限ID列表
	 * @param roleId
	 * @return
	 */
	List<String> listRolePermissionsId(String roleId);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 * @param roleId
	 * @return
	 */
	List<String> listRoleMenuId(String roleId);
	

	
}
