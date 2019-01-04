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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.chart.plot.XYPlot;

import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.landslide.service.LandslideSamplingService;
import com.yixin.A1000.landslide.service.impl.LandslideSamplingServiceImpl;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.XYLineChartData;

/**
 * 地质滑坡数据分析Action类
 * @version v1.0.0
 * @author 
 *
 */
public class LandslideDisplacementAction extends BaseSamplingAction<LandslideSampling> implements ServletRequestAware,ServletResponseAware {

	

	private static final long serialVersionUID = 267440495309052530L;

	
 
	/**
	 * 地质滑坡参数服务类接口
	 */
	private   LandslideParameterService landslideParameterService;
	
	/**
	 * 设备 地质滑坡参数服务类接口
	 * @param landslideParameterService
	 */
	public void setLandslideParameterService(
			LandslideParameterService landslideParameterService) {
		this.landslideParameterService = landslideParameterService;
	}

	private Integer StartYearId;
	private Integer StartMonthId;

	private Integer EndYearId;
	private Integer EndMonthId;
	
	/** 曲线类型，1绝对位移，2，相对位移 */
	private Integer chartType;
	/** 数据类型,只有双轴可以选择，单轴的只能是0，0 X方向位移，1 Y方向位移，2 合移位 */
	private Integer dataType;
	
	private   HttpServletResponse  response;

	public LandslideDisplacementAction(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
				
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
	
	private void getMonthParameter(){
		//默认查询12个月值
		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 15;

		// 年份
		for (int i = startYear; i < endYear; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YearId", i);
			map.put("YearName", String.valueOf(i) + "年");
			Years.add(map);
		}
		request.setAttribute("Years", Years);

		// 月份
		List<Map<String, Object>> Months = new ArrayList<Map<String, Object>>();
		for (int j = 1; j < 13; j++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MonthId", j);
			map.put("MonthName", String.valueOf(j) + "月");
			Months.add(map);
		}
		request.setAttribute("Months", Months);

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR);
		}
		if (this.StartMonthId == null) {
			this.StartMonthId = 1;
		}

		if (this.EndYearId == null) {
			this.EndYearId = cal.get(Calendar.YEAR);
		}
		if (this.EndMonthId == null) {
			this.EndMonthId = cal.get(Calendar.MONTH) + 1;
		}

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		String d1 = String.valueOf(this.StartYearId) + "-"
				+ String.valueOf(this.StartMonthId) + "-01";
		String d2 = String.valueOf(this.EndYearId) + "-"
				+ String.valueOf(this.EndMonthId) + "-01";

		try {
			this.beginTime = fd.parse(d1);
			this.endTime = fd.parse(d2);
		} catch (ParseException e) {
		}		
	}
	
	private void getYearParameter(){
		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 15;

		// 年份
		for (int i = startYear; i < endYear; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YearId", i);
			map.put("YearName", String.valueOf(i) + "年");
			Years.add(map);
		}
		request.setAttribute("Years", Years);

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR) - 5;
		}

		if (this.EndYearId == null) {
			this.EndYearId = cal.get(Calendar.YEAR);
		}

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = String.valueOf(this.StartYearId) + "-01-01";
		String d2 = String.valueOf(this.EndYearId) + "-12-01";

		try {
			this.beginTime = fd.parse(d1);
			this.endTime = fd.parse(d2);
		} catch (ParseException e) {
		}		
	}
	
	public String dayList() {
		//默认查询一周值
		if(null == beginTime){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7) ;
			beginTime = calendar.getTime();
		}
		if(null == endTime){
			endTime = new Date();
		}
		return list(ContextKeys.DAY_EXPORT);
	}
	
	
	public String monthList() {
		getMonthParameter();
		return list(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearList() {
		getYearParameter();		
		return list(ContextKeys.YEAR_EXPORT);
	}
 
	public String list(Integer listType) {
		if(chartType == null){
			chartType = 1;
		}
		
		if (beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}
		switch (listType) {
		case ContextKeys.DAY_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.DAY);
				break;			
		case ContextKeys.MONTH_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.MONTH);
				break;			
		case ContextKeys.YEAR_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.YEAR);
				break;			
		default:
			break;
		}					
		
		this.page  =  this.samplingService.getPageSamplingsByDateTime(this.sensor, this.beginTime, this.endTime,getPageNo(),getPageSize());

		LandslideParameter landslideParameter = landslideParameterService.getLandslideParameterBySensor(this.sensor);
		this.request.setAttribute("xyType", landslideParameter.getXyType());
		if(dataType == null){
			dataType = 0;
		}
		if(landslideParameter == null || landslideParameter.getXyType() == null || landslideParameter.getXyType() == 1 ){
			dataType = 0;
		}		
		Map<Integer,String> dataTypes = new HashMap<Integer,String>();
		dataTypes.put(0, "X方向位移");
		dataTypes.put(1, "Y方向位移");
		dataTypes.put(2, "合位移");		
		request.setAttribute("dataTypes", dataTypes);
		return super.list();
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.JFreeChartAction#chart()
	 */
	
	public String dayChart() {
		return chart(ContextKeys.DAY_EXPORT);
	}
	
	public String monthChart() {
		return chart(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearChart() {
		return chart(ContextKeys.YEAR_EXPORT);
	}
	
	public String chart(Integer listType){
		List<XYLineChartData> chartDatas = new ArrayList<XYLineChartData>();
		
		String chartTitle = "";
		String titlename = "";
		if(chartType == null){
			chartType = 1;
		}
		if(chartType==1){		
			chartTitle = "绝对位移";
		}
		else{
			chartTitle = "相对位移";
		}
		 
		switch (dataType) {
		case 0:
			chartTitle = "X方向"+chartTitle;			
			break;
		case 1:
			chartTitle = "Y方向"+chartTitle;			
			break;
		case 2:
			chartTitle = "合移位";			
			break;
		default:
			break;
		}	
		
		switch (listType) {
		case ContextKeys.DAY_EXPORT:
			titlename = "日数据";
			samplingService.setSamplingDataType(SamplingDataType.DAY);
			break;
		case ContextKeys.MONTH_EXPORT:
			titlename = "月数据";
			samplingService.setSamplingDataType(SamplingDataType.MONTH);
			break;
		case ContextKeys.YEAR_EXPORT:
			titlename = "年数据";
			samplingService.setSamplingDataType(SamplingDataType.YEAR);			
			break;			
		default:
			break;
		}

		/* 填充数据内容 */
		List<LandslideSampling> ttSamplings = this.samplingService.getSamplings(this.sensor, this.beginTime, this.endTime);
		
		Iterator<LandslideSampling> iterator = ttSamplings.iterator();
		Double x = 0.0;
		Double y = 0.0;
		Double maxX = 0.0, maxY = 0.0;
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		List<Double> lastMoveValue = new ArrayList<Double>();
		Boolean first = true;
		int r = 20,g=20,b=20;
		
		while (iterator.hasNext()) {
			LandslideSampling sampling = (LandslideSampling) iterator.next();			
			HashMap<Integer, List<Number>> datasets = new HashMap<Integer, List<Number>>();
				
			Iterator<LandslideSamplingDetail> itDetail = sampling.getLandslideSamplingDetails().iterator();
			int i = 0 ;
			List<Double> currMoveValue = new ArrayList<Double>();

			Map<Integer , List<Number>> valueMap = new HashMap<Integer,List<Number>>();
			while(itDetail.hasNext()){				
				LandslideSamplingDetail detail = itDetail.next();				
				//Y为深度
				y = 0 - detail.getRelativeDepth();
				//数据类型，X轴，Y轴，合位移
				x = 0.0;
				switch (dataType) {
				case 0:
					//为什么要X另上位移值，因为测试是从下到上，1，2，3，即2个测点是相对第1个测点，第一个测点是相对基岩的，
					//查询的结果是从下到上的。晕啊，即，3，2，1.
					x = detail.getDisplacementX();
					break;
				case 1:
					x = detail.getDisplacementY();
					break;
				case 2:
					x = detail.getDisplacement();
					break;
				default:
					break;
				}
				//如果为相对位移 
				if(chartType != 1){
					currMoveValue.add(x);
					if(first){						
						lastMoveValue.add(x);
						x = 0.0;
					}else {
						//相对位移是，本次相对上次的位移值。lastMoveValue.get(i)为上次的位移值
						x =  x - lastMoveValue.get(i);
					}
				}
				//只有XY都不为空时才添加数据
				if(x != null && y != null){
					
					
					List<Number> list = new ArrayList<Number>();
					//Y轴为深度
					list.add(y);
					//X为位移值
					list.add(x);
					
					valueMap.put(detail.getPointNo(), list);
					
				}
				i++;
			}
			
			x=0.0;
			Iterator it = valueMap.keySet().iterator();			
			while(it.hasNext()){				
				Integer key = (Integer)it.next();
				List<Number> list = valueMap.get(key);
				
				Number xValue = list.get(1);
				x =x + (Double)xValue;  
				list.set(1, x);				
								
				datasets.put(key,list);
				if(maxX < Math.abs(x)){
					maxX = Math.abs(x);
				}

			}			
			
			if(chartType == 3){
				lastMoveValue = currMoveValue;
			}
			if(sampling.getAllDepth() == null ){
				y = 0.0;
			}else{
				y = 0 - sampling.getAllDepth();
			}
			
			List<Number> list = new ArrayList<Number>();
			list.add(y);
			list.add(0.0);
			datasets.put(0, list);
			if(maxY < Math.abs(y)){
				maxY = Math.abs(y);
			}	
			if(first){
				first = false;
			}
			XYLineChartData chartData = new XYLineChartData();

			if(b+20>180){
				r = 20;
				g = 20;
				b = 20;
			}else{
				r += 25;
				g += 10;
				b += 20;
			}
				
			//曲线的颜色变化
			chartData.setColor(new Color(r,g,b)); //Color.BLUE			
			chartData.setLegendText(fd.format(sampling.getSamplingTime()));			
			chartData.setDatasets(datasets);
			chartDatas.add(chartData);		
			
		}

		// 生成图形
		this.chart = JFreeChartFactory.createXYLineChart("地质滑坡"+chartTitle+titlename+"曲线图",
				createTimespanSubTitles(), "深度(m)","位移值(mm)", true, chartDatas,true);
		XYPlot xyPlot = (XYPlot) chart.getPlot();// 取得JFreeChart对象的XYPlot
		xyPlot.getRangeAxis().setRange(0-maxX-1, maxX+1);
		xyPlot.getDomainAxis().setRange(0-maxY, 0);
		
		return super.chart();
	}

	public String dayExportExcel(){
		//默认查询一周值
		if(null == beginTime){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7) ;
			beginTime = calendar.getTime();
		}
		if(null == endTime){
			endTime = new Date();
		}		
		return exportExcel(ContextKeys.DAY_EXPORT);
	}
	
	public String monthExportExcel(){
		getMonthParameter();
		return exportExcel(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearExportExcel(){
		getYearParameter();
		return exportExcel(ContextKeys.YEAR_EXPORT);
	}

	public String exportExcel(Integer exportType)
	{
	
		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}
		
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
		this.response = response; 
	}


 

	public Integer getStartYearId() {
		return StartYearId;
	}

	public void setStartYearId(Integer startYearId) {
		StartYearId = startYearId;
	}

	public Integer getStartMonthId() {
		return StartMonthId;
	}

	public void setStartMonthId(Integer startMonthId) {
		StartMonthId = startMonthId;
	}

	public Integer getEndYearId() {
		return EndYearId;
	}

	public void setEndYearId(Integer endYearId) {
		EndYearId = endYearId;
	}

	public Integer getEndMonthId() {
		return EndMonthId;
	}

	public void setEndMonthId(Integer endMonthId) {
		EndMonthId = endMonthId;
	}

	/**
	 * @param chartType the chartType to set
	 */
	public void setChartType(Integer chartType) {
		this.chartType = chartType;
	}

	/**
	 * @return the chartType
	 */
	public Integer getChartType() {
		return chartType;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
	
}
