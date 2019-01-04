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

import com.yixin.A1000.archive.model.Sensor;

/**
 * 装置ID
 * 
 * @version v1.0.0
 * @author 
 */
public class DeviceID {

	/** 监测装置 */
	private Sensor sensor;
	/** 被监测设备ID（17位编码） */
	private String componentID;
	/** 原始ID，各厂家内部识别号 */
	private Integer originalID;

	/**
	 * 获取 监测装置
	 * 
	 * @return 监测装置
	 */
	public Sensor getSensor() {
		return this.sensor;
	}

	/**
	 * 设置 监测装置
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * 获取 被监测设备ID（17位编码）
	 * 
	 * @return 被监测设备ID（17位编码）
	 */
	public String getComponentID() {
		return this.componentID;
	}

	/**
	 * 设置 被监测设备ID（17位编码）
	 * 
	 * @param componentID
	 *            被监测设备ID（17位编码）
	 */
	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}

	/**
	 * 获取 原始ID，各厂家内部识别号
	 * 
	 * @return 原始ID，各厂家内部识别号
	 */
	public Integer getOriginalID() {
		return this.originalID;
	}

	/**
	 * 设置 原始ID，各厂家内部识别号
	 * 
	 * @param originalID
	 *            原始ID，各厂家内部识别号
	 */
	public void setOriginalID(Integer originalID) {
		this.originalID = originalID;
	}
}
