package com.beisi.module.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.controller.BaseController;
import com.beisi.common.util.PageUtil;
import com.beisi.common.util.QueryUtil;
import com.beisi.common.util.Result;
import com.beisi.module.sys.service.SysOnlineService;

@Controller
@RequestMapping("/sys/online")
public class SysOnlineController extends BaseController{
	@Autowired
	private SysOnlineService sysOnlineService;
	
	/**
	 * 在线用户列表界面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "/index" })
	public String onlinePage() {
		return "sys/online";
	}

	/**
	 * 在线用户
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:online:list")
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> onlineList(@RequestParam Map<String, Object> params) {
		QueryUtil query = new QueryUtil(params);
		List<Map<String, Object>> queryList = null;
		List<Map<String, Object>> list = this.sysOnlineService.listOnline(getSysUserId());
		int total = list.size();
		int start = (query.getPage()-1)*query.getLimit();
		int end = start + query.getLimit();
		queryList = list.subList(start, end > list.size() ? list.size() : end);
		PageUtil pageUtil = new PageUtil(queryList, total, query.getLimit(), query.getPage());
		return Result.success().put("page", pageUtil);
	}
	
	/**
	 * 踢在线用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/offline")
	@RequiresPermissions("sys:online:offline")
	@ResponseBody
	public Map<String, Object> offline(@RequestBody String[] sessionIds) {
		this.sysOnlineService.offOnline(sessionIds,getSysUserId());
		return Result.success();
	}


}
