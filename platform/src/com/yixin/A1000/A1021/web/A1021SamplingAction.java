/*
 * Project platform
 *
 * Classname WeatherSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 16:04
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.A1021.web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.towertilt.model.TowerTiltParameter;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.towertilt.service.TowerTiltParameterService;
import com.yixin.A1000.towertilt.service.TowerTiltSamplingService;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherSamplingService;
import com.yixin.framework.base.model.Page;

/**
 * 微气象Action类
 * @version v1.0.0
 * @author 
 *
 */
public class A1021SamplingAction extends ActionSupport implements ServletRequestAware  {

	/** 序列版本ID  */
	private static final long serialVersionUID = -272314034L;	
 
	private String id ;
	
	private Sensor sensor;
	
	/** request请求对象  */
	private HttpServletRequest request;
	
	/**
	 * 设置request对象
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	} 
	private TowerTiltParameterService towerTiltParameterService;
	private WeatherSamplingService weatherSamplingService;
	private TowerTiltSamplingService towerTiltSamplingService;
	private SensorService sensorService;
	private PictureService pictureService;
		
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public TowerTiltParameterService getTowerTiltParameterService() {
		return towerTiltParameterService;
	}

	public void setTowerTiltParameterService(
			TowerTiltParameterService towerTiltParameterService) {
		this.towerTiltParameterService = towerTiltParameterService;
	}

	public WeatherSamplingService getWeatherSamplingService() {
		return weatherSamplingService;
	}

	public void setWeatherSamplingService(
			WeatherSamplingService weatherSamplingService) {
		this.weatherSamplingService = weatherSamplingService;
	}

	public TowerTiltSamplingService getTowerTiltSamplingService() {
		return towerTiltSamplingService;
	}

	public void setTowerTiltSamplingService(
			TowerTiltSamplingService towerTiltSamplingService) {
		this.towerTiltSamplingService = towerTiltSamplingService;
	}

	public SensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	public PictureService getPictureService() {
		return pictureService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public A1021SamplingAction(){
		 //
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public String main(){
		//**********************************************************
		//开始取得日期值
		//**********************************************************
		
		Date beginTime = null;
		Date endTime = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());		
		//calendar.add(Calendar.DATE, -7) ;
				
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(calendar.getTime());
		Date  d1 = calendar.getTime();
		 
		try {
			d1 = fd.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		beginTime = d1; 
		
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1) ;
		endTime = calendar.getTime();
		
		//**********************************************************
		//开始取得微气象最新数据
		//**********************************************************		
		
		Page<WeatherSampling> page = this.weatherSamplingService.getPageSamplings(
				sensor, beginTime, endTime, 1, 1);
		request.setAttribute("pageWeatherData", page);
		
		//**********************************************************
		//开始取得杆塔倾斜最新数据
		//**********************************************************		
		
		//参数
		TowerTiltParameter towerTiltParameter = towerTiltParameterService.getTowerTiltParameterBySensor(sensor);
		request.setAttribute("towerTiltParameter", towerTiltParameter);
		
		//最新数据
		towerTiltSamplingService.setSamplingDataType(SamplingDataType.REAL);
		Page<TowerTiltSampling> ttsPage = this.towerTiltSamplingService.getPageSamplingsByDateTime(
				this.sensor, beginTime, endTime, 1, 1);
		request.setAttribute("realData", ttsPage);	
		
		//上一日
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -2) ;
		
		towerTiltSamplingService.setSamplingDataType(SamplingDataType.DAY);
		ttsPage = this.towerTiltSamplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);
		request.setAttribute("daliyData", ttsPage);
		
		//上周
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
		beginTime = calendar.getTime();
		towerTiltSamplingService.setSamplingDataType(SamplingDataType.DAY);		
		ttsPage = this.towerTiltSamplingService.getPageSamplingsByDateTime(
				this.sensor, beginTime, endTime, 1, 1);
		request.setAttribute("beginTime", beginTime);
		//calendar.setTime(new Date());
		//上月
		calendar.add(Calendar.DATE, -32) ;
		towerTiltSamplingService.setSamplingDataType(SamplingDataType.MONTH);		
		ttsPage = this.towerTiltSamplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);
		request.setAttribute("monthData", ttsPage);
		
		//上年
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -366) ;
		towerTiltSamplingService.setSamplingDataType(SamplingDataType.YEAR);		
		ttsPage = this.towerTiltSamplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);				
		request.setAttribute("yearData", ttsPage);
		
		
		//**********************************************************
		//开始取得现场图片
		//**********************************************************			
		List<Picture> pictures =  pictureService.getAllPictures(sensor);
		request.setAttribute("pictures", pictures);		
		
		
		
		 return SUCCESS;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		sensor = sensorService.getSensor(id);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {		
		return id;
	}
 
}
