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
package com.yixin.A1000.icethinckness.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;


/**
 * 覆冰监测参数 实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_IceThincknessParams")
public class IceThincknessParameter {

	private static final long serialVersionUID = -8861312366L;

	/** 覆冰监测参数 数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String iceThincknessParamsID;
    
	/** 监测装置ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	
	/** X方向倾斜一级报警*/
	private Double angleXAlarm1;
	/** X方向倾斜二级报警*/
	private Double angleXAlarm2;
	/** X方向倾斜三级报警*/
	private Double angleXAlarm3;
	/** Y方向倾斜一级报警*/
	private Double angleYAlarm1;
	/** Y方向倾斜二级报警*/
	private Double angleYAlarm2;
	/** Y方向倾斜三级报警*/
	private Double angleYAlarm3;
 
	/** 当前X方向报警状态 **/
	private  Integer currentXState;
	/** 当前Y方向报警状态 **/
	private  Integer currentYState;
	
	

	public String getIceThincknessParamsID() {
		return iceThincknessParamsID;
	}

	public void setIceThincknessParamsID(String iceThincknessParamsID) {
		this.iceThincknessParamsID = iceThincknessParamsID;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Double getAngleXAlarm1() {
		return angleXAlarm1;
	}

	public void setAngleXAlarm1(Double angleXAlarm1) {
		this.angleXAlarm1 = angleXAlarm1;
	}

	public Double getAngleXAlarm2() {
		return angleXAlarm2;
	}

	public void setAngleXAlarm2(Double angleXAlarm2) {
		this.angleXAlarm2 = angleXAlarm2;
	}

	public Double getAngleXAlarm3() {
		return angleXAlarm3;
	}

	public void setAngleXAlarm3(Double angleXAlarm3) {
		this.angleXAlarm3 = angleXAlarm3;
	}

	public Double getAngleYAlarm1() {
		return angleYAlarm1;
	}

	public void setAngleYAlarm1(Double angleYAlarm1) {
		this.angleYAlarm1 = angleYAlarm1;
	}

	public Double getAngleYAlarm2() {
		return angleYAlarm2;
	}

	public void setAngleYAlarm2(Double angleYAlarm2) {
		this.angleYAlarm2 = angleYAlarm2;
	}

	public Double getAngleYAlarm3() {
		return angleYAlarm3;
	}

	public void setAngleYAlarm3(Double angleYAlarm3) {
		this.angleYAlarm3 = angleYAlarm3;
	}

	public Integer getCurrentXState() {
		return currentXState;
	}

	public void setCurrentXState(Integer currentXState) {
		this.currentXState = currentXState;
	}
	
	public Integer getCurrentYState() {
		return currentYState;
	}

	public void setCurrentYState(Integer currentYState) {
		this.currentYState = currentYState;
	}
		
	
	
	
}
