/*
 * Project platform
 *
 * Classname Role.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-8 下午
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */

package com.yixin.A1000.setting.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.framework.system.model.User;


/**
 * 命令任务表

 * @author 梁立全

 *
 */

@Entity
@Table(name="T_TaskConfig")
public class TaskConfig {
	
	/** 命令任务信息ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String taskConfigId;

	/** 操作用户 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "userId")
	private User user = new User();

	/** 升级文件。当是升级任务时，不为null */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "upgradeFileId")
	private UpgradeFile upgradeFile;
	
	
	/** 参数ID **/
	private String deviceParameterId;
	
	
	public String getDeviceParameterId() {
		return deviceParameterId;
	}


	public void setDeviceParameterId(String deviceParameterId) {
		this.deviceParameterId = deviceParameterId;
	}

	
	/**监测装置编码 **/
    private  String  sensorCode;
    
    /** cma编码 **/
    private  String  cmaCode;

   
	/** 任务创建时间 **/
	private Date createTime;
	
	/** 任务执行时间 **/
	private Date executeTime;
	
	/** 命令类型 **/
	private String cmdType;
	
	/** 命令子类型 **/
	private String subCmdType;
	
	/** 任务状态  0：未下发   1：已下发  2 ：已取消  3：已执行成功  **/
	private Integer state;

	
	/**
	 * 返回任务信息ID
	 * @return
	 */
	public String getTaskConfigId() {
		return taskConfigId;
	}

	/**
	 * 设置任务信息ID
	 * @param taskConfigId
	 */
	public void setTaskConfigId(String taskConfigId) {
		this.taskConfigId = taskConfigId;
	}

	/** 返回操作 用户 **/
	public User getUser() {
		return user;
	}

	/** 设置操作用户 **/
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 返回任务创建时间
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置任务创建时间
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回任务执行时间
	 * @return
	 */
	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * 返回命令类型
	 * @return
	 */
	public String getCmdType() {
		return cmdType;
	}

	/**
	 * 设置命令类型
	 * @param cmdType
	 */
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}

	/**
	 * 返回命令子类型

	 * @return
	 */
	public String getSubCmdType() {
		return subCmdType;
	}

	/**
	 * 设置命令子类型

	 * @param subCmdType
	 */
	public void setSubCmdType(String subCmdType) {
		this.subCmdType = subCmdType;
	}

	/** 
	 * 返回任务状态

	 * @return
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置任务状态

	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 升级文件。当是升级任务时，不为null
	 *
	 * @return 升级文件。当是升级任务时，不为null
	 */
	public UpgradeFile getUpgradeFile() {
		return this.upgradeFile;
	}

	/**
	 * 设置 升级文件。当是升级任务时，不为null
	 *
	 * @param upgradeFile 升级文件。当是升级任务时，不为null
	 */
	public void setUpgradeFile(UpgradeFile upgradeFile) {
		this.upgradeFile = upgradeFile;
	}
	
    public String getSensorCode() {
			return sensorCode;
	}

	public void setSensorCode(String sensorCode) {
			this.sensorCode = sensorCode;
	}

	public String getCmaCode() {
			return cmaCode;
	}

	public void setCmaCode(String cmaCode) {
			this.cmaCode = cmaCode;
	}
		
}
