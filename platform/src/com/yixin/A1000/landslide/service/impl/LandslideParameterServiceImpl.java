/*
 * Project platform
 *
 * Classname TowerTiltSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.landslide.dao.LandslideParameterDao;
import com.yixin.A1000.landslide.dao.LandslideParameterDetailDao;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideParameterDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
 

/**
 * 地质滑坡业务实现类
 * 
 * @version v1.0.0
 * @author 
 */

public class LandslideParameterServiceImpl implements LandslideParameterService {

	private LandslideParameterDao landslideParameterDao;
	private LandslideParameterDetailDao landslideParameterDetailDao;
	
	public void setLandslideParameterDao(LandslideParameterDao landslideParameterDao){
		this.landslideParameterDao = landslideParameterDao;
	}
	
	public void setLandslideParameterDetailDao(LandslideParameterDetailDao landslideParameterDetailDao){
		this.landslideParameterDetailDao = landslideParameterDetailDao;
	}

	@Override
	public void addLandslideParameter(LandslideParameter landslideParameter) {
		if(null == landslideParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		
		LandslideParameter t = getLandslideParameterBySensor(landslideParameter.getSensor());
		if(t != null){
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		//清除子表的数据
		 List<LandslideParameterDetail> landslideParameterDetails = landslideParameter.getLandslideParameterDetails();
		 landslideParameter.setLandslideParameterDetails(null);
		 //保存主表
		landslideParameterDao.save(landslideParameter);
		
		t = getLandslideParameterBySensor(landslideParameter.getSensor());
		Iterator<LandslideParameterDetail> iterator = landslideParameterDetails.iterator();
		while(iterator.hasNext()){
			LandslideParameterDetail d = iterator.next();
			d.setLandslideParameter(t);
		}
		landslideParameterDetailDao.saveAll(landslideParameterDetails);
	}

	@Override
	public void editLandslideParameter(LandslideParameter landslideParameter) {
		if(null == landslideParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		//清除子表的数据
		List<LandslideParameterDetail> landslideParameterDetails = landslideParameter.getLandslideParameterDetails();
		landslideParameter.setLandslideParameterDetails(null);

		 
		LandslideParameter t = getLandslideParameterBySensor(landslideParameter.getSensor());
		if(t != null){
			t.setSampleNum(landslideParameter.getSampleNum());			
			t.setAllDepth(landslideParameter.getAllDepth());
			t.setXyType(landslideParameter.getXyType());
			//修改的
			if(t.getLandslideParameterDetails() != null){
				Iterator<LandslideParameterDetail> iterator = t.getLandslideParameterDetails().iterator();
				while(iterator.hasNext()){
					LandslideParameterDetail d = iterator.next();
					landslideParameterDetailDao.delete(d);
				}
				t.setLandslideParameterDetails(null);
			}
			landslideParameterDao.save(t);
		}else{
			 //添加的
			landslideParameterDao.save(landslideParameter);
			t = getLandslideParameterBySensor(landslideParameter.getSensor());
		}
		
		//设置主表ID
		Iterator<LandslideParameterDetail> iterator = landslideParameterDetails.iterator();
		while(iterator.hasNext()){
			LandslideParameterDetail d = iterator.next();
			d.setLandslideParameter(t);
		}
		landslideParameterDetailDao.saveAll(landslideParameterDetails);
	}

	@Override
	public void deleteLandslideParameter(LandslideParameter landslideParameter) {
		landslideParameterDetailDao.deleteAll(landslideParameter.getLandslideParameterDetails());
		landslideParameterDao.delete(landslideParameter);
	}

	@Override
	public void deleteLandslideParameter(
			Collection<LandslideParameter> landslideParameters) {
		Iterator<LandslideParameter> iterator = landslideParameters.iterator();
		while(iterator.hasNext()){
			LandslideParameter landslideParameter = iterator.next();
			landslideParameterDetailDao.deleteAll(landslideParameter.getLandslideParameterDetails());
		}
		landslideParameterDao.deleteAll(landslideParameters);
	}

	@Override
	public LandslideParameter getLandslideParameter(String id) {		
		return landslideParameterDao.findById(id);
	}

	@Override
	public LandslideParameter getLandslideParameterBySensor(Sensor sensor) {
		List<LandslideParameter> list = landslideParameterDao.getAllByProperty("sensor", sensor);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<LandslideParameter> getAllLandslideParameters() {
		return this.landslideParameterDao.getAll();
	}

	 
	 

 
	 
	 

}
