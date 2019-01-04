/*
 * Project platform
 *
 * Class AuthResourceDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 下午01:53:40
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.dao.impl.springhibernate;


import java.util.List;

import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;
import com.yixin.framework.system.dao.AuthResourceDao;
import com.yixin.framework.system.model.AuthResource;

/**
 * 权限资源DAO的Spring+Hibernate实现类
 * 
 * @version v1.0.0
 * @author 
 */
public  class AuthResourceDaoImpl extends BaseDaoImpl<AuthResource, String> implements AuthResourceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AuthResource> getAllCanView() {
		return this.getHibernateTemplate().find("from AuthResource where visible = 1 order by authResourceId");
	
	}	
}
