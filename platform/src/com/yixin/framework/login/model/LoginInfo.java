/*
 * Project platform
 *
 * Classname LoginInfo.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-2 下午02:22:56
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.User;

/**
 * 登录用户信息
 * 
 * @version v1.0.0
 * @author 
 */
public class LoginInfo implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 3906506171195191044L;

	/** 当前登录用户 */
	private User user;

	/** 当前登录用户所允许操作的所有URL地址 */
	private Set<String> allowedUrls;

	/** 需要限制的URLS（系统内所有的URL） */
	private Set<String> limitUrls;
		
	/** 当前用户所拥有的系统资源，包括系统设置、档案管理、系统监控三大类 **/
	private  List<AuthResource> sysAuthResuorces;
	
	
	/** 当前用户所拥有的数据类型资源 （导航菜单），主要有：实时数据、数据分析、历史数据等 **/
	private  List<AuthResource> dataTypeAuthResuorces;
	
	/** 当前登录用户具有的所有权限操作菜单(二级） */
	private List<AuthResource> menuList;

	/** 系统主窗口左边菜单资源 **/
    private List<AuthResource> leftMenuAuthResourceList;
  	
    /** 系统树形目录数据 **/
    private List<TreeInfo> treeInfos = new ArrayList<TreeInfo>();
  

	/** 当前登录用户所拥有的所有角色 */
	private List<Role> roles;

	/** 用户所有可操作的资源ID列表 */
	private List<String> authResourceIdList = new ArrayList<String>();

	/** 用户所有可操作的资源ID */
	private String authResourceIds;

	/** 
	 * 天气预报
	 */
	private  String  WeatherInfoHtml;
	
	

	/**
	 * 获取 当前登录用户
	 * 
	 * @return 当前登录用户
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 设置 当前登录用户
	 * 
	 * @param user
	 *            当前登录用户
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取 当前登录用户所允许操作的所有URL地址
	 * 
	 * @return 当前登录用户所允许操作的所有URL地址
	 */
	public Set<String> getAllowedUrls() {
		return this.allowedUrls;
	}

	/**
	 * 设置 当前登录用户所允许操作的所有URL地址
	 * 
	 * @param allowedUrls
	 *            当前登录用户所允许操作的所有URL地址
	 */
	public void setAllowedUrls(Set<String> allowedUrls) {
		this.allowedUrls = allowedUrls;
	}
	
	/**
	 * 获取 需要限制的URLS
	 * 
	 * @return 需要限制的URLS
	 */
	public Set<String> getLimitUrls() {
		return this.limitUrls;
	}

	/**
	 * 设置 需要限制的URLS
	 * 
	 * @param limitUrls
	 *            需要限制的URLS
	 */
	public void setLimitUrls(Set<String> limitUrls) {
		this.limitUrls = limitUrls;
	}

	/**
	 * 获取 当前登录用户具有的所有权限操作菜单
	 * 
	 * @return 当前登录用户具有的所有权限操作菜单
	 */
	public List<AuthResource> getMenuList() {
		return this.menuList;
	}

	/**
	 * 设置 当前登录用户具有的所有权限操作菜单
	 * 
	 * @param menuList
	 *            当前登录用户具有的所有权限操作菜单
	 */
	public void setMenuList(List<AuthResource> menuList) {
		this.menuList = menuList;
	}

	/**
	 * 获取 当前登录用户所拥有的所有角色
	 * 
	 * @return 当前登录用户所拥有的所有角色
	 */
	public List<Role> getRoles() {
		return this.roles;
	}

	/**
	 * 设置 当前登录用户所拥有的所有角色
	 * 
	 * @param roles
	 *            当前登录用户所拥有的所有角色
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 获取 用户所有可操作的资源ID列表
	 * 
	 * @return 用户所有可操作的资源ID列表
	 */
	public List<String> getAuthResourceIdList() {
		return this.authResourceIdList;
	}

	/**
	 * 设置 用户所有可操作的资源ID列表
	 * 
	 * @param authResourceIdList
	 *            用户所有可操作的资源ID列表
	 */
	public void setAuthResourceIdList(List<String> authResourceIdList) {
		this.authResourceIdList = authResourceIdList;
	}

	/**
	 * 获取 用户所有可操作的资源ID
	 * 
	 * @return 用户所有可操作的资源ID
	 */
	public String getAuthResourceIds() {
		return this.authResourceIds;
	}

	/**
	 * 设置 用户所有可操作的资源ID
	 * 
	 * @param authResourceIds
	 *            用户所有可操作的资源ID
	 */
	public void setAuthResourceIds(String authResourceIds) {
		this.authResourceIds = authResourceIds;
	}
	
	
    /** 
     * 返回系统资源
     * @return
     */
	public List<AuthResource> getSysAuthResuorces() {
		return sysAuthResuorces;
	}

	/**
	 * 设置系统资源
	 * @param sysAuthResuorces
	 */
	public void setSysAuthResuorces(List<AuthResource> sysAuthResuorces) {
		this.sysAuthResuorces = sysAuthResuorces;
	}
	
	/**
	 * 返回导航菜单资源
	 * @return
	 */
	public List<AuthResource> getDataTypeAuthResuorces() {
		return dataTypeAuthResuorces;
	}

	/**
	 * 设置导航菜单资源
	 * @param dataTypeAuthResuorces
	 */
	public void setDataTypeAuthResuorces(List<AuthResource> dataTypeAuthResuorces) {
		this.dataTypeAuthResuorces = dataTypeAuthResuorces;
	}
	
	
	/**
	 * 返回菜单主窗口导航左边菜单资源
	 * @return
	 */
    public List<AuthResource> getLeftMenuAuthResourceList() {
    	return leftMenuAuthResourceList;
	}

    /**
     * 设置菜单主窗口导航左边菜单资源
     * @param leftMenuAuthResourceList
     */
	public void setLeftMenuAuthResourceList(
				List<AuthResource> leftMenuAuthResourceList) {
		this.leftMenuAuthResourceList = leftMenuAuthResourceList;
	}
	
	
	/**
	 * 返回系统登录的树数据  
	 * @return
	 */
	public List<TreeInfo> getTreeInfos() {
		return treeInfos;
	}


	/**
	 * 设置系统登录的树数据  
	 * @param treeInfos
	 */
	public void setTreeInfos(List<TreeInfo> treeInfos) {
		this.treeInfos = treeInfos;
	}
	
	/**
	 * 返回天气预报HTML
	 * @return
	 */
	public String getWeatherInfoHtml() {
		return WeatherInfoHtml;
	}
	
	/**
	 * 设置天气预报HTML
	 * @param weatherInfoHtml
	 */
	public void setWeatherInfoHtml(String weatherInfoHtml) {
		WeatherInfoHtml = weatherInfoHtml;
	}
	
		
}

