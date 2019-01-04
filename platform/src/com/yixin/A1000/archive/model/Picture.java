/*
 * Project platform
 *
 * Class SensorType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:31:38
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.io.Serializable;
import java.util.Date;

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
 * 安装图片
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_Picture")
public class Picture implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4221654734184754L;

	/** 监测类型Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String pictureId;
	
	/** 所属监测装置*/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorId")
	private Sensor sensor;	

	/** 图片名称*/
	private String caption;

	/** 文件名*/
	private String fileName;

	/** 图片说明 */
	private String pictureDesc;
	
	/** 默认显示 */
	private Integer defaultShow;
	
	/** 创建时间 */
	private Date createTime;

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPictureDesc() {
		return pictureDesc;
	}

	public void setPictureDesc(String pictureDesc) {
		this.pictureDesc = pictureDesc;
	}

	public Integer getDefaultShow() {
		return defaultShow;
	}

	public void setDefaultShow(Integer defaultShow) {
		this.defaultShow = defaultShow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
