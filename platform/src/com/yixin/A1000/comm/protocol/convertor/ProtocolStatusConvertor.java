/*
 * Project platform
 *
 * Class ProtocolSamplingConvertor.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 上午09:11:25
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
import java.util.ArrayList;
import java.util.List;

import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;

/**
 * 工作状态数据报 转换类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolStatusConvertor extends ProtocolConvertor {
//
//	/** 协议日志记录器 */
//	private static final Logger protocolLogger = Logger.getLogger("protocolLogger");

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

	public byte[] upOnlineDeviceStatus2Bytes() {
		return "listdtu".getBytes();
	}

	/**
	 * 在线终端状态
	 * 
	 * @param data
	 * @return
	 */
	/*
	 * ID，IP，端口，登录时间，最后活动时间; 00000000000000003,127.0.0.1,3321,2012-09-11
	 * 15:58:18,2012-09-12 11:29:58; 00000000000000004,127.0.0.1,3321,2012-09-11
	 * 15:58:18,2012-09-12 11:29:58; 00000000000000000,127.0.0.1,3321,2012-09-11
	 * 15:58:22,2012-09-12 11:30:07; 00000000000000001,127.0.0.1,3321,2012-09-11
	 * 15:58:22,2012-09-12 11:29:58; 00000000000000002,127.0.0.1,3321,2012-09-11
	 * 15:58:23,2012-09-12 11:29:58;
	 */
	public List<OnlineDeviceStatus> bytes2UpOnlineDeviceStatus(byte[] data) {
		List<OnlineDeviceStatus> result = new ArrayList<OnlineDeviceStatus>();
		String str = new String(data);
		//String 的 split(";")方法，将字符串用分号隔开。
		String[] strs = str.split(";");
		 OnlineDeviceStatus deviceStatus;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (String singleStr : strs) {
			String[] strArray = singleStr.split(",");

			if ( strArray.length  > 4 ) { // 协议格式错误：协议长度不足
			{
					
				String cmd_ID = strArray[0];// ID
				String ip = strArray[1];// IP
				String port = strArray[2];// 端口
				String logon_time = strArray[3];// 登录时间
				String last_comm_time = strArray[4];// 最后活动时间

				deviceStatus = new OnlineDeviceStatus();
				deviceStatus.setIp(ip);
				try {
//					parse方法，sdf.parse(last_comm_time);表示返回last_comm_time的整数值，
					deviceStatus.setLastCommTime(sdf.parse(last_comm_time));
					deviceStatus.setLogonTime(sdf.parse(logon_time));
				} catch (ParseException e) {
				}
				deviceStatus.setPort(Integer.parseInt(port));
				deviceStatus.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
				result.add(deviceStatus);
			    }
			}	
		}
		return result;
	}
}
