/*
 * Project platform
 *
 * Class Line.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:05:30
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

import com.yixin.framework.system.model.Dictionary;

/**
 * 线路
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_Line")
public class Line implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4692740108697085947L;

	/** 线路Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String lineId;

	/** 线路编号 */
	private String lineCode;

	/** 线路名称 */
	private String lineName;

	/** 电压等级 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "voltageLevel")
	private Dictionary voltageLevel = new Dictionary();

	/** 起点 */
	private String lineStart;

	/** 终点 */
	private String lineEnd;

	/** 长度 */
	private Double length;

	/** 描述 */
	private String lineDesc;

	/**
	 * 获取 线路Id
	 * 
	 * @return 线路Id
	 */
	public String getLineId() {
		return this.lineId;
	}

	/**
	 * 设置 线路Id
	 * 
	 * @param lineId
	 *            线路Id
	 */
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	/**
	 * 获取 线路编号
	 * 
	 * @return 线路编号
	 */
	public String getLineCode() {
		return this.lineCode;
	}

	/**
	 * 设置 线路编号
	 * 
	 * @param lineCode
	 *            线路编号
	 */
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	/**
	 * 获取 线路名称
	 * 
	 * @return 线路名称
	 */
	public String getLineName() {
		return this.lineName;
	}

	/**
	 * 设置 线路名称
	 * 
	 * @param lineName
	 *            线路名称
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * 获取 电压等级
	 * 
	 * @return 电压等级
	 */
	public Dictionary getVoltageLevel() {
		return this.voltageLevel;
	}

	/**
	 * 设置 电压等级
	 * 
	 * @param voltageLevel
	 *            电压等级
	 */
	public void setVoltageLevel(Dictionary voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	/**
	 * 获取 起点
	 * 
	 * @return 起点
	 */
	public String getLineStart() {
		return this.lineStart;
	}

	/**
	 * 设置 起点
	 * 
	 * @param lineStart
	 *            起点
	 */
	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	/**
	 * 获取 终点
	 * 
	 * @return 终点
	 */
	public String getLineEnd() {
		return this.lineEnd;
	}

	/**
	 * 设置 终点
	 * 
	 * @param lineEnd
	 *            终点
	 */
	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	/**
	 * 获取 长度
	 * 
	 * @return 长度
	 */
	public Double getLength() {
		return this.length;
	}

	/**
	 * 设置 长度
	 * 
	 * @param length
	 *            长度
	 */
	public void setLength(Double length) {
		this.length = length;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getLineDesc() {
		return this.lineDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param lineDesc
	 *            描述
	 */
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
}