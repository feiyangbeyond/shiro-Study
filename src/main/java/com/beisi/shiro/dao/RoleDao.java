package com.beisi.shiro.dao;

import java.util.Set;

public interface RoleDao extends BaseDao{

	Set<String> getRolesByUid(Integer id);
	
}
