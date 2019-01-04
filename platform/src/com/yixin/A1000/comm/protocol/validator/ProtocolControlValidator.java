/*
 * Project platform
 *
 * Class ProtocoControlValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 下午02:33:07
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
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.comm.protocol.ProtocolErrorCode;

/**
 * 控制数据报 验证类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolControlValidator extends ProtocolValidator {

	// 0xA1，监测装置时间查询/设置
	public void validateDeviceTime(byte[] data) {

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
		String clocktime_Stamp = strs[4]; // 设置时间

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(clocktime_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xA2，监测装置网络适配器查询/设置
	public void validateNetworkAdapter(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 9) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0];// 状态监测装置ID（17位编码）
		// String frame_Type = strs[1];// 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2];// 报文类型—参考表2-55相关含义
		// String request_Set_Flag = strs[3];// 参数配置类型标识：①0x00查询配置信息②0x01设置配置信息
		// String ip = strs[4];// 状态监测装置IP地址
		// String subnet_mask = strs[5];// 子网掩码
		// String gateway = strs[6];// 网关
		// String dns_Server = strs[7];// DNS 服务器
		// String reserve = strs[8];// 备用

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xA3，上级设备请求数据
	// 0xA4，监测装置采样参数查询/设置
	// 0xA5，模型参数配置信息查询/设置
	public void validateModelParam(byte[] data,PacketType packetType) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 6) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; //数据发送状态：①0xFF成功②0x00失败
		// String request_Type= strs[4]; //配置的参数类型—参考表2-55相关含义
		String config_Total = strs[5]; // 配置参数个数

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		int count = 0;
		try {
			count = Integer.parseInt(config_Total);
		} catch (NumberFormatException e) {// 数据格式错误：数据无法转换至对应格式
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
		}
		int len = 6 + count * 2;

		if (strs.length < len) {// 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xA6，报警阈值查询/设置
	// 0xA6，杆塔倾斜报警阈值查询/设置
	public void validateAlarmParam(byte[] data,PacketType packetType) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 6) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String request_Set_Flag = strs[3]; // 参数配置类型标识：①0x00查询报警信息②0x01设置报警信息
		// String request_Type = strs[4]; // 配置的参数类型—参考表2-55相关含义
		String alarm_Total = strs[5]; // 报警参数个数

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		int count = 0;
		try {
			Integer.parseInt(alarm_Total);
		} catch (NumberFormatException e) {// 数据格式错误：数据无法转换至对应格式
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
		}
		int len = 6 + count * 2;

		if (strs.length < len) {// 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xA7，监测装置指向上位机的信息查询/设置
	public void validateMasterStation(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 8) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; // 数据发送状态：①0xFF成功；②0x00失败
		// String ip_Address = strs[4]; // 上位机IP地址
		String port = strs[5]; // 上位机端口号
		// String domain_Name = strs[6]; // 上位机域名，以’\0’结尾ASCII字符串，占用64Byte
		// String reserve = strs[7]; // 备用

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		// 数据格式错误：时间格式错误
		try {
			Integer.parseInt(port);
		} catch (NumberFormatException e) {// 数据格式错误：数据无法转换至对应格式
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xA8，基本信息查询/设置
	// 0xA9，远程升级数据报：软件数据报
	// 0xAA，远程升级数据报：软件数据报下发结束
	// 0xAB，远程升级数据报：软件数据报补包数据
	// 0xAC，装置ID查询/设置
	public void validateDeviceID(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 6) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; //报文类型—参考表2-55相关含义
		// String command_Status = strs[3]; //数据发送状态：①0xFF成功；②0x00失败
		String component_ID = strs[4]; // 被监测设备ID（17位编码）
		String original_ID = strs[5]; // 原始ID

		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		// 数据格式错误：时间格式错误
		try {
			Integer.parseInt(original_ID);
		} catch (NumberFormatException e) {// 数据格式错误：数据无法转换至对应格式
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xAD，装置复位
	public void validateDeviceReset(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 4) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type= strs[2]; // 报文类型—参考表2-55相关含义
		// String command_Status= strs[3]; // 数据发送状态：①0xFF成功；②0x00失败

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		// 数据格式错误：时间格式错误

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}
	public void validateRequestUploadData(byte[] data){
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 4) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; //帧类型—参考表2-54相关含义
		// String packet_Type= strs[2]; // 报文类型—参考表2-55相关含义
		// String command_Status= strs[3]; // 数据发送状态：①0xFF成功；②0x00失败

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
	}

	// 0xAE，装置苏醒时间设置
	// 0xAF，气象参数

	// 0xB0，杆塔倾斜参数
	public void validateSamplingParam(byte[] data,PacketType packetType) {

		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 9) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0];// 状态监测装置ID（17位编码）
		// String frame_Type = strs[1];//帧类型—参考表2-54相关含义
		// String packet_Type = strs[2];//报文类型—参考表2-55相关含义
		// String command_Status = strs[3];//数据发送状态：①0xFF成功②0x00失败
		// String request_Type = strs[4];//配置的参数类型—参考表2-55相关含义
		String main_Time = strs[5];// 采集时间周期重新设定，表示分钟数
		String sample_Count = strs[6];// 高速采样点数
		String sample_Frequency = strs[7];// 高速采样频率
		// String reserve = strs[8];// 备用

		// ID校验//
		if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			Integer.parseInt(main_Time);
			Integer.parseInt(sample_Count);
			Integer.parseInt(sample_Frequency);
		} catch (NumberFormatException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
		}

		// 数据内容校验
		// 数据内容错误：监测类型与监测参数不一致
		// 数据内容错误：监测类型不存在
		// 数据内容错误：监测参数不存在
		// 数据内容错误：监测参数缺失
		// 数据内容错误：数据不符合业务规范
		//
		// ERROR_99xxxxx("99xxxxx", "未知错误类型");//
	}

	// 0xB1，导地线微风振动参数
	// 0xB2，导线弧垂参数
	// 0xB3，导线温度参数
	// 0xB4，覆冰参数
	// 0xB5，导线风偏参数
	// 0xB6，导地线舞动参数
	// 0xB7，现场污秽度参数
	// 0xB8～// 0xC8，控制数据报预留字段
}
