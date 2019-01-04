/*
 * Project platform
 *
 * Class TowerQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午11:38:47
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 杆塔的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerQueryModel {

	/** 所属线路。如果为null，则表示该项不作限制。否则采用lineQueryMode指定的比较机制。默认为null。 */
	private Line line;

	/** 所属线路的比较机制 */
	private ObjectQueryMode lineQueryMode;

	/** 杆塔类型。如果为null，则表示该项不作限制。否则采用towerTypeQueryMode指定的比较机制。默认为null。 */
	private TowerType towerType;

	/** 杆塔类型的比较机制 */
	private ObjectQueryMode towerTypeQueryMode;

	/** 杆塔编号。如果为null，则表示该项不作限制。否则采用towerCodeQueryMode指定的比较机制。默认为null。 */
	private String towerCode;

	/** 杆塔编号的比较机制 */
	private StringQueryMode towerCodeQueryMode;

	/** 杆塔高度。如果为null，则表示该项不作限制。否则采用towerHeightQueryMode指定的比较机制。默认为null。 */
	private Double towerHeight;

	/** 杆塔高度的比较机制 */
	private NumberQueryMode towerHeightQueryMode;

	/** 经度。如果为null，则表示该项不作限制。否则采用longitudeQueryMode指定的比较机制。默认为null。 */
	private Double longitude;

	/** 经度的比较机制 */
	private NumberQueryMode longitudeQueryMode;

	/** 纬度。如果为null，则表示该项不作限制。否则采用latitudeQueryMode指定的比较机制。默认为null。 */
	private Double latitude;

	/** 纬度的比较机制 */
	private NumberQueryMode latitudeQueryMode;

	/** 所在地址。如果为null，则表示该项不作限制。否则采用addressQueryMode指定的比较机制。默认为null。 */
	private String address;

	/** 所在地址的比较机制 */
	private StringQueryMode addressQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用towerDescQueryMode指定的比较机制。默认为null。 */
	private String towerDesc;

	/** 描述的比较机制 */
	private StringQueryMode towerDescQueryMode;

	/**
	 * 获取 所属线路。如果为null，则表示该项不作限制。否则采用lineQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 所属线路。如果为null，则表示该项不作限制。否则采用lineQueryMode指定的比较机制。默认为null。
	 */
	public Line getLine() {
		return this.line;
	}

	/**
	 * 设置 所属线路。如果为null，则表示该项不作限制。否则采用lineQueryMode指定的比较机制。默认为null。
	 * 
	 * @param line
	 *            所属线路。如果为null，则表示该项不作限制。否则采用lineQueryMode指定的比较机制。默认为null。
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * 获取 所属线路的比较机制
	 * 
	 * @return 所属线路的比较机制
	 */
	public ObjectQueryMode getLineQueryMode() {
		return this.lineQueryMode;
	}

	/**
	 * 设置 所属线路的比较机制
	 * 
	 * @param lineQueryMode
	 *            所属线路的比较机制
	 */
	public void setLineQueryMode(ObjectQueryMode lineQueryMode) {
		this.lineQueryMode = lineQueryMode;
	}

	/**
	 * 获取 杆塔类型。如果为null，则表示该项不作限制。否则采用towerTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 杆塔类型。如果为null，则表示该项不作限制。否则采用towerTypeQueryMode指定的比较机制。默认为null。
	 */
	public TowerType getTowerType() {
		return this.towerType;
	}

	/**
	 * 设置 杆塔类型。如果为null，则表示该项不作限制。否则采用towerTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerType
	 *            杆塔类型。如果为null，则表示该项不作限制。否则采用towerTypeQueryMode指定的比较机制。默认为null
	 *            。
	 */
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}

	/**
	 * 获取 杆塔类型的比较机制
	 * 
	 * @return 杆塔类型的比较机制
	 */
	public ObjectQueryMode getTowerTypeQueryMode() {
		return this.towerTypeQueryMode;
	}

	/**
	 * 设置 杆塔类型的比较机制
	 * 
	 * @param towerTypeQueryMode
	 *            杆塔类型的比较机制
	 */
	public void setTowerTypeQueryMode(ObjectQueryMode towerTypeQueryMode) {
		this.towerTypeQueryMode = towerTypeQueryMode;
	}

	/**
	 * 获取 杆塔编号。如果为null，则表示该项不作限制。否则采用towerCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 杆塔编号。如果为null，则表示该项不作限制。否则采用towerCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getTowerCode() {
		return this.towerCode;
	}

	/**
	 * 设置 杆塔编号。如果为null，则表示该项不作限制。否则采用towerCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerCode
	 *            杆塔编号。如果为null，则表示该项不作限制。否则采用towerCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setTowerCode(String towerCode) {
		this.towerCode = towerCode;
	}

	/**
	 * 获取 杆塔编号的比较机制
	 * 
	 * @return 杆塔编号的比较机制
	 */
	public StringQueryMode getTowerCodeQueryMode() {
		return this.towerCodeQueryMode;
	}

	/**
	 * 设置 杆塔编号的比较机制
	 * 
	 * @param towerCodeQueryMode
	 *            杆塔编号的比较机制
	 */
	public void setTowerCodeQueryMode(StringQueryMode towerCodeQueryMode) {
		this.towerCodeQueryMode = towerCodeQueryMode;
	}

	/**
	 * 获取 杆塔高度。如果为null，则表示该项不作限制。否则采用towerHeightQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 杆塔高度。如果为null，则表示该项不作限制。否则采用towerHeightQueryMode指定的比较机制。默认为null。
	 */
	public Double getTowerHeight() {
		return this.towerHeight;
	}

	/**
	 * 设置 杆塔高度。如果为null，则表示该项不作限制。否则采用towerHeightQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerHeight
	 *            杆塔高度。如果为null，则表示该项不作限制。否则采用towerHeightQueryMode指定的比较机制。默认为null
	 *            。
	 */
	public void setTowerHeight(Double towerHeight) {
		this.towerHeight = towerHeight;
	}

	/**
	 * 获取 杆塔高度的比较机制
	 * 
	 * @return 杆塔高度的比较机制
	 */
	public NumberQueryMode getTowerHeightQueryMode() {
		return this.towerHeightQueryMode;
	}

	/**
	 * 设置 杆塔高度的比较机制
	 * 
	 * @param towerHeightQueryMode
	 *            杆塔高度的比较机制
	 */
	public void setTowerHeightQueryMode(NumberQueryMode towerHeightQueryMode) {
		this.towerHeightQueryMode = towerHeightQueryMode;
	}

	/**
	 * 获取 经度。如果为null，则表示该项不作限制。否则采用longitudeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 经度。如果为null，则表示该项不作限制。否则采用longitudeQueryMode指定的比较机制。默认为null。
	 */
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * 设置 经度。如果为null，则表示该项不作限制。否则采用longitudeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param longitude
	 *            经度。如果为null，则表示该项不作限制。否则采用longitudeQueryMode指定的比较机制。默认为null。
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取 经度的比较机制
	 * 
	 * @return 经度的比较机制
	 */
	public NumberQueryMode getLongitudeQueryMode() {
		return this.longitudeQueryMode;
	}

	/**
	 * 设置 经度的比较机制
	 * 
	 * @param longitudeQueryMode
	 *            经度的比较机制
	 */
	public void setLongitudeQueryMode(NumberQueryMode longitudeQueryMode) {
		this.longitudeQueryMode = longitudeQueryMode;
	}

	/**
	 * 获取 纬度。如果为null，则表示该项不作限制。否则采用latitudeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 纬度。如果为null，则表示该项不作限制。否则采用latitudeQueryMode指定的比较机制。默认为null。
	 */
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * 设置 纬度。如果为null，则表示该项不作限制。否则采用latitudeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param latitude
	 *            纬度。如果为null，则表示该项不作限制。否则采用latitudeQueryMode指定的比较机制。默认为null。
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取 纬度的比较机制
	 * 
	 * @return 纬度的比较机制
	 */
	public NumberQueryMode getLatitudeQueryMode() {
		return this.latitudeQueryMode;
	}

	/**
	 * 设置 纬度的比较机制
	 * 
	 * @param latitudeQueryMode
	 *            纬度的比较机制
	 */
	public void setLatitudeQueryMode(NumberQueryMode latitudeQueryMode) {
		this.latitudeQueryMode = latitudeQueryMode;
	}

	/**
	 * 获取 所在地址。如果为null，则表示该项不作限制。否则采用addressQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 所在地址。如果为null，则表示该项不作限制。否则采用addressQueryMode指定的比较机制。默认为null。
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * 设置 所在地址。如果为null，则表示该项不作限制。否则采用addressQueryMode指定的比较机制。默认为null。
	 * 
	 * @param address
	 *            所在地址。如果为null，则表示该项不作限制。否则采用addressQueryMode指定的比较机制。默认为null。
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取 所在地址的比较机制
	 * 
	 * @return 所在地址的比较机制
	 */
	public StringQueryMode getAddressQueryMode() {
		return this.addressQueryMode;
	}

	/**
	 * 设置 所在地址的比较机制
	 * 
	 * @param addressQueryMode
	 *            所在地址的比较机制
	 */
	public void setAddressQueryMode(StringQueryMode addressQueryMode) {
		this.addressQueryMode = addressQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用towerDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用towerDescQueryMode指定的比较机制。默认为null。
	 */
	public String getTowerDesc() {
		return this.towerDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用towerDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用towerDescQueryMode指定的比较机制。默认为null。
	 */
	public void setTowerDesc(String towerDesc) {
		this.towerDesc = towerDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getTowerDescQueryMode() {
		return this.towerDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param towerDescQueryMode
	 *            描述的比较机制
	 */
	public void setTowerDescQueryMode(StringQueryMode towerDescQueryMode) {
		this.towerDescQueryMode = towerDescQueryMode;
	}
}
