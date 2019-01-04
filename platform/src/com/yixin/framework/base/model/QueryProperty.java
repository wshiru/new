/*
 * Project platform
 *
 * Class QueryProperty.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午03:06:42
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model;

import java.util.Date;

import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 查询属性。<b>注意：当使用DateQueryMode.BETWEEN，或者 DateQueryMode.NOTBETWEEN作为比较方式时
 * ，对象中的value对应开始时间，而endTime作为结束时间。使用其他方式时，endTime不用设置。</b>
 * 
 * @version v1.0.0
 * @author 
 */
public class QueryProperty {

	/** 属性名称 */
	private String name;

	/** 属性的值 */
	private Object value;

	/** 字符串类型的比较方式 */
	private StringQueryMode stringQueryMode;

	/** 数字类型的比较方式 */
	private NumberQueryMode numberQueryMode;

	/** 对象类型的比较方式 */
	private ObjectQueryMode objectQueryMode;

	/** 日期类型的比较方式 */
	private DateQueryMode dateQueryMode;
	
	/** 数据排序模式 **/
	private DataOrder dataOrder;

	/**
	 * 结束时间。该项专门用于日期字段使用QueryModeType.DATE_BETWEEN，或者
	 * QueryModeType.DATE_NOTBETWEEN设置的结束时间。此时该对象的value值将对应开始时间
	 */
	private Date endTime;
	
	/**
	 * 只排序，不查询
	 * @param name	排序的字段
	 * @param dataOrder
	 * 			排序的方式
	 */
	public QueryProperty(String name,  DataOrder dataOrder) {
		this.name = name;		
		this.dataOrder = dataOrder;
	}
	/**
	 * 构造方法。属性为字符串类型时使用该构造方法。
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @param stringQueryMode
	 *            字符串类型的比较方式
	 */
	public QueryProperty(String name, Object value, StringQueryMode stringQueryMode) {
		this.name = name;
		this.value = value;
		this.stringQueryMode = stringQueryMode;
	}

	/**
	 * 构造方法。属性为数字类型时使用该构造方法。
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @param numberQueryMode
	 *            数字类型的比较方式
	 */
	public QueryProperty(String name, Object value, NumberQueryMode numberQueryMode) {
		this.name = name;
		this.value = value;
		this.numberQueryMode = numberQueryMode;
	}

	/**
	 * 构造方法。属性为数字类型时使用该构造方法。
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @param numberQueryMode
	 *            对象类型的比较方式
	 */
	public QueryProperty(String name, Object value, ObjectQueryMode objectQueryMode) {
		this.name = name;
		this.value = value;
		this.objectQueryMode = objectQueryMode;
	}

	/**
	 * 构造方法。属性为Date类型且不使用DateQueryMode.BETWEEN和DateQueryMode.
	 * NOTBETWEEN比较方式时时使用该构造方法。
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @param dateQueryMode
	 *            日期类型的比较方式
	 */
	public QueryProperty(String name, Object value, DateQueryMode dateQueryMode) {
		this.name = name;
		this.value = value;
		this.dateQueryMode = dateQueryMode;
	}

	/**
	 * 构造方法。属性为Date类型且使用DateQueryMode.BETWEEN或DateQueryMode.
	 * NOTBETWEEN比较方式时时使用该构造方法。
	 * 
	 * @param name
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @param dateQueryMode
	 *            日期类型的比较方式
	 * @param endTime
	 *            结束时间。该项专门用于日期字段使用QueryModeType.DATE_BETWEEN，或者
	 *            QueryModeType.DATE_NOTBETWEEN设置的结束时间。此时该对象的value值将对应开始时间
	 */
	public QueryProperty(String name, Object value, DateQueryMode dateQueryMode, Date endTime) {
		this.name = name;
		this.value = value;
		this.dateQueryMode = dateQueryMode;
		this.endTime = endTime;
	}

	/**
	 * 获取 属性名称
	 * 
	 * @return 属性名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 获取 属性的值
	 * 
	 * @return 属性的值
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * 获取 字符串类型的比较方式
	 * 
	 * @return 字符串类型的比较方式
	 */
	public StringQueryMode getStringQueryMode() {
		return this.stringQueryMode;
	}

	/**
	 * 获取 数字类型的比较方式
	 * 
	 * @return 数字类型的比较方式
	 */
	public NumberQueryMode getNumberQueryMode() {
		return this.numberQueryMode;
	}

	/**
	 * 获取 对象类型的比较方式
	 * 
	 * @return 对象类型的比较方式
	 */
	public ObjectQueryMode getObjectQueryMode() {
		return this.objectQueryMode;
	}

	/**
	 * 获取 日期类型的比较方式
	 * 
	 * @return 日期类型的比较方式
	 */
	public DateQueryMode getDateQueryMode() {
		return this.dateQueryMode;
	}

	/**
	 * 获取 结束时间。该项专门用于日期字段使用QueryModeType.DATE_BETWEEN，或者
	 * QueryModeType.DATE_NOTBETWEEN设置的结束时间。此时该对象的value值将对应开始时间
	 * 
	 * @return 结束时间。该项专门用于日期字段使用QueryModeType.DATE_BETWEEN，或者
	 *         QueryModeType.DATE_NOTBETWEEN设置的结束时间。此时该对象的value值将对应开始时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	public DataOrder getDataOrder() {
		return dataOrder;
	}

	public void setDataOrder(DataOrder dataOrder) {
		this.dataOrder = dataOrder;
	}
	
	
}
