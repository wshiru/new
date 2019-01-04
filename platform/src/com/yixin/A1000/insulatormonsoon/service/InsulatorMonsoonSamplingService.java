/*
 * Project platform
 *
 * Classname InsulatorMonsoonSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:15
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract InsulatorMonsoonSampling getLastInsulatorMonsoon(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.insulatormonsoon.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;

/**
 * 绝缘子串风偏业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface InsulatorMonsoonSamplingService extends BaseSamplingService<InsulatorMonsoonSampling> {

	/**
	 * 添加绝缘子串风偏
	 * 
	 * @param insulatorMonsoonSampling
	 */
	public abstract void addInsulatorMonsoonSampling(InsulatorMonsoonSampling insulatorMonsoonSampling);

	/**
	 * 添加绝缘子串风偏 多条
	 * 
	 * @param insulatorMonsoonSampling
	 */
	public abstract void addInsulatorMonsoonSampling(List<InsulatorMonsoonSampling> insulatorMonsoonSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。
	 */
	public abstract InsulatorMonsoonSampling getLastInsulatorMonsoon(Sensor sensor);
}
