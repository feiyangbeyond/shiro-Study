package com.beisi.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.beisi.module.sys.entity.SysRole;

public interface SysRoleDao {
	
	/**
	 * 查询用户创建的角色ID列表
	 * @param createUser
	 * @return
	 */
	List<String> listRoleId(String createUser);
	
	/**
	 * 根据角色名获取角色条数
	 * @param createUser
	 * @return
	 */
	int getCountByRoleName(String roleName);

	/**
	 * 根据角色ID获取角色信息
	 * @param roleId
	 * @return
	 */
	SysRole getByRoleId(String roleId);

	/**
	 * 获取角色列表
	 * @param map
	 * @return
	 */
	List<SysRole> listRole(Map<String, Object> map);

	/**
	 * 角色总数
	 * @param map
	 * @return
	 */
	int countRole(Map<String, Object> map);

	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	void saveRole(SysRole role);

	/**
	 * 更新角色
	 * @param role
	 */
	void updateRole(SysRole role);

	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	void deleteBatchByRoleIds(String[] roleIds);
}
