package com.beisi.shiro.dao.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.beisi.shiro.model.sys.Permission;


public interface PermissionDao {
	
	/**
	 * 通过uid获取与这个用户关联的权限swt集合
	 */
	Set<String> getPermission(@Param("uid") int uid);

	List<Permission> getAllPermissions();

}
