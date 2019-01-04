/*
 * Project platform
 *
 * Class ControlSettingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:42:17
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.settings.service;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.DeviceID;
import com.yixin.A1000.settings.model.DeviceReset;
import com.yixin.A1000.settings.model.DeviceTime;
import com.yixin.A1000.settings.model.MasterStation;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.NetworkAdapter;
import com.yixin.A1000.settings.model.SamplingParam;

/**
 * 终端参数配置业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface DeviceSettingsService {

	/**
	 * 召测时间
	 * 
	 * @param sensor
	 * @return
	 */
	public abstract DeviceTime upDeviceTime(Sensor sensor);

	/**
	 * 下发时间
	 * 
	 * @param sensor
	 * @return
	 */
	public abstract DeviceTime downDeviceTime(Sensor sensor);

	/**
	 * （0xA2，监测装置网络适配器查询）
	 * 
	 */
	public abstract NetworkAdapter upNetworkAdapter(Sensor sensor);

	/**
	 * （0xA2，监测装置网络适配器设置）
	 * 
	 */
	public abstract boolean downNetworkAdapter(Sensor sensor, NetworkAdapter networkAdapter);

	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 微气象 
	 * @param sensor
	 * @return
	 */
	public abstract SamplingParam upWeatherSamplingParam(Sensor sensor);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 微气象
	 * @param sensor
	 * @param samplingParam
	 * @return
	 */
	public abstract boolean downWeatherSamplingParam(Sensor sensor, SamplingParam samplingParam);
	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 杆塔倾斜
	 * @param sensor
	 * @return
	 */
	public abstract SamplingParam upTowerTiltSamplingParam(Sensor sensor);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 杆塔倾斜
	 * @param sensor
	 * @param samplingParam
	 * @return
	 */
	public abstract boolean downTowerTiltSamplingParam(Sensor sensor, SamplingParam samplingParam);
	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 覆冰监测
	 * @param sensor
	 * @return
	 */
	public abstract SamplingParam upIceThincknessSamplingParam(Sensor sensor);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 覆冰监测
	 * @param sensor
	 * @param samplingParam
	 * @return
	 */
	public abstract boolean downIceThincknessSamplingParam(Sensor sensor, SamplingParam samplingParam);
	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 污秽度监测
	 * @param sensor
	 * @return
	 */
	public abstract SamplingParam upContaminationSamplingParam(Sensor sensor);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 污秽度监测
	 * @param sensor
	 * @param samplingParam
	 * @return
	 */
	public abstract boolean downContaminationSamplingParam(Sensor sensor, SamplingParam samplingParam);	
	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 地质灾害 T_LANDSLIDEPARAMS
	 * @param sensor
	 * @return
	 */
	public abstract SamplingParam upLandslideSamplingParam(Sensor sensor);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 地质灾害
	 * @param sensor
	 * @param samplingParam
	 * @return
	 */
	public abstract boolean downLandslideSamplingParam(Sensor sensor, SamplingParam samplingParam);

	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 
	 * @param sensor
	 * @return
	 */
	public abstract ModelParam upWeatherModelParam(Sensor sensor);

	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 微气象
	 * @param sensor
	 * @param modelParam
	 * @return
	 */
	public abstract boolean downWeatherModelParam(Sensor sensor, ModelParam modelParam);
	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 微气象
	 * @param sensor
	 * @return
	 */
	public abstract ModelParam upTowerTiltModelParam(Sensor sensor);
	
	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 杆塔倾斜
	 * @param sensor
	 * @param modelParam
	 * @return
	 */
	public abstract boolean downTowerTiltModelParam(Sensor sensor, ModelParam modelParam);	

	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 覆冰
	 * @param sensor
	 * @param modelParam
	 * @return
	 */
	public abstract boolean downIceThincknessModelParam(Sensor sensor, ModelParam modelParam);
	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 覆冰
	 * @param sensor
	 * @return
	 */
	public abstract ModelParam upContaminationModelParam(Sensor sensor);
	
	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 污秽度测试
	 * @param sensor
	 * @param modelParam
	 * @return
	 */
	public abstract boolean downContaminationModelParam(Sensor sensor, ModelParam modelParam);
	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 污秽度测试
	 * @param sensor
	 * @return
	 */
	public abstract ModelParam upIceThincknessModelParam(Sensor sensor);	
	

	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 地质滑坡
	 * @param sensor
	 * @return
	 */
	public abstract ModelParam upLandslideModelParam(Sensor sensor);	

	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 地质滑坡
	 * @param sensor
	 * @param modelParam
	 * @return
	 */
	public abstract boolean downLandslideModelParam(Sensor sensor, ModelParam modelParam);

	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 微气象
	 * @param sensor
	 * @return
	 */
	public abstract AlarmParam upWeatherAlarmParam(Sensor sensor);

	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 微气象
	 * @param sensor
	 * @param alarmParam
	 * @return
	 */
	public abstract boolean downWeatherAlarmParam(Sensor sensor, AlarmParam alarmParam);
	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 杆塔倾斜
	 * @param sensor
	 * @return
	 */
	public abstract AlarmParam upTowerTiltAlarmParam(Sensor sensor);
	
	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 杆塔倾斜
	 * @param sensor
	 * @param alarmParam
	 * @return
	 */
	public abstract boolean downTowerTiltAlarmParam(Sensor sensor, AlarmParam alarmParam);	


	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 覆冰
	 * @param sensor
	 * @return
	 */
	public abstract AlarmParam upIceThincknessAlarmParam(Sensor sensor);
	
	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 覆冰
	 * @param sensor
	 * @param alarmParam
	 * @return
	 */
	public abstract boolean downIceThincknessAlarmParam(Sensor sensor, AlarmParam alarmParam);	
	
	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 覆冰
	 * @param sensor
	 * @return
	 */
	public abstract AlarmParam upContaminationAlarmParam(Sensor sensor);
	
	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 覆冰
	 * @param sensor
	 * @param alarmParam
	 * @return
	 */
	public abstract boolean downContaminationAlarmParam(Sensor sensor, AlarmParam alarmParam);		
	
	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 地质滑坡
	 * @param sensor
	 * @param alarmParam
	 * @return
	 */
	public abstract boolean downLandslideAlarmParam(Sensor sensor, AlarmParam alarmParam);
	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 地质滑坡
	 * @param sensor
	 * @return
	 */
	public abstract AlarmParam upLandslideAlarmParam(Sensor sensor);



	/**
	 * 召测终端通讯参数（0xA7，监测装置指向上位机的信息查询）
	 */
	public abstract MasterStation upMasterStation(Sensor sensor);

	/**
	 * 下发终端通讯参数（0xA7，监测装置指向上位机的信息设置）
	 */
	public abstract boolean downMasterStation(Sensor sensor, MasterStation masterStation);

	/**
	 * 终端状态监测（0xAC，装置ID查询）
	 * 
	 */
	public abstract DeviceID upDeviceID(Sensor sensor);

	/**
	 * 终端状态监测（0xAC，装置ID查询/设置）
	 * 
	 */
	public abstract boolean downDeviceID(Sensor sensor, DeviceID deviceID);

	/**
	 * 终端复位（0xAD，装置复位）
	 * 
	 * @param sensor
	 * @param deviceReset
	 * @return
	 */
	public abstract boolean downDeviceReset(Sensor sensor, DeviceReset deviceReset);
	
	/**
	 * 请求上传历史数据
	 * @param sensor
	 * 	传感器
	 * @param packetType
	 *  数据类型
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public abstract boolean requestUploadData(Sensor sensor, PacketType packetType,Date beginTime,Date endTime);
	
}
