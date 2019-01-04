/*
 * Project platform
 *
 * Classname ContaminationSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 

 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract ContaminationSampling getLastContamination(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.contamination.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.contamination.model.ContaminationSampling;

/**
 * 污秽度监测业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface ContaminationSamplingService extends BaseSamplingService<ContaminationSampling>{

	/**
	 * 添加污秽度监测
	 * 
	 * @param contaminationSampling
	 */
	public abstract void addContaminationSampling(ContaminationSampling contaminationSampling);


	/**
	 * 添加污秽度监测 多条
	 * 
	 * @param contaminationSamplings
	 */
	public abstract void addContaminationSampling(List<ContaminationSampling> contaminationSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。

	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。

	 */
	public abstract ContaminationSampling getLastContamination(Sensor sensor);

}
