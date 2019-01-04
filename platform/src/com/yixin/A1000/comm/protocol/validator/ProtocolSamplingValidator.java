/*
 * Project platform
 *
 * Class ProtocolSamplingValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 下午02:32:29
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
 * 监测数据报 校验类
 * 
 * @version v1.0.0
 * @author 
 */
// 0x01，气象环境类数据报
// 0x02～// 0x0B，气象类数据报预留字段
// 0x0C，杆塔倾斜数据报
// 0x0D～// 0x1D，杆塔类数据报预留字段
// 0x1E，导地线微风振动特征量数据报
// 0x1F，导地线微风振动波形信号数据报
// 0x20，导线弧垂数据报
// 0x21，导线温度数据报
// 0x22，覆冰及不均衡张力差数据报
// 0x23，导线风偏数据报
// 0x24，导地线舞动特征量数据报
// 0x25，导地线舞动轨迹数据报
// 0x26～// 0x46，导地线类数据报预留字段
// 0x47～// 0x5B，金具类数据报预留字段
// 0x5C，现场污秽度数据报
// 0x5E～// 0x6E，绝缘子类数据报预留字段
// 0x6F～// 0x82，杆塔基础类数据报预留字段
// 0x83～// 0x96，附属设施类数据报预留字段
// 0x97～// 0xA0，通道环境类数据报预留字段
public class ProtocolSamplingValidator extends ProtocolValidator {
	
	public void validateTowerTilt(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 11) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		// String alerm_Flag = strs[5]; // 报警标识
		String inclination = strs[6]; // 倾斜度
		String inclination_X = strs[7]; // 顺线倾斜度
		String inclination_Y = strs[8]; // 横向倾斜度
		String angle_X = strs[9]; // 顺线倾斜角
		String angle_Y = strs[10]; // 横向倾斜角
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用

		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}
		try {
			Double.valueOf(inclination);
			Double.valueOf(inclination_X);
			Double.valueOf(inclination_Y);
			Double.valueOf(angle_X);
			Double.valueOf(angle_Y);
		} catch (NumberFormatException ex) {// 数据格式错误：数据无法转换至对应格式
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
	/**
	 * 校验覆冰监测数据
	 * @param data
	 */
	public void validateIceThinckness(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 11) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		// String alerm_Flag = strs[5]; // 报警标识
		String equalIceThickness = strs[6]; // 等值覆冰厚度
		String tension = strs[7]; // 综合悬挂载荷
		String tensionDifference = strs[8]; // 不均衡张力差
		String windageYawAngle = strs[9]; // 绝缘子串风偏角
		String deflectionAngle = strs[10]; // 绝缘子串偏斜角
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用

		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}
		try {
			Double.valueOf(equalIceThickness);
			Double.valueOf(tension);
			Double.valueOf(tensionDifference);
			Double.valueOf(windageYawAngle);
			Double.valueOf(deflectionAngle);
		} catch (NumberFormatException ex) {// 数据格式错误：数据无法转换至对应格式
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
	
	/**
	 * 校验污秽度数据
	 * @param data
	 */
	public void validateContamination(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 11) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		// String alerm_Flag = strs[5]; // 报警标识
		String esdd = strs[6]; // 等值附盐密度，即盐密
		String nsdd = strs[7]; // 不溶物密度，即灰密
		String dailyMaxTemperature = strs[8]; // 日最高温度
		String dailyMinTemperature = strs[9]; // 日最低温度
		String dailyMaxHumidity = strs[10]; // 日最大湿度
		String dailyMinHumidity = strs[11]; // 日最小湿度
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用

		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}
		try {
			Double.valueOf(esdd);
			Double.valueOf(nsdd);
			Double.valueOf(dailyMaxTemperature);
			Double.valueOf(dailyMinTemperature);
			Double.valueOf(dailyMaxHumidity);
			Double.valueOf(dailyMinHumidity);
		} catch (NumberFormatException ex) {// 数据格式错误：数据无法转换至对应格式
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
	
	public void validateWeather(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 11) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		// String alerm_Flag = strs[5]; // 报警标识
		
		String averageWindSpeed10min = strs[6]; // 10分钟平均风速
		String averageWindDirection10min = strs[7]; // 10分钟平均风向
		String maxWindSpeed = strs[8]; // 最大风速
		String extremeWindSpeed = strs[9]; // 极大风速
		String strandrdWindSpeed = strs[10]; // 标准风速
		String temperature = strs[11]; // 气温
		String humidity = strs[12]; // 湿度
		String airPressure = strs[13]; // 气压
		String dailyRainfall = strs[14]; // 雨量
		String precipitationIntensity = strs[15]; // 降水强度		
		String radiationIntensity = strs[16]; // 光幅射度
		
		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		// ID校验错误：ID不存在
		// ID校验错误：CMA与所辖状态监测装置不匹配
		// ID校验错误：状态监测装置与被监测设备不匹配

		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}
		try {
			Double.valueOf(averageWindSpeed10min);
			Double.valueOf(averageWindDirection10min);
			Double.valueOf(maxWindSpeed);
			Double.valueOf(extremeWindSpeed);
			Double.valueOf(strandrdWindSpeed);
			Double.valueOf(temperature);
			Double.valueOf(humidity);
			Double.valueOf(airPressure);
			Double.valueOf(dailyRainfall);
			Double.valueOf(precipitationIntensity);
			Double.valueOf(radiationIntensity);
		} catch (NumberFormatException ex) {// 数据格式错误：数据无法转换至对应格式
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
	
	
	
	public void validateLandslide(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");

		// 协议格式
		if (strs.length < 11) { // 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100002);
		}
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		// String alerm_Flag = strs[5]; // 报警标识
		
		String Sample_Num = strs[6]; // 采样点数
		
		// 数据格式校验
		try {
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp);
		} catch (ParseException e) {// 数据格式错误：时间格式错误
			throw new ProtocolException(ProtocolErrorCode.ERROR_0102001, str);
		}
		
		// ID校验//
		if (17 != cmd_ID.length() || 17 != component_ID.length()) { // ID校验错误：ID不合法
			throw new ProtocolException(ProtocolErrorCode.ERROR_0101001, str);
		}
		
		try {
			
			int sample_num = Integer.valueOf(Sample_Num);
			for(int i = 0 ; i<sample_num;i++){
				String PonitNo = strs[i*3+7]; //采样点编号 1
				String Ax = strs[i*3+7+1]; //采样点X倾角
				String Ay = strs[i*3+7+2]; //采样点Y倾角
				
				Integer.valueOf(PonitNo);
				Double.valueOf(Ax);
				Double.valueOf(Ay);	
			}
			 
		} catch (NumberFormatException ex) {// 数据格式错误：数据无法转换至对应格式
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
		
}
