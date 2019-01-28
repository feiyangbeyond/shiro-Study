package com.beisi.shiro.service.sys;

import java.util.List;

import com.beisi.shiro.model.sys.User;
import com.github.pagehelper.PageInfo;


public interface UserService extends BaseService<User>{
	
	//添加用户
	void addUser(User user, Integer[] roleIds);

	/**
	 * 关联查询（用户和角色信息）
	 * 获取所有的用户记录信息，实现关联查询，关联role
	 * @return
	 */
	List<User> selectRelevanceUser();
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<User> selectUsersByPage(int pageNum,int pageSize);
	
	/**
	 * 根据id查询用户记录
	 * @param id
	 * @return
	 */
	User selectRelUserByUid(Integer uid);

	void updateUser(User user, Integer[] roleIds);

	void deleteByUidRelRole(Integer id);

	void batchDelUsersByIds(Integer[] uidArr);

	PageInfo<User> selectUsersBySearchPage(int pageNum, int pageSize,String userInfo);	
	
}
