package com.beisi.shiro.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapToEntityTool {
	
	/**
	 * 缓存类的属性信息
	 */
	private static Map<String, EntityCacheItem> convertItemCache = new HashMap<>();

	/**
	 * 将map 转换为 entity
	 * 
	 * @return
	 */
	public static <T> T map2entity(Map<Object, Object> map, Class<T> entityClass) {
		// 通过entityClass参数，获取类型里的属性名称集合
		// 通过entityClass参数，获取类型里的set方法的map集合
//		Field[] fields = entityClass.getDeclaredFields();
//		List<String> fieldNameList = new ArrayList<>();
//		String fieldName;
//		String setMethodName;
//		Method setMethod = null;
//		Map<String,Method> setMethodMap = new HashMap<>();
//		for (Field field : fields) {
//			field.setAccessible(true);// 获取去掉private修饰的属性
//			fieldName = field.getName();// 拿到属性对象对应的名称
//			fieldNameList.add(fieldName);
//			//获取方法名称
//			setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//			try {
//				setMethod = entityClass.getDeclaredMethod(setMethodName, field.getType());
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//			setMethodMap.put(fieldName, setMethod);
//		}
		
		//因为添加了缓存机制
		//直接尝试从缓存中获取EntityCacheItem对象
		EntityCacheItem entityCacheItem = convertItemCache.get(entityClass.getName());
		if(entityCacheItem == null) {
			entityCacheItem = EntityCacheItem.createEntityCacheItem(entityClass);
			convertItemCache.put(entityClass.getName(), entityCacheItem);//如果缓存中没有，则创建，然后放到缓存中
		}
		
		// 通过entityClass参数，获取类型里的属性名称集合
		List<String> fieldNameList = entityCacheItem.getFieldNameList();
		// 通过entityClass参数，获取类型里的set方法的map集合
		Map<String,Method> setMethodMap = entityCacheItem.getSetMethodMap();
		
		String key;
		String key1;
		String key2;
		Map<Object,Object> targetMap = new HashMap<>();
		
		for(Map.Entry<Object, Object> entry:map.entrySet()) {
			key = entry.getKey().toString();
			while(key.contains("_")) {
				key1 = key.substring(0,key.indexOf('_'));
				key2 = key.substring(key.indexOf('_')+1);
				key2 = key2.substring(0,1).toUpperCase() + key2.substring(1);
				key = key1 + key2;
			}
			
			targetMap.put(key, entry.getValue());
		}
		
		// 把map里的值，放到entity对象里
		T entity = null;
		try {
			entity = entityClass.newInstance();//通过反射的方式获取类型的实例对象
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//获取map中的值，注入到实体类中
		Object fieldVale = null;
		Method setM = null;
		Class<?>[] parameterTypes = null;
		for (String strFieldName : fieldNameList) {
			fieldVale = targetMap.get(strFieldName);
			if (fieldVale == null)
				continue;
			setM = setMethodMap.get(strFieldName);
			if (setM == null)
				continue;
			parameterTypes = setM.getParameterTypes();//拿到set方法的参数类型对象
			if(parameterTypes == null || parameterTypes.length>1) {//判断参数是否存在，或参数个数大于1，只能有一个
				continue;
			}
			if(parameterTypes[0].isAssignableFrom(fieldVale.getClass())) {//判断参数类型是否与map中的值类型是否一致
				//一致
				try {
					setM.invoke(entity, fieldVale);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}else {
				System.out.println("不同类型：set方法中的参数类型：" + parameterTypes[0] + "======数据库中查询的结果集中数据类型：" + fieldVale.getClass());
			}
		}		
		return entity;
	}
	
	//实现缓存机制
	static class EntityCacheItem{
		
		private EntityCacheItem() {}//私有化构造器，不能直接实例化
		
		private List<String> fieldNameList = new ArrayList<>();
		private Map<String,Method> setMethodMap = new HashMap<>();
		public List<String> getFieldNameList() {
			return fieldNameList;
		}
		public Map<String, Method> getSetMethodMap() {
			return setMethodMap;
		}
		
		public void parseEntity(Class<?> entityClass) {
			Field[] fields = entityClass.getDeclaredFields();
			String fieldName;
			String setMethodName;
			Method setMethod = null;
			for (Field field : fields) {
				field.setAccessible(true);// 获取去掉private修饰的属性
				fieldName = field.getName();// 拿到属性对象对应的名称
				fieldNameList.add(fieldName);
				//获取方法名称
				setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					setMethod = entityClass.getDeclaredMethod(setMethodName, field.getType());
				} catch (Exception e) {
					e.printStackTrace();
				}
				setMethodMap.put(fieldName, setMethod);
			}
		}
		
		public static EntityCacheItem createEntityCacheItem(Class<?> entityClass) {
			EntityCacheItem ci = new EntityCacheItem();
			ci.parseEntity(entityClass);
			return ci;
		}		
	}
}
