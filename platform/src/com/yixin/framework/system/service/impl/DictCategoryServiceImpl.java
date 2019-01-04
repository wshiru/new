/*
 * Project platform
 *
 * Classname DictCategoryServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-13 上午 9:32
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
import com.yixin.framework.system.dao.DictCategoryDao;
import com.yixin.framework.system.dao.DictionaryDao;
import com.yixin.framework.system.model.DictCategory;
import com.yixin.framework.system.model.Dictionary;
import com.yixin.framework.system.service.DictCategoryService;

/**
 * 数据字典类型服务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class DictCategoryServiceImpl implements DictCategoryService {

	/** 数据字典类型DAO */
	private DictCategoryDao dictCategoryDao;
	/** 数据字典DAO */
	private DictionaryDao dictionaryDao;

	/**
	 * 设置数据字典类型DAO
	 * @param dictCategoryDao
	 */
	public void setDictCategoryDao(DictCategoryDao dictCategoryDao) {
		this.dictCategoryDao = dictCategoryDao;
	}

	/**
	 * 设置数据字典DAO
	 * @param dictionaryDao
	 */
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#getDictCategory(java.lang.Integer)
	 */
	public DictCategory getDictCategory(Integer id) {
		return this.dictCategoryDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#addDictCategory(com.yixin.framework.system.model.DictCategory)
	 */
	public void addDictCategory(DictCategory dictCategory) {
		if(this.hasExistence(dictCategory))
			throw new BusinessException(ErrorCode.CONFLICT);
		this.dictCategoryDao.save(dictCategory);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#deleteDictCategory(java.util.Collection)
	 */
	public void deleteDictCategory(Collection<DictCategory> dictCategorys) {
		DictCategory dictCategory = null;
		Iterator<DictCategory> iterator = dictCategorys.iterator();
		while (iterator.hasNext()) {
			dictCategory = iterator.next();
			this.deleteDictCategory(dictCategory);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#deleteDictCategory(com.yixin.framework.system.model.DictCategory)
	 */
	public void deleteDictCategory(DictCategory dictCategory) {
		if(dictCategory == null){
			throw new NullArgumentException("dictCategory");
		}
		List<Dictionary> dictionarys = this.dictionaryDao.getAllByProperty("dictCategory", dictCategory);
		if (dictionarys.size() > 0) {
			throw new BusinessException(ErrorCode.IN_USE);
		}
		try {
			this.dictCategoryDao.delete(dictCategory);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.IN_USE);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#ediDictCategory(com.yixin.framework.system.model.DictCategory)
	 */
	public void editDictCategory(DictCategory dictCategory) {
		if(this.hasExistence(dictCategory))
			throw new BusinessException(ErrorCode.CONFLICT);
		this.dictCategoryDao.update(dictCategory);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#getAllDictCategorys()
	 */
	public List<DictCategory> getAllDictCategorys() {
		return this.dictCategoryDao.getAll();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#getPageDictCategorys(long, long)
	 */
	public Page<DictCategory> getPageDictCategorys(long pageNo, long pageSize) {
		return this.dictCategoryDao.getPage(pageNo, pageSize);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictCategoryService#getModifyDictCategorys()
	 */
	public List<DictCategory> getModifyDictCategorys() {	 
		return this.dictCategoryDao.getAllByProperty("state", 1);
	}

	/**
	 * 判断该类型是否已存在
	 * @param dictCategory
	 * @return
	 */
	private Boolean hasExistence(DictCategory dictCategory){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", dictCategory.getCode());
		map.put("name", dictCategory.getName());
		List<DictCategory> listCategories = this.dictCategoryDao.getAllByOrProperties(map);
		if (listCategories.size() == 0)
			return false;
		Iterator<DictCategory> dcIterator = listCategories.iterator();
		while (dcIterator.hasNext()){
			DictCategory category = dcIterator.next();
			if(category.getDictCategoryId()!= dictCategory.getDictCategoryId())
				return true;
		}
		return false;
	}
}
