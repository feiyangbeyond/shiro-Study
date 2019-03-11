package com.beisi.module.sys.dao;

import java.util.List;

import com.beisi.module.sys.entity.SysRoleMenu;
/**
 * 菜单角色关联
 * 
 */
public interface SysRoleMenuDao {

	
	
	
	/**
	 * 根据角色ID，获取权限ID列表
	 * @param roleId
	 * @return
	 */
	List<String> listRolePermissionsId(String roleId);
	
	
	/**
	 * 删除角色菜单关系
	 * @param roleId
	 * @return
	 */
	int deleteRoleMenu(String roleId);


	/**
	 * 保存
	 * @param roleId
	 * @param menuIdList
	 */
	void saveRoleMenu(SysRoleMenu roleMenu);

	/**
	 * 根据角色ID，获取菜单ID列表
	 * @param roleId
	 * @return
	 */
	List<String> listRoleMenuId(String roleId);

}
