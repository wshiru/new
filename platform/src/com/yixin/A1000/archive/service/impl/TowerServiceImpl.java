/*
 * Project platform
 *
 * Class TowerServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午02:26:55
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
import com.yixin.A1000.archive.dao.TowerDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.model.TowerQueryModel;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerServiceImpl implements TowerService {

	/** 杆塔DAO接口对象 */
	private TowerDao towerDao;

	/** 监测装置DAO接口对象 */
	private SensorDao sensorDao;

	/**
	 * 设置 杆塔DAO接口对象
	 * 
	 * @param towerDao
	 *            杆塔DAO接口对象
	 */
	public void setTowerDao(TowerDao towerDao) {
		this.towerDao = towerDao;
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
	 * com.yixin.ca2000.archive.service.TowerService#addTower(com.yixin.ca2000
	 * .archive.model.Tower)
	 */
	@Override
	public void addTower(Tower tower) {
		List<Tower> list = this.towerDao.getAllByProperty("towerCode", tower.getTowerCode());
		//if (list.size() > 0) {
		//	throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS);
		//}
		this.towerDao.save(tower);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#editTower(com.yixin.ca2000
	 * .archive.model.Tower)
	 */
	@Override
	public void editTower(Tower tower) {
		/*
		List<Tower> list = this.towerDao.getAllByProperty("towerCode", tower.getTowerCode());
		Iterator<Tower> iterator = list.iterator();
		while (iterator.hasNext()) {
			Tower l = iterator.next();
			if (!l.getTowerId().equals(tower.getTowerId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS);
			}
		}
		*/
		this.towerDao.update(tower);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#deleteTower(com.yixin.ca2000
	 * .archive.model.Tower)
	 */
	@Override
	public void deleteTower(Tower tower) {
		if (this.getAllSensors(tower).size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS);
		}
		this.towerDao.delete(tower);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#deleteTowers(java.util.
	 * Collection)
	 */
	@Override
	public void deleteTowers(Collection<Tower> towers) {
		try {
			this.towerDao.deleteAll(towers);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#getTower(java.lang.String)
	 */
	@Override
	public Tower getTower(String id) {
		return this.towerDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.TowerService#getAllTowers()
	 */
	@Override
	public List<Tower> getAllTowers() {
		return this.towerDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.TowerService#getPageTowers(long,
	 * long)
	 */
	@Override
	public Page<Tower> getPageTowers(long pageNo, long pageSize) {
		return this.towerDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#getAllTowers(com.yixin.
	 * ca2000.archive.model.TowerQueryModel)
	 */
	@Override
	public List<Tower> getAllTowers(TowerQueryModel towerQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(towerQueryModel);
		return this.towerDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#getPageTowers(com.yixin
	 * .ca2000.archive.model.TowerQueryModel, long, long)
	 */
	@Override
	public Page<Tower> getPageTowers(TowerQueryModel towerQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(towerQueryModel);
		return this.towerDao.getPage(queryProperties, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.TowerService#getAllSensors(com.yixin
	 * .ca2000.archive.model.Tower)
	 */
	@Override
	public List<Sensor> getAllSensors(Tower tower) {
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("tower", tower);
		//map.put("state", 1); //过滤已启用装置
		//return this.sensorDao.getAllByProperties(map);

		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		QueryProperty qp1 =  new QueryProperty("tower",tower,ObjectQueryMode.EQ); 
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
	 * com.yixin.ca2000.archive.service.TowerService#getPageSensors(com.yixin
	 * .ca2000.archive.model.Tower, long, long)
	 */
	@Override
	public Page<Sensor> getPageSensors(Tower tower, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tower", tower);
		return this.sensorDao.getPageByProperties(map, pageNo, pageSize);
	}

	/**
	 * 根据towerQueryModel创建查询属性集
	 * 
	 * @param towerQueryModel
	 *            杆塔查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(TowerQueryModel towerQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != towerQueryModel.getLatitude()) {
			QueryProperty qp = new QueryProperty("latitude", towerQueryModel.getLatitude(),
					towerQueryModel.getLatitudeQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getLongitude()) {
			QueryProperty qp = new QueryProperty("longitude", towerQueryModel.getLongitude(),
					towerQueryModel.getLongitudeQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getTowerHeight()) {
			QueryProperty qp = new QueryProperty("towerHeight", towerQueryModel.getTowerHeight(),
					towerQueryModel.getTowerHeightQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getAddress()) {
			QueryProperty qp = new QueryProperty("address", towerQueryModel.getAddress(),
					towerQueryModel.getAddressQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getLine()) {
			QueryProperty qp = new QueryProperty("line", towerQueryModel.getLine(), towerQueryModel.getLineQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getTowerCode()) {
			QueryProperty qp = new QueryProperty("towerCode", towerQueryModel.getTowerCode(),
					towerQueryModel.getTowerCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getTowerDesc()) {
			QueryProperty qp = new QueryProperty("towerDesc", towerQueryModel.getTowerDesc(),
					towerQueryModel.getTowerDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != towerQueryModel.getTowerType()) {
			QueryProperty qp = new QueryProperty("towerType", towerQueryModel.getTowerType(),
					towerQueryModel.getTowerTypeQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}


}
