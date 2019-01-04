/*
 * Project platform
 *
 * Classname ContaminationSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public ContaminationSampling getLastContamination(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.contamination.service.impl;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.contamination.dao.ContaminationParameterDao;
import com.yixin.A1000.contamination.model.ContaminationParameter;
import com.yixin.A1000.contamination.service.ContaminationParameterService;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;

/**
 * 污秽度监测业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class ContaminationParameterServiceImpl implements ContaminationParameterService {
	
	/** 污秽度监测参数 数据接口 */
	private ContaminationParameterDao contaminationParameterDao;
	
	public void setContaminationParameterDao(ContaminationParameterDao contaminationParameterDao){
		this.contaminationParameterDao = contaminationParameterDao;
	}
	
	@Override
	public void addContaminationParameter(ContaminationParameter contaminationParameter) {
		if(null == contaminationParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		
		if(contaminationParameterDao.getAllByProperty("sensor", contaminationParameter.getSensor()).size() >0 ){
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		contaminationParameterDao.save(contaminationParameter);
		
	}

	@Override
	public void editContaminationParameter(ContaminationParameter contaminationParameter) {
		if(null == contaminationParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}
		contaminationParameterDao.save(contaminationParameter);
	}

	@Override
	public void deleteContaminationParameter(ContaminationParameter contaminationParameter) {
		contaminationParameterDao.delete(contaminationParameter);		
	}

	@Override
	public void deleteContaminationParameter(
			Collection<ContaminationParameter> contaminationParameters) {
		contaminationParameterDao.deleteAll(contaminationParameters);
	}

	@Override
	public ContaminationParameter getContaminationParameter(String id) {
		return contaminationParameterDao.findById(id);
	}

	@Override
	public ContaminationParameter getContaminationParameterBySensor(Sensor sensor) {
		List<ContaminationParameter> rets = contaminationParameterDao.getAllByProperty("sensor", sensor);
		if(rets.size() >0 ){
			return rets.get(0);
		}else{
			return null;
		}		
	}

	@Override
	public List<ContaminationParameter> getAllContaminationParameters() {
		return contaminationParameterDao.getAll();
	}
 
}
