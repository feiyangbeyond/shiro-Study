package com.beisi.shiro.dao;

import org.apache.ibatis.annotations.Param;

public interface UserRoleDao extends BaseDao{

	/**
	 * 通过用户id，删除与该用户关联的role对象，关联信息
	 * @param id
	 */
	void deleteByUid(@Param("uid") Integer uid);
	
}
