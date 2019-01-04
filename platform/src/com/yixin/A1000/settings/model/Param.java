/*
 * Project platform
 *
 * Class Param.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-7 下午03:43:39
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.settings.model;

/**
 * 参数。终端报警阈值、终端模型参数等
 * 
 * @version v1.0.0
 * @author 
 */
public class Param {

	/** 参数名称 */
	private String name = "paramName";
	
	/** 参数值 */
	private String value;
	
	/** 说明 */
	private String desc;

	/**
	 * 获取 参数名称
	 * 
	 * @return 参数名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 参数名称
	 * 
	 * @param name
	 *            参数名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 参数值
	 * 
	 * @return 参数值
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * 设置 参数值
	 * 
	 * @param value
	 *            参数值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取 说明
	 * 
	 * @return 说明
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 设置 说明
	 * 
	 * @param desc
	 *            说明
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
