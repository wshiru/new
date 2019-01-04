/*
 * Project platform
 *
 * Class AuthResourceDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 下午01:53:31
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.dao;

import java.util.List;

import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.system.model.AuthResource;

/**
 * 权限资源DAO接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface AuthResourceDao extends BaseDao<AuthResource, String> {
	
	public abstract List<AuthResource> getAllCanView();
	
}
