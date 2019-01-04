/*
 * Project platform
 *
 * Classname CmaParameterDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-10-21 16：51
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.dao.impl.springhibernate;


import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.dao.DeviceParameterDao;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;

public class DeviceParameterDaoImpl extends BaseDaoImpl<DeviceParameter, String> implements DeviceParameterDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceParameter> getAllCmaParams() {
		StringBuilder queryString = new StringBuilder("from DeviceParameter where  cmaInfo is not null ");
		return this.getHibernateTemplate().find(queryString.toString());		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public DeviceParameter getLastConfigCmaParams(CmaInfo cmaInfo) {
		if (null == cmaInfo ){
			return null;
		}
		DeviceParameter ret  = null;
		
		StringBuilder queryString = new StringBuilder("from DeviceParameter where  1=1 ");	
		queryString.append(" and  cmaInfo =?  and state = 3  order by createTime desc ");
	    
		List<DeviceParameter> list = this.getHibernateTemplate().find(queryString.toString(), (Object)cmaInfo);
		
		if ( !list.isEmpty() ){
		   ret =  list.iterator().next();
		}
		
        return ret;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DeviceParameter getLastConfigSensorParams(Sensor sensor) {
		if (null == sensor ){
			return null;
		}
		
			
		DeviceParameter ret  = null;
		StringBuilder queryString = new StringBuilder("from DeviceParameter where  1=1 ");	
		queryString.append(" and  sensor =?  and state = 3  order by createTime desc ");
	    
		List<DeviceParameter> list = this.getHibernateTemplate().find(queryString.toString(), (Object) sensor);
		if ( !list.isEmpty() ){
			ret = list.get(0);
		}
        return ret;
	}
	
	
}
