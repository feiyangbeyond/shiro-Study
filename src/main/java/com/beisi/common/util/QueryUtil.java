package com.beisi.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.beisi.common.xss.SQLFilter;

/**
 * 分页查询辅助类
 * 
 */
public class QueryUtil extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	// 当前页码
	private int page;

	// 每页条数
	private int limit;

	public QueryUtil(Map<String, Object> params) {
		this.putAll(params);

		this.page = Integer.parseInt(params.get("page").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());

		this.put("offset", (page - 1) * limit);
		this.put("page", page);
		this.put("limit", limit);

		
		String sidx = params.get("sidx").toString();
		String order = params.get("order").toString();
		// 防止SQL注入
		this.put("sidx", SQLFilter.sqlValidate(sidx));
		this.put("order", SQLFilter.sqlValidate(order));

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
