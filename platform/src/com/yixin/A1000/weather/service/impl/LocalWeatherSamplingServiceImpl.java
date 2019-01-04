package com.yixin.A1000.weather.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.weather.dao.LocalWeatherSamplingDao;
import com.yixin.A1000.weather.model.LocalWeatherSampling;
import com.yixin.A1000.weather.service.LocalWeatherSamplingService;
import com.yixin.framework.util.SystemInfo;
import com.yixin.framework.util.WeatherReport;


public class LocalWeatherSamplingServiceImpl implements LocalWeatherSamplingService {

	
	private LocalWeatherSamplingDao localWeatherSamplingDao;

	public void setLocalWeatherSamplingDao(
			LocalWeatherSamplingDao localWeatherSamplingDao) {
		this.localWeatherSamplingDao = localWeatherSamplingDao;
	}

	
    @Override
	public  String getWebTodayWeatherHtml(String basePath,WeatherReport weatherInfo) {
		
    	String  style = new SystemInfo().getSystemTheme() ;
    	
		StringBuffer weatherInfoHtml = new StringBuffer();
	    if  (weatherInfo.getToday_weather() !=null ) {
		    weatherInfoHtml.append(weatherInfo.getCityName() +"&nbsp;&nbsp;");
		    weatherInfoHtml.append("<img src='" + basePath + "resource/theme/" + style + "/images/messageFrame/weather/" + weatherInfo.getToday_weather_png1()+"' width='20' height='20' />");
		    if (! weatherInfo.getToday_weather_png1().equals(weatherInfo.getToday_weather_png2())){
		         weatherInfoHtml.append("<img src='" + basePath + "resource/theme/" + style + "/images/messageFrame/weather/" + weatherInfo.getToday_weather_png2()+"' width='20' height='20' />");          
		     }
		    weatherInfoHtml.append("&nbsp;"+weatherInfo.getToday_weather() + "&nbsp;" + weatherInfo.getToday_winddirection() + "&nbsp;" + weatherInfo.getToday_low()+"~"+weatherInfo.getToday_high());
		                  	     
		 }
	     else {
	    	return null;
	     }
	     return  weatherInfoHtml.toString();
	   
	}
    
    
    
	@Override
	public LocalWeatherSampling getTodayWeather(WeatherReport weatherInfo) {
		 LocalWeatherSampling localWeather  = new LocalWeatherSampling();
       	 Date dt = new Date(); 
       	 SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");  
         String  fdt = fd.format(dt);
      	 try {
      	  	     dt = fd.parse(fdt);
      	 } catch (ParseException e) {	  
         } 
      	 localWeather.setAcquisitionTime(dt); 
      	 localWeather.setTemperature_low(weatherInfo.getToday_low());    //最低气温

      	 localWeather.setTemperature_hight(weatherInfo.getToday_high()); //最高气湿

         localWeather.setStartIcon(weatherInfo.getToday_weather_png1());//图片1
      	 localWeather.setEndIcon(weatherInfo.getToday_weather_png2()); //图片2
      	 localWeather.setWeather(weatherInfo.getToday_weather());  //天气
      	 localWeather.setWindDesc(weatherInfo.getToday_winddirection());//风向实况 
      	 localWeather.setLiveWeather(weatherInfo.getToday_weather_detail());  //天气实况
      	 //localWeather.setExponent(weatherInfo.getToday_weather_index());//指数 	 
         return  localWeather;
	}
	
	
	@Override
	public void addLocalWeather(LocalWeatherSampling localWeatherSampling) {
		this.localWeatherSamplingDao.save(localWeatherSampling);
	}


	@Override
	public String getLocalTodayWeatherHtml(String basePath) {
		 String  style = new SystemInfo().getSystemTheme() ;
		 List<LocalWeatherSampling>  todayWeatherList = this.localWeatherSamplingDao.getTodayWeather();
		 StringBuffer weatherInfoHtml = new StringBuffer();
	     WeatherReport  weatherInfo = new  WeatherReport();
	     
	     String name = weatherInfo.getIPCityName();
	 	 
	     if ( name != null  &&  !"\"".equals(name.trim()) &&  !"".equals(name.trim()) ) {
	    	 weatherInfo.setCityName(name);
	 	 }
	     
		 if ( todayWeatherList.size() > 0 ){				
			 Iterator<LocalWeatherSampling>  iterator = todayWeatherList.iterator();
			 LocalWeatherSampling todayWeather = iterator.next();
			 
		  	 weatherInfoHtml.append(weatherInfo.getCityName() +"&nbsp;&nbsp;");
	   	     weatherInfoHtml.append("<img src='" + basePath + "resource/theme/" + style + "/images/messageFrame/weather/" + todayWeather.getStartIcon()+"' width='20' height='20' />");
	         if (! todayWeather.getStartIcon().equals(todayWeather.getEndIcon())){
	           weatherInfoHtml.append("<img src='" + basePath + "resource/theme/" + style + "/images/messageFrame/weather/" + todayWeather.getEndIcon()+"' width='20' height='20' />");          
	         }
	   	     weatherInfoHtml.append("&nbsp;"+todayWeather.getWeather() + "&nbsp;" + todayWeather.getWindDesc() + "&nbsp;" + todayWeather.getTemperature_low()+"~"+todayWeather.getTemperature_hight());			 
		 }
		 else  {
			 return  null;
		 }
	 	 return  weatherInfoHtml.toString();
	}

	
	
}
