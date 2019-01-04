/*
 * Project platform
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
 * 地质滑坡子表实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_LandslideSamplingDetail")
public class LandslideSamplingDetail {

	private static final long serialVersionUID = -886197456325748366L;

	/** 地质滑坡子表数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String landslideSamplingDetailId;
	
	/** 主表 **/	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "landslideSamplingId")
	private LandslideSampling landslideSampling = new LandslideSampling();     
	
	/** 监测点号  **/
	private  Integer pointNo;
	
	/** 相对深度 **/
	private  Double relativeDepth;
    
	/** X倾角  **/
	private  Double angleX;
	
	/** Y倾角 **/
	private  Double angleY;
	
	/** X位移 **/
	private  Double displacementX;

	/** Y位移 **/
	private  Double displacementY;
	
	/** 合位移 **/
	private  Double displacement;

	public String getLandslideSamplingDetailId() {
		return landslideSamplingDetailId;
	}

	public void setLandslideSamplingDetailId(String landslideSamplingDetailId) {
		this.landslideSamplingDetailId = landslideSamplingDetailId;
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

	public Double getDisplacementX() {
		return displacementX;
	}

	public void setDisplacementX(Double displacementX) {
		this.displacementX = displacementX;
	}

	public Double getDisplacementY() {
		return displacementY;
	}

	public void setDisplacementY(Double displacementY) {
		this.displacementY = displacementY;
	}

	public Double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Double displacement) {
		this.displacement = displacement;
	}

	public void setLandslideSampling(LandslideSampling landslideSampling) {
		this.landslideSampling = landslideSampling;
	}

	public LandslideSampling getLandslideSampling() {
		return landslideSampling;
	}
	
	 
	
}
