/*
 * Project platform
 *
 * Classname RoleService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-8 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service;


import java.util.Collection;
import java.util.List;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.User;


/**
 * 角色业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RoleService {

	/**
	 * 增加角色信息
	 * 
	 * @param role
	 *            要添加的角色
	 * @exception BusinessException
	 *                <br />
	 *                说明：errorCode-错误描述<br />
	 *                ErrorCodeKeys.SYSTEM_Role_ROLENAME_ALREADYeXISTS-“角色名称”已经存在

	 */
	public abstract void addRole(Role role);

	/**
	 * 更新角色信息。

	 * 
	 * @param role
	 *            要进行更新的角色
	 * @exception BusinessException
	 *                <br />
	 *                说明：errorCode-错误描述<br />
	 *                ErrorCodeKeys.SYSTEM_Role_ROLENAME_ALREADYeXISTS-“角色名称”已经存在

	 */
	public abstract void editRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param role
	 *            要进行删除的角色
	 * @exception BusinessException
	 *                <br />
	 *                说明：errorCode-错误描述<br />
	 *                ErrorCodeKeys.SYSTEM_Role_ROLEEXISTSUSERS-角色中存在用户

	 */
	public abstract void deleteRole(Role role);

	/**
	 * 删除多个角色
	 * 
	 * @param users
	 *            要删除的角色列表
	 * @exception BusinessException
	 *                <br />
	 *                说明：errorCode-错误描述<br />
	 *                ErrorCodeKeys.SYSTEM_Role_ROLEEXISTSUSERS-角色中存在用户

	 */
	public abstract void deleteRoles(Collection<Role> roles);

	/**
	 * 根据ID查找角色
	 * 
	 * @param id
	 *            要查找角色的ID
	 * @return 角色信息
	 */
	public abstract Role getRole(String id);

	/**
	 * 获取所有的角色信息
	 * 
	 * @return 所有角色信息

	 */
	public abstract List<Role> getAllRoles();

	/**
	 * 查询第pageNo页的角色列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小

	 * @return 每pageNo页的角色列表
	 */
	public abstract Page<Role> getPageRoles(long pageNo, long pageSize);

	/**
	 * 根据条件查询角色
	 * 
	 * @param roleName
	 *            角色编号。如果为null，则表示该项不作限制
	 * @return 返回符合条件的所有角色

	 */
	public abstract List<Role> getAllRoles(String roleName);

	/**
	 * 根据条件查询角色
	 * 
	 * @param roleName
	 *            角色名称。如果为null，则表示该项不作限制
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小

	 * @return 返回符合条件的第pageNo页的角色
	 */
	public abstract Page<Role> getPageRoles(String roleName, long pageNo, long pageSize);


	/**
	 * 查找role下面的用户

	 * 
	 * @param role
	 * 			角色
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小

	 * @return 返回role下的第pageNo页的用户
	 */
	public abstract Page<User> getPageUsers(Role role, long pageNo, long pageSize);


	/**
	 * 获取角色role的权限资源

	 * @param role
	 * 			角色
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页面大小
	 * @return
	 */
	public abstract Page<AuthResource> getPageAuthResource(Role role, long pageNo, long pageSize);
	
}
