package com.beisi.shiro.dao.sys;

import java.util.Set;

public interface RoleDao extends BaseDao{

	Set<String> getRolesByUid(Integer id);
	
}
