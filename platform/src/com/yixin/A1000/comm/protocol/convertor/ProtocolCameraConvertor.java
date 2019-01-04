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

import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.protocol.CameraControlType;
import com.yixin.A1000.comm.protocol.CommandStatusType;
import com.yixin.A1000.comm.protocol.FrameType;
import com.yixin.A1000.comm.protocol.PacketType;



/**
 * 通讯协议 转换类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolCameraConvertor {
	protected SensorService sensorService;

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}
	
	// 打开时间
	public byte[] OpenCameraPower2Bytes(String cmdId, Integer minute) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.IMAGE_DOWN.getHexCode());
		sb.append("," + PacketType.IMAGE_CONTROL.getHexCode());
		sb.append(",0,0");
		sb.append("," + CameraControlType.OPEN_POWER_EX.getHexCode());
		sb.append("," + minute.toString());
		// this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}
	//打开时间
	public boolean bytes2OpenCameraPower(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		// this.log(str, false);
		//String command_Status = strs[3]; // 数据发送状态：①0xFF成功 ②0x00失败
		//return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(command_Status) ? true : false;
		//String cmd = strs[2]; // 数据发送状态：①0xFF成功 ②0x00失败
		//return CommandStatusType.SUCCESS.getHexCode().equalsIgnoreCase(cmd) ? true : false;
		return true;
	}
	
}
