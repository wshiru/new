/*
 * Project platform
 *
 * Class Tower.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:22:14
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.io.Serializable;

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
 * 杆塔
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_Tower")
public class Tower implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -8763521943948952320L;

	/** 杆塔Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String towerId;

	/** 所属线路 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "lineId")
	private Line line = new Line();

	/** 杆塔类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "towerTypeId")
	private TowerType towerType = new TowerType();

	/** 杆塔编号 */
	private String towerCode;

	/** 杆塔高度。单位：m */
	private Double towerHeight;

	/** 经度 */
	private Double longitude;

	/** 纬度 */
	private Double latitude;

	/** 所在地址 */
	private String address;

	/** 描述 */
	private String towerDesc;

	/**
	 * 获取 杆塔Id
	 * 
	 * @return 杆塔Id
	 */
	public String getTowerId() {
		return this.towerId;
	}

	/**
	 * 设置 杆塔Id
	 * 
	 * @param towerId
	 *            杆塔Id
	 */
	public void setTowerId(String towerId) {
		this.towerId = towerId;
	}

	/**
	 * 获取 所属线路
	 * 
	 * @return 所属线路
	 */
	public Line getLine() {
		return this.line;
	}

	/**
	 * 设置 所属线路
	 * 
	 * @param line
	 *            所属线路
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * 获取 杆塔类型
	 * 
	 * @return 杆塔类型
	 */
	public TowerType getTowerType() {
		return this.towerType;
	}

	/**
	 * 设置 杆塔类型
	 * 
	 * @param towerType
	 *            杆塔类型
	 */
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}

	/**
	 * 获取 杆塔编号
	 * 
	 * @return 杆塔编号
	 */
	public String getTowerCode() {
		return this.towerCode;
	}

	/**
	 * 设置 杆塔编号
	 * 
	 * @param towerCode
	 *            杆塔编号
	 */
	public void setTowerCode(String towerCode) {
		this.towerCode = towerCode;
	}

	/**
	 * 获取 杆塔高度。单位：m
	 * 
	 * @return 杆塔高度。单位：m
	 */
	public Double getTowerHeight() {
		return this.towerHeight;
	}

	/**
	 * 设置 杆塔高度。单位：m
	 * 
	 * @param towerHeight
	 *            杆塔高度。单位：m
	 */
	public void setTowerHeight(Double towerHeight) {
		this.towerHeight = towerHeight;
	}

	/**
	 * 获取 经度
	 * 
	 * @return 经度
	 */
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * 设置 经度
	 * 
	 * @param longitude
	 *            经度
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取 纬度
	 * 
	 * @return 纬度
	 */
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * 设置 纬度
	 * 
	 * @param latitude
	 *            纬度
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取 所在地址
	 * 
	 * @return 所在地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置 所在地址
	 * 
	 * @param address
	 *            所在地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getTowerDesc() {
		return this.towerDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param towerDesc
	 *            描述
	 */
	public void setTowerDesc(String towerDesc) {
		this.towerDesc = towerDesc;
	}
}
