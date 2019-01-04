package com.yixin.A1000.weather.service;

import com.yixin.A1000.weather.model.LocalWeatherSampling;
import com.yixin.framework.util.WeatherReport;


/**
 * 本地天气采集业务层接口类--
 * 
 * @author 梁立全

 *
 */

public interface LocalWeatherSamplingService {
	
	/**
	 *  添加天气记录
	 * @param localWeatherSampling  天气对象
    */	
	
	public abstract void addLocalWeather(LocalWeatherSampling localWeatherSampling);
     
	/**
      * 从一个已获取气象数据的 WeatherReport类 生产一笔记录 
      * @param weatherInfo 天气预报类

      * @return
      */
	
	public abstract  LocalWeatherSampling getTodayWeather(WeatherReport weatherInfo);
	/**
	 * 从数据库获取主页面显示的HTML脚本
	 * @param basePath 请求路径
	 * @return
	 */
	public abstract  String getLocalTodayWeatherHtml(String basePath);
	/**
	 * 从已经获取天气数据的  WeatherReport 类得到主页页显示的HTML脚本
	 * @param basePath 请求路径
	 * @param weatherInfo 天气预报类

	 * @return
	 */
	public abstract  String getWebTodayWeatherHtml(String basePath,WeatherReport weatherInfo);
	

}
