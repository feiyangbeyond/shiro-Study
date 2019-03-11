package com.beisi.module.sys.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.controller.BaseController;
import com.beisi.common.util.QueryUtil;
import com.beisi.common.util.Constant;
import com.beisi.common.util.GenerateUUID;
import com.beisi.common.util.PageUtil;
import com.beisi.common.util.Result;
import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysUserRoleService;
import com.beisi.module.sys.service.SysUserService;
import com.beisi.module.sys.shiro.ShiroUtil;

/**
 * 系统用户
 * 
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 管理员列表
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "/index" })
	public String user() {
		return "sys/user";
	}


	/**
	 * 用户列表
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:user:list")
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(required = true) Map<String, Object> params) {
		if (params.isEmpty()) {
			return Result.error("参数不能为空");
		}
		if (!getSysUserId().equals(Constant.SUPER_ADMIN)) {
			params.put("createUser", getSysUserId());
		}
		QueryUtil query = new QueryUtil(params);
		List<SysUser> sysUserList = sysUserService.listUser(query);
		int total = sysUserService.countUser(query);
		PageUtil pageUtil = new PageUtil(sysUserList, total, query.getLimit(), query.getPage());
		return Result.success().put("page", pageUtil);
	}

	
	/**
	 * 用户信息
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("sys:user:info")
	@RequestMapping("/info/{cUsrId}")
	@ResponseBody
	public Map<String,Object> info(@PathVariable("cUsrId") String cUsrId) {
		SysUser sysUser = sysUserService.getByUserId(cUsrId);
		List<String> roleIdList = sysUserRoleService.listUserRoleId(cUsrId);
		sysUser.setRoleIdList(roleIdList);
		return Result.success().put("user", sysUser);
	}
	
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequiresPermissions("sys:user:save")
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(@RequestBody SysUser user) {
		user.setcUsrId(GenerateUUID.getUUID());
		user.setCreateUser(getSysUserId());
		user.setCreateTime(new Date());
		sysUserService.saveUser(user);
		return Result.success();
	} 
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@RequestBody SysUser user) {
		user.setUpdateUser(getSysUserId());
		user.setUpdateTime(new Date());
		sysUserService.updateUser(user);
		return Result.success();
	} 
	
	/**
	 * 批量删除用户
	 * @param userIds
	 * @return
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody String[] userIds) {
		if (ArrayUtils.contains(userIds, Constant.SUPER_ADMIN)) {
			return Result.error("系统管理员不能删除");
		}
		if (ArrayUtils.contains(userIds, getSysUserId())) {
			return Result.error("不能删除当前用户(本账号)");
		}
		sysUserService.deleteBatchByUserIds(userIds);
		return Result.success();
	}
	
	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 * @return
	 */
	@RequestMapping("/password")
	@ResponseBody
	public Map<String, Object> password(String password, String newPassword, String confirmPassword) {
		String selfUsrId = getSysUser().getcUsrId();//当前登录用户
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)) {
			return Result.error("密码不能为空！");
		}
		if(!newPassword.equals(confirmPassword)) {
			return Result.error("新密码与确认密码不相同！");
		}
		SysUser sysUser = getSysUser();
		// 原密码
		password = ShiroUtil.shiroMD5(password, sysUser.getcUsrName());
		// 新密码
		newPassword = ShiroUtil.shiroMD5(newPassword, sysUser.getcUsrName());
		// 更新密码
		int count = sysUserService.updatePassword(sysUser.getcUsrId(), password, newPassword,selfUsrId);
		if (count == 0) {
			return Result.error("原密码不正确,重新输入！");
		}
		return Result.success();
	}
}