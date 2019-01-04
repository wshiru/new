/*
 * Project platform
 *
 * Classname IceThincknessSampling.java
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
package com.yixin.A1000.icethinckness.model;

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
@Table(name = "T_IceThincknessSampling")
public class IceThincknessSampling extends BaseSampling {

	private static final long serialVersionUID = -747257351305L;

	/** 杆塔倾斜数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String iceThincknessSamplingId;

	/** 报警标志**/
	private Integer alarmFlag;
	
	/** 等值覆冰厚度 **/
	private Double equalIceThickness;

	/** 等值覆冰厚度 是否为告警数据 */
	@Transient
	private boolean isEqualIceThicknessWarningData;
	
	/** 综合悬挂载荷 */
	private Double tension;

	/** 综合悬挂载荷 是否为告警数据 */
	@Transient
	private boolean isTensionWarningData;

	/** 不均衡张力差 */
	private Double tensionDifference;

	/** 不均衡张力差 是否为告警数据 */
	@Transient
	private boolean isTensionDifferenceWarningData;

	/** 绝缘子串风偏角 **/
	private Double windageYawAngle;

	/** 绝缘子串风偏角 是否为告警数据 */
	@Transient
	private boolean isWindageYawAngleWarningData;

	/** 绝缘子串偏斜角 **/
	private Double deflectionAngle;

	/** 绝缘子串偏斜角 是否为告警数据 */
	@Transient
	private boolean isDeflectionAngleWarningData;

	/***
	 * 
	 * @return
	 */
	public String getIceThincknessSamplingId() {
		return iceThincknessSamplingId;
	}

	public void setIceThincknessSamplingId(String iceThincknessSamplingId) {
		this.iceThincknessSamplingId = iceThincknessSamplingId;
	}

	public Integer getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(Integer alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public Double getEqualIceThickness() {
		return equalIceThickness;
	}

	public void setEqualIceThickness(Double equalIceThickness) {
		this.equalIceThickness = equalIceThickness;
	}

	public boolean isEqualIceThicknessWarningData() {
		return isEqualIceThicknessWarningData;
	}

	public void setEqualIceThicknessWarningData(
			boolean isEqualIceThicknessWarningData) {
		this.isEqualIceThicknessWarningData = isEqualIceThicknessWarningData;
	}

	public Double getTension() {
		return tension;
	}

	public void setTension(Double tension) {
		this.tension = tension;
	}

	public boolean isTensionWarningData() {
		return isTensionWarningData;
	}

	public void setTensionWarningData(boolean isTensionWarningData) {
		this.isTensionWarningData = isTensionWarningData;
	}

	public Double getTensionDifference() {
		return tensionDifference;
	}

	public void setTensionDifference(Double tensionDifference) {
		this.tensionDifference = tensionDifference;
	}

	public boolean isTensionDifferenceWarningData() {
		return isTensionDifferenceWarningData;
	}

	public void setTensionDifferenceWarningData(
			boolean isTensionDifferenceWarningData) {
		this.isTensionDifferenceWarningData = isTensionDifferenceWarningData;
	}

	public Double getWindageYawAngle() {
		return windageYawAngle;
	}

	public void setWindageYawAngle(Double windageYawAngle) {
		this.windageYawAngle = windageYawAngle;
	}

	public boolean isWindageYawAngleWarningData() {
		return isWindageYawAngleWarningData;
	}

	public void setWindageYawAngleWarningData(boolean isWindageYawAngleWarningData) {
		this.isWindageYawAngleWarningData = isWindageYawAngleWarningData;
	}

	public Double getDeflectionAngle() {
		return deflectionAngle;
	}

	public void setDeflectionAngle(Double deflectionAngle) {
		this.deflectionAngle = deflectionAngle;
	}

	public boolean isDeflectionAngleWarningData() {
		return isDeflectionAngleWarningData;
	}

	public void setDeflectionAngleWarningData(boolean isDeflectionAngleWarningData) {
		this.isDeflectionAngleWarningData = isDeflectionAngleWarningData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

 
	/** 备用1 **/
	//private String reserve1;

	/** 备用2 **/
	//private String reserve2;


}
