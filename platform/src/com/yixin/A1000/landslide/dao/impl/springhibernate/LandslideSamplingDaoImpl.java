package com.yixin.A1000.landslide.dao.impl.springhibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.landslide.dao.LandslideSamplingDao;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

public class LandslideSamplingDaoImpl extends BaseDaoImpl<LandslideSampling, String> implements LandslideSamplingDao {

	@Override
	public LandslideSampling getLastLandslideSampling(Sensor sensor) {
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
		List<LandslideSampling> list = this.getAllByProperties(map, dateProperty,1);		 

		return (list.size() > 0) ? list.iterator().next() : null;	
	}
	
}
