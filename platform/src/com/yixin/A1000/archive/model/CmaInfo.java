/*
 * Project platform
 *
 * Class CmaInfo.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:26:40
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 监测代理
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_CmaInfo")
public class CmaInfo implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -3746186928299413925L;

	/** 监测代理Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String cmaInfoId;

	/** 监测代理编码 */
	private String cmaCode;

	/** 监测代理名称 */
	private String cmaName;

	/** 型号 */
	private String cmaModel;

	/** 安装日期 */
	private Date installDate;

	/** 状态。0（CmaInfo.STATE_DISABLE） - 停用 ；1（CmaInfo.STATE_ENABLE） - 启用 。 */
	private Integer state;

	/** 描述 */
	private String cmaDesc;

	/**
	 * 获取 监测代理Id
	 * 
	 * @return 监测代理Id
	 */
	public String getCmaInfoId() {
		return this.cmaInfoId;
	}

	/**
	 * 设置 监测代理Id
	 * 
	 * @param cmaInfoId
	 *            监测代理Id
	 */
	public void setCmaInfoId(String cmaInfoId) {
		this.cmaInfoId = cmaInfoId;
	}

	/**
	 * 获取 监测代理编码
	 * 
	 * @return 监测代理编码
	 */
	public String getCmaCode() {
		return this.cmaCode;
	}

	/**
	 * 设置 监测代理编码
	 * 
	 * @param cmaCode
	 *            监测代理编码
	 */
	public void setCmaCode(String cmaCode) {
		this.cmaCode = cmaCode;
	}

	/**
	 * 获取 监测代理名称
	 * 
	 * @return 监测代理名称
	 */
	public String getCmaName() {
		return this.cmaName;
	}

	/**
	 * 设置 监测代理名称
	 * 
	 * @param cmaName
	 *            监测代理名称
	 */
	public void setCmaName(String cmaName) {
		this.cmaName = cmaName;
	}

	/**
	 * 获取 型号
	 * 
	 * @return 型号
	 */
	public String getCmaModel() {
		return this.cmaModel;
	}

	/**
	 * 设置 型号
	 * 
	 * @param cmaModel
	 *            型号
	 */
	public void setCmaModel(String cmaModel) {
		this.cmaModel = cmaModel;
	}

	/**
	 * 获取 installDate
	 * 
	 * @return installDate
	 */
	public Date getInstallDate() {
		return this.installDate;
	}

	/**
	 * 设置 installDate
	 * 
	 * @param installDate
	 *            installDate
	 */
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	/**
	 * 获取 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 * 
	 * @return 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 * 
	 * @param state
	 *            状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getCmaDesc() {
		return this.cmaDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param cmaDesc
	 *            描述
	 */
	public void setCmaDesc(String cmaDesc) {
		this.cmaDesc = cmaDesc;
	}

	/**
	 * 状态。对应的int值通过调用getValue*(获得。
	 * 
	 * @version v1.0.0
	 * @author 
	 */
	public enum State {

		/** 状态：停用 */
		DISABLE(0),

		/** 状态：启用 */
		ENABLE(1);

		/** 对应的int值 */
		private int value;

		/**
		 * 构造方法
		 * 
		 * @param value
		 */
		State(int value) {
			this.value = value;
		}

		/**
		 * 获取对应的int值
		 * 
		 * @return value 对应的int值
		 */
		public int getValue() {
			return this.value;
		}
	}
}
