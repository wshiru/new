/*
 * Project platform
 *
 * Classname WeatherSamplingDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 下午13:39
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.dao.impl.springhibernate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.dao.WeatherSamplingDao;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;

/**
 * 微气象DAO的Spring+Hibernate实现类
 * 
 * @version v1.0.0
 * @author
 */
public class WeatherSamplingDaoImpl extends
		BaseDaoImpl<WeatherSampling, String> implements WeatherSamplingDao {

	@Override
	public List<Map<String, Object>> getAllDayWeatyerAnalysis(Sensor sensor,
			Date beginTime, Date endTime) {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		StringBuffer sb = new StringBuffer();
		StringBuffer sbfield = new StringBuffer();

		sbfield.append(" TRUNC (samplingtime) samplingTime, MAX (maxwindspeed) max_windspeed,");
		sbfield.append(" MAX (temperature) max_temperature, MIN (temperature) min_temperature,");
		sbfield.append(" MAX (humidity) max_humidity, MIN (humidity) min_humidity,");
		sbfield.append(" MAX (airpressure) max_airpressure, MIN (airpressure) min_airpressure,");
		sbfield.append(" MAX (dailyrainfall) max_dailyrainfall,");
		sbfield.append(" MAX (precipitationintensity) max_precipitationintensity,");
		sbfield.append(" MAX (radiationintensity) max_radiationintensity");

		sb.append(" from  T_Weathersampling  ");
		sb.append(" where   1=1 ");
		sb.append(" and   SENSORID ='%s' ");
		sb.append(" and   SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" group by  Trunc(SAMPLINGTIME) ");
		sb.append(" order by  1 ");

		String sql = String.format(sb.toString(), sensor.getSensorId(),
				fd.format(beginTime) + " 00:00:00", fd.format(endTime)
						+ " 23:59:59");

		final String queryString = " select " + sbfield + sql;

		List list = (List) this.getAllBySQL(queryString);
		
		return this.formatRetVal(list);

	}

	@Override
	public Page<Map<String, Object>> getPageDayWeatyerAnalysis(Sensor sensor,
			Date beginTime, Date endTime, final long pageNo, final long pageSize) {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		StringBuffer sb = new StringBuffer();
		StringBuffer sbfield = new StringBuffer();

		sbfield.append(" TRUNC (samplingtime) samplingTime, MAX (maxwindspeed) max_windspeed,");
		sbfield.append(" MAX (temperature) max_temperature, MIN (temperature) min_temperature,");
		sbfield.append(" MAX (humidity) max_humidity, MIN (humidity) min_humidity,");
		sbfield.append(" MAX (airpressure) max_airpressure, MIN (airpressure) min_airpressure,");
		sbfield.append(" MAX (dailyrainfall) max_dailyrainfall,");
		sbfield.append(" MAX (precipitationintensity) max_precipitationintensity,");
		sbfield.append(" MAX (radiationintensity) max_radiationintensity");

		sb.append(" from  T_Weathersampling  ");
		sb.append(" where   1=1 ");
		sb.append(" and   SENSORID ='%s' ");
		sb.append(" and   SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" group by  Trunc(SAMPLINGTIME) ");
		sb.append(" order by  1 ");

		String sql = String.format(sb.toString(), sensor.getSensorId(),
				fd.format(beginTime) + " 00:00:00", fd.format(endTime)
						+ " 23:59:59");

		final String queryString = " select  " + sbfield + sql;

		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(queryString);
						int startIndex = (int) ((pageNo - 1) * pageSize);
						query.setFirstResult(startIndex);
						query.setMaxResults((int) pageSize);
						return query.list();
					}
				});

		List objCount = (List) this.getAllBySQL("select count(*) as count from (select 1 "
				+ sql + ") a");
		String sCount = String.valueOf(objCount.get(0));
		int count = Integer.valueOf(sCount);
		
		return new Page(pageNo, pageSize, count, formatRetVal(list));
	}

	@SuppressWarnings("rawtypes")
	private List<Map<String, Object>> formatRetVal(List list) {
		List<Map<String, Object>> retval = new ArrayList<Map<String, Object>>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (Object o : list) {

			Object[] arr = (Object[]) o;
			Map<String, Object> map = new HashMap<String, Object>();
			
			

			String samplingTime = String.valueOf(arr[0]);
			Date dt;
			try {
				dt = sdf.parse(samplingTime);
				map.put("samplingTime", dt);
			} catch (ParseException e) {
			}
			if(arr[1] != null){
				Double maxwindspeed = Double.valueOf(String.valueOf(arr[1]));
				map.put("max_windspeed", maxwindspeed);	
			}
			if(arr[2] != null){
				Double max_temperature = Double.valueOf(String.valueOf(arr[2]));
				map.put("max_temperature", max_temperature);	
			}
			if(arr[3] != null){
				Double min_temperature = Double.valueOf(String.valueOf(arr[3]));
				map.put("min_temperature", min_temperature);	
			}
			if(arr[4] != null){
				Double max_humidity = Double.valueOf(String.valueOf(arr[4]));
				map.put("max_humidity", max_humidity);	
			}
			if(arr[5] != null){
				Double min_humidity = Double.valueOf(String.valueOf(arr[5]));
				map.put("min_humidity", min_humidity);	
			}
			if(arr[6] != null){
				Double max_airpressure = Double.valueOf(String.valueOf(arr[6]));
				map.put("max_airpressure", max_airpressure);	
			}
			if(arr[7] != null){
				Double min_airpressure = Double.valueOf(String.valueOf(arr[7]));
				map.put("min_airpressure", min_airpressure);	
			}
			if(arr[8] != null){
				Double dailyrainfall = Double.valueOf(String.valueOf(arr[8]));
				map.put("dailyrainfall", dailyrainfall);	
			}
			if(arr[9] != null){
				Double max_precipitationintensity = Double.valueOf(String.valueOf(arr[9]));
				map.put("max_precipitationintensity", max_precipitationintensity);	
			}
			
			if(arr[10] != null){
				Double max_radiationintensity = Double.valueOf(String.valueOf(arr[10]));
				map.put("max_radiationintensity", max_radiationintensity);	
			}
			retval.add(map);
		}

		return retval;

	}
	@SuppressWarnings("unused")
	private List<WeatherSampling> formatRetval(List<Object> list , String[] names) 
	{
		List<WeatherSampling> result = new ArrayList<WeatherSampling>();
		for(Object row:list){
			Object[] values = (Object[])row;
			
			WeatherSampling e = new WeatherSampling();
 			Map<String,Method> methodMap = new HashMap<String,Method>();
			for(Method f: e.getClass().getMethods())
			{
				//System.out.println(String.format("%s",f.getName()));
				methodMap.put(f.getName(), f);
			}
			
			for(int i = 0 ;i<names.length;i++){
				Method method;				
				String key = names[i].trim();
				Object value = values[i];
				try {
					if(value!=null)
					{
						//method = e.getClass().getMethod("set"+key,new Class[1](value));
						method = methodMap.get("set"+key);
						if(method!=null)
						{
							System.out.println(String.format("%s %s",key,value.getClass().getName()));
							
							if(value.getClass().getName().equals("java.math.BigDecimal"))
							{
								Double d = Double.valueOf(value.toString());
								method.invoke(e, d);
							}
							else
							{
								method.invoke(e, value);
							}
							System.out.println(e.toString());
						}
					}
				} catch (SecurityException e1) {
				} catch (IllegalArgumentException e1) {
				} catch (InvocationTargetException e1) {
				} catch (IllegalAccessException e3) {
	 
				}						
			}
			result.add(e);
		}	
		return result;
	}	
	@Override
	public WeatherSampling getLastWeatherSampling(Sensor sensor) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensor", sensor);
		
		Date beginTime,endTime;
		
		endTime = new Date();		
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(endTime);		
		//calendar.add(Calendar.DATE, -0.5) ;
		calendar.add(Calendar.HOUR, -1) ;
		beginTime = calendar.getTime();		

		DateProperty dateProperty = new DateProperty("samplingTime",beginTime,endTime);
		dateProperty.setDataOrder(DataOrder.DESC);
		//System.out.println("begin getLastWeather &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ (new Date()).toString());
		List<WeatherSampling> list = this.getAllByProperties(map, dateProperty,1);
		//System.out.println("end getLastWeather &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ (new Date()).toString());
		 
		return (list.size() > 0) ? list.iterator().next() : null;	
		
		
	}
}
