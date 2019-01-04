/*
 * Project platform
 *
 * Class TowerType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:18:49
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 杆塔类型
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_TowerType")
public class TowerType implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -8062795784308305779L;

	/** 杆塔类型Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String towerTypeId;

	/** 杆塔类型编号 */
	private String towerTypeCode;

	/** 杆塔类型名称 */
	private String towerTypeName;

	/** 描述 */
	private String towerTypeDesc;

	/**
	 * 获取 杆塔类型Id
	 * 
	 * @return 杆塔类型Id
	 */
	public String getTowerTypeId() {
		return this.towerTypeId;
	}

	/**
	 * 设置 杆塔类型Id
	 * 
	 * @param towerTypeId
	 *            杆塔类型Id
	 */
	public void setTowerTypeId(String towerTypeId) {
		this.towerTypeId = towerTypeId;
	}

	/**
	 * 获取 杆塔类型编号
	 * 
	 * @return 杆塔类型编号
	 */
	public String getTowerTypeCode() {
		return this.towerTypeCode;
	}

	/**
	 * 设置 杆塔类型编号
	 * 
	 * @param towerTypeCode
	 *            杆塔类型编号
	 */
	public void setTowerTypeCode(String towerTypeCode) {
		this.towerTypeCode = towerTypeCode;
	}

	/**
	 * 获取 杆塔类型名称
	 * 
	 * @return 杆塔类型名称
	 */
	public String getTowerTypeName() {
		return this.towerTypeName;
	}

	/**
	 * 设置 杆塔类型名称
	 * 
	 * @param towerTypeName
	 *            杆塔类型名称
	 */
	public void setTowerTypeName(String towerTypeName) {
		this.towerTypeName = towerTypeName;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getTowerTypeDesc() {
		return this.towerTypeDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param towerTypeDesc
	 *            描述
	 */
	public void setTowerTypeDesc(String towerTypeDesc) {
		this.towerTypeDesc = towerTypeDesc;
	}
}
