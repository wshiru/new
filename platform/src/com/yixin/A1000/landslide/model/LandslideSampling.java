/*
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


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;


/**
 * 地质滑坡实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_LandslideSampling")
public class LandslideSampling extends BaseSampling{

	private static final long serialVersionUID = 1315887720010338954L;
	

	/** 地质滑坡数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String landslideSamplingId;
    
 	/** 告警标识  **/
	private  Integer alarmFlag;
	
	/** 监测点数 **/
	private  Integer sampleNum;
    
	/** 基岩深度  **/
	private  Double allDepth;
	
	

	/** 子表数据 */
		
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "landslideSampling",cascade = {})
	@OrderBy("pointNo desc") 
	private List<LandslideSamplingDetail> landslideSamplingDetails = new ArrayList<LandslideSamplingDetail>();
	
	//@OneToMany(fetch = FetchType.EAGER,mappedBy = "landslideSampling",cascade = {CascadeType.ALL})
	//private List<LandslideSamplingDetail> landslideSamplingDetails = new ArrayList<LandslideSamplingDetail>();

	public String getLandslideSamplingId() {
		return landslideSamplingId;
	}

	public void setLandslideSamplingId(String landslideSamplingId) {
		this.landslideSamplingId = landslideSamplingId;
	}

	public Integer getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(Integer alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public Integer getSampleNum() {
		return sampleNum;
	}

	public void setSampleNum(Integer sampleNum) {
		this.sampleNum = sampleNum;
	}

	public Double getAllDepth() {
		return allDepth;
	}

	public void setAllDepth(Double allDepth) {
		this.allDepth = allDepth;
	}


	public List<LandslideSamplingDetail> getLandslideSamplingDetails() {
		return landslideSamplingDetails;
	}

	public void setLandslideSamplingDetails(
			List<LandslideSamplingDetail> landslideSamplingDetails) {
		this.landslideSamplingDetails = landslideSamplingDetails;
	}
 
}
