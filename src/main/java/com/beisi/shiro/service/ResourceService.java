package com.beisi.shiro.service;

import java.util.List;

import com.beisi.shiro.model.Resource;

public interface ResourceService extends BaseService<Resource> {

	void initPathes(List<String> pathes);

}
