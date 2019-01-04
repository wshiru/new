package com.yixin.A1000.weather.dao;

import java.util.List;

import com.yixin.A1000.weather.model.LocalWeatherSampling;
import com.yixin.framework.base.dao.BaseDao;

public interface LocalWeatherSamplingDao extends BaseDao<LocalWeatherSampling, String> {
	
	public abstract List<LocalWeatherSampling> getTodayWeather();
	
	
}
