/*
 * Project platform
 *
 * Classname WhiteMonsoonSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:01
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract WhiteMonsoonSampling getLastWhiteMonsoon(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.whitemonsoon.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.whitemonsoon.model.WhiteMonsoonSampling;

/**
 * 相间风偏业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WhiteMonsoonSamplingService extends BaseSamplingService<WhiteMonsoonSampling>{

	/**
	 * 添加相间风偏
	 * 
	 * @param whiteMonsoonSampling
	 */
	public abstract void addWhiteMonsoonSampling(WhiteMonsoonSampling whiteMonsoonSampling);

	/**
	 * 添加相间风偏 多条
	 * 
	 * @param whiteMonsoonSamplings
	 */
	public abstract void addWhiteMonsoonSampling(List<WhiteMonsoonSampling> whiteMonsoonSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。
	 */
	public abstract WhiteMonsoonSampling getLastWhiteMonsoon(Sensor sensor);

}
