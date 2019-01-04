/*
 * Project platform
 *
 * Classname ContaminationSamplingAction.java
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

package com.yixin.A1000.contamination.web;

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
import com.yixin.A1000.contamination.model.ContaminationParameter;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.contamination.service.ContaminationParameterService;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 污秽度监测Action类
 * 
 * @version v1.0.0
 * @author
 * 
 */
public class ContaminationSamplingAction extends
		BaseSamplingAction<ContaminationSampling> implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 267440495309052530L;

	
	/** 是否显示盐密 */
	private boolean showEsdd = true;
	
	/** 是否显示灰密 */
	private boolean showNsdd = true;

	/** 是否显示日最高温度 */
	private boolean showDailyMaxTemperature = true;

	/** 是否显示日最低温度 */
	private boolean showDailyMinTemperature = true;

	/** 是否显示日最大湿度 */
	private boolean showDailyMaxHumidity = true;
	
	/** 是否显示日最小湿度 */
	private boolean showDailyMinHumidity = true;	


	private HttpServletResponse response;
	
	/** 污秽度监测参数接口**/
	private ContaminationParameterService contaminationParameterService;
	
	/** 安装图片接口**/
	private PictureService pictureService;
	
	
	public ContaminationSamplingAction(){
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
		

		//最新数据
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		Page<ContaminationSampling> ttsPage = samplingService.getPageSamplingsByDateTime(
				this.sensor, beginTime, endTime, 1, 1);
		request.setAttribute("realData", ttsPage);
		
		//上一日
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -2) ;
		
		samplingService.setSamplingDataType(SamplingDataType.DAY);
		ttsPage = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);
		request.setAttribute("daliyData", ttsPage);
		
		//上周
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
		beginTime = calendar.getTime();
		samplingService.setSamplingDataType(SamplingDataType.DAY);		
		ttsPage = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, beginTime, endTime, 1, 1);		
		request.setAttribute("weekData", ttsPage);
		//calendar.setTime(new Date());
		this.beginTime = beginTime;
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		
		//上月
		calendar.add(Calendar.DATE, -32) ;
		samplingService.setSamplingDataType(SamplingDataType.MONTH);		
		ttsPage = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);
		request.setAttribute("monthData", ttsPage);
		
		//6月前
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -366) ;
		samplingService.setSamplingDataType(SamplingDataType.YEAR);		
		ttsPage = this.samplingService.getPageSamplingsByDateTime(
				this.sensor, calendar.getTime(), endTime, 1, 1);				
		request.setAttribute("yearData", ttsPage);	
		
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

		Hashtable<RegularTimePeriod, Number> esddDatasets = null;
		Hashtable<RegularTimePeriod, Number> nsddDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> maxTDatasets = null;
		Hashtable<RegularTimePeriod, Number> minTDatasets = null;
		Hashtable<RegularTimePeriod, Number> maxHDatasets = null;
		Hashtable<RegularTimePeriod, Number> minHDatasets = null;


		int chartCount = 0 ;
		/* 设置并添加等盐密数据 */
		if (showEsdd) {
			TimeSeriesChartData esddChartData = new TimeSeriesChartData();
			esddChartData.setColor(Color.RED);
			esddChartData.setLegendText("盐密");
			esddChartData.setOrdinateText("盐密(mg/cm2)");
			//giChartData.setOrdinateLocation((chartCount % 2 ==0) ? AxisLocation.BOTTOM_OR_LEFT : AxisLocation.BOTTOM_OR_LEFT);
			esddChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			esddDatasets = new Hashtable<RegularTimePeriod, Number>();
			esddChartData.setDatasets(esddDatasets);
			chartCount++;
			chartDatas.add(esddChartData);
		}

		/* 设置并添加灰密数据 */
		if (showNsdd) {
			TimeSeriesChartData nsddChartData = new TimeSeriesChartData();
			nsddChartData.setColor(Color.GREEN);
			nsddChartData.setLegendText("灰密");
			nsddChartData.setOrdinateText("灰密(mg/cm2)");			
			nsddChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			nsddDatasets = new Hashtable<RegularTimePeriod, Number>();
			nsddChartData.setDatasets(nsddDatasets);

			chartDatas.add(nsddChartData);
		}
		/* 设置并添加日最高温度数据 */
		if (showDailyMaxTemperature) {
			TimeSeriesChartData maxTChartData = new TimeSeriesChartData();
			maxTChartData.setColor(Color.BLUE);
			maxTChartData.setLegendText("日最高温度");
			maxTChartData.setOrdinateText("温度(℃)");			
			maxTChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			maxTDatasets = new Hashtable<RegularTimePeriod, Number>();
			maxTChartData.setDatasets(maxTDatasets);

			chartDatas.add(maxTChartData);
		}

		/* 设置并添加日最低温度数据 */
		if (showDailyMinTemperature) {
			TimeSeriesChartData minTChartData = new TimeSeriesChartData();
			minTChartData.setColor(Color.CYAN);
			minTChartData.setLegendText("日最低温度");
			minTChartData.setOrdinateText("温度(℃)");			
			minTChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			minTDatasets = new Hashtable<RegularTimePeriod, Number>();
			minTChartData.setDatasets(minTDatasets);
			chartDatas.add(minTChartData);
		}

		/* 设置并添加日最大湿度数据 */
		if (showDailyMaxHumidity) {
			TimeSeriesChartData maxHChartData = new TimeSeriesChartData();
			maxHChartData.setColor(Color.MAGENTA);
			maxHChartData.setLegendText("日最大湿度");
			maxHChartData.setOrdinateText("湿度(％RH)");			
			maxHChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			maxHDatasets = new Hashtable<RegularTimePeriod, Number>();
			maxHChartData.setDatasets(maxHDatasets);
			chartDatas.add(maxHChartData);
		}
		
		/* 设置并添加日最小湿度数据 */
		if (showDailyMinHumidity) {
			TimeSeriesChartData minHChartData = new TimeSeriesChartData();
			minHChartData.setColor(Color.MAGENTA);
			minHChartData.setLegendText("日最小湿度");
			minHChartData.setOrdinateText("湿度(％RH)");			
			minHChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			minHDatasets = new Hashtable<RegularTimePeriod, Number>();
			minHChartData.setDatasets(minHDatasets);
			chartDatas.add(minHChartData);
		}		

		/* 填充数据内容 */
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		List<ContaminationSampling> ttSamplings = this.samplingService
				.getSamplings(sensor, beginTime, endTime);

		Iterator<ContaminationSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			ContaminationSampling sampling = (ContaminationSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			
			if (showEsdd) {
				Double gi = sampling.getEsdd();
				if (null != gi)
					esddDatasets.put(t, gi);
			}

			if (showNsdd) {
				Double te = sampling.getNsdd();
				if (null != te)
					nsddDatasets.put(t, te);
			}

			if (showDailyMaxTemperature) {
				Double td = sampling.getDailyMaxTemperature();
				if (null != td)
					maxTDatasets.put(t, td);
			}

			if (showDailyMinTemperature) {
				Double wya = sampling.getDailyMinTemperature();
				if (null != wya)
					minTDatasets.put(t, wya);
			}

			if (showDailyMaxHumidity) {
				Double da = sampling.getDailyMaxHumidity();
				if (null != da)
					maxHDatasets.put(t, da);
			}
			
			if (showDailyMinHumidity) {
				Double da = sampling.getDailyMinHumidity();
				if (null != da)
					minHDatasets.put(t, da);
			}			
			 
		}

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("污秽度监测历史采样数据曲线图",
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
		List<ContaminationSampling>  ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);
		
	
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
			final Iterator<ContaminationSampling> it= ttSamplings.iterator();
			boolean ex = ee.buileExcelStream(os, new ExcelExportInterface(){

				@Override
				public List<String> addTitle(Integer row) {
					String s = "污秽度监测历史采样数据;;;;;";
					return ExcelExport.Array2List(s.split(";"));
				}

				@Override
				public List<String> addHead(Integer row) {
					String s = "采集时间;等值覆冰厚度(mm);综合悬挂载荷(N);不均衡张力差(N);绝缘子串风偏角(°) ;绝缘子串偏斜角(°)";
					return ExcelExport.Array2List(s.split(";")); 
				}

				@Override
				public List<String> addRow(Integer row) {
					List<String> ls = null;
					if(it.hasNext()){
						ContaminationSampling p = it.next();
						ls = new ArrayList<String>();
						ExcelExport.addObject(ls, p.getSamplingTime());
						ExcelExport.addObject(ls, p.getEsdd());
						ExcelExport.addObject(ls, p.getNsdd());
						ExcelExport.addObject(ls, p.getDailyMaxTemperature());
						ExcelExport.addObject(ls, p.getDailyMinTemperature());
						ExcelExport.addObject(ls, p.getDailyMaxHumidity());
						ExcelExport.addObject(ls, p.getDailyMinHumidity());
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

	 
	public boolean isShowEsdd() {
		return showEsdd;
	}
	public void setShowEsdd(boolean showEsdd) {
		this.showEsdd = showEsdd;
	}
	public boolean isShowNsdd() {
		return showNsdd;
	}
	public void setShowNsdd(boolean showNsdd) {
		this.showNsdd = showNsdd;
	}
	public boolean isShowDailyMaxTemperature() {
		return showDailyMaxTemperature;
	}
	public void setShowDailyMaxTemperature(boolean showDailyMaxTemperature) {
		this.showDailyMaxTemperature = showDailyMaxTemperature;
	}
	public boolean isShowDailyMinTemperature() {
		return showDailyMinTemperature;
	}
	public void setShowDailyMinTemperature(boolean showDailyMinTemperature) {
		this.showDailyMinTemperature = showDailyMinTemperature;
	}
	public boolean isShowDailyMaxHumidity() {
		return showDailyMaxHumidity;
	}
	public void setShowDailyMaxHumidity(boolean showDailyMaxHumidity) {
		this.showDailyMaxHumidity = showDailyMaxHumidity;
	}
	public boolean isShowDailyMinHumidity() {
		return showDailyMinHumidity;
	}
	public void setShowDailyMinHumidity(boolean showDailyMinHumidity) {
		this.showDailyMinHumidity = showDailyMinHumidity;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	/**
	 * @param 污秽度监测参数接口 set
	 */
	public void setContaminationParameterService(ContaminationParameterService contaminationParameterService) {
		this.contaminationParameterService = contaminationParameterService;
	}
	/**
	 * @return the 污秽度监测参数接口
	 */
	public ContaminationParameterService getContaminationParameterService() {
		return contaminationParameterService;
	}
	/**
	 * @param pictureService the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

}
