package com.beisi.shiro.service.impl;

import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.shiro.dao.BaseDao;
import com.beisi.shiro.dao.UserDao;
import com.beisi.shiro.dao.UserRoleDao;
import com.beisi.shiro.model.User;
import com.beisi.shiro.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public BaseDao getBaseDao() {
		return userDao;
	}

	@Override
	public void addUser(User user, Integer[] roleIds) {
		// 完成添加用户的功能，分两部分
		this.addForNotMatch(new Object[] { "username", "password", "email", "phone", "enable", "add_date" },
				new Object[] { user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), 1,
						new Date() });
		// 根据新添加激怒的用户的用户名称来查询出刚刚添加进数据库的用户(根据用户名获取用户对象方法)
		User u = userDao.getUserByName(user.getUsername());
		// 添加用户与角色关联表
		for (Integer rid : roleIds) {
			// 这里的id也需要使用null占位
			this.userRoleDao.add("t_user_role", new Object[] { null, u.getId(), rid });
		}
	}

	@Override
	public List<User> selectRelevanceUser() {
		List<User> users = this.userDao.selectRelevanceUser();
		return users;
	}

	@Override
	public PageInfo<User> selectUsersByPage(int pageNum, int pageSize) {
		// 分页查询
		Page<User> pager = PageHelper.startPage(pageNum, pageSize);
		List<User> userDatas = this.userDao.selectRelevanceUser();
		PageInfo<User> info = new PageInfo<>(userDatas);
		return info;
	}

	@Override
	public User selectRelUserByUid(Integer uid) {
		User user = this.userDao.getUserByUid(uid);
		return user;
	}

	@Override
	public void updateUser(User user, Integer[] roleIds) {
		if (user.getPassword().trim().equals("")) {
			user.setPassword(null);
		}
		// 修改用户的第一步
		this.update(user);
		// 修改用户的第二部，修改user关联的roles
		// 1.删除用户id是user.getId()的所有的关联的role
		userRoleDao.deleteByUid(user.getId());
		// 2.吧接受到的roleIds这里的新的用户管理的role的id的数组，重新添加到t_user_role关系表中
		for (Integer rid : roleIds) {
			// 这里的id也需要使用null占位
			this.userRoleDao.add("t_user_role", new Object[] { null, user.getId(), rid });
		}

	}

	@Override
	public void deleteByUidRelRole(Integer id) {
		// 第一步：删除user关联的关系删除
		this.userRoleDao.deleteByUid(id);
		// 第二部：删除用户本身
		this.delete(id);
	}

	@Override
	public void batchDelUsersByIds(Integer[] uidArr) {
		for (Integer id : uidArr) {
			this.deleteByUidRelRole(id);
		}
	}

	@Override
	public PageInfo<User> selectUsersBySearchPage(int pageNum, int pageSize, String userInfo) {
		// 分页查询
		Page<User> pager = PageHelper.startPage(pageNum, pageSize);
		List<User> userDatas = this.userDao.selectUsersBySearchPage("%" + userInfo + "%");
		PageInfo<User> info = new PageInfo<>(userDatas);
		return info;
	}

	@Override
	public User login(String userInfo, String password) {
		// userInfo,查询user表里有没有相应记录
		User user = this.userDao.selectUserByUserInfo(userInfo);
		if (user == null) {
			throw new RuntimeException("用户名或密码有误！");
		}
		// 有对应的用户的情况下，进一步的验证
		if (!password.equals(user.getPassword()))
			throw new RuntimeException("用户名或密码有吴！");
		return user;
	}

}
