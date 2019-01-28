package com.beisi.shiro.service.sys;

import java.util.List;

import com.beisi.shiro.model.sys.Resource;


public interface ResourceService extends BaseService<Resource> {

	void initPathes(List<String> pathes);

}
