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
package com.yixin.A1000.contamination.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.contamination.model.ContaminationParameter;
import com.yixin.A1000.contamination.service.ContaminationParameterService;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.Param;
import com.yixin.A1000.settings.service.DeviceSettingsService;

/**
 * 污秽度监测参数配置Action类
 * 
 * @version v1.0.0
 * @author  
 */
public class ContaminationParameterAction extends ActionSupport implements
		ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 1864168741639388450L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 终端参数配置业务逻辑接口 */
	private DeviceSettingsService deviceSettingsService;

	/** 污秽度监测参数配置业务逻辑接口 */
	private ContaminationParameterService contaminationParameterService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 要进行操作的监测代理ID */
	private String id;

	/** 污秽度监测参数参数 **/
	private ContaminationParameter contaminationParameter;

	/** 参数。终端报警阈值、终端模型参数等 */
	private List<Param> params = new ArrayList<Param>();

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
	 * 设置 终端参数配置业务逻辑接口
	 * 
	 * @param deviceSettingsService
	 *            终端参数配置业务逻辑接口
	 */
	public void setDeviceSettingsService(
			DeviceSettingsService deviceSettingsService) {
		this.deviceSettingsService = deviceSettingsService;
	}

	/**
	 * 设置 污秽度监测参数配置业务逻辑接口
	 * 
	 * @param contaminationParameterService
	 *            污秽度监测参数配置业务逻辑接口
	 */
	public void setContaminationParameterService(
			ContaminationParameterService contaminationParameterService) {
		this.contaminationParameterService = contaminationParameterService;
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 获取 参数
	 * 
	 * @return 参数
	 */
	public List<Param> getParams() {
		return this.params;
	}

	/**
	 * 设置 参数
	 * 
	 * @param params
	 *            参数
	 */
	public void setParams(List<Param> params) {
		this.params = params;
	}

	/**
	 * 设置 污秽度监测参数参数
	 * 
	 * @param contaminationParameter
	 */
	public void setContaminationParameter(ContaminationParameter contaminationParameter) {
		this.contaminationParameter = contaminationParameter;
	}

	/**
	 * 取得 污秽度监测参数参数
	 * 
	 * @return
	 */
	public ContaminationParameter getContaminationParameter() {
		return contaminationParameter;
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
	 * 传感器参数配置
	 * 
	 * @return
	 */
	public String sensorParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.contaminationParameter = contaminationParameterService.getContaminationParameterBySensor(sensor);
		return SUCCESS;
	}
	  

	/**
	 * 保存传感器参数配置
	 * @return
	 */
	public String saveSensorParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		ContaminationParameter tp =  contaminationParameterService.getContaminationParameterBySensor(sensor);
		if(tp == null){
			tp = new ContaminationParameter();
			tp.setSensor(sensor);
			tp.setAngleXAlarm1(contaminationParameter.getAngleXAlarm1());
			tp.setAngleXAlarm2(contaminationParameter.getAngleXAlarm2());
			tp.setAngleXAlarm3(contaminationParameter.getAngleXAlarm3());
			tp.setAngleYAlarm1(contaminationParameter.getAngleYAlarm1());
			tp.setAngleYAlarm2(contaminationParameter.getAngleYAlarm2());
			tp.setAngleYAlarm3(contaminationParameter.getAngleYAlarm3());
			contaminationParameterService.addContaminationParameter(tp);
		}else{
			tp.setAngleXAlarm1(contaminationParameter.getAngleXAlarm1());
			tp.setAngleXAlarm2(contaminationParameter.getAngleXAlarm2());
			tp.setAngleXAlarm3(contaminationParameter.getAngleXAlarm3());
			tp.setAngleYAlarm1(contaminationParameter.getAngleYAlarm1());
			tp.setAngleYAlarm2(contaminationParameter.getAngleYAlarm2());
			tp.setAngleYAlarm3(contaminationParameter.getAngleYAlarm3());
			contaminationParameterService.editContaminationParameter(tp);
		}

		return SUCCESS;
	}

	// ============================================================================================

	public String modelParam() {
		return this.gotoPage();
	}

	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询) 
	 * @return
	 */
	public String upModelParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.params.clear();
		ModelParam modelParam = null;

		modelParam = this.deviceSettingsService.upContaminationModelParam(sensor);			

		if(modelParam != null){
			for (String key : modelParam.getParams().keySet()) {
				Param p = new Param();
				p.setDesc(key);
				p.setValue(modelParam.getParams().get(key));
				this.params.add(p);
			}	
		}
		
		request.setAttribute("sensor", sensor);
		return SUCCESS;
	}

	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * @return
	 */
	public String downModelParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}

		ModelParam modelParam = new ModelParam();
		
		modelParam.setSensor(sensor);
		Map<String, String> map = new HashMap<String, String>();
		for (Param p : params) {
			map.put(p.getDesc(), p.getValue());
		}
		modelParam.setParams(map);
		if(map.size() == 0){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先召测再下发。");
			return INPUT;
		}		

		boolean result = false;
		result = this.deviceSettingsService.downContaminationModelParam(sensor, modelParam);		
		
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	
	// ============================================================================================
	/**
	 * 告警阀值参数
	 */
	public String alarmParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.contaminationParameter = contaminationParameterService.getContaminationParameterBySensor(sensor);		
		return this.gotoPage();
	}

	/**
	 * 召测告警阀值参数
	 * 
	 * @return
	 */
	public String upAlarmParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.params.clear();
		AlarmParam alarmParam = null;
		
		
		//根据不同同的传感器类型，配置参数
		alarmParam = this.deviceSettingsService.upContaminationAlarmParam(sensor);
				
		
		if(alarmParam!=null){
			for (String key : alarmParam.getParams().keySet()) {
				Param p = new Param();
				p.setDesc(key);
				p.setValue(alarmParam.getParams().get(key));
				this.params.add(p);
			}	
		}
		
		request.setAttribute("sensor", sensor);
		return SUCCESS;
	}

	/**
	 * 下发告警阀值参数
	 * @return
	 */
	public String downAlarmParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}

		AlarmParam alarmParam = new AlarmParam();
		alarmParam.setSensor(sensor);
		Map<String, String> map = new HashMap<String, String>();
		for (Param p : params) {
			map.put(p.getDesc(), p.getValue());
		}
		alarmParam.setParams(map);
		if(map.size() == 0){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先召测再下发。");
			return INPUT;
		}
		
		
		boolean result = this.deviceSettingsService.downContaminationAlarmParam(sensor, alarmParam);		
		
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

}