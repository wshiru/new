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

import java.util.Date;

/**
 * 请求历史数据
 * 
 * @version v1.0.0
 * @author 梁立全
 */
public class RequestHistoryData {

	/** 开始时间 */
	private Date beginTime;
	/** 结束时间 */
	private Date endTime;
	
	
	/**
	 * 获取 开始时间
	 * @return
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置 开始时间
	 * @param beginTime
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * 获取 结束时间
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * 设置 结束时间
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
}
