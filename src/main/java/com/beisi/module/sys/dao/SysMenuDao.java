package com.beisi.module.sys.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.beisi.module.sys.entity.SysMenu;

/**
 * 菜单
 * 
 */

public interface SysMenuDao{
	

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> listMenuIdByParentId(Integer parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> listNotButton();
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenu> listUserPerm(String userId);

	/**
	 * 根据菜单ID获取菜单信息
	 * @param menuId
	 * @return
	 */
	SysMenu getByPermissionsId(@Param("prmissionsId") String prmissionsId);

	/**
	 * 查询菜单列表
	 * @param map
	 * @return
	 */
	List<SysMenu> listMenu(Map<String, Object> map);

	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	int countMenu(Map<String, Object> map);

	/**
	 * 保存菜单
	 * @param menu
	 */
	void saveMenu(SysMenu menu);

	/**
	 * 更新菜单
	 * @param menu
	 */
	void updateMenu(SysMenu menu);

	/**
	 * 根据菜单ID删除菜单
	 * @param menuIds
	 */
	void deleteBatchByMenuIds(String[] permissionsIds);

	/**
	 * 查询最大的menuId值
	 * @param menuIds
	 */
	int queryLastMenuId();
	
	/**
	 * 根据menuId查询menu对象
	 * @param menuId
	 * @return
	 */
	SysMenu getByMenuId(Integer menuId);
	

}
