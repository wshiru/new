/*
 * Project platform
 *
 * Classname Log.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-9 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.framework.system.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作日志类
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_OperationLog")
public class Log implements Serializable{
	
	private static final long serialVersionUID = -6733026215159004972L;
	
	/** 操作日志ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="operationLogId")
	private String logId;
	/** 操作时间  */
	private Date operateTime;
	/** 日志类型  */
	private String operateType;
	/** 内容描述  */
	private String remark;
	/** 操作IP */
	private String ipAddress;
	
	/** 操作用户 */
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="userId")
	private User user;
	
	/**
	 * 无参构造函数
	 */
	public Log(){}
	/**
	 * 带参构造函数
	 * @param operateTime
	 * @param operateType
	 * @param user
	 * @param remark
	 * @param ipAddress
	 */
	public Log(Date operateTime, String operateType, User user, String remark, String ipAddress){
		this.operateTime = operateTime;
		this.operateType = operateType;
		this.user = user;
		this.remark = remark;
		this.ipAddress = ipAddress;
	}
	
	/**
	 * 获取日志Id
	 * @return
	 */
	public String getLogId() {
		return logId;
	}

	/**
	 * 设置日志Id
	 * @param logId
	 */
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	/**
	 * 获取操作用户
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置操作用户
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取操作时间
	 * @return
	 */
	public Date getOperateTime() {
		return operateTime;
	}

	/**
	 * 设置操作时间
	 * @param operateTime
	 */
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * 获取操作类型
	 * @return
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置操作类型
	 * @param operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取日志内容
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置日志内容
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取IP地址
	 * @return
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 设置IP地址
	 * @param ipAddress
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
