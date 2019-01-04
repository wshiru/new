/*
 * Project platform
 *
 * Classname Role.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-8 下午
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */

package com.yixin.A1000.setting.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 设备参数明细
 * 
 * @author 梁立全

 */
@Entity
@Table(name = "T_DeviceParameterDetail")
public class DeviceParameterDetail {

	/** ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String deviceParameterDetailId;


	/** 设备参数 **/
	@ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "deviceParameterId")
	private DeviceParameter deviceParameter;
	
	/** 参数编码  名称 （英文代码)**/
	private String  name;
	
	/** 参数值 **/
	private String  value;
	
  
    /** 参数类型 **/
	private String  dataType;
	
	/** 长度 **/
	private Integer dataLength;
	
	/** 单位 **/
	private String  unit;
	

	/** 配置参数值域说明 **/
	private String  rangeValue;
	
	
	/** 参数备注信息说明 **/
	private String  note;
	

	/** 状态: 0：不下发   1 :下发 **/	
	private Integer state;

	/** 顺序号  **/
	private Integer orderNumber;

	
	/** 描述 **/
	private String  dsec;


	public String getDeviceParameterDetailId() {
		return deviceParameterDetailId;
	}

	
	public void setDeviceParameterDetailId(String deviceParameterDetailId) {
		this.deviceParameterDetailId = deviceParameterDetailId;
	}


	public DeviceParameter getDeviceParameter() {
		return deviceParameter;
	}


	public void setDeviceParameter(DeviceParameter deviceParameter) {
		this.deviceParameter = deviceParameter;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	public Integer getDataLength() {
		return dataLength;
	}


	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getRangeValue() {
		return rangeValue;
	}


	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Integer getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getDsec() {
		return dsec;
	}


	public void setDsec(String dsec) {
		this.dsec = dsec;
	}


	}
