/*
 * Project platform
 *
 * Classname BaseSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-11 19:05
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.base.service.impl;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.model.BaseSampling;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.export.ExcelExportInterface;

/**
 * 采样历史数据业务接口实现基类
 * 
 * @version v1.0.0
 * @author 
 */
public class BaseSamplingServiceImpl<T extends BaseSampling> implements BaseSamplingService<T> {

	/** DAO接口  */
	protected BaseDao<T, String> samplingDao;

	/** 保存监测装置是否为空 */
	private Boolean isNullOrEmpty = null;
	
	/** 采集数据类型 */
	private SamplingDataType dataType = SamplingDataType.REAL; 
	
	/**
	 * 设置DAO接口
	 * @param samplingDao
	 */
	public void setSamplingDao(BaseDao<T, String> samplingDao) {
		this.samplingDao = samplingDao;
	}
	
	/**
	 * 检查监测装置是否为空(如果ID和编码都为空)
	 * @param sensor
	 * @return
	 */
	private boolean isNullOrEmpty(Sensor sensor) {
		//if (null == isNullOrEmpty) {
			isNullOrEmpty = checkNullOrEmpty(sensor);
		//}
		return isNullOrEmpty;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#checkNullOrEmpty(com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public boolean checkNullOrEmpty(Sensor sensor) {
		if(null == sensor){
			return true;
		}else{
			String id = sensor.getSensorId();
			String code = sensor.getSensorCode();
			if(null == id && null == code)
				return  true;
			else if(id.isEmpty() && code.isEmpty())
				return  true;	
			else 
				return false;	
		}
	}
	
	/**
	 * 生成时间查询属性（精确到日)
	 * @param beginTime
	 * 			开始时间 
	 * @param endTime
	 * 			结束时间
	 * @return
	 */
	private DateProperty createDateProperty(Date beginTime, Date endTime) {
			
		Date dt1 =  beginTime;
		Date dt2 =  endTime;		
		SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");		
	    String s1 = fd.format(beginTime);
	    
	    try {
			dt1 = fds.parse(s1 + " 00:00:00");			
			String s2 = fd.format(endTime);
		    dt2 = fds.parse(s2 + " 23:59:59");
		} catch (ParseException e) {
				
		}
	    
		DateProperty dateProperty = new DateProperty("samplingTime", dt1, dt2);
		dateProperty.setDataOrder(DataOrder.DESC);
		return dateProperty;
	}
	
	/**
	 * 生成时间查询属性（精确到时分秒)
	 * @param beginTime
	 * 			开始时间 
	 * @param endTime
	 * 			结束时间
	 * @return
	 */
	
	private DateProperty createDateTimeProperty(Date beginTime, Date endTime)  {	
		//SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateProperty dateProperty = new DateProperty("samplingTime", beginTime, endTime);
		dateProperty.setDataOrder(DataOrder.DESC);
		return dateProperty;	
	}
	
	/**
	 * 生成时间查询属性
	 * @return
	 */
	private DateProperty createDateProperty() {
		DateProperty dateProperty = new DateProperty();
		dateProperty.setPropertyName("samplingTime");
		dateProperty.setDataOrder(DataOrder.DESC);
		return dateProperty;	
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getSampling(java.lang.String)
	 */
	@Override
	public T getSampling(String id) {	
		return this.samplingDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getAllSamplings()
	 */
	@Override
	public List<T> getAllSamplings() {	
		return this.samplingDao.getAll(createDateProperty());
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getSamplings(com.yixin.A1000.archive.model.Sensor)
	 */
	@Override
	public List<T> getSamplings(Sensor sensor) {
		if (isNullOrEmpty(sensor))
			return getAllSamplings();
		String id = sensor.getSensorId();
		isNullOrEmpty = null; 
		Map<String, Object> map = new HashMap<String, Object>();
		if(!id.isEmpty())
			map.put("sensor", sensor);
		else
			map.put("sensor.sensorCode", sensor.getSensorCode());
		map.put("dataType", this.dataType.getDatatype());
		return samplingDao.getAllByProperties(map, createDateProperty());
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getSamplings(java.util.Date, java.util.Date)
	 */
	@Override
	public List<T> getSamplings(Date beginTime, Date endTime) {
		if (null == beginTime && null == endTime)
			return getAllSamplings();
		return samplingDao.getAll(createDateProperty(beginTime, endTime));
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getSamplings(com.yixin.A1000.archive.model.Sensor, java.util.Date, java.util.Date)
	 */
	@Override
	public List<T> getSamplings(Sensor sensor, Date beginTime, Date endTime) {
		if (isNullOrEmpty(sensor))
			return getSamplings(beginTime, endTime);
		else if (null == beginTime && null == endTime) {
			return getSamplings(sensor);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = sensor.getSensorId();
			if(!id.isEmpty())
				map.put("sensor", sensor);
			else
				map.put("sensor.sensorCode", sensor.getSensorCode());
			isNullOrEmpty = null; 
			map.put("dataType", this.dataType.getDatatype());
			return samplingDao.getAllByProperties(map, createDateProperty(beginTime, endTime));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getPageSamplings(long, long)
	 */
	@Override
	public Page<T> getPageSamplings(long pageNo, long pageSize) {	
		return samplingDao.getPage(createDateProperty(), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getPageSamplings(com.yixin.A1000.archive.model.Sensor, long, long)
	 */
	@Override
	public Page<T> getPageSamplings(Sensor sensor, long pageNo, long pageSize) {
		if (isNullOrEmpty(sensor))
			return this.getPageSamplings(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		String id = sensor.getSensorId();
		if(!id.isEmpty())
			map.put("sensor", sensor);
		else
			map.put("sensor.sensorCode", sensor.getSensorCode());
		isNullOrEmpty = null; 
		map.put("dataType", this.dataType.getDatatype());
		return this.samplingDao.getPageByProperties(map, createDateProperty(), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getPageSamplings(java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<T> getPageSamplings(Date beginTime, Date endTime, long pageNo,
			long pageSize) {
		if (null == beginTime && null == endTime)
			return this.getPageSamplings(pageNo, pageSize);
		else {
			
			return this.samplingDao.getPage(createDateProperty(beginTime, endTime), pageNo, pageSize);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getPageSamplingsByDateTime(com.yixin.A1000.archive.model.Sensor, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<T> getPageSamplingsByDateTime(Sensor sensor, Date beginTime, Date endTime, long pageNo, long pageSize) {
		if (isNullOrEmpty(sensor))
			return this.getPageSamplings(beginTime, endTime, pageNo, pageSize);
		else if (null == beginTime && null == endTime) {
			return this.getPageSamplings(sensor, pageNo, pageSize);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = sensor.getSensorId();
			String code = sensor.getSensorCode();
			if(!id.isEmpty())
				map.put("sensor", sensor);
			else
				map.put("sensor.sensorCode", code);
			isNullOrEmpty = null; 
			map.put("dataType", this.dataType.getDatatype());
			return this.samplingDao.getPageByProperties(map, createDateTimeProperty(beginTime, endTime), pageNo, pageSize);
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#getPageSamplings(com.yixin.A1000.archive.model.Sensor, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<T> getPageSamplings(Sensor sensor, Date beginTime,
			Date endTime, long pageNo, long pageSize) {
		
		if (isNullOrEmpty(sensor))
			return this.getPageSamplings(beginTime, endTime, pageNo, pageSize);
		else if (null == beginTime && null == endTime) {
			return this.getPageSamplings(sensor, pageNo, pageSize);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = sensor.getSensorId();
			String code = sensor.getSensorCode();
			if(!id.isEmpty())
				map.put("sensor", sensor);
			else
				map.put("sensor.sensorCode", code);
			isNullOrEmpty = null; 
			map.put("dataType", this.dataType.getDatatype());
			return this.samplingDao.getPageByProperties(map, createDateProperty(beginTime, endTime), pageNo, pageSize);
		}		
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.A1000.base.service.BaseSamplingService#exportExcel(com.yixin.A1000.archive.model.Sensor, java.util.Date, java.util.Date, java.util.List, java.io.OutputStream)
	 */	
	@Override
	public boolean exportExcel(Sensor sensor, Date beginTime, Date endTime,
			List<T> Samplings,ExcelExportInterface callBack, OutputStream os) {

		 String tmptitle ="";//头标题
		 String smpDate =""; //采集时间
		 String type ="";
		 
		 SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// smpDate  = String.format("%s 至 %s", fd.format(beginTime),fd.format(endTime));
		 type = "历史采样数据";
	     tmptitle = String.format("地质滑坡%s",type);

		 try 
		 { 	   
		
		      WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件   
		      WritableSheet wsheet = wbook.createSheet(type, 0); // sheet名称  
		           
		      // 设置excel标题   
		      WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD, 
		                          false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
		      WritableCellFormat wcfFC = new WritableCellFormat(wfont);		    
		      //wcfFC.setBackground(Colour.AQUA); 
		      wsheet.addCell(new Label(1, 0, tmptitle, wcfFC)); 
		      
		      
		      
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
		      wsheet.addCell(new Label(1, 3, "倾斜度(°)",wcfFC2));  
		      wsheet.addCell(new Label(2, 3, "顺线倾斜度(°)",wcfFC2));  
		      wsheet.addCell(new Label(3, 3, "横向倾斜度(°)",wcfFC2));  
		      wsheet.addCell(new Label(4, 3, "顺线倾斜角 ",wcfFC2));  
		      wsheet.addCell(new Label(5, 3, "横向倾斜角 ",wcfFC2));  
		   
		      int i = 4; 
		      Iterator<T> iterator = Samplings.iterator();
		      
			  while (iterator.hasNext()) {
				  T map = iterator.next();
					/*
					Date samplingTime = map.getSamplingTime();
					Double gi = map.getd();
					Double ga = map.getGradientAlongLines();
					Double lt = map.getLateralTilt();
					Double ax = map.getAngleX();
					Double ay = map.getAngleY();
					
					smpDate  =  fd.format(samplingTime);
					wsheet.addCell(new Label(0, i,smpDate));		
					wsheet.addCell(new Label(1, i,gi.toString()));
					wsheet.addCell(new Label(2, i,ga.toString()));
					wsheet.addCell(new Label(3, i,lt.toString()));
					wsheet.addCell(new Label(4, i,ax.toString()));
					wsheet.addCell(new Label(5, i,ay.toString()));
					*/
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
	public void setSamplingDataType(SamplingDataType dataType) {
		this.dataType = dataType;
	}

	@Override
	public SamplingDataType getSamplingDataType() {
		return this.dataType;
	}
	
}
