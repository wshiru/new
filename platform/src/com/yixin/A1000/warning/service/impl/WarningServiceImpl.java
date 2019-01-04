/*
 * Project platform
 *
 * Classname WarningServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 15:04
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.warning.dao.WarningDao;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.model.WarningDict;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;

/**
 * 告警服务接口实现
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningServiceImpl implements WarningService {

	/* 告警DAO */
	private WarningDao warningDao;
	
	/**
	 * 设置告警DAO
	 * @param warningDao
	 */
	public void setWarningDao(WarningDao warningDao) {
		this.warningDao = warningDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#addWarning(com.yixin.ca2000.warning.model.Warning)
	 */
	@Override
	public void addWarning(Warning warning) {
		if(null != warning)
			this.warningDao.save(warning);
	}
	
	
	/* (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#addWarnings(java.util.List)
	 */
	@Override
	public void addWarnings(List<Warning> warnings) {
		this.warningDao.saveAll(warnings);
	}
	
	@Override
	public void editWarning(Warning warning) {
		if(null != warning)
			this.warningDao.save(warning);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#deleteWarning(com.yixin.ca2000.warning.model.Warning)
	 */
	@Override
	public void deleteWarning(Warning warning) {
		if(null != warning)
			this.warningDao.delete(warning);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#deleteWarnings(java.util.Collection)
	 */
	@Override
	public void deleteWarnings(Collection<Warning> warnings) {
		if(null != warnings)
			for(Warning warning : warnings)
				this.deleteWarning(warning);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getWarning(java.lang.String)
	 */
	@Override
	public Warning getWarning(String id) {
		return this.warningDao.findById(id);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getAllWarnings()
	 */
	@Override
	public List<Warning> getAllWarnings() {
		return this.warningDao.getAll();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getWarningsBySensor(com.yixin.ca2000.warning.model.WarningDict, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Warning> getWarningsBySensor(String lineId, WarningDict warningDict, 
			String sensorCode, Date beginTime, Date endTime, Integer opeatorState) {
		Map<String, Object> map = buildMapProperties(warningDict, null, sensorCode, lineId,opeatorState);
		return getRawWarnings(map, buildDateProperty(beginTime, endTime));
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getWarningsByCMA(com.yixin.ca2000.warning.model.WarningDict, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Warning> getWarningsByCMA(String lineId, WarningDict warningDict,
			String cmaCode, Date beginTime, Date endTime) {
		Map<String, Object> map = buildMapProperties(warningDict, cmaCode, null ,lineId,null);
		return getRawWarnings(map, buildDateProperty(beginTime, endTime));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getPageWarnings(long, long)
	 */
	@Override
	public Page<Warning> getPageWarnings(long pageNo, long pageSize) {
		return this.warningDao.getPage(buildDateProperty(null, null), pageNo, pageSize);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getPageWarningsBySensor(com.yixin.ca2000.warning.model.WarningDict, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<Warning> getPageWarningsBySensor(String lineId,WarningDict warningDict, 
			String sensorCode, Date beginTime, Date endTime, Integer opeatorState, long pageNo, long pageSize) {
		Map<String, Object> map = buildMapProperties(warningDict, null, sensorCode,lineId,opeatorState);	
		return this.getPageRawWarnings(map, buildDateProperty(beginTime, endTime), pageNo, pageSize);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningService#getPageWarningsByCMA(com.yixin.ca2000.warning.model.WarningDict, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<Warning> getPageWarningsByCMA(String lineId, WarningDict warningDict, 
			String cmaCode, Date beginTime, Date endTime, long pageNo, long pageSize) {
		Map<String, Object> map = buildMapProperties(warningDict, cmaCode, null, lineId,null);	
		return this.getPageRawWarnings(map, buildDateProperty(beginTime, endTime), pageNo, pageSize);
	}
	
	/**
	 * 查询告警
	 * @param map
	 * @param dateProperty
	 * @return
	 */
	private List<Warning> getRawWarnings(Map<String, Object> map, DateProperty dateProperty){
		if(null != map && null != dateProperty)				
			return this.warningDao.getAllByProperties(map, dateProperty);
		else if(null != map && null == dateProperty)
			return this.warningDao.getAllByProperties(map);
		else if(null == map && null != dateProperty)
			return this.warningDao.getAll(dateProperty);
		else 
			return this.warningDao.getAll();
	}
	
	/**
	 * 查询告警分页
	 * @param map
	 * @param dateProperty
	 * @return
	 */
	private Page<Warning> getPageRawWarnings(Map<String, Object> map, DateProperty dateProperty, 
											long pageNo, long pageSize){
		if(null != map && null != dateProperty)				
			return this.warningDao.getPageByProperties(map, dateProperty, pageNo, pageSize);
		else if(null != map && null == dateProperty)
			return this.warningDao.getPageByProperties(map, pageNo, pageSize);
		else if(null == map && null != dateProperty)
			return this.warningDao.getPage(dateProperty, pageNo, pageSize);
		else 
			return this.warningDao.getPage(pageNo, pageSize);
	}
	/**
	 * 构建多属性查询映射对象
	 * @param warningDict
	 * 			告警类型
	 * @param noDispose
	 * 			是否已查阅
	 * @param cmaCode
	 * 			监测代理编码
	 * @param sensorCode
	 * 			监测装置编码
	 * @return
	 */
	private Map<String, Object> buildMapProperties(WarningDict warningDict, String cmaCode, String sensorCode, String lineId, Integer operatorState){
		if(null == warningDict && null == cmaCode && null == sensorCode && null == lineId && null == operatorState)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != warningDict){
			String warningDictId = warningDict.getWarningDictId();
			if(null != warningDictId && !warningDictId.trim().isEmpty())
				map.put("warningDict", warningDict);
		}
		if(null != cmaCode)
			map.put("sensor.cmaInfo.cmaCode", cmaCode);
		if(null != sensorCode)
			map.put("sensor.sensorCode", sensorCode);
		if(null != lineId && lineId.length() != 0){
			map.put("sensor.tower.line.lineId", lineId);
		}
		if(null != operatorState)
			map.put("operationState", operatorState);
		
		if(map.size() == 0)
			map = null;
		return map;
	}
	/**
	 * 构建时间属性对象
	 * @param beginTime
	 * 			开始时间
	 * @param endTime
	 * 			结束时间
	 * @return
	 */
	private DateProperty buildDateProperty(Date beginTime, Date endTime) {	
		DateProperty dateProperty = new DateProperty();
		dateProperty.setPropertyName("createDate");
		dateProperty.setDataOrder(DataOrder.DESC);
		if(null != beginTime)
			dateProperty.setBeginTime(beginTime);
		if(null != endTime)
			dateProperty.setEndTime(endTime);	
		return dateProperty;
	}
}
