/*
 * Project platform
 *
 * Class LineServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-22 下午12:06:55
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.video.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.video.dao.RecordPlanDao;
import com.yixin.A1000.video.model.RecordPlan;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RecordPlanService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;

/**
 * 视频录像计划 逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class RecordPlanServiceImpl implements RecordPlanService {

	private RecordPlanDao recordPlanDao;
	
	public void setRecordPlanDao(RecordPlanDao recordPlanDao){
		this.recordPlanDao = recordPlanDao;
	}

	@Override
	public void addRecordPlan(RecordPlan recordPlan) {				 
		this.recordPlanDao.save(recordPlan);
		
	}

	@Override
	public void editRecordPlan(RecordPlan recordPlan) {
		this.recordPlanDao.save(recordPlan);
		
	}

	@Override
	public void deleteRecordPlan(RecordPlan recordPlan) {
		this.recordPlanDao.delete(recordPlan);		
	}

	@Override
	public void deleteRecordPlans(Collection<RecordPlan> recordPlans) {
		this.recordPlanDao.deleteAll(recordPlans);		
	}

	@Override
	public RecordPlan getRecordPlan(String id) {
		return this.recordPlanDao.findById(id);
	}

	@Override
	public List<RecordPlan> getAllRecordPlans() {
		return this.recordPlanDao.getAll();
	}

	@Override
	public Page<RecordPlan> getPageRecordPlans(long pageNo, long pageSize) {
		return this.recordPlanDao.getPage(pageNo, pageSize);
	}

	@Override
	public Page<RecordPlan> getPageRecordPlans(VideoServer videoServer,
			long pageNo, long pageSize) {
		 Map<String, Object> map = new HashMap<String ,Object>();
		 map.put("videoServer", videoServer);	
		 //加这个就为了排序
		 DateProperty dateProperty = new DateProperty("startTime",null,null);
		 dateProperty.setBeginTime(null);
		 dateProperty.setEndTime(null);
		 dateProperty.setDataOrder(DataOrder.ASC);
		 return recordPlanDao.getPageByProperties(map,dateProperty, pageNo, pageSize);		 
	}

	@Override
	public List<RecordPlan> getRecordPlanByVideoServer(VideoServer videoServer) {
		return this.recordPlanDao.getAllByProperty("videoServer", videoServer);
	}
 
	
	 
}
