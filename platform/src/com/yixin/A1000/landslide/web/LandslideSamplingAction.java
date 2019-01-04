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

package com.yixin.A1000.landslide.web;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.landslide.service.LandslideSamplingService;
import com.yixin.A1000.landslide.service.impl.LandslideSamplingServiceImpl;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 地质滑坡数据查询Action类
 * @version v1.0.0
 * @author 
 *
 */
public class LandslideSamplingAction extends BaseSamplingAction<LandslideSampling> implements ServletRequestAware,ServletResponseAware {

	

	private static final long serialVersionUID = 267440495309052530L;

	/** 显示的测点，如果为空，即显示所有的 */
	private Integer showSampleNum = null;
	
	/** 是否为显示角度曲线  */
	private boolean angleChart = true;
 
	/**
	 * 地质滑坡参数服务类接口
	 */
	private   LandslideParameterService landslideParameterService;
	
	/** 安装图片接口**/
	private PictureService pictureService;
	
	/**
	 * 设备 地质滑坡参数服务类接口
	 * @param landslideParameterService
	 */
	public void setLandslideParameterService(
			LandslideParameterService landslideParameterService) {
		this.landslideParameterService = landslideParameterService;
	}
	/**
	 * @param pictureService the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	private   HttpServletResponse  response;

	public LandslideSamplingAction(){
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
		long  d1 = 1;
		long  d2 =  1;		
		//参数
		LandslideParameter landslideParameter = landslideParameterService.getLandslideParameterBySensor(this.sensor);
		this.request.setAttribute("landslideParameter", landslideParameter);
		
		//最新实时数据
		this.samplingService.setSamplingDataType(SamplingDataType.REAL);
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, this.beginTime, this.endTime,d1,d2);
		if(page.getRecords().size()>0){
			request.setAttribute("realData", page.getRecords().toArray()[0]);			
		}
		
		
		Calendar calendar = Calendar.getInstance();
		
		//日数据
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
		this.samplingService.setSamplingDataType(SamplingDataType.DAY);
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, calendar.getTime(), this.endTime,d1,d2);
		
		if(page.getRecords().size()>0){
			request.setAttribute("dayData", page.getRecords().toArray()[0]);			
		}
		//月数据
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -32) ;
		this.samplingService.setSamplingDataType(SamplingDataType.MONTH);				
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, calendar.getTime(), this.endTime,d1,d2);
		if(page.getRecords().size()>0){
			request.setAttribute("monthData", page.getRecords().toArray()[0]);			
		}
		
		//年数据
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -366) ;
		this.samplingService.setSamplingDataType(SamplingDataType.YEAR);				
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, calendar.getTime(), this.endTime,d1,d2);
		
		if(page.getRecords().size()>0){
			request.setAttribute("yearData", page.getRecords().toArray()[0]);			
		}		
		
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30) ;		
		this.beginTime =calendar.getTime();
		
		List<Picture> pictures =  pictureService.getAllPictures(sensor);
		request.setAttribute("pictures", pictures);				
		
		return super.list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.JFreeChartAction#list()
	 */
	@Override
	public String list() {
		long  d1 = getPageNo();
		long  d2 =  getPageSize();		
		LandslideParameter landslideParameter = landslideParameterService.getLandslideParameterBySensor(this.sensor);
		//int samplingNum = landslideParameter.getSampleNum();
		this.request.setAttribute("xyType", landslideParameter.getXyType());
		this.samplingService.setSamplingDataType(SamplingDataType.REAL);
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, this.beginTime, this.endTime,d1,d2);
		/*
		Iterator<LandslideSampling>  iterator = this.page.getRecords().iterator();		
		LandslideSamplingService landslideSamplingService = (LandslideSamplingService)samplingService;
		while(iterator.hasNext()){
			LandslideSampling landslideSampling = iterator.next();
			landslideSampling.setLandslideSamplingDetails(
					landslideSamplingService.getSamplingLandslideDetail(landslideSampling));
		}
		*/
		Map<Integer,String> showSampleNums = new HashMap<Integer,String>();
		for(int i = 1 ; i <= landslideParameter.getSampleNum();i++){
			showSampleNums.put(i, String.format("监测点%d", i));
		}
		request.setAttribute("showSampleNums", showSampleNums);
		if(showSampleNum==null){
			return super.list();
		}else{
			super.list();
			return "listPoint";
		}	
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.JFreeChartAction#chart()
	 */
	@Override
	public String chart() {
		Hashtable<RegularTimePeriod, Number> axDatasets = null;
		Hashtable<RegularTimePeriod, Number> ayDatasets = null;
		Hashtable<RegularTimePeriod, Number> dxDatasets = null;
		Hashtable<RegularTimePeriod, Number> dyDatasets = null;
		Hashtable<RegularTimePeriod, Number> dDatasets = null;

		boolean showAngel = angleChart;// != null && showInclination? true:
		
		
		LandslideParameter landslideParameter = landslideParameterService.getLandslideParameterBySensor(this.sensor);
		
		/* 设置并添加倾斜度数据 */
		if (showAngel) {
			TimeSeriesChartData axChartData = new TimeSeriesChartData();
			axChartData.setColor(Color.RED);
			axChartData.setLegendText("X倾角");
			axChartData.setOrdinateText("X倾角(°)");
			axChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			axDatasets = new Hashtable<RegularTimePeriod, Number>();
			axChartData.setDatasets(axDatasets);
			chartDatas.add(axChartData);
			
			if(landslideParameter.getXyType() == 2){
				TimeSeriesChartData ayChartData = new TimeSeriesChartData();
				ayChartData.setColor(Color.GREEN);
				ayChartData.setLegendText("Y倾角");
				ayChartData.setOrdinateText("Y倾角(°)");
				ayChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
				ayDatasets = new Hashtable<RegularTimePeriod, Number>();
				ayChartData.setDatasets(ayDatasets);
				chartDatas.add(ayChartData);
			}
		}else{
			TimeSeriesChartData dxChartData = new TimeSeriesChartData();
			dxChartData.setColor(Color.RED);
			dxChartData.setLegendText("X位移");
			dxChartData.setOrdinateText("X位移(mm)");
			dxChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			dxDatasets = new Hashtable<RegularTimePeriod, Number>();
			dxChartData.setDatasets(dxDatasets);
			chartDatas.add(dxChartData);
			
			if(landslideParameter.getXyType() == 2){
				TimeSeriesChartData dyChartData = new TimeSeriesChartData();
				dyChartData.setColor(Color.GREEN);
				dyChartData.setLegendText("Y位移");
				dyChartData.setOrdinateText("Y位移(mm)");
				dyChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
				dyDatasets = new Hashtable<RegularTimePeriod, Number>();
				dyChartData.setDatasets(dyDatasets);
				chartDatas.add(dyChartData);		
				
				TimeSeriesChartData dChartData = new TimeSeriesChartData();
				dChartData.setColor(Color.BLUE);
				dChartData.setLegendText("合位移");
				dChartData.setOrdinateText("合位移(mm)");
				dChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
				dDatasets = new Hashtable<RegularTimePeriod, Number>();
				dChartData.setDatasets(dDatasets);
				chartDatas.add(dChartData);					
			}
		}

		 

		/* 填充数据内容 */
		this.samplingService.setSamplingDataType(SamplingDataType.REAL);
		LandslideSamplingService landslideSamplingService =(LandslideSamplingService)this.samplingService;
		List<LandslideSamplingDetail> ttSamplings = landslideSamplingService.getSamplingLandslideDetail(sensor, this.showSampleNum, beginTime, endTime);
		

		Iterator<LandslideSamplingDetail> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			LandslideSamplingDetail sampling = (LandslideSamplingDetail) iterator.next();
			Minute t = new Minute(sampling.getLandslideSampling().getSamplingTime());
			if (showAngel) {
				Double ax = sampling.getAngleX();
				if (null != ax)
					axDatasets.put(t, ax);
				if(landslideParameter.getXyType() == 2){
					Double ay = sampling.getAngleY();
					if (null != ay)
						ayDatasets.put(t, ay);
				}
				
			}else{
				Double dx = sampling.getDisplacementX();
				if (null != dx)
					dxDatasets.put(t, dx);
				if(landslideParameter.getXyType() == 2){
					Double dy = sampling.getDisplacementY();
					if (null != dy)
						dyDatasets.put(t, dy);
					Double d = sampling.getDisplacement();
					if (null != d)
						dDatasets.put(t, d);					
				}
			}		 
		}

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("地质滑坡历史采样数据曲线图",
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
		this.samplingService.setSamplingDataType(SamplingDataType.REAL);
		List<LandslideSampling>  ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);
		
	
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
			LandslideSamplingService lss = (LandslideSamplingServiceImpl)samplingService;   	
		    boolean ex = false;//lss.exportExcel(sensor, beginTime, endTime, ttSamplings, os);		  
		    if(ex){
		    	request.setAttribute("succMessage","成功导到EXCEL文件!");
		    } 
		    else{
		    	request.setAttribute("errorMessage", "文件导出失败");
		    }
		     
		} catch (IOException e) {
			
		}
		
		return SUCCESS;
	}
	
	
	 


	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response; 
	}


	public void setShowSampleNum(Integer showSampleNum) {
		this.showSampleNum = showSampleNum;
	}


	public Integer getShowSampleNum() {
		return showSampleNum;
	}


	/**
	 * @param angleChart the angleChart to set
	 */
	public void setAngleChart(boolean angleChart) {
		this.angleChart = angleChart;
	}


	/**
	 * @return the angleChart
	 */
	public boolean isAngleChart() {
		return angleChart;
	}
}
