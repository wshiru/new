/*
 * Project platform
 *
 * Classname User.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 下午05:33:46
 *
 * ModifiedBy 
 * ModifyAt 2011-6-8 下午
 * ModifyDescription 添加角色字段，修改表名
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户类
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_User")
public class User implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -2205658953516793179L;
	
	/** 用户状态：停用  */
	@Transient
	public static final int STATE_DISABLE = 0;
	
	/** 用户状态：启用  */
	@Transient
	public static final int STATE_ENABLE = 1;

	/** 用户ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;

	/** 用户编号 */
	private String userCode;

	/** 用户名称 */
	private String userName;

	/** 登录密码 */
	private String password;

	/** 联系电话 */
	private String phone;

	/** 到期时间 */
	private Date expiredTime;

	/** 状态。0 - 停用。1 - 启用，默认0 */
	private Integer state;

	/** 最后登录时间 */
	private Date lastLoginTime;

	/** 最后登录IP */
	private String lastLoginIp;

	/** 创建时间 */
	private Date createTime;

	/** 用户描述 */
	private String userDesc;
	
	/** 密码确认：新增用户时用到 */
	@Transient
	private String confirmPassword;
	
	/** 用户角色 */
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinTable(name="T_UserRole",
	    joinColumns=@JoinColumn(name="userId", referencedColumnName="userId"),
	    inverseJoinColumns=@JoinColumn(name="roleId", referencedColumnName="roleId")    
    	)  
	private Set<Role> roles;

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 获取 用户ID
	 * 
	 * @return 用户ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置 用户ID
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取 用户编号
	 * 
	 * @return 用户编号
	 */
	public String getUserCode() {
		return this.userCode;
	}

	/**
	 * 设置 用户编号
	 * 
	 * @param userCode
	 *            用户编号
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 获取 用户名称
	 * 
	 * @return 用户名称
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 设置 用户名称
	 * 
	 * @param username
	 *            用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取 登录密码
	 * 
	 * @return 登录密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 登录密码
	 * 
	 * @param password
	 *            登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 联系电话
	 * 
	 * @return 联系电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置 联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取 到期时间
	 * 
	 * @return 到期时间
	 */
	public Date getExpiredTime() {
		return this.expiredTime;
	}

	/**
	 * 设置 到期时间
	 * 
	 * @param expiredTime
	 *            到期时间
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	/**
	 * 获取 状态。0 - 停用。1 - 启用，默认0
	 * 
	 * @return 状态。0 - 停用。1 - 启用，默认0
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 状态。0 - 停用。1 - 启用，默认0
	 * 
	 * @param state
	 *            状态。0 - 停用。1 - 启用，默认0
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 最后登录时间
	 * 
	 * @return 最后登录时间
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * 设置 最后登录时间
	 * 
	 * @param lastLoginTime
	 *            最后登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取 最后登录IP
	 * 
	 * @return 最后登录IP
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * 设置 最后登录IP
	 * 
	 * @param lastLoginIp
	 *            最后登录IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 用户描述
	 * 
	 * @return 用户描述
	 */
	public String getUserDesc() {
		return this.userDesc;
	}

	/**
	 * 设置 用户描述
	 * 
	 * @param userDesc
	 *            用户描述
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
	 * 获取 密码确认：新增用户时用到
	 *
	 * @return 密码确认：新增用户时用到
	 */
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	/**
	 * 设置 密码确认：新增用户时用到
	 *
	 * @param confirmPassword 密码确认：新增用户时用到
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
