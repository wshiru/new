/*
 * Project platform
 *
 * Class CmaInfoQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午09:51:07
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.util.Date;

import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 监测代理的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaInfoQueryModel {

	/** 监测代理编码。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。 */
	private String cmaCode;

	/** 监测代理编码的比较机制 */
	private StringQueryMode cmaCodeQueryMode;

	/** 监测代理名称。如果为null，则表示该项不作限制。否则采用cmaNameQueryMode指定的比较机制。默认为null。 */
	private String cmaName;

	/** 监测代理名称的比较机制 */
	private StringQueryMode cmaNameQueryMode;

	/** 型号。如果为null，则表示该项不作限制。否则采用cmaModelQueryMode指定的比较机制。默认为null。 */
	private String cmaModel;

	/** 型号的比较机制 */
	private StringQueryMode cmaModelQueryMode;

	/** 安装日期。如果为null，则表示该项不作限制。否则采用installDateQueryMode指定的比较机制。默认为null。 */
	private Date installDate;

	/**
	 * 安装日期的比较机制。<b>注意：</b>当使用 <code>DateQueryMode.BETWEEN</code> 或
	 * <code>DateQueryMode.NOTBETWEEN</code> 时，需要设置开始时间与结束时间
	 */
	private DateQueryMode installDateQueryMode;

	/** 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。 */
	private Integer state;

	/** 状态的比较机制 */
	private NumberQueryMode stateQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用cmaDescQueryMode指定的比较机制。默认为null。 */
	private String cmaDesc;

	/** 描述的比较机制 */
	private StringQueryMode cmaDescQueryMode;

	/**
	 * 获取 监测代理编码。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测代理编码。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getCmaCode() {
		return this.cmaCode;
	}

	/**
	 * 设置 监测代理编码。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaCode
	 *            监测代理编码。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setCmaCode(String cmaCode) {
		this.cmaCode = cmaCode;
	}

	/**
	 * 获取 监测代理编码的比较机制
	 * 
	 * @return 监测代理编码的比较机制
	 */
	public StringQueryMode getCmaCodeQueryMode() {
		return this.cmaCodeQueryMode;
	}

	/**
	 * 设置 监测代理编码的比较机制
	 * 
	 * @param cmaCodeQueryMode
	 *            监测代理编码的比较机制
	 */
	public void setCmaCodeQueryMode(StringQueryMode cmaCodeQueryMode) {
		this.cmaCodeQueryMode = cmaCodeQueryMode;
	}

	/**
	 * 获取 监测代理名称。如果为null，则表示该项不作限制。否则采用cmaNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测代理名称。如果为null，则表示该项不作限制。否则采用cmaNameQueryMode指定的比较机制。默认为null。
	 */
	public String getCmaName() {
		return this.cmaName;
	}

	/**
	 * 设置 监测代理名称。如果为null，则表示该项不作限制。否则采用cmaNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaName
	 *            监测代理名称。如果为null，则表示该项不作限制。否则采用cmaNameQueryMode指定的比较机制。默认为null。
	 */
	public void setCmaName(String cmaName) {
		this.cmaName = cmaName;
	}

	/**
	 * 获取 监测代理名称的比较机制
	 * 
	 * @return 监测代理名称的比较机制
	 */
	public StringQueryMode getCmaNameQueryMode() {
		return this.cmaNameQueryMode;
	}

	/**
	 * 设置 监测代理名称的比较机制
	 * 
	 * @param cmaNameQueryMode
	 *            监测代理名称的比较机制
	 */
	public void setCmaNameQueryMode(StringQueryMode cmaNameQueryMode) {
		this.cmaNameQueryMode = cmaNameQueryMode;
	}

	/**
	 * 获取 型号。如果为null，则表示该项不作限制。否则采用cmaModelQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 型号。如果为null，则表示该项不作限制。否则采用cmaModelQueryMode指定的比较机制。默认为null。
	 */
	public String getCmaModel() {
		return this.cmaModel;
	}

	/**
	 * 设置 型号。如果为null，则表示该项不作限制。否则采用cmaModelQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaModel
	 *            型号。如果为null，则表示该项不作限制。否则采用cmaModelQueryMode指定的比较机制。默认为null。
	 */
	public void setCmaModel(String cmaModel) {
		this.cmaModel = cmaModel;
	}

	/**
	 * 获取 型号的比较机制
	 * 
	 * @return 型号的比较机制
	 */
	public StringQueryMode getCmaModelQueryMode() {
		return this.cmaModelQueryMode;
	}

	/**
	 * 设置 型号的比较机制
	 * 
	 * @param cmaModelQueryMode
	 *            型号的比较机制
	 */
	public void setCmaModelQueryMode(StringQueryMode cmaModelQueryMode) {
		this.cmaModelQueryMode = cmaModelQueryMode;
	}

	/**
	 * 获取 安装日期。如果为null，则表示该项不作限制。否则采用installDateQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 安装日期。如果为null，则表示该项不作限制。否则采用installDateQueryMode指定的比较机制。默认为null。
	 */
	public Date getInstallDate() {
		return this.installDate;
	}

	/**
	 * 设置 安装日期。如果为null，则表示该项不作限制。否则采用installDateQueryMode指定的比较机制。默认为null。
	 * 
	 * @param installDate
	 *            安装日期。如果为null，则表示该项不作限制。否则采用installDateQueryMode指定的比较机制。默认为null
	 *            。
	 */
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	/**
	 * 获取 安装日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @return 安装日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 *         时，需要设置开始时间与结束时间
	 */
	public DateQueryMode getInstallDateQueryMode() {
		return this.installDateQueryMode;
	}

	/**
	 * 设置 安装日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @param installDateQueryMode
	 *            安装日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或
	 *            DateQueryMode.NOTBETWEEN 时，需要设置开始时间与结束时间
	 */
	public void setInstallDateQueryMode(DateQueryMode installDateQueryMode) {
		this.installDateQueryMode = installDateQueryMode;
	}

	/**
	 * 获取 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 * 
	 * @param state
	 *            状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 状态的比较机制
	 * 
	 * @return 状态的比较机制
	 */
	public NumberQueryMode getStateQueryMode() {
		return this.stateQueryMode;
	}

	/**
	 * 设置 状态的比较机制
	 * 
	 * @param stateQueryMode
	 *            状态的比较机制
	 */
	public void setStateQueryMode(NumberQueryMode stateQueryMode) {
		this.stateQueryMode = stateQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用cmaDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用cmaDescQueryMode指定的比较机制。默认为null。
	 */
	public String getCmaDesc() {
		return this.cmaDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用cmaDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用cmaDescQueryMode指定的比较机制。默认为null。
	 */
	public void setCmaDesc(String cmaDesc) {
		this.cmaDesc = cmaDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getCmaDescQueryMode() {
		return this.cmaDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param cmaDescQueryMode
	 *            描述的比较机制
	 */
	public void setCmaDescQueryMode(StringQueryMode cmaDescQueryMode) {
		this.cmaDescQueryMode = cmaDescQueryMode;
	}
}
