/*
 * Project platform
 *
 * Classname BaseSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-11 18:58
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.base.service;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.model.BaseSampling;
import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.export.ExcelExportInterface;

/**
 * 采集历史数据业务接口基类
 * 
 * @version v1.0.0
 * @author 
 */
public interface BaseSamplingService<T extends BaseSampling> {

	/**
	 * 设置 要查询的采集数据类型
	 * @param dataType
	 * @return
	 */
	public abstract void setSamplingDataType(SamplingDataType dataType);
	
	/**
	 * 读取 要查询的采集数据类型
	 * @return
	 */
	public abstract SamplingDataType getSamplingDataType();
	
	/**
	 * 检查检查装置是否为空；如果监测装置或ID和编码都为空，即是。
	 * @param sensor
	 * @return
	 */
	public abstract boolean checkNullOrEmpty(Sensor sensor);
	/**
	 * 获取数据
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public abstract T getSampling(String id);

	/**
	 * 获取全部数据
	 * 
	 * @return
	 */
	public abstract List<T> getAllSamplings();

	/**
	 * 根据监测装置查询数据
	 * 
	 * @param sensorCode
	 *            监测装置
	 * @return
	 */
	public abstract List<T> getSamplings(Sensor sensor);

	/**
	 * 根据起至时间查询数据
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public abstract List<T> getSamplings(Date beginTime, Date endTime);

	/**
	 * 根据监测装置和起至时间查询数据
	 * 
	 * @param sensorCode
	 *            监测装置
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public abstract List<T> getSamplings(Sensor sensor, Date beginTime, Date endTime);

	/**
	 * 查询分页数据
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public abstract Page<T> getPageSamplings(long pageNo, long pageSize);

	/**
	 * 根据监测装置查询分页数据
	 * 
	 * @param sensorCode
	 *            监测装置
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public abstract Page<T> getPageSamplings(Sensor sensor, long pageNo, long pageSize);

	/**
	 * 根据起至时间查询分页数据
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public abstract Page<T> getPageSamplings(Date beginTime, Date endTime, long pageNo,
			long pageSize);

	
	/**
	 * 
	 * @param sensor  （精确到天数)
	 * @param beginTime
	 * @param endTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page<T> getPageSamplings(Sensor sensor, Date beginTime, Date endTime,
			long pageNo, long pageSize);

	
	/**
	 * 根据监测装置和起至时间查询分页数据 （精确到时分秒）
	 * 
	 * @param sensorCode
	 *            监测装置
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public abstract Page<T> getPageSamplingsByDateTime(Sensor sensor, Date beginTime, Date endTime,
			long pageNo, long pageSize);
	

	/**
	 * 导出Excel
	 * @param sensor
	 * @param beginTime
	 * @param endTime
	 * @param Samplings
	 * @param os
	 * @return
	 */
	public abstract boolean  exportExcel(Sensor sensor, Date beginTime,Date endTime,List<T> Samplings,ExcelExportInterface callBack,OutputStream os);

}
