/*
 * Project platform
 *
 * Classname BaseSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 10:26
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.base.web;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.base.model.BaseSampling;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.framework.base.web.JFreeChartAction;

/**
 * 采样数据Action基类
 * @version v1.0.0
 * @author 
 *
 */
public class BaseSamplingAction<T extends BaseSampling> extends JFreeChartAction<T> {
	
	/** 序列版本ID  */
	private static final long serialVersionUID = 6947133320906228873L;
	
	/** 历史采集服务接口 */
	protected BaseSamplingService<T> samplingService;
	
	
	protected SensorService sensorService;
	
	/** 监测装置  */
	protected Sensor sensor;
	
	
	/**
	 * 设置历史采集服务接口
	 * @param samplingService
	 */
	public void setSamplingService(BaseSamplingService<T> samplingService) {
		this.samplingService = samplingService;
	}
	
	/**
	 * 设置监测装置ID
	 * @param id
	 */
	public void setId(String id){
		//sensor = new Sensor();
		//sensor.setSensorId(id);
		sensor = sensorService.getSensor(id);
	}
	/**
	 * 参数SensorId 
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}
	
	/**
	 * 设置监测装置ID
	 * @param sensorId
	 */
	public void setSensorId(String sensorId){
		//sensor = new Sensor();
		//sensor.setSensorId(sensorId);
		sensor = sensorService.getSensor(sensorId);
	}
	/**
	 * 获取监测装置ID
	 * @param sensorId
	 */
	public String getSensorId(){
		return this.sensor.getSensorId();
	}	
	/**
	 * 获取监测装置是否为空
	 * @return
	 */
	public Boolean getIsSensorNullOrEmpty() {
		return this.samplingService.checkNullOrEmpty(this.sensor);
	}

	/**
	 * 获取监测装置
	 * @return
	 */
	public Sensor getSensor() {
		return sensor;
	}
	/**
	 * 设置监测装置
	 * @param sensor
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
}
