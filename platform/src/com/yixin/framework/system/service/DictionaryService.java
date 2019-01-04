/*
 * Project platform
 *
 * Classname DictionaryService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-13 上午 9:20
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
import com.yixin.framework.system.model.Dictionary;

/**
 * 数据字典服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface DictionaryService {
	/**
	 * 添加字典
	 * @param dictionary
	 */
	public abstract void addDictionary(Dictionary dictionary);
	
	/**
	 * 修改字典
	 * @param dictionary
	 */
	public abstract void editDictionary(Dictionary dictionary);
	
	/**
	 * 删除字典
	 * @param dictionary
	 */
	public abstract void deleteDictionary(Dictionary dictionary);
	
	/**
	 * 删除多项字典
	 * @param dictionarys
	 */
	public abstract void deleteDictionary(Collection<Dictionary> dictionarys);

	/**
	 * 根据ID得到字典项
	 * @param id
	 * @return
	 */
	public abstract Dictionary  getDictionary(Integer id);
	
	/**
	 * 根据字典分类ID和Code得到明细
	 * @param dictCategoryId
	 * @param code
	 * @return
	 */
	public abstract Dictionary getDictionaryByCode(Integer dictCategoryId , String code);
	/**
	 * 根据字典分类ID和Name得到明细
	 * @param dictCategoryId
	 * @param name
	 * @return
	 */
	public abstract Dictionary getDictionaryByName(Integer dictCategoryId , String name);
	
	/**
	 * 得到所有字典
	 * @return
	 */
	public abstract List<Dictionary> getAllDictionarys();
	/**
	 * 根据字典分类得到该分类的所有字典
	 * @param dictCategory
	 * @return
	 */
	public abstract List<Dictionary> getAllDictionarysByDictCategory(DictCategory dictCategory);
	/**
	 * 根据字典分类ID得到该分类的所有字典
	 * @param dictCategoryId
	 * @return
	 */
	public abstract List<Dictionary> getAllDictionarysByDictCategoryId(Integer dictCategoryId);

	/**
	 * 分页显示字典
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page<Dictionary> getPageDictionarys(long pageNo, long pageSize);
	/**
	 * 根据字典分类得到该分类的所有字典
	 * @param dictCategory
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page<Dictionary> getPageDictionarysByDictCategory(DictCategory dictCategory , long pageNo, long pageSize);
	
	/**
	 * 根据字典分类ID、Code、name得到所有字典
	 * @param dictCategoryId
	 * @param code
	 * @return
	 */
	public abstract Page<Dictionary> getPageDictionarys(Integer dictCategoryId , String code, String name, long pageNo, long pageSize);

	

}
