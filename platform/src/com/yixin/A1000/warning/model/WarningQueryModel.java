/*
 * Project platform
 *
 * Classname WarningQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 16：10
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.model;

import java.util.Calendar;
import java.util.Date;

import com.yixin.A1000.archive.model.Line;

/**
 * 告警查询模型类
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningQueryModel {

	/** 告警类型参数 */
	private WarningDict warningDict;
	/** */
	
	private String lineId; 
	/** 监测装置编码参数 */
	private String sensorCode;
	/** 监测代理编码参数 */
	private String cmaCode;
	/** 发生开始时间参数 */
	private Date beginTime;
	/** 发生结束时间参数 */
	private Date endTime;

	public  WarningQueryModel (){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7); 
		beginTime = cal.getTime();
		
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);		
		endTime = cal.getTime();
	}
	

	/**
	 * 获取告警类型参数
	 * @return
	 */
	public WarningDict getWarningDict() {
		return warningDict;
	}
	/**
	 * 设置告警类型参数
	 * @return
	 */
	public void setWarningDict(WarningDict warningDict) {
		this.warningDict = warningDict;
	}
	/**
	 * 获取监测装置编码参数
	 * @return
	 */
	public String getSensorCode() {
		return sensorCode;
	}
	/**
	 * 设置监测装置编码参数
	 * @return
	 */
	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}
	/**
	 * 获取监测代理编码参数
	 * @return
	 */
	public String getCmaCode() {
		return cmaCode;
	}
	/**
	 * 设置监测代理编码参数
	 * @return
	 */
	public void setCmaCode(String cmaCode) {
		this.cmaCode = cmaCode;
	}
	/**
	 * 获取发生开始时间参数
	 * @return
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置发生开始时间参数
	 * @return
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取发生结束时间参数
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置发生结束时间参数
	 * @return
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * @param lineId the lineId to set
	 */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}


	/**
	 * @return the lineId
	 */
	public String getLineId() {
		return lineId;
	}
	
	
}
