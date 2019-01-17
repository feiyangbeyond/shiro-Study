package com.beisi.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.shiro.dao.BaseDao;
import com.beisi.shiro.dao.ResourceDao;
import com.beisi.shiro.model.Resource;
import com.beisi.shiro.service.ResourceService;


@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public BaseDao getBaseDao() {
		return resourceDao;
	}

	@Override
	public void initPathes(List<String> pathes) {
		//有两步考虑
		//1.把数据插入到数据库
		int resCount = 0;
		for(String path:pathes) {
			resCount = resourceDao.selectCountResByPath(path);
			if(resCount == 0) {
				this.add(new Resource(path));
			}
		}
	}

}
