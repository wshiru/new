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
package com.yixin.A1000.landslide.web;

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
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.Param;
import com.yixin.A1000.settings.service.DeviceSettingsService;

/**
 * 终端参数配置Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class LandslideParameterAction extends ActionSupport implements
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

	/** 地质滑坡参数配置业务逻辑接口 */
	private LandslideParameterService landslideParameterService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 要进行操作的监测代理ID */
	private String id;

	/** 地质滑坡参数参数 **/
	private LandslideParameter landslideParameter;

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
	 * 设置 地质滑坡参数配置业务逻辑接口
	 * 
	 * @param landslideParameterService
	 *            地质滑坡参数配置业务逻辑接口
	 */
	public void setLandslideParameterService(
			LandslideParameterService landslideParameterService) {
		this.landslideParameterService = landslideParameterService;
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
	 * 设置 地质滑坡参数参数
	 * 
	 * @param landslideParameter
	 */
	public void setLandslideParameter(LandslideParameter landslideParameter) {
		this.landslideParameter = landslideParameter;
	}

	/**
	 * 取得 地质滑坡参数参数
	 * 
	 * @return
	 */
	public LandslideParameter getLandslideParameter() {
		return landslideParameter;
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
		this.landslideParameter = landslideParameterService
				.getLandslideParameterBySensor(sensor);
		if (landslideParameter == null) {
			// 初始化
			landslideParameter = new LandslideParameter();
			landslideParameter.setSampleNum(3);
			landslideParameter.setAllDepth(30.0);
			List<LandslideParameterDetail> deatil = new ArrayList<LandslideParameterDetail>();
			for (int i = 0; i < landslideParameter.getSampleNum(); i++) {
				LandslideParameterDetail d = new LandslideParameterDetail();
				d.setPointNo(i + 1);
				d.setRelativeDepth(5.0);
				d.setAlarm1(null);
				d.setAlarm2(null);
				d.setAlarm3(null);
				d.setCurrentState(0);
				deatil.add(d);
			}
			landslideParameter.setLandslideParameterDetails(deatil);
			landslideParameter.setSensor(sensor);
		}
		return SUCCESS;
	}

	/**
	 * 增加传感器个数
	 * 
	 * @return
	 */
	public String addSensorCount() {
		if (landslideParameter.getSampleNum() < 5) {
			landslideParameter
					.setSampleNum(landslideParameter.getSampleNum() + 1);
			LandslideParameterDetail d = new LandslideParameterDetail();
			d.setPointNo(landslideParameter.getSampleNum());
			d.setRelativeDepth(5.0);
			d.setAlarm1(null);
			d.setAlarm2(null);
			d.setAlarm3(null);
			d.setCurrentState(0);
			
			landslideParameter.getLandslideParameterDetails().add(0,d);
			double depth = 0;
			for(LandslideParameterDetail detail:landslideParameter.getLandslideParameterDetails()){
				depth = depth + detail.getRelativeDepth();
			}
			if(depth > landslideParameter.getAllDepth()){
				landslideParameter.setAllDepth(depth);
			}
			
		}
		System.out.println(landslideParameter.getAllDepth());
		return SUCCESS;
	}

	/**
	 * 减少传感器个数
	 * 
	 * @return
	 */
	public String decSensorCount() {
		if (landslideParameter.getSampleNum() > 1) {
			landslideParameter.getLandslideParameterDetails().remove(
					0);
			landslideParameter
					.setSampleNum(landslideParameter.getSampleNum() - 1);
		}
		System.out.println(landslideParameter.getAllDepth());
		return SUCCESS;
	}

	/**
	 * 保存传感器参数配置
	 * 
	 * @return
	 */
	public String saveSensorParam() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		this.landslideParameter.setSensor(sensor);
		landslideParameterService
				.editLandslideParameter(this.landslideParameter);
		// 保存后重新取数据
		this.landslideParameter = landslideParameterService
				.getLandslideParameterBySensor(sensor);
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
		LandslideParameter landslideParameter = landslideParameterService
				.getLandslideParameterBySensor(sensor);
		if (landslideParameter == null) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先配置传感器参数。");
			return INPUT;
		}
		this.params.clear();
		Param n = new Param();
		n.setDesc("num");
		n.setName("(监测点个数)");
		int sampleNum = landslideParameter.getSampleNum();
		n.setValue(String.valueOf(sampleNum));
		this.params.add(n);

		for (int i = 0; i < landslideParameter.getSampleNum(); i++) {
			Param p = new Param();
			p.setDesc("x" + String.valueOf(i));
			p.setValue("");
			p.setName(String.format("(监测点%d的x方向初始值,单位:°)", i + 1));
			this.params.add(p);
			// 如果是双轴的，就增加y轴
			if (landslideParameter.getXyType() != null
					&& landslideParameter.getXyType() == 2) {
				p = new Param();
				p.setDesc("y" + String.valueOf(i));
				p.setValue("");
				p.setName(String.format("(监测点%d的y方向初始值,单位:°)", i + 1));
				this.params.add(p);
			}
		}

		ModelParam modelParam = null;
		// 根据不同同的传感器类型，配置参数
		modelParam = this.deviceSettingsService.upLandslideModelParam(sensor);

		if (modelParam != null) {
			Iterator<Param> iterator = this.params.iterator();
			while (iterator.hasNext()) {
				Param p = iterator.next();
				if (p.getDesc() != "num" && modelParam.getParams().containsKey(p.getDesc())) {
					p.setValue(modelParam.getParams().get(p.getDesc()));
				}else if("num".equals(p.getDesc())){
					p.setValue(String.valueOf(sampleNum));
				}
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
		Map<String, String> map = new TreeMap<String, String>();
		for (Param p : params) {
			map.put(p.getDesc(), p.getValue());
			// 值是否正确
			try {
				Double.valueOf(p.getValue());
			} catch (Exception e) {
				String msg = String.format("参数[%s]的值必需为数值类型，请重新录入。",
						p.getDesc(), p.getValue());
				request.setAttribute(ERROR_MESSAGE_ATTR_NAME, msg);
				return INPUT;
			}
		}
		modelParam.setParams(map);
		if (map.size() == 0) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先召测再下发。");
			return INPUT;
		}
		if (this.deviceSettingsService.downLandslideModelParam(sensor,
				modelParam)) {
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
		LandslideParameter landslideParameter = landslideParameterService
				.getLandslideParameterBySensor(sensor);
		if (landslideParameter == null) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先配置传感器参数。");
			return INPUT;
		}
		this.params.clear();
		Param n = new Param();
		n.setDesc("n");
		n.setName("(监测点个数)");
		int sampleNum = landslideParameter.getSampleNum();
		n.setValue(String.valueOf(sampleNum));	
		this.params.add(n);

		for (int i = 0; i < landslideParameter.getSampleNum(); i++) {
			Param p = new Param();
			p.setDesc("x" + String.valueOf(i));
			p.setValue("");
			p.setName(String.format("(监测点%d的x方向报警阀值,单位:°)", i + 1));
			this.params.add(p);
			// 如果是双轴的，就增加y轴
			if (landslideParameter.getXyType() != null
					&& landslideParameter.getXyType() == 2) {
				p = new Param();
				p.setDesc("y" + String.valueOf(i));
				p.setValue("");
				p.setName(String.format("(监测点%d的y方向报警阀值,单位:°)", i + 1));
				this.params.add(p);
			}
		}

		AlarmParam alarmParam = this.deviceSettingsService
				.upLandslideAlarmParam(sensor);

		if (alarmParam != null) {
			Iterator<Param> iterator = this.params.iterator();
			while (iterator.hasNext()) {
				Param p = iterator.next();
				if (!"n".equals(p.getDesc()) && alarmParam.getParams().containsKey(p.getDesc())) {
					p.setValue(alarmParam.getParams().get(p.getDesc()));
				}
			}
		}
		request.setAttribute("sensor", sensor);
		return SUCCESS;
	}

	/**
	 * 下发告警阀值参数
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
		Map<String, String> map = new TreeMap<String, String>();
		for (Param p : params) {
			map.put(p.getDesc(), p.getValue());
			// 值是否正确
			try {
				Double.valueOf(p.getValue());
			} catch (Exception e) {
				String msg = String.format("参数[%s]的值必需为数值类型，请重新录入。",
						p.getDesc(), p.getValue());
				request.setAttribute(ERROR_MESSAGE_ATTR_NAME, msg);
				return INPUT;
			}
		}
		alarmParam.setParams(map);
		if (map.size() == 0) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先召测再下发。");
			return INPUT;
		}
		if (this.deviceSettingsService.downLandslideAlarmParam(sensor,
				alarmParam)) {
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作失败");
		}
		return SUCCESS;
	}

}