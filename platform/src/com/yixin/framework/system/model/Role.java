/*
 * Project platform
 *
 * Classname Role.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-8 下午
 *
 * ModifiedBy 
 * ModifyAt 2011-6-29 上午10:35:38
 * ModifyDescription 1、新增属性authResource；
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.framework.system.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色类
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_Role")
public class Role implements Serializable{
	
	private static final long serialVersionUID = 5449959671519998305L;
	
	/** 角色ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String roleId;
	/** 角色名称 */
	private String roleName;
	/** 角色类型 */
	private Integer roleType;
	/** 角色描述 */
	private String roleDesc;	
	
	/** 角色用户 */
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	/** 角色资源权限 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "T_RoleAuthResource", 
			joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"), 
			inverseJoinColumns = @JoinColumn(name = "authResourceId", referencedColumnName = "authResourceId"))
	//private Set<AuthResource> authResources = new HashSet<AuthResource>();
    private List<AuthResource> authResources = new ArrayList<AuthResource>();
  

	

	/**
	 * 获取拥有该角色的所有用户集合
	 * @return 用户集合
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * 设置拥有该角色的所有用户集合
	 * @param users 用户集合
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * 获取角色Id
	 * @return 角色Id
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色Id
	 * @param roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取角色名称
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名称
	 * @param roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 获取角色类型
	 * @return
	 */
	public Integer getRoleType() {
		return roleType;
	}

	/**
	 * 设置角色类型
	 * @param roleType
	 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	
	/**
	 * 获取角色描述
	 * @return
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * 设置角色描述
	 * @param roleDesc
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<AuthResource> getAuthResources() {
		return authResources;
	}

	public void setAuthResources(List<AuthResource> authResources) {
		this.authResources = authResources;
	}

	
}
