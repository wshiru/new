/*
 * Project platform
 *
 * Classname BaseSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-11 18:58
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.base.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.model.BaseSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.system.model.DictCategory;
import com.yixin.framework.system.model.Dictionary;

/**
 * 基本数据操作服务接口基类
 * 
 * @version v1.0.0
 * @author 
 */
public interface BaseDAOService<T> {

	/**
	 * 添加
	 * @param dictionary
	 */
	public abstract void add(T t);
	
	/**
	 * 修改
	 * @param dictionary
	 */
	public abstract void edit(T t);
	
	/**
	 * 删除
	 * @param dictionary
	 */
	public abstract void delete(T t);
	
	/**
	 * 删除多项
	 * @param dictionarys
	 */
	public abstract void delete(Collection<T> ts);

	/**
	 * 根据ID得到项
	 * @param id
	 * @return
	 */
	public abstract Dictionary  get(String id);
	
	/**
	 * 得到所有
	 * @return
	 */
	public abstract List<T> getAll();

	/**
	 * 分页显示
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page<Dictionary> getPage(long pageNo, long pageSize);
 
	
	
}
