/*
 * Project platform
 *
 * Classname BaseAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-15 上午10:00
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.framework.base.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.dao.DataIntegrityViolationException;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
import com.yixin.framework.util.SystemConfig;

/**
 * Action基类
 * @version v1.0.0
 * @author 
 *
 */
public class BaseAction<T extends Object> extends ActionSupport implements ServletRequestAware {

	/** 序列版本ID  */
	private static final long serialVersionUID = 863182676837250263L;
	
	/** 操作失败的消息键名  */
	public final static String ERROR_MESSAGE = "errorMessage";
	/** 操作成功的消息键名  */
	public final static String SUCCESS_MESSAGE = "succMessage";
	
	/** 页码键名  */
	private final static String PAGE_NO_NAME = "pn";
	/** 页面大小键名  */
	private final static String PAGE_SIZE_NAME = "ps";
	
	/** 缺省页码  */
	private final static long DEFAULT_PAGE_NO = 1;
	/** 缺省页面大小  */
	private final static long DEFAULT_PAGE_SIZE = SystemConfig.PAGE_SIZE;
	/** 缺省数据键名  */
	protected final static String PAGE_DATA_NAME = "pageData";	
	
	/** 页面数据 */
	protected Page<T> page;

	/** request请求对象  */
	protected HttpServletRequest request;
	
	/**
	 * 设置request对象
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	/**
	 * 数据列表
	 * @return
	 */
	public String list() {
		if(null == page)
			page = new Page<T>(1, 20, 0, null);
		request.setAttribute(PAGE_DATA_NAME, page);	
		initData();
		return SUCCESS;
	}
	/**
	 * 保存或更新数据（适合自动生成ID的类型）
	 * @return
	 */
	public String saveOrUpdate() {
		Object id = getId();
		try{
			if(null == id) {
				return save();
			}
			else if (id.getClass().equals(String.class)) {
				String idStr = (String)id;
				if(idStr.isEmpty())
					return save();
				else
					return checkExist(false)? update() : SUCCESS;
			}
			else if(id.getClass().equals(Object.class)){
				return SUCCESS;
			}
			else {
				return checkExist(false)? update() : SUCCESS;
			}
		}catch (BusinessException e) {
			switch (e.getErrorCode()) {
				case ErrorCode.CONFLICT:
					setErrorMsg("该数据已存在（编号或名称冲突）");
					break;
				default:
					break;
			}
			initData();
			return INPUT;
		}
	}
	/**
	 * 进入添加页面
	 * @return
	 */
	public String add(){
		initData();
		return INPUT;
	}
	/**
	 * 保存新数据
	 * @return
	 */
	public String save(){
		return SUCCESS;
	}
	/**
	 * 进入修改页面
	 * @return
	 */
	public String edit(){
		if(checkExist(true)){
			initData();
			return INPUT;
		}
		else {
			return list(); //返回list页
		}		
	}
	/**
	 * 更新数据
	 * @return
	 */
	public String update(){
		return SUCCESS;
	}
	/**
	 * 删除数据
	 * @return
	 */
	public String delete() {
		if(checkExist(true)){
			try {
				rawDelete();
			} catch (BusinessException e) {
				switch (e.getErrorCode()) {
					case ErrorCode.IN_USE:
						setErrorMsg("该数据正被使用，无法删除");
						break;
					default:
						break;				
				}
			} catch (DataIntegrityViolationException e) {
				setErrorMsg("该数据正被使用，无法删除");
			}
		}
		return list();
	}
	/**
	 * 直接删除数据
	 */
	protected void rawDelete() {
		
	}
	
	/**
	 * 获取实体数据ID, 一般要重写
	 * @return
	 */
	protected Object getId(){
		return new Object();
	}
	/**
	 * 根据实体Id获取持久实体, 一般需要重写
	 * @return
	 */
	protected T getPersistenceById(){
		return null;
	}
	/**
	 * 将持久状态赋给实体对象，一般需要重写
	 */
	protected void setEntityValues(T entity){

	}
	/**
	 * 判断所选记录是否仍存在
	 * @param overwrite
	 * 			是否用持久对象替换当前（临时）对象
	 * @return
	 */
	protected boolean checkExist(boolean overwrite) {
		T t = getPersistenceById();
		if(null == t){
			setErrorMsg("该记录可能已被删除");
			return false;
		}
		if(overwrite)
			setEntityValues(t);
		return true;
	}
	/**
	 * 初始化页面数据
	 */
	protected void initData() {
		
	}
	
	/**
	 * 获取页码
	 * @return
	 */
	protected long getPageNo() {
		String pageNo = request.getParameter(PAGE_NO_NAME);
		if(null == pageNo || pageNo.isEmpty())
			return DEFAULT_PAGE_NO;
		else
			return Long.parseLong(pageNo);		
	}
	/**
	 * 获取页面大小
	 * @return
	 */
	protected long getPageSize() {
		String pageSize = request.getParameter(PAGE_SIZE_NAME);
		if(null == pageSize || pageSize.isEmpty())
			return DEFAULT_PAGE_SIZE;
		else
			return Long.parseLong(pageSize);
	}
	
	/**
	 * 设置成功消息
	 * @param msg
	 */
	protected void setSuccessMsg(String msg) {
		request.setAttribute(SUCCESS_MESSAGE, msg);
	}
	/**
	 * 设置错误消息
	 * @param msg
	 */
	protected void setErrorMsg(String msg) {
		request.setAttribute(ERROR_MESSAGE, msg);
	}
}
