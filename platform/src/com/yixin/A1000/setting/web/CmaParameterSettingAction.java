/*
 * Project platform
 *
 * Classname CmaParameterSettingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-10-24
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.web;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.service.DeviceParameterSettingService;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;

/**
 * 监测代理参数子模块action处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaParameterSettingAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 2403549766480244491L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** request域对象 */
	private HttpServletRequest request;
	
	private DeviceParameterSettingService deviceParameterSettingService;
	
	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;
	
	/** 监测代理参数。保存新增线路提交的数据，及保存到request域的线路信息等 */
	private DeviceParameter cmaParam;

	/** 要进行操作的监测代理ID */
	private String id;
	
	/**
	 * @param deviceParameterSettingService the cmaParameterSettingService to set
	 */
	public void setCmaParameterSettingService(DeviceParameterSettingService deviceParameterSettingService) {
		this.deviceParameterSettingService = deviceParameterSettingService;
	}
	/**
	 * 设置 监测装置业务逻辑接口
	 *
	 * @param sensorService 监测装置业务逻辑接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}
	/**
	 * 获取 监测代理参数。保存新增线路提交的数据，及保存到request域的线路信息等
	 * 
	 * @return 监测代理参数。保存新增线路提交的数据，及保存到request域的线路信息等
	 */
	public DeviceParameter getCmaParam() {
		return this.cmaParam;
	}

	/**
	 * 设置 监测代理参数。保存新增线路提交的数据，及保存到request域的线路信息等
	 * 
	 * @param cmaParam
	 *            监测代理参数。保存新增线路提交的数据，及保存到request域的线路信息等
	 */
	public void setCmaParam(DeviceParameter cmaParam) {
		this.cmaParam = cmaParam;
	}

	/**
	 * 获取 要进行操作的监测代理ID
	 * 
	 * @return 要进行操作的监测代理ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 要进行操作的监测代理ID
	 * 
	 * @param id
	 *            要进行操作的监测代理ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 从数据库中读监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String getDbCmaParam() {
		return SUCCESS;
	}

	/**
	 * 召测监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String readCmaParam() {
		CmaInfo cma = this.checkCma();
		if (null == cma) {
			return INPUT;
		}
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		//this.deviceParameterSettingService.readCmaParams(user, cma);
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
		return SUCCESS;
	}

	/**
	 * 读监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String writeCmaParam() {
		CmaInfo cma = this.checkCma();
		if (null == cma) {
			return INPUT;
		}
		this.cmaParam.setCmaInfo(cma);
		User user = ((LoginInfo)request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
//		User user = new User();
//		user.setUserId("1");
		//this.deviceParameterSettingService.addCmaParams(user, this.cmaParam);
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
		return this.getDbCmaParam();
	}

	private CmaInfo checkCma() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return null;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
//		CmaInfo cma = this.cmaInfoService.getCmaInfo(this.id);
//		if (null == cma) {
//			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
//			return null;
//		}
		return sensor.getCmaInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		CmaInfo cma = this.checkCma();
		if (null == cma) {
			return;
		}
		request.setAttribute("cma", cma);
		
		//得取最后与监测代理同步的CMA参数 显示在页面上
		//this.cmaParam = this.deviceParameterSettingService.getDbCmaParams(cma);
		
	    //CMA未完成下发的任务
		//Map<String, Object> map = this.deviceParameterSettingService.getCmaParamsUnfinishedTasks(cma);
		//request.setAttribute("unfinishedTasks", map);
	
		//未完成召测的任务
		//request.setAttribute("unfinishedReadTaskConfig", this.deviceParameterSettingService.getUnfinishedReadTaskConfig(cma));
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
