/*

 * Project platform
 *
 * Classname RoleAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-15 下午 15:16
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.web;

import java.util.ArrayList;
import java.util.List;

import com.yixin.framework.base.web.BaseAction;
import com.yixin.framework.system.dao.AuthResourceDao;
import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.service.RoleService;
import com.yixin.framework.system.util.OperationLogger;
import com.yixin.framework.system.util.OperationType;

/**
 * 用户角色Action类
 * @version v1.0.0
 * @author 
 *
 */
public class RoleAction extends BaseAction<Role> {

	/** 序列版本ID  */
	private static final long serialVersionUID = 1245484160500622455L;
	
	/** 全部权限资源集键名 */
	private static String ALL_AUTHRESOURCE = "allAuths";
	/** 已有权限资源ID集键名 */
	private static String OWENED_AUTHRESOURCE = "ownedAuthIds";
	
	/** 角色服务  */
	private RoleService roleService;
	/** 临时角色对象，保存提交数据等  */
	private Role role;
	
	/** 权限资源DAO  */
	private AuthResourceDao authResourceDao;
	
	/** 权限ID列表  */
	private String[] authResourceIds = new String[0]; 
	
	/** 角色名称查询项 */
	private String queryRoleName;

	/**
	 * 获取角色
	 * @return
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * 设置角色
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * 获取角色名称查询项
	 * @return
	 */
	public String getQueryRoleName() {
		return queryRoleName;
	}
	/**
	 * 设置角色名称查询项
	 * @param queryRoleName
	 */
	public void setQueryRoleName(String queryRoleName) {
		this.queryRoleName = queryRoleName;
	}
	/**
	 * 获取权限ID列表
	 * @return
	 */
	public String[] getAuthResourceIds() {
		return authResourceIds;
	}
	/**
	 * 设置权限ID列表
	 * @param authResourceIds
	 */
	public void setAuthResourceIds(String[] authResourceIds) {
		this.authResourceIds = authResourceIds;
	}
	/**
	 * 设置角色服务
	 * @param roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}	
	/**
	 * 设置权限资源DAO
	 * @param authResourceDao
	 */
	public void setAuthResourceDao(AuthResourceDao authResourceDao) {
		this.authResourceDao = authResourceDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		page = roleService.getPageRoles(queryRoleName, getPageNo(), getPageSize());
		return super.list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#save()
	 */
	@Override
	public String save() {
		roleService.addRole(this.role);
		OperationLogger.addLog(request, OperationType.ADD_DATA, "添加角色("+role.getRoleName()+")");
		setSuccessMsg("角色添加成功");
		return list();	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#update()
	 */
	@Override
	public String update() {
		roleService.editRole(this.role);
		OperationLogger.addLog(request, OperationType.EDIT_DATA, "修改角色("+role.getRoleName()+")");
		setSuccessMsg("角色修改成功");
		return list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#rawDelete()
	 */
	@Override
	protected void rawDelete() {
		roleService.deleteRole(this.role);
		OperationLogger.addLog(request, OperationType.DELETE_DATA, "删除角色("+role.getRoleName()+")");
		setSuccessMsg("角色删除成功");
	}
	
	/**
	 * 配置权限
	 * @return
	 */
	public String modAuth() {
		if(checkExist(true)){
			//已拥有权限
			StringBuilder builder = new StringBuilder();
			List<AuthResource> resources = role.getAuthResources();
				
			for(AuthResource aResource : resources)
				builder.append(aResource.getAuthResourceId() + ",");
			if(builder.length() > 1){
				builder.deleteCharAt(builder.length()-1);
			}
			String ownedAuthIds = builder.toString();
		
			//所有权限
			List<AuthResource> allAuths = this.authResourceDao.getAllCanView();
		
			request.setAttribute(OWENED_AUTHRESOURCE, ownedAuthIds);
			request.setAttribute(ALL_AUTHRESOURCE, allAuths);
			
			return "modAuth";
		}
		else {
			return list(); //返回list页
		}			
	}
	/**
	 * 更新角色权限
	 * @return
	 */
	public String updateAuth() {
		if(checkExist(true)){
			List<AuthResource> authResources = new ArrayList<AuthResource>();
			
			for(int i=0, len=authResourceIds.length; i<len; i++){
				AuthResource aResource = new AuthResource();
				aResource.setAuthResourceId(authResourceIds[i]);
				authResources.add(aResource);
			}
			
			role.setAuthResources(authResources); //设置新权限集
			this.roleService.editRole(role);
			OperationLogger.addLog(request, OperationType.EDIT_DATA, "配置角色("+role.getRoleName()+")权限");
			setSuccessMsg("权限配置成功");
		}		
		return list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#getId()
	 */
	@Override
	protected Object getId() {
		return this.role.getRoleId();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#getPersistenceById()
	 */
	@Override
	protected Role getPersistenceById() {
		return roleService.getRole((String)getId());
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#setEntityValues(java.lang.Object)
	 */
	@Override
	protected void setEntityValues(Role entity) {
		this.role = entity;
	}

}
