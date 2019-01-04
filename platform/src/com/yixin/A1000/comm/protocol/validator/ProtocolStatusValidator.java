/*
 * Project platform
 *
 * Class ProtocolStatusValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 下午02:34:10
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
 * 工作状态数据报 验证类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolStatusValidator extends ProtocolValidator {
	// 0xE6，心跳数据报
	// 0xE7，基本信息报
	// 0xE8，工作状态报
	// 0xE9，故障信息报
	// 0xEA～// 0xFF，其他报文预留字段

	// 在线终端状态
	public void validateOnlineDeviceStatus(byte[] data) {

		String str = new String(data);
		String[] strs = str.split(";");

	//	if ( strs.length  >  0  ){
			
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  for (String singleStr : strs) {
			String[] strArray = singleStr.split(",");

			//if (strArray.length >  1  ) {
				
			//}
			
			
			// 协议格式
			if ( strArray.length  > 4 ) { // 协议格式错误：协议长度不足
			
				///throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
			
				String cmd_ID = strArray[0];// ID
				// String ip = strArray[1];// IP
				String port = strArray[2];// 端口
				String logon_time = strArray[3];// 登录时间
				String last_comm_time = strArray[4];// 最后活动时间

				// ID校验//
				if (17 != cmd_ID.length()) { // ID校验错误：ID不合法
					throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
				}
				// ID校验错误：ID不存在
				// ID校验错误：CMA与所辖状态监测装置不匹配
				// ID校验错误：状态监测装置与被监测设备不匹配

				// 数据格式校验
				try {
					sdf.parse(last_comm_time);
					sdf.parse(logon_time);
				} catch (ParseException e) {// 数据格式错误：时间格式错误
					throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
				}
				try {
					Integer.parseInt(port);
				} catch (NumberFormatException e) {// 数据格式错误：数据无法转换至对应格式
					throw new ProtocolException(ProtocolErrorCode.ERROR_0102002, str);
				}
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
		}
	
}
