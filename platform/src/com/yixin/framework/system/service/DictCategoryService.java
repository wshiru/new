/*
 * Project platform
 *
 * Classname DictCategoryService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-13 上午 9:29
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service;

import java.util.Collection;
import java.util.List;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.system.model.DictCategory;

/**
 * 数据字典类型服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface DictCategoryService {
	
	/**
	 * 根据分类ID得到某一类型的的字典明细
	 * @param id
	 * @return
	 */	
	public abstract DictCategory getDictCategory(Integer id);

	/**
	 * 添加字典分类
	 * @param dictCategory
	 */
	public abstract void addDictCategory(DictCategory dictCategory);
	
	/**
	 * 修改字典分类
	 * @param dictCategory
	 */
	public abstract void editDictCategory(DictCategory dictCategory);
	
	/**
	 * 删除某一项字典分类
	 * @param dictCategory
	 */
	public abstract void deleteDictCategory(DictCategory dictCategory);
	
	/**
	 * 删除多项字典分类
	 * @param dictCategorys
	 */
	public abstract void deleteDictCategory(Collection<DictCategory> dictCategorys);

	/**
	 * 得到所有字典
	 * @return
	 */
	public abstract List<DictCategory> getAllDictCategorys();
	/**
	 * 得到可修改的字典分类，State = 1的才可以修改
	 * @return
	 */
	public abstract List<DictCategory> getModifyDictCategorys();
	/**
	 * 分页显示字典分类
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page<DictCategory> getPageDictCategorys(long pageNo, long pageSize);
	
}
