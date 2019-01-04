/*
 * Project platform
 *
 * Classname WarningDictServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 06:32
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.service.impl;

import java.util.List;

import com.yixin.A1000.warning.dao.WarningDictDao;
import com.yixin.A1000.warning.model.WarningDict;
import com.yixin.A1000.warning.service.WarningDictService;

/**
 * 告警服务接口实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningDictServiceImpl implements WarningDictService {

	/** 告警类型Dao  */
	private WarningDictDao warningDictDao;
	
	/**
	 * 设置告警类型Dao	
	 * @param warningDictDao
	 */
	public void setWarningDictDao(WarningDictDao warningDictDao) {
		this.warningDictDao = warningDictDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#addWarningDict(com.yixin.ca2000.warning.model.WarningDict)
	 */
	@Override
	public void addWarningDict(WarningDict warningDict) {
		if(null != warningDict)
			this.warningDictDao.save(warningDict);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#updateWarningDict(com.yixin.ca2000.warning.model.WarningDict)
	 */
	@Override
	public void updateWarningDict(WarningDict warningDict) {
		if(null != warningDict)
			this.warningDictDao.update(warningDict);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#deleteWarningDict(com.yixin.ca2000.warning.model.WarningDict)
	 */
	@Override
	public void deleteWarningDict(WarningDict warningDict) {
		if(null != warningDict)
			this.warningDictDao.delete(warningDict);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#getWarningDict(java.lang.String)
	 */
	@Override
	public WarningDict getWarningDict(String id) {
		return this.warningDictDao.findById(id);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#getAllWarningDicts()
	 */
	@Override
	public List<WarningDict> getAllWarningDicts() {
		return this.warningDictDao.getAll();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningDictService#getWarningDictByName(java.lang.String)
	 */
	@Override
	public WarningDict getWarningDictByName(String name) {
		List<WarningDict> warningDicts = this.warningDictDao.getAllByProperty("name", name);
		return null != warningDicts && warningDicts.size() > 0 ? warningDicts.get(0) : null;
	}

}
