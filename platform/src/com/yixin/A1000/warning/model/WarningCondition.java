/*
 * Project platform
 *
 * Classname WarningCondition.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-06 9:33
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.model;

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
 * 告警条件实体类
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name="T_WarningCondition")
public class WarningCondition {

	/** 告警条件ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")	
	private String warningConditionId;
	/** 告警类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "warningDictId")
	private WarningDict warningDict;
	/** 字段名称 */
	private String fieldName;
	/** 比较类型 */
	private Integer compare; //0等于，1大于，2小于，3,大于等于，4小于等于
	/** 阀值 */
	private Double threshold; 
	/** 告警显示格式 */
	private String displayFormat;

	/**
	 * 判断某值是否符合此条件
	 * @param value
	 * 			值
	 * @return
	 */
	public boolean fit(Double value) {
		if(null == value){
			return false;
		}	
		switch (compare) {
			case 0: //等于
				return value.equals(threshold);	
			case 1: //大于
				return value.compareTo(threshold) > 0;
			case 2: //小于
				return value.compareTo(threshold) < 0;
			case 3: //大于等于
				return value.compareTo(threshold) >= 0;
			case 4: //小于等于
				return value.compareTo(threshold) <= 0;
			default:
				return false;
		}
	}
	
	/**
	 * 获取告警条件ID
	 * @return
	 */
	public String getWarningConditionId() {
		return warningConditionId;
	}
	/**
	 * 设置告警条件ID
	 * @return
	 */
	public void setWarningConditionId(String warningConditionId) {
		this.warningConditionId = warningConditionId;
	}
	/**
	 * 获取告警类型
	 * @return
	 */
	public WarningDict getWarningDict() {
		return warningDict;
	}
	/**
	 * 设置告警类型
	 * @return
	 */
	public void setWarningDict(WarningDict warningDict) {
		this.warningDict = warningDict;
	}
	/**
	 * 获取字段名
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * 设置字段名
	 * @return
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * 获取比较类型(0等于，1大于，2小于，3,大于等于，4小于等于)
	 * @return
	 */
	public Integer getCompare() {
		return compare;
	}
	/**
	 * 设置比较类型(0等于，1大于，2小于，3,大于等于，4小于等于)
	 * @return
	 */
	public void setCompare(Integer compare) {
		this.compare = compare;
	}
	/**
	 * 获取阀值
	 * @return
	 */
	public Double getThreshold() {
		return threshold;
	}
	/**
	 * 设置阀值
	 * @return
	 */
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}
	/**
	 * 获取显示格式
	 * @return
	 */
	public String getDisplayFormat() {
		return displayFormat;
	}
	/**
	 * 设置显示格式
	 * @return
	 */
	public void setDisplayFormat(String displayFormat) {
		this.displayFormat = displayFormat;
	}
	
	
}
