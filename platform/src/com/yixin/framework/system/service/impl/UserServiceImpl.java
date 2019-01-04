/*
 * Project platform
 *
 * Classname UserServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 下午05:41:12
 *
 * ModifiedBy 
 * ModifyAt 2011-6-14 下午 17:38
 * ModifyDescription 添加getUseByCode方法实现；为添加、编辑方法添加空参异常判断
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.NullArgumentException;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.dao.UserDao;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.SystemErrorCode;
import com.yixin.framework.system.model.User;
import com.yixin.framework.system.service.RoleService;
import com.yixin.framework.system.service.UserService;

/**
 * 用户业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	private RoleService roleService;
	
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#addUser(com.yixin.framework
	 * .system.model.User)
	 */
	public void addUser(User user) {
		if (user == null) {
			throw new NullArgumentException("user");
		}
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("userCode", user.getUserCode());
		if (this.userDao.getAllByProperties(conditionMap).size() > 0) {
			throw new BusinessException(SystemErrorCode.SYSTEM_USER_USERCODE_ALREADYEXISTS);
		}
		this.userDao.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#editUser(com.yixin.framework
	 * .system.model.User)
	 */
	public void editUser(User user) {
		if (user == null) {
			throw new NullArgumentException("user");
		}
		this.userDao.update(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#deleteUser(com.yixin.framework
	 * .system.model.User)
	 */
	public void deleteUser(User user) {
		boolean  cannotDel = user.getUserCode().toUpperCase().equals("ADMIN") ||
		user.getUserName().equals("超级管理员");
		//不能删除系统管理员
		if ( cannotDel ) {
			throw new BusinessException(SystemErrorCode.SYSTEM_ADMIN_CANNOT_DELETE);
		}
		this.roleService.deleteRoles(user.getRoles()); //删除用户权限
		//user.setState(User.STATE_DISABLE);
		//this.editUser(user); //删除用户
		//this.deleteUser(user);
		
        this.userDao.delete(user);
      
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.system.service.UserService#deleteUser(java.util.
	 * Collection)
	 */
	public void deleteUser(Collection<User> users) {
		for (User user : users) {
			this.deleteUser(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getUser(java.lang.String)
	 */
	public User getUser(String id) {
		return this.userDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getUserByCode(java.lang
	 * .String)
	 */
	public User getUserByCode(String userCode) {
		List<User> list = this.userDao.getAllByProperty("userCode", userCode);
		if (list.size() > 0)
			return list.get(0);
		else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.system.service.UserService#getAllUser()
	 */
	public List<User> getAllUsers() {
		return this.userDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.system.service.UserService#getPageUser(long,
	 * long)
	 */
	public Page<User> getPageUsers(long pageNo, long pageSize) {
		return this.userDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getAllUser(java.lang.String
	 * , java.lang.String, int, java.util.Date)
	 */
	public List<User> getAllUsers(String userCode, String username, int state) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		if (null != userCode) {
			conditionMap.put("userCode", userCode);
		}
		if (null != username) {
			conditionMap.put("username", username);
		}
		if (-1 != state) {
			conditionMap.put("state", state);
		}
		return this.userDao.getAllByProperties(conditionMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getPageUser(java.lang.
	 * String, java.lang.String, int, java.util.Date, long, long)
	 */
	public Page<User> getPageUsers(String userCode, String username, int state, long pageNo, long pageSize) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		if (null != userCode) {
			conditionMap.put("userCode", userCode);
		}
		if (null != username) {
			conditionMap.put("userName", username);
		}
		if (-1 != state) {
			conditionMap.put("state", state);
		}
		return this.userDao.getPageByProperties(conditionMap, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getAllRoles(com.yixin.
	 * framework.system.model.User)
	 */
	public List<Role> getAllRoles(User user) {
		Collection<Role> roles = user.getRoles();
		List<Role> result = new ArrayList<Role>();
		result.addAll(roles);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#getPageRoles(com.yixin
	 * .framework.system.model.User, long, long)
	 */
	public Page<Role> getPageRoles(User user, long pageNo, long pageSize) {
		Collection<Role> roles = user.getRoles();
		List<Role> roleList = new ArrayList<Role>();
		int i = 1;
		long startIndex = (pageNo - 1) * pageSize + 1;
		long endIndex = pageNo * pageSize;
		for (Role role : roles) {
			if (i > endIndex) {
				break;
			}
			if (i >= startIndex && i <= endIndex) {
				roleList.add(role);
			}
			i++;
		}
		Page<Role> result = new Page<Role>(pageNo, pageSize, roles.size(), roleList);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.system.service.UserService#editRoles(com.yixin.framework
	 * .system.model.User, java.util.Collection)
	 */
	public void editRoles(User user, Set<Role> roles) {
		user.setRoles(roles);
		this.userDao.update(user);
	}
}
