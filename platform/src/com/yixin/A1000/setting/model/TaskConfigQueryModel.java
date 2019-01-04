/*
 * Project platform
 *
 * Class TaskConfigQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-5 下午02:36:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.model;

import java.util.Date;

import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.system.model.User;

/**
 * 任务的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class TaskConfigQueryModel {

	/** 操作用户。如果为null，则表示该项不作限制。否则采用userMode指定的比较机制。默认为null。 */
	private User user;

	/** 操作用户的比较机制 */
	private ObjectQueryMode userQueryMode;

	/** 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。 */
	private String sensorCode;

	/** 监测装置编码的比较机制 */
	private StringQueryMode sensorCodeQueryMode;

	/** 监测代理编码。如果为null，则表示该项不作限制。否则采用cmaCodeQueryMode指定的比较机制。默认为null。 */
	private String cmaCode;

	/** 监测代理编码的比较机制 */
	private StringQueryMode cmaCodeQueryMode;

	/** 创建时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。 */
	private Date createTime;

	/**
	 * 创建时间的比较机制。<b>注意：</b>当使用 <code>DateQueryMode.BETWEEN</code> 或
	 * <code>DateQueryMode.NOTBETWEEN</code> 时，需要设置开始时间与结束时间
	 */
	private DateQueryMode createTimeQueryMode;

	/** 执行时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。 */
	private Date executeTime;

	/**
	 * 执行时间的比较机制。<b>注意：</b>当使用 <code>DateQueryMode.BETWEEN</code> 或
	 * <code>DateQueryMode.NOTBETWEEN</code> 时，需要设置开始时间与结束时间
	 */
	private DateQueryMode executeTimeQueryMode;

	/** 命令类型。如果为null，则表示该项不作限制。否则采用cmdTypeQueryMode指定的比较机制。默认为null。 */
	private String cmdType;

	/** 命令类型的比较机制 */
	private StringQueryMode cmdTypeQueryMode;

	/** 命令子类型。如果为null，则表示该项不作限制。否则采用subCmdTypeQueryMode指定的比较机制。默认为null。 */
	private String subCmdType;

	/** 命令子类型的比较机制 */
	private StringQueryMode subCmdTypeQueryMode;

	/** 任务状态 0：未下发 1：已下发 2 ：已取消 3：已执行成功 **/
	private Integer state;

	/** 任务状态 的比较机制 */
	private NumberQueryMode stateQueryMode;

	/**
	 * 获取 操作用户。如果为null，则表示该项不作限制。否则采用userMode指定的比较机制。默认为null。
	 * 
	 * @return 操作用户。如果为null，则表示该项不作限制。否则采用userMode指定的比较机制。默认为null。
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 设置 操作用户。如果为null，则表示该项不作限制。否则采用userMode指定的比较机制。默认为null。
	 * 
	 * @param user
	 *            操作用户。如果为null，则表示该项不作限制。否则采用userMode指定的比较机制。默认为null。
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取 操作用户的比较机制
	 * 
	 * @return 操作用户的比较机制
	 */
	public ObjectQueryMode getUserQueryMode() {
		return this.userQueryMode;
	}

	/**
	 * 设置 操作用户的比较机制
	 * 
	 * @param usereQueryMode
	 *            操作用户的比较机制
	 */
	public void setUserQueryMode(ObjectQueryMode userQueryMode) {
		this.userQueryMode = userQueryMode;
	}

	/**
	 * 获取 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getSensorCode() {
		return this.sensorCode;
	}

	/**
	 * 设置 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorCode
	 *            监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}

	/**
	 * 获取 监测装置编码的比较机制
	 * 
	 * @return 监测装置编码的比较机制
	 */
	public StringQueryMode getSensorCodeQueryMode() {
		return this.sensorCodeQueryMode;
	}

	/**
	 * 设置 监测装置编码的比较机制
	 * 
	 * @param sensorCodeQueryMode
	 *            监测装置编码的比较机制
	 */
	public void setSensorCodeQueryMode(StringQueryMode sensorCodeQueryMode) {
		this.sensorCodeQueryMode = sensorCodeQueryMode;
	}

	/**
	 * 获取 监测代理编码。如果为null，则表示该项不作限制。否则采用cmaCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测代理编码。如果为null，则表示该项不作限制。否则采用cmaCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getCmaCode() {
		return this.cmaCode;
	}

	/**
	 * 设置 监测代理编码。如果为null，则表示该项不作限制。否则采用cmaCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaCode
	 *            监测代理编码。如果为null，则表示该项不作限制。否则采用cmaCodeQueryMode指定的比较机制。默认为null。
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
	 * 获取 创建时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 创建时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param createTime
	 *            创建时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 创建时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @return 创建时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 *         时，需要设置开始时间与结束时间
	 */
	public DateQueryMode getCreateTimeQueryMode() {
		return this.createTimeQueryMode;
	}

	/**
	 * 设置 创建时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @param createTimeQueryMode
	 *            创建时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或
	 *            DateQueryMode.NOTBETWEEN 时，需要设置开始时间与结束时间
	 */
	public void setCreateTimeQueryMode(DateQueryMode createTimeQueryMode) {
		this.createTimeQueryMode = createTimeQueryMode;
	}

	/**
	 * 获取 执行时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 执行时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public Date getExecuteTime() {
		return this.executeTime;
	}

	/**
	 * 设置 执行时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param executeTime
	 *            执行时间。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * 获取 执行时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @return 执行时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 *         时，需要设置开始时间与结束时间
	 */
	public DateQueryMode getExecuteTimeQueryMode() {
		return this.executeTimeQueryMode;
	}

	/**
	 * 设置 执行时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @param executeTimeQueryMode
	 *            执行时间的比较机制。注意：当使用 DateQueryMode.BETWEEN 或
	 *            DateQueryMode.NOTBETWEEN 时，需要设置开始时间与结束时间
	 */
	public void setExecuteTimeQueryMode(DateQueryMode executeTimeQueryMode) {
		this.executeTimeQueryMode = executeTimeQueryMode;
	}

	/**
	 * 获取 命令类型。如果为null，则表示该项不作限制。否则采用cmdTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 命令类型。如果为null，则表示该项不作限制。否则采用cmdTypeQueryMode指定的比较机制。默认为null。
	 */
	public String getCmdType() {
		return this.cmdType;
	}

	/**
	 * 设置 命令类型。如果为null，则表示该项不作限制。否则采用cmdTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmdType
	 *            命令类型。如果为null，则表示该项不作限制。否则采用cmdTypeQueryMode指定的比较机制。默认为null。
	 */
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	/**
	 * 获取 命令类型的比较机制
	 * 
	 * @return 命令类型的比较机制
	 */
	public StringQueryMode getCmdTypeQueryMode() {
		return this.cmdTypeQueryMode;
	}

	/**
	 * 设置 命令类型的比较机制
	 * 
	 * @param cmdTypeQueryMode
	 *            命令类型的比较机制
	 */
	public void setCmdTypeQueryMode(StringQueryMode cmdTypeQueryMode) {
		this.cmdTypeQueryMode = cmdTypeQueryMode;
	}

	/**
	 * 获取 命令子类型。如果为null，则表示该项不作限制。否则采用subCmdTypeQueryMode指定的比较机制。默认为null
	 * 
	 * @return 命令子类型。如果为null，则表示该项不作限制。否则采用subCmdTypeQueryMode指定的比较机制。默认为null
	 */
	public String getSubCmdType() {
		return this.subCmdType;
	}

	/**
	 * 设置 命令子类型。如果为null，则表示该项不作限制。否则采用subCmdTypeQueryMode指定的比较机制。默认为null
	 * 
	 * @param subCmdType
	 *            命令子类型。如果为null，则表示该项不作限制。否则采用subCmdTypeQueryMode指定的比较机制。默认为null
	 */
	public void setSubCmdType(String subCmdType) {
		this.subCmdType = subCmdType;
	}

	/**
	 * 获取 命令子类型的比较机制
	 * 
	 * @return 命令子类型的比较机制
	 */
	public StringQueryMode getSubCmdTypeQueryMode() {
		return this.subCmdTypeQueryMode;
	}

	/**
	 * 设置 命令子类型的比较机制
	 * 
	 * @param subCmdTypeQueryMode
	 *            命令子类型的比较机制
	 */
	public void setSubCmdTypeQueryMode(StringQueryMode subCmdTypeQueryMode) {
		this.subCmdTypeQueryMode = subCmdTypeQueryMode;
	}

	/**
	 * 获取 任务状态 0：未下发 1：已下发 2 ：已取消 3：已执行成功
	 * 
	 * @return 任务状态 0：未下发 1：已下发 2 ：已取消 3：已执行成功
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 任务状态 0：未下发 1：已下发 2 ：已取消 3：已执行成功
	 * 
	 * @param state
	 *            任务状态 0：未下发 1：已下发 2 ：已取消 3：已执行成功
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 任务状态 的比较机制
	 * 
	 * @return 任务状态 的比较机制
	 */
	public NumberQueryMode getStateQueryMode() {
		return this.stateQueryMode;
	}

	/**
	 * 设置 任务状态 的比较机制
	 * 
	 * @param stateQueryMode
	 *            任务状态 的比较机制
	 */
	public void setStateQueryMode(NumberQueryMode stateQueryMode) {
		this.stateQueryMode = stateQueryMode;
	}
}
