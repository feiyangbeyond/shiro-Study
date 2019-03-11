package com.beisi.module.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.annotation.SysLogAnno;
import com.beisi.common.controller.BaseController;
import com.beisi.common.util.Constant;
import com.beisi.common.util.GenerateUUID;
import com.beisi.common.util.Result;
import com.beisi.module.sys.entity.SysMenu;
import com.beisi.module.sys.service.SysMenuService;

/**
 * 菜单
 * 
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
	@Autowired
	SysMenuService sysMenuService;
	
	/**
	 * 菜单列表界面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "/index" })
	public String menu() {
		return "sys/menu";
	}
	
	/**
	 * 获取所有菜单列表
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:menu:list")
	@RequestMapping("/list")
	@ResponseBody
	public List<SysMenu> listMenu() {
		List<SysMenu> menuList = sysMenuService.listMenu(getSysUserId());
		return menuList;
	}
	
	/**
	 * 获取用户可访问的页面左侧菜单
	 * @return
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public List<SysMenu> listUserMenu() {
		List<SysMenu> menuList = sysMenuService.listUserMenu(getSysUserId());
		return menuList;
	}
	
	/**
	 * 保存菜单
	 * @param sysMenu
	 * @return
	 */
	@RequiresPermissions("sys:menu:save")
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> saveMenu(@RequestBody SysMenu sysMenu) {
		Map<String, Object> map = new HashMap<String,Object>();
		map=verifyForm(sysMenu);
		if (map.containsKey("msg")) {
			return map;
		}
		int lastMenuId = sysMenuService.queryLastMenuId();
		sysMenu.setMenuId(lastMenuId+1);
		sysMenu.setPermissionsId(GenerateUUID.getUUID());
		sysMenu.setCreateUser(getSysUserId());
		sysMenu.setCreateTime(new Date());
		sysMenuService.saveMenu(sysMenu);
		return Result.success();
	} 
	
	/**
	 * 更新菜单
	 * @param sysMenu
	 * @return
	 */
	@RequiresPermissions("sys:menu:update")
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updateMenu(@RequestBody SysMenu sysMenu){
		Map<String, Object> map = new HashMap<String,Object>();
		map=verifyForm(sysMenu);
		if (map.containsKey("msg")) {
			return map;
		}
		sysMenu.setUpdateUser(getSysUserId());
		sysMenu.setUpdateTime(new Date());
		sysMenuService.updateMenu(sysMenu);
		return Result.success();
	}
	
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 */
	@SysLogAnno("删除菜单")
	@RequiresPermissions("sys:menu:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> deleteMenu(@RequestParam("permissionsId")String permissionsId) {
		SysMenu sysMenu = sysMenuService.getByPermissionsId(permissionsId);
		//系统菜单不能删除
		if (sysMenu.getMenuId()<=Constant.MenuType.SYS_MENU_NUM) {
			return Result.error("系统菜单，万万不可删除啊");
		}
		//查询要删除的菜单的所有子菜单
		List<SysMenu> menuList = sysMenuService.listMenuIdByParentId(sysMenu.getMenuId());
		if (menuList.size()>0) {
			return Result.error("请先删除子菜单");
		}
		sysMenuService.deleteBatchByMenuIds(new String[]{permissionsId});
		return Result.success();
	}
	
	/**
	 * 选择菜单!!!!!!!!!!!!!!!!!!!这个在暂时未用到
	 * @return
	 */
	@RequiresPermissions("sys:menu:select")
	@RequestMapping("/select")
	@ResponseBody
	public Map<String, Object> selectMenu(){
		//查询不包含按钮的菜单列表
		List<SysMenu> menuList = sysMenuService.listNotButton(getSysUserId());
		//添加一个顶级菜单目录
		SysMenu root = new SysMenu();
		root.setMenuId(0);
		root.setMenuName("一级菜单");
		root.setParentId(-1);
		menuList.add(root);
		return Result.success().put("menuList", menuList);
	}

	/**
	 * 单个菜单详情
	 * @return
	 */
	@RequiresPermissions("sys:menu:info")
	@RequestMapping("/info/{permissionsId}")
	@ResponseBody
	public Map<String, Object> infoMenu(@PathVariable("permissionsId") String permissionsId){
		SysMenu menu = sysMenuService.getByPermissionsId(permissionsId);
		return Result.success().put("menu", menu);
	}
	
	
	/**
	 * 验证参数是否正确
	 */
	private Map<String, Object> verifyForm(SysMenu menu){
		Map<String, Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(menu.getMenuName())) {
			map.put("msg", "菜单名称不能为空");
			return map;
		} 
		if (menu.getParentId()==null) {
			map.put("msg", "上级菜单不能为空");
			return map;
		}
		Integer menuType = menu.getMenuType();
		if(menuType==null || menuType < 0) {
			map.put("msg", "类型为必选");
			return map;
		}
		int parentType = -1;//菜单类型不可能小于-1，可能等于0
		switch(menuType){
			case 0://选择为目录类型
				if (menu.getParentId() != 0) {
					map.put("msg", "目录的上级只能是一级菜单");
				}
				break;
	        case 1://选择为菜单类型
	        	if (StringUtils.isBlank(menu.getMenuUrl())) {
					map.put("msg", "菜单URL不能为空");
					break;
				}
	        	if (menu.getParentId() != 0) {
					SysMenu parentMenu = sysMenuService.getByMenuId(menu.getParentId());
					parentType = parentMenu.getMenuType();
					if (parentType != 0) {
						map.put("msg", "菜单的上级只能是目录");
					}
				}else {
					map.put("msg", "菜单的上级只能是目录");
				}
	        	break;
	        case 2://选择为按钮类型
	        	if (menu.getParentId() != 0) {
					SysMenu parentMenu = sysMenuService.getByMenuId(menu.getParentId());
					parentType = parentMenu.getMenuType();
					if (parentType != 1) {
						map.put("msg", "按钮的上级只能是菜单");
					}
				}else {
					map.put("msg", "按钮的上级只能是菜单");
				}
				break;
	        default:
	        	map.put("msg", "非法操作");break;
		}
		return map;
	}
}