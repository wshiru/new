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

package com.yixin.A1000.weather.web;


import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 微气象Action类
 * @version v1.0.0
 * @author 
 *
 */
public class WeatherSamplingAction extends BaseSamplingAction<WeatherSampling> {

	/** 序列版本ID  */
	private static final long serialVersionUID = -2765013632008468034L;



	/** 是否显示气温 */
	protected Boolean showTemperature = true;
	/** 是否显示湿度  */
	protected Boolean showHumidity = true;
	/** 是否显示风速  */
	protected Boolean showWindSpeed = false;	
	/** 是否显示风向 */
	protected Boolean showWindDirection = false;
	/** 是否显示气压  */
	protected Boolean showAirPressure = true;
	/** 是否显示日降雨量  */
	protected Boolean showDailyRainfall = false;
	/** 是否显示光辐射度  */
	protected Boolean showRadiationIntensity = false;
 
	/** 降水强度 **/
	protected Boolean showPrecipitationIntensity = true;
	
	/** 安装图片接口**/
	private PictureService pictureService;
	
	public Boolean getShowPrecipitationIntensity() {
		return showPrecipitationIntensity;
	}
	public void setShowPrecipitationIntensity(Boolean showPrecipitationIntensity) {
		this.showPrecipitationIntensity = showPrecipitationIntensity;
	}
	
	/**
	 * @param pictureService the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	/**
	 * 获取是否显示气温
	 * @return
	 */
	public Boolean getShowTemperature() {
		return showTemperature;
	}
	/**
	 * 设置是否显示气温
	 * @return
	 */
	public void setShowTemperature(Boolean showTemperature) {
		this.showTemperature = showTemperature;
	}
	/**
	 * 获取是否显示湿度
	 * @return
	 */
	public Boolean getShowHumidity() {
		return showHumidity;
	}
	/**
	 * 设置是否显示湿度
	 * @return
	 */
	public void setShowHumidity(Boolean showHumidity) {
		this.showHumidity = showHumidity;
	}
	/**
	 * 获取是否显示风速
	 * @return
	 */
	public Boolean getShowWindSpeed() {
		return showWindSpeed;
	}
	/**
	 * 设置是否显示风速
	 * @return
	 */
	public void setShowWindSpeed(Boolean showWindSpeed) {
		this.showWindSpeed = showWindSpeed;
	}
	/**
	 * 获取是否显示风向
	 * @return
	 */
	public Boolean getShowWindDirection() {
		return showWindDirection;
	}
	/**
	 * 设置是否显示风向
	 * @return
	 */
	public void setShowWindDirection(Boolean showWindDirection) {
		this.showWindDirection = showWindDirection;
	}
	/**
	 * 获取是否显示气压
	 * @return
	 */
	public Boolean getShowAirPressure() {
		return showAirPressure;
	}
	/**
	 * 设置是否显示气压
	 * @return
	 */
	public void setShowAirPressure(Boolean showAirPressure) {
		this.showAirPressure = showAirPressure;
	}
	/**
	 * 获取是否显示日降雨量
	 * @return
	 */
	public Boolean getShowDailyRainfall() {
		return showDailyRainfall;
	}
	/**
	 * 设置是否显示日降雨量
	 * @return
	 */
	public void setShowDailyRainfall(Boolean showDailyRainfall) {
		this.showDailyRainfall = showDailyRainfall;
	}
	/**
	 * 获取是否显示光辐射度
	 * @return
	 */
	public Boolean getShowRadiationIntensity() {
		return showRadiationIntensity;
	}
	/**
	 * 设置是否显示光辐射度
	 * @return
	 */
	public void setShowRadiationIntensity(Boolean showRadiationIntensity) {
		this.showRadiationIntensity = showRadiationIntensity;
	}

	public WeatherSamplingAction(){
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
	}

	/**
	 * 
	 * @return
	 */	
	public String main(){
		page = this.samplingService.getPageSamplings(
				sensor, beginTime, endTime, 1, 1);
		
		List<Picture> pictures =  pictureService.getAllPictures(sensor);
		request.setAttribute("pictures", pictures);		
		
		return super.list();
		
	}	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
	
		page = this.samplingService.getPageSamplings(
					sensor, beginTime, endTime, getPageNo(), getPageSize());
		return super.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {
		
		Hashtable<RegularTimePeriod, Number> tDatasets = null;
		Hashtable<RegularTimePeriod, Number> hDatasets = null;
		Hashtable<RegularTimePeriod, Number> wsDatasets = null;
		Hashtable<RegularTimePeriod, Number> wdDatasets = null;
		Hashtable<RegularTimePeriod, Number> apDatasets = null;
		Hashtable<RegularTimePeriod, Number> drDatasets = null;
		Hashtable<RegularTimePeriod, Number> riDatasets = null;
		Hashtable<RegularTimePeriod, Number> piDatasets = null;
		
	
		boolean showT = showTemperature !=null && showTemperature? true: false;
		boolean showH = showHumidity !=null && showHumidity? true: false;
		boolean showWS = showWindSpeed !=null && showWindSpeed? true: false;
		boolean showWD = showWindDirection !=null && showWindDirection? true: false;
		boolean showAP = showAirPressure !=null && showAirPressure? true: false;
		boolean showDR = showDailyRainfall !=null && showDailyRainfall? true: false;
		boolean showRI = showRadiationIntensity !=null && showRadiationIntensity? true: false;
		
		boolean showPI = showPrecipitationIntensity !=null && showPrecipitationIntensity? true: false;
		
		
		
		int countLine = 0; //统计曲线数
		
		
		/* 设置并添加气温数据 */
		if(showT){
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.GREEN);
			tChartData.setLegendText("气温");
			tChartData.setOrdinateText("气温(℃)");
			tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			tDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(tDatasets);

			chartDatas.add(tChartData);
			countLine ++;
		}
		/* 设置并添加气压数据 */
		if(showAP){
			TimeSeriesChartData apChartData = new TimeSeriesChartData();
			apChartData.setColor(Color.ORANGE);
			apChartData.setLegendText("气压");
			apChartData.setOrdinateText("气压(hPa)");
			apChartData.setOrdinateLocation(arrangeLocation(countLine));
			apDatasets = new Hashtable<RegularTimePeriod, Number>();
			apChartData.setDatasets(apDatasets);

			chartDatas.add(apChartData);
			countLine ++;
		}
		/* 设置并添加雨量数据 */
		if(showDR){
			TimeSeriesChartData drChartData = new TimeSeriesChartData();
			drChartData.setColor(Color.MAGENTA);
			drChartData.setLegendText("雨量");
			drChartData.setOrdinateText("雨量(mm)");
			drChartData.setOrdinateLocation(arrangeLocation(countLine));
			drDatasets = new Hashtable<RegularTimePeriod, Number>();
			drChartData.setDatasets(drDatasets);

			chartDatas.add(drChartData);
			countLine ++;
		}
		/* 设置并添加光辐射度数据 */
		if(showRI){
			TimeSeriesChartData riChartData = new TimeSeriesChartData();
			riChartData.setColor(Color.CYAN);
			riChartData.setLegendText("光辐射度");
			riChartData.setOrdinateText("光辐射度(W/m2)");
			riChartData.setOrdinateLocation(arrangeLocation(countLine));
			riDatasets = new Hashtable<RegularTimePeriod, Number>();
			riChartData.setDatasets(riDatasets);

			chartDatas.add(riChartData);
			countLine ++;
		}
		
		/* 设置并添加湿度数据 */
		if(showH){
			TimeSeriesChartData hChartData = new TimeSeriesChartData();
			hChartData.setColor(Color.BLUE);
			hChartData.setLegendText("湿度");
			hChartData.setOrdinateText("湿度(%)");
			hChartData.setOrdinateLocation(arrangeLocation(countLine));
			hDatasets = new Hashtable<RegularTimePeriod, Number>();
			hChartData.setDatasets(hDatasets);
			
			chartDatas.add(hChartData);
			countLine ++;
		}
		/* 设置并添加风速数据 */
		if(showWS){
			TimeSeriesChartData wsChartData = new TimeSeriesChartData();
			wsChartData.setColor(Color.PINK);
			wsChartData.setLegendText("标准风速");
			wsChartData.setOrdinateText("风速(km/h)");
			wsChartData.setOrdinateLocation(arrangeLocation(countLine));
			wsDatasets = new Hashtable<RegularTimePeriod, Number>();
			wsChartData.setDatasets(wsDatasets);
			
			chartDatas.add(wsChartData);
			countLine ++;
		}
		/* 设置并添加风向数据 */
		if(showWD){
			TimeSeriesChartData wdChartData = new TimeSeriesChartData();
			wdChartData.setColor(Color.YELLOW);
			wdChartData.setLegendText("风向");
			wdChartData.setOrdinateText("风向(°)");
			wdChartData.setOrdinateLocation(arrangeLocation(countLine));
			wdDatasets = new Hashtable<RegularTimePeriod, Number>();
			wdChartData.setDatasets(wdDatasets);
			
			chartDatas.add(wdChartData);
			countLine ++;
		}
		
		if (showPI){
			TimeSeriesChartData piChartData = new TimeSeriesChartData();
			piChartData.setColor(Color.red);
			piChartData.setLegendText("降水强度");
			piChartData.setOrdinateText("降水强度(mm/min)");
			piChartData.setOrdinateLocation(arrangeLocation(countLine));
			piDatasets = new Hashtable<RegularTimePeriod, Number>();
			piChartData.setDatasets(piDatasets);
			
			chartDatas.add(piChartData);
			countLine ++;
		}
		
		/* 填充数据内容*/
		List<WeatherSampling> wSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);
		Iterator<WeatherSampling> iterator = wSamplings.iterator();		
		while (iterator.hasNext()) {
			WeatherSampling sampling = (WeatherSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			
			Double temp = sampling.getTemperature();
			Double humid = sampling.getHumidity();
			//Double ws = sampling.getWindSpeed();
			Double ws = sampling.getStrandrdWindSpeed();
			Double wd = sampling.getWindDirection();
			Double ap = sampling.getAirPressure();
			Double dr = sampling.getDailyRainfall();
			Double ri = sampling.getRadiationIntensity();
			Double pi = sampling.getPrecipitationIntensity();
			
			
			if(showT && null != temp)
				tDatasets.put(t, temp);
			if(showH && null != humid)
				hDatasets.put(t, humid);
			if(showWS && null != ws)		
				wsDatasets.put(t, ws);
			if(showWD && null != wd)
				wdDatasets.put(t, wd);
			if(showAP && null != ap)
				apDatasets.put(t, ap);
			if(showDR && null != dr)
				drDatasets.put(t, dr);
			if(showRI && null != ri)
				riDatasets.put(t, ri);
			
			if(showPI && null != pi)
				piDatasets.put(t, pi);
			
		}
		
		//生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("微气象曲线图", createTimespanSubTitles(), "采样时间", true, chartDatas);
		
		return super.chart();
	}
	
	/**
	 * 安排曲线纵轴位置
	 * @param number
	 * @return
	 */
	private AxisLocation arrangeLocation(int number) {
		if(number%2 == 0)
			return AxisLocation.BOTTOM_OR_LEFT;
		return AxisLocation.BOTTOM_OR_RIGHT;
	}

 
}
