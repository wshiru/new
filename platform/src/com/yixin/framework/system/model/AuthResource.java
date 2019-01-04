/*
 * Project platform
 *
 * Class AuthResource.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 上午10:30:38
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * 权限资源
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_AuthResource")
public class AuthResource implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 6123447695055554916L;

	/** 资源类型：系统资源（包含系统设置、档案管理、系统监测三大类型包含的资源） */
	public static final int RESOURCETYPE_SYS = 0;
			
	/** 资源类型：监测装置所拥有导航大类资源  如：实时数据、历史数据、娄据分析等*/
	public static final int RESOURCETYPE_SENSOR_ONE_MENU = 1;

	/** 资源类型：监测装置二级菜单资源  */
	public static final int RESOURCETYPE_SENSOR_TWO_MENU = 2;
	
	/** 资源类型：页面内元素资源 （监测装置三级菜单资源）*/
	public static final int RESOURCETYPE_SENSOR_THREE_MENU = 3;

	/** 权限资源ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String authResourceId;

	/** 父节点 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "parentId")
	private AuthResource parent;



	/** 权限资源名称 */
	private String name;

	/** URL链接地址 */
	private String url;

	/** 打开窗口 */
	private String target;

	/** 图片文件名称，包括后缀 */
	private String icon;

	/** 资源类型。0 - 系统资源，1 - 用户资源，2 - 导航资源， 3 - 页面内元素资源。默认0 */
	private Integer resourceType;

	/** 资源编号。固定唯一 */
	private Integer resourceCode;

	/** 排序序号。序号超小，排的越前，可调整 */
	@OrderBy("ASC")    
	private Integer sortingNumber;

	/** 系统编号 */
	private String systemCode;

	/** 操作类型 */
	private Integer operationType;

	
	/** 权限资源描述 */
	private String resourceDesc;
	
	/** 角色权限资源 */
	@ManyToMany(mappedBy="authResources")
	private Set<Role> roles;

	
	/**
	 * 是否可视  0： 否  1：是
	 */
	private  Integer visible;
	
	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	/**
	 * 获取 权限资源ID
	 * 
	 * @return 权限资源ID
	 */
	public String getAuthResourceId() {
		return this.authResourceId;
	}

	/**
	 * 设置 权限资源ID
	 * 
	 * @param authResourceId
	 *            权限资源ID
	 */
	public void setAuthResourceId(String authResourceId) {
		this.authResourceId = authResourceId;
	}

	/**
	 * 获取 父节点
	 * 
	 * @return 父节点
	 */
	public AuthResource getParent() {
		return this.parent;
	}

	/**
	 * 设置 父节点
	 * 
	 * @param parent
	 *            父节点
	 */
	public void setParent(AuthResource parent) {
		this.parent = parent;
	}

	/**
	 * 获取 权限资源名称
	 * 
	 * @return 权限资源名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 权限资源名称
	 * 
	 * @param name
	 *            权限资源名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 URL链接地址
	 * 
	 * @return URL链接地址
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置 URL链接地址
	 * 
	 * @param url
	 *            URL链接地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取 打开窗口
	 * 
	 * @return 打开窗口
	 */
	public String getTarget() {
		return this.target;
	}

	/**
	 * 设置 打开窗口
	 * 
	 * @param target
	 *            打开窗口
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 获取 图片文件名称，包括后缀
	 * 
	 * @return 图片文件名称，包括后缀
	 */
	public String getIcon() {
		return this.icon;
	}

	/**
	 * 设置 图片文件名称，包括后缀
	 * 
	 * @param icon
	 *            图片文件名称，包括后缀
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取 资源类型。0 - 系统资源，1 - 用户资源，2 - 导航资源， 3 - 页面内元素资源。默认0
	 * 
	 * @return 资源类型。0 - 系统资源，1 - 用户资源，2 - 导航资源， 3 - 页面内元素资源。默认0
	 */
	public Integer getResourceType() {
		return this.resourceType;
	}

	/**
	 * 设置 资源类型。0 - 系统资源，1 - 用户资源，2 - 导航资源， 3 - 页面内元素资源。默认0
	 * 
	 * @param resourceType
	 *            资源类型。0 - 系统资源，1 - 用户资源，2 - 导航资源， 3 - 页面内元素资源。默认0
	 */
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 获取 资源编号。固定唯一
	 * 
	 * @return 资源编号。固定唯一
	 */
	public Integer getResourceCode() {
		return this.resourceCode;
	}

	/**
	 * 设置 资源编号。固定唯一
	 * 
	 * @param resourceCode
	 *            资源编号。固定唯一
	 */
	public void setResourceCode(Integer resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**
	 * 获取 排序序号。序号超小，排的越前，可调整
	 * 
	 * @return 排序序号。序号超小，排的越前，可调整
	 */
	public Integer getSortingNumber() {
		return this.sortingNumber;
	}

	/**
	 * 设置 排序序号。序号超小，排的越前，可调整
	 * 
	 * @param sortingNumber
	 *            排序序号。序号超小，排的越前，可调整
	 */
	public void setSortingNumber(Integer sortingNumber) {
		this.sortingNumber = sortingNumber;
	}

	/**
	 * 获取 系统编号
	 * 
	 * @return 系统编号
	 */
	public String getSystemCode() {
		return this.systemCode;
	}

	/**
	 * 设置 系统编号
	 * 
	 * @param systemCode
	 *            系统编号
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * 获取 操作类型
	 * 
	 * @return 操作类型
	 */
	public Integer getOperationType() {
		return this.operationType;
	}

	/**
	 * 设置 操作类型
	 * 
	 * @param operationType
	 *            操作类型
	 */
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	/**
	 * 获取 权限资源描述
	 * 
	 * @return 权限资源描述
	 */
	public String getResourceDesc() {
		return this.resourceDesc;
	}

	/**
	 * 设置 权限资源描述
	 * 
	 * @param resourceDesc
	 *            权限资源描述
	 */
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	/**
	 * 获取 角色权限资源
	 * 
	 * @return 角色权限资源
	 */
	public Set<Role> getRoles() {
		return this.roles;
	}

	/**
	 * 设置 角色权限资源
	 * 
	 * @param roles
	 *            角色权限资源
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
