/*
 * Project platform
 *
 * Classname BaseSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-11 19:05
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.base.service.impl;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.base.service.BaseDAOService;
import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.system.model.Dictionary;

/**
 * 采样历史数据业务接口实现基类
 * 
 * @version v1.0.0
 * @author 
 */
public class BaseDAOServiceImpl<T> implements BaseDAOService<T> {

	/** DAO接口  */
	protected BaseDao<T, String> baseDao;

	
	/**
	 * 设置DAO接口
	 * @param baseDao
	 */
	public void setBaseDao(BaseDao<T, String> baseDao) {
		this.baseDao = baseDao;
	}


	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void edit(T t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Collection<T> ts) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Dictionary get(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<Dictionary> getPage(long pageNo, long pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
