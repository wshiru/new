/*
 * Project platform
 *
 * Class CommService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-30 上午09:11:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm;

import java.util.Date;
import java.util.List;

import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.DeviceID;
import com.yixin.A1000.settings.model.DeviceReset;
import com.yixin.A1000.settings.model.DeviceTime;
import com.yixin.A1000.settings.model.MasterStation;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.NetworkAdapter;
import com.yixin.A1000.settings.model.SamplingParam;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;

/**
 * 通讯服务类
 * 
 * @version v1.0.0
 * @author 
 */
public interface CommService {

	// ======================================================================================
	// 监测数据报
	/**
	 * 杆塔倾斜实时抄读
	 * 
	 * @param cmdId
	 *            监测装置ID
	 * @exception TimeOutException
	 *                接收数据等待的时间超出所设置值timeout时，抛出该异常
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出该异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出该异常
	 * @exception ProtocolException
	 *                协议处理错误
	 * @exception TCPServerException
	 *                服务器异常
	 * @see com.yixin.A1000.comm.exception.ProtocolException
	 * @see com.yixin.A1000.comm.protocol.ProtocolErrorCode
	 * @see com.yixin.A1000.comm.protocol.TCPServerException
	 * @return
	 */
	public abstract TowerTiltSampling towertilt(String cmdId);
	
	/**
	 * 覆冰监测数据实时抄读
	 * @param cmdId	
	 * @return
	 */	
	public abstract IceThincknessSampling iceThinckness(String cmdId);
	
	/**
	 * 污秽度数据抄读
	 * @param cmdId
	 * @return
	 */
	public abstract ContaminationSampling contamination(String cmdId);
	
	/**
	 * 微气象数据抄读
	 * @param cmdId
	 * @return
	 */
	public abstract WeatherSampling weather(String cmdId);
	
	/**
	 * 地质守灾害数据抄读
	 * @param cmdId
	 * @return
	 */
	public abstract LandslideSampling landslide(String cmdId);

	// ======================================================================================
	// 控制数据报
	/**
	 * 召测时间（0xA1，监测装置时间查询）
	 * 
	 * @param cmdId
	 *            监测装置ID
	 */
	public abstract DeviceTime upDeviceTime(String cmdId);

	/**
	 * 下发时间（0xA1，监测装置时间设置）
	 */
	public abstract DeviceTime downDeviceTime(String cmdId);

	/**
	 * （0xA2，监测装置网络适配器查询）
	 * 
	 */
	public abstract NetworkAdapter upNetworkAdapter(String cmdId);

	/**
	 * （0xA2，监测装置网络适配器设置）
	 * 
	 */
	public abstract boolean downNetworkAdapter(String cmdId, NetworkAdapter networkAdapter);

	// 0xA3，上级设备请求数据

	/**
	 * 召测终端采样间隔（0xA4，监测装置采样参数查询）
	 * 
	 */
	public abstract SamplingParam upSamplingParam(String cmdId ,PacketType packetType);

	/**
	 * 下发终端采样间隔（0xA4，监测装置采样参数设置）
	 * 
	 */
	public abstract boolean downSamplingParam(String cmdId,PacketType packetType, SamplingParam samplingParam);

	/**
	 * 召测终端模型参数(0xA5，模型参数配置信息查询)
	 * 
	 */
	public abstract ModelParam upModelParam(String cmdId,PacketType packetType);

	/**
	 * 下发终端模型参数(0xA5，模型参数配置信息设置)
	 * 
	 */
	public abstract boolean downModelParam(String cmdId,PacketType packetType, ModelParam modelParam);

	/**
	 * 召测终端报警阈值（0xA6，报警阈值查询）
	 * 
	 */
	public abstract AlarmParam upAlarmParam(String cmdId,PacketType packetType);

	/**
	 * 下发终端报警阈值（0xA6，报警阈值设置）
	 * 
	 */
	public abstract boolean downAlarmParam(String cmdId,PacketType packetType, AlarmParam towerTiltAlarmParam);

	// 0xA7，监测装置指向上位机的信息查询/设置
	/**
	 * 召测终端通讯参数（0xA7，监测装置指向上位机的信息查询）
	 */
	public abstract MasterStation upMasterStation(String cmdId);

	/**
	 * 下发终端通讯参数（0xA7，监测装置指向上位机的信息设置）
	 */
	public abstract boolean downMasterStation(String cmdId, MasterStation masterStation);

	// 0xA8，基本信息查询/设置
	// 0xA9，远程升级数据报：软件数据报
	// 0xAA，远程升级数据报：软件数据报下发结束
	// 0xAB，远程升级数据报：软件数据报补包数据
	// 0xAC，装置ID查询/设置

	/**
	 * 终端状态监测（0xAC，装置ID查询）
	 * 
	 */
	public abstract DeviceID upDeviceID(String cmdId);

	/**
	 * 终端状态监测（0xAC，装置ID查询/设置）
	 * 
	 */
	public abstract boolean downDeviceID(String cmdId, DeviceID deviceID);

	/**
	 * 终端复位（0xAD，装置复位）
	 * 
	 */
	public abstract boolean downDeviceReset(String cmdId, DeviceReset deviceReset);
	
	/**
	 * 打开视频设备的电源
	 * @param cmdId		cmdId
	 * @param minute   打开的时间
	 * @return
	 */
	public abstract boolean openVideoPower(String cmdId, Integer minute);
	
	/**
	 * 请求上传历史数据
	 * @param cmdId
	 * @param packetType
	 * @return
	 */	
	public abstract boolean requestUploadData(String cmdId, PacketType packetType,Date beginTime,Date endTime);
	// 0xAE，装置苏醒时间设置
	// 0xAF，气象参数
	// 0xB0，杆塔倾斜参数
	// 0xB1，导地线微风振动参数
	// 0xB2，导线弧垂参数
	// 0xB3，导线温度参数
	// 0xB4，覆冰参数
	// 0xB5，导线风偏参数
	// 0xB6，导地线舞动参数
	// 0xB7，现场污秽度参数
	// 0xB8～// 0xC8，控制数据报预留字段

	// ======================================================================================
	// 远程图像数据报

	// ======================================================================================
	// 工作状态报
	/**
	 * 在线终端（“listdtu”）
	 */
	public abstract List<OnlineDeviceStatus> getOnlineDeviceStatus();

		
	// ======================================================================================
	// 同步数据
}
