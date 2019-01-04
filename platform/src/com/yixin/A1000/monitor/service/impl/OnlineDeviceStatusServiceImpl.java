/*
 * Project platform
 *
 * Class OnlineDeviceStatusServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 下午03:21:51
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.monitor.dao.OnlineDeviceStatusDao;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.monitor.service.OnlineDeviceStatusService;
import com.yixin.framework.base.model.Page;

/**
 * 在线终端状态业务逻辑类
 * 
 * @version v1.0.0
 * @author
 */
public class OnlineDeviceStatusServiceImpl implements OnlineDeviceStatusService {

	/** 通讯服务类 */
	private CommService commService;

	/** 设备在线状态查询 **/
	private OnlineDeviceStatusDao onlineDeviceStatusDao;

	/**
	 * 设置 通讯服务类
	 * 
	 * @param commService
	 *            通讯服务类
	 */
	public void setCommService(CommService commService) {
		this.commService = commService;
	}

	/**
	 * 设置 设备在线状态查询 类
	 * 
	 * @param onlineDeviceStatusDao
	 *            the 设备在线状态查询 to set
	 */
	public void setOnlineDeviceStatusDao(
			OnlineDeviceStatusDao onlineDeviceStatusDao) {
		this.onlineDeviceStatusDao = onlineDeviceStatusDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.monitor.service.OnlineDeviceStatusService#
	 * getOnlineDeviceStatus()
	 */
	@Override
	public List<OnlineDeviceStatus> getOnlineDeviceStatus() {
		try {
			return this.commService.getOnlineDeviceStatus();
		} catch (Exception e) {
			return new ArrayList<OnlineDeviceStatus>();
		}
	}

	@Override
	public Page<Map<String, Object>> getPageOnlineRateStatistics(
			Date beginDate, Date endDate,String sensorTypeId, long pageNo, long pageSize) {
		return onlineDeviceStatusDao.getPageOnlineRateStatistics(beginDate,
				endDate, sensorTypeId, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getAllOnlineRateStatistics(Date beginDate,
			Date endDate,String sensorTypeId) {
		return onlineDeviceStatusDao.getAllOnlineRateStatistics(beginDate,
				endDate, sensorTypeId);
	}

}
