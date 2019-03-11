package com.beisi.module.sys.service;

import java.util.List;

import com.beisi.module.sys.entity.SysUser;
/**
 * 用户角色关联
 * 
 */
public interface SysUserRoleService {

	/**
	 * 根据用户ID保存更新关联角色
	 * @param userId
	 * @param roleIdList
	 */
	void saveUserRole(SysUser sysUser);

	/**
	 * 根据用户ID获取角色列表
	 * @param userId
	 * @return
	 */
	List<String> listUserRoleId(String userId);

}
