/*
 * Project platform
 *
 * Class SensorTypeQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午11:34:55
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
 * 监测类型的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorTypeQueryModel {

	/** 监测类型编号。如果为null，则表示该项不作限制。否则采用sensorTypeCodeQueryMode指定的比较机制。默认为null。 */
	private String sensorTypeCode;

	/** 监测类型编号的比较机制 */
	private StringQueryMode sensorTypeCodeQueryMode;

	/** 监测类型名称。如果为null，则表示该项不作限制。否则采用sensorTypeNameQueryMode指定的比较机制。默认为null。 */
	private String sensorTypeName;

	/** 监测类型名称的比较机制 */
	private StringQueryMode sensorTypeNameQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。 */
	private String sensorDesc;

	/** 描述的比较机制 */
	private StringQueryMode sensorDescQueryMode;

	/**
	 * 获取 监测类型编号。如果为null，则表示该项不作限制。否则采用sensorTypeCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 
	 *         监测类型编号。如果为null，则表示该项不作限制。否则采用sensorTypeCodeQueryMode指定的比较机制。默认为null
	 *         。
	 */
	public String getSensorTypeCode() {
		return this.sensorTypeCode;
	}

	/**
	 * 设置 监测类型编号。如果为null，则表示该项不作限制。否则采用sensorTypeCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorTypeCode
	 *            监测类型编号。如果为null，则表示该项不作限制。否则采用sensorTypeCodeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setSensorTypeCode(String sensorTypeCode) {
		this.sensorTypeCode = sensorTypeCode;
	}

	/**
	 * 获取 监测类型编号的比较机制
	 * 
	 * @return 监测类型编号的比较机制
	 */
	public StringQueryMode getSensorTypeCodeQueryMode() {
		return this.sensorTypeCodeQueryMode;
	}

	/**
	 * 设置 监测类型编号的比较机制
	 * 
	 * @param sensorTypeCodeQueryMode
	 *            监测类型编号的比较机制
	 */
	public void setSensorTypeCodeQueryMode(StringQueryMode sensorTypeCodeQueryMode) {
		this.sensorTypeCodeQueryMode = sensorTypeCodeQueryMode;
	}

	/**
	 * 获取 监测类型名称。如果为null，则表示该项不作限制。否则采用sensorTypeNameQueryMode指定的比较机制。默认为null
	 * 
	 * @return 
	 *         监测类型名称。如果为null，则表示该项不作限制。否则采用sensorTypeNameQueryMode指定的比较机制。默认为null
	 */
	public String getSensorTypeName() {
		return this.sensorTypeName;
	}

	/**
	 * 设置 监测类型名称。如果为null，则表示该项不作限制。否则采用sensorTypeNameQueryMode指定的比较机制。默认为null
	 * 
	 * @param sensorTypeName
	 *            监测类型名称。如果为null，则表示该项不作限制。否则采用sensorTypeNameQueryMode指定的比较机制。
	 *            默认为null
	 */
	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
	}

	/**
	 * 获取 监测类型名称的比较机制
	 * 
	 * @return 监测类型名称的比较机制
	 */
	public StringQueryMode getSensorTypeNameQueryMode() {
		return this.sensorTypeNameQueryMode;
	}

	/**
	 * 设置 监测类型名称的比较机制
	 * 
	 * @param sensorTypeNameQueryMode
	 *            监测类型名称的比较机制
	 */
	public void setSensorTypeNameQueryMode(StringQueryMode sensorTypeNameQueryMode) {
		this.sensorTypeNameQueryMode = sensorTypeNameQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 */
	public String getSensorDesc() {
		return this.sensorDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 */
	public void setSensorDesc(String sensorDesc) {
		this.sensorDesc = sensorDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getSensorDescQueryMode() {
		return this.sensorDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param sensorDescQueryMode
	 *            描述的比较机制
	 */
	public void setSensorDescQueryMode(StringQueryMode sensorDescQueryMode) {
		this.sensorDescQueryMode = sensorDescQueryMode;
	}
}
