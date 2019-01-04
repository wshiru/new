/*
 * Project platform
 *
 * Classname WireSagSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 13:41
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract WireSagSampling getLastWireSag(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiresag.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.wiresag.model.WireSagSampling;

/**
 * 导线弧垂服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WireSagSamplingService extends BaseSamplingService<WireSagSampling>{

	/**
	 * 添加导线弧垂
	 * 
	 * @param wireSagSampling
	 */
	public abstract void addWireSagSampling(WireSagSampling wireSagSampling);


	/**
	 * 添加导线弧垂
	 * 
	 * @param wireSagSampling
	 *            多记录
	 */
	public abstract void addWireSagSampling(List<WireSagSampling> wireSagSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。
	 */
	public abstract WireSagSampling getLastWireSag(Sensor sensor);

}
