/*
 * Project platform
 *
 * Classname IceThincknessSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 

 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract IceThincknessSampling getLastIceThinckness(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.icethinckness.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.icethinckness.model.IceThincknessParameter;

/**
 * 覆冰监测参数业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface IceThincknessParameterService {

	/**
	 * 添加覆冰监测参数
	 * 
	 * @param iceThincknessSampling
	 */
	public abstract void addIceThincknessParameter(IceThincknessParameter iceThincknessParameter);


	
	/**
	 * 修改覆冰监测参数
	 * @param iceThincknessParameter
	 */
	public abstract void editIceThincknessParameter(IceThincknessParameter iceThincknessParameter);
	
	/**
	 * 删除覆冰监测参数
	 * @param iceThincknessParameter
	 */
	public abstract void deleteIceThincknessParameter(IceThincknessParameter iceThincknessParameter);
	
	/**
	 * 删除多项覆冰监测参数
	 * @param iceThincknessParameters
	 */
	public abstract void deleteIceThincknessParameter(Collection<IceThincknessParameter> iceThincknessParameters);

	/**
	 * 根据ID得到覆冰监测参数
	 * @param id
	 * @return
	 */
	public abstract IceThincknessParameter  getIceThincknessParameter(String id);
	
	/**
	 * Sensor
	 * @param sensor
	 * @return
	 */
	public abstract IceThincknessParameter getIceThincknessParameterBySensor(Sensor sensor);
	
	/**
	 * 得到所有覆冰监测参数
	 * @return
	 */
	public abstract List<IceThincknessParameter> getAllIceThincknessParameters();



}
