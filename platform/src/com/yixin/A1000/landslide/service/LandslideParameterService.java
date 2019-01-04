/*
 * Project platform
 *
 * Classname LandslideSamplingService
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 13:41
 *
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseDAOService;
import com.yixin.A1000.landslide.model.LandslideParameter;

/**
 * 地质滑坡监测服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LandslideParameterService{
	/**
	 * 添加参数
	 * @param landslideParameter
	 */
	public abstract void addLandslideParameter(LandslideParameter landslideParameter);
	
	/**
	 * 修改参数
	 * @param landslideParameter
	 */
	public abstract void editLandslideParameter(LandslideParameter landslideParameter);
	
	/**
	 * 删除参数
	 * @param landslideParameter
	 */
	public abstract void deleteLandslideParameter(LandslideParameter landslideParameter);
	
	/**
	 * 删除多项参数
	 * @param landslideParameters
	 */
	public abstract void deleteLandslideParameter(Collection<LandslideParameter> landslideParameters);

	/**
	 * 根据ID得到参数项
	 * @param id
	 * @return
	 */
	public abstract LandslideParameter  getLandslideParameter(String id);
	
	/**
	 * Sensor
	 * @param sensor
	 * @return
	 */
	public abstract LandslideParameter getLandslideParameterBySensor(Sensor sensor);
	
	/**
	 * 得到所有参数
	 * @return
	 */
	public abstract List<LandslideParameter> getAllLandslideParameters();


	

}
