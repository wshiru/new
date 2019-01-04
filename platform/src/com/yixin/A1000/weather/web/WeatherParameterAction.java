/*
 * Project platform
 *
 * Class DeviceSettingsAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:55:35
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideParameterDetail;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.Param;
import com.yixin.A1000.weather.model.WeatherParameter;
import com.yixin.A1000.weather.service.WeatherParameterService;

/**
 * 终端参数配置Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WeatherParameterAction extends ActionSupport implements
		ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 1864168741639388450L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;


	/** 微气象参数配置业务逻辑接口 */
	private WeatherParameterService weatherParameterService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 要进行操作的监测代理ID */
	private String id;

	/** 微气象参数 **/
	private WeatherParameter weatherParameter;

 

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
	 * 设置  微气象参数配置业务逻辑接口
	 * 
	 * @param weatherParameterService
	 *             微气象参数配置业务逻辑接口
	 */
	public void setWeatherParameterService(
			WeatherParameterService weatherParameterService) {
		this.weatherParameterService = weatherParameterService;
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
	 * 设置 微气象参数参数
	 * 
	 * @param weatherParameter
	 */
	public void setWeatherParameter(WeatherParameter weatherParameter) {
		this.weatherParameter = weatherParameter;
	}

	/**
	 * 取得  微气象参数
	 * 
	 * @return
	 */
	public WeatherParameter getWeatherParameter() {
		return weatherParameter;
	}

	/**
	 * 检查所要操作的监测装置是否存在。存在则返回该对象，否则返回null
	 * 
	 * @return 存在则返回Sensor对象，否则返回null
	 */
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

	private String gotoPage() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		return SUCCESS;
	}

	// ============================================================================================

 
	/**
	 * 风速转换参数
	 */
	public String windParam() {
		Sensor sensor = checkSensor();
		this.weatherParameter = this.weatherParameterService.getWeatherParameterBySensor(sensor);
		return SUCCESS;
	}

	/**
	 * 保存风速转换参数
	 * 
	 * @return
	 */
	public String saveWindParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		
		WeatherParameter wp = this.weatherParameterService.getWeatherParameterBySensor(sensor);
		if(wp == null){
			this.weatherParameter.setSensor(sensor);
			weatherParameterService.addWeatherParameter(weatherParameter);
		}else{
			wp.setMountingHeight(this.weatherParameter.getMountingHeight());
			wp.setDesignHeight(this.weatherParameter.getDesignHeight());
			wp.setWindCoefficient(this.weatherParameter.getWindCoefficient());
			this.weatherParameter  = wp;
			weatherParameterService.editWeatherParameter(weatherParameter);
		}	
		request.setAttribute("sensor", sensor);
		request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "保存成功");
		return SUCCESS;
	}

	 
}