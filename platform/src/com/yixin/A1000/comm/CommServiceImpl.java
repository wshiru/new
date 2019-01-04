/*
 * Project platform
 *
 * Class CommServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-30 上午09:20:21
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.comm.protocol.convertor.ProtocolCameraConvertor;
import com.yixin.A1000.comm.protocol.convertor.ProtocolControlConvertor;
import com.yixin.A1000.comm.protocol.convertor.ProtocolSamplingConvertor;
import com.yixin.A1000.comm.protocol.convertor.ProtocolStatusConvertor;
import com.yixin.A1000.comm.protocol.validator.ProtocolCameraValidator;
import com.yixin.A1000.comm.protocol.validator.ProtocolControlValidator;
import com.yixin.A1000.comm.protocol.validator.ProtocolSamplingValidator;
import com.yixin.A1000.comm.protocol.validator.ProtocolStatusValidator;
import com.yixin.A1000.comm.tcp.ShortTCPClient;
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
 * 通讯
 * 
 * @version v1.0.0
 * @author 
 */
public class CommServiceImpl implements CommService {

	public static InetSocketAddress server;
	public static long timeout;

	/** 控制数据报 转换类 */
	private ProtocolControlConvertor controlConvertor;

	/** 控制数据报 验证类 */
	private ProtocolControlValidator controlValidator;

	/** 监测数据报 转换类 */
	private ProtocolSamplingConvertor samplingConvertor;

	/** 监测数据报 校验类 */ 
	private ProtocolSamplingValidator samplingValidator;
	
	/** 工作状态数据报 转换类 */
	private ProtocolStatusConvertor statusConvertor;
	
	/** 工作状态数据报 校验类 */
	private ProtocolStatusValidator statusValidator;
	
	/** 摄像机控制  转换类*/
	private ProtocolCameraConvertor cameraConvertor;
	
	/** 摄像机控制 校验类 */
	private ProtocolCameraValidator cameraValidator;

	/**
	 * 设置 控制数据报 转换类
	 * 
	 * @param controlConvertor
	 *            控制数据报 转换类
	 */
	public void setControlConvertor(ProtocolControlConvertor controlConvertor) {
		this.controlConvertor = controlConvertor;
	}

	/**
	 * 设置 控制数据报 验证类
	 * 
	 * @param controlValidator
	 *            控制数据报 验证类
	 */
	public void setControlValidator(ProtocolControlValidator controlValidator) {
		this.controlValidator = controlValidator;
	}

	/**
	 * 设置 监测数据报 转换类
	 * 
	 * @param samplingConvertor
	 *            监测数据报 转换类
	 */
	public void setSamplingConvertor(ProtocolSamplingConvertor samplingConvertor) {
		this.samplingConvertor = samplingConvertor;
	}

	/**
	 * 设置 监测数据报 校验类
	 * 
	 * @param samplingValidator
	 *            监测数据报 校验类
	 */
	public void setSamplingValidator(ProtocolSamplingValidator samplingValidator) {
		this.samplingValidator = samplingValidator;
	}

	/**
	 * 设置 工作状态数据报 转换类
	 *
	 * @param statusConvertor 工作状态数据报 转换类
	 */
	public void setStatusConvertor(ProtocolStatusConvertor statusConvertor) {
		this.statusConvertor = statusConvertor;
	}

	/**
	 * 设置 工作状态数据报 校验类
	 *
	 * @param statusValidator 工作状态数据报 校验类
	 */
	public void setStatusValidator(ProtocolStatusValidator statusValidator) {
		this.statusValidator = statusValidator;
	}

	
	/**
	 * 设置 摄像机控制 转换类
	 *
	 * @param statusConvertor 摄像机控制 转换类
	 */
	public void setConvertor(ProtocolCameraConvertor cameraConvertor) {
		this.cameraConvertor = cameraConvertor;
	}
	
	/**
	 * 设置 摄像机控制 转换类
	 *
	 * @param statusValidator 摄像机控制 转换类
	 */
	public void setCameraConvertor(ProtocolCameraConvertor cameraConvertor) {
		this.cameraConvertor = cameraConvertor;
	}	

	/**
	 * 设置 摄像机控制 校验类
	 *
	 * @param statusValidator 摄像机控制 校验类
	 */
	public void setCameraValidator(ProtocolCameraValidator cameraValidator) {
		this.cameraValidator = cameraValidator;
	}
	
	// ======================================================================================
	// 监测数据报
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.comm.CommService#towertilt()
	 */
	@Override
	public TowerTiltSampling towertilt(String cmdId) {
		byte[] sendData = this.samplingConvertor.towerTile2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.samplingValidator.validate(recvData);
		this.samplingValidator.validateTowerTilt(recvData);
		return this.samplingConvertor.bytes2TowerTilt(recvData);
	}
	
	
	@Override
	public IceThincknessSampling iceThinckness(String cmdId) {
		byte[] sendData = this.samplingConvertor.iceThinckness2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.samplingValidator.validate(recvData);
		this.samplingValidator.validateIceThinckness(recvData);
		return this.samplingConvertor.bytes2IceThinckness(recvData);
	}
	
	@Override
	public ContaminationSampling contamination(String cmdId) {
		byte[] sendData = this.samplingConvertor.contamination2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.samplingValidator.validate(recvData);
		this.samplingValidator.validateContamination(recvData);
		return this.samplingConvertor.bytes2Contamination(recvData);
	}

 	
	
	
	@Override
	public WeatherSampling weather(String cmdId) {
		byte[] sendData = this.samplingConvertor.weather2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.samplingValidator.validate(recvData);
		this.samplingValidator.validateWeather(recvData);
		return this.samplingConvertor.bytes2Weather(recvData);
	}	
	
	@Override
	public LandslideSampling landslide(String cmdId) {
		byte[] sendData = this.samplingConvertor.landslide2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.samplingValidator.validate(recvData);
		this.samplingValidator.validateLandslide(recvData);
		return this.samplingConvertor.bytes2Landslide(recvData);
	}	
	
	// ======================================================================================
	// 控制数据报
	@Override
	public DeviceTime upDeviceTime(String cmdId) {
		byte[] sendData = this.controlConvertor.upDeviceTime2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateDeviceTime(recvData);
		return this.controlConvertor.bytes2UpDeviceTime(recvData);
	}

	@Override
	public DeviceTime downDeviceTime(String cmdId) {
		byte[] sendData = this.controlConvertor.downDeviceTime2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateDeviceTime(recvData);
		return this.controlConvertor.bytes2DownDeviceTime(recvData);
	}

	@Override
	public NetworkAdapter upNetworkAdapter(String cmdId) {
		byte[] sendData = this.controlConvertor.upNetworkAdapter2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateNetworkAdapter(recvData);
		return this.controlConvertor.bytes2UpNetworkAdapter(recvData);
	}

	@Override
	public boolean downNetworkAdapter(String cmdId, NetworkAdapter networkAdapter) {
		byte[] sendData = this.controlConvertor.downNetworkAdapter2Bytes(cmdId, networkAdapter);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateNetworkAdapter(recvData);
		return this.controlConvertor.bytes2DownNetworkAdapter(recvData);
	}

	@Override
	public SamplingParam upSamplingParam(String cmdId,PacketType packetType) {
		byte[] sendData = this.controlConvertor.upSamplingParam2Bytes(cmdId,packetType);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateSamplingParam(recvData,packetType);
		return this.controlConvertor.bytes2UpSamplingParam(recvData);
	}

	@Override
	public boolean downSamplingParam(String cmdId,PacketType packetType, SamplingParam samplingParam) {
		byte[] sendData = this.controlConvertor.downSamplingParam2Bytes(cmdId,packetType, samplingParam);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateSamplingParam(recvData,packetType);
		return this.controlConvertor.bytes2DownSamplingParam(recvData);
	}

	@Override
	public ModelParam upModelParam(String cmdId,PacketType packetType) {
		byte[] sendData = this.controlConvertor.upModelParam2Bytes(cmdId,packetType);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateModelParam(recvData,packetType);
		return this.controlConvertor.bytes2UpModelParam(recvData);
	}

	@Override
	public boolean downModelParam(String cmdId,PacketType packetType, ModelParam modelParam) {
		byte[] sendData = this.controlConvertor.downModelParam2Bytes(cmdId,packetType, modelParam);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateModelParam(recvData,packetType);
		return this.controlConvertor.bytes2DownModelParam(recvData);
	}

	@Override
	public AlarmParam upAlarmParam(String cmdId,PacketType packetType) {
		byte[] sendData = this.controlConvertor.upAlarmParam2Bytes(cmdId,packetType);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateAlarmParam(recvData,packetType);
		return this.controlConvertor.bytes2UpAlarmParam(recvData);
	}

	@Override
	public boolean downAlarmParam(String cmdId ,PacketType packetType, AlarmParam towerTiltAlarmParam) {
		byte[] sendData = this.controlConvertor.downAlarmParam2Bytes(cmdId,packetType,towerTiltAlarmParam);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateAlarmParam(recvData,packetType);
		return this.controlConvertor.bytes2DownAlarmParam(recvData);
	}
	
	/* (non-Javadoc)
	 * @see com.yixin.ca2000.comm.CommService#upMasterStation(java.lang.String)
	 */
	@Override
	public MasterStation upMasterStation(String cmdId) {
		byte[] sendData = this.controlConvertor.upMasterStation2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateMasterStation(recvData);
		return this.controlConvertor.bytes2UpMasterStation(recvData);
	}

	/* (non-Javadoc)
	 * @see com.yixin.ca2000.comm.CommService#downMasterStation(java.lang.String, com.yixin.ca2000.settings.model.MasterStation)
	 */
	@Override
	public boolean downMasterStation(String cmdId, MasterStation masterStation) {
		byte[] sendData = this.controlConvertor.downMasterStation2Bytes(cmdId, masterStation);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateMasterStation(recvData);
		return this.controlConvertor.bytes2DownMasterStation(recvData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.comm.CommService#upDeviceID(java.lang.String)
	 */
	@Override
	public DeviceID upDeviceID(String cmdId) {
		byte[] sendData = this.controlConvertor.upDeviceID2Bytes(cmdId);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateDeviceID(recvData);
		return this.controlConvertor.bytes2UpDeviceID(recvData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.comm.CommService#downDeviceID(java.lang.String,
	 * com.yixin.ca2000.settings.model.DeviceID)
	 */
	@Override
	public boolean downDeviceID(String cmdId, DeviceID deviceID) {
		byte[] sendData = this.controlConvertor.downDeviceID2Bytes(cmdId, deviceID);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateDeviceID(recvData);
		return this.controlConvertor.bytes2DownDeviceID(recvData);
	}
	/**
	 * 复位
	 */
	@Override
	public boolean downDeviceReset(String cmdId, DeviceReset deviceReset) {
		byte[] sendData = this.controlConvertor.downDeviceReset2Bytes(cmdId, deviceReset);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateDeviceReset(recvData);
		return this.controlConvertor.bytes2DownDeviceReset(recvData);
	}

	/**
	 * 请求上传历史数据
	 */
	@Override
	public boolean requestUploadData(String cmdId, PacketType packetType,
			Date beginTime, Date endTime) {
		byte[] sendData = this.controlConvertor.requestUploadData(cmdId,packetType,beginTime,endTime);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.controlValidator.validate(recvData);
		this.controlValidator.validateRequestUploadData(recvData);
		return this.controlConvertor.bytes2RequestUploadData(recvData);
		
	}	
	
	// ======================================================================================
	// 远程图像数据报

	// ======================================================================================
	// 工作状态报

	/* (non-Javadoc)
	 * @see com.yixin.ca2000.comm.CommService#getOnlineDeviceStatus()
	 */
	@Override
	public List<OnlineDeviceStatus> getOnlineDeviceStatus() {
		byte[] sendData = this.statusConvertor.upOnlineDeviceStatus2Bytes();
		byte[] recvData = new ShortTCPClient(server).sendSpecificCmd(sendData, timeout);
		recvData = this.statusValidator.validate(recvData);
		this.statusValidator.validateOnlineDeviceStatus(recvData);
		return this.statusConvertor.bytes2UpOnlineDeviceStatus(recvData);
	}
	/**
	 * 打开电源
	 */
	
	@Override
	public boolean openVideoPower(String cmdId, Integer minute) {
		byte[] sendData = this.cameraConvertor.OpenCameraPower2Bytes(cmdId, minute);
		byte[] recvData = new ShortTCPClient(server).sendCharacterStream(sendData, timeout);
		recvData = this.cameraValidator.validate(recvData);
		this.cameraValidator.validateOpenCameraPower(recvData);		
		return this.cameraConvertor.bytes2OpenCameraPower(recvData);
	}


	
	
	// ======================================================================================
	// 同步数据
}
