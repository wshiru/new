/*
 * Project platform
 *
 * Classname IceThincknessSamplingAction.java
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

package com.yixin.A1000.icethinckness.web;

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
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.A1000.icethinckness.service.IceThincknessParameterService;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherSamplingService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 覆冰监测Action类
 * 
 * @version v1.0.0
 * @author
 * 
 */
public class IceThincknessSamplingAction extends
		BaseSamplingAction<IceThincknessSampling> implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = 267440495309052530L;

	
	/** 是否显示等值覆冰厚度 */
	private boolean showEqualIceThickness = true;
	
	/** 是否显示综合悬挂载荷 */
	private boolean showTension = true;

	/** 是否显示不均衡张力差 */
	private boolean showTensionDifference = true;

	/** 是否显示绝缘子串风偏角 */
	private boolean showWindageYawAngle = true;

	/** 是否显示绝缘子串偏斜角 */
	private boolean showDeflectionAngle = true;


	private HttpServletResponse response;
	
	/** 覆冰监测参数接口**/
	private IceThincknessParameterService iceThincknessParameterService;
	/** 微气象参数接口**/
	private WeatherSamplingService weatherSamplingService;
	
	/** 安装图片接口**/
	private PictureService pictureService;
	
	
	public IceThincknessSamplingAction(){
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
		//开始取得覆冰最新数据
		//**********************************************************		
		
		
		
		//最新数据
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		Page<IceThincknessSampling> ttsPage = samplingService.getPageSamplingsByDateTime(
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

		Hashtable<RegularTimePeriod, Number> etDatasets = null;
		Hashtable<RegularTimePeriod, Number> tDatasets = null;
		Hashtable<RegularTimePeriod, Number> tdDatasets = null;

		Hashtable<RegularTimePeriod, Number> wyaDatasets = null;
		Hashtable<RegularTimePeriod, Number> daDatasets = null;

		boolean showET = showEqualIceThickness;// != null && showEqualIceThickness? true:
											// false;

		boolean showT = showTension;// !=null && showTension? true: false;

		boolean showTD = showTensionDifference;// !=null && showTensionDifference? true: false;

		boolean showWYA = showWindageYawAngle;// !=null &&
												// showWindageYawAngle? true:
												// false;

		boolean showDA = showDeflectionAngle;// !=null && showDeflectionAngle? true:
											// false;
		int chartCount = 0 ;
		/* 设置并添加等值覆冰厚度数据 */
		if (showET) {
			TimeSeriesChartData etChartData = new TimeSeriesChartData();
			etChartData.setColor(Color.RED);
			etChartData.setLegendText("等值覆冰厚度");
			etChartData.setOrdinateText("等值覆冰厚度(mm)");
			//giChartData.setOrdinateLocation((chartCount % 2 ==0) ? AxisLocation.BOTTOM_OR_LEFT : AxisLocation.BOTTOM_OR_LEFT);
			etChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			etDatasets = new Hashtable<RegularTimePeriod, Number>();
			etChartData.setDatasets(etDatasets);
			chartCount++;
			chartDatas.add(etChartData);
		}

		/* 设置并添加综合悬挂载荷数据 */
		if (showT) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.GREEN);
			tChartData.setLegendText("综合悬挂载荷");
			tChartData.setOrdinateText("综合悬挂载荷(N)");			
			tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			tDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(tDatasets);

			chartDatas.add(tChartData);
		}
		/* 设置并添加不均衡张力差数据 */
		if (showTD) {
			TimeSeriesChartData tdChartData = new TimeSeriesChartData();
			tdChartData.setColor(Color.BLUE);
			tdChartData.setLegendText("不均衡张力差");
			tdChartData.setOrdinateText("不均衡张力差(N)");			
			tdChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			tdDatasets = new Hashtable<RegularTimePeriod, Number>();
			tdChartData.setDatasets(tdDatasets);

			chartDatas.add(tdChartData);
		}

		/* 设置并添加绝缘子串风偏角数据 */
		if (showWYA) {
			TimeSeriesChartData wyaChartData = new TimeSeriesChartData();
			wyaChartData.setColor(Color.CYAN);
			wyaChartData.setLegendText("绝缘子串风偏角");
			wyaChartData.setOrdinateText("绝缘子串风偏角(°)");			
			wyaChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			wyaDatasets = new Hashtable<RegularTimePeriod, Number>();
			wyaChartData.setDatasets(wyaDatasets);
			chartDatas.add(wyaChartData);
		}

		/* 设置并添加绝缘子串偏斜角数据 */
		if (showDA) {
			TimeSeriesChartData daChartData = new TimeSeriesChartData();
			daChartData.setColor(Color.MAGENTA);
			daChartData.setLegendText("绝缘子串偏斜角");
			daChartData.setOrdinateText("绝缘子串偏斜角(°)");			
			daChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			daDatasets = new Hashtable<RegularTimePeriod, Number>();
			daChartData.setDatasets(daDatasets);
			chartDatas.add(daChartData);
		}

		/* 填充数据内容 */
		samplingService.setSamplingDataType(SamplingDataType.REAL);
		List<IceThincknessSampling> ttSamplings = this.samplingService
				.getSamplings(sensor, beginTime, endTime);

		Iterator<IceThincknessSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			IceThincknessSampling sampling = (IceThincknessSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			 
			if (showET) {
				Double gi = sampling.getEqualIceThickness();
				if (null != gi)
					etDatasets.put(t, gi);
			}

			if (showT) {
				Double te = sampling.getTension();
				if (null != te)
					tDatasets.put(t, te);
			}

			if (showTD) {
				Double td = sampling.getTensionDifference();
				if (null != td)
					tdDatasets.put(t, td);
			}

			if (showWYA) {
				Double wya = sampling.getWindageYawAngle();
				if (null != wya)
					wyaDatasets.put(t, wya);
			}

			if (showDA) {
				Double da = sampling.getDeflectionAngle();
				if (null != da)
					daDatasets.put(t, da);
			}
			 
		}

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("覆冰监测历史采样数据曲线图",
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
		List<IceThincknessSampling>  ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);
		
	
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
			final Iterator<IceThincknessSampling> it= ttSamplings.iterator();
			boolean ex = ee.buileExcelStream(os, new ExcelExportInterface(){

				@Override
				public List<String> addTitle(Integer row) {
					String s = "覆冰监测历史采样数据;;;;;";
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
						IceThincknessSampling p = it.next();
						ls = new ArrayList<String>();
						ExcelExport.addObject(ls, p.getSamplingTime());
						ExcelExport.addObject(ls, p.getEqualIceThickness());
						ExcelExport.addObject(ls, p.getTension());
						ExcelExport.addObject(ls, p.getTensionDifference());
						ExcelExport.addObject(ls, p.getWindageYawAngle());
						ExcelExport.addObject(ls, p.getDeflectionAngle());
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

	 

	public boolean isShowEqualIceThickness() {
		return showEqualIceThickness;
	}
	public void setShowEqualIceThickness(boolean showEqualIceThickness) {
		this.showEqualIceThickness = showEqualIceThickness;
	}
	public boolean isShowTension() {
		return showTension;
	}
	public void setShowTension(boolean showTension) {
		this.showTension = showTension;
	}
	public boolean isShowTensionDifference() {
		return showTensionDifference;
	}
	public void setShowTensionDifference(boolean showTensionDifference) {
		this.showTensionDifference = showTensionDifference;
	}
	public boolean isShowWindageYawAngle() {
		return showWindageYawAngle;
	}
	public void setShowWindageYawAngle(boolean showWindageYawAngle) {
		this.showWindageYawAngle = showWindageYawAngle;
	}
	public boolean isShowDeflectionAngle() {
		return showDeflectionAngle;
	}
	public void setShowDeflectionAngle(boolean showDeflectionAngle) {
		this.showDeflectionAngle = showDeflectionAngle;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	/**
	 * @param 覆冰监测参数接口 set
	 */
	public void setIceThincknessParameterService(IceThincknessParameterService iceThincknessParameterService) {
		this.iceThincknessParameterService = iceThincknessParameterService;
	}
	
	/**
	 * @return the 覆冰监测参数接口
	 */
	public IceThincknessParameterService getIceThincknessParameterService() {
		return iceThincknessParameterService;
	}
	
	/**
	 * @param 微气象监测参数接口 set
	 */
	public void setWeatherSamplingService(WeatherSamplingService weatherSamplingService) {
		this.weatherSamplingService = weatherSamplingService;
	}
	
	/**
	 * @return the 微气象监测参数接口
	 */
	public WeatherSamplingService getWeatherSamplingService() {
		return weatherSamplingService;
	}	
	
	/**
	 * @param pictureService the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

}
