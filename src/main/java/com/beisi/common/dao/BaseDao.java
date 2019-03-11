package com.beisi.common.dao;

import java.util.List;
import java.util.Map;


/**
 * 基础Dao
 * 需要在Mapper文件中配置相应的SQL文件
 * 
 * @param <T>	实体类
 */
public interface BaseDao<T> {


	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	

	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] ids);
	
	
	int update(T t);
	
	int update(Map<String, Object> map);
	
	
	T getByObject(Object id);
	
	List<T> listObject(Object id);
	
	List<T> listObject(Map<String, Object> map);
	
	int countTotal(Map<String, Object> map);

	int countTotal();
}
