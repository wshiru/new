/*
 * Project platform
 *
 * Classname Dictionary.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-13 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 数据字典类
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name = "T_Dictionary")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = -192341248573L;
	
	/** 字典ID  */
	@Id
	@TableGenerator(name = "dict_Gen", table = "T_NumIdGenerator", pkColumnName = "code", pkColumnValue = "Dictionary_Id", valueColumnName = "value", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dict_Gen")
	private Integer dictionaryId;
	/** 字典编码  */
	private String code;
	/** 字典名称  */
	private String name;
	/** 字典状态 ：1可见，0不可见 */
	private Integer state;
 
	/** 字典类型  */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "dictCategoryId")
	private DictCategory dictCategory;
	
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
	 * 获取字典Id
	 * @return
	 */
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	/**
	 * 设置字典Id
	 * @param dictionaryId
	 */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	/**
	 * 设置字典编码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置字典编码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取字典名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置字典名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取字典状态
	 * @return
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置字典状态
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
}
