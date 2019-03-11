package com.beisi.module.job.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beisi.module.job.dao.ScheduleJobLogDao;
import com.beisi.module.job.entity.ScheduleJobLog;
import com.beisi.module.job.service.ScheduleJobLogService;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;

	@Override
	public ScheduleJobLog getByJobLogId(String jobId) {
		return scheduleJobLogDao.getByJobLogId(jobId);
	}

	@Override
	public List<ScheduleJobLog> listJobLog(Map<String, Object> map) {
		return scheduleJobLogDao.listJobLog(map);
	}

	@Override
	public int countJobLog(Map<String, Object> map) {
		return scheduleJobLogDao.countJobLog(map);
	}

	@Override
	public void saveJobLog(ScheduleJobLog log) {
		scheduleJobLogDao.saveJobLog(log);
	}

}
