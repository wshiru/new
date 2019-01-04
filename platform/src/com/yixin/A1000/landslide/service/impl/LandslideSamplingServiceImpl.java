/*
 * Project platform
 *
 * Classname TowerTiltSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.landslide.dao.LandslideSamplingDao;
import com.yixin.A1000.landslide.dao.LandslideSamplingDetailDao;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
 

/**
 * 地质滑坡业务实现类
 * 
 * @version v1.0.0
 * @author 
 */

public class LandslideSamplingServiceImpl extends BaseSamplingServiceImpl<LandslideSampling> implements LandslideSamplingService {

	private LandslideSamplingDetailDao landslideSamplingDetailDao;
	
	
	
	@Override
	public List<LandslideSamplingDetail> getSamplingLandslideDetail(Sensor sensor,
			Integer pointNo, Date beginDate, Date endDate) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		if(null!=sensor){
			conditionMap.put("landslideSampling.sensor", sensor);
		}
		if(null != pointNo ){
			conditionMap.put("pointNo", pointNo);
		}
			
		conditionMap.put("landslideSampling.dataType", this.getSamplingDataType().getDatatype());
		
		//DateProperty dateProperty = new DateProperty("landslideSampling.samplingTime", beginDate, endDate);
		//dateProperty.setDataOrder(DataOrder.DESC);
		 
		Date dt1 =  beginDate;
		Date dt2 =  endDate;		
		SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");		
	    
	    
	    try {
	    	String s1 = fd.format(beginDate);
			dt1 = fds.parse(s1 + " 00:00:00");
			
			String s2 = fd.format(endDate);
		    dt2 = fds.parse(s2 + " 23:59:59");
		} catch (ParseException e) {
				
		}
	    
		DateProperty dateProperty = new DateProperty("landslideSampling.samplingTime", dt1, dt2);
		dateProperty.setDataOrder(DataOrder.DESC);		
		 
		return this.landslideSamplingDetailDao.getAllLikeProperties(conditionMap, dateProperty);
		
	}
	@Override
	public List<LandslideSamplingDetail> getSamplingLandslideDetail(
			LandslideSampling landslideSampling) {		 
		return landslideSamplingDetailDao.getAllByProperty("landslideSampling", landslideSampling);
	}	
	
	/*
	@Override
	public boolean exportExcel(Sensor sensor, Date beginTime, Date endTime,
			List<LandslideSampling> Samplings, OutputStream os) {
		
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
		      Iterator<LandslideSampling> iterator = Samplings.iterator();
		      
			  while (iterator.hasNext()) {
				  LandslideSampling map = iterator.next();
					  
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
	*/

	@Override
	public LandslideSampling getLastLandslideDetail(Sensor sensor) {
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensor", sensor);
		Date beginTime,endTime;
		
		endTime = new Date();		
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(endTime);		
		calendar.add(Calendar.DATE, -3) ;
		beginTime = calendar.getTime();		
 
		DateProperty dateProperty = new DateProperty("samplingTime", beginTime, endTime);
		dateProperty.setDataOrder(DataOrder.DESC);
		Collection<LandslideSampling> results = this.samplingDao.getPageByProperties(map, dateProperty, 1, 1)
				.getRecords();
		LandslideSampling landslideSampling = (results.size() > 0) ? results.iterator().next() : null;
		
		if(landslideSampling!=null){
			List<LandslideSamplingDetail> landslideSamplingDetails = landslideSamplingDetailDao.getAllByProperty("landslideSampling", landslideSampling);
			landslideSampling.setLandslideSamplingDetails(landslideSamplingDetails);
		}		
		return landslideSampling;
		*/
		LandslideSamplingDao landslideSamplingDao = (LandslideSamplingDao) samplingDao;
		return landslideSamplingDao.getLastLandslideSampling(sensor);		
	}


	/**
	 * @param landslideSamplingDetailDao the landslideSamplingDetailDao to set
	 */
	public void setLandslideSamplingDetailDao(LandslideSamplingDetailDao landslideSamplingDetailDao) {
		this.landslideSamplingDetailDao = landslideSamplingDetailDao;
	}






}
