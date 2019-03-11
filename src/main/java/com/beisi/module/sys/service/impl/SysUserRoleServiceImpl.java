package com.beisi.module.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.common.util.GenerateUUID;
import com.beisi.module.sys.dao.SysUserRoleDao;
import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.entity.SysUserRole;
import com.beisi.module.sys.service.SysUserRoleService;

/**
 * 用户角色关联
 * 
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public void saveUserRole(SysUser sysUser) {
		String cUsrId = sysUser.getcUsrId();
		List<String> roleIdList = sysUser.getRoleIdList();
		// 先删除用户与角色对应关系
		sysUserRoleDao.deleteUserRole(cUsrId);
		if (roleIdList.size() == 0) {//如果没有角色，则后面的都不用执行，直接返回即可
			return;
		}
		String createUser = null;
		Date createTime = null;
		if(sysUser.getCreateUser() == null || sysUser.getCreateUser().isEmpty()) {//更新
			createUser = sysUser.getUpdateUser();
			createTime = sysUser.getUpdateTime();
		}else {//修改
			createUser = sysUser.getCreateUser();
			createTime = sysUser.getCreateTime();
		}
		for(int i=0;i<roleIdList.size();i++) {
			SysUserRole role = new SysUserRole();
			role.setRowId(GenerateUUID.getUUID());
			role.setcUsrId(cUsrId);
			role.setRoleId(roleIdList.get(i));
			role.setCreateUser(createUser);
			role.setCreateTime(createTime);
			sysUserRoleDao.saveUserRole(role);
		}
	}

	@Override
	public List<String> listUserRoleId(String userId) {
		return sysUserRoleDao.listUserRoleId(userId);
	}

}
