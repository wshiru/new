/*
 * Project platform
 *
 * Class OnlineDeviceStatus.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 下午03:10:01
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.model;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 在线率统计
 * 
 * @version v1.0.0
 * @author 
 */
public class OnlineRateStatistics {

	/** 监测装置 */
	//private Sensor sensor;
	
	private String sensorCode;

	/** 统计时间 */
	private Date statDate;

	/** 在线天数 */
	private Integer count ;

 

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param sensorCode the sensorCode to set
	 */
	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}

	/**
	 * @return the sensorCode
	 */
	public String getSensorCode() {
		return sensorCode;
	}
	
	
}
