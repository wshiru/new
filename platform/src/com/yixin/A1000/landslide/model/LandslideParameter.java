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


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;


/**
 * 地质滑坡参数 实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_LandslideParams")
public class LandslideParameter {

	private static final long serialVersionUID = -88619752342238366L;

	/** 地质滑坡参数 数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String landslideParamsID;
    
	/** 监测装置ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	
	/** 传感器类型   1单轴，2双轴  **/
	private  Integer xyType;
	
	/** 监测点数 **/
	private  Integer sampleNum;
    
	/** 基岩深度  **/
	private  Double allDepth;
	
	 
	/** 用户角色 */
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "landslideParameter",cascade = {CascadeType.ALL})
	@OrderBy("pointNo desc") 
	private List<LandslideParameterDetail> landslideParameterDetails;


	public String getLandslideParamsID() {
		return landslideParamsID;
	}


	public void setLandslideParamsID(String landslideParamsID) {
		this.landslideParamsID = landslideParamsID;
	}


	public Sensor getSensor() {
		return sensor;
	}


	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}


	public Integer getXyType() {
		return xyType;
	}


	public void setXyType(Integer xyType) {
		this.xyType = xyType;
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


	public List<LandslideParameterDetail> getLandslideParameterDetails() {
		return landslideParameterDetails;
	}


	public void setLandslideParameterDetails(
			List<LandslideParameterDetail> landslideParameterDetails) {
		this.landslideParameterDetails = landslideParameterDetails;
	}
 
	
}
