/*
 * Project platform
 *
 * Classname TowerTiltSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract TowerTiltSampling getLastTowerTilt(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.analysis.towertilt.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.base.model.Page;


/**
 * 杆塔倾斜分析接口类
 * 
 * @author 梁立全
 * 
 */
public interface TowerTiltAnalysisService extends
		BaseSamplingService<TowerTiltSampling> {

	// 日分析数据
	public abstract List<Map<String, Object>> getDayTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime);

	public abstract Page<Map<String, Object>> getDayTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			long pageSize);

	// 月分析数据
	public abstract List<Map<String, Object>> getMonthTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime);

	public abstract Page<Map<String, Object>> getMonthTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			final long pageSize);

	// 年分析数据
	public abstract List<Map<String, Object>> getYearTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime);

	public abstract Page<Map<String, Object>> getYearTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			final long pageSize);

	// 极值分析
	public abstract List<Map<String, Object>> getExtremeValueTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime,
			final Integer AnalysiStype, final String DataType);

	/**
	 * 
	 * @param sensor
	 *            传感器
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param pageNo
	 *            起始页号
	 * @param pageSize
	 *            结束页号
	 * @param AnalysiStype
	 *            数据类型 0：日数据 1：月数据 2:年数据
	 * @param DataType
	 *            数据项字段名 （ 倾斜度、 顺线倾斜度、 横向倾斜度、顺线倾斜角、横向倾斜角）
	 * @return
	 */
	public abstract Page<Map<String, Object>> getExtremeValueTowerTiltSamplings(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			final long pageSize, final Integer AnalysiStype,
			final String DataType);
	
	/**
	 * 
	 * @param exportType  导出类型  0. 小时数据  1.日数据   2.月数据   3.年数据 
	 * @param sensor
	 * @param beginTime
	 * @param endTime
	 * @param Samplings   采集数据集
	 * @param os
	 * @param dataName :数据项名称 (极值统计）
	 * @param 时间格式类型 :       0. 小时数据  1.日数据   2.月数据   3.年数据 (极值统计）
	 *
	 * @return
	 */
	public abstract boolean  exportExcel(int exportType,Sensor sensor, Date beginTime, Date endTime,
		List<Map<String, Object>> Samplings,OutputStream os,String dataName,int ExtreType);
	
	
 

	/**
	 * 根据ID查找监测装置
	 * 
	 * @param id
	 *            要查找监测装置的ID
	 * @return 监测装置
	 */
	public abstract Sensor getSensor(String id);
	

}
