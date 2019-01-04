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
package com.yixin.A1000.settings.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.DeviceID;
import com.yixin.A1000.settings.model.DeviceReset;
import com.yixin.A1000.settings.model.DeviceReset.ResetMode;
import com.yixin.A1000.settings.model.DeviceTime;
import com.yixin.A1000.settings.model.MasterStation;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.NetworkAdapter;
import com.yixin.A1000.settings.model.Param;
import com.yixin.A1000.settings.model.RequestHistoryData;
import com.yixin.A1000.settings.model.SamplingParam;
import com.yixin.A1000.settings.service.DeviceSettingsService;

/**
 * 终端参数配置Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class DeviceSettingsAction extends ActionSupport implements ServletRequestAware {

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

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 要进行操作的监测代理ID */
	private String id;

	/** 终端时间 */
	private DeviceTime deviceTime;

	/** 状态监测装置网络适配器 */
	private NetworkAdapter networkAdapter;

	/** 状态监测装置采样参数 */
	private SamplingParam samplingParam;

	/** 上位机信息 */
	private MasterStation masterStation;

	/** 装置ID */
	private DeviceID deviceID;

	/** 参数。终端报警阈值、终端模型参数等 */
	private List<Param> params = new ArrayList<Param>();
	
	/** 请求历史数据 */
	private RequestHistoryData requestHistoryData ;

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
	public void setDeviceSettingsService(DeviceSettingsService deviceSettingsService) {
		this.deviceSettingsService = deviceSettingsService;
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
	 * 获取 状态监测装置网络适配器
	 * 
	 * @return networkAdapter 状态监测装置网络适配器
	 */
	public NetworkAdapter getNetworkAdapter() {
		return this.networkAdapter;
	}

	/**
	 * 设置 状态监测装置网络适配器
	 * 
	 * @param networkAdapter
	 *            状态监测装置网络适配器
	 */
	public void setNetworkAdapter(NetworkAdapter networkAdapter) {
		this.networkAdapter = networkAdapter;
	}

	/**
	 * 获取 状态监测装置采样参数
	 * 
	 * @return 状态监测装置采样参数
	 */
	public SamplingParam getSamplingParam() {
		return this.samplingParam;
	}

	/**
	 * 设置 状态监测装置采样参数
	 * 
	 * @param samplingParam
	 *            状态监测装置采样参数
	 */
	public void setSamplingParam(SamplingParam samplingParam) {
		this.samplingParam = samplingParam;
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
	 * 获取 装置ID
	 * 
	 * @return 装置ID
	 */
	public DeviceID getDeviceID() {
		return this.deviceID;
	}

	/**
	 * 获取 上位机信息
	 * 
	 * @return 上位机信息
	 */
	public MasterStation getMasterStation() {
		return this.masterStation;
	}

	/**
	 * 设置 上位机信息
	 * 
	 * @param masterStation
	 *            上位机信息
	 */
	public void setMasterStation(MasterStation masterStation) {
		this.masterStation = masterStation;
	}

	/**
	 * 设置 装置ID
	 * 
	 * @param deviceID
	 *            装置ID
	 */
	public void setDeviceID(DeviceID deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @param requestHistoryData 
	 * 	  设置    请求历史数据对象
	 */
	public void setRequestHistoryData(RequestHistoryData requestHistoryData) {
		this.requestHistoryData = requestHistoryData;
	}

	/**
	 * 获取    请求历史数据对象
	 * @return the requestHistoryData
	 */
	public RequestHistoryData getRequestHistoryData() {
		return requestHistoryData;
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

	public String deviceTime() {
		return this.gotoPage();
	}

	private String gotoPage() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 获取 终端时间
	 * 
	 * @return 终端时间
	 */
	public DeviceTime getDeviceTime() {
		return this.deviceTime;
	}

	/**
	 * 设置 终端时间
	 * 
	 * @param deviceTime
	 *            终端时间
	 */
	public void setDeviceTime(DeviceTime deviceTime) {
		this.deviceTime = deviceTime;
	}

	/**
	 * 
	 * @return
	 */
	public String upDeviceTime() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.deviceTime = this.deviceSettingsService.upDeviceTime(sensor);
		return SUCCESS;
	}

	public String downDeviceTime() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		DeviceTime deviceTime = this.deviceSettingsService.downDeviceTime(sensor);
		request.setAttribute("deviceTime", deviceTime);
		request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		return SUCCESS;
	}

	// ============================================================================================
	public String networkAdapter() {
		return this.gotoPage();
	}

	/**
	 * （0xA2，监测装置网络适配器查询）
	 * 
	 */
	public String upNetworkAdapter() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.networkAdapter = this.deviceSettingsService.upNetworkAdapter(sensor);
		return SUCCESS;
	}

	/**
	 * （0xA2，监测装置网络适配器设置）
	 * 
	 */
	public String downNetworkAdapter() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		boolean result = this.deviceSettingsService.downNetworkAdapter(sensor, networkAdapter);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================
	public String samplingParam() {
		return this.gotoPage();
	}

	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 
	 * @return
	 */
	public String upSamplingParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			this.samplingParam = this.deviceSettingsService.upWeatherSamplingParam(sensor);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			this.samplingParam = this.deviceSettingsService.upIceThincknessSamplingParam(sensor);
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			this.samplingParam = this.deviceSettingsService.upTowerTiltSamplingParam(sensor);
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			this.samplingParam = this.deviceSettingsService.upContaminationSamplingParam(sensor);
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			this.samplingParam = this.deviceSettingsService.upLandslideSamplingParam(sensor);
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置采样间隔参数");
		}
		
		return SUCCESS;
	}

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 
	 * @return
	 */
	public String downSamplingParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		boolean result = false;
		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downWeatherSamplingParam(sensor, samplingParam);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downIceThincknessSamplingParam(sensor, samplingParam);
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downTowerTiltSamplingParam(sensor, samplingParam);
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downContaminationSamplingParam(sensor, samplingParam);			
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downLandslideSamplingParam(sensor, samplingParam);			
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置采样间隔参数");
			return SUCCESS;
		}		
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================

	public String modelParam() {
		return this.gotoPage();
	}

	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 
	 * @return
	 */
	public String upModelParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.params.clear();
		ModelParam modelParam = null;
		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			modelParam = this.deviceSettingsService.upWeatherModelParam(sensor);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			modelParam = this.deviceSettingsService.upIceThincknessModelParam(sensor);			
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			modelParam = this.deviceSettingsService.upTowerTiltModelParam(sensor);			
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			modelParam = this.deviceSettingsService.upContaminationModelParam(sensor);		
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			modelParam = this.deviceSettingsService.upLandslideModelParam(sensor);		
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置模型参数");
		}
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
	 * 
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
		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downWeatherModelParam(sensor, modelParam);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downIceThincknessModelParam(sensor, modelParam);
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downTowerTiltModelParam(sensor, modelParam);
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downContaminationModelParam(sensor, modelParam);			
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downLandslideModelParam(sensor, modelParam);			
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置模型参数");
			return SUCCESS;
		}
		
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================

	public String alarmParam() {
		return this.gotoPage();
	}

	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
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
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			alarmParam = this.deviceSettingsService.upWeatherAlarmParam(sensor);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			alarmParam = this.deviceSettingsService.upIceThincknessAlarmParam(sensor);
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			alarmParam = this.deviceSettingsService.upTowerTiltAlarmParam(sensor);
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			alarmParam = this.deviceSettingsService.upContaminationAlarmParam(sensor);
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			alarmParam = this.deviceSettingsService.upLandslideAlarmParam(sensor);
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置告警参数");
		}		
		
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
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 
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
		
		
		boolean result = false;
		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downWeatherAlarmParam(sensor, alarmParam);
		}
		else if("A1002".equals(sensor.getSensorType().getSensorTypeCode()) || "A1022".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downIceThincknessAlarmParam(sensor, alarmParam);
		}		
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			//保存告警参数，用来作后台处理。
			result = this.deviceSettingsService.downTowerTiltAlarmParam(sensor, alarmParam);
		}
		else if("A1011".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downContaminationAlarmParam(sensor, alarmParam);			
		}		
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			result = this.deviceSettingsService.downLandslideAlarmParam(sensor, alarmParam);			
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置告警参数");
			return SUCCESS;
		}
		
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================
	public String masterStation() {
		return this.gotoPage();
	}

	/**
	 * 召测终端通讯参数（0xA7，监测装置指向上位机的信息查询）
	 * 
	 */
	public String upMasterStation() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.masterStation = this.deviceSettingsService.upMasterStation(sensor);
		return SUCCESS;
	}

	/**
	 * 下发终端通讯参数（0xA7，监测装置指向上位机的信息设置）
	 * 
	 */
	public String downMasterStation() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		boolean result = this.deviceSettingsService.downMasterStation(sensor, masterStation);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================
	public String deviceID() {
		return this.gotoPage();
	}

	/**
	 * 召测终端状态监测（0xAC，装置ID查询）
	 * 
	 * @return
	 */
	public String upDeviceID() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.deviceID = this.deviceSettingsService.upDeviceID(sensor);
		return SUCCESS;
	}

	/**
	 * 下发终端状态监测（0xAC，装置ID设置）
	 * 
	 * @return
	 */
	public String downDeviceID() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		boolean result = this.deviceSettingsService.downDeviceID(sensor, deviceID);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	// ============================================================================================

	public String deviceReset() {
		return this.gotoPage();
	}

	/**
	 * 终端复位（0xAD，装置复位）：常规复位（重启）
	 * 
	 * @return
	 */
	public String downDeviceResetNormal() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		DeviceReset deviceReset = new DeviceReset();
		deviceReset.setSensor(sensor);
		deviceReset.setResetMode(ResetMode.NORMAL_MODE);
		boolean result = this.deviceSettingsService.downDeviceReset(sensor, deviceReset);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	/**
	 * 终端复位（0xAD，装置复位）：复位至诊断模式
	 * 
	 * @return
	 */
	public String downDeviceResetDiagnose() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		DeviceReset deviceReset = new DeviceReset();
		deviceReset.setSensor(sensor);
		deviceReset.setResetMode(ResetMode.DIAGNOSE_MODE);
		boolean result = this.deviceSettingsService.downDeviceReset(sensor, deviceReset);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	/**
	 * 终端复位（0xAD，装置复位）：复位至诊断模式
	 * 
	 * @return
	 */
	public String downDeviceResetOTHER() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		DeviceReset deviceReset = new DeviceReset();
		deviceReset.setSensor(sensor);
		deviceReset.setResetMode(ResetMode.OTHER);
		boolean result = this.deviceSettingsService.downDeviceReset(sensor, deviceReset);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

	/**
	 * 终端复位（0xAD，装置复位）：复位至升级模式
	 * 
	 * @return
	 */
	public String downDeviceResetUpdate() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		DeviceReset deviceReset = new DeviceReset();
		deviceReset.setSensor(sensor);
		deviceReset.setResetMode(ResetMode.UPDATE_MODE);
		boolean result = this.deviceSettingsService.downDeviceReset(sensor, deviceReset);
		if (result) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 显示 上传历史数据界面
	 * @return
	 */
	
	public String uploadData(){
		if(requestHistoryData == null){
			requestHistoryData = new RequestHistoryData();
					

			SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
			String s1 = fd.format(new Date());
			Date d1 = null;

			try {
				d1 = fd.parse(s1);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			requestHistoryData.setBeginTime(d1);
			requestHistoryData.setEndTime(new Date());
		}
		
		return this.gotoPage();
	}
	/**
	 * 请求上传历史数据 
	 * @return
	 */
	public String requestUploadData(){
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}		
		if(requestHistoryData == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "参数不能为空");
			return INPUT;
		}	
		if(requestHistoryData.getBeginTime() == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "开始时间不能为空");
			return INPUT; 
		}
		if(requestHistoryData.getEndTime() == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "结束时间不能为空");
			return INPUT; 
		}

		//根据不同同的传感器类型，配置参数
		if("A1001".equals(sensor.getSensorType().getSensorTypeCode())){
			this.deviceSettingsService.requestUploadData(sensor, PacketType.SAMPLING_WEATHER, requestHistoryData.getBeginTime(), requestHistoryData.getEndTime());
		}
		else if("A1003".equals(sensor.getSensorType().getSensorTypeCode()) || "A1021".equals(sensor.getSensorType().getSensorTypeCode())){
			this.deviceSettingsService.requestUploadData(sensor, PacketType.SAMPLING_TOWERTILT, requestHistoryData.getBeginTime(), requestHistoryData.getEndTime());
		}
		else if("A1010".equals(sensor.getSensorType().getSensorTypeCode())){
			//因为是微气象和地质数据同时上传的。
			this.deviceSettingsService.requestUploadData(sensor, PacketType.SAMPLING_ALLTYPE, requestHistoryData.getBeginTime(), requestHistoryData.getEndTime());
		}
		else{
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该传感器类型不可配置采样间隔参数");
		}
		request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功！在数据上传过程中远程终端可能无反应，请耐心等待...");
		return SUCCESS;		
	}
	/**
	 * 停止上传历史数据
	 * @return
	 */
	public String stopUploadData(){
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		//TODO:未支持停止历史数据上传功能 
		return SUCCESS;		
	}


}