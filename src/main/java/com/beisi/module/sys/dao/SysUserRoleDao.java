package com.beisi.module.sys.dao;

import java.util.List;

import com.beisi.module.sys.entity.SysUserRole;

public interface SysUserRoleDao {

	/**
	 * 根据用户ID保存更新关联角色
	 * @param userId
	 * @param roleIdList
	 */
	void saveUserRole(SysUserRole roleObj);

	/**
	 * 根据用户ID获取角色列表ID
	 * @param userId
	 * @return
	 */
	List<String> listUserRoleId(String userId);
	
	/**
	 * 根据用户ID删除用户与角色对应关系
	 * @param userId
	 */
	void deleteUserRole(String userId);

}
