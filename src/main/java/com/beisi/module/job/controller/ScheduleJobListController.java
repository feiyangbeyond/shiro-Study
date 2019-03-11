package com.beisi.module.job.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.controller.BaseController;
import com.beisi.common.util.GenerateUUID;
import com.beisi.common.util.PageUtil;
import com.beisi.common.util.QueryUtil;
import com.beisi.common.util.Result;
import com.beisi.module.job.entity.ScheduleJobList;
import com.beisi.module.job.service.ScheduleJobListService;

/**
 * 任务调度
 * 
 */
@Controller
@RequestMapping("/sys/schedule")
public class ScheduleJobListController extends BaseController{

	@Autowired
	private ScheduleJobListService scheduleJobListService;

	/**
	 * 定时任务界面
	 * 
	 * @return
	 */
	@RequestMapping({ "", "/", "index" })
	public String sechdule() {
		return "job/schedule";
	}

	/**
	 * 定时任务列表
	 * 
	 * @param params
	 * @return
	 */
	@RequiresPermissions("sys:schedule:list")
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> listSchedule(@RequestParam Map<String, Object> params) {
		QueryUtil query = new QueryUtil(params);
		List<ScheduleJobList> jobList = scheduleJobListService.listJob(query);
		int total = scheduleJobListService.countJob(query);
		PageUtil pageUtil = new PageUtil(jobList, total, query.getLimit(),query.getPage());
		return Result.success().put("page", pageUtil);
	}

	/**
	 * 保存定时任务
	 * 
	 * @param schedule
	 * @return
	 */
	@RequiresPermissions("sys:schedule:save")
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> saveSchedule(@RequestBody ScheduleJobList schedule) {
		schedule.setJobId(GenerateUUID.getUUID());
		schedule.setCreateUser(getSysUserId());
		schedule.setCreateTime(new Date());
		scheduleJobListService.saveJob(schedule);
		return Result.success();
	}

	/**
	 * 定时任务信息
	 * 
	 * @return
	 */
	@RequiresPermissions("sys:schedule:info")
	@RequestMapping("/info/{jobId}")
	@ResponseBody
	public Map<String, Object> infoSchedule(@PathVariable("jobId") String jobId) {
		ScheduleJobList schedule = scheduleJobListService.getByJobId(jobId);
		return Result.success().put("schedule", schedule);
	}

	/**
	 * 修改定时任务
	 * 
	 * @param schedule
	 * @return
	 */
	@RequiresPermissions("sys:schedule:update")
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> updateSchedule(@RequestBody ScheduleJobList schedule) {
		schedule.setUpdateUser(getSysUserId());
		schedule.setUpdateTime(new Date());
		ScheduleJobList jobData = scheduleJobListService.getByJobId(schedule.getJobId());
		schedule.setJobStatus(jobData.getJobStatus());
		scheduleJobListService.updateJob(schedule);
		return Result.success();
	}

	/**
	 * 删除任务
	 * 
	 * @param jobIds
	 * @return
	 */
	@RequiresPermissions("sys:schedule:delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteSchedule(@RequestBody String[] jobIds) {
		scheduleJobListService.deleteBatchByJobIds(jobIds);
		return Result.success();
	}

	/**
	 * 暂停定时任务
	 * 
	 * @param jobIds
	 * @return
	 */
	@RequiresPermissions("sys:schedule:pause")
	@RequestMapping("/pause")
	@ResponseBody
	public Map<String, Object> pauseSchedule(@RequestBody String[] jobIds) {
		scheduleJobListService.pause(jobIds,getSysUserId());
		return Result.success();
	}

	/**
	 * 恢复定时任务
	 * 
	 * @param jobIds
	 * @return
	 */
	@RequiresPermissions("sys:schedule:resume")
	@RequestMapping("/resume")
	@ResponseBody
	public Map<String, Object> resumeSchedule(@RequestBody String[] jobIds) {
		scheduleJobListService.resume(jobIds,getSysUserId());
		return Result.success();
	}

	/**
	 * 立即执行任务
	 * 
	 * @param jobIds
	 * @return
	 */
	@RequiresPermissions("sys:schedule:run")
	@RequestMapping("/run")
	@ResponseBody
	public Map<String, Object> runSchedule(@RequestBody String[] jobIds) {
		scheduleJobListService.run(jobIds);
		return Result.success();
	}
}
