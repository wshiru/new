/* Project platform
 *
 * Classname WeatherSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:31
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 地质滑坡参数子表 实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_LandslideParamsDetail")
public class LandslideParameterDetail {

	private static final long serialVersionUID = -88619752342238366L;

	/** 地质滑坡参数子表数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String landslideParamsDetailID;
	
	/** 地质滑坡参数主表数据ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="landslideParamsId")
	private LandslideParameter landslideParameter;
	
	/** 监测点号  **/
	private  Integer pointNo;
	
	/** 相对深度  **/
	private  Double relativeDepth;
	
	/** 一级位移报警  **/
	private  Double alarm1;

	/** 二级位移报警  **/
	private  Double alarm2;
	
	/** 三级位移报警  **/
	private  Double alarm3;	
	
	/** 当前报警状态 **/
	private  Integer currentState;

	public String getLandslideParamsDetailID() {
		return landslideParamsDetailID;
	}

	public void setLandslideParamsDetailID(String landslideParamsDetailID) {
		this.landslideParamsDetailID = landslideParamsDetailID;
	}

	public LandslideParameter getLandslideParameter() {
		return landslideParameter;
	}

	public void setLandslideParameter(LandslideParameter landslideParameter) {
		this.landslideParameter = landslideParameter;
	}

	public Integer getPointNo() {
		return pointNo;
	}

	public void setPointNo(Integer pointNo) {
		this.pointNo = pointNo;
	}

	public Double getRelativeDepth() {
		return relativeDepth;
	}

	public void setRelativeDepth(Double relativeDepth) {
		this.relativeDepth = relativeDepth;
	}

	public Double getAlarm1() {
		return alarm1;
	}

	public void setAlarm1(Double alarm1) {
		this.alarm1 = alarm1;
	}

	public Double getAlarm2() {
		return alarm2;
	}

	public void setAlarm2(Double alarm2) {
		this.alarm2 = alarm2;
	}

	public Double getAlarm3() {
		return alarm3;
	}

	public void setAlarm3(Double alarm3) {
		this.alarm3 = alarm3;
	}

	public Integer getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Integer currentState) {
		this.currentState = currentState;
	}	
	
	 
	
}
