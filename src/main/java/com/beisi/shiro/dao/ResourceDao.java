package com.beisi.shiro.dao;

import org.apache.ibatis.annotations.Param;

public interface ResourceDao extends BaseDao{

	int selectCountResByPath(@Param("path") String path);

}
