/*
 * Project platform
 *
 * Classname UserAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 下午05:46:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-20 10:20
 * ModifyDescription 添加密码修改功能，修改QueryModel用户名字段为userName, 添加角色分配功能
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.web;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.SystemErrorCode;
import com.yixin.framework.system.model.User;
import com.yixin.framework.system.service.RoleService;
import com.yixin.framework.system.service.UserService;
import com.yixin.framework.system.util.OperationLogger;
import com.yixin.framework.system.util.OperationType;

/**
 * 用户管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class UserAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 1558460220430596980L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_Attr_name = "errorMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 用户业务接口 */
	private UserService userService;

	/** 用户信息。保存新增用户提交的数据，及保存到request域的用户信息等 */
	private User user;

	/** 用户ID，在修改、删除时用到 */
	private String id;
	
	/** 新密码，用于密码修改 */
	private String newPwd;
	/** 角色服务业务接口  */
	private RoleService roleService;
	/** 角色ID列表  */
	private String[] roleIds = new String[0]; 
	

	/** 查询模型 */
	private QueryModel queryModel = new QueryModel();

	/**
	 * 获取 用户信息。保存新增用户提交的数据，及保存到request域的用户信息等
	 * 
	 * @return 用户信息
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 设置 用户信息。保存新增用户提交的数据，及保存到request域的用户信息等
	 * 
	 * @param user
	 *            用户信息
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取 用户ID，在修改、删除时用到
	 * 
	 * @return 用户ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 用户ID，在修改、删除时用到
	 * 
	 * @param id
	 *            用户ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置新密码
	 * @param newPwd
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	/**
	 * 获取角色ID列表
	 * @return
	 */
	public String[] getRoleIds() {
		return roleIds;
	}

	/**
	 * 设置角色ID列表
	 * @param roleIds
	 */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 设置角色服务接口
	 * @param roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public QueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(QueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 获取 用于保存在request域的错误提示信息
	 * 
	 * @return 用于保存在request域的错误提示信息
	 */
	public static String getErrorMessageAttrName() {
		return ERROR_MESSAGE_Attr_name;
	}

	/**
	 * 设置 用户业务接口
	 * 
	 * @param userService
	 *            用户业务接口
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 用户列表
	 * 
	 * @return 结果页面
	 */
	public String list() {
		String pageNoStr = request.getParameter("pn");
		String pageSizeStr = request.getParameter("ps");
		int pageNo = 1;
		int pageSize = 20;
		if (pageNoStr != null && !pageNoStr.equals("")) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		if (null == this.queryModel.getUserCode() || 0 == this.queryModel.getUserCode().trim().length()) {
			this.queryModel.setUserCode(null);
		}
		if (null == this.queryModel.getUserName() || 0 == this.queryModel.getUserName().trim().length()) {
			this.queryModel.setUserName(null);
		}
		Page<User> page = this.userService.getPageUsers(this.queryModel.getUserCode(), this.queryModel.getUserName(),
				this.queryModel.getState(), pageNo, pageSize);
		request.setAttribute("userName", "userName");

		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到新增用户页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * 保存新增用户信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		try {
			PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			String pwd = passwordEncoder.encodePassword(this.user.getPassword(), null);
			this.user.setPassword(pwd);
			user.setCreateTime(new Date());
			this.userService.addUser(user);
			OperationLogger.addLog(request, OperationType.ADD_DATA, "添加用户("+user.getUserName()+")");
			request.setAttribute(getErrorMessageAttrName(), "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case SystemErrorCode.SYSTEM_USER_USERCODE_ALREADYEXISTS:
				this.request.setAttribute(UserAction.getErrorMessageAttrName(), "用户编号已经存在");
				break;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到修改用户页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.user = this.userService.getUser(this.id);
		if (null == this.user) {
			request.setAttribute(UserAction.getErrorMessageAttrName(), "用户不存在，可能已经被删除");
			return this.list();
		}
		
		return SUCCESS;
	}

	/**
	 * 保存修改用户信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		User u = this.userService.getUser(this.user.getUserId());
		if (null == u) {
			request.setAttribute(UserAction.getErrorMessageAttrName(), "用户不存在，可能已经被删除");
		} else {
			u.setExpiredTime(this.user.getExpiredTime());
			u.setPhone(this.user.getPhone());
			
			boolean  adminUser = u.getUserCode().toUpperCase().equals("ADMIN") ||
			u.getUserName().equals("超级管理员");
			
			
			if ( adminUser  ){
			   if  ( this.user.getState().equals(0) ){
				   request.setAttribute(UserAction.getErrorMessageAttrName(), "超级管理员帐号不能停用,操作失败!");
				   return this.list();
			   }
			}
			
		    u.setState(this.user.getState());				
			u.setUserDesc(this.user.getUserDesc());
			u.setUserName(this.user.getUserName());
			this.user = u;
			this.userService.editUser(this.user);
			OperationLogger.addLog(request, OperationType.ADD_DATA, "修改用户("+user.getUserName()+")");
			request.setAttribute(UserAction.getErrorMessageAttrName(), "操作成功");
			
		}
		return  this.list();
		
	}

	/**
	 * 跳转到用户详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.user = this.userService.getUser(this.id);
		if (null == this.user) {
			request.setAttribute(UserAction.getErrorMessageAttrName(), "用户不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.user = this.userService.getUser(this.id);
		
		try {
			this.userService.deleteUser(this.user);
			OperationLogger.addLog(request, OperationType.ADD_DATA, "删除用户("+user.getUserName()+")");
		} catch (BusinessException ex) {
		    if ( ex.getErrorCode() == SystemErrorCode.SYSTEM_ADMIN_CANNOT_DELETE )  {
		    	request.setAttribute(UserAction.getErrorMessageAttrName(), "超级管理员帐号不允许删除,操作失败！");
		    	return this.list();
		    }
		}
		
		request.setAttribute(UserAction.getErrorMessageAttrName(), "操作成功");
		return this.list();
	}

	/**
	 * 跳转到编辑用户角色页面
	 * 
	 * @return 结果页面
	 */
	public String editRole() {
		return SUCCESS;
	}

	/**
	 * 保存用户角色
	 * 
	 * @return 结果页面
	 */
	public String editRoleSave() {
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	public String modPwd() {
		LoginInfo loginInfo = (LoginInfo)request.getSession(true).getAttribute(LoginAction.SESSION_LOGIN_INFO);
		if(null == loginInfo){
			request.setAttribute(ERROR_MESSAGE_Attr_name, "当前用户信息丢失，请重新登录");
			return INPUT;
		}
		this.user = loginInfo.getUser();
		this.user.setPassword(null); //清空原始密码值，以便不显示
		return SUCCESS;
	}
	
	/**
	 * 保存密码修改
	 * @return
	 */
	public String modPwdSave() {
		User pUser = this.userService.getUser(this.user.getUserId());
		if (null == pUser) {
			request.setAttribute(ERROR_MESSAGE_Attr_name, "用户可能已经被删除，密码无法修改");
			return INPUT;
		}	
		PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		String password = 	passwordEncoder.encodePassword(this.user.getPassword(), null);
		if(!password.equals(pUser.getPassword())){
			request.setAttribute(ERROR_MESSAGE_Attr_name, "原始密码输入错误");
			this.user = pUser;
			this.user.setPassword(null);
			return INPUT;
		}
		
		this.user = pUser;		
		this.user.setPassword(passwordEncoder.encodePassword(newPwd, null));	
		this.userService.editUser(this.user);
		OperationLogger.addLog(request, OperationType.ADD_DATA, "用户修改密码("+user.getUserName()+")");
		request.setAttribute(ERROR_MESSAGE_Attr_name, "密码修改成功");
		return SUCCESS;
	}
	
	/**
	 * 分配角色
	 * @return
	 */
	public String editRoles() {
		this.user = this.userService.getUser(this.id);
		if (null == this.user) {
			request.setAttribute(ERROR_MESSAGE_Attr_name, "用户不存在，可能已经被删除");
			return INPUT;
		}
		
		StringBuilder builder = new StringBuilder();
		Set<Role> roles = user.getRoles();
		for(Role role : roles){
			builder.append(role.getRoleId() + ",");
		}
		if(builder.length() > 1){
			builder.deleteCharAt(builder.length()-1);
		}
		this.request.setAttribute("ownedRoleIds", builder.toString());
		this.request.setAttribute("allRoles", this.roleService.getAllRoles());
		
		return SUCCESS;
	}
	
	/**
	 * 保存角色分配
	 * @return
	 */
	public String editRolesSave(){
		this.user = this.userService.getUser(this.user.getUserId());
		if (null == this.user) {
			request.setAttribute(ERROR_MESSAGE_Attr_name, "用户不存在，可能已经被删除");
		}
		Set<Role> roles = new HashSet<Role>();
		for(int i=0, len=roleIds.length; i<len; i++){
			Role role = new Role();
			role.setRoleId(roleIds[i]);
			roles.add(role);
		}
		this.user.setRoles(roles);
		this.userService.editUser(user);
		OperationLogger.addLog(request, OperationType.ADD_DATA, "角色用户分配("+user.getUserName()+")");
		request.setAttribute(ERROR_MESSAGE_Attr_name, "角色分配成功");
		return this.list();
	}
	
	/**
	 * 查询模型
	 * 
	 * @version v1.0
	 * @author 
	 */
	class QueryModel {

		/** 用户编号 */
		private String userCode;

		/** 用户名称 */
		private String userName;

		/** 状态 */
		private Integer state = -1;

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
		 * 获取 状态
		 * 
		 * @return 状态
		 */
		public Integer getState() {
			return this.state;
		}

		/**
		 * 设置 状态
		 * 
		 * @param state
		 *            状态
		 */
		public void setState(Integer state) {
			this.state = state;
		}

	}
}
