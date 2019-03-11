package com.beisi.module.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.common.util.GenerateUUID;
import com.beisi.module.sys.dao.SysRoleMenuDao;
import com.beisi.module.sys.entity.SysRole;
import com.beisi.module.sys.entity.SysRoleMenu;
import com.beisi.module.sys.service.SysRoleMenuService;

/**
 * 菜单角色关联
 * 
 */

@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	
	/**
	 * 保存角色与菜单的对应关系
	 * @param roleId
	 * @param menuList
	 */
	@Override
	public void saveRoleMenu(SysRole role) {
		String roleId = role.getRoleId();
		List<String> menuIdList = role.getPermissionsIdList();
		//先删除角色菜单关系
		sysRoleMenuDao.deleteRoleMenu(roleId);
		if (menuIdList.size()==0) {//即使为空，则说明目标为空，删除角色菜单关系与管理员想要达到的目标一致
			return;
		}
		String createUser = null;
		Date createTime = null;
		if(role.getCreateUser() == null || role.getCreateUser().isEmpty()) {//更新
			createUser = role.getUpdateUser();
			createTime = role.getUpdateTime();
		}else {//修改
			createUser = role.getCreateUser();
			createTime = role.getCreateTime();
		}
		//保存角色菜单关系
		for(int i=0;i<menuIdList.size();i++) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRowId(GenerateUUID.getUUID());
			roleMenu.setRoleId(roleId);
			roleMenu.setPermissionsId(menuIdList.get(i));
			roleMenu.setCreateUser(createUser);
			roleMenu.setCreateTime(createTime);
			sysRoleMenuDao.saveRoleMenu(roleMenu);
		}
	}

	/**
	 * 根据角色ID，获取权限ID列表
	 * @param roleId
	 * @return
	 */
	@Override
	public List<String> listRolePermissionsId(String roleId) {
		return sysRoleMenuDao.listRolePermissionsId(roleId);
	}

	/**
	 * 根据角色ID，获取菜单ID列表
	 * @param roleId
	 * @return
	 */
	@Override
	public List<String> listRoleMenuId(String roleId) {
		return sysRoleMenuDao.listRoleMenuId(roleId);
	}

}
