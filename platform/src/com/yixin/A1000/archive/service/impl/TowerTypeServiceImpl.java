/*
 * Project platform
 *
 * Class TowerTypeServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午02:31:49
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

import com.yixin.A1000.archive.dao.TowerDao;
import com.yixin.A1000.archive.dao.TowerTypeDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.model.TowerType;
import com.yixin.A1000.archive.model.TowerTypeQueryModel;
import com.yixin.A1000.archive.service.TowerTypeService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔类型业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTypeServiceImpl implements TowerTypeService {

	/** 杆塔类型DAO接口对象 */
	private TowerTypeDao towerTypeDao;

	/** 杆塔DAO接口对象 */
	private TowerDao towerDao;

	/**
	 * 设置 杆塔类型DAO接口对象
	 * 
	 * @param towerTypeDao
	 *            杆塔类型DAO接口对象
	 */
	public void setTowerTypeDao(TowerTypeDao towerTypeDao) {
		this.towerTypeDao = towerTypeDao;
	}

	/**
	 * 设置 杆塔DAO接口对象
	 * 
	 * @param towerDao
	 *            杆塔DAO接口对象
	 */
	public void setTowerDao(TowerDao towerDao) {
		this.towerDao = towerDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#addTowerType(com.yixin
	 * .ca2000.archive.model.TowerType)
	 */
	@Override
	public void addTowerType(TowerType towerType) {
		List<TowerType> list = this.towerTypeDao.getAllByProperty("towerTypeCode", towerType.getTowerTypeCode());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS);
		}
		this.towerTypeDao.save(towerType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#editTowerType(com.yixin
	 * .ca2000.archive.model.TowerType)
	 */
	@Override
	public void editTowerType(TowerType towerType) {
		List<TowerType> list = this.towerTypeDao.getAllByProperty("towerTypeCode", towerType.getTowerTypeCode());
		Iterator<TowerType> iterator = list.iterator();
		while (iterator.hasNext()) {
			TowerType l = iterator.next();
			if (!l.getTowerTypeId().equals(towerType.getTowerTypeId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS);
			}
		}
		this.towerTypeDao.update(towerType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#deleteTowerType(com
	 * .yixin.ca2000.archive.model.TowerType)
	 */
	@Override
	public void deleteTowerType(TowerType towerType) {
		if (this.getAllTowers(towerType).size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWERTYPE_EXISTSTOWERS);
		}
		this.towerTypeDao.delete(towerType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#deleteTowerTypes(java
	 * .util.Collection)
	 */
	@Override
	public void deleteTowerTypes(Collection<TowerType> towerTypes) {
		try {
			this.towerTypeDao.deleteAll(towerTypes);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWERTYPE_EXISTSTOWERS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getTowerType(java.lang
	 * .String)
	 */
	@Override
	public TowerType getTowerType(String id) {
		return this.towerTypeDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.TowerTypeService#getAllTowerTypes()
	 */
	@Override
	public List<TowerType> getAllTowerTypes() {
		return this.towerTypeDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getPageTowerTypes(long,
	 * long)
	 */
	@Override
	public Page<TowerType> getPageTowerTypes(long pageNo, long pageSize) {
		return this.towerTypeDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getAllTowerTypes(com
	 * .yixin.ca2000.archive.model.TowerTypeQueryModel)
	 */
	@Override
	public List<TowerType> getAllTowerTypes(TowerTypeQueryModel towerTypeQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(towerTypeQueryModel);
		return this.towerTypeDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getPageTowerTypes(com
	 * .yixin.ca2000.archive.model.TowerTypeQueryModel, long, long)
	 */
	@Override
	public Page<TowerType> getPageTowerTypes(TowerTypeQueryModel towerTypeQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(towerTypeQueryModel);
		return this.towerTypeDao.getPage(queryProperties, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getAllTowers(com.yixin
	 * .ca2000.archive.model.TowerType)
	 */
	@Override
	public List<Tower> getAllTowers(TowerType towerType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("towerType", towerType);
		return this.towerDao.getAllByOrProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerTypeService#getPageTowers(com.yixin
	 * .ca2000.archive.model.TowerType, long, long)
	 */
	@Override
	public Page<Tower> getPageTowers(TowerType towerType, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("towerType", towerType);
		return this.towerDao.getPageByProperties(map, pageNo, pageSize);
	}

	/**
	 * 根据towerTypeQueryModel创建查询属性集
	 * 
	 * @param towerTypeQueryModel
	 *            杆塔类型查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(TowerTypeQueryModel towerTypeQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != towerTypeQueryModel.getTowerTypeCode()) {
			QueryProperty qp = new QueryProperty("towerTypeCode", towerTypeQueryModel.getTowerTypeCode(),
					towerTypeQueryModel.getTowerTypeCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerTypeQueryModel.getTowerTypeDesc()) {
			QueryProperty qp = new QueryProperty("towerTypeDesc", towerTypeQueryModel.getTowerTypeDesc(),
					towerTypeQueryModel.getTowerTypeDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerTypeQueryModel.getTowerTypeName()) {
			QueryProperty qp = new QueryProperty("towerTypeName", towerTypeQueryModel.getTowerTypeName(),
					towerTypeQueryModel.getTowerTypeNameQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}
}
