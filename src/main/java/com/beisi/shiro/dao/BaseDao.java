package com.beisi.shiro.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseDao {

	//增删改查
	public void add(@Param("tableName") String tableName,@Param("objects") Object[] objects);
	
	public void addForNotMatch(@Param("tableName")String tableName,@Param("fielsNames")Object[] fielsNames,@Param("fieldValues") Object[] fieldValues);
	
	public void delete(@Param("tableName") String tableName,@Param("id") Integer id);
	
	public void update(@Param("tableName") String tableName,@Param("id") Integer id,@Param("objects") Object[] objects);
	
	public Map<Object,Object> select(@Param("tableName") String tableName,@Param("id") Integer id);
	
	public List<Map<Object,Object>> selectAll(@Param("tableName") String tableName);


}
