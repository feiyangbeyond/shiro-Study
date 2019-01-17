package com.beisi.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.shiro.dao.BaseDao;
import com.beisi.shiro.dao.RoleDao;
import com.beisi.shiro.model.Role;
import com.beisi.shiro.service.RoleService;



@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public BaseDao getBaseDao() {
		return roleDao;
	}

}
