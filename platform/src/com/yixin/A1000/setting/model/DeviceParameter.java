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

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;


/**
 * CMA参数
 * 
 * @author 梁立全

 */
@Entity
@Table(name = "T_DeviceParameter")
public class DeviceParameter {

	@Id
	private String deviceParameterId;

	
	/** 监测代理 **/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "cmaInfoId")
	private CmaInfo cmaInfo;
	
	/** 监测装置 **/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorId")
	private Sensor sensor;
	
	
	/** CMA参数明细列表  按序号升序   cascade 一定要定义，否则无法存储子表数据**/
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "deviceParameter",cascade = {CascadeType.ALL})
	@OrderBy("orderNumber asc")
	private List<DeviceParameterDetail> deviceParameterDetails;
	

	/** 任务状态  0：未下发   1：已下发  2 ：已取消  3：已执行成功  **/
	private Integer state;

	/** 创建时间。与TaskConfig中的createTime相同 */
	@OrderBy(value = "desc")
	private Date createTime;


    /**
     * 返回  设备参数ID
     * @return
     */
	public String getDeviceParameterId() {
		return deviceParameterId;
	}

	/**
	 * 设置设备参数ID
	 * @param deviceParameterId
	 */
	public void setDeviceParameterId(String deviceParameterId) {
		this.deviceParameterId = deviceParameterId;
	}

	
	/**
	 * 返回监测代理
	 * @return
	 */
	public CmaInfo getCmaInfo() {
		return cmaInfo;
	}

	/**
	 * 设置监测代理
	 * @param cmaInfo
	 */
	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}

	/**
	 * 返回监置装置
	 * @return
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * 设置监测装置
	 * @param sensor
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}


	public List<DeviceParameterDetail> getDeviceParameterDetails() {
		return deviceParameterDetails;
	}

	public void setDeviceParameterDetails(
			List<DeviceParameterDetail> deviceParameterDetails) {
		this.deviceParameterDetails = deviceParameterDetails;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	



}
