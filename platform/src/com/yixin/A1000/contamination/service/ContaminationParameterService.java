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

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.contamination.model.ContaminationParameter;

/**
 * 污秽度监测参数业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface ContaminationParameterService {

	/**
	 * 添加污秽度监测参数
	 * 
	 * @param contaminationSampling
	 */
	public abstract void addContaminationParameter(ContaminationParameter contaminationParameter);


	
	/**
	 * 修改污秽度监测参数
	 * @param contaminationParameter
	 */
	public abstract void editContaminationParameter(ContaminationParameter contaminationParameter);
	
	/**
	 * 删除污秽度监测参数
	 * @param contaminationParameter
	 */
	public abstract void deleteContaminationParameter(ContaminationParameter contaminationParameter);
	
	/**
	 * 删除多项污秽度监测参数
	 * @param contaminationParameters
	 */
	public abstract void deleteContaminationParameter(Collection<ContaminationParameter> contaminationParameters);

	/**
	 * 根据ID得到污秽度监测参数
	 * @param id
	 * @return
	 */
	public abstract ContaminationParameter  getContaminationParameter(String id);
	
	/**
	 * Sensor
	 * @param sensor
	 * @return
	 */
	public abstract ContaminationParameter getContaminationParameterBySensor(Sensor sensor);
	
	/**
	 * 得到所有污秽度监测参数
	 * @return
	 */
	public abstract List<ContaminationParameter> getAllContaminationParameters();



}
