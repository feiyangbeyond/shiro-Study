package com.beisi.shiro.service.sys.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.beisi.shiro.dao.sys.BaseDao;
import com.beisi.shiro.service.sys.BaseService;
import com.beisi.shiro.utils.MapToEntityTool;

/**
 * BaseServiceImpl做成抽象类
 * @author Administrator
 *
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	/**
	 * 获取baseDao对象的解决方法
	 * @return
	 */
	public abstract BaseDao getBaseDao();
	
	/**
	 * 在增删改查方法中需要知道T到底是什么Class类型
	 */
	public Class<?> clazz;
	public String tableName;
	
	/**
	 * 获取父类泛型的class
	 */
	public BaseServiceImpl() {
//		clazz = (Class<?>) ((((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments())[0]);
		//拆分写理解
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType)type;
		Type[] types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];	
		//获取数据表的名字
		tableName = "t_" + clazz.getSimpleName().toLowerCase();
	}
	
	@Override
	public void add(T t) {		
		List<Object> list = new ArrayList<>();
		for (Field field : t.getClass().getDeclaredFields()) {//可访问私有类型的字段
			field.setAccessible(true);//打开获取private修饰的书信权限  权限
			try {
				list.add(field.get(t));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		getBaseDao().add(tableName, list.toArray());
	}
	
	@Override
	public void addForNotMatch(Object[] fielsNames,Object[] fieldValues) {
		//这个方法可以是不相同个数
		getBaseDao().addForNotMatch(tableName,fielsNames,fieldValues);
	}

	@Override
	public void delete(int id) {
		getBaseDao().delete(tableName, id);		
	}

	@Override
	public void update(T t) {
		List<Object> list = new ArrayList<>();
		int id = 0;
		for (Field field : t.getClass().getDeclaredFields()) {//可访问私有类型的字段
			field.setAccessible(true);//打开获取private修饰的书信权限  权限
			try {
				if(field.get(t)==null) {
					continue;
				}
				if("id".equals(field.getName())) {
					id = (Integer) field.get(t);
					continue;
				}
				//剩下的才是要修改到数据表里的字段值
				//构造update中set后面的等式"字段名=修改后的字段值"
				list.add(field.getName() + "=" + "'" + field.get(t) + "'");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		getBaseDao().update(tableName,id,list.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T select(int id) {
		Map<Object,Object> rsMap = getBaseDao().select(tableName, id);
		//需要把Map转型为T
		return (T) MapToEntityTool.map2entity(rsMap, clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectAll() {
		List<Map<Object, Object>> rsList = getBaseDao().selectAll(tableName);
		List<T> list = new ArrayList<>();
		T t = null;
		for(Map<Object,Object> map : rsList) {
			//也需要把list集合里的map转型为T
			t = (T) MapToEntityTool.map2entity(map, clazz);
			list.add(t);
		}
		return list;
	}

}
