/*
 * Project platform
 *
 * Classname TowerTiltSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public TowerTiltSampling getLastTowerTilt(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.towertilt.service.impl;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.towertilt.dao.TowerTiltParameterDao;
import com.yixin.A1000.towertilt.model.TowerTiltParameter;
import com.yixin.A1000.towertilt.service.TowerTiltParameterService;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;

/**
 * 杆塔倾斜业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTiltParameterServiceImpl implements TowerTiltParameterService {
	
	/** 杆塔倾斜参数 数据接口 */
	private TowerTiltParameterDao towerTiltParameterDao;
	
	public void setTowerTiltParameterDao(TowerTiltParameterDao towerTiltParameterDao){
		this.towerTiltParameterDao = towerTiltParameterDao;
	}
	
	@Override
	public void addTowerTiltParameter(TowerTiltParameter towerTiltParameter) {
		if(null == towerTiltParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		
		if(towerTiltParameterDao.getAllByProperty("sensor", towerTiltParameter.getSensor()).size() >0 ){
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		towerTiltParameterDao.save(towerTiltParameter);
		
	}

	@Override
	public void editTowerTiltParameter(TowerTiltParameter towerTiltParameter) {
		if(null == towerTiltParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		towerTiltParameterDao.save(towerTiltParameter);
	}

	@Override
	public void deleteTowerTiltParameter(TowerTiltParameter towerTiltParameter) {
		towerTiltParameterDao.delete(towerTiltParameter);		
	}

	@Override
	public void deleteTowerTiltParameter(
			Collection<TowerTiltParameter> towerTiltParameters) {
		towerTiltParameterDao.deleteAll(towerTiltParameters);
	}

	@Override
	public TowerTiltParameter getTowerTiltParameter(String id) {
		return towerTiltParameterDao.findById(id);
	}

	@Override
	public TowerTiltParameter getTowerTiltParameterBySensor(Sensor sensor) {
		List<TowerTiltParameter> rets = towerTiltParameterDao.getAllByProperty("sensor", sensor);
		if(rets.size() >0 ){
			return rets.get(0);
		}else{
			return null;
		}		
	}

	@Override
	public List<TowerTiltParameter> getAllTowerTiltParameters() {
		return towerTiltParameterDao.getAll();
	}
 
}
