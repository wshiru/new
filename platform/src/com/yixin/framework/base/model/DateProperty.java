/*
 * Project platform
 *
 * Classname DateProperty.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 上午10:31:25
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model;

import java.util.Date;

/**
 * 日期时间范围属性类。当数据库查询时，需要查询某一日期/时间字段在时间范围内的记录时用到。
 * 表示查询propertyName在时间beginTime与endTime间的记录，并按dataOrder排序，即“propertyName between
 * beginTime and endTime order by dataOrder”。
 * 
 * @version v1.0.0
 * @author 
 */
public class DateProperty {

	/** 日期属性名称 */
	private String propertyName;

	/** 开始时间 */
	private Date beginTime;

	/** 结束时间 */
	private Date endTime;

	/** 按照propertyName的排序，默认为升序 */
	private DataOrder dataOrder = DataOrder.ASC;


	/**
	 * 构造方法
	 */
	public DateProperty() {
	}

	/**
	 * 构造方法
	 * 
	 * @param propertyName
	 *            日期属性名称
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 */
	public DateProperty(String propertyName, Date beginTime, Date endTime) {
		this.propertyName = propertyName;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	/**
	 * 获取 日期属性名称
	 * 
	 * @return 日期属性名称
	 */
	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * 设置 日期属性名称
	 * 
	 * @param propertyName
	 *            日期属性名称
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 获取 开始时间
	 * 
	 * @return 开始时间
	 */
	public Date getBeginTime() {
		return this.beginTime;
	}

	/**
	 * 设置 开始时间
	 * 
	 * @param beginTime
	 *            开始时间
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 获取 结束时间
	 * 
	 * @return 结束时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 设置 结束时间
	 * 
	 * @param endTime
	 *            结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取 按照propertyName的排序
	 * 
	 * @return 按照propertyName的排序
	 */
	public DataOrder getDataOrder() {
		return this.dataOrder;
	}

	/**
	 * 设置 按照propertyName的排序，默认为升序
	 * 
	 * @param dataOrder
	 *            按照propertyName的排序，默认为升序
	 */
	public void setDataOrder(DataOrder dataOrder) {
		this.dataOrder = dataOrder;
	}
}
