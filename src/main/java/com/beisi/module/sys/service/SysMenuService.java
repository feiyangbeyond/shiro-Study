package com.beisi.module.sys.service;

import java.util.List;
import java.util.Map;

import com.beisi.module.sys.entity.SysMenu;


/**
 * 菜单
 * 
 */
public interface SysMenuService {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> listMenuIdByParentId(Integer parentId, List<String> permissionsIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> listMenuIdByParentId(Integer parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> listNotButton(String userId);
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> listUserMenu(String userId);
	
	/**
	 * 查询菜单
	 */
	SysMenu getByPermissionsId(String prmissionsId);
	
	/**
	 * 查询菜单列表
	 */
	List<SysMenu> listMenu(String userId);
	
	/**
	 * 查询总数
	 */
	int countMenu(Map<String, Object> map);
	
	/**
	 * 保存菜单
	 */
	void saveMenu(SysMenu menu);
	
	/**
	 * 修改
	 */
	void updateMenu(SysMenu menu);
	
	/**
	 * 删除
	 */
	void deleteBatchByMenuIds(String[] permissionsIds);
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> listUserPerm(String userId);
	
	/**
	 * 查询当前最后一个menuId（不使用自增的原因在于，数据库认为修改不容易修改）
	 * @return
	 */
	int queryLastMenuId();
	
	/**
	 * 根据menuId获取menu对象
	 * @param parentId
	 * @return
	 */
	SysMenu getByMenuId(Integer menuId);
	
	
}
