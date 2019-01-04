/*
 * Project platform
 *
 * Classname IceThincknessSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public IceThincknessSampling getLastIceThinckness(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.icethinckness.service.impl;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.icethinckness.dao.IceThincknessParameterDao;
import com.yixin.A1000.icethinckness.model.IceThincknessParameter;
import com.yixin.A1000.icethinckness.service.IceThincknessParameterService;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;

/**
 * 覆冰监测业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class IceThincknessParameterServiceImpl implements IceThincknessParameterService {
	
	/** 覆冰监测参数 数据接口 */
	private IceThincknessParameterDao iceThincknessParameterDao;
	
	public void setIceThincknessParameterDao(IceThincknessParameterDao iceThincknessParameterDao){
		this.iceThincknessParameterDao = iceThincknessParameterDao;
	}
	
	@Override
	public void addIceThincknessParameter(IceThincknessParameter iceThincknessParameter) {
		if(null == iceThincknessParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		
		if(iceThincknessParameterDao.getAllByProperty("sensor", iceThincknessParameter.getSensor()).size() >0 ){
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		iceThincknessParameterDao.save(iceThincknessParameter);
		
	}

	@Override
	public void editIceThincknessParameter(IceThincknessParameter iceThincknessParameter) {
		if(null == iceThincknessParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		iceThincknessParameterDao.save(iceThincknessParameter);
	}

	@Override
	public void deleteIceThincknessParameter(IceThincknessParameter iceThincknessParameter) {
		iceThincknessParameterDao.delete(iceThincknessParameter);		
	}

	@Override
	public void deleteIceThincknessParameter(
			Collection<IceThincknessParameter> iceThincknessParameters) {
		iceThincknessParameterDao.deleteAll(iceThincknessParameters);
	}

	@Override
	public IceThincknessParameter getIceThincknessParameter(String id) {
		return iceThincknessParameterDao.findById(id);
	}

	@Override
	public IceThincknessParameter getIceThincknessParameterBySensor(Sensor sensor) {
		List<IceThincknessParameter> rets = iceThincknessParameterDao.getAllByProperty("sensor", sensor);
		if(rets.size() >0 ){
			return rets.get(0);
		}else{
			return null;
		}		
	}

	@Override
	public List<IceThincknessParameter> getAllIceThincknessParameters() {
		return iceThincknessParameterDao.getAll();
	}
 
}
