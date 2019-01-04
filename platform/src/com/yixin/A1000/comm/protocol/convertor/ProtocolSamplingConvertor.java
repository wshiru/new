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

import org.apache.log4j.Logger;

import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.protocol.FrameType;
import com.yixin.A1000.comm.protocol.PacketType;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;

/**
 * 监测数据报 转换类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolSamplingConvertor extends ProtocolConvertor {

	/** 协议日志记录器 */
	private static final Logger protocolLogger = Logger.getLogger("protocolLogger");

	protected SensorService sensorService;

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	private void log(String str, boolean isSend) {
		if (isSend) {
			protocolLogger.debug("SEND  " + str);
		} else {
			protocolLogger.debug("RECV  " + str);
		}
	}

	public byte[] towerTile2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_TOWERTILT.getHexCode());
		sb.append(",,");
		this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}
	
	public byte[] iceThinckness2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_ICETHINCKNESS.getHexCode());
		sb.append(",,");
		this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}
	
	public byte[] contamination2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_CONTAMINATION.getHexCode());
		sb.append(",,");
		this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}	
	
	public byte[] weather2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_WEATHER.getHexCode());
		sb.append(",,");
		this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}	

	public byte[] landslide2Bytes(String cmdId) {
		StringBuilder sb = new StringBuilder(cmdId);
		sb.append("," + FrameType.CONTROL_DOWN.getHexCode());
		sb.append("," + PacketType.CONTROL_DATA.getHexCode());
		sb.append("," + PacketType.SAMPLING_LANDSLIDE.getHexCode());
		sb.append(",,");
		this.log(sb.toString(), true);
		return sb.toString().getBytes();
	}		
	

	public TowerTiltSampling bytes2TowerTilt(byte[] data) {
		TowerTiltSampling towerTilt = new TowerTiltSampling();
		String str = new String(data);
		String[] strs = str.split(",");
		this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		String alarm_Flag = strs[5]; // 报警标识
		String inclination = strs[6]; // 倾斜度
		String inclination_X = strs[7]; // 顺线倾斜度
		String inclination_Y = strs[8]; // 横向倾斜度
		String angle_X = strs[9]; // 顺线倾斜角
		String angle_Y = strs[10]; // 横向倾斜角
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用
		int alarm = 0 ;
		for(int i = 0 ; i < alarm_Flag.length();i++){
			if(alarm_Flag.charAt(i) == '1'){
				alarm += 1 << i;
			}			
		}
		towerTilt.setAlarmFlag(alarm);
		towerTilt.setInclination(Double.parseDouble(inclination));
		towerTilt.setGradientAlongLines(Double.parseDouble(inclination_X));		
		towerTilt.setLateralTilt(Double.parseDouble(inclination_Y));
		towerTilt.setAngleX(Double.parseDouble(angle_X));
		towerTilt.setAngleY(Double.parseDouble(angle_Y));
		// towerTilt.setReserve1(reserve1);
		// towerTilt.setReserve2(reserve2);
		try {
			towerTilt.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp));
		} catch (ParseException e) {
		}
		towerTilt.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return towerTilt;
	}
	
	
	public IceThincknessSampling bytes2IceThinckness(byte[] data) {
		IceThincknessSampling iceThinckness = new IceThincknessSampling();
		String str = new String(data);
		String[] strs = str.split(",");
		this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		String alarm_Flag = strs[5]; // 报警标识
		String equalIceThickness = strs[6]; // 等值覆冰厚度
		String tension = strs[7]; // 综合悬挂载荷
		String tensionDifference = strs[8]; // 不均衡张力差
		String windageYawAngle = strs[9]; // 绝缘子串风偏角
		String deflectionAngle = strs[10]; // 绝缘子串偏斜角
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用
		int alarm = 0 ;
		for(int i = 0 ; i < alarm_Flag.length();i++){
			if(alarm_Flag.charAt(i) == '1'){
				alarm += 1 << i;
			}			
		}
		iceThinckness.setAlarmFlag(alarm);
		iceThinckness.setEqualIceThickness(Double.parseDouble(equalIceThickness));
		iceThinckness.setTension(Double.parseDouble(tension));		
		iceThinckness.setTensionDifference(Double.parseDouble(tensionDifference));
		iceThinckness.setWindageYawAngle(Double.parseDouble(windageYawAngle));
		iceThinckness.setDeflectionAngle(Double.parseDouble(deflectionAngle));
		// towerTilt.setReserve1(reserve1);
		// towerTilt.setReserve2(reserve2);
		try {
			iceThinckness.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp));
		} catch (ParseException e) {
		}
		iceThinckness.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return iceThinckness;
	}	
	
	public ContaminationSampling bytes2Contamination(byte[] data) {
		ContaminationSampling contamination = new ContaminationSampling();
		String str = new String(data);
		String[] strs = str.split(",");
		this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		String alarm_Flag = strs[5]; // 报警标识
		String esdd = strs[6]; // 等值附盐密度，即盐密
		String nsdd = strs[7]; // 不溶物密度，即灰密
		String dailyMaxTemperature = strs[8]; // 日最高温度
		String dailyMinTemperature = strs[9]; // 日最低温度
		String dailyMaxHumidity = strs[10]; // 日最大湿度
		String dailyMinHumidity = strs[11]; // 日最小湿度
		// String reserve1 = strs[11]; // 备用
		// String reserve2 = strs[12]; // 备用
		int alarm = 0 ;
		for(int i = 0 ; i < alarm_Flag.length();i++){
			if(alarm_Flag.charAt(i) == '1'){
				alarm += 1 << i;
			}			
		}
		contamination.setAlarmFlag(alarm);
		contamination.setEsdd(Double.parseDouble(esdd));
		contamination.setNsdd(Double.parseDouble(nsdd));		
		contamination.setDailyMaxTemperature(Double.parseDouble(dailyMaxTemperature));
		contamination.setDailyMinTemperature(Double.parseDouble(dailyMinTemperature));
		contamination.setDailyMaxHumidity(Double.parseDouble(dailyMaxHumidity));
		contamination.setDailyMinHumidity(Double.parseDouble(dailyMinHumidity));
		// towerTilt.setReserve1(reserve1);
		// towerTilt.setReserve2(reserve2);
		try {
			contamination.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp));
		} catch (ParseException e) {
		}
		contamination.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return contamination;
	}		
	
	public WeatherSampling bytes2Weather(byte[] data) {
		WeatherSampling weather = new WeatherSampling();
		String str = new String(data);
		String[] strs = str.split(",");
		this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		String alarm_Flag = strs[5]; // 报警标识
		
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

		int alarm = 0 ;
		for(int i = 0 ; i < alarm_Flag.length();i++){
			if(alarm_Flag.charAt(i) == '1'){
				alarm += 1 << i;
			}			
		}
		weather.setAlarmFlag(alarm);
		
		weather.setAverageWindSpeed10min(Double.parseDouble(averageWindSpeed10min));
		weather.setAverageWindDirection10min(Double.parseDouble(averageWindDirection10min));
		weather.setMaxWindSpeed(Double.parseDouble(maxWindSpeed));
		weather.setExtremeWindSpeed(Double.parseDouble(extremeWindSpeed));
		//weather.setStrandrdWindSpeed(Double.parseDouble(strandrdWindSpeed));
		//注意这里，设备上传的不是标准网速，要在realWeather模块转换才是标准速
		weather.setWindSpeed(Double.parseDouble(strandrdWindSpeed));
		weather.setTemperature(Double.parseDouble(temperature));
		weather.setHumidity(Double.parseDouble(humidity));
		weather.setAirPressure(Double.parseDouble(airPressure));
		weather.setDailyRainfall(Double.parseDouble(dailyRainfall));
		weather.setPrecipitationIntensity(Double.parseDouble(precipitationIntensity));
		weather.setRadiationIntensity(Double.parseDouble(radiationIntensity));	
 
		try {
			weather.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp));
		} catch (ParseException e) {
		}
		weather.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return weather;
	}
	
	
	public LandslideSampling bytes2Landslide(byte[] data) {
		LandslideSampling landslide = new LandslideSampling();
		String str = new String(data);
		String[] strs = str.split(",");
		this.log(str, false);
		String cmd_ID = strs[0]; // 状态监测装置ID（17位编码）
		// String frame_Type = strs[1]; // 帧类型—参考表2-54相关含义
		// String packet_Type = strs[2]; // 报文类型—参考表2-55相关含义
		// String component_ID = strs[3]; // 被监测设备ID（17位编码）
		String time_Stamp = strs[4]; // 采集时间
		String alarm_Flag = strs[5]; // 报警标识
		
		String Sample_Num = strs[6]; // 采样点数
		 

		int alarm = 0 ;
		for(int i = 0 ; i < alarm_Flag.length();i++){
			if(alarm_Flag.charAt(i) == '1'){
				alarm += 1 << i;
			}			
		}
		landslide.setAlarmFlag(alarm);
		
		int sampleNum = Integer.parseInt(Sample_Num);
		landslide.setSampleNum(sampleNum);
		
		List<LandslideSamplingDetail> LandslideSamplingDetails = new ArrayList<LandslideSamplingDetail>();
		
		for(int i = 0 ; i<sampleNum;i++){
			String PonitNo = strs[i*3+7]; //采样点编号 1
			String Ax = strs[i*3+7+1]; //采样点X倾角
			String Ay = strs[i*3+7+2]; //采样点Y倾角
			LandslideSamplingDetail LandslideSamplingDetail = new LandslideSamplingDetail();
			LandslideSamplingDetail.setLandslideSampling(landslide);
			LandslideSamplingDetail.setPointNo(Integer.parseInt(PonitNo));
			LandslideSamplingDetail.setAngleX(Double.parseDouble(Ax));
			LandslideSamplingDetail.setAngleY(Double.parseDouble(Ay));
			LandslideSamplingDetails.add(LandslideSamplingDetail);
		}
		
		landslide.setLandslideSamplingDetails(LandslideSamplingDetails);
		try {
			landslide.setSamplingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time_Stamp));
		} catch (ParseException e) {
		}
		landslide.setSensor(this.sensorService.getSensorbySensorCode(cmd_ID));
		return landslide;
	}
}
