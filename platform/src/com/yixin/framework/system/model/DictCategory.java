/*
 * Project platform
 *
 * Classname DictCategory.java
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * 数据字典类型实体类
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name = "T_DictCategory")
public class DictCategory implements Serializable {
	private static final long serialVersionUID = -192602262116348573L;

	/** 字典类型ID */
	@Id
	@TableGenerator(name = "dictCat_Gen", table = "T_NumIdGenerator", pkColumnName = "code", pkColumnValue = "DictCategory_Id", valueColumnName = "value", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "dictCat_Gen")
	private Integer dictCategoryId;
	/** 字典类型编码 */
	private String code;	
	/** 字典类型名称 */
	private String name;
	/** 字典类型状态：1可见，0不可见 */
	private Integer state;
	
	/**
	 * 获取字典类型ID
	 * @return
	 */
	public Integer getDictCategoryId() {
		return dictCategoryId;
	}
	/**
	 * 设置字典类型ID
	 * @param dictCategoryId
	 */
	public void setDictCategoryId(Integer dictCategoryId) {
		this.dictCategoryId = dictCategoryId;
	}
	/**
	 * 获取字典类型编码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置字典类型编码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取字典类型名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置字典类型名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取字典类型状态
	 * @return
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置字典类型状态
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}		
}
