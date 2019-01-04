/*
 * Project platform
 *
 * Classname UserService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 下午05:40:43
 *
 * ModifiedBy 
 * ModifyAt 2011-6-14 下午 17:38
 * ModifyDescription 添加getUseByCode方法，修改编辑角色参数为Set型
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;


import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.User;

/**
 * 用户业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface UserService {

	/**
	 * 增加用户信息。当“用户编号”已经存在时，抛出BusinessException异常。
	 * 
	 * @param user
	 *            要添加的用户
	 * @throws BusinessException
	 * <br />
	 *             说明：errorCode-错误描述<br />
	 *             SystemErrorCode.SYSTEM_USER_USERCODE_ALREADYEXISTS-该“用户编号” 已经存在
	 */
	public abstract void addUser(User user);

	/**
	 * 修改用户信息。
	 * 
	 * @param user
	 *            要修改的用户
	 */
	public abstract void editUser(User user);

	/**
	 * 单个用户删除。
	 * 
	 * @param user
	 *            要删除的用户
	 */
	public abstract void deleteUser(User user);

	/**
	 * 多个用户删除
	 * 
	 * @param users
	 *            用户列表集合
	 */
	public abstract void deleteUser(Collection<User> users);

	/**
	 * 根据ID获取某一用户
	 * 
	 * @param id
	 *            ID标识
	 * @return
	 */
	public abstract User getUser(String id);
	
	/**
	 * 根据用户编号（唯一）获取用户
	 * @param userCode
	 * @return
	 */
	public abstract User getUserByCode(String userCode);

	/**
	 * 获取所有用户
	 * 
	 * @return 查找到的对象集
	 */
	public abstract List<User> getAllUsers();

	/**
	 * 查找某一页的用户
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的对象集
	 */
	public abstract Page<User> getPageUsers(long pageNo, long pageSize);

	/**
	 * 通过指定条件查找用户
	 * 
	 * @param userCode
	 *            用户编号，为null时表示不进行过滤
	 * @param username
	 *            用户名称，为null时表示不进行过滤
	 * @param state
	 *            状态，为-1时表示不进行过滤
	 * @return 查找到的对象集
	 */
	public abstract List<User> getAllUsers(String userCode, String username, int state);

	/**
	 * 通过指定条件查找用户
	 * 
	 * @param userCode
	 *            用户编号，为null时表示不进行过滤
	 * @param username
	 *            用户名称，为null时表示不进行过滤
	 * @param state
	 *            状态，为-1时表示不进行过滤
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的对象集
	 */
	public abstract Page<User> getPageUsers(String userCode, String username, int state, long pageNo, long pageSize);

	/***
	 * 获取某个用户的所有权限列表
	 * 
	 * @param user
	 *            用户
	 * @return 用户所拥有的权限集
	 */
	public abstract List<Role> getAllRoles(User user);

	/**
	 * 查询某个用户的权限信息列表，按分页方式
	 * 
	 * @param user
	 *            用户
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 用户所拥有的权限集
	 */
	public abstract Page<Role> getPageRoles(User user, long pageNo, long pageSize);

	/**
	 * 修改某个用户的操作权限
	 * 
	 * @param user
	 *            用户
	 * @param roles
	 *            权限列表
	 */
	public abstract void editRoles(User user, Set<Role> roles);
}
