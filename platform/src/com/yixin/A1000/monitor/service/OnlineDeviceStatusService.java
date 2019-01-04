/*
 * Project platform
 *
 * Class OnlineDeviceStatusService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 下午03:20:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.framework.base.model.Page;

/**
 * 在线终端状态业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface OnlineDeviceStatusService {

	/**
	 * 在线终端
	 */
	public abstract List<OnlineDeviceStatus> getOnlineDeviceStatus();
	
	/**
	 * 在线率统计
	 * @param beginDate		开始时间
	 * @param endDate		结束时间
	 * @param pageNo		页号
	 * @param pageSize		每页大小
	 * @return
	 */
	public abstract Page<Map<String, Object>> getPageOnlineRateStatistics(final  Date beginDate ,final Date endDate ,final String sensorTypeId, final long pageNo, final long pageSize) ;	
	public abstract List<Map<String, Object>> getAllOnlineRateStatistics(final  Date beginDate ,final Date endDate ,final String sensorTypeId) ;	
}
