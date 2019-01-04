/*
 * Project platform
 *
 * Class RealTimeWireTemperatureAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-8 下午04:26:12
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiretemperature.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.model.TaskConfigQueryModel;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;
import com.yixin.A1000.wiretemperature.service.RealTimeWireTemperatureService;
import com.yixin.A1000.wiretemperature.service.WireTemperatureSamplingService;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;

/**
 * 导线温度数据实时召测的Action处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeWireTemperatureAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 554323906408619242L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 导线温度数据召测业务接口 */
	private RealTimeWireTemperatureService realTimeWireTemperatureService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 导线温度业务接口 */
	private WireTemperatureSamplingService wireTemperatureSamplingService;

	/** 任务业务接口 */
	private TaskConfigService taskConfigService;

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
	 * 设置 导线温度数据召测业务接口
	 * 
	 * @param realTimeWireTemperatureService
	 *            导线温度数据召测业务接口
	 */
	public void setRealTimeWireTemperatureService(RealTimeWireTemperatureService realTimeWireTemperatureService) {
		this.realTimeWireTemperatureService = realTimeWireTemperatureService;
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
	 * 设置 导线温度业务接口
	 * 
	 * @param wireTemperatureSamplingService
	 *            导线温度业务接口
	 */
	public void setWireTemperatureSamplingService(WireTemperatureSamplingService wireTemperatureSamplingService) {
		this.wireTemperatureSamplingService = wireTemperatureSamplingService;
	}

	/**
	 * 设置 任务业务接口
	 * 
	 * @param taskConfigService
	 *            任务业务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
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
	 * 从数据库中读监测代理参数
	 * 
	 * @return 结果页面
	 */
	public String getDbCmaParams() {
		return SUCCESS;
	}

	/**
	 * 跳转至数据召测页面
	 * 
	 * @return 结果页面
	 */
	public String realTime() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		WireTemperatureSampling lastData = this.wireTemperatureSamplingService.getLastWireTemperature(sensor);
		TaskConfigQueryModel taskConfigQueryModel = new TaskConfigQueryModel();
		taskConfigQueryModel.setCmdType(ProtocolCmdType.REALTIME);
		taskConfigQueryModel.setCmdTypeQueryMode(StringQueryMode.EQ);
		taskConfigQueryModel.setSensorCode(sensor.getSensorCode());
		taskConfigQueryModel.setSensorCodeQueryMode(StringQueryMode.EQ);
		taskConfigQueryModel.setState(0);
		taskConfigQueryModel.setStateQueryMode(NumberQueryMode.EQ);
		taskConfigQueryModel.setSubCmdType(ProtocolCmdSubType.WEATHER);
		taskConfigQueryModel.setSubCmdTypeQueryMode(StringQueryMode.EQ);
		List<TaskConfig> unfinishedTasks = this.taskConfigService.getAllTaskConfigs(taskConfigQueryModel);
		TaskConfig unfinishedTask = null;
		if (unfinishedTasks.size() > 0) {
			unfinishedTask = unfinishedTasks.get(0);
		}
		Map<String, Object> realTimeDetail = new HashMap<String, Object>();
		realTimeDetail.put("lastData", lastData);
		realTimeDetail.put("unfinishedTask", unfinishedTask);
		request.setAttribute("realTimeDetail", realTimeDetail);
		return SUCCESS;
	}

	/**
	 * 新增数据召测任务
	 * 
	 * @return 结果页面
	 */
	public String addRealTimeTask() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		this.realTimeWireTemperatureService.addRealTimeTask(user, sensor);
		request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		return SUCCESS;
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
}