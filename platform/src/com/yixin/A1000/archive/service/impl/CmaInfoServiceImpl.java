/*
 * Project platform
 *
 * Class CmaInfoServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午12:03:39
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

import com.yixin.A1000.archive.dao.CmaInfoDao;
import com.yixin.A1000.archive.dao.SensorDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.CmaInfoQueryModel;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测代理业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaInfoServiceImpl implements CmaInfoService {

	/** 监测代理DAO接口对象 */
	private CmaInfoDao cmaInfoDao;

	/** 监测装置DAO接口对象 */
	private SensorDao sensorDao;

	/**
	 * 设置 监测代理DAO接口对象
	 * 
	 * @param cmaInfoDao
	 *            监测代理DAO接口对象
	 */
	public void setCmaInfoDao(CmaInfoDao cmaInfoDao) {
		this.cmaInfoDao = cmaInfoDao;
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
	 * com.yixin.ca2000.archive.service.CmaInfoService#addCmaInfo(com.yixin.
	 * ca2000.archive.model.CmaInfo)
	 */
	@Override
	public void addCmaInfo(CmaInfo cmaInfo) {
		List<CmaInfo> list = this.cmaInfoDao.getAllByProperty("cmaCode", cmaInfo.getCmaCode());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS);
		}
		this.cmaInfoDao.save(cmaInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#editCmaInfo(com.yixin
	 * .ca2000.archive.model.CmaInfo)
	 */
	@Override
	public void editCmaInfo(CmaInfo cmaInfo) {
		List<CmaInfo> list = this.cmaInfoDao.getAllByProperty("cmaCode", cmaInfo.getCmaCode());
		Iterator<CmaInfo> iterator = list.iterator();
		while (iterator.hasNext()) {
			CmaInfo cma = iterator.next();
			if (!cma.getCmaInfoId().equals(cmaInfo.getCmaInfoId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS);
			}
		}
		this.cmaInfoDao.update(cmaInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#deleteCmaInfo(com.yixin
	 * .ca2000.archive.model.CmaInfo)
	 */
	@Override
	public void deleteCmaInfo(CmaInfo cmaInfo) {
		if (this.getAllSensors(cmaInfo).size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_CMAINFO_EXISTSSENSORS);
		}
		this.cmaInfoDao.delete(cmaInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#deleteCmaInfos(java.util
	 * .Collection)
	 */
	@Override
	public void deleteCmaInfos(Collection<CmaInfo> cmaInfos) {
		try {
			this.cmaInfoDao.deleteAll(cmaInfos);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_CMAINFO_EXISTSSENSORS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getCmaInfo(java.lang.
	 * String)
	 */
	@Override
	public CmaInfo getCmaInfo(String id) {
		return this.cmaInfoDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.CmaInfoService#getAllCmaInfos()
	 */
	@Override
	public List<CmaInfo> getAllCmaInfos() {
		return this.cmaInfoDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getPageCmaInfos(long,
	 * long)
	 */
	@Override
	public Page<CmaInfo> getPageCmaInfos(long pageNo, long pageSize) {
		return this.cmaInfoDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getAllCmaInfos(com.yixin
	 * .ca2000.archive.model.CmaInfoQueryModel)
	 */
	@Override
	public List<CmaInfo> getAllCmaInfos(CmaInfoQueryModel cmaInfoQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(cmaInfoQueryModel);
		return this.cmaInfoDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getPageCmaInfos(com.yixin
	 * .ca2000.archive.model.CmaInfoQueryModel, long, long)
	 */
	@Override
	public Page<CmaInfo> getPageCmaInfos(CmaInfoQueryModel cmaInfoQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(cmaInfoQueryModel);
		return this.cmaInfoDao.getPage(queryProperties, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getAllSensors(com.yixin
	 * .ca2000.archive.model.CmaInfo)
	 */
	@Override
	public List<Sensor> getAllSensors(CmaInfo cmaInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmaInfo", cmaInfo);
		return this.sensorDao.getAllByProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.CmaInfoService#getPageSensors(com.yixin
	 * .ca2000.archive.model.CmaInfo, long, long)
	 */
	@Override
	public Page<Sensor> getPageSensors(CmaInfo cmaInfo, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmaInfo", cmaInfo);
		return this.sensorDao.getPageByProperties(map, pageNo, pageSize);
	}

	/**
	 * 根据cmaInfoQueryModel创建查询属性集
	 * 
	 * @param cmaInfoQueryModel
	 *            监测代理查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(CmaInfoQueryModel cmaInfoQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != cmaInfoQueryModel.getCmaCode()) {
			QueryProperty qp = new QueryProperty("cmaCode", cmaInfoQueryModel.getCmaCode(),
					cmaInfoQueryModel.getCmaCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != cmaInfoQueryModel.getCmaDesc()) {
			QueryProperty qp = new QueryProperty("cmaDesc", cmaInfoQueryModel.getCmaDesc(),
					cmaInfoQueryModel.getCmaDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != cmaInfoQueryModel.getCmaModel()) {
			QueryProperty qp = new QueryProperty("cmaModel", cmaInfoQueryModel.getCmaModel(),
					cmaInfoQueryModel.getCmaModelQueryMode());
			queryProperties.add(qp);
		}
		if (null != cmaInfoQueryModel.getCmaName()) {
			QueryProperty qp = new QueryProperty("cmaName", cmaInfoQueryModel.getCmaName(),
					cmaInfoQueryModel.getCmaNameQueryMode());
			queryProperties.add(qp);
		}
		if (null != cmaInfoQueryModel.getInstallDate()) {
			QueryProperty qp = new QueryProperty("installDate", cmaInfoQueryModel.getInstallDate(),
					cmaInfoQueryModel.getInstallDateQueryMode());
			queryProperties.add(qp);
		}
		if (null != cmaInfoQueryModel.getState()) {
			QueryProperty qp = new QueryProperty("state", cmaInfoQueryModel.getState(),
					cmaInfoQueryModel.getStateQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}

	@Override
	public CmaInfo getCmaInfobyCmaCode(String cmaCode) {
		CmaInfo cmaInfo = null;
		List<CmaInfo> cmaInfos = this.cmaInfoDao.getAllByProperty("cmaCode", cmaCode);
		if (cmaInfos.size() > 0 ){
			cmaInfo = cmaInfos.iterator().next();
		}
		return  cmaInfo;
	}
	
}
