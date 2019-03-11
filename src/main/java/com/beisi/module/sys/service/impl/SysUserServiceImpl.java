package com.beisi.module.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.beisi.module.sys.dao.SysUserDao;
import com.beisi.module.sys.entity.SysUser;
import com.beisi.module.sys.service.SysUserRoleService;
import com.beisi.module.sys.service.SysUserService;
import com.beisi.module.sys.shiro.ShiroUtil;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	@Override
	public SysUser getByUserName(String userName) {
		return sysUserDao.getByUserName(userName);
	}

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 * @return
	 */
	@Override
	public int updatePassword(String cUsrId, String password, String newPassword,String updateUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", cUsrId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		map.put("updateUser", updateUser);
		map.put("updateTime", new Date());
		return sysUserDao.updatePassword(map);
	}

	/**
	 * 查询用户所有的权限
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> listAllperms(String cUsrId) {
		return sysUserDao.listAllperms(cUsrId);
	}

	/**
	 * 查询用户的所有菜单ID
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> listAllPrmissionsId(String cUusrId) {
		return sysUserDao.listAllPrmissionsId(cUusrId);
	}

	/**
	 * 保存用户
	 * @param sysUser
	 */
	@Override
	public void saveUser(SysUser sysUser) {
		SysUser dbUser = sysUserDao.getByUserName(sysUser.getcUsrName());
		if(dbUser != null)throw new DuplicateKeyException("用户名为“"+sysUser.getcUsrName()+"”已存在！");
		String salt = sysUser.getcUsrName();//使用用户名作为盐值
		String simpleHash = ShiroUtil.shiroMD5(sysUser.getcUsrPassword(), salt);
		sysUser.setcUsrPassword(simpleHash);
		sysUserDao.saveUser(sysUser);
		sysUserRoleService.saveUserRole(sysUser);
	}

	/**
	 * 批量删除用户
	 * @param userIds
	 */
	@Override
	public void deleteBatchByUserIds(String[] userIds) {
		sysUserDao.deleteBatchByUserIds(userIds);
	}

	/**
	 * 修改用户
	 * @param sysUser
	 */
	@Override
	public void updateUser(SysUser sysUser) {
		SysUser dbUser = sysUserDao.getByUserName(sysUser.getcUsrName());
		if(dbUser == null || !dbUser.getcUsrId().equals(sysUser.getcUsrId()))throw new SecurityException();//非法操作，更改了用户名
		if (StringUtils.isBlank(sysUser.getcUsrPassword())) {
			sysUser.setcUsrPassword(null);
		} else {
			String simpleHash = ShiroUtil.shiroMD5(sysUser.getcUsrPassword(), sysUser.getcUsrName());
			sysUser.setcUsrPassword(simpleHash);
		}
		sysUserDao.updateUser(sysUser);
		sysUserRoleService.saveUserRole(sysUser);
	}

	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 */
	@Override
	public SysUser getByUserId(String userId) {
		return sysUserDao.getByUserId(userId);
	}

	/**
	 * 查询用户列表
	 * @param map
	 * @return
	 */
	@Override
	public List<SysUser> listUser(Map<String, Object> map) {
		return sysUserDao.listUser(map);
	}

	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	@Override
	public int countUser(Map<String, Object> map) {
		return sysUserDao.countUser(map);
	}

	/**
	 * 修改最后登陆时间
	 */
	@Override
	public void updateLoginTime(SysUser user) {
		sysUserDao.updateUser(user);
		
	}

}
