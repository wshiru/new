/*
 * Project platform
 *
 * Classname DictionaryServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-13 上午 11:02
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
import org.springframework.dao.DataIntegrityViolationException;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
import com.yixin.framework.system.dao.DictionaryDao;
import com.yixin.framework.system.model.DictCategory;
import com.yixin.framework.system.model.Dictionary;
import com.yixin.framework.system.service.DictionaryService;

/**
 * 数据字典服务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class DictionaryServiceImpl implements DictionaryService {

	/** 数据字典DAO */
	private DictionaryDao dictionaryDao;

	/**
	 * 设置数据字典DAO
	 * @param dictionaryDao
	 */
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#addDictionary(com.yixin.framework.system.model.Dictionary)
	 */
	public void addDictionary(Dictionary dictionary) {	
		if (this.hasExistence(dictionary)) {
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		if(dictionary.getState() == null)
			dictionary.setState(1);
		this.dictionaryDao.save(dictionary);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#deleteDictionary(java.util.Collection)
	 */
	public void deleteDictionary(Collection<Dictionary> dictionarys) {
		Dictionary dictionary = null;
		Iterator<Dictionary> iterator = dictionarys.iterator();
		while(iterator.hasNext()){
			dictionary = iterator.next();
			this.dictionaryDao.delete(dictionary);
		}			
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#deleteDictionary(com.yixin.framework.system.model.Dictionary)
	 */
	public void deleteDictionary(Dictionary dictionary) {
		if(dictionary == null){
			throw new NullArgumentException("dictionary");
		}
		try{
			this.dictionaryDao.delete(dictionary);			
		}catch(DataIntegrityViolationException e){
			throw new BusinessException(ErrorCode.IN_USE);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#editDictionary(com.yixin.framework.system.model.Dictionary)
	 */
	public void editDictionary(Dictionary dictionary) {				
		if (this.hasExistence(dictionary)) {
			throw new BusinessException(ErrorCode.CONFLICT);
		}		
		this.dictionaryDao.update(dictionary);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getAllDictionarys()
	 */
	public List<Dictionary> getAllDictionarys() {
		return dictionaryDao.getAll();
	}	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getAllDictionarysByDictCategory(com.yixin.framework.system.model.DictCategory)
	 */
	public List<Dictionary> getAllDictionarysByDictCategory(
			DictCategory dictCategory) {
		if(null == dictCategory)
			return dictionaryDao.getAll();
		else 
			return dictionaryDao.getAllByProperty("dictCategory", dictCategory);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getAllDictionarysByDictCategoryId(java.lang.Integer)
	 */
	public List<Dictionary> getAllDictionarysByDictCategoryId(
			Integer dictCategoryId) {
		return this.dictionaryDao.getAllByProperty("dictCategory.dictCategoryId", dictCategoryId);	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getDictionary(java.lang.Integer)
	 */
	public Dictionary getDictionary(Integer id) {
		return dictionaryDao.findById(id);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getPageDictionarys(long, long)
	 */
	public Page<Dictionary> getPageDictionarys(long pageNo, long pageSize) {
		return dictionaryDao.getPage(pageNo, pageSize);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getPageDictionarysByDictCategory(com.yixin.framework.system.model.DictCategory, long, long)
	 */
	public Page<Dictionary> getPageDictionarysByDictCategory(
			DictCategory dictCategory, long pageNo, long pageSize) {
		if(null == dictCategory)
			return this.getPageDictionarys(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictCategory", dictCategory);
		return dictionaryDao.getPageByProperties(map, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getDictionaryByCode(java.lang.Integer, java.lang.String)
	 */
	public Dictionary getDictionaryByCode(Integer dictCategoryId, String code) {
		//根据分类ID和CODE查询
		Map<String,Object> mapQueryCode = new HashMap<String,Object>();
		mapQueryCode.put("dictCategory.dictCategoryId", dictCategoryId);
		mapQueryCode.put("code", code);		
		List<Dictionary> dictionarys = this.dictionaryDao.getAllLikeProperties(mapQueryCode);
		if(dictionarys.size()>0){
			return dictionarys.get(0);
		}else{
			return null;
		}	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.DictionaryService#getDictionaryByName(java.lang.Integer, java.lang.String)
	 */
	public Dictionary getDictionaryByName(Integer dictCategoryId, String name) {
		//根据分类ID和name查询
		Map<String,Object> mapQueryCode = new HashMap<String,Object>();
		mapQueryCode.put("dictCategory.dictCategoryId", dictCategoryId);
		mapQueryCode.put("name", name);		
		List<Dictionary> dictionarys = this.dictionaryDao.getAllLikeProperties(mapQueryCode);
		if(dictionarys.size()>0){
			return dictionarys.get(0);
		}else{
			return null;
		}	
	}

	/**
	 * 判断该类型是否已存在
	 * @param dictionary
	 * @return
	 */
	private Boolean hasExistence(Dictionary dictionary){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", dictionary.getCode());
		map.put("name", dictionary.getName());
		List<Dictionary> listDictionaries = this.dictionaryDao.getAllByOrProperties(map);
		if(listDictionaries.size() == 0)
			return false;
		Iterator<Dictionary> dIterator = listDictionaries.iterator();
		while(dIterator.hasNext()){
			Dictionary dict = dIterator.next();
			if(dict.getDictCategory().getDictCategoryId()==dictionary.getDictCategory().getDictCategoryId() &&
					dict.getDictionaryId() != dictionary.getDictionaryId()) //同类，两个字典
				return true;
		}
		return false;
	}

	@Override
	public Page<Dictionary> getPageDictionarys(Integer dictCategoryId,
			String code, String name, long pageNo, long pageSize) {
		Map<String,Object> mapQueryCode = new HashMap<String,Object>();
		if(null != dictCategoryId)
			mapQueryCode.put("dictCategory.dictCategoryId", dictCategoryId);
		if(null != code && !code.trim().isEmpty())
			mapQueryCode.put("code", code);
		if(null != name && !name.trim().isEmpty())
			mapQueryCode.put("name", name);
		return dictionaryDao.getPageLikeProperties(mapQueryCode, pageNo, pageSize);
	}

}
