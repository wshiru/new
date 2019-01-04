/*
 * Project platform
 *
 * Class ProtocolParseServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:58:17
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.cag.model.XMLBusinessKeys;
import com.yixin.A1000.cag.model.request.DownloadCAGCtrlRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMAHistoryVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMALatestVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMAUpdateFileRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAConfigRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMADataRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAHeartbeatInfoRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAImageRequestModel;
import com.yixin.A1000.cag.model.uploadcmadata.InsulatorMonsoonItem;
import com.yixin.A1000.cag.model.uploadcmadata.MonitoringTypeCode;
import com.yixin.A1000.cag.model.uploadcmadata.TowerTiltItem;
import com.yixin.A1000.cag.model.uploadcmadata.WeatherItem;
import com.yixin.A1000.cag.model.uploadcmadata.WhiteMonsoonItem;
import com.yixin.A1000.cag.model.uploadcmadata.WireSagItem;
import com.yixin.A1000.cag.model.uploadcmadata.WireTemperatureItem;
import com.yixin.A1000.cag.service.ProtocolParseService;
import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;
import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.DeviceParameterDetail;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.whitemonsoon.model.WhiteMonsoonSampling;
import com.yixin.A1000.wiresag.model.WireSagSampling;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;
import com.yixin.framework.util.xml.XMLUtil;

/**
 * 协议解析实现类。将传入的数据解析成所需要的对象模型。
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolParseServiceImpl implements ProtocolParseService {

	/** 日志记录器 */
	private static final Logger logger = Logger.getLogger(ProtocolParseServiceImpl.class);

	/** CMA服务接口 */
	private CmaInfoService cmaInfoService;

	/** 监测装置服务接口 */
	private SensorService sensorService;


	private TaskConfigService taskConfigService;
	
	
	/** 设备参数参数服务接口 */
	//private DeviceParameterService deviceParameterService;
	
	

	/** 监测代理服务接口 */
	//private SensorParamsService sensorParamsService;
	

    /**
     * /**
	 * 设置 任务服务接口
	 * 
	 * @param taskConfigService
	 *            任务服务接口
     */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}

	/**
	 * 设置 CMA服务接口
	 * 
	 * @param cmaInfoService
	 *            CMA服务接口
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	/**
	 * 设置 监测装置服务接口
	 * 
	 * @param sensorService
	 *            监测装置服务接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	
	/**
	 * 设置 设备参数服务接口
	 * 
	 * @param deviceParameterService
	 *            设备参数服务接口
	 */
	
	/*public void setDeviceParameterService(
			DeviceParameterService deviceParameterService) {
		this.deviceParameterService = deviceParameterService;
	}
    */

	/**
	 * 设置 监测代理服务接口
	 * 
	 * @param sensorParamsService
	 *            监测代理服务接口
	 */
	/*public void setSensorParamsService(SensorParamsService sensorParamsService) {
		this.sensorParamsService = sensorParamsService;
	}
    */
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseUploadCMAHeartbeatInfo
	 * (java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public UploadCMAHeartbeatInfoRequestModel parseUploadCMAHeartbeatInfo(String strXMLParams) {
		CmaHeartbeatInfo cmaHeartbeatInfo = new CmaHeartbeatInfo();
		List<SensorHeartbeatInfo> sensorHeartbeatInfos = new ArrayList<SensorHeartbeatInfo>();
		Document doc = XMLUtil.parseToXML(strXMLParams);
		if (null == doc) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Element root = doc.getRootElement(); // 指向根节点
		Element cmaNode = (Element) root.element("cma");
		String cmaCode = cmaNode.attributeValue("id");
		Element cmaIpNode = cmaNode.element("ip");
		Element cmaCurTimeNode = cmaNode.element("curtime");
		Element cmaBatteryVoltageNode = cmaNode.element("batteryvoltage");
		Element cmaOperationTemperatureNode = cmaNode.element("operationtemperature");
		Element cmaFloatingChargeNode = cmaNode.element("floatingcharge");
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		cmaHeartbeatInfo.setCmaInfo(cmaInfo);
		cmaHeartbeatInfo.setCreateTime(new Date());// 入库时间
		cmaHeartbeatInfo.setIpAddress(cmaIpNode.getText());
		try {
			cmaHeartbeatInfo.setCurtime(sdf.parse(cmaCurTimeNode.getText()));
		} catch (ParseException e) {
		}
		if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_CHARGE.equals(cmaFloatingChargeNode.getText())) {
			cmaHeartbeatInfo.setFloatingcharge(0); // 充电
		} else if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_DISCHARGE.equals(cmaFloatingChargeNode
				.getText())) {
			cmaHeartbeatInfo.setFloatingcharge(1);// 放电
		}
		cmaHeartbeatInfo.setBatteryvoltage(Double.parseDouble(cmaBatteryVoltageNode.getText()));
		cmaHeartbeatInfo.setOperationtemperature(Double.parseDouble(cmaOperationTemperatureNode.getText()));

		Element sensorsNode = (Element) root.element("sensors");
		for (Iterator iterator = sensorsNode.elementIterator(); iterator.hasNext();) {
			Element sensorNode = (Element) iterator.next();
			String sensorCode = sensorNode.attributeValue("id");
			Element sensorStatusNode = sensorNode.element("status");
			Element sensorBatteryVoltageNode = sensorNode.element("batteryvoltage");
			Element sensorOperationTemperatureNode = sensorNode.element("operationtemperature");
			Element sensorFloatingChargeNode = sensorNode.element("floatingcharge");

			Sensor sensor = this.sensorService.getSensorbySensorCode(sensorCode);
			SensorHeartbeatInfo sensorHeartbeatInfo = new SensorHeartbeatInfo();
			sensorHeartbeatInfo.setBatteryvoltage(Double.parseDouble(sensorBatteryVoltageNode.getText()));
			sensorHeartbeatInfo.setCreateTime(new Date());
			if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_CHARGE.equals(sensorFloatingChargeNode
					.getText())) {
				sensorHeartbeatInfo.setFloatingcharge(0); // 充电
			} else if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_DISCHARGE
					.equals(sensorFloatingChargeNode.getText())) {
				sensorHeartbeatInfo.setFloatingcharge(1);// 放电
			}
			sensorHeartbeatInfo.setOperationtemperature(Double.parseDouble(sensorOperationTemperatureNode.getText()));
			sensorHeartbeatInfo.setSensor(sensor);
			if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_NORMAL.equals(sensorStatusNode.getText())) {
				sensorHeartbeatInfo.setStatus(1);
			} else if (XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_BREAK.equals(sensorStatusNode.getText())) {
				sensorHeartbeatInfo.setStatus(0);
			}
			sensorHeartbeatInfos.add(sensorHeartbeatInfo);
		}
		UploadCMAHeartbeatInfoRequestModel result = new UploadCMAHeartbeatInfoRequestModel();
		result.setCmaHeartbeatInfo(cmaHeartbeatInfo);
		result.setSensorHeartbeatInfos(sensorHeartbeatInfos);
		this.logUploadCMAHeartbeatInfo(result);
		return result;
	}

	private void logUploadCMAHeartbeatInfo(UploadCMAHeartbeatInfoRequestModel uploadCMAHeartbeatInfoRequestModel) {
		CmaHeartbeatInfo cmaHeartbeatInfo = uploadCMAHeartbeatInfoRequestModel.getCmaHeartbeatInfo();
		List<SensorHeartbeatInfo> sensorHeartbeatInfos = uploadCMAHeartbeatInfoRequestModel.getSensorHeartbeatInfos();
		StringBuilder sb = new StringBuilder();
		sb.append("CMA心跳：\r\n");
		sb.append("编码：" + cmaHeartbeatInfo.getCmaInfo().getCmaCode() + "\r\n");
		sb.append("IP地址：" + cmaHeartbeatInfo.getIpAddress() + "\r\n");
		sb.append("电压：" + cmaHeartbeatInfo.getBatteryvoltage() + "\r\n");
		sb.append("浮充状态：" + cmaHeartbeatInfo.getFloatingcharge() + "\r\n");
		sb.append("工作温度：" + cmaHeartbeatInfo.getOperationtemperature() + "\r\n");
		sb.append("CMA当前时间：" + cmaHeartbeatInfo.getCurtime() + "\r\n");
		sb.append("监测代理心跳：\r\n");
		for (SensorHeartbeatInfo s : sensorHeartbeatInfos) {
			sb.append("编码：" + s.getSensor().getSensorCode() + "\r\n");
			sb.append("电压：" + s.getBatteryvoltage() + "\r\n");
			sb.append("浮充状态：" + s.getFloatingcharge() + "\r\n");
			sb.append("工作温度：" + s.getOperationtemperature() + "\r\n");
			sb.append("心跳状态：" + s.getStatus() + "\r\n");
			
		}
		sb.append("\r\n");		
		logger.debug(sb.toString());
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseUploadCMAData(
	 * java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public UploadCMADataRequestModel parseUploadCMAData(String strXMLParams) {
		List<WeatherSampling> weatherSamplings = new ArrayList<WeatherSampling>();
		List<WireTemperatureSampling> wireTemperatureSamplings = new ArrayList<WireTemperatureSampling>();
		List<WhiteMonsoonSampling> whiteMonsoonSamplings = new ArrayList<WhiteMonsoonSampling>();
		List<WireSagSampling> wireSagSamplings = new ArrayList<WireSagSampling>();
		List<TowerTiltSampling> towerTiltSamplings = new ArrayList<TowerTiltSampling>();
		List<InsulatorMonsoonSampling> insulatorMonsoonSamplings = new ArrayList<InsulatorMonsoonSampling>();

		Map<String, Sensor> sensorMap = new HashMap<String, Sensor>(); // 存放sensor对象,以减少对数据库的访问
		Document doc = XMLUtil.parseToXML(strXMLParams);
		Element root = doc.getRootElement(); // 取得根节点
		Element monitordataNode = root.element("monitordata");
		// String cmaCode = monitordataNode.attributeValue("cmaid");
		// int dataNodeNum =
		// Integer.parseInt(monitordataNode.attributeValue("datanodenum"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Iterator iterator = monitordataNode.elementIterator(); iterator.hasNext();) {
			Element dataNode = (Element) iterator.next();
			String sensorCode = dataNode.attributeValue("sensorid");
			String type = dataNode.element("type").getText();
			// String equipmentid = dataNode.element("equipmentid").getText();
			Element attrsNode = dataNode.element("attrs");
			Date timestamp = null;
			try {
				timestamp = sdf.parse(dataNode.element("timestamp").getText());
			} catch (ParseException ex) {
				logger.error("[上传监测数据] - 数据采集时间格式错误。详细如下：", ex);
			}
			Sensor sensor = sensorMap.get(sensorCode);
			if (null == sensor) {// 存放sensor对象,以减少对数据库的访问
				sensor = this.sensorService.getSensorbySensorCode(sensorCode);
				sensorMap.put(sensorCode, sensor);
			}
			if (MonitoringTypeCode.ASOLIAN_VIBRATION.getCode().equals(type)) { // 微风振动

			} else if (MonitoringTypeCode.DANCING.getCode().equals(type)) { // 舞动

			} else if (MonitoringTypeCode.DEGREEE_OF_SITE_CONTAMINATION.getCode().equals(type)) { // 现场污秽度

			} else if (MonitoringTypeCode.ICE.getCode().equals(type)) { // 覆冰

			} else if (MonitoringTypeCode.IMAGE_VIDEO.getCode().equals(type)) { // 图像/视频

			} else if (MonitoringTypeCode.INSULATORMONSOONS.getCode().equals(type)) { // 绝缘子串风偏
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					if (InsulatorMonsoonItem.WINDAGE_YAW_ANGLE.equals(name)) {
						params.put(InsulatorMonsoonItem.WINDAGE_YAW_ANGLE, value);
					} else if (InsulatorMonsoonItem.DEFLECTION_ANGLE.equals(name)) {
						params.put(InsulatorMonsoonItem.DEFLECTION_ANGLE, value);
					} else if (InsulatorMonsoonItem.LEAST_CLEARANCE.equals(name)) {
						params.put(InsulatorMonsoonItem.LEAST_CLEARANCE, value);
					}
				}
				insulatorMonsoonSamplings.add(this.getInsulatorMonsoonSampling(sensor, timestamp, params));
			} else if (MonitoringTypeCode.MONSOON.getCode().equals(type)) { // 风偏

			} else if (MonitoringTypeCode.TOWER_TILT.getCode().equals(type)) { // 杆塔倾斜
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					if (TowerTiltItem.INCLINATION.equals(name)) {
						params.put(TowerTiltItem.INCLINATION, value);
					} else if (TowerTiltItem.INCLINATION_X.equals(name)) {
						params.put(TowerTiltItem.INCLINATION_X, value);
					} else if (TowerTiltItem.INCLINATION_Y.equals(name)) {
						params.put(TowerTiltItem.INCLINATION_Y, value);
					} else if (TowerTiltItem.ANGLE_X.equals(name)) {
						params.put(TowerTiltItem.ANGLE_X, value);
					} else if (TowerTiltItem.ANGLE_Y.equals(name)) {
						params.put(TowerTiltItem.ANGLE_Y, value);
					}
				}
				towerTiltSamplings.add(this.getTowerTiltSampling(sensor, timestamp, params));
			} else if (MonitoringTypeCode.WEATHER.getCode().equals(type)) { // 微气象
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					
					if (WeatherItem.AIR_PRESSURE.equals(name)) {
						params.put(WeatherItem.AIR_PRESSURE, value);
					} else if (WeatherItem.PRECIPITATION.equals(name)) {
						params.put(WeatherItem.PRECIPITATION, value);
					} else if (WeatherItem.HUMIDITY.equals(name)) {
						params.put(WeatherItem.HUMIDITY, value);
					} else if (WeatherItem.RADIATION_INTENSITY.equals(name)) {
						params.put(WeatherItem.RADIATION_INTENSITY, value);
					} else if (WeatherItem.AIR_TEMPERATURE.equals(name)) {
						params.put(WeatherItem.AIR_TEMPERATURE, value);
					} else if (WeatherItem.AVERAGE_WINDDIRECTION_10MIN.equals(name)) {
						params.put(WeatherItem.AVERAGE_WINDDIRECTION_10MIN, value);
					} else if (WeatherItem.AVERAGE_WINDSPEED_10MIN.equals(name)) {
						params.put(WeatherItem.AVERAGE_WINDSPEED_10MIN, value);
					} else if (WeatherItem.MAX_WINDSPEED.equals(name)) {
						params.put(WeatherItem.MAX_WINDSPEED, value);
					} else if (WeatherItem.EXTREME_WINDSPEED.equals(name)) {
						params.put(WeatherItem.EXTREME_WINDSPEED, value);
					} else if (WeatherItem.STANDARD_WINDSPEED.equals(name)) {
						params.put(WeatherItem.STANDARD_WINDSPEED, value);
					} else if (WeatherItem.PRECIPITATION_INTENSITY.equals(name)) {
						params.put(WeatherItem.PRECIPITATION_INTENSITY, value);
					}
					
				}
				weatherSamplings.add(this.getWeatherSampling(sensor, timestamp, params));
			} else if (MonitoringTypeCode.WHITEMONSOON.getCode().equals(type)) { // 相间风偏
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					if (WhiteMonsoonItem.WINDAGE_YAW_ANGLE.equals(name)) {
						params.put(WhiteMonsoonItem.WINDAGE_YAW_ANGLE, value);
					} else if (WhiteMonsoonItem.DEFLECTION_ANGLE.equals(name)) {
						params.put(WhiteMonsoonItem.DEFLECTION_ANGLE, value);
					} else if (WhiteMonsoonItem.LEAST_CLEARANCE.equals(name)) {
						params.put(WhiteMonsoonItem.LEAST_CLEARANCE, value);
					}
				}
				whiteMonsoonSamplings.add(this.getWhiteMonsoonSampling(sensor, timestamp, params));
			} else if (MonitoringTypeCode.WIRE_SAG.getCode().equals(type)) { // 导线弧垂
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					if (WireSagItem.CONDUCTOR_SAG.equals(name)) {
						params.put(WireSagItem.CONDUCTOR_SAG, value);
					} else if (WireSagItem.TOGROUND_DISTANCE.equals(name)) {
						params.put(WireSagItem.TOGROUND_DISTANCE, value);
					}
				}
				wireSagSamplings.add(this.getWireSagSampling(sensor, timestamp, params));
			} else if (MonitoringTypeCode.WIRE_TEMPERATURE.getCode().equals(type)) { // 导线温度
				Map<String, String> params = new HashMap<String, String>();
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					// boolean isAlarmData = false;
					// if ("TRUE".equals(attrsNode.attributeValue("alarm"))) {
					// isAlarmData = true;
					// }
					if (WireTemperatureItem.LINE_TEMPERATURE1.equals(name)) {
						params.put(WireTemperatureItem.LINE_TEMPERATURE1, value);
					} else if (WireTemperatureItem.LINE_TEMPERATURE2.equals(name)) {
						params.put(WireTemperatureItem.LINE_TEMPERATURE2, value);
					}
				}
				wireTemperatureSamplings.add(this.getWireTemperatureSampling(sensor, timestamp, params));
			}
		}
		UploadCMADataRequestModel result = new UploadCMADataRequestModel();
		result.setInsulatorMonsoonSamplings(insulatorMonsoonSamplings);
		result.setTowerTiltSamplings(towerTiltSamplings);
		result.setWeatherSamplings(weatherSamplings);
		result.setWhiteMonsoonSamplings(whiteMonsoonSamplings);
		result.setWireSagSamplings(wireSagSamplings);
		result.setWireTemperatureSamplings(wireTemperatureSamplings);
		return result;
	}

	/**
	 * 生成微气象对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private WeatherSampling getWeatherSampling(Sensor sensor, Date simplingTime, Map<String, String> params) {
		WeatherSampling result = new WeatherSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		
		result.setAverageWindSpeed10min(this.parseStringToDouble(params.get(WeatherItem.AVERAGE_WINDSPEED_10MIN), "微气象[10分钟平均风速]"));
		result.setAverageWindDirection10min(this.parseStringToDouble(params.get(WeatherItem.AVERAGE_WINDDIRECTION_10MIN), "微气象[10分钟平均风向]"));
		result.setMaxWindSpeed(this.parseStringToDouble(params.get(WeatherItem.MAX_WINDSPEED), "微气象[最大风速 ]"));
		result.setExtremeWindSpeed(this.parseStringToDouble(params.get(WeatherItem.EXTREME_WINDSPEED), "微气象[极大风速 ]"));
		result.setStrandrdWindSpeed(this.parseStringToDouble(params.get(WeatherItem.STANDARD_WINDSPEED), "微气象[标准风速 ]"));
		result.setTemperature(this.parseStringToDouble(params.get(WeatherItem.AIR_TEMPERATURE), "微气象[气温]"));
		result.setHumidity(this.parseStringToDouble(params.get(WeatherItem.HUMIDITY), "微气象[湿度]"));
		result.setAirPressure(this.parseStringToDouble(params.get(WeatherItem.AIR_PRESSURE), "微气象[气压]"));
		result.setDailyRainfall(this.parseStringToDouble(params.get(WeatherItem.PRECIPITATION), "微气象[雨量]"));
		result.setRadiationIntensity(this.parseStringToDouble(params.get(WeatherItem.RADIATION_INTENSITY), "微气象[光辐射强度]"));	
		result.setPrecipitationIntensity(this.parseStringToDouble(params.get(WeatherItem.PRECIPITATION_INTENSITY), "微气象[降水强度]"));
		
		result.setWindDirection(this.parseStringToDouble(params.get(WeatherItem.AVERAGE_WINDDIRECTION_10MIN), "微气象[风向]"));
		result.setWindSpeed(this.parseStringToDouble(params.get(WeatherItem.AVERAGE_WINDSPEED_10MIN), "微气象[风速]"));
		
		return result;
	}

	/**
	 * 生成导线温度对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private WireTemperatureSampling getWireTemperatureSampling(Sensor sensor, Date simplingTime,
			Map<String, String> params) {
		WireTemperatureSampling result = new WireTemperatureSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		result.setLineTemperature1(this.parseStringToDouble(params.get(WireTemperatureItem.LINE_TEMPERATURE1),
				"导线温度[温度1]"));
		result.setLineTemperature2(this.parseStringToDouble(params.get(WireTemperatureItem.LINE_TEMPERATURE2),
				"导线温度[温度2]"));
		return result;
	}

	/**
	 * 生成绝缘子串风偏对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private InsulatorMonsoonSampling getInsulatorMonsoonSampling(Sensor sensor, Date simplingTime,
			Map<String, String> params) {
		InsulatorMonsoonSampling result = new InsulatorMonsoonSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		result.setAngle(this.parseStringToDouble(params.get(InsulatorMonsoonItem.DEFLECTION_ANGLE), "绝缘子串风偏[倾斜角]"));
		result.setMinClearanceParams(this.parseStringToDouble(params.get(InsulatorMonsoonItem.LEAST_CLEARANCE),
				"绝缘子串风偏[最小电气间隙参数]"));
		result.setWindAngle(this.parseStringToDouble(params.get(InsulatorMonsoonItem.WINDAGE_YAW_ANGLE), "绝缘子串风偏[风偏角]"));
		return result;
	}

	/**
	 * 生成杆塔倾斜对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private TowerTiltSampling getTowerTiltSampling(Sensor sensor, Date simplingTime, Map<String, String> params) {
		TowerTiltSampling result = new TowerTiltSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		
		result.setInclination(this.parseStringToDouble(params.get(TowerTiltItem.INCLINATION), "杆塔倾斜[倾斜度 ]"));
		result.setGradientAlongLines(this.parseStringToDouble(params.get(TowerTiltItem.INCLINATION_X), "杆塔倾斜[顺线倾斜度 ]"));
		result.setLateralTilt(this.parseStringToDouble(params.get(TowerTiltItem.INCLINATION_Y), "杆塔倾斜[横向倾斜度 ]"));
		result.setAngleX(this.parseStringToDouble(params.get(TowerTiltItem.ANGLE_X), "杆塔倾斜[顺线倾斜角]"));
		result.setAngleY(this.parseStringToDouble(params.get(TowerTiltItem.ANGLE_Y), "杆塔倾斜[横向倾斜角]"));
		
		result.setGeneralInclination(this.parseStringToDouble(params.get(TowerTiltItem.INCLINATION), "杆塔倾斜[设置综合倾斜度 ]"));
		
		return result;
	}

	/**
	 * 生成导线弧垂对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private WireSagSampling getWireSagSampling(Sensor sensor, Date simplingTime, Map<String, String> params) {
		WireSagSampling result = new WireSagSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		result.setGroundDistance(this.parseStringToDouble(params.get(WireSagItem.TOGROUND_DISTANCE), "杆塔倾斜[综合倾斜度]"));
		result.setSag(this.parseStringToDouble(params.get(WireSagItem.CONDUCTOR_SAG), "杆塔倾斜[综合倾斜度]"));
		return result;
	}

	/**
	 * 生成导线弧垂对象
	 * 
	 * @param sensor
	 *            监测装置
	 * @param simplingTime
	 *            采莲时间
	 * @param params
	 *            参数键值对
	 * @return
	 */
	private WhiteMonsoonSampling getWhiteMonsoonSampling(Sensor sensor, Date simplingTime, Map<String, String> params) {
		WhiteMonsoonSampling result = new WhiteMonsoonSampling();
		result.setSamplingTime(simplingTime);
		result.setSensor(sensor);
		result.setCreateTime(new Date());
		result.setAngle(this.parseStringToDouble(params.get(WhiteMonsoonItem.DEFLECTION_ANGLE), "绝缘子串风偏[倾斜角]"));
		result.setMinClearanceParams(this.parseStringToDouble(params.get(WhiteMonsoonItem.LEAST_CLEARANCE),
				"绝缘子串风偏[最小电气间隙参数]"));
		result.setWindAngle(this.parseStringToDouble(params.get(WhiteMonsoonItem.WINDAGE_YAW_ANGLE), "绝缘子串风偏[风偏角]"));
		return result;
	}

	/**
	 * 将str转换成Double类型.转换出错时返回null,并记录相关错误信息.
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param errorMessage
	 *            出错日志
	 * @return
	 */
	private Double parseStringToDouble(String str, String errorMessage) {
		Double result = null;
		try {
			result = Double.parseDouble(str);
		} catch (NumberFormatException ex) {
			logger.error("[上传监测数据] - " + errorMessage + "数据格式错误。", ex);
		} catch (NullPointerException ex){
			//logger.error("[上传监测数据] - " + errorMessage + "数据为空。", ex);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseUploadCMAImage
	 * (byte[], java.lang.String)
	 */
	@Override
	public UploadCMAImageRequestModel parseUploadCMAImage(byte[] imageByte, String strXMLParams) {
		// TODO 上传静态图像数据
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseUploadCMAConfig
	 * (java.lang.String)
	 */
	
	/**
	 * 上传配置信息：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public UploadCMAConfigRequestModel parseUploadCMAConfig(String strXMLParams) {
		
		List<DeviceParameter> deviceParams = new ArrayList<DeviceParameter>();
		
		Document doc = XMLUtil.parseToXML(strXMLParams);
		
		if (null == doc) {
			return new UploadCMAConfigRequestModel();
		}
		Element root = doc.getRootElement(); // 指向根节点
		Element configsNode = root.element("configs");
		
		
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
	
			Element configNode = (Element) iterator.next(); // <config />标签
			String objId = configNode.attributeValue("objid");
			
			CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(objId);
			Sensor  sensor = this.sensorService.getSensorbySensorCode(objId);
			
			
			//所有上报数据均新增到参数为中，状态为3： 表示已执行成功
			DeviceParameter Params = new DeviceParameter();
			UUID  uuid = UUID.randomUUID();  
			Params.setDeviceParameterId(uuid.toString());     //手动设置 ID
			Params.setCreateTime(new Date()); //创建时间
			//执行成功标志
			Params.setState(3);
			
			if (null != cmaInfo) { // 如果上传的数据为监测代理参数
			 	Params.setCmaInfo(cmaInfo);
                //更 cma 召测数据状态为已经执行成功
			 	this.taskConfigService.updateCmaInfoTaskConfig(uuid.toString(), cmaInfo);
			}

			if (null != sensor) { // 如果上传的数据为监测装置参数
			 	Params.setSensor(sensor);
			 	 //更 Sensor 召测数据状态为已经执行成功
			 	this.taskConfigService.updateSensorTaskConfig(uuid.toString(), sensor);
			}
			
		
			int row = 1; 
			List<DeviceParameterDetail> details = new ArrayList<DeviceParameterDetail>();
			
			//数据上传周期，单位为分钟
			String  dataacquiretime = configNode.attributeValue("dataacquiretime"); 
			if ( !"".equals(dataacquiretime.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("dataacquiretime");
				cpd.setValue(dataacquiretime);
				cpd.setDataLength(dataacquiretime.length());
				cpd.setDataType("Integer");
				cpd.setDsec("数据上传周期");
				cpd.setUnit("分钟");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);				
				row++;
			}
			
			//心跳周期
			String heartbeattime =  configNode.attributeValue("heartbeattime"); 
			if ( !"".equals(heartbeattime.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("heartbeattime");
				cpd.setValue(heartbeattime);
				cpd.setDataLength(heartbeattime.length());
				cpd.setDataType("Integer");
				cpd.setDsec("心跳周期");
				cpd.setUnit("秒");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);			
				row++;
			}	
			
			
			//版本
			String version =  configNode.attributeValue("version"); 
			if ( !"".equals(version.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("version");
				cpd.setValue(version);
				cpd.setDataLength(version.length());
				cpd.setDataType("String");
				cpd.setDsec("版本");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);			
				row++;
			}	
			
			//苏醒基准时间
			String wakeupreferencetime =  configNode.attributeValue("wakeupreferencetime"); 
			if ( !"".equals(wakeupreferencetime.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("wakeupreferencetime");
				cpd.setValue(wakeupreferencetime);
				cpd.setDataLength(wakeupreferencetime.length());
				cpd.setDataType("Integer");
				cpd.setDsec("苏醒基准时间");
				cpd.setUnit("分钟");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);			
				row++;
			}	
			
			//苏醒周期
			String wakeupcycle =  configNode.attributeValue("wakeupcycle"); 
			if ( !"".equals(wakeupcycle.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("wakeupcycle");
				cpd.setValue(wakeupcycle);
				cpd.setDataLength(wakeupcycle.length());
				cpd.setDataType("Integer");
				cpd.setDsec("苏醒周期");
				cpd.setUnit("分钟");
				cpd.setRangeValue("必须为1440的约数，等于0表示不休眠");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);				
				row++;
			}	
			
			//苏醒持续时间
			String wakeuptime =  configNode.attributeValue("wakeuptime"); 
			if ( !"".equals(wakeuptime.trim()) ){
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);
				cpd.setName("wakeuptime");
				cpd.setValue(wakeuptime);
				cpd.setDataLength(wakeuptime.length());
				cpd.setDataType("Integer");
				cpd.setDsec("苏醒持续时间");
				cpd.setUnit("秒");
				cpd.setRangeValue("必须小于或等于苏醒周期值的60倍");
				cpd.setState(1);
				cpd.setOrderNumber(row);
				details.add(cpd);				
				row++;
			}	
			
			//设置参数属值与对应的值等
			
			for (Iterator ite = configNode.elementIterator(); ite.hasNext();) {
				Element attrNode = (Element) ite.next();//arrtr
				
				String desc = attrNode.attributeValue("desc");
				String name = attrNode.attributeValue("name");
				String type = attrNode.attributeValue("type");
				String length = attrNode.attributeValue("length");
				String unit = attrNode.attributeValue("unit");
				String range = attrNode.attributeValue("range");
				String note = attrNode.attributeValue("note");
				String value = attrNode.attributeValue("value");
				
				DeviceParameterDetail cpd = new DeviceParameterDetail();
				cpd.setDeviceParameter(Params);

				cpd.setName(name);
				cpd.setValue(value);
				cpd.setDataLength(Integer.valueOf(length));
				cpd.setDataType(type);
				cpd.setDsec(desc);
				cpd.setNote(note);
				cpd.setUnit(unit);
				cpd.setRangeValue(range);
				
				cpd.setOrderNumber(row);
				cpd.setState(1);
				details.add(cpd);		
				row++;	
			 }
			
		 	 //添加参数
			 if ( details.size() > 0  ){
				Params.setDeviceParameterDetails(details);
				deviceParams.add(Params);
		     }
				
		}
		
		UploadCMAConfigRequestModel result = new UploadCMAConfigRequestModel();
		result.setDeviceParams(deviceParams);
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseDownloadCAGCtrl
	 * (java.lang.String)
	 */
	@Override
	public DownloadCAGCtrlRequestModel parseDownloadCAGCtrl(String strXMLParams) {
		// TODO 下发控制指令
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolParseService#
	 * parseDownloadCMALatestVersion(java.lang.String)
	 */
	@Override
	public DownloadCMALatestVersionRequestModel parseDownloadCMALatestVersion(String strXMLParams) {
		DownloadCMALatestVersionRequestModel result = new DownloadCMALatestVersionRequestModel();
		Document doc = XMLUtil.parseToXML(strXMLParams);
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String id = commandNode.attributeValue("id");
		if (null != this.cmaInfoService.getCmaInfobyCmaCode(id)) {
			result.setCmaCode(commandNode.attributeValue("id"));
		} else {
			result.setSensorCode(id);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolParseService#
	 * parseDownloadCMAHistoryVersion(java.lang.String)
	 */
	@Override
	public DownloadCMAHistoryVersionRequestModel parseDownloadCMAHistoryVersion(String strXMLParams) {
		DownloadCMAHistoryVersionRequestModel result = new DownloadCMAHistoryVersionRequestModel();
		Document doc = XMLUtil.parseToXML(strXMLParams);
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String id = commandNode.attributeValue("id");
		if (null != this.cmaInfoService.getCmaInfobyCmaCode(id)) {
			result.setCmaCode(commandNode.attributeValue("id"));
		} else {
			result.setSensorCode(id);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolParseService#parseDownloadCMAUpdateFile
	 * (java.lang.String)
	 */
	@Override
	public DownloadCMAUpdateFileRequestModel parseDownloadCMAUpdateFile(String strXMLParams) {
		DownloadCMAUpdateFileRequestModel result = new DownloadCMAUpdateFileRequestModel();
		Document doc = XMLUtil.parseToXML(strXMLParams);
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		result.setCmaCode(commandNode.attributeValue("id"));
		result.setFileName(commandNode.attributeValue("filename"));
		result.setVersionNo(commandNode.attributeValue("versionno"));
		return result;
	}
}
