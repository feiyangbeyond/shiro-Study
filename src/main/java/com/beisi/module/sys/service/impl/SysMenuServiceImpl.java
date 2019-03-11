package com.beisi.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.common.util.Constant;
import com.beisi.module.sys.dao.SysMenuDao;
import com.beisi.module.sys.entity.SysMenu;
import com.beisi.module.sys.service.SysMenuService;
import com.beisi.module.sys.service.SysUserService;





/**
 * 菜单
 * 
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 * 这里使用了多态
	 */
	@Override
	public List<SysMenu> listMenuIdByParentId(Integer parentId, List<String> permissionsIdList) {
		List<SysMenu> menuList = listMenuIdByParentId(parentId);
		if (permissionsIdList == null) {
			return menuList;
		}
		List<SysMenu> userMenuList = fileterMenu(permissionsIdList,menuList);
		return userMenuList;
	}
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * 这里使用了多态
	 */
	@Override
	public List<SysMenu> listMenuIdByParentId(Integer parentId) {
		return sysMenuDao.listMenuIdByParentId(parentId);
	}
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	@Override
	public List<SysMenu> listNotButton(String cUsrId) {
		List<SysMenu> menuList = sysMenuDao.listNotButton();
		//系统管理员，全部菜单
		if (cUsrId.equals(Constant.SUPER_ADMIN)) {
			return menuList;
		}
		//用户菜单列表
		List<String> prmissionsIdList = sysUserService.listAllPrmissionsId(cUsrId);
		List<SysMenu> userMenuList = fileterMenu(prmissionsIdList,menuList);
		return userMenuList;
	}
	
	/**
	 * 查询用户的权限列表
	 */
	@Override
	public List<SysMenu> listUserPerm(String cUsrId) {
		return sysMenuDao.listUserPerm(cUsrId);
	}
	
	/**
	 * 根据菜单ID获取菜单信息
	 * @param menuId
	 * @return
	 */
	@Override
	public SysMenu getByPermissionsId(String prmissionsId) {
		return sysMenuDao.getByPermissionsId(prmissionsId);
	}
	
	/**
	 * 查询菜单列表
	 * @param map
	 * @return
	 */
	@Override
	public List<SysMenu> listMenu(String cUsrId) {
		List<SysMenu> menuList = sysMenuDao.listMenu(null);
		System.out.println(menuList);
		//系统管理员，全部菜单
		if (cUsrId.equals(Constant.SUPER_ADMIN)) {
			return menuList;
		}
		//用户菜单列表
		List<String> prmissionsIdList = sysUserService.listAllPrmissionsId(cUsrId);
		List<SysMenu> userMenuList = fileterMenu(prmissionsIdList,menuList);
		return userMenuList;
	}
	
	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	@Override
	public int countMenu(Map<String, Object> map) {
		return sysMenuDao.countMenu(map);
	}
	
	/**
	 * 保存菜单
	 * @param menu
	 */
	@Override
	public void saveMenu(SysMenu menu) {
		sysMenuDao.saveMenu(menu);
	}
	
	/**
	 * 更新菜单
	 * @param menu
	 */
	@Override
	public void updateMenu(SysMenu menu) {
		sysMenuDao.updateMenu(menu);
	}
	
	/**
	 * 根据菜单ID删除菜单
	 * @param menuIds
	 */
	@Override
	public void deleteBatchByMenuIds(String[] permissionsIds) {
		sysMenuDao.deleteBatchByMenuIds(permissionsIds);
	}


	/**
	 * 获取用户菜单列表
	 */
	@Override
	public List<SysMenu> listUserMenu(String cUusrId) {
		//系统管理员，全部菜单
		if (cUusrId.equals(Constant.SUPER_ADMIN)){
			List<SysMenu> allMenu = getAllMenuList(null);
			return allMenu;
		}
		//用户菜单列表
		List<String> prmissionsIdList = sysUserService.listAllPrmissionsId(cUusrId);
		return getAllMenuList(prmissionsIdList);
	}
	
	
	/**
	 * 获取表最大的menuId值
	 */
	@Override
	public int queryLastMenuId() {
		return this.sysMenuDao.queryLastMenuId();
	}
	
	/**
	 * 根据menuId查询menu对象
	 */
	@Override
	public SysMenu getByMenuId(Integer menuId) {
		return sysMenuDao.getByMenuId(menuId);
	}
	
	//------
	
	private List<SysMenu> fileterMenu(List<String> permissionsIdList,List<SysMenu> menuList){
		//过滤用户当前菜单
		List<SysMenu> userMenuList = new ArrayList<>();
		for (SysMenu sysMenu : menuList) {
			if (permissionsIdList.contains(sysMenu.getPermissionsId())) {
				userMenuList.add(sysMenu);
			}
		}
		return userMenuList;
	}
	
	
	
	//------
	
	private List<SysMenu> getAllMenuList(List<String> permissionsIdList) {
		//查询根菜单列表
		List<SysMenu> menuList = listMenuIdByParentId(0,permissionsIdList);
		//递归获取子菜单
		getMenuTreeList(menuList,permissionsIdList);
		return menuList;
	}

	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> permissionsIdList) {
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		for (SysMenu menu : menuList) {
			//目录
			if (menu.getMenuType() == Constant.MenuType.CATALOG) {
				menu.setSubList(getMenuTreeList(listMenuIdByParentId(menu.getMenuId(),permissionsIdList), permissionsIdList));
			}
			subMenuList.add(menu);
		}
		return subMenuList;
	}

}
