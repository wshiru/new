/*
 * Project platform
 *
 * Classname RoleServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-8 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.dao.DataIntegrityViolationException;


import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
import com.yixin.framework.system.dao.RoleDao;
import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.User;
import com.yixin.framework.system.service.RoleService;

/**
 * 角色业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class RoleServiceImpl implements RoleService {
	// **********************************************************************************
	/* fields */
	private RoleDao roleDao;

	// **********************************************************************************
	/* setters and getters */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#addRole(com.yixin.framework.system.model.Role)
	 */
	public void addRole(Role role) {
		if(role == null){
			throw new NullArgumentException("role");
		}
		if (this.roleDao.getAllByProperty("roleName", role.getRoleName()).size() > 0) {
			throw new BusinessException(ErrorCode.CONFLICT);
		}
		this.roleDao.save(role);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#editRole(com.yixin.framework.system.model.Role)
	 */
	public void editRole(Role role) {
		if(role == null)
			throw new NullArgumentException("role");	
//		if(null == role.getRoleId() || null == getRole(role.getRoleId()))
//			throw new BusinessException(ErrorCode.NOT_FOUND);
		List<Role> roles = this.roleDao.getAllByProperty("roleName", role.getRoleName());
		Iterator<Role> iterator = roles.iterator();
		while (iterator.hasNext()) {
			Role r = iterator.next();
			if (!r.getRoleId().equals(role.getRoleId())) {
				throw new BusinessException(ErrorCode.CONFLICT);
			}
		}
		this.roleDao.update(role);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#deleteRole(com.yixin.framework.system.model.Role)
	 */
	public void deleteRole(Role role) {
		if(role == null)
			throw new NullArgumentException("role");	
//		if(null == role.getRoleId() || null == getRole(role.getRoleId()))
//			throw new BusinessException(ErrorCode.NOT_FOUND);
		try{
			this.roleDao.delete(role);
		}catch(DataIntegrityViolationException e){
			throw new BusinessException(ErrorCode.IN_USE);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#deleteRoles(java.util.Collection)
	 */
	public void deleteRoles(Collection<Role> roles) {
		Iterator<Role> it = roles.iterator();
		while (it.hasNext()) {
			Role role = it.next();
			this.deleteRole(role);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getRole(java.lang.String)
	 */
	public Role getRole(String id) {
		return this.roleDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getAllRoles()
	 */
	public List<Role> getAllRoles() {
		return this.roleDao.getAll();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getPageRoles(long, long)
	 */
	public Page<Role> getPageRoles(long pageNo, long pageSize) {
		return this.roleDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getAllRoles(java.lang.String)
	 */
	public List<Role> getAllRoles(String roleName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (roleName != null && roleName.trim().length() > 0) {
			map.put("roleName", roleName);
		}
		return this.roleDao.getAllLikeProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getPageRoles(java.lang.String, long, long)
	 */
	public Page<Role> getPageRoles(String roleName, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (roleName != null && roleName.trim().length() > 0) {
			map.put("roleName", roleName);
		}
		return this.roleDao.getPageLikeProperties(map, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getPageUsers(com.yixin.framework.system.model.Role, long, long)
	 */
	public Page<User> getPageUsers(Role role, long pageNo, long pageSize) {
		List<User> users = new ArrayList<User>();
		users.addAll(role.getUsers());	
		return new Page<User>(pageNo, pageSize, users.size(), users);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.RoleService#getPageAuthResource(com.yixin.framework.system.model.Role, long, long)
	 */
	public Page<AuthResource> getPageAuthResource(Role role, long pageNo, long pageSize) {
		List<AuthResource> authResources = new ArrayList<AuthResource>();
		authResources.addAll(role.getAuthResources());
		return new Page<AuthResource>(pageNo, pageSize, authResources.size(), authResources);
	}
}
