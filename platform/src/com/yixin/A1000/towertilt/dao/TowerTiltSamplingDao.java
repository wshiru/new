/*
 * Project platform
 *
 * Classname TowerTiltSamplingDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14：49
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.towertilt.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.Page;

/**
 * 杆塔倾斜DAO接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface TowerTiltSamplingDao extends BaseDao<TowerTiltSampling, String> {

	//日分析数据
	public abstract  List<Map<String, Object>>   getDayTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime);
	public abstract  Page<Map<String, Object>>   getDayTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime, final long pageNo, long pageSize);
	
	//月分析数据
	public abstract  List<Map<String, Object>>   getMonthTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime);	
	public abstract  Page<Map<String, Object>>   getMonthTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime,final  long pageNo, final long pageSize);
	
	//年分析数据
	public abstract  List<Map<String, Object>>   getYearTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime);
	public abstract  Page<Map<String, Object>>   getYearTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime, final long pageNo, final long pageSize);
	
	
    
	
	//极值分析
	public abstract  List<Map<String, Object>>  getExtremeValueTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime,final Integer  AnalysiStype,final  String  DataType);
	
	/**
     * 
     * @param sensor  传感器
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param pageNo  起始页号
     * @param pageSize 结束页号
     * @param AnalysiStype 数据类型  0：日数据   1：月数据   2:年数据 
     * @param DataType  数据项字段名   （ 倾斜度、 顺线倾斜度、 横向倾斜度、顺线倾斜角、横向倾斜角）
     * @return
     */
	public abstract  Page<Map<String, Object>>  getExtremeValueTowerTiltSamplings(Sensor sensor, Date beginTime,Date endTime,
			final long pageNo, final long pageSize,
			final Integer  AnalysiStype,final  String  DataType);
	
    
	/**
	 * 取得最新采集的数据
	 * @param sensor 
	 * @return
	 */
	public abstract TowerTiltSampling getLastTowerTiltSampling(Sensor sensor);
	
	
	
}