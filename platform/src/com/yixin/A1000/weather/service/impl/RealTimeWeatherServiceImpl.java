/*
 * Project platform
 *
 * Class RealTimeWeatherServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-8 下午04:43:53
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.weather.model.WeatherParameter;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.RealTimeWeatherService;
import com.yixin.A1000.weather.service.WeatherParameterService;
import com.yixin.framework.system.model.User;

/**
 * 微气象数据召测业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeWeatherServiceImpl implements RealTimeWeatherService {

	/** 任务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;
	
	/** 微气象参数接口 **/
	private WeatherParameterService weatherParameterService;
	
	/** 通讯服务类 */
	private CommService commService;

	/**
	 * 设置 任务DAO接口
	 * 
	 * @param taskConfigDao
	 *            任务DAO接口
	 */
	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}

	/**
	 * 设置 任务业务接口
	 * 
	 * @param taskConfigService
	 *            任务业务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}

	/**
	 * 设置 通讯服务类
	 * 
	 * @param commService
	 *            通讯服务类
	 */
	public void setCommService(CommService commService) {
		this.commService = commService;
	}	
	
	/**
	 * 设置 微气象服务类
	 * 
	 * @param weatherParameter
	 *            微气象服务类
	 */
	public void setWeatherParameterService(WeatherParameterService weatherParameterService) {
		this.weatherParameterService = weatherParameterService;
	}	
		
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.weather.service.RealTimeWeatherService#addRealTimeTask
	 * (com.yixin.framework.system.model.User,
	 * com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public void addRealTimeTask(User user, Sensor sensor) {
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.REALTIME);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 新增下发任务 */
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.REALTIME);
		taskConfig.setCreateTime(createTime);
		taskConfig.setSensorCode(sensor.getSensorCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.WEATHER);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	@Override
	public WeatherSampling samplingWeather(Sensor sensor) {
		WeatherSampling weatherSampling = this.commService.weather(sensor.getSensorCode());
		WeatherParameter weatherParameter = this.weatherParameterService.getWeatherParameterBySensor(sensor); 
		//配置了参数
		double windXX = 1;
		if(weatherSampling.getWindSpeed()!=null)
		{
			if(weatherParameter!=null){
				double mountingHeight = weatherParameter.getMountingHeight();
		        double designHeight = weatherParameter.getDesignHeight();
		        double windCoefficient = weatherParameter.getWindCoefficient();
				if(weatherParameter!=null && weatherSampling!= null){
			        windCoefficient = Math.log(windCoefficient);
			        windXX = Math.log(mountingHeight) - windCoefficient;
		            double d = Math.log(designHeight) - windCoefficient;
		            windXX = windXX / d;                        
		  		}        
			}
		}
		//标准风速
        weatherSampling.setStrandrdWindSpeed(windXX * weatherSampling.getAverageWindSpeed10min());
        //最大风速
        weatherSampling.setMaxWindSpeed(windXX * weatherSampling.getMaxWindSpeed());
        //极大风速
        weatherSampling.setExtremeWindSpeed(windXX * weatherSampling.getExtremeWindSpeed());        
		return weatherSampling;
	}
}
