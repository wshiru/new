/*
 * Project platform
 *
 * Class CmaParamsAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-30 下午03:00:55
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.DeviceParameterDetail;
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

public class CmaParamsSettingAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -5688677844809459103L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;
	
	/** 参数设置业务接口**/                
	private DeviceParameterSettingService deviceParamSettingService;


	/** 参数明细列表 **/
	private List<DeviceParameterDetail> deviceParameterdetails = new ArrayList<DeviceParameterDetail>();
	
	
	
    //召测时间
	private String  updateParamDate ;
	
	private String[] ids;

	

	public DeviceParameterSettingService getDeviceParamSettingService() {
		return deviceParamSettingService;
	}

	public void setDeviceParamSettingService(
			DeviceParameterSettingService deviceParamSettingService) {
		this.deviceParamSettingService = deviceParamSettingService;
	}

	public List<DeviceParameterDetail> getDeviceParameterdetails() {
		return deviceParameterdetails;
	}

	public void setDeviceParameterdetails(
			List<DeviceParameterDetail> deviceParameterdetails) {
		this.deviceParameterdetails = deviceParameterdetails;
	}

	public String getUpdateParamDate() {
		return updateParamDate;
	}

	public void setUpdateParamDate(String updateParamDate) {
		this.updateParamDate = updateParamDate;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	/** 要进行操作的监测代理ID */
	private String id;

	
	/**
	 * 获取 要进行操作的监测代理ID
	 * 
	 * @return 要进行操作的监测代理ID
	 */
	public String getId() {
		return this.id;
	}
	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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
	 * 设置 监测装置业务逻辑接口
	 *
	 * @param sensorService 监测装置业务逻辑接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
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
	 * 显示页面
	 * @return
	 */
	public  String  getDbCmaParams(){
		
		CmaInfo cmaInfo = this.checkCmaInfo();
		if (null == cmaInfo ) {
			return INPUT;
		}
		
		//获取最后一次上报的参数
		DeviceParameter deviceParameter = this.deviceParamSettingService.getLastConfigCmaParams(cmaInfo);
	  
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if ( null != deviceParameter ){
			if (! deviceParameter.getDeviceParameterDetails().equals(null)    ){
				this.updateParamDate = fd.format(deviceParameter.getCreateTime());			
				this.deviceParameterdetails =  deviceParameter.getDeviceParameterDetails();
				
			}
		}
		
		request.setAttribute("id",this.id);
		request.setAttribute("cmaCode", cmaInfo.getCmaCode());
		
		return SUCCESS;
	}


	
	/**
	 * 召测监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String readCmaParam() {
		
		CmaInfo cmaInfo = this.checkCmaInfo();
		request.setAttribute("id",this.id);
			 	
		if (null == cmaInfo ) {
			return INPUT;
		}
		
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		this.deviceParamSettingService.ReadCmaPareter(user, cmaInfo);
       
		request.setAttribute("cmaCode", cmaInfo.getCmaCode());
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
    	
		return SUCCESS;
		
	}
	
	/**
	 * 下发监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String writeCmaParam() {
		
		CmaInfo cmaInfo = this.checkCmaInfo();
		request.setAttribute("id",this.id);	
			
		
		if (null == cmaInfo) {
			return INPUT;
		}
			
		if ( null == ids || 0 == ids.length  ){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请选择要下发的参数!");
		    return INPUT;		
		}
		
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		DeviceParameter deviceParameter  = new DeviceParameter();
		deviceParameter.setDeviceParameterId(suuid); //手动设置ID
		deviceParameter.setCmaInfo(cmaInfo);
		deviceParameter.setCreateTime(new Date());
		deviceParameter.setState(0);
		//设置从表数据对应的主表参数
		int i = 1;
		for ( DeviceParameterDetail detail:deviceParameterdetails ){
			detail.setDeviceParameter(deviceParameter);
			detail.setOrderNumber(i);
			i++;
		}
	
		deviceParameter.setDeviceParameterDetails(deviceParameterdetails);
		
		this.deviceParamSettingService.WriteCmaPareter(user,deviceParameter);
		
		request.setAttribute("cmaCode", cmaInfo.getCmaCode());  
	    request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
		return  SUCCESS;
	}

	
	private CmaInfo checkCmaInfo() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return null;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor ) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
		return sensor.getCmaInfo();
	}




	
}
