package com.beisi.module.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.beisi.module.sys.dao.SysRoleDao;
import com.beisi.module.sys.entity.SysRole;
import com.beisi.module.sys.service.SysRoleMenuService;
import com.beisi.module.sys.service.SysRoleService;

/**
 * 角色
 * 
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 查询用户创建的角色ID列表
	 * @param createUser
	 * @return
	 */
	@Override
	public List<String> listRoleId(String createUser) {
		return sysRoleDao.listRoleId(createUser);
	}
	
	/**
	 * 根据角色ID获取角色
	 * @param roleId
	 * @return
	 */
	@Override
	public SysRole getByRoleId(String roleId) {
		return sysRoleDao.getByRoleId(roleId);
	}
	
	/**
	 * 查询角色列表
	 * @param map
	 * @return
	 */
	@Override
	public List<SysRole> listRole(Map<String, Object> map) {
		return sysRoleDao.listRole(map);
	}
	
	/**
	 * 查询角色总数
	 * @param map
	 * @return
	 */
	@Override
	public int countRole(Map<String, Object> map) {
		return sysRoleDao.countRole(map);
	}
	
	/**
	 * 保存角色
	 * @param role
	 */
	@Override
	public void saveRole(SysRole role) {
		int countByRoleName = sysRoleDao.getCountByRoleName(role.getRoleName());
		if(countByRoleName > 0)throw new DuplicateKeyException("角色名为“"+role.getRoleName()+"”已存在，请更换为其他名称！");
		role.setCreateTime(new Date());
		sysRoleDao.saveRole(role);
		sysRoleMenuService.saveRoleMenu(role);
	}
	
	/**
	 * 更新角色
	 * @param role
	 */
	@Override
	public void updateRole(SysRole role) {
		sysRoleDao.updateRole(role);
		//更新菜单角色关系
		sysRoleMenuService.saveRoleMenu(role);
	}
	
	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	@Override
	public void deleteBatchByRoleIds(String[] roleIds) {
		sysRoleDao.deleteBatchByRoleIds(roleIds);
	}
	

}
