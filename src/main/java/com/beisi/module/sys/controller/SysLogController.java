package com.beisi.module.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.beisi.common.controller.BaseController;
import com.beisi.common.util.PageUtil;
import com.beisi.common.util.QueryUtil;
import com.beisi.common.util.Result;
import com.beisi.module.sys.entity.SysLog;
import com.beisi.module.sys.service.SysLogService;

/**
 * 系统日志
 * 
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 日志界面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "/index" })
	public String log() {
		return "sys/log";
	}

	/**
	 * 日志列表
	 * 
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:log:list")
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> listLog(@RequestParam Map<String, Object> params) {
		QueryUtil query = new QueryUtil(params);
		List<SysLog> logList = sysLogService.listLog(query);
		int total = sysLogService.countLog(query);
		PageUtil pageUtil = new PageUtil(logList, total, query.getLimit(),query.getPage());
		System.out.println("------------------------"+JSON.toJSONString(pageUtil));
		return Result.success().put("page", pageUtil);
	}

}