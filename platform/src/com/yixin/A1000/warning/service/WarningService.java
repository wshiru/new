/*
 * Project platform
 *
 * Classname WarningService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 14:26
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.model.WarningDict;
import com.yixin.framework.base.model.Page;

/**
 * 告警服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WarningService {

	/**
	 * 添加告警
	 * @param warning
	 */
	public abstract void addWarning(Warning warning);

	/**
	 * 添加多个告警
	 * @param warnings
	 */
	public abstract void addWarnings(List<Warning> warnings);
	
	/**
	 * 修改告警信息
	 * @param warning
	 */
	public abstract void editWarning(Warning warning);
	
	/**
	 * 删除告警
	 * @param warning
	 */
	public abstract void deleteWarning(Warning warning);
	/**
	 * 删除告警集合
	 * @param warnings
	 */
	public abstract void deleteWarnings(Collection<Warning> warnings);
	/**
	 * 获取告警
	 * @param id
	 * 			告警ID
	 * @return
	 */
	public abstract Warning getWarning(String id);
	
	/**
	 * 获取全部告警
	 * @return
	 */
	public abstract List<Warning> getAllWarnings();		
	/**
	 * 根据监测装置查询告警
	 * @param warningDict
	 * 			告警类型，可为Null
	 * @param noDispose
	 * 			是否已查阅，可为Null
	 * @param sensorCode
	 * 			监测装置编码，可为Null
	 * @param beginTime
	 * 			开始时间，可为Null
	 * @param endTime
	 * 			结束时间，可为Null
	 * @return
	 */
	public abstract List<Warning> getWarningsBySensor(String lineId, WarningDict warningDict, String sensorCode, 
			Date beginTime, Date endTime, Integer operatorState);
	/**
	 * 根据监测带来查询告警
	 * @param warningDict
	 * 			告警类型，可为Null
	 * @param noDispose
	 * 			是否已查阅 ，可为Null
	 * @param cmaCode
	 * 			监测代理编码，可为Null
	 * @param beginTime
	 * 			开始时间，可为Null
	 * @param endTime
	 * 			结束时间，可为Null
	 * @return
	 */
	public abstract List<Warning> getWarningsByCMA(String lineId, WarningDict warningDict, 
			String cmaCode, Date beginTime, Date endTime);
	/**
	 * 获取全部告警分页数据
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页面大小
	 * @return
	 */
	public abstract Page<Warning> getPageWarnings(long pageNo, long pageSize);
	/**
	 * 根据监测装置查询告警分页数据
	 * @param warningDict
	 * 			告警类型，可为Null
	 * @param noDispose
	 * 			是否已查阅，可为Null
	 * @param sensorCode
	 * 			监测装置编码，可为Null
	 * @param beginTime
	 * 			开始时间，可为Null
	 * @param endTime
	 * 			结束时间，可为Null
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页面大小
	 * @return
	 */
	public abstract Page<Warning> getPageWarningsBySensor(String lineId,WarningDict warningDict, 
			String sensorCode, Date beginTime, Date endTime, Integer operatorState,long pageNo, long pageSize);
	/**
	 * 根据监测带来查询告警分页数据
	 * @param warningDict
	 * 			告警类型，可为Null
	 * @param noDispose
	 * 			是否已查阅，可为Null
	 * @param cmaCode
	 * 			监测代理编码，可为Null
	 * @param beginTime
	 * 			开始时间，可为Null
	 * @param endTime
	 * 			结束时间，可为Null
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页面大小
	 * @return
	 */
	public abstract Page<Warning> getPageWarningsByCMA(String lineId,WarningDict warningDict, 
			String cmaCode, Date beginTime, Date endTime, long pageNo, long pageSize);	
	
}
