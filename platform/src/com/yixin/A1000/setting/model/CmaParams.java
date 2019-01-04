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

import com.yixin.A1000.archive.model.CmaInfo;

/**
 * 监测代理参数
 * 
 * @author 梁立全
 */
@Entity
@Table(name = "T_CmaParams")
public class CmaParams {

	/** ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String cmaParamsId;

	/** 监测代理 **/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "cmaInfoId")
	private CmaInfo cmaInfo = new CmaInfo();

	/** CAG IP **/
	private String cagIp;

	/** 版本号 **/
	private String version;

	/*** 状态 0：未下发 1：已下发并确认 **/
	private Integer state;

	/** 创建时间。与TaskConfig中的createTime相同 */
	private Date createTime;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCmaParamsId() {
		return cmaParamsId;
	}

	public void setCmaParamsId(String cmaParamsId) {
		this.cmaParamsId = cmaParamsId;
	}

	public CmaInfo getCmaInfo() {
		return cmaInfo;
	}

	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}

	public String getCagIp() {
		return cagIp;
	}

	public void setCagIp(String cagIp) {
		this.cagIp = cagIp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取 创建时间。与TaskConfig中的createTime相同
	 * 
	 * @return 创建时间。与TaskConfig中的createTime相同
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间。与TaskConfig中的createTime相同
	 * 
	 * @param createTime
	 *            创建时间。与TaskConfig中的createTime相同
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
