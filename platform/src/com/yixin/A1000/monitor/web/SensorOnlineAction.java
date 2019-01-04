/*
 * Project platform
 *
 * Classname CmaOnlineAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-07-07 11:50
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.monitor.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.ReceiveDataException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.monitor.service.OnlineDeviceStatusService;
import com.yixin.A1000.setting.service.SensorHeartbeatInfoService;
import com.yixin.framework.base.model.Page;

/**
 * 在线CMA查询Action类
 * 
 * 
 * @version v1.0.0
 * @author
 */
public class SensorOnlineAction extends ActionSupport implements
		ServletRequestAware {

	/** 序列版本ID */
	private static final long serialVersionUID = 664104606600888188L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	// /** 用于保存在request域的成功提示信息 */
	// private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 在线监测装置集键名 */
	private static final String ONLINE_SENSOR_LIST = "OnlineSensorList";

	/** 监测装置业务接口 */
	private SensorService sensorService;
	/** 监测装置心跳业务接口 */
	private SensorHeartbeatInfoService sensorHeartbeatInfoService;

	/** 在线终端状态业务逻辑接口 */
	private OnlineDeviceStatusService onlineDeviceStatusService;
	
	/** 监测类型 **/
	private SensorTypeService sensorTypeService;

	/** 监测装置总数 */
	private long totalCount = 0;
	/** 监测装置在线数 */
	private long onlineCount = 0;
	/** 监测装置在线率 */
	private String onlineRate = "100";

	/** 是否显示在线装置 */
	private Boolean isShowOnline = true;
	
	private Date beginTime;
	private Date endTime;

	/**
	 * 获取监测装置总数
	 * 
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取监测装置在线数
	 * 
	 * @return
	 */
	public long getOnlineCount() {
		return onlineCount;
	}

	/**
	 * 获取监测装置在线率
	 * 
	 * @return
	 */
	public String getOnlineRate() {
		return onlineRate;
	}

	/**
	 * 设置监测装置服务
	 * 
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * 设置监测装置心跳服务
	 * 
	 * @param sensorHeartbeatInfoService
	 */
	public void setSensorHeartbeatInfoService(
			SensorHeartbeatInfoService sensorHeartbeatInfoService) {
		this.sensorHeartbeatInfoService = sensorHeartbeatInfoService;
	}
	/**
	 * 设置监测类型服务
	 * 
	 * @param sensorTypeService
	 */
	public void setSensorTypeService(
			SensorTypeService sensorTypeService) {
		this.sensorTypeService = sensorTypeService;
	}

	/**
	 * 设置 在线终端状态业务逻辑接口
	 * 
	 * @param onlineDeviceStatusService
	 *            在线终端状态业务逻辑接口
	 */
	public void setOnlineDeviceStatusService(
			OnlineDeviceStatusService onlineDeviceStatusService) {
		this.onlineDeviceStatusService = onlineDeviceStatusService;
	}

	/**
	 * 获取是否显示在线装置
	 * 
	 * @return
	 */
	public Boolean getIsShowOnline() {
		return isShowOnline;
	}

	/**
	 * 设置是否显示在线装置
	 * 
	 * @param isShowOnline
	 */
	public void setIsShowOnline(Boolean isShowOnline) {
		this.isShowOnline = isShowOnline;
	}
	
	
	

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * 查询在线监测装置
	 * 
	 * @return
	 */
	public String list() {
		List<Sensor> allsSensors = this.sensorService.getAllSensors();
		if (null != allsSensors) {
			totalCount = allsSensors.size();
			List<Sensor> resultSensors = new ArrayList<Sensor>();
			for (Sensor sensor : allsSensors) {
				if (isShowOnline.equals(this.sensorHeartbeatInfoService
						.checkOnline(sensor))) {
					resultSensors.add(sensor);
				}
			}
			onlineCount = isShowOnline ? resultSensors.size() : totalCount
					- resultSensors.size();
			if (totalCount != 0) {
				DecimalFormat dFormat = new DecimalFormat("#0.00");
				onlineRate = dFormat
						.format(((double) onlineCount / totalCount) * 100);
			}
			ActionContext.getContext().put(ONLINE_SENSOR_LIST, resultSensors);
		}
		return SUCCESS;
	}

	public String onlineRateStatistics() {
		long pageNo = 1 ;
		long pageSize = 20;
		
		String sPageNo = request.getParameter("pn");
		if(!(null == sPageNo || sPageNo.isEmpty())){
			pageNo = Long.parseLong(sPageNo);
		}
				
		String sPageSize = request.getParameter("ps");
		if(!(null == sPageSize || sPageSize.isEmpty())){
			pageSize = Long.parseLong(sPageSize);
		}
		
		if(beginTime == null){
						
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());		
			calendar.add(Calendar.DATE, -60) ;
			beginTime = calendar.getTime();
		}
		
		if(endTime == null){
			endTime = new Date();			
		}
		
		String sensorTypeId = request.getParameter("sensorTypeId"); 
		 
		
		Page<Map<String,Object>> mpage = this.onlineDeviceStatusService.getPageOnlineRateStatistics(
				beginTime, endTime,sensorTypeId, pageNo, pageSize);
		List<SensorType> sensorTypes = sensorTypeService.getAllSensorTypes();
		request.setAttribute("page", mpage);
		request.setAttribute("sensorTypes", sensorTypes);
		request.setAttribute("sensorTypeId", sensorTypeId);
		return SUCCESS;

	}

	/**
	 * 判断是否在线
	 * 
	 * @param sensor
	 * @param onlineDevices
	 * @return
	 */
	private boolean checkOnlineDeviceStatus(Sensor sensor,
			List<OnlineDeviceStatus> onlineDevices) {
		for (OnlineDeviceStatus on : onlineDevices) {
			if (null != on.getSensor()
					&& sensor.getSensorCode().equalsIgnoreCase(
							on.getSensor().getSensorCode())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 在线监测装置
	 * 
	 * @return
	 */
	public String onlineDeviceStatus() {
		List<Sensor> allsSensors = this.sensorService.getAllSensors();
		if (null != allsSensors) {
			totalCount = allsSensors.size();
			List<Sensor> resultSensors = new ArrayList<Sensor>();
			onlineCount = isShowOnline ? resultSensors.size() : totalCount
					- resultSensors.size();
			if (totalCount != 0) {
				DecimalFormat dFormat = new DecimalFormat("#0.00");
				onlineRate = dFormat
						.format(((double) onlineCount / totalCount) * 100);
			}
			ActionContext.getContext().put(ONLINE_SENSOR_LIST, resultSensors);
		}
		return SUCCESS;
	}

	/**
	 * 在线监测装置
	 * 
	 * @return
	 */
	public String upOnlineDeviceStatus() {
		List<Sensor> allsSensors = this.sensorService.getAllSensors();
		try {
			List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService
					.getOnlineDeviceStatus();
			if (null != allsSensors) {
				totalCount = allsSensors.size();
				List<Sensor> resultSensors = new ArrayList<Sensor>();
				for (Sensor sensor : allsSensors) {
					if (isShowOnline.equals(this.checkOnlineDeviceStatus(
							sensor, onlineDevices))) {
						resultSensors.add(sensor);
					}
				}
				onlineCount = isShowOnline ? resultSensors.size() : totalCount
						- resultSensors.size();
				if (totalCount != 0) {
					DecimalFormat dFormat = new DecimalFormat("#0.00");
					onlineRate = dFormat
							.format(((double) onlineCount / totalCount) * 100);
				}
				ActionContext.getContext().put(ONLINE_SENSOR_LIST,
						resultSensors);
			}
			request.setAttribute("onlineDevices", onlineDevices);
		} catch (TimeOutException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器未响应");
		} catch (ClosedConnectionException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "与服务器间未建立连接，或者连接已经断开");
		} catch (SendDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "向服务器写数据时发生错误");
		} catch (ProtocolException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, e.getMessage());
		} catch (TCPServerException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器返回错误信息：\\r\\n"
					+ e.getMessage());
		} catch (ReceiveDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "从服务器接收数据发生错误");
		} catch (Exception e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME,
					"系统发生内部错误\\r\\n" + e.getMessage());
		}
		return SUCCESS;
	}
}
