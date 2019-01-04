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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.video.dao.RecordFileDao;
import com.yixin.A1000.video.model.RecordFile;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RecordFileService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;

/**
 * 视频录像文件  逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class RecordFileServiceImpl implements RecordFileService {

	private RecordFileDao recordFileDao;
	
	public void setRecordFileDao(RecordFileDao recordFileDao){
		this.recordFileDao = recordFileDao;
	}

	@Override
	public void addRecordFile(RecordFile recordFile) {
		this.recordFileDao.save(recordFile);
		
	}

	@Override
	public void editRecordFile(RecordFile recordFile) {
		this.recordFileDao.save(recordFile);		
	}

	@Override
	public void deleteRecordFile(RecordFile recordFile) {
		this.recordFileDao.delete(recordFile);
		
	}

	@Override
	public void deleteRecordFiles(Collection<RecordFile> recordFiles) {
		this.recordFileDao.deleteAll(recordFiles);
		
	}

	@Override
	public RecordFile getRecordFile(String id) {
		return this.recordFileDao.findById(id);
	}

	@Override
	public List<RecordFile> getAllRecordFiles() {
		return this.recordFileDao.getAll();
	}

	@Override
	public Page<RecordFile> getPageRecordFiles(long pageNo, long pageSize) {
		return this.recordFileDao.getPage(pageNo, pageSize);
	}

	@Override
	public Page<RecordFile> getPageRecordFiles(VideoServer videoServer,Integer filetype,
			Date beginTime,Date endTime,long pageNo, long pageSize) {
		 Map<String, Object> map = new HashMap<String ,Object>();
		 if(videoServer!=null)
		 {
			 map.put("videoServer", videoServer);
		 }
		 if(filetype!=null)
		 {
			 map.put("fileType", filetype);
		 }
		 DateProperty dateProperty = new DateProperty("createTime",beginTime,endTime);
		 dateProperty.setBeginTime(beginTime);
		 dateProperty.setEndTime(endTime);
		 return recordFileDao.getPageByProperties(map,dateProperty, pageNo, pageSize);
	}

	@Override
	public List<RecordFile> getRecordFileByVideoServer(VideoServer videoServer) {
		return this.recordFileDao.getAllByProperty("videoServer", videoServer);
	}

	@Override
	public Page<RecordFile> getPageRecordFiles(Sensor sensor, Integer filetype,
			Date beginTime,Date endTime,long pageNo, long pageSize) {
		 Map<String, Object> map = new HashMap<String ,Object>();
		 if(sensor!=null)
		 {
			 map.put("videoServer.sensor", sensor);
		 }
		 if(filetype!=null)
		 {
			 map.put("fileType", filetype);
		 }
		 DateProperty dateProperty = new DateProperty("createTime",beginTime,endTime);
		 dateProperty.setBeginTime(beginTime);
		 dateProperty.setEndTime(endTime);
		 dateProperty.setDataOrder(DataOrder.DESC);
		 return recordFileDao.getPageByProperties(map,dateProperty, pageNo, pageSize);

	}

	@Override
	public List<RecordFile> getRecordFiles(Sensor sensor, Integer filetype,
			Date beginTime, Date endTime) {
		 Map<String, Object> map = new HashMap<String ,Object>();
		 if(sensor!=null)
		 {
			 map.put("videoServer.sensor", sensor);
		 }
		 if(filetype!=null)
		 {
			 map.put("fileType", filetype);
		 }
		 DateProperty dateProperty = new DateProperty("createTime",beginTime,endTime);
		 dateProperty.setBeginTime(beginTime);
		 dateProperty.setEndTime(endTime);
		 dateProperty.setDataOrder(DataOrder.DESC);
		 return recordFileDao.getAllByProperties(map,dateProperty);
	}
 
	
	 
}
