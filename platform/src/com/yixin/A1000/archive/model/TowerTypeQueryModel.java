/*
 * Project platform
 *
 * Class TowerTypeQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午11:46:49
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 杆塔类型的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTypeQueryModel {

	/** 杆塔类型编号。如果为null，则表示该项不作限制。否则采用towerTypeCodeQueryMode指定的比较机制。默认为null。 */
	private String towerTypeCode;

	/** 杆塔类型编号的比较机制 */
	private StringQueryMode towerTypeCodeQueryMode;

	/** 杆塔类型名称。如果为null，则表示该项不作限制。否则采用towerTypeNameQueryMode指定的比较机制。默认为null。 */
	private String towerTypeName;

	/** 杆塔类型名称的比较机制 */
	private StringQueryMode towerTypeNameQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用towerTypeDescQueryMode指定的比较机制。默认为null。 */
	private String towerTypeDesc;

	/** 描述的比较机制 */
	private StringQueryMode towerTypeDescQueryMode;

	/**
	 * 获取 杆塔类型编号。如果为null，则表示该项不作限制。否则采用towerTypeCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 
	 *         杆塔类型编号。如果为null，则表示该项不作限制。否则采用towerTypeCodeQueryMode指定的比较机制。默认为null
	 *         。
	 */
	public String getTowerTypeCode() {
		return this.towerTypeCode;
	}

	/**
	 * 设置 杆塔类型编号。如果为null，则表示该项不作限制。否则采用towerTypeCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerTypeCode
	 *            杆塔类型编号。如果为null，则表示该项不作限制。否则采用towerTypeCodeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setTowerTypeCode(String towerTypeCode) {
		this.towerTypeCode = towerTypeCode;
	}

	/**
	 * 获取 杆塔类型编号的比较机制
	 * 
	 * @return 杆塔类型编号的比较机制
	 */
	public StringQueryMode getTowerTypeCodeQueryMode() {
		return this.towerTypeCodeQueryMode;
	}

	/**
	 * 设置 杆塔类型编号的比较机制
	 * 
	 * @param towerTypeCodeQueryMode
	 *            杆塔类型编号的比较机制
	 */
	public void setTowerTypeCodeQueryMode(StringQueryMode towerTypeCodeQueryMode) {
		this.towerTypeCodeQueryMode = towerTypeCodeQueryMode;
	}

	/**
	 * 获取 杆塔类型名称。如果为null，则表示该项不作限制。否则采用towerTypeNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 
	 *         杆塔类型名称。如果为null，则表示该项不作限制。否则采用towerTypeNameQueryMode指定的比较机制。默认为null
	 *         。
	 */
	public String getTowerTypeName() {
		return this.towerTypeName;
	}

	/**
	 * 设置 杆塔类型名称。如果为null，则表示该项不作限制。否则采用towerTypeNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerTypeName
	 *            杆塔类型名称。如果为null，则表示该项不作限制。否则采用towerTypeNameQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setTowerTypeName(String towerTypeName) {
		this.towerTypeName = towerTypeName;
	}

	/**
	 * 获取 杆塔类型名称的比较机制
	 * 
	 * @return 杆塔类型名称的比较机制
	 */
	public StringQueryMode getTowerTypeNameQueryMode() {
		return this.towerTypeNameQueryMode;
	}

	/**
	 * 设置 杆塔类型名称的比较机制
	 * 
	 * @param towerTypeNameQueryMode
	 *            杆塔类型名称的比较机制
	 */
	public void setTowerTypeNameQueryMode(StringQueryMode towerTypeNameQueryMode) {
		this.towerTypeNameQueryMode = towerTypeNameQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用towerTypeDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用towerTypeDescQueryMode指定的比较机制。默认为null。
	 */
	public String getTowerTypeDesc() {
		return this.towerTypeDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用towerTypeDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param towerTypeDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用towerTypeDescQueryMode指定的比较机制。默认为null
	 *            。
	 */
	public void setTowerTypeDesc(String towerTypeDesc) {
		this.towerTypeDesc = towerTypeDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getTowerTypeDescQueryMode() {
		return this.towerTypeDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param towerTypeDescQueryMode
	 *            描述的比较机制
	 */
	public void setTowerTypeDescQueryMode(StringQueryMode towerTypeDescQueryMode) {
		this.towerTypeDescQueryMode = towerTypeDescQueryMode;
	}
}
