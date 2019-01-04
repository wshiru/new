/*
 * Project platform
 *
 * Classname TowerTiltSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全
 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 梁立全
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract TowerTiltSampling getLastTowerTilt(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.analysis.towertilt.service.impl;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.Year;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.yixin.A1000.analysis.towertilt.service.TowerTiltAnalysisService;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.towertilt.dao.TowerTiltSamplingDao;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;


/**
 * 杆塔倾斜分析接口实现类
 * 
 * @author 梁立全
 * 
 */
public class TowerTiltAnalysisServiceImpl extends
		BaseSamplingServiceImpl<TowerTiltSampling> implements
		TowerTiltAnalysisService {

	private SensorService sensorService;
	
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	private TowerTiltSamplingDao towerTiltSamplingDao;

	public void setTowerTiltSamplingDao(
			TowerTiltSamplingDao towerTiltSamplingDao) {
		this.towerTiltSamplingDao = towerTiltSamplingDao;
	}

	@Override
	public List<Map<String, Object>> getDayTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		return this.towerTiltSamplingDao.getDayTowerTiltSamplings(sensor,
				beginTime, endTime);
	}

	@Override
	public Page<Map<String, Object>> getDayTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime, long pageNo, long pageSize) {
		return this.towerTiltSamplingDao.getDayTowerTiltSamplings(sensor,
				beginTime, endTime, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getMonthTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		return this.towerTiltSamplingDao.getMonthTowerTiltSamplings(sensor,
				beginTime, endTime);
	}

	@Override
	public Page<Map<String, Object>> getMonthTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime, long pageNo, long pageSize) {
		return this.towerTiltSamplingDao.getMonthTowerTiltSamplings(sensor,
				beginTime, endTime, pageNo, pageSize);
	}

	@Override
	public List<Map<String, Object>> getYearTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		return this.towerTiltSamplingDao.getYearTowerTiltSamplings(sensor,
				beginTime, endTime);
	}

	@Override
	public Page<Map<String, Object>> getYearTowerTiltSamplings(Sensor sensor,
			Date beginTime, Date endTime, long pageNo, long pageSize) {
		return this.towerTiltSamplingDao.getYearTowerTiltSamplings(sensor,
				beginTime, endTime, pageNo, pageSize);

	}

	@Override
	public Page<Map<String, Object>> getExtremeValueTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, long pageNo,
			long pageSize, Integer AnalysiStype, String DataType) {
		return this.towerTiltSamplingDao.getExtremeValueTowerTiltSamplings(
				sensor, beginTime, endTime, pageNo, pageSize, AnalysiStype,
				DataType);
	}

	@Override
	public List<Map<String, Object>> getExtremeValueTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, Integer AnalysiStype,
			String DataType) {
		return this.towerTiltSamplingDao.getExtremeValueTowerTiltSamplings(
				sensor, beginTime, endTime, AnalysiStype, DataType);

	}
	
	
	
	@Override
	public boolean exportExcel(int exportType, Sensor sensor, Date beginTime,Date endTime, List<Map<String, Object>> Samplings, OutputStream os,
			String dataName,int ExtreType) {
		// TODO Auto-generated method stub
		 
		 String tmptitle ="";//头标题
		 String smpDate =""; //采集时间
		 String type ="";
		 
		 SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat fd1 = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat fd2 = new SimpleDateFormat("yyyy年MM月");
		 SimpleDateFormat fd3 = new SimpleDateFormat("yyyy年");
					  
		 switch ( exportType ) {
		 case ContextKeys.TIME_EXPORT:
			smpDate  = String.format("%s 至 %s", fd.format(beginTime),fd.format(endTime));
			type = "杆塔倾斜历史采样数据";
		   break;
		 case ContextKeys.DAY_EXPORT:
			smpDate  = String.format("%s 至 %s", fd1.format(beginTime),fd1.format(endTime));
			type = "杆塔倾斜日数据";
			break;
		 case ContextKeys.MONTH_EXPORT:
			smpDate  = String.format("%s 至 %s", fd2.format(beginTime),fd2.format(endTime));
			type = "杆塔倾斜月数据";
			break;
		 case ContextKeys.YEAR_EXPORT:
			smpDate  = String.format("%s 至 %s", fd3.format(beginTime),fd3.format(endTime));
			type = "杆塔倾斜年数据";
			break;
	     case ContextKeys.EXTRE_EXPORT:
			smpDate  = String.format("%s 至 %s", fd3.format(beginTime),fd3.format(endTime));
			if ( ExtreType == ContextKeys.DAY_EXPORT )
			{
				type = String.format("%s极值日数据",dataName.trim());		
			}
			else if ( ExtreType == ContextKeys.MONTH_EXPORT )
			{
				type = String.format("%s极值月数据",dataName.trim());	
			}
			else if (  ExtreType == ContextKeys.YEAR_EXPORT ) 
			{
				type = String.format("%s极值年数据",dataName.trim());	
			}
			break;
	     }
		 
		 tmptitle = String.format("%s",type);
	     
	   
		 try 
		 { 	   
			 
		      WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件   
		      WritableSheet wsheet = wbook.createSheet("数据分析", 0); // sheet名称  
		      
		      // 设置excel标题   
		      WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD, 
		                          false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
		      WritableCellFormat wcfFC = new WritableCellFormat(wfont); 
		      //wcfFC.setBackground(Colour.AQUA); 
		      wsheet.addCell(new Label(1, 0, tmptitle, wcfFC));//头标题 
		      
		      wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14,WritableFont.BOLD, 
		                      false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
		      wcfFC = new WritableCellFormat(wfont);  
		      
		      WritableFont wfont1 = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, 
                      false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
              WritableCellFormat wcfFC1 = new WritableCellFormat(wfont1); 
              
		      wsheet.addCell(new Label(0, 2, "监测设备编号", wcfFC1));  
		      wsheet.addCell(new Label(1, 2, sensor.getSensorCode(), wcfFC1));  
		           
		      WritableFont wfont2 = new WritableFont(WritableFont.ARIAL, 12,WritableFont.NO_BOLD, 
                      false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
              WritableCellFormat wcfFC2 = new WritableCellFormat(wfont2); 
              wcfFC2.setBackground(Colour.AQUA);
        
              		      
              // 开始生成主体内容                   
 		     
        	  wsheet.addCell(new Label(0, 3, "采集时间 ",wcfFC2));   
      	
              if ( exportType == ContextKeys.EXTRE_EXPORT   )
              {
                  wsheet.addCell(new Label(1, 3, "最大值",wcfFC2));  
    		      wsheet.addCell(new Label(2, 3, "最小值",wcfFC2));  
    		      wsheet.addCell(new Label(3, 3, "平均值",wcfFC2));  
              }
              else {
                  wsheet.addCell(new Label(1, 3, "倾斜度(°)",wcfFC2));  
    		      wsheet.addCell(new Label(2, 3, "顺线倾斜度(°)",wcfFC2));  
    		      wsheet.addCell(new Label(3, 3, "横向倾斜度(°)",wcfFC2));  
    		      wsheet.addCell(new Label(4, 3, "顺线倾斜角 ",wcfFC2));  
    		      wsheet.addCell(new Label(5, 3, "横向倾斜角 ",wcfFC2));  	  
              }
		     
		      
		      int i = 4; 
		      Iterator<Map<String, Object>> iterator = Samplings.iterator();
			  while (iterator.hasNext()) {
					Map<String, Object> map = iterator.next();
					
					Date samplingTime = null;
					
					if ( exportType != ContextKeys.EXTRE_EXPORT )
					{
						samplingTime  = (Date) map.get("samplingTime");
					}
					
					switch ( exportType ) {
					case ContextKeys.TIME_EXPORT:
					{
						 smpDate  =  fd.format(samplingTime);
					     break;
					}
				    case ContextKeys.DAY_EXPORT:
					{
						 smpDate  =  fd1.format(samplingTime);
						break;
					}
					case ContextKeys.MONTH_EXPORT:
					{
						 smpDate  =  fd2.format(samplingTime);
						 break;
					}
				    case ContextKeys.YEAR_EXPORT:
				    {
						 smpDate  =  fd3.format(samplingTime);	 
						 break;
				    }
					case ContextKeys.EXTRE_EXPORT:
					{
						 smpDate = (String) map.get("samplingTime");			
						 break;
					}
					}
					
					wsheet.addCell(new Label(0, i,smpDate));
					
					if ( exportType == ContextKeys.EXTRE_EXPORT  ) //极值分析
					{
						Double maxValue = (Double) map.get("maxValue");
						Double minValue = (Double) map.get("minValue");
						Double avgValue = (Double) map.get("avgValue");	

						wsheet.addCell(new Label(1, i,maxValue.toString()));
						wsheet.addCell(new Label(2, i,minValue.toString()));
						wsheet.addCell(new Label(3, i,avgValue.toString()));
				
					}
					else {
						Double gi = (Double) map.get("inclination");
						Double ga = (Double) map.get("gradientAlongLines");
						Double lt = (Double) map.get("lateralTilt");
						Double ax = (Double) map.get("angle_x");
						Double ay = (Double) map.get("angle_y");
				
						wsheet.addCell(new Label(1, i,gi.toString()));
						wsheet.addCell(new Label(2, i,ga.toString()));
						wsheet.addCell(new Label(3, i,lt.toString()));
						wsheet.addCell(new Label(4, i,ax.toString()));
						wsheet.addCell(new Label(5, i,ay.toString()));
					}
									
					i++;
			  }
					 
			  // 主体内容生成结束           
		      wbook.write(); // 写入文件   
		      wbook.close();  
	          
	          return  true;
		  } 
		  catch(Exception ex) 
		  {  
		 	  return  false;
		     
		  } 
	
	}

	@Override
	public Sensor getSensor(String id) {
		return this.sensorService.getSensor(id);
		// TODO Auto-generated method stub	
	}
	


}
