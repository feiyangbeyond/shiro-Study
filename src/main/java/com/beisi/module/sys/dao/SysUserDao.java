package com.beisi.module.sys.dao;


import java.util.List;
import java.util.Map;

import com.beisi.module.sys.entity.SysUser;


public interface SysUserDao {
	
	
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	SysUser getByUserName(String userName);
	
	/**
	 * 修改密码
	 * @param map
	 * @return
	 */
	int updatePassword(Map<String, Object> map);
	
	/**
	 * 查询用户所有的权限
	 * @param userId
	 * @return
	 */
	List<String> listAllperms(String cUsrId);

	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	List<String> listAllPrmissionsId(String cUsrId);

	
	/**
	 * 保存用户
	 * @param sysUser
	 */
	void saveUser(SysUser sysUser);

	/**
	 * 批量删除用户
	 * @param userIds
	 */
	void deleteBatchByUserIds(String[] cUserIds);

	/**
	 * 更新用户信息
	 * @param sysUser
	 */
	void updateUser(SysUser sysUser);

	/**
	 * 根据用户ID获取用户信息
	 * @param userId
	 * @return
	 */
	SysUser getByUserId(String cUserId);

	/**
	 * 获取用户列表
	 * @param map
	 * @return
	 */
	List<SysUser> listUser(Map<String, Object> map);

	/**
	 * 获取总用户数
	 * @param map
	 * @return
	 */
	int countUser(Map<String, Object> map);
	
	/**
	 * 修改用户最终登陆时间
	 * @param cUsrName
	 */
	void updateUserLoginTime(String cUsrName);
	
	

}
