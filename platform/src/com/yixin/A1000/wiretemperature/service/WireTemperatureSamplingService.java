/*
 * Project platform
 *
 * Classname WireTemperatureSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 晚上 19:20
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract WireTemperatureSampling getLastWireTemperature(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiretemperature.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;

/**
 * 导线温度服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WireTemperatureSamplingService extends BaseSamplingService<WireTemperatureSampling>{

	/**
	 * 添加导线温度
	 * 
	 * @param wireTemperatureSampling
	 */
	public abstract void addWireTemperatureSampling(WireTemperatureSampling wireTemperatureSampling);

	/**
	 * 添加导线温度 多记录
	 * 
	 * @param wireTemperatureSamplings
	 */
	public abstract void addWireTemperatureSampling(List<WireTemperatureSampling> wireTemperatureSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。
	 */
	public abstract WireTemperatureSampling getLastWireTemperature(Sensor sensor);
}
