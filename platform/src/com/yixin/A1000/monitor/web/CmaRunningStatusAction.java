/*
 * Project platform
 *
 * Classname CmaRunningStatusAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-06 14:40
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.web;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.A1000.setting.service.CmaHeartbeatInfoService;
import com.yixin.framework.base.web.JFreeChartAction;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 监测代理运行状态Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaRunningStatusAction extends JFreeChartAction<CmaHeartbeatInfo> {

	/** 序列版本ID */
	private static final long serialVersionUID = 5780558335427187036L;
	/** 监测代理心跳服务 */
	private CmaHeartbeatInfoService cmaHeartbeatInfoService;
	/** 监测代理 */
	private CmaInfo cma;
	
	/** 监测装置服务接口 */
	private SensorService sensorService;

	/**
	 * 设置CMA心跳服务
	 * @param cmaHeartbeatInfoService
	 */
	public void setCmaHeartbeatInfoService(
			CmaHeartbeatInfoService cmaHeartbeatInfoService) {
		this.cmaHeartbeatInfoService = cmaHeartbeatInfoService;
	}
	
	/**
	 * 设置监测装置ID
	 * @param id
	 */
	public void setId(String id) {
		setSensorId(id);
	}
	/**
	 * 设置监测装置ID
	 * @param sensorId
	 */
	public void setSensorId(String sensorId) {
		this.cma = this.sensorService.getSensor(sensorId).getCmaInfo(); //获取该监测装置的代理
	}
	/**
	 * 获取监测代理编码
	 * @return
	 */
	public String getCmaCode() {
		return null == cma? null : this.cma.getCmaCode();
	}
	/**
	 * 设置监测代理编码
	 * @param cmaCode
	 */
	public void setCmaCode(String cmaCode) {
		this.cma = new CmaInfo();
		this.cma.setCmaCode(cmaCode);
	}

	/**
	 * 设置监测代理
	 * @param cma 
	 */
	public void setCma(CmaInfo cma) {
		this.cma = cma;
	}
	/**获取监测代理
	 * @return the cma
	 */
	public CmaInfo getCma() {
		return cma;
	}
	/**
	 * @param sensorService the sensorService to set
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		page = this.cmaHeartbeatInfoService.getPageCmaHeartbeatInfos(cma, beginTime, endTime, 
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
		TimeSeriesChartData vChartData = new TimeSeriesChartData();
		vChartData.setColor(Color.BLUE);
		vChartData.setLegendText("电压");
		vChartData.setOrdinateText("电压(V)");
		vChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
		vChartData.setDatasets(vHashtable);
		chartDatas.add(vChartData);
		/* 设置并添加电压数据 */
		TimeSeriesChartData tChartData = new TimeSeriesChartData();
		tChartData.setColor(Color.PINK);
		tChartData.setLegendText("工作温度");
		tChartData.setOrdinateText("工作温度(℃)");
		tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
		tChartData.setDatasets(tHashtable);
		chartDatas.add(tChartData);	
		
		List<CmaHeartbeatInfo> infos = this.cmaHeartbeatInfoService.getCmaHeartbeatInfos(cma, beginTime, endTime);
		for(CmaHeartbeatInfo info : infos){
			Double voltage = info.getBatteryvoltage();
			Double temperature = info.getOperationtemperature();
			Minute t = new Minute(info.getCreateTime());
			if(null != voltage)
				vHashtable.put(t, voltage);
			if(null != temperature)
				tHashtable.put(t, temperature);
		}
		this.chart = JFreeChartFactory.createTimeSeriesChart("监测代理运行状态曲线图", createTimespanSubTitles(), "时间", true, chartDatas);
		return super.chart();
	}
	
	
}
