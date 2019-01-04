package com.yixin.A1000.weather.dao.impl.springhibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.weather.dao.LocalWeatherSamplingDao;
import com.yixin.A1000.weather.model.LocalWeatherSampling;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;


public class LocalWeatherSamplingDaoImpl extends BaseDaoImpl<LocalWeatherSampling, String> implements LocalWeatherSamplingDao {

	@SuppressWarnings("unchecked")
	public List<LocalWeatherSampling> getTodayWeather() {
		Date  dt  = new Date();
	    SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	    String ft = fd.format(dt);
	    
	    try {
		   dt  = fd.parse(ft);
		} catch (ParseException e) { 
		}
	    String queryString  = "from  LocalWeatherSampling where acquisitionTime =? ";
		List<LocalWeatherSampling> list  =  this.getHibernateTemplate().find(queryString,dt);
		return  list;
		
	}
   
}
