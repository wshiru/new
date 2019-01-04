/*
 * Project platform
 *
 * Classname TowerTiltSamplingAction.java
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

package com.yixin.A1000.towertilt.web;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.towertilt.model.TowerTiltParameter;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.towertilt.service.TowerTiltParameterService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 杆塔倾斜Action类
 * 
 * @version v1.0.0
 * @author
 * 
 */
public class TowerTiltSamplingAction extends
		BaseSamplingAction<TowerTiltSampling> implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 267440495309052530L;

	
	/** 是否显示倾斜度 */
	private boolean showInclination = true;
	
	/** 是否显示顺线倾斜度 */
	private boolean showGradientAlongLines = true;

	/** 是否显示横向倾斜度 */
	private boolean showLateralTilt = true;

	/** 是否显示顺线倾斜角 */
	private boolean showAngle_x = true;

	/** 是否显示横向倾斜角 */
	private boolean showAngle_y = true;


	private HttpServletResponse response;
	
	/** 杆塔倾斜参数接口**/
	private TowerTiltParameterService towerTiltParameterService;
	
	/** 安装图片接口**/
	private PictureService pictureService;
	
	
	public TowerTiltSamplingAction(){
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
		
		TowerTiltParameter towerTiltParameter = towerTiltParameterService.getTowerTiltParameterBySensor(sensor);
		request.setAttribute("towerTiltParameter", towerTiltParameter);

		
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, this.beginTime, this.endTime, 1, 1);
		request.setAttribute("realData", page);	
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -2) ;
		
		samplingService.setSamplingDataType(SamplingDataType.DAY);
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), this.endTime, 1, 1);
		request.setAttribute("daliyData", page);
		
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
		this.beginTime = calendar.getTime();
		samplingService.setSamplingDataType(SamplingDataType.DAY);		
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, this.beginTime, this.endTime, 1, 1);
		
		//calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -32) ;
		samplingService.setSamplingDataType(SamplingDataType.MONTH);		
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), this.endTime, 1, 1);
		request.setAttribute("monthData", page);
		
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -366) ;
		samplingService.setSamplingDataType(SamplingDataType.YEAR);		
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), this.endTime, 1, 1);				
		request.setAttribute("yearData", page);
		
		List<Picture> pictures =  pictureService.getAllPictures(sensor);
		
		request.setAttribute("pictures", pictures);
		
		return super.list();
	}	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {

		long pageNo = getPageNo();
		long pageSize = getPageSize();
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		this.page = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, this.beginTime, this.endTime, pageNo, pageSize);
		return super.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {

		Hashtable<RegularTimePeriod, Number> giDatasets = null;
		Hashtable<RegularTimePeriod, Number> gaDatasets = null;
		Hashtable<RegularTimePeriod, Number> ltDatasets = null;

		Hashtable<RegularTimePeriod, Number> axDatasets = null;
		Hashtable<RegularTimePeriod, Number> ayDatasets = null;

		boolean showIL = showInclination;// != null && showInclination? true:
											// false;

		boolean showAX = showAngle_x;// !=null && showAngle_x? true: false;

		boolean showAY = showAngle_y;// !=null && showAngle_y? true: false;

		boolean showGA = showGradientAlongLines;// !=null &&
												// showGradientAlongLines? true:
												// false;

		boolean showLT = showLateralTilt;// !=null && showLateralTilt? true:
											// false;
		int chartCount = 0 ;
		/* 设置并添加倾斜度数据 */
		if (showIL) {
			TimeSeriesChartData giChartData = new TimeSeriesChartData();
			giChartData.setColor(Color.RED);
			giChartData.setLegendText("倾斜度");
			giChartData.setOrdinateText("倾斜度(mm/m)");
			//giChartData.setOrdinateLocation((chartCount % 2 ==0) ? AxisLocation.BOTTOM_OR_LEFT : AxisLocation.BOTTOM_OR_LEFT);
			giChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			giDatasets = new Hashtable<RegularTimePeriod, Number>();
			giChartData.setDatasets(giDatasets);
			chartCount++;
			chartDatas.add(giChartData);
		}

		/* 设置并添加顺线倾斜度数据 */
		if (showGA) {
			TimeSeriesChartData gaChartData = new TimeSeriesChartData();
			gaChartData.setColor(Color.GREEN);
			//gaChartData.setLegendText("顺线倾斜度");
			//gaChartData.setOrdinateText("顺线倾斜度(°)");
			gaChartData.setLegendText("顺线倾斜度");
			gaChartData.setOrdinateText("倾斜度(mm/m)");			
			gaChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			gaDatasets = new Hashtable<RegularTimePeriod, Number>();
			gaChartData.setDatasets(gaDatasets);

			chartDatas.add(gaChartData);
		}
		/* 设置并添加横向倾斜度数据 */
		if (showLT) {
			TimeSeriesChartData ltChartData = new TimeSeriesChartData();
			ltChartData.setColor(Color.BLUE);
			//ltChartData.setLegendText("横向倾斜度");
			//ltChartData.setOrdinateText("横向倾斜度(°)");
			ltChartData.setLegendText("横向倾斜度");
			ltChartData.setOrdinateText("倾斜度(mm/m)");			
			ltChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			ltDatasets = new Hashtable<RegularTimePeriod, Number>();
			ltChartData.setDatasets(ltDatasets);

			chartDatas.add(ltChartData);
		}

		/* 设置并添加顺线倾斜角数据 */
		if (showAX) {
			TimeSeriesChartData axChartData = new TimeSeriesChartData();
			axChartData.setColor(Color.CYAN);
			//axChartData.setLegendText("顺线倾斜角");
			//axChartData.setOrdinateText("顺线倾斜角");
			axChartData.setLegendText("顺线倾斜角");
			axChartData.setOrdinateText("倾斜角(°)");			
			axChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			axDatasets = new Hashtable<RegularTimePeriod, Number>();
			axChartData.setDatasets(axDatasets);
			chartDatas.add(axChartData);
		}

		/* 设置并添加横向倾斜角数据 */
		if (showAY) {
			TimeSeriesChartData ayChartData = new TimeSeriesChartData();
			ayChartData.setColor(Color.MAGENTA);
			//ayChartData.setLegendText("横向倾斜角");
			//ayChartData.setOrdinateText("横向倾斜角");
			ayChartData.setLegendText("横向倾斜角");
			ayChartData.setOrdinateText("倾斜角(°)");			
			ayChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			ayDatasets = new Hashtable<RegularTimePeriod, Number>();
			ayChartData.setDatasets(ayDatasets);
			chartDatas.add(ayChartData);
		}

		/* 填充数据内容 */
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		List<TowerTiltSampling> ttSamplings = this.samplingService
				.getSamplings(sensor, beginTime, endTime);

		Iterator<TowerTiltSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			TowerTiltSampling sampling = (TowerTiltSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			if (showIL) {
				Double gi = sampling.getInclination();
				if (null != gi)
					giDatasets.put(t, gi);
			}

			if (showGA) {
				Double ga = sampling.getGradientAlongLines();
				if (null != ga)
					gaDatasets.put(t, ga);
			}

			if (showLT) {
				Double lt = sampling.getLateralTilt();
				if (null != lt)
					ltDatasets.put(t, lt);
			}

			if (showAX) {
				Double ax = sampling.getAngleX();
				if (null != ax)
					axDatasets.put(t, ax);
			}

			if (showAY) {
				Double ay = sampling.getAngleY();
				if (null != ay)
					ayDatasets.put(t, ay);
			}

		}

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("杆塔倾斜历史采样数据曲线图",
				createTimespanSubTitles(), "采样时间", true, chartDatas);

		return super.chart();
	}

	public String exportExcel()
	{

		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}
		
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		List<TowerTiltSampling>  ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);
		
	
		if ( ttSamplings.size() ==0 ){
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));	
			request.setAttribute("errorMessage", "无数据导出");	
			return INPUT;
		}		
	
		
		String fname = String.format("%s-历史采样数据",sensor.getSensorCode());
		
		try {
			fname = new String( fname.getBytes("gb2312"),"ISO8859-1" );
		} catch (UnsupportedEncodingException e1) {
		
		}
		
		try {
			//取得输出流
			OutputStream os = response.getOutputStream();
			//清空输出流
			response.reset();
			//设定输出文件头
			response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");
			//定义输出类型
			response.setContentType("application/msexcel;charset=gbk");
		      	
		    //boolean ex = this.samplingService.exportExcel(sensor, beginTime, endTime, ttSamplings, os);
			ExcelExport ee = new ExcelExport();
			final Iterator<TowerTiltSampling> it= ttSamplings.iterator();
			boolean ex = ee.buileExcelStream(os, new ExcelExportInterface(){

				@Override
				public List<String> addTitle(Integer row) {
					String s = "地质滑坡历史采样数据;;;;;";
					return ExcelExport.Array2List(s.split(";"));
				}

				@Override
				public List<String> addHead(Integer row) {
					String s = "采集时间;倾斜度;顺线倾斜度;横向倾斜度;顺线倾斜角(°) ;横向倾斜角(°)";
					return ExcelExport.Array2List(s.split(";")); 
				}

				@Override
				public List<String> addRow(Integer row) {
					List<String> ls = null;
					if(it.hasNext()){
						TowerTiltSampling p = it.next();
						ls = new ArrayList<String>();
						ExcelExport.addObject(ls, p.getSamplingTime());
						ExcelExport.addObject(ls, p.getInclination());
						ExcelExport.addObject(ls, p.getGradientAlongLines());
						ExcelExport.addObject(ls, p.getLateralTilt());
						ExcelExport.addObject(ls, p.getAngleX());
						ExcelExport.addObject(ls, p.getAngleY()); 
					}				
					return ls;
				}

				@Override
				public List<String> addFoot(Integer row) {
					return null;
				}
				
				
			});
		    if (  ex  ){
		       request.setAttribute("succMessage","成功导到EXCEL文件!");
		    } 
		    else
		    {
		    	request.setAttribute("errorMessage", "文件导出失败");
		    }
		     
		} catch (IOException e) {
			
		}
		
		return SUCCESS;
	}

	public boolean isShowInclination() {
		return showInclination;
	}

	public void setShowInclination(boolean showInclination) {
		this.showInclination = showInclination;
	}

	public boolean isShowGradientAlongLines() {
		return showGradientAlongLines;
	}

	public void setShowGradientAlongLines(boolean showGradientAlongLines) {
		this.showGradientAlongLines = showGradientAlongLines;
	}

	public boolean isShowLateralTilt() {
		return showLateralTilt;
	}

	public void setShowLateralTilt(boolean showLateralTilt) {
		this.showLateralTilt = showLateralTilt;
	}

	public boolean isShowAngle_x() {
		return showAngle_x;
	}

	public void setShowAngle_x(boolean showAngle_x) {
		this.showAngle_x = showAngle_x;
	}

	public boolean isShowAngle_y() {
		return showAngle_y;
	}

	public void setShowAngle_y(boolean showAngle_y) {
		this.showAngle_y = showAngle_y;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	/**
	 * @param 杆塔倾斜参数接口 set
	 */
	public void setTowerTiltParameterService(TowerTiltParameterService towerTiltParameterService) {
		this.towerTiltParameterService = towerTiltParameterService;
	}
	/**
	 * @return the 杆塔倾斜参数接口
	 */
	public TowerTiltParameterService getTowerTiltParameterService() {
		return towerTiltParameterService;
	}
	/**
	 * @param pictureService the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

}
