/*
 * Project platform
 *
 * Class SensorServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-22 下午02:11:15
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.archive.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.dao.SensorDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorQueryModel;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测装置业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class SensorServiceImpl implements SensorService {

	/** 监测装置DAO接口对象 */
	private SensorDao sensorDao;

	/**
	 * 设置 监测装置DAO接口对象
	 * 
	 * @param sensorDao
	 *            监测装置DAO接口对象
	 */
	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#addSensor(com.yixin.ca2000
	 * .archive.model.Sensor)
	 */
	@Override
	public void addSensor(Sensor sensor) {
		List<Sensor> list = this.sensorDao.getAllByProperty("sensorCode", sensor.getSensorCode());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS);
		}
		this.sensorDao.save(sensor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#editSensor(com.yixin.ca2000
	 * .archive.model.Sensor)
	 */
	@Override
	public void editSensor(Sensor sensor) {
		List<Sensor> list = this.sensorDao.getAllByProperty("sensorCode", sensor.getSensorCode());
		Iterator<Sensor> iterator = list.iterator();
		while (iterator.hasNext()) {
			Sensor sen = iterator.next();
			if (!sen.getSensorId().equals(sensor.getSensorId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS);
			}
		}
		this.sensorDao.update(sensor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#deleteSensor(com.yixin
	 * .ca2000.archive.model.Sensor)
	 */
	@Override
	public void deleteSensor(Sensor sensor) {
		try {
			this.sensorDao.delete(sensor);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_SENSOR_EXISTSDATA);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#deleteSensors(java.util
	 * .Collection)
	 */
	@Override
	public void deleteSensors(Collection<Sensor> sensors) {
		try {
			this.sensorDao.deleteAll(sensors);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_SENSOR_EXISTSDATA);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#getSensor(java.lang.String
	 * )
	 */
	@Override
	public Sensor getSensor(String id) {
		return this.sensorDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.SensorService#getAllSensors()
	 */
	@Override
	public List<Sensor> getAllSensors() {
		return this.sensorDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.SensorService#getPageSensors(long,
	 * long)
	 */
	@Override
	public Page<Sensor> getPageSensors(long pageNo, long pageSize) {
		return this.sensorDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#getAllSensors(com.yixin
	 * .ca2000.archive.model.SensorQueryModel)
	 */
	@Override
	public List<Sensor> getAllSensors(SensorQueryModel sensorQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(sensorQueryModel);
		return this.sensorDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorService#getPageSensors(com.yixin
	 * .ca2000.archive.model.SensorQueryModel, long, long)
	 */
	@Override
	public Page<Sensor> getPageSensors(SensorQueryModel sensorQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(sensorQueryModel);
		return this.sensorDao.getPage(queryProperties, pageNo, pageSize);
	}

	/**
	 * 根据sensorQueryModel创建查询属性集
	 * 
	 * @param sensorQueryModel
	 *            监测装置查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(SensorQueryModel sensorQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != sensorQueryModel.getBySensorCode()) {
			QueryProperty qp = new QueryProperty("bySensorCode", sensorQueryModel.getBySensorCode(),
					sensorQueryModel.getBySensorCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getCmaInfo()) {
			QueryProperty qp = new QueryProperty("cmaInfo", sensorQueryModel.getCmaInfo(),
					sensorQueryModel.getCmaInfoQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getIdentificationNumber()) {
			QueryProperty qp = new QueryProperty("identificationNumber", sensorQueryModel.getIdentificationNumber(),
					sensorQueryModel.getIdentificationNumberQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getInstallSite()) {
			QueryProperty qp = new QueryProperty("installSite", sensorQueryModel.getInstallSite(),
					sensorQueryModel.getInstallSiteQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getManuFacturer()) {
			QueryProperty qp = new QueryProperty("manuFacturer", sensorQueryModel.getManuFacturer(),
					sensorQueryModel.getManuFacturerQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getProductionDate()) {
			QueryProperty qp = new QueryProperty("productionDate", sensorQueryModel.getProductionDate(),
					sensorQueryModel.getProductionDateQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getSensorCode()) {
			QueryProperty qp = new QueryProperty("sensorCode", sensorQueryModel.getSensorCode(),
					sensorQueryModel.getSensorCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getSensorDesc()) {
			QueryProperty qp = new QueryProperty("sensorDesc", sensorQueryModel.getSensorDesc(),
					sensorQueryModel.getSensorDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getSensorType()) {
			QueryProperty qp = new QueryProperty("sensorType", sensorQueryModel.getSensorType(),
					sensorQueryModel.getSensorTypeQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getState()) {
			QueryProperty qp = new QueryProperty("state", sensorQueryModel.getState(),
					sensorQueryModel.getStateQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorQueryModel.getTower()) {
			QueryProperty qp = new QueryProperty("tower", sensorQueryModel.getTower(),
					sensorQueryModel.getTowerQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}

	
	@Override
	public Sensor getSensorbySensorCode(String sensorCode) {
		Sensor sensor = null; 
		List<Sensor> sensors  = this.sensorDao.getAllByProperty("sensorCode", sensorCode);
		Iterator<Sensor> it = sensors.iterator();
		while (it.hasNext()){
			sensor = it.next();
			break;
		}
		return sensor;
	}
	
	
}
