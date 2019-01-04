/*
 * Project platform
 *
 * Class DateQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午09:56:54
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model.db;

import java.util.Date;

/**
 * Date属性所使用的比较机制。即使用“=”或“LIKE”进行字符串的比较。
 * 
 * @version v1.0.0
 * @author 
 */
public enum DateQueryMode {

	/** 使用等号“between A and B”进行比较查找，必须使用init(Date, Date)方法初始化开始时间与结束时间。 */
	BETWEEN,

	/** 使用等号“not between A and B”进行比较查找，必须使用init(Date, Date)方法初始化开始时间与结束时间。 */
	NOTBETWEEN,

	/** 使用等号“=”进行比较查找 */
	EQ,

	/** 使用不等于“<>”进行比较查找 */
	NEQ,

	/** 使用大于号“>”进行比较查找 */
	GT,

	/** 使用小于号“<”进行比较查找 */
	LT,

	/** 使用大于等于号号“>=”进行比较查找 */
	GE,

	/** 使用小于等于号“<=”进行比较查找 */
	LE,
	
	/** 使用“IS”进行查找，空判断。即使用“IS NULL” */
	IS,
	
	/** 使用“IS NOT”进行查找，非空判断。即使用“IS NOT NULL” */
	ISNOT;

	/** 使用DateQueryModel.BETWEEN时的开始时间 */
	private Date begin;

	/** 使用DateQueryModel.BETWEEN时的结束时间 */
	private Date end;

	/**
	 * 当使用 <code>DateQueryModel.BETWEEN</code> 或
	 * <code>DateQueryModel.NOTBETWEEN</code> 的比较机制时，必须使用该方法初始化开始时间与结束时间。
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return 所使用的比较机制
	 */
	public DateQueryMode init(Date begin, Date end) {
		this.begin = begin;
		this.end = end;
		return BETWEEN;
	}

	/**
	 * 获取 使用DateQueryModel.BETWEEN时的开始时间
	 * 
	 * @return 使用DateQueryModel.BETWEEN时的开始时间
	 */
	public Date getBegin() {
		return this.begin;
	}

	/**
	 * 设置 使用DateQueryModel.BETWEEN时的开始时间
	 * 
	 * @param begin
	 *            使用DateQueryModel.BETWEEN时的开始时间
	 */
	public void setBegin(Date begin) {
		this.begin = begin;
	}

	/**
	 * 获取 使用DateQueryModel.BETWEEN时的结束时间
	 * 
	 * @return 使用DateQueryModel.BETWEEN时的结束时间
	 */
	public Date getEnd() {
		return this.end;
	}

	/**
	 * 设置 使用DateQueryModel.BETWEEN时的结束时间
	 * 
	 * @param end
	 *            使用DateQueryModel.BETWEEN时的结束时间
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
}
