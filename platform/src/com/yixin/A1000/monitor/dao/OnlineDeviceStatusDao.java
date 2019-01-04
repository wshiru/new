/*
 * Project platform
 *
 * Class LineDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午03:49:01
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.Page;

/**
 * 线路DAO接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface OnlineDeviceStatusDao extends BaseDao<OnlineDeviceStatus, String> {
	public abstract Page<Map<String, Object>> getPageOnlineRateStatistics(final  Date beginDate ,final Date endDate , final String sensorTypeId , final long pageNo, final long pageSize) ;	
	public abstract List<Map<String, Object>> getAllOnlineRateStatistics(final  Date beginDate ,final Date endDate , final String sensorTypeId) ;
}
