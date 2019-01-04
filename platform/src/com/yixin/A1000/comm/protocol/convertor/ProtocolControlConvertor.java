/*
 * Project platform
 *
 * Class ProtocolControlConvertor.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:06:40
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.protocol.CommandStatusType;
import com.yixin.A1000.comm.protocol.DeviceResetModelType;
import com.yixin.A1000.comm.protocol.FrameType;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.comm.protocol.RequestType;
import com.yixin.A1000.settings.model.AlarmParam;
import com.yixin.A1000.settings.model.DeviceID;
import com.yixin.A1000.settings.model.DeviceReset;
import com.yixin.A1000.settings.model.DeviceTime;
import com.yixin.A1000.settings.model.MasterStation;
import com.yixin.A1000.settings.model.ModelParam;
import com.yixin.A1000.settings.model.NetworkAdapter;
import com.yixin.A1000.settings.model.SamplingParam;

/**
 * 控制数据报 转换类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolControlConvertor extends ProtocolConvertor {

	/** 协议日志记录器 */
	// private static final Logger protocolLogger =
	// Logger.getLogger("protocolLogger");

	protected SensorService sensorService;

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	// private void log(String str, boolean isSend) {
	// if (isSend) {
	// protocolLogger.debug("SEND  " + str);
	// } else {
	// protocolLogger.debug("RECV  " + str);
	// }
	// }

	// ============================================================================================
	// 终端时间
	public byte[] upDeviceTime2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DEVICE_TIME.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public DeviceTime bytes2UpDeviceTime(byte[] data) {
		DeviceTime deviceTime = new DeviceTime();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		String clocktime_Stamp = strs[4]; // 设置时间

		deviceTime.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		try {
			deviceTime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clocktime_Stamp));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return deviceTime;
	}

	public byte[] downDeviceTime2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DEVICE_TIME.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public DeviceTime bytes2DownDeviceTime(byte[] data) {
		DeviceTime deviceTime = new DeviceTime();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		String clocktime_Stamp = strs[4]; // 当前时间

		deviceTime.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		try {
			deviceTime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clocktime_Stamp));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (CommandStatusType.FAIL.getHexCode().equalsIgnoreCase(command_Status)) {
			deviceTime.setTime(null);
		}
		return deviceTime;
	}

	// ============================================================================================
	// 状态监测装置网络适配器
	public byte[] upNetworkAdapter2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_NETWORK_ADAPTER.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public NetworkAdapter bytes2UpNetworkAdapter(byte[] data) {
		NetworkAdapter networkAdapter = new NetworkAdapter();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0];// 状态监测装置ID（17位编码）
		// String frame_Type = strs[1];// 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2];// 报文类型—参考表2-55相关含义
		// String command_Status; //数据发送状态：①0xFF成功 ②0x00失败
		String ip = strs[4];// 状态监测装置IP地址
		String subnet_mask = strs[5];// 子网掩码
		String gateway = strs[6];// 网关
		String dns_Server = strs[7];// DNS 服务器
		String reserve = strs[8];// 备用

		networkAdapter.setDnsServer(dns_Server);
		networkAdapter.setGateway(gateway);
		networkAdapter.setIp(ip);
		networkAdapter.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		networkAdapter.setSubnetMask(subnet_mask);
		networkAdapter.setReserve(reserve);
		return networkAdapter;
	}

	public byte[] downNetworkAdapter2Bytes(String cmdId, NetworkAdapter networkAdapter) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_NETWORK_ADAPTER.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + networkAdapter.getIp());
		sb.append("," + networkAdapter.getSubnetMask());
		sb.append("," + networkAdapter.getGateway());
		sb.append("," + networkAdapter.getDnsServer());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownNetworkAdapter(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	// ============================================================================================
	// 杆塔倾斜采样参数
	public byte[] upSamplingParam2Bytes(String cmdId,PacketType packetType) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_SAMPLING_PARAM.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		sb.append("," + packetType.getHexCode());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public SamplingParam bytes2UpSamplingParam(byte[] data) {
		SamplingParam samplingParam = new SamplingParam();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0];// 状态监测装置ID（17位编码）
		// String frame_Type = strs[1];//帧类型—参考表2-54相关含义
		// String packet_Type = strs[2];//报文类型—参考表2-55相关含义
		// String request_Set_Flag = strs[3];//参数配置类型标识：①0x00查询配置信息；②0x01设置配置信息
		// String request_Type = strs[4];//配置的参数类型—参考表2-55相关含义
		String main_Time = strs[5];// 采集时间周期重新设定，表示分钟数
		String sample_Count = strs[6];// 高速采样点数
		String sample_Frequency = strs[7];// 高速采样频率
		String reserve = strs[8];// 备用

		samplingParam.setMainTime(Integer.parseInt(main_Time));
		samplingParam.setReserve(reserve);
		samplingParam.setSampleCount(Integer.parseInt(sample_Count));
		samplingParam.setSampleFrequency(Integer.parseInt(sample_Frequency));
		samplingParam.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return samplingParam;
	}

	public byte[] downSamplingParam2Bytes(String cmdId ,PacketType packetType , SamplingParam samplingParam) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_SAMPLING_PARAM.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + packetType.getHexCode());
		sb.append("," + samplingParam.getMainTime());
		sb.append("," + samplingParam.getSampleCount());
		sb.append("," + samplingParam.getSampleFrequency());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownSamplingParam(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	// ============================================================================================
	// 杆塔倾斜模型参数配置信息
	public byte[] upModelParam2Bytes(String cmdId,PacketType packetType) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_MODEL_PARAM.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		sb.append("," + packetType.getHexCode());
		sb.append(",0");
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public ModelParam bytes2UpModelParam(byte[] data) {
		ModelParam modelParam = new ModelParam();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; //数据发送状态：①0xFF成功②0x00失败
		// String request_Type= strs[4]; //配置的参数类型—参考表2-55相关含义
//		String config_Total = strs[5]; // 配置参数个数

		Map<String, String> params = new TreeMap<String, String>();
		for (int i = 6, len = strs.length; i < len; i++) {
			params.put(strs[i], strs[i + 1]);
			i++;
		}

		modelParam.setParams(params);
		modelParam.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return modelParam;
	}

	public byte[] downModelParam2Bytes(String cmdId, PacketType packetType, ModelParam modelParam) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_MODEL_PARAM.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + packetType.getHexCode());
		sb.append("," + modelParam.getParams().keySet().size());
		for (String param : modelParam.getParams().keySet()) {
			sb.append("," + param);
			sb.append("," + modelParam.getParams().get(param));
		}
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownModelParam(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	// ============================================================================================
	// 杆塔倾斜报警阈值
	public byte[] upAlarmParam2Bytes(String cmdId, PacketType packetType) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_ALARM_PARAM.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		sb.append("," + packetType.getHexCode());
		sb.append(",0");
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public AlarmParam bytes2UpAlarmParam(byte[] data) {
		AlarmParam alarmParam = new AlarmParam();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String request_Set_Flag = strs[3]; // 参数配置类型标识：①0x00查询报警信息②0x01设置报警信息
		// String request_Type = strs[4]; // 配置的参数类型—参考表2-55相关含义
//		String alarm_Total = strs[5]; // 报警参数个数

		Map<String, String> params = new HashMap<String, String>();
		for (int i = 6, len = strs.length; i < len; i++) {
			params.put(strs[i], strs[i + 1]);
			i++;
		}

		alarmParam.setParams(params);
		alarmParam.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return alarmParam;
	}

	public byte[] downAlarmParam2Bytes(String cmdId, PacketType packetType, AlarmParam alarmParam) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_ALARM_PARAM.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + packetType.getHexCode());
		sb.append("," + alarmParam.getParams().keySet().size());
		for (String param : alarmParam.getParams().keySet()) {
			sb.append("," + param);
			sb.append("," + alarmParam.getParams().get(param));
		}
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownAlarmParam(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	// ============================================================================================
	// 上位机信息
	public byte[] upMasterStation2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_MASTER_STATION.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public MasterStation bytes2UpMasterStation(byte[] data) {
		MasterStation masterStation = new MasterStation();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
//		String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
//		String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
//		String command_Status = strs[3]; // 数据发送状态：①0xFF成功；②0x00失败
		String ip_Address = strs[4]; // 上位机IP地址
		String port = strs[5]; // 上位机端口号
		String domain_Name = strs[6]; // 上位机域名，以’\0’结尾ASCII字符串，占用64Byte
		String reserve = strs[7]; // 备用

		masterStation.setDomainName(domain_Name);
		masterStation.setIpAddress(ip_Address);
		masterStation.setPort(Integer.parseInt(port));
		masterStation.setReserve(reserve);
		masterStation.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return masterStation;
	}

	public byte[] downMasterStation2Bytes(String cmdId, MasterStation masterStation) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_MASTER_STATION.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + masterStation.getIpAddress());
		sb.append("," + masterStation.getPort());
		sb.append("," + masterStation.getDomainName());
//		sb.append("," + masterStation.getReserve());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownMasterStation(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	
	// ============================================================================================
	// 监测装置ID
	public byte[] upDeviceID2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DEVICE_ID.getHexCode());
		sb.append("," + RequestType.UP.getHexCode());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public DeviceID bytes2UpDeviceID(byte[] data) {
		DeviceID deviceID = new DeviceID();
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; //数据发送状态：①0xFF成功；②0x00失败
		String component_ID = strs[4]; // 被监测设备ID（17位编码）
		String original_ID = strs[5]; // 原始ID

		deviceID.setComponentID(component_ID);
		deviceID.setOriginalID(Integer.parseInt(original_ID));
		deviceID.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return deviceID;
	}

	public byte[] downDeviceID2Bytes(String cmdId, DeviceID deviceID) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DEVICE_ID.getHexCode());
		sb.append("," + RequestType.DOWN.getHexCode());
		sb.append("," + deviceID.getComponentID());
		sb.append("," + deviceID.getOriginalID());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownDeviceID(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}

	// ============================================================================================
	// 装置复位
	public byte[] downDeviceReset2Bytes(String cmdId, DeviceReset deviceReset) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DEVICE_RESET.getHexCode());
		switch (deviceReset.getResetMode()) {
		case NORMAL_MODE:
			sb.append("," + DeviceResetModelType.NORMAL_MODE.getHexCode());
			break;
		case UPDATE_MODE:
			sb.append("," + DeviceResetModelType.UPDATE_MODE.getHexCode());
			break;
		case DIAGNOSE_MODE:
			sb.append("," + DeviceResetModelType.DIAGNOSE_MODE.getHexCode());
			break;
		case OTHER:
			sb.append("," + DeviceResetModelType.OTHER.getHexCode());
			break;
		}
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}

	public boolean bytes2DownDeviceReset(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}
	
	public byte[] requestUploadData(String cmdId,PacketType packetType, Date beginTime,Date endTime){
		StringBuilder sb = new StringBuilder(cmdId);
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_WEATHER.getHexCode());
		sb.append("," + fd.format(beginTime));
		sb.append("," + fd.format(endTime));
		return sb.toString().getBytes();
	}	
	public boolean bytes2RequestUploadData(byte[] data){
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
	}
}
