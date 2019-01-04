/*
 * Project platform
 *
 * Class RealTimeContaminationAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-8 下午04:23:44
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.contamination.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.ReceiveDataException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.contamination.service.ContaminationSamplingService;
import com.yixin.A1000.contamination.service.RealTimeContaminationService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;

/**
 * 污秽度监测数据实时召测的Action处理类
 * 
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeContaminationAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 4433270820058276772L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 污秽度监测数据召测业务接口 */
	private RealTimeContaminationService realTimeContaminationService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 污秽度监测业务接口 */
	private ContaminationSamplingService contaminationSamplingService;

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
	 * 设置 污秽度监测数据召测业务接口
	 * 
	 * @param realTimeContaminationService
	 *            污秽度监测数据召测业务接口
	 */
	public void setRealTimeContaminationService(RealTimeContaminationService realTimeContaminationService) {
		this.realTimeContaminationService = realTimeContaminationService;
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
	 * 设置 污秽度监测业务接口
	 * 
	 * @param contaminationSamplingService
	 *            污秽度监测业务接口
	 */
	public void setContaminationSamplingService(ContaminationSamplingService contaminationSamplingService) {
		this.contaminationSamplingService = contaminationSamplingService;
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
	 * 
	 * @return 结果页面
	 */
	public String realTime() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		/*
		ContaminationSampling lastData = this.contaminationSamplingService.getLastContamination(sensor);
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
		*/
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
		this.realTimeContaminationService.addRealTimeTask(user, sensor);
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

	public String samplingContamination() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		try {
			ContaminationSampling contamination = this.realTimeContaminationService.samplingContamination(sensor);
			request.setAttribute("contamination", contamination);
		} catch (TimeOutException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器未响应");
		} catch (ClosedConnectionException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "与服务器间未建立连接，或者连接已经断开");
		} catch (SendDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "向服务器写数据时发生错误");
		} catch (ProtocolException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, e.getMessage());
		} catch (TCPServerException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器返回错误信息：\\r\\n" + e.getMessage());
		} catch (ReceiveDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "从服务器接收数据发生错误");
		} catch (Exception e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "系统发生内部错误\\r\\n" + e.getMessage());
		}
		return SUCCESS;
	}
}