/*
 * Project platform
 *
 * Class DeviceAcquisition.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午04:31:41
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
 * 状态监测装置采样参数
 * 
 * @version v1.0.0
 * @author 
 */
public class SamplingParam {

	/** 监测装置 */
	private Sensor sensor;
	/** 采集时间周期重新设定，表示分钟数 */
	private Integer mainTime;
	/** 高速采样点数 */
	private Integer sampleCount;
	/** 高速采样频率 */
	private Integer sampleFrequency;
	/** 备用 */
	private String reserve;

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Integer getMainTime() {
		return this.mainTime;
	}

	public void setMainTime(Integer mainTime) {
		this.mainTime = mainTime;
	}

	public Integer getSampleCount() {
		return this.sampleCount;
	}

	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	public Integer getSampleFrequency() {
		return this.sampleFrequency;
	}

	public void setSampleFrequency(Integer sampleFrequency) {
		this.sampleFrequency = sampleFrequency;
	}

	public String getReserve() {
		return this.reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
}
