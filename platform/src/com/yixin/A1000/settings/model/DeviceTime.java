/*
 * Project platform
 *
 * Class DeviceTime.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:11:24
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.settings.model;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 监测装置时间
 * 
 * @version v1.0.0
 * @author 
 */
public class DeviceTime {

	private Sensor sensor;
	private Date time;
	public Sensor getSensor() {
		return this.sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public Date getTime() {
		return this.time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
