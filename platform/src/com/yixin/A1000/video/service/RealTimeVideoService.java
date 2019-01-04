/*
 * Project platform
 *
 * Class RealTimeWeatherService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-8 下午02:49:18
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.video.service;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.system.model.User;

/**
 * 微气象数据召测业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RealTimeVideoService {

	/**
	 * 打开电源
	 * @param sensor	传感 器
	 * @param minute	打开时间
	 */
	public abstract boolean openPower(Sensor sensor,Integer minute);
}
