/*
 * Project platform
 *
 * Class ProtocolImageValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 下午02:33:39
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.protocol.ProtocolErrorCode;

/**
 * 远程图像数据报 验证类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolCameraValidator extends ProtocolValidator {
	// 0xC9，图像采集参数设置
	// 0xCA，拍照时间表设置
	// 0xCB，手动请求拍摄照片
	// 0xCC，采集装置请求上送照片
	// 0xCD，远程图像数据报
	// 0xCE，远程图像数据上送结束标记
	// 0xCF，远程图像补包数据下发
	// 0xD0，摄像机远程调节
	// 0xD1，启动 / 终止摄像视频传输
	// 0xD2，设置状态监测装置保存的服务器地址
	// 0xD3，终止状态监测装置与服务器的连接
	// 0xD4，请求/返回/通知状态监测装置基本信息
	// 0xD5～// 0xE5，远程图像数据报预留字段
	// 0xA1，监测装置时间查询/设置
	public void validateOpenCameraPower(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 5) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String request_Set_Flag = strs[3]; // 参数配置类型标识：①0x00查询配置信息
		// ②0x01设置配置信息
		//String clocktime_Stamp = strs[4]; // 设置时间

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		//try {
			//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clocktime_Stamp);
		//} catch (ParseException e) {// 数据格式错误：时间格式错误
			//throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		//}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

}
