package com.beisi.module.job.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.util.PageUtil;
import com.beisi.common.util.QueryUtil;
import com.beisi.common.util.Result;
import com.beisi.module.job.entity.ScheduleJobLog;
import com.beisi.module.job.service.ScheduleJobLogService;

/**
 * 任务调度日志
 *
 */
@Controller
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志界面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "index" })
	public String sechdule() {
		return "job/schedule_log";
	}

	/**
	 * 定时任务列表
	 * 
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:schedule:log")
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> listScheduleLog(
			@RequestParam Map<String, Object> params) {
		QueryUtil query = new QueryUtil(params);
		List<ScheduleJobLog> jobList = scheduleJobLogService.listJobLog(query);
		int total = scheduleJobLogService.countJobLog(query);
		PageUtil pageUtil = new PageUtil(jobList, total, query.getLimit(),
				query.getPage());
		return Result.success().put("page", pageUtil);
	}

	/**
	 * 定时任务日志信息
	 * 
	 * @param logId
	 * @return
	 */
	@RequestMapping("/info/{logId}")
	@ResponseBody
	public Map<String, Object> infoScheduleLog(@PathVariable("logId") String logId) {
		ScheduleJobLog jobLog = scheduleJobLogService.getByJobLogId(logId);
		return Result.success().put("log", jobLog);
	}

}
