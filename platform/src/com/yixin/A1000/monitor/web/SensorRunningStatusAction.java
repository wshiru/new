/*
 * Project platform
 *
 * Classname SensorRunningStatusAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-06 17:12
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.web;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.A1000.setting.service.SensorHeartbeatInfoService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.web.JFreeChartAction;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 监测装置运行状态Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorRunningStatusAction extends JFreeChartAction<SensorHeartbeatInfo> {

	/** 序列版本ID */
	private static final long serialVersionUID = -4779470561330467918L;
	/** 监测装置心跳服务 */
	private SensorHeartbeatInfoService sensorHeartbeatInfoService;

	
	/** 监测装置 */
	private Sensor sensor;
	/** 是否显示电压曲线  */
	private Boolean showBatteryVoltage = true;
	
	/** 是否显示工作温度 */
	private Boolean showOperationTemperature = true;
	/**
	 * 设置监测装置心跳服务
	 * @param sensorHeartbeatInfoService
	 */
	public void setSensorHeartbeatInfoService(
			SensorHeartbeatInfoService sensorHeartbeatInfoService) {
		this.sensorHeartbeatInfoService = sensorHeartbeatInfoService;
	}
	
	
	/**
	 * 设置监测装置ID
	 * @param id
	 */
	public void setId(String id){
		sensor = new Sensor();
		sensor.setSensorId(id);
	}	
	/**
	 * 设置监测装置ID
	 * @param sensorId
	 */
	public void setSensorId(String sensorId){
		sensor = new Sensor();
		sensor.setSensorId(sensorId);
	}
	/**
	 * 获取监测装置ID
	 * @param sensorId
	 */
	public String getSensorId(){
		return null == sensor? null : this.sensor.getSensorId();
	}
	/**
	 * 获取监测装置编码
	 * @return
	 */
	public String getSensorCode() {
		return null == sensor? null : sensor.getBySensorCode();
	}
	/**
	 * 设置监测装置编码
	 * @param sensorCode
	 */
	public void setSensorCode(String sensorCode) {
		sensor = new Sensor();
		sensor.setSensorCode(sensorCode);
	}

	/**
	 * @param 显示工作温度  to set
	 */
	public void setShowOperationTemperature(Boolean showOperationTemperature) {
		this.showOperationTemperature = showOperationTemperature;
	}

	/**
	 * @return the 显示工作温度 
	 */
	public Boolean getShowOperationTemperature() {
		return showOperationTemperature;
	}

	/**
	 * @param 电压 to set
	 */
	public void setShowBatteryVoltage(Boolean showBatteryVoltage) {
		this.showBatteryVoltage = showBatteryVoltage;
	}

	/**
	 * @return the 电压
	 */
	public Boolean getShowBatteryVoltage() {
		return showBatteryVoltage;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		if(beginTime == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());			
			SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
			String s1 = fd.format(calendar.getTime()); 
			try {
				beginTime = fd.parse(s1);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
		}
		if(endTime == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			endTime = calendar.getTime();
		}
		page = this.sensorHeartbeatInfoService.getPageSensorHeartbeatInfos(sensor, beginTime, endTime, 
				getPageNo(), getPageSize());
		
		return super.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.JFreeChartAction#chart()
	 */
	@Override
	public String chart() {
		
		Hashtable<RegularTimePeriod, Number> vHashtable = new Hashtable<RegularTimePeriod, Number>();
		Hashtable<RegularTimePeriod, Number> tHashtable = new Hashtable<RegularTimePeriod, Number>();	
		
		List<TimeSeriesChartData> chartDatas = new ArrayList<TimeSeriesChartData>();
		/* 设置并添加电压数据 */
		if(showBatteryVoltage){			
			TimeSeriesChartData vChartData = new TimeSeriesChartData();
			vChartData.setColor(Color.BLUE);
			vChartData.setLegendText("电压");
			vChartData.setOrdinateText("电压(V)");
			vChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			vChartData.setDatasets(vHashtable);
			chartDatas.add(vChartData);
		}
		/* 设置并添加电压数据 */
		if(showOperationTemperature){			
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.PINK);
			tChartData.setLegendText("工作温度");
			tChartData.setOrdinateText("工作温度(℃)");
			tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			tChartData.setDatasets(tHashtable);
			chartDatas.add(tChartData);
		}
		/* 填充数据内容*/
		List<SensorHeartbeatInfo> infos = this.sensorHeartbeatInfoService.getSensorHeartbeatInfos(sensor, beginTime, endTime);
		for(SensorHeartbeatInfo info : infos){
			Double voltage = info.getBatteryvoltage();
			Double temperature = info.getOperationtemperature();
			Minute t = new Minute(info.getCreateTime());
			if(showBatteryVoltage && null != voltage)
				vHashtable.put(t, voltage);
			if(showOperationTemperature && null != temperature)
				tHashtable.put(t, temperature);
		}
		this.chart = JFreeChartFactory.createTimeSeriesChart("监测装置运行状态曲线图", createTimespanSubTitles(), "时间", true, chartDatas);
		return super.chart();
	}

	

}
