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

import java.util.Map;
import java.util.TreeMap;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 模型参数配置信息
 * 
 * @version v1.0.0
 * @author 
 */
public class ModelParam {

	private Sensor sensor;
	private Map<String, String> params = new TreeMap<String, String>();

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Map<String, String> getParams() {
		return this.params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
