package com.beisi.shiro.service.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.beisi.shiro.dao.PermissionDao;
import com.beisi.shiro.model.Permission;

public class FilterChainDefinitionMapFactory {
	@Autowired
	private PermissionDao permissionDao;

	public Map<String, String> getFilterChainDefinitionMap(){
		//从数据库中获取数据，构造出一个Map<String, String> 
		List<Permission> permissions = this.permissionDao.getAllPermissions();

		//定一个有顺序的map
		LinkedHashMap<String,String> permsMap = new LinkedHashMap<>();
		
		for(Permission permission : permissions) {
			if(permission.getPname().contains("p:")) {//如果存在，则构造成perms[userlist]样子
				String p = permission.getPname().replaceAll("p:","");//删除前缀
				permsMap.put(permission.getUrl(), "perms["+p+"]");
			}else {
				permsMap.put(permission.getUrl(),permission.getPname());
			}
		}

		return permsMap;
	}
}
