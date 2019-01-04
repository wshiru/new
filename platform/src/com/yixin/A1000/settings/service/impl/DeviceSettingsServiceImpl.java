/*
 * Project platform
 *
 * Class DeviceParamServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:47:12
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.settings.service.impl;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.DeviceID;
import com.yixin.A1000.settings.model.DeviceReset;
import com.yixin.A1000.settings.model.DeviceTime;
import com.yixin.A1000.settings.model.MasterStation;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.NetworkAdapter;
import com.yixin.A1000.settings.model.SamplingParam;
import com.yixin.A1000.settings.service.DeviceSettingsService;

/**
 * 终端参数配置服务类
 * 
 * @version v1.0.0
 * @author 
 */
public class DeviceSettingsServiceImpl implements DeviceSettingsService {

	/** 通讯服务类 */
	private CommService commService;

	/**
	 * 设置 通讯服务类
	 * 
	 * @param commService
	 *            通讯服务类
	 */
	public void setCommService(CommService commService) {
		this.commService = commService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceParamService#upDeviceTime()
	 */
	@Override
	public DeviceTime upDeviceTime(Sensor sensor) {
		return this.commService.upDeviceTime(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceParamService#DownDeviceTime()
	 */
		
	@Override
	public DeviceTime downDeviceTime(Sensor sensor) {
		return this.commService.downDeviceTime(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upNetworkAdapter
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public NetworkAdapter upNetworkAdapter(Sensor sensor) {
		return this.commService.upNetworkAdapter(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#downNetworkAdapter
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public boolean downNetworkAdapter(Sensor sensor, NetworkAdapter networkAdapter) {
		return this.commService.downNetworkAdapter(sensor.getSensorCode(), networkAdapter);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * upWeatherSamplingParam(com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public SamplingParam upWeatherSamplingParam(Sensor sensor) {
		return this.commService.upSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downWeatherSamplingParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.SamplingParam)
	 */
	@Override
	public boolean downWeatherSamplingParam(Sensor sensor, SamplingParam samplingParam) {
		return this.commService.downSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER, samplingParam);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * upTowerTiltSamplingParam(com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public SamplingParam upTowerTiltSamplingParam(Sensor sensor) {
		return this.commService.upSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downTowerTiltSamplingParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.SamplingParam)
	 */
	@Override
	public boolean downTowerTiltSamplingParam(Sensor sensor, SamplingParam samplingParam) {
		return this.commService.downSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT, samplingParam);
	}

	@Override
	public SamplingParam upIceThincknessSamplingParam(Sensor sensor) {
		return this.commService.upSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS);
	}

	@Override
	public boolean downIceThincknessSamplingParam(Sensor sensor,
			SamplingParam samplingParam) {
		return this.commService.downSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS, samplingParam);
	}

	@Override
	public SamplingParam upContaminationSamplingParam(Sensor sensor) {
		return this.commService.upSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION);
	}

	@Override
	public boolean downContaminationSamplingParam(Sensor sensor,
			SamplingParam samplingParam) {
		return this.commService.downSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION, samplingParam);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * upLandslideSamplingParam(com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public SamplingParam upLandslideSamplingParam(Sensor sensor) {
		return this.commService.upSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downLandslideSamplingParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.SamplingParam)
	 */
	@Override
	public boolean downLandslideSamplingParam(Sensor sensor, SamplingParam samplingParam) {
		return this.commService.downSamplingParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE, samplingParam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upWeatherModelParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public ModelParam upWeatherModelParam(Sensor sensor) {
		return this.commService.upModelParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downWeatherModelParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.ModelParam)
	 */
	@Override
	public boolean downWeatherModelParam(Sensor sensor, ModelParam modelParam) {
		return this.commService.downModelParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER, modelParam);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upTowerTiltModelParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public ModelParam upTowerTiltModelParam(Sensor sensor) {
		return this.commService.upModelParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downTowerTiltModelParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.ModelParam)
	 */
	@Override
	public boolean downTowerTiltModelParam(Sensor sensor, ModelParam modelParam) {
		return this.commService.downModelParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT, modelParam);
	}
	
	@Override
	public boolean downIceThincknessModelParam(Sensor sensor,
			ModelParam modelParam) {
		return this.commService.downModelParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS, modelParam);
	}

	@Override
	public ModelParam upIceThincknessModelParam(Sensor sensor) {
		return this.commService.upModelParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS);
	}
	
	@Override
	public ModelParam upContaminationModelParam(Sensor sensor) {
		return this.commService.upModelParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION);
	}

	@Override
	public boolean downContaminationModelParam(Sensor sensor,
			ModelParam modelParam) {
		return this.commService.downModelParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION, modelParam);
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upLandslideModelParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public ModelParam upLandslideModelParam(Sensor sensor) {
		return this.commService.upModelParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downLandslideModelParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.ModelParam)
	 */
	@Override
	public boolean downLandslideModelParam(Sensor sensor, ModelParam modelParam) {
		return this.commService.downModelParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE, modelParam);
	}	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upWeatherAlarmParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public AlarmParam upWeatherAlarmParam(Sensor sensor) {
		return this.commService.upAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downWeatherAlarmParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.TowerTiltAlarmParam)
	 */
	@Override
	public boolean downWeatherAlarmParam(Sensor sensor, AlarmParam alarmParam) {
		return this.commService.downAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_WEATHER, alarmParam);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upTowerTiltAlarmParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public AlarmParam upTowerTiltAlarmParam(Sensor sensor) {
		return this.commService.upAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downTowerTiltAlarmParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.TowerTiltAlarmParam)
	 */
	@Override
	public boolean downTowerTiltAlarmParam(Sensor sensor, AlarmParam alarmParam) {
		return this.commService.downAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_TOWERTILT, alarmParam);
	}
	

	@Override
	public AlarmParam upIceThincknessAlarmParam(Sensor sensor) {
		return this.commService.upAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS);
	}

	@Override
	public boolean downIceThincknessAlarmParam(Sensor sensor,
			AlarmParam alarmParam) {
		return this.commService.downAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_ICETHINCKNESS, alarmParam);
	}

	@Override
	public AlarmParam upContaminationAlarmParam(Sensor sensor) {
		return this.commService.upAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION);
	}

	@Override
	public boolean downContaminationAlarmParam(Sensor sensor,
			AlarmParam alarmParam) {
		return this.commService.downAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_CONTAMINATION, alarmParam);
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upLandslideAlarmParam
	 * (com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public AlarmParam upLandslideAlarmParam(Sensor sensor) {
		return this.commService.upAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.A1000.settings.service.DeviceSettingsService#
	 * downLandslideAlarmParam(com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.TowerTiltAlarmParam)
	 */
	@Override
	public boolean downLandslideAlarmParam(Sensor sensor, AlarmParam alarmParam) {
		return this.commService.downAlarmParam(sensor.getSensorCode(),PacketType.CONTROL_LANDSLIDE, alarmParam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upMasterStation
	 * (java.lang.String)
	 */
	@Override
	public MasterStation upMasterStation(Sensor sensor) {
		return this.commService.upMasterStation(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#downMasterStation
	 * (java.lang.String, com.yixin.A1000.settings.model.MasterStation)
	 */
	@Override
	public boolean downMasterStation(Sensor sensor, MasterStation masterStation) {
		return this.commService.downMasterStation(sensor.getSensorCode(), masterStation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#upDeviceID(java
	 * .lang.String)
	 */
	@Override
	public DeviceID upDeviceID(Sensor sensor) {
		return this.commService.upDeviceID(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#downDeviceID(
	 * java.lang.String, com.yixin.A1000.settings.model.DeviceID)
	 */
	@Override
	public boolean downDeviceID(Sensor sensor, DeviceID deviceID) {
		return this.commService.downDeviceID(sensor.getSensorCode(), deviceID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.A1000.settings.service.DeviceSettingsService#downDeviceReset
	 * (com.yixin.A1000.archive.model.Sensor,
	 * com.yixin.A1000.settings.model.DeviceReset)
	 */
	@Override
	public boolean downDeviceReset(Sensor sensor, DeviceReset deviceReset) {
		return this.commService.downDeviceReset(sensor.getSensorCode(), deviceReset);
	}

	@Override
	public boolean requestUploadData(Sensor sensor, PacketType packetType,Date beginTime,Date endTime) {
		return this.commService.requestUploadData(sensor.getSensorCode(),packetType,beginTime,endTime);
	}







}
