/*
 * Project platform
 *
 * Classname TowerTiltSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:23
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.towertilt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 杆塔倾斜实体类
 * 
 * @version v1.0.0
 * @author 
 * 
 */
@Entity
@Table(name = "T_TowerTiltSampling")
public class TowerTiltSampling extends BaseSampling {

	private static final long serialVersionUID = -7477525433557051305L;

	/** 杆塔倾斜数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String towerTiltSamplingId;

	/** 报警标志**/
	private Integer alarmFlag;
	
	/** 倾斜度 **/
	private Double inclination;

	/** 倾斜度 是否为告警数据 */
	@Transient
	private boolean isInclinationWarningData;
	
	/** 顺线倾斜度 */
	private Double gradientAlongLines;

	/** 顺线倾斜度 是否为告警数据 */
	@Transient
	private boolean isGradientAlongLinesWarningData;

	/** 横向倾斜度 */
	private Double lateralTilt;

	/** 横向倾斜度 是否为告警数据 */
	@Transient
	private boolean isLateralTiltWarningData;

	/** 顺线倾斜角 **/
	private Double angleX;

	/** 顺线倾斜角 是否为告警数据 */
	@Transient
	private boolean isAngleXWarningData;

	/** 横向倾斜角 **/
	private Double angleY;

	/** 横向倾斜角 是否为告警数据 */
	@Transient
	private boolean isAngleYWarningData;


	/** 综合倾斜度 */
	private Double generalInclination;

	/** 备用1 **/
	//private String reserve1;

	/** 备用2 **/
	//private String reserve2;

	/**
	 * 获取杆塔倾斜数据ID
	 * 
	 * @return
	 */
	public String getTowerTiltSamplingId() {
		return towerTiltSamplingId;
	}

	/**
	 * 设置杆塔倾斜数据ID
	 * 
	 * @param wireSagSamplingId
	 */
	public void setTowerTiltSamplingId(String towerTiltSamplingId) {
		this.towerTiltSamplingId = towerTiltSamplingId;
	}

	/**
	 * 获取综合倾斜度
	 * 
	 * @return
	 */
	public Double getGeneralInclination() {
		return generalInclination;
	}

	/**
	 * 设置综合倾斜度
	 * 
	 * @param generalInclination
	 */
	public void setGeneralInclination(Double generalInclination) {
		this.generalInclination = generalInclination;
	}

	/**
	 * 获取顺线倾斜度
	 * 
	 * @return
	 */
	public Double getGradientAlongLines() {
		return gradientAlongLines;
	}

	/**
	 * 设置顺线倾斜度
	 * 
	 * @return
	 */
	public void setGradientAlongLines(Double gradientAlongLines) {
		this.gradientAlongLines = gradientAlongLines;
	}

	/**
	 * 获取横向倾斜度
	 * 
	 * @return
	 */
	public Double getLateralTilt() {
		return lateralTilt;
	}

	/**
	 * 设置横向倾斜度
	 * 
	 * @param lateralTilt
	 */
	public void setLateralTilt(Double lateralTilt) {
		this.lateralTilt = lateralTilt;
	}

	public Double getInclination() {
		return inclination;
	}

	public void setInclination(Double inclination) {
		this.inclination = inclination;
	}

	public Double getAngleX() {
		return angleX;
	}

	public void setAngleX(Double angleX) {
		this.angleX = angleX;
	}

	public Double getAngleY() {
		return angleY;
	}

	public void setAngleY(Double angleY) {
		this.angleY = angleY;
	}

 
	public void setAlarmFlag(Integer alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public Integer getAlarmFlag() {
		return alarmFlag;
	}

	/**
	 * 获取 倾斜度 是否为告警数据
	 * 
	 * @return 倾斜度 是否为告警数据
	 */
	public boolean isInclinationWarningData() {
		return this.isInclinationWarningData;
	}

	/**
	 * 设置 倾斜度 是否为告警数据
	 * 
	 * @param isInclinationWarningData
	 *            倾斜度 是否为告警数据
	 */
	public void setInclinationWarningData(boolean isInclinationWarningData) {
		this.isInclinationWarningData = isInclinationWarningData;
	}

	/**
	 * 获取 顺线倾斜度 是否为告警数据
	 * 
	 * @return 顺线倾斜度 是否为告警数据
	 */
	public boolean isGradientAlongLinesWarningData() {
		return this.isGradientAlongLinesWarningData;
	}

	/**
	 * 设置 顺线倾斜度 是否为告警数据
	 * 
	 * @param isPradientAlongLinesWarningData
	 *            顺线倾斜度 是否为告警数据
	 */
	public void setGradientAlongLinesWarningData(boolean isGradientAlongLinesWarningData) {
		this.isGradientAlongLinesWarningData = isGradientAlongLinesWarningData;
	}

	/**
	 * 获取 横向倾斜度 是否为告警数据
	 * 
	 * @return 横向倾斜度 是否为告警数据
	 */
	public boolean isLateralTiltWarningData() {
		return this.isLateralTiltWarningData;
	}

	/**
	 * 设置 横向倾斜度 是否为告警数据
	 * 
	 * @param isLateralTiltWarningData
	 *            横向倾斜度 是否为告警数据
	 */
	public void setLateralTiltWarningData(boolean isLateralTiltWarningData) {
		this.isLateralTiltWarningData = isLateralTiltWarningData;
	}

	/**
	 * 获取 顺线倾斜角 是否为告警数据
	 * 
	 * @return 顺线倾斜角 是否为告警数据
	 */
	public boolean isAngleXWarningData() {
		return this.isAngleXWarningData;
	}

	/**
	 * 设置 顺线倾斜角 是否为告警数据
	 * 
	 * @param isAngleXWarningData
	 *            顺线倾斜角 是否为告警数据
	 */
	public void setAngleXWarningData(boolean isAngleXWarningData) {
		this.isAngleXWarningData = isAngleXWarningData;
	}

	/**
	 * 获取 横向倾斜角 是否为告警数据
	 * 
	 * @return 横向倾斜角 是否为告警数据
	 */
	public boolean isAngleYWarningData() {
		return this.isAngleYWarningData;
	}

	/**
	 * 设置 横向倾斜角 是否为告警数据
	 * 
	 * @param isAngleYWarningData
	 *            横向倾斜角 是否为告警数据
	 */
	public void setAngleYWarningData(boolean isAngleYWarningData) {
		this.isAngleYWarningData = isAngleYWarningData;
	}


}
