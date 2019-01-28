package com.beisi.shiro.service.sys;

import java.util.List;

public interface BaseService<T> {
	
	//增删改查
	
	/*
	 * 这个添加方法有局限性，只能是entity类的属性个数和表格的列数，他们是一个一对一的对应关系下才能使用
	 */
	public void add(T t);
	
	/*
	 * 这个方法可以是不相同个数
	 * fielsNames属性名，filedValues属性值
	 */
	public void addForNotMatch(Object[] fielsNames,Object[] fieldValues);
	
	public void delete(int id);
	
	public void update(T t);
	
	public T select(int id);
	
	public List<T> selectAll();
}
