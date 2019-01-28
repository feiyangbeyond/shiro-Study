package com.beisi.shiro.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beisi.shiro.model.sys.User;


public interface UserDao extends BaseDao{
	//不用再写重复的方法，只需要在这里写，userDao特有的，不重复的方法
	
	//根据用户名称获取用户对象的方法
	public User getUserByName(@Param("username")String username);

	public List<User> selectRelevanceUser();

	/**
	 * 通过user的id查询对应的user对象，关联上role对象
	 */
	public User getUserByUid(@Param("uid") Integer uid);

	public List<User> selectUsersBySearchPage(@Param("userInfo") String userInfo);

	public User selectUserByUserInfo(String userInfo);
}
