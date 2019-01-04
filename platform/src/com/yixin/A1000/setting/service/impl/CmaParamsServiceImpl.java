/*
 * Project platform
 *
 * Class TowerService.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-21 下午07:33:27
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.setting.dao.CmaParamsDao;
import com.yixin.A1000.setting.model.CmaParams;
import com.yixin.A1000.setting.service.CmaParamsService;


/**
 * CMA参数信息业务逻辑接口实现类

 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public class  CmaParamsServiceImpl  implements CmaParamsService {

	private  CmaParamsDao cmaParamsDao;
	private  CmaInfoService cmaInfoService;
	
	
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	public CmaParamsDao getCmaParamsDao() {
		return cmaParamsDao;
	}
	
	public void setCmaParamsDao(CmaParamsDao cmaParamsDao) {
		this.cmaParamsDao = cmaParamsDao;
	}


	@Override
	public List<CmaParams> getAllCmaParams() {
		return this.cmaParamsDao.getAll();
	}


	@Override
	public void editCmaParams(List<CmaParams> cmaParamsList) {
		Iterator<CmaParams> it = cmaParamsList.iterator();
	    while (it.hasNext()){
	    	CmaParams cmaParams = it.next();    	
	    	if (  cmaParams.getCmaParamsId() != null) {
	    		this.editCmaParams(cmaParams);
	    	}
	    	else {
	    		this.addCmaParams(cmaParams);
	    	}
	    }
	    
	}

	@Override
	public void editCmaParams(CmaParams cmaParams) {
		this.cmaParamsDao.update(cmaParams);
	}

	
	@Override
	public void addCmaParams(CmaParams cmaParams) {
		this.cmaParamsDao.save(cmaParams);
	}

	
	@Override
	public CmaParams getConfirmCmaParams(String cmaCode) {
		CmaParams  cmaParams = null;
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		
		if ( cmaInfo != null ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cmaInfo", cmaInfo);
			map.put("state", 1);
			
			List<CmaParams> infos = this.cmaParamsDao.getAllByProperties(map);
			if ( infos != null ){
				if ( infos.size() >0 ){
					cmaParams =  infos.iterator().next();
				}
			}
		}
		return  cmaParams;
	}

	
	@Override
	public CmaParams getUnrecognizedCmaParams(String cmaCode) {
		CmaParams  cmaParams = null;
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		
		if ( cmaInfo != null ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("cmaInfo", cmaInfo);
			map.put("state", 0);
			
			List<CmaParams> infos = this.cmaParamsDao.getAllByProperties(map);
			if ( infos != null ){
				if ( infos.size() >0 ){
					cmaParams =  infos.iterator().next();
				}
			}
		}
		return  cmaParams;
	}
  
}
