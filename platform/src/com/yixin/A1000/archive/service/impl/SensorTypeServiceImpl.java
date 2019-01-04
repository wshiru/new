/*
 * Project platform
 *
 * Class SensorTypeServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午02:20:16
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.SensorDao;
import com.yixin.A1000.archive.dao.SensorTypeDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.SensorTypeQueryModel;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测类型业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author
 */
public class SensorTypeServiceImpl implements SensorTypeService {

	/** 监测类型DAO接口对象 */
	private SensorTypeDao sensorTypeDao;

	/** 监测装置DAO接口对象 */
	private SensorDao sensorDao;

	/**
	 * 设置 监测类型DAO接口对象
	 * 
	 * @param sensorTypeDao
	 *            监测类型DAO接口对象
	 */
	public void setSensorTypeDao(SensorTypeDao sensorTypeDao) {
		this.sensorTypeDao = sensorTypeDao;
	}

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
	 * com.yixin.ca2000.archive.service.SensorTypeService#addSensorType(com.
	 * yixin.ca2000.archive.model.SensorType)
	 */
	@Override
	public void addSensorType(SensorType sensorType) {
		List<SensorType> list = this.sensorTypeDao.getAllByProperty(
				"sensorTypeCode", sensorType.getSensorTypeCode());
		if (list.size() > 0) {
			throw new BusinessException(
					ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS);
		}
		this.sensorTypeDao.save(sensorType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#editSensorType(com
	 * .yixin.ca2000.archive.model.SensorType)
	 */
	@Override
	public void editSensorType(SensorType sensorType) {
		List<SensorType> list = this.sensorTypeDao.getAllByProperty(
				"sensorTypeCode", sensorType.getSensorTypeCode());
		Iterator<SensorType> iterator = list.iterator();
		while (iterator.hasNext()) {
			SensorType l = iterator.next();
			if (!l.getSensorTypeId().equals(sensorType.getSensorTypeId())) {
				throw new BusinessException(
						ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS);
			}
		}
		this.sensorTypeDao.update(sensorType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#deleteSensorType(com
	 * .yixin.ca2000.archive.model.SensorType)
	 */
	@Override
	public void deleteSensorType(SensorType sensorType) {
		if (this.getAllSensors(sensorType).size() > 0) {
			throw new BusinessException(
					ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS);
		}

		this.sensorTypeDao.delete(sensorType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#deleteSensorTypes(
	 * java.util.Collection)
	 */
	@Override
	public void deleteSensorTypes(Collection<SensorType> sensorTypes) {
		try {
			this.sensorTypeDao.deleteAll(sensorTypes);
		} catch (Exception ex) {
			throw new BusinessException(
					ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getSensorType(java
	 * .lang.String)
	 */
	@Override
	public SensorType getSensorType(String id) {
		return this.sensorTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getAllSensorTypes()
	 */
	@Override
	public List<SensorType> getAllSensorTypes() {

		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		QueryProperty qp =  new QueryProperty("sensorTypeCode",DataOrder.ASC); 
		queryProperties.add(qp);		
		return this.sensorTypeDao.getAll(queryProperties);
		//return this.sensorTypeDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getPageSensorTypes
	 * (long, long)
	 */
	@Override
	public Page<SensorType> getPageSensorTypes(long pageNo, long pageSize) {
		return this.sensorTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getAllSensorTypes(
	 * com.yixin.ca2000.archive.model.SensorTypeQueryModel)
	 */
	@Override
	public List<SensorType> getAllSensorTypes(
			SensorTypeQueryModel sensorTypeQueryModel) {
		Collection<QueryProperty> queryProperties = this
				.createQuery(sensorTypeQueryModel);
		return this.sensorTypeDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getPageSensorTypes
	 * (com.yixin.ca2000.archive.model.SensorTypeQueryModel, long, long)
	 */
	@Override
	public Page<SensorType> getPageSensorTypes(
			SensorTypeQueryModel sensorTypeQueryModel, long pageNo,
			long pageSize) {
		Collection<QueryProperty> queryProperties = this
				.createQuery(sensorTypeQueryModel);
		return this.sensorTypeDao.getPage(queryProperties, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getAllSensors(com.
	 * yixin.ca2000.archive.model.SensorType)
	 */
	@Override
	public List<Sensor> getAllSensors(SensorType sensorType) {
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("sensorType", sensorType);
		//return this.sensorDao.getAllByOrProperties(map);
		
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		QueryProperty qp1 =  new QueryProperty("sensorType",sensorType,ObjectQueryMode.EQ); 
		queryProperties.add(qp1);
		QueryProperty qp2 =  new QueryProperty("state",1,NumberQueryMode.EQ); 
		queryProperties.add(qp2);						
		QueryProperty qp3 =  new QueryProperty("sensorCode",DataOrder.ASC); 
		queryProperties.add(qp3);
		return this.sensorDao.getAll(queryProperties);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.SensorTypeService#getPageSensors(com
	 * .yixin.ca2000.archive.model.SensorType, long, long)
	 */
	@Override
	public Page<Sensor> getPageSensors(SensorType sensorType, long pageNo,
			long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensorType", sensorType);
		return this.sensorDao.getPageByProperties(map, pageNo, pageSize);
	}

	/**
	 * 根据sensorTypeQueryModel创建查询属性集
	 * 
	 * @param sensorTypeQueryModel
	 *            监测装置查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(
			SensorTypeQueryModel sensorTypeQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != sensorTypeQueryModel.getSensorDesc()) {
			QueryProperty qp = new QueryProperty("sensorDesc",
					sensorTypeQueryModel.getSensorDesc(),
					sensorTypeQueryModel.getSensorDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorTypeQueryModel.getSensorTypeCode()) {
			QueryProperty qp = new QueryProperty("sensorTypeCode",
					sensorTypeQueryModel.getSensorTypeCode(),
					sensorTypeQueryModel.getSensorTypeCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != sensorTypeQueryModel.getSensorTypeName()) {
			QueryProperty qp = new QueryProperty("sensorTypeName",
					sensorTypeQueryModel.getSensorTypeName(),
					sensorTypeQueryModel.getSensorTypeNameQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}
}
