/*
 * Project platform
 *
 * Classname IceThincknessSamplingDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:52
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.icethinckness.dao.impl.springhibernate;

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
import com.yixin.A1000.icethinckness.dao.IceThincknessSamplingDao;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;


/**
 * 覆冰监测DAO的Spring+Hibernate实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class IceThincknessSamplingDaoImpl extends BaseDaoImpl<IceThincknessSampling, String> implements
IceThincknessSamplingDao {

	//月份从0开 始算   0：表示1月份  1：表示2月份，以此类推
    private static String getLastDayOfMonth(int year, int month) {      
         Calendar cal = Calendar.getInstance();      
         cal.set(Calendar.YEAR, year);      
         cal.set(Calendar.MONTH, month);      
         cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));   
         return  new   SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());   
    }    
 
    private  static String getFirstDayOfMonth(int year, int month) {      
         Calendar cal = Calendar.getInstance();      
         cal.set(Calendar.YEAR, year);      
         cal.set(Calendar.MONTH, month);   
         cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));   
         return   new   SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());   
    }

   
    private  boolean  IsNotNull(String value){
    	
    	if ( value == null ) {
    		return  false;
    	}
    	if ( value.trim().length() == 0 ){
    		return false;
    	}
    	if ( value.trim().equals("null")){
    		return false;
    	}
    	return  true;
    }
    
   
    @SuppressWarnings("rawtypes")
	private   List<Map<String, Object>>  formatRetVal(List list){
    	List<Map<String, Object>> retval = new ArrayList<Map<String, Object>>();
    
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    	for (Object o : list) {
			
    		Object[] arr = (Object[]) o;
			Map<String, Object> map = new HashMap<String, Object>();
			String iceThincknessSamplingId = String.valueOf(arr[0]);
			map.put("iceThincknessSamplingId", iceThincknessSamplingId);
			
			String SensorId = String.valueOf(arr[1]);
			map.put("sensorId", SensorId);
			
			String samplingTime = String.valueOf(arr[2]);
			Date dt;
			try {
				dt = sdf.parse(samplingTime);
				map.put("samplingTime", dt);
			} catch (ParseException e) {
			}
					
			if  ( this.IsNotNull(String.valueOf(arr[3])) ){
				Integer Alerm_Flag = Integer.valueOf(String.valueOf(arr[3]));
				map.put("Alerm_Flag", Alerm_Flag);	
			}
		
			
			if  ( this.IsNotNull(String.valueOf(arr[4])) ){	
				Double   inclination_v1 = Double.valueOf(String.valueOf(arr[4]));  
				String   inclination_v2 = String.format("%.2f", inclination_v1);
				map.put("inclination", Double.valueOf(inclination_v2));
			}
			
			
			if  ( this.IsNotNull(String.valueOf(arr[5])) ){
				Double   gradientAlongLines_v1 = Double.valueOf(String.valueOf(arr[5]));  
				String   gradientAlongLines_v2 = String.format("%.2f", gradientAlongLines_v1);
				map.put("gradientAlongLines", Double.valueOf(gradientAlongLines_v2));
			}
			
			
			if  ( this.IsNotNull(String.valueOf(arr[6])) ){
			    Double   lateralTilt_v1 = Double.valueOf(String.valueOf(arr[6]));  
			    String   lateralTilt_v2 = String.format("%.2f", lateralTilt_v1);
			    map.put("lateralTilt", Double.valueOf(lateralTilt_v2));
			}
			
					
			if  ( this.IsNotNull(String.valueOf(arr[7])) ){	
				Double   angle_x_v1 = Double.valueOf(String.valueOf(arr[7]));  
				String   angle_x_v2 = String.format("%.2f", angle_x_v1);
				map.put("angle_x", Double.valueOf(angle_x_v2));
			}
			
			if  ( this.IsNotNull(String.valueOf(arr[8])) ){	
				 Double   angle_y_v1 = Double.valueOf(String.valueOf(arr[8]));  
			     String   angle_y_v2 = String.format("%.2f", angle_y_v1);
			     map.put("angle_y", Double.valueOf(angle_y_v2));
			}
			
			if  ( this.IsNotNull(String.valueOf(arr[9])) ){		
				Double   generalInclination_v1 = Double.valueOf(String.valueOf(arr[9]));  
				String   generalInclination_v2 = String.format("%.2f", generalInclination_v1);
				map.put("generalInclination", Double.valueOf(generalInclination_v2));
			}

			if  ( this.IsNotNull(String.valueOf(arr[10])) ){		
				String acquisitionTime = String.valueOf(arr[10]);
				Date dt1;
				try {
					dt1 = sdf.parse(acquisitionTime);
					map.put("acquisitionTime", dt1);
				} catch (ParseException e) {
				}
			}
			
			if  ( this.IsNotNull(String.valueOf(arr[11])) ){		
				
				String createTime = String.valueOf(arr[11]);
				Date dt2;
				try {
					dt2 = sdf.parse(createTime);
					map.put("createTime", dt2);
				}catch (ParseException e) {
				}
			}
			
			retval.add(map);
		}	
    	
       	return retval;
       	
    }
    
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> getDayIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");	
	   /* StringBuffer sb = new  StringBuffer(); 
	    sb.append(" select  a.*  from T_TOWERTILTSAMPLING   a   where  not exists(  select   *   from T_TOWERTILTSAMPLING  where  to_char(SAMPLINGTIME,'yyyy-mm-dd')   =   to_char(a.SAMPLINGTIME,'yyyy-mm-dd') " + 
		 " and   SAMPLINGTIME > a.SAMPLINGTIME )   and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and  SENSORID ='%s' ");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		final String queryString =  String.format(
				sb.toString(),
				fd.format(beginTime) + " 00:00:00" ,
				fd.format(endTime) + " 23:59:59",
				sensor.getSensorId());	
		
		*/
	   
		StringBuffer sb = new  StringBuffer();	
			
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy-mm-dd')   = to_char(t.SAMPLINGTIME,'yyyy-mm-dd') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
			
			
		String sql =  String.format(sb.toString(),
					sensor.getSensorId(),
					fd.format(beginTime) + " 00:00:00" ,
					fd.format(endTime) + " 23:59:59" );	

		final  String queryString = " select  *  " + sql;

		
		List list = (List) this.getAllBySQL(queryString);
		
		return  this.formatRetVal(list);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page<Map<String, Object>> getDayIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime, final long pageNo, final long pageSize) {
		
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");	
	    
		/*StringBuffer sb = new  StringBuffer(); 
	    sb.append("from T_TOWERTILTSAMPLING   a   where  not exists(  select   *   from T_TOWERTILTSAMPLING  where  to_char(SAMPLINGTIME,'yyyy-mm-dd')   =   to_char(a.SAMPLINGTIME,'yyyy-mm-dd') " + 
		 " and   SAMPLINGTIME > a.SAMPLINGTIME )   and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" and  SENSORID ='%s' ");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		String sql =  String.format(
				sb.toString(),
				fd.format(beginTime) + " 00:00:00" ,
				fd.format(endTime) + " 23:59:59",
				sensor.getSensorId());	
		
		final  String queryString = " select  a.* " + sql;
		*/
		

	    StringBuffer sb = new  StringBuffer();	
		
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy-mm-dd')   = to_char(t.SAMPLINGTIME,'yyyy-mm-dd') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		
		String sql =  String.format(sb.toString(),
				sensor.getSensorId(),
				fd.format(beginTime) + " 00:00:00" ,
				fd.format(endTime) + " 23:59:59" );	

		final  String queryString = " select  *  " + sql;

		
		
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

		List objCount = (List) this.getAllBySQL("select count(*) as count " + sql );
		String sCount = String.valueOf(objCount.get(0));
		int count = Integer.valueOf(sCount);

		return new Page(pageNo, pageSize, count, this.formatRetVal(list));
	
	}

	

	@SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public List<Map<String, Object>> getMonthIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		//起始月第一天
		String  FirstDayOfMonth =  getFirstDayOfMonth(cal1.get(cal1.YEAR),cal1.get(cal1.MONTH));
	    //结束月最后一天
		String  LastDayOfMonth =  getLastDayOfMonth(cal2.get(cal2.YEAR),cal2.get(cal2.MONTH));
			
	    StringBuffer sb = new  StringBuffer();	
		
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy-mm')   = to_char(t.SAMPLINGTIME,'yyyy-mm') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		String sql =  String.format(
				sb.toString(),
				sensor.getSensorId(),
				FirstDayOfMonth  + " 00:00:00" ,
				LastDayOfMonth + " 23:59:59");	

		final  String queryString = " select  *  " + sql;
			
    	List list = (List) this.getAllBySQL(queryString);
		
		return  this.formatRetVal(list);
		
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	@Override
	public Page<Map<String, Object>> getMonthIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime, final long pageNo, final long pageSize) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		//起始月第一天
		String  FirstDayOfMonth =  getFirstDayOfMonth(cal1.get(cal1.YEAR),cal1.get(cal1.MONTH));
	    //结束月最后一天
		String  LastDayOfMonth =  getLastDayOfMonth(cal2.get(cal2.YEAR),cal2.get(cal2.MONTH));
			
		
		StringBuffer sb = new  StringBuffer();	
		
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy-mm')   = to_char(t.SAMPLINGTIME,'yyyy-mm') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		String sql =  String.format(
				sb.toString(),
				sensor.getSensorId(),
				FirstDayOfMonth  + " 00:00:00" ,
				LastDayOfMonth + " 23:59:59");	
		
		final  String queryString = " select  *  " + sql;
		
	
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

		
		List objCount = (List) this.getAllBySQL("select count(*) as count " + sql );
		
		String sCount = String.valueOf(objCount.get(0));
		int count = Integer.valueOf(sCount);

		return new Page(pageNo, pageSize, count, this.formatRetVal(list));
		
	}

	
	
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public List<Map<String, Object>> getYearIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		//该年1月1号起
		String  FirstDayOfMonth =  getFirstDayOfMonth(cal1.get(cal1.YEAR),0);
		
		//该年12月份最后一天
		String  LastDayOfMonth =  getLastDayOfMonth(cal2.get(cal2.YEAR),11);
			
		
		
	    StringBuffer sb = new  StringBuffer();	
		
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy')   = to_char(t.SAMPLINGTIME,'yyyy') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		String sql =  String.format(
				sb.toString(),
				sensor.getSensorId(),
				FirstDayOfMonth  + " 00:00:00" ,
				LastDayOfMonth + " 23:59:59");	
		
		final  String queryString = " select  *  " + sql;

        List list = (List) this.getAllBySQL(queryString);
	
  	    return  this.formatRetVal(list);

	}

	
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Override
	public Page<Map<String, Object>> getYearIceThincknessSamplings(Sensor sensor,
			Date beginTime, Date endTime, final long pageNo, final long pageSize) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		//该年1月1号起
		String  FirstDayOfMonth =  getFirstDayOfMonth(cal1.get(cal1.YEAR),0);
		
		//该年12月份最后一天
		String  LastDayOfMonth =  getLastDayOfMonth(cal2.get(cal2.YEAR),11);
			
	
	    StringBuffer sb = new  StringBuffer();	
		
		sb.append(" from  (	SELECT *    FROM  T_TOWERTILTSAMPLING  t  WHERE   SAMPLINGTIME   = (SELECT MAX(SAMPLINGTIME) FROM   T_TOWERTILTSAMPLING   WHERE sensorid = t.sensorid  ");
		sb.append(" and  to_char(SAMPLINGTIME,'yyyy')   = to_char(t.SAMPLINGTIME,'yyyy') ) )  a ");
		sb.append(" where   1=1 ");
		sb.append(" and   a.SENSORID ='%s' ");
		sb.append(" and   a.SAMPLINGTIME  between  to_date('%s','yyyy-mm-dd hh24:mi:ss')   and    to_date('%s','yyyy-mm-dd hh24:mi:ss')");
		sb.append(" order by  a.SAMPLINGTIME ");
		
		String sql =  String.format(
				sb.toString(),
				sensor.getSensorId(),
				FirstDayOfMonth  + " 00:00:00" ,
				LastDayOfMonth + " 23:59:59");	
		
		final  String queryString = " select  *  " + sql;

		
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

		
		List objCount = (List) this.getAllBySQL("select count(*) as count " + sql );
		String sCount = String.valueOf(objCount.get(0));
		int count = Integer.valueOf(sCount);

		return new Page(pageNo, pageSize, count, this.formatRetVal(list));
		
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Override
	public Page<Map<String, Object>> getExtremeValueIceThincknessSamplings(
			Sensor sensor, Date beginTime, Date endTime,
			final long pageNo, final long pageSize,
			Integer AnalysiStype,String DataType) {
		
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");	
		
		String dateFormat = "yyyy-mm-dd";
		String begingDateStr = fd.format(beginTime)  + " 00:00:00";
		String endDateStr = fd.format(endTime)  + " 23:59:59";
			
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		if ( AnalysiStype == 1  ){
			 dateFormat =  "yyyy-mm";
			 //起始月第一天
			 begingDateStr =  getFirstDayOfMonth(cal1.get(cal1.YEAR),cal1.get(cal1.MONTH))   + " 00:00:00";
		     //结束月最后一天
			 endDateStr =  getLastDayOfMonth(cal2.get(cal2.YEAR),cal2.get(cal2.MONTH)) + " 23:59:59";

		}
		else if ( AnalysiStype ==2  ){
			 dateFormat = "yyyy";
			 //该年1月1号起
			 begingDateStr =  getFirstDayOfMonth(cal1.get(cal1.YEAR),0) + " 00:00:00";		
			 //该年12月份最后一天
			 endDateStr =  getLastDayOfMonth(cal2.get(cal2.YEAR),11) + " 23:59:59";
		}
		
		StringBuffer sb = new  StringBuffer();
		sb.append(" select  to_char(SAMPLINGTIME,'%s') samplingTime , max(%s) maxValue, min(%s) minValue,avg(%s) avgValue ");
		sb.append(" from T_TOWERTILTSAMPLING   where  SENSORID ='%s'  and SAMPLINGTIME   between  to_date('%s','yyyy-mm-dd hh24:mi:ss')  and  to_date('%s','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" group  by   to_char(SAMPLINGTIME,'%s')");
		sb.append(" order  by  SAMPLINGTIME");
		
		
		System.out.println("sb:" + sb.toString());
		
	    final String queryString = String.format(sb.toString(),
	    		dateFormat,
	    		DataType,DataType,DataType,
	    		sensor.getSensorId(),
	    		begingDateStr,
	    		endDateStr,
	    		dateFormat);	
		
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

		List<Map<String, Object>> retval = new ArrayList<Map<String, Object>>();
	    
    	for (Object o : list) {	
    		Object[] arr = (Object[]) o;
			Map<String, Object> map = new HashMap<String, Object>();	
			String samplingTime = String.valueOf(arr[0]);
			map.put("samplingTime", samplingTime);
			
			//最大值
		
			if ( this.IsNotNull(String.valueOf(arr[1]))){
				Double   maxValue_v1 = Double.valueOf(String.valueOf(arr[1]));  
				String   maxValue_v2 = String.format("%.2f", maxValue_v1);
				map.put("maxValue", Double.valueOf(maxValue_v2));
			}
			//最小值
			if ( this.IsNotNull(String.valueOf(arr[2]))){
				Double   minValue_v1 = Double.valueOf(String.valueOf(arr[2]));  
				String   minValue_v2 = String.format("%.2f", minValue_v1);
				map.put("minValue", Double.valueOf(minValue_v2));
			}
			
			//平均值
			if ( this.IsNotNull(String.valueOf(arr[3]))){
				Double   avgValue_v1 = Double.valueOf(String.valueOf(arr[3]));  
				String   avgValue_v2 = String.format("%.2f", avgValue_v1);
				map.put("avgValue", Double.valueOf(avgValue_v2));
			}
			
			retval.add(map);
    	}
		
		List objCount = (List) this.getAllBySQL("select count(*) as count  from (" + queryString + ")" );
		String sCount = String.valueOf(objCount.get(0));
		int count = Integer.valueOf(sCount);
		
		return new Page(pageNo, pageSize, count, retval);
	
	}

	
	@SuppressWarnings({ "rawtypes", "static-access" })
	@Override
	public List<Map<String, Object>> getExtremeValueIceThincknessSamplings(
			Sensor sensor, Date beginTime, Date endTime, Integer AnalysiStype,
			String DataType) {
		
	    SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");	
		
		String dateFormat = "yyyy-mm-dd";
		String begingDateStr = fd.format(beginTime)  + " 00:00:00";
		String endDateStr = fd.format(endTime)  + " 23:59:59";
			
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(beginTime);
		cal2.setTime(endTime);	
		
		if ( AnalysiStype == 1  ){
			 dateFormat =  "yyyy-mm";
			 //起始月第一天
			 begingDateStr =  getFirstDayOfMonth(cal1.get(cal1.YEAR),cal1.get(cal1.MONTH))   + " 00:00:00";
		     //结束月最后一天
			 endDateStr =  getLastDayOfMonth(cal2.get(cal2.YEAR),cal2.get(cal2.MONTH)) + " 23:59:59";

		}
		else if ( AnalysiStype ==2  ){
			 dateFormat = "yyyy";
			 //该年1月1号起
			 begingDateStr =  getFirstDayOfMonth(cal1.get(cal1.YEAR),0) + " 00:00:00";		
			 //该年12月份最后一天
			 endDateStr =  getLastDayOfMonth(cal2.get(cal2.YEAR),11) + " 23:59:59";
		}
		
		StringBuffer sb = new  StringBuffer();
		sb.append(" select  to_char(SAMPLINGTIME,'%s') samplingTime , max(%s) maxValue, min(%s) minValue,avg(%s) avgValue ");
		sb.append(" from T_TOWERTILTSAMPLING   where  SENSORID ='%s'  and SAMPLINGTIME   between  to_date('%s','yyyy-mm-dd hh24:mi:ss')  and  to_date('%s','yyyy-mm-dd hh24:mi:ss') ");
		sb.append(" group  by   to_char(SAMPLINGTIME,'%s')");
		sb.append(" order  by  SAMPLINGTIME");
		
	    final String queryString = String.format(sb.toString(),
	    		dateFormat,
	    		DataType,DataType,DataType,
	    		sensor.getSensorId(),
	    		begingDateStr,
	    		endDateStr,
	    		dateFormat);	
	    
	    List list = (List) this.getAllBySQL(queryString);
		
	    
	    List<Map<String, Object>> retval = new ArrayList<Map<String, Object>>();
	    
    	for (Object o : list) {	
    		Object[] arr = (Object[]) o;
			Map<String, Object> map = new HashMap<String, Object>();	
			String samplingTime = String.valueOf(arr[0]);
			map.put("samplingTime", samplingTime);
			
			//最大值
		
			if ( this.IsNotNull(String.valueOf(arr[1]))){
				Double   maxValue_v1 = Double.valueOf(String.valueOf(arr[1]));  
				String   maxValue_v2 = String.format("%.2f", maxValue_v1);
				map.put("maxValue", Double.valueOf(maxValue_v2));
			}
			//最小值
			if ( this.IsNotNull(String.valueOf(arr[2]))){
				Double   minValue_v1 = Double.valueOf(String.valueOf(arr[2]));  
				String   minValue_v2 = String.format("%.2f", minValue_v1);
				map.put("minValue", Double.valueOf(minValue_v2));
			}
			
			//平均值
			if ( this.IsNotNull(String.valueOf(arr[3]))){
				Double   avgValue_v1 = Double.valueOf(String.valueOf(arr[3]));  
				String   avgValue_v2 = String.format("%.2f", avgValue_v1);
				map.put("avgValue", Double.valueOf(avgValue_v2));
			}
			
			retval.add(map);
    	 }
    	
    	return   retval;
		
	}

	@Override
	public IceThincknessSampling getLastIceThincknessSampling(Sensor sensor) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensor", sensor);
		Date beginTime,endTime;
		
		endTime = new Date();		
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(endTime);		
		//calendar.add(Calendar.DATE, -1) ;
		calendar.add(Calendar.HOUR, -1) ;
		beginTime = calendar.getTime();		

		DateProperty dateProperty = new DateProperty("samplingTime",beginTime,endTime);
		dateProperty.setDataOrder(DataOrder.DESC);
		List<IceThincknessSampling> list = this.getAllByProperties(map, dateProperty,1);		 

		return (list.size() > 0) ? list.iterator().next() : null;		
	}


	
    
}
