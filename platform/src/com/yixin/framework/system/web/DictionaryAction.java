/*
 * Project platform
 *
 * Classname BaseAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-15 上午10:10
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.web;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.web.BaseAction;
import com.yixin.framework.system.model.DictCategory;
import com.yixin.framework.system.model.Dictionary;
import com.yixin.framework.system.service.DictCategoryService;
import com.yixin.framework.system.service.DictionaryService;
import com.yixin.framework.system.util.OperationLogger;
import com.yixin.framework.system.util.OperationType;

/**
 * 数据字典Action类
 * @version v1.0.0
 * @author 
 *
 */
public class DictionaryAction extends BaseAction<Dictionary> {

	/** 序列版本ID  */
	private static final long serialVersionUID = 1005624242417547231L;

	/** 数据字典类型列表键名 */
	private static final String DICTCATEGORY_LIST_NAME = "dictCategoryList";

	/** 数据字典服务 */
	private DictionaryService dictionaryService;

	/** 数据字典类型服务 */
	private DictCategoryService dictCategoryService;

	/** 用于保存提交数据的数据字典对象 */
	private Dictionary dictionary;
	
	/** 用于保存所选的数据字典类型 */
	private DictCategory dictCategory;
	
	/** 字典编码查询项 */
	private String queryDictionaryCode;
	/** 字典名称查询项 */
	private String queryDictionaryName;
	
	
	/**
	 * 设置字典服务
	 * @param dictionaryService
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	/**
	 * 设置字典类型服务
	 * @return
	 */
	public void setDictCategoryService(DictCategoryService dictCategoryService) {
		this.dictCategoryService = dictCategoryService;
	}
	/**
	 * 获取字典
	 * @return
	 */
	public Dictionary getDictionary() {
		return dictionary;
	}
	/**
	 * 设置字典
	 * @param dictionary
	 */
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	/**
	 * 获取字典类型
	 * @return
	 */
	public DictCategory getDictCategory() {
		return dictCategory;
	}
	/**
	 * 设置字典类型
	 * @param dictCategory
	 */
	public void setDictCategory(DictCategory dictCategory) {
		this.dictCategory = dictCategory;
	}
	/**
	 * 获取查询字典编码
	 * @return
	 */
	public String getQueryDictionaryCode() {
		return queryDictionaryCode;
	}
	/**
	 * 设置查询字典编码
	 * @param queryDictionaryCode
	 */
	public void setQueryDictionaryCode(String queryDictionaryCode) {
		this.queryDictionaryCode = queryDictionaryCode;
	}
	/**
	 * 获取查询字典名称
	 * @return
	 */
	public String getQueryDictionaryName() {
		return queryDictionaryName;
	}
	/**
	 * 设置查询字典编码
	 * @param queryDictionaryName
	 */
	public void setQueryDictionaryName(String queryDictionaryName) {
		this.queryDictionaryName = queryDictionaryName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		if(null == dictCategory || dictCategory.getDictCategoryId() == null)
			dictCategory = dictCategoryService.getDictCategory(1);
		if(null == dictCategory){ //无字典分类数据
			page = new Page<Dictionary>(getPageNo(), getPageSize(), 0, null);
		}
		else {
			page = dictionaryService.getPageDictionarys(dictCategory.getDictCategoryId(), 
					queryDictionaryCode, queryDictionaryName, getPageNo(), getPageSize());
		}
//		else if((null == queryDictionaryCode || queryDictionaryCode.trim().isEmpty()) && 
//				(null == queryDictionaryName || queryDictionaryName.trim().isEmpty())){ 
//			page = dictionaryService.getPageDictionarysByDictCategory(dictCategory, 
//					getPageNo(), getPageSize());
//		}
//		else { //字典编码或名称查询
//			Dictionary dict = null;
//			Collection<Dictionary> records = new ArrayList<Dictionary>();
//			if(!queryDictionaryCode.trim().isEmpty())
//				dict = dictionaryService.getDictionaryByCode(dictCategory.getDictCategoryId(), queryDictionaryCode);
//			else
//				dict = dictionaryService.getDictionaryByName(dictCategory.getDictCategoryId(), queryDictionaryName);
//			if(null != dict)
//				records.add(dict);
//			page = new Page<Dictionary>(getPageNo(), getPageSize(), records.size(), records);
//			
//		}
		
		return super.list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#add()
	 */
	@Override
	public String add() {
		dictionary = new Dictionary();
		dictionary.setDictCategory(dictCategory);
		return super.add();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.web.BaseAction#save()
	 */
	@Override
	public String save() {
		dictionaryService.addDictionary(dictionary);
		OperationLogger.addLog(request, OperationType.ADD_DATA, "添加字典" + dictionary.getCode() + "(" + dictionary.getName() + ")");
		setSuccessMsg("字典添加成功");
		return list();	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.web.BaseAction#update()
	 */
	@Override
	public String update() {
		dictionaryService.editDictionary(dictionary);
		OperationLogger.addLog(request, OperationType.EDIT_DATA, "修改字典" + dictionary.getCode() + "(" + dictionary.getName() + ")");
		setSuccessMsg("字典修改成功");
		return list();	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#rawDelete()
	 */
	@Override
	public void rawDelete() {
		dictionaryService.deleteDictionary(dictionary);
		OperationLogger.addLog(request, OperationType.DELETE_DATA, "删除字典" + dictionary.getCode() + "(" + dictionary.getName() + ")");
		setSuccessMsg("删除字典成功");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#getId()
	 */
	@Override
	protected Object getId() {
		return dictionary.getDictionaryId();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#getPersistenceById()
	 */
	@Override
	protected Dictionary getPersistenceById() {
		return dictionaryService.getDictionary((Integer)getId());
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#setEntityValues(java.lang.Object)
	 */
	@Override
	protected void setEntityValues(Dictionary entity) {
		this.dictionary = entity;
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#initData()
	 */
	@Override
	protected void initData(){
		request.setAttribute(DICTCATEGORY_LIST_NAME, dictCategoryService.getModifyDictCategorys());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
	//进行重写，始终加载页面数据，否则验证失败时，页面数据未加载会出错
		initData();
		super.validate();
	}
	

}


