package com.beisi.shiro.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.shiro.dao.sys.BaseDao;
import com.beisi.shiro.dao.sys.RoleDao;
import com.beisi.shiro.model.sys.Role;
import com.beisi.shiro.service.sys.RoleService;





@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public BaseDao getBaseDao() {
		return roleDao;
	}

}
