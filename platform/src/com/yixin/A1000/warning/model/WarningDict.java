/*
 * Project platform
 *
 * Classname WarningDict.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 11:47
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 告警类型实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WarningDict")
public class WarningDict {
	
	/** 告警类型ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String warningDictId;
	/** 告警类型编码 */
	private String code;
	/** 告警类型名称 */
	private String name;
	
	/**
	 * 获取告警类型ID
	 * @return
	 */
	public String getWarningDictId() {
		return warningDictId;
	}
	/**
	 * 设置告警类型ID
	 * @return
	 */
	public void setWarningDictId(String warningDictId) {
		this.warningDictId = warningDictId;
	}
	/**
	 * 获取告警类型编码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置告警类型编码
	 * @return
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取告警类型名称
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置告警类型名称
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}	
	
}
