/*
 * Project platform
 *
 * Class LineQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午07:58:59
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
import com.yixin.framework.system.model.Dictionary;

/**
 * 线路的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class LineQueryModel {

	/** 线路编号。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。 */
	private String lineCode;

	/** 线路编号的比较机制 */
	private StringQueryMode lineCodeQueryMode;

	/** 线路名称。如果为null，则表示该项不作限制。否则采用lineNameQueryMode指定的比较机制。默认为null。 */
	private String lineName;

	/** 线路名称的比较机制 */
	private StringQueryMode lineNameQueryMode;

	/** 电压等级。如果为null，则表示该项不作限制。否则采用voltageLevelQueryMode指定的比较机制。默认为null。 */
	private Dictionary voltageLevel;

	/** 电压等级的比较机制 */
	private ObjectQueryMode voltageLevelQueryMode;

	/** 起点。如果为null，则表示该项不作限制。否则采用lineStartQueryMode指定的比较机制。默认为null。 */
	private String lineStart;

	/** 起点的比较机制 */
	private StringQueryMode lineStartQueryMode;

	/** 终点。如果为null，则表示该项不作限制。否则采用lineEndQueryMode指定的比较机制。默认为null。 */
	private String lineEnd;

	/** 终点的比较机制 */
	private StringQueryMode lineEndQueryMode;

	/** 长度。如果为null，则表示该项不作限制。否则采用lengthQueryMode指定的比较机制。默认为null。 */
	private Double length;

	/** 长度的比较机制 */
	private NumberQueryMode lengthQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用lineDescQueryMode指定的比较机制。默认为null。 */
	private String lineDesc;

	/** 描述的比较机制 */
	private StringQueryMode lineDescQueryMode;

	/**
	 * 获取 线路编号。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 线路编号。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getLineCode() {
		return this.lineCode;
	}

	/**
	 * 设置 线路编号。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param lineCode
	 *            线路编号。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	/**
	 * 获取 线路编号的比较机制
	 * 
	 * @return 线路编号的比较机制
	 */
	public StringQueryMode getLineCodeQueryMode() {
		return this.lineCodeQueryMode;
	}

	/**
	 * 设置 线路编号的比较机制
	 * 
	 * @param lineCodeQueryMode
	 *            线路编号的比较机制
	 */
	public void setLineCodeQueryMode(StringQueryMode lineCodeQueryMode) {
		this.lineCodeQueryMode = lineCodeQueryMode;
	}

	/**
	 * 获取 线路名称。如果为null，则表示该项不作限制。否则采用lineNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 线路名称。如果为null，则表示该项不作限制。否则采用lineNameQueryMode指定的比较机制。默认为null。
	 */
	public String getLineName() {
		return this.lineName;
	}

	/**
	 * 设置 线路名称。如果为null，则表示该项不作限制。否则采用lineNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @param lineName
	 *            线路名称。如果为null，则表示该项不作限制。否则采用lineNameQueryMode指定的比较机制。默认为null。
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * 获取 线路名称的比较机制
	 * 
	 * @return 线路名称的比较机制
	 */
	public StringQueryMode getLineNameQueryMode() {
		return this.lineNameQueryMode;
	}

	/**
	 * 设置 线路名称的比较机制
	 * 
	 * @param lineNameQueryMode
	 *            线路名称的比较机制
	 */
	public void setLineNameQueryMode(StringQueryMode lineNameQueryMode) {
		this.lineNameQueryMode = lineNameQueryMode;
	}

	/**
	 * 获取 电压等级。如果为null，则表示该项不作限制。否则采用voltageLevelQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 电压等级。如果为null，则表示该项不作限制。否则采用voltageLevelQueryMode指定的比较机制。默认为null。
	 */
	public Dictionary getVoltageLevel() {
		return this.voltageLevel;
	}

	/**
	 * 设置 电压等级。如果为null，则表示该项不作限制。否则采用voltageLevelQueryMode指定的比较机制。默认为null。
	 * 
	 * @param voltageLevel
	 *            电压等级。如果为null，则表示该项不作限制。否则采用voltageLevelQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setVoltageLevel(Dictionary voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	/**
	 * 获取 电压等级的比较机制
	 * 
	 * @return 电压等级的比较机制
	 */
	public ObjectQueryMode getVoltageLevelQueryMode() {
		return this.voltageLevelQueryMode;
	}

	/**
	 * 设置 电压等级的比较机制
	 * 
	 * @param voltageLevelQueryMode
	 *            电压等级的比较机制
	 */
	public void setVoltageLevelQueryMode(ObjectQueryMode voltageLevelQueryMode) {
		this.voltageLevelQueryMode = voltageLevelQueryMode;
	}

	/**
	 * 获取 起点。如果为null，则表示该项不作限制。否则采用lineStartQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 起点。如果为null，则表示该项不作限制。否则采用lineStartQueryMode指定的比较机制。默认为null。
	 */
	public String getLineStart() {
		return this.lineStart;
	}

	/**
	 * 设置 起点。如果为null，则表示该项不作限制。否则采用lineStartQueryMode指定的比较机制。默认为null。
	 * 
	 * @param lineStart
	 *            起点。如果为null，则表示该项不作限制。否则采用lineStartQueryMode指定的比较机制。默认为null。
	 */
	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	/**
	 * 获取 起点的比较机制
	 * 
	 * @return 起点的比较机制
	 */
	public StringQueryMode getLineStartQueryMode() {
		return this.lineStartQueryMode;
	}

	/**
	 * 设置 起点的比较机制
	 * 
	 * @param lineStartQueryMode
	 *            起点的比较机制
	 */
	public void setLineStartQueryMode(StringQueryMode lineStartQueryMode) {
		this.lineStartQueryMode = lineStartQueryMode;
	}

	/**
	 * 获取 终点。如果为null，则表示该项不作限制。否则采用lineEndQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 终点。如果为null，则表示该项不作限制。否则采用lineEndQueryMode指定的比较机制。默认为null。
	 */
	public String getLineEnd() {
		return this.lineEnd;
	}

	/**
	 * 设置 终点。如果为null，则表示该项不作限制。否则采用lineEndQueryMode指定的比较机制。默认为null。
	 * 
	 * @param lineEnd
	 *            终点。如果为null，则表示该项不作限制。否则采用lineEndQueryMode指定的比较机制。默认为null。
	 */
	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	/**
	 * 获取 终点的比较机制
	 * 
	 * @return 终点的比较机制
	 */
	public StringQueryMode getLineEndQueryMode() {
		return this.lineEndQueryMode;
	}

	/**
	 * 设置 终点的比较机制
	 * 
	 * @param lineEndQueryMode
	 *            终点的比较机制
	 */
	public void setLineEndQueryMode(StringQueryMode lineEndQueryMode) {
		this.lineEndQueryMode = lineEndQueryMode;
	}

	/**
	 * 获取 长度。如果为null，则表示该项不作限制。否则采用lengthQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 长度。如果为null，则表示该项不作限制。否则采用lengthQueryMode指定的比较机制。默认为null。
	 */
	public Double getLength() {
		return this.length;
	}

	/**
	 * 设置 长度。如果为null，则表示该项不作限制。否则采用lengthQueryMode指定的比较机制。默认为null。
	 * 
	 * @param length
	 *            长度。如果为null，则表示该项不作限制。否则采用lengthQueryMode指定的比较机制。默认为null。
	 */
	public void setLength(Double length) {
		this.length = length;
	}

	/**
	 * 获取 长度的比较机制
	 * 
	 * @return 长度的比较机制
	 */
	public NumberQueryMode getLengthQueryMode() {
		return this.lengthQueryMode;
	}

	/**
	 * 设置 长度的比较机制
	 * 
	 * @param lengthQueryMode
	 *            长度的比较机制
	 */
	public void setLengthQueryMode(NumberQueryMode lengthQueryMode) {
		this.lengthQueryMode = lengthQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用lineDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用lineDescQueryMode指定的比较机制。默认为null。
	 */
	public String getLineDesc() {
		return this.lineDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用lineDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param lineDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用lineDescQueryMode指定的比较机制。默认为null。
	 */
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getLineDescQueryMode() {
		return this.lineDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param lineDescQueryMode
	 *            描述的比较机制
	 */
	public void setLineDescQueryMode(StringQueryMode lineDescQueryMode) {
		this.lineDescQueryMode = lineDescQueryMode;
	}
}
