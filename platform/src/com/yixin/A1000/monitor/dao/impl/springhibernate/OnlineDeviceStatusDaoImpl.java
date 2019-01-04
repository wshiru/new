/*
 * Project platform
 *
 * Class LineDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午03:50:06
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.dao.impl.springhibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.monitor.dao.OnlineDeviceStatusDao;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.monitor.model.OnlineRateStatistics;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;
import com.yixin.framework.base.model.Page;

/**
 * 线路DAO的Spring+Hibernate实现类
 * 
 * @version v1.0.0
 * @author
 */
public class OnlineDeviceStatusDaoImpl extends
		BaseDaoImpl<OnlineDeviceStatus, String> implements
		OnlineDeviceStatusDao {

	@Override
	public Page<Map<String, Object>> getPageOnlineRateStatistics(
			final Date beginDate, final Date endDate , final String sensorTypeId , final long pageNo,
			final long pageSize) {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(beginDate);
		String s2 = fd.format(endDate);
		long diff = endDate.getTime() - beginDate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		if (days < 1) {
			days = 1;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.lineName,t.towerCode,s.sensorCode,s.sensorId,  (case when cnt is null then 0 else cnt end) cnt,"
				+ String.valueOf(days)
				+ " as days, (case when cnt is null then 0 else cnt end)/"
				+ String.valueOf(days) + " * 100 rate ");
		sb.append(" FROM T_sensor s LEFT JOIN ( ");
		sb.append("     Select sensorId, count(1) cnt FROM ( ");
		sb.append("        Select sensorId, TRUNC (CreateTime) CreateTime FROM T_SENSORHEARTBEATINFO ");
		sb.append("        where CreateTime  between TO_Date('" + s1
				+ "','YYYY-MM-DD') and TO_Date('" + s2 + "','YYYY-MM-DD')");
		sb.append("         GROUP BY sensorId, TRUNC (CreateTime)) d");
		sb.append("     GROUP BY sensorId) d");
		sb.append(" ON s.sensorId = d.SensorId");
		sb.append(" LEFT JOIN T_SensorType st ON s.sensorTypeId = st.sensorTypeId ");
		sb.append(" LEFT JOIN T_Tower t ON s.towerId = t.towerId");
		sb.append(" LEFT JOIN T_Line l ON t.lineId = l.lineId");		
		if(sensorTypeId != null && !"".equals(sensorTypeId)){
			sb.append(" where s.sensorTypeId='"+sensorTypeId+"'");
		}
		
		sb.append(" Order by 5 Desc");

		String[] names = { "lineName", "towerCode", "sensorCode", "sensorId",
				"count", "days", "rate" };
		return this.getPageBySQLToMap(sb.toString(), names, pageNo, pageSize);

	}

	@Override
	public List<Map<String, Object>> getAllOnlineRateStatistics(Date beginDate,
			Date endDate , final String sensorTypeId ) {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(beginDate);
		String s2 = fd.format(endDate);
		long diff = endDate.getTime() - beginDate.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		if (days < 1) {
			days = 1;
		}		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT l.lineName,t.towerCode,s.sensorCode,s.sensorId,  (case when cnt is null then 0 else cnt end) cnt,"
				+ String.valueOf(days)
				+ " as days, (case when cnt is null then 0 else cnt end)/"
				+ String.valueOf(days) + " * 100 rate ");
		sb.append(" FROM T_sensor s LEFT JOIN ( ");
		sb.append("     Select sensorId, count(1) cnt FROM ( ");
		sb.append("        Select sensorId, TRUNC (CreateTime) CreateTime FROM T_SENSORHEARTBEATINFO ");
		sb.append("        where CreateTime  between TO_Date('" + s1
				+ "','YYYY-MM-DD') and TO_Date('" + s2 + "','YYYY-MM-DD')");
		sb.append("         GROUP BY sensorId, TRUNC (CreateTime)) d");
		sb.append("     GROUP BY sensorId) d");
		sb.append(" ON s.sensorId = d.SensorId");
		sb.append(" LEFT JOIN T_SensorType st ON s.sensorTypeId = st.sensorTypeId ");
		sb.append(" LEFT JOIN T_Tower t ON s.towerId = t.towerId");
		sb.append(" LEFT JOIN T_Line l ON t.lineId = l.lineId");
		if(sensorTypeId != null && !"".equals(sensorTypeId)){
			sb.append(" where s.sensorTypeId='"+sensorTypeId+"'");
		}		
		
		sb.append(" Order by 5 Desc");

		String[] names = { "lineName", "towerCode", "sensorCode", "sensorId",
				"count", "days", "rate" };
		return this.getAllBySQLToMap(sb.toString(), names);

	}

}
