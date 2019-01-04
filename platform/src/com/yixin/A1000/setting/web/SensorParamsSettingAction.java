/*
 * Project platform
 *
 * Class SensorParamsAction.java
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
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.DeviceParameterDetail;
import com.yixin.A1000.setting.service.DeviceParameterSettingService;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;


public class SensorParamsSettingAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 6104609990631717123L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** request域对象 */
	private HttpServletRequest request;

	
	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	
	/** 要进行操作的监测装置ID */
    private String id;

    
    /** 参数设置业务接口**/                
	private DeviceParameterSettingService deviceParamSettingService;


	/** 参数明细列表 **/
	private List<DeviceParameterDetail> deviceParameterdetails = new ArrayList<DeviceParameterDetail>();
	
	
	
	//召测时间，即最后一次上报时间
	private String  updateParamDate;

	
	//选择操作的数组ID
	private String[] ids;

	
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

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public void setDeviceParamSettingService(
			DeviceParameterSettingService deviceParamSettingService) {
		this.deviceParamSettingService = deviceParamSettingService;
	}

	/**
	 * 获取 要进行操作的监测装置ID
	 * 
	 * @return 要进行操作的监测装置ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 要进行操作的监测装置ID
	 * 
	 * @param id
	 *            要进行操作的监测装置ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * 设置 监测装置业务逻辑接口
	 * 
	 * @param sensorService
	 *            监测装置业务逻辑接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}


	/**
	 * 从数据库中读监测装置参数
	 * 
	 * @return 结果页面
	 */
	public String getDbSensorParams() {
	
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
	
		//获取CMD最后一次上报的参数
		DeviceParameter deviceParameter = this.deviceParamSettingService.getLastConfigSensorParams(sensor);
	    
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if ( null != deviceParameter ){
			
			if (! deviceParameter.getDeviceParameterDetails().equals(null)    ){
			   String d1 =  fd.format(deviceParameter.getCreateTime());
			   this.updateParamDate = d1;
			   this.deviceParameterdetails =  deviceParameter.getDeviceParameterDetails();
			}
		}
		
		request.setAttribute("sensorCode",sensor.getSensorCode());
		request.setAttribute("id",this.id);
			
		return SUCCESS;
	}

	/**
	 * 召测监测装置参数
	 * 
	 * @return 结果页面
	 */          
	public String readSensorParams() {
		
		Sensor sensor = this.checkSensor();
		request.setAttribute("id",this.id);
		
		if (null == sensor) {
			return INPUT;
		}
		
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		this.deviceParamSettingService.ReadSensorPareter(user, sensor);

		request.setAttribute("sensorCode",sensor.getSensorCode());
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
		return SUCCESS;
		
	}

	/**
	 * 下发测装置参数
	 * 
	 * @return 结果页面
	 */
	public String writeSensorParam() {
	
		Sensor sensor = this.checkSensor();
		request.setAttribute("id",this.id);	
	
		if (null == sensor) {
		   return INPUT;
		}
		

		if ( null == ids || 0 == ids.length  ){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请选择要下发的参数!");
		    return INPUT;		
		}
		
		
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		
		//生成新的参数下发参数
		UUID uuid = UUID.randomUUID();
		String suuid = uuid.toString();
		DeviceParameter deviceParameter  = new DeviceParameter();
		deviceParameter.setDeviceParameterId(suuid); //手动设置ID
		deviceParameter.setSensor(sensor);   //设置其监测装置对象
		deviceParameter.setCreateTime(new Date());
		deviceParameter.setState(0);//新参数，0：未下发
		
		//设置从表数据对应的主表参数
		int i = 1;
		for ( DeviceParameterDetail detail:deviceParameterdetails ){
			detail.setDeviceParameter(deviceParameter);
			detail.setOrderNumber(i);
			i++;
		}
	
		deviceParameter.setDeviceParameterDetails(deviceParameterdetails); //设置从表参数
		
		//业务处理
		this.deviceParamSettingService.WriteSensorPareter(user,deviceParameter);
        
		request.setAttribute("sensorCode",sensor.getSensorCode());
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
		
		return  SUCCESS;
		
	}

	private Sensor checkSensor() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return null;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
		return sensor;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	



	
	
}
