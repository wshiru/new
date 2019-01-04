/*
 * Project platform
 *
 * Class LoginServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 上午11:29:32
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.login.model.LoginErrorCode;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.model.TreeInfo;
import com.yixin.framework.login.service.LoginService;
import com.yixin.framework.system.dao.AuthResourceDao;
import com.yixin.framework.system.dao.UserDao;
import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.Role;
import com.yixin.framework.system.model.User;
import com.yixin.framework.system.service.UserService;



/**
 * 用户登录业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class LoginServiceImpl implements LoginService {

	/** 用户DAO接口 */
	private UserDao userDao;

	/** 权限资源DAO接口 */
	private AuthResourceDao authResourceDao;

	/** 用户业务接口 */
	private UserService userService;
	
	/**  线路信息接口 **/
	private LineService lineService;
	
	/** 杆塔信息接口**/
	private TowerService towerService;
	
	/** 监测类型接口 **/
	private SensorTypeService sensorTypeService;
    
	private CommService  commService;
	
	
	public void setCommService(CommService commService) {
		this.commService = commService;
	}


	/**
	 * 设置杆塔信息接口
	 * @param towerService
	 */
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	public void setSensorTypeService(SensorTypeService sensorTypeService){
		this.sensorTypeService = sensorTypeService;
	}
	
	/**
	 * 设置线路接口
	 * @param lineService
	 */
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	/**
	 * 设置 用户DAO接口
	 * 
	 * @param userDao
	 *            用户DAO接口
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 设置 权限资源DAO接口
	 * 
	 * @param authResourceDao
	 *            权限资源DAO接口
	 */
	public void setAuthResourceDao(AuthResourceDao authResourceDao) {
		this.authResourceDao = authResourceDao;
	}

	/**
	 * 设置 用户业务接口
	 * 
	 * @param userService
	 *            用户业务接口
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	private  boolean  getDeviceOnline(List<OnlineDeviceStatus>  OnlineDevices,String sensorCode)
	{
		if ( OnlineDevices.size() == 0 )
		{
			return  false;
		}
		
		boolean  ret  = false;
		
		Iterator<OnlineDeviceStatus> it  = OnlineDevices.iterator();
		
		String code = null;
		while ( it.hasNext() )
		{
			OnlineDeviceStatus   st = it.next();
			
			if ( st.getSensor() != null )
			{
				code  =  st.getSensor().getSensorCode();
				if (  code.equals(sensorCode) &&  code != null  )
				{
				   ret = true;
				   break;
				}
			}
		}
		
		/*for ( OnlineDeviceStatus  device :  OnlineDevices )
		{
			if ( device.getSensor().getSensorCode() != null )
			{
				if ( device.getSensor().getSensorCode().equals(sensorCode) )
				{
					ret =  true;
					break;
				}
			}
		}
		*/
		
		return ret;
	}
	
	/**
	 * 得到系统树目录数据
	 * @return
	 */
	public  List<TreeInfo>  getTreeInfos(Integer treeType){
		
		List<OnlineDeviceStatus>  OnlineDevices   = new ArrayList<OnlineDeviceStatus>();
		
		try
	    {
		     OnlineDevices = this.commService.getOnlineDeviceStatus();
	    }
		catch(Exception e )
		{	
			
		}
		
     	List<TreeInfo> treeInfo = new ArrayList<TreeInfo>();
     	if(treeType == 0){     		
	     	
			TreeInfo firstNode = new TreeInfo();// 根结点			
			firstNode.setId("top");
			firstNode.setPid("-1");
			firstNode.setName("所有线路");
			firstNode.setUrl("");
			firstNode.setNodeType(0);
			treeInfo.add(firstNode);	
			
			//ADD: 获取在线列表
				
			//获取线路信息
			List<Line> line = this.lineService.getAllLines();
			Iterator<Line> iterator = line.iterator();
			
			
			/* 添加线路 */
			while (iterator.hasNext()) {
				Line l = iterator.next();
				TreeInfo lineTree = new TreeInfo();
				lineTree.setId("Line" + l.getLineId().toString());
				lineTree.setPid(firstNode.getId());// 上级id是 根结点ID “所有线路”
				
				lineTree.setName(l.getLineName());// 线路名称
				lineTree.setIcon("line1.gif"); // 线路图片
				lineTree.setNodeType(1); // 节点类型
				lineTree.setOnline(0);
				lineTree.setTarget("");
				lineTree.setTitle(l.getLineName());
				lineTree.setUrl("showGroupPage.jspx?lineId=" + l.getLineId());
				treeInfo.add(lineTree);
	            
				List<Tower> tower = this.lineService.getAllTowers(l);
				Iterator<Tower> t0 = tower.iterator();
	
				//  杆塔 
				while (t0.hasNext()) {
					Tower t = t0.next();
					
					//TreeInfo towerTreeInfo = new TreeInfo();
					//towerTreeInfo.setId("tower" + t.getTowerId().toString());
					//towerTreeInfo.setPid(lineTree.getId());      // 上级ID为线路线点ID
					//towerTreeInfo.setName(t.getTowerCode());
					//lineTree.setUrl("showGroupPage.jspx?towerid=" + t.getTowerId());
					//towerTreeInfo.setIcon("tower-online.gif");
					//treeInfo.add(towerTreeInfo);
					
					
					/* 增加监测装置 */
					List<Sensor> sensor = this.towerService.getAllSensors(t);
					
					Iterator<Sensor> t1 = sensor.iterator();
					while (t1.hasNext()) {
						Sensor s = t1.next();
						TreeInfo sensorTree = new TreeInfo();
				        //String  cmaCode =  s.getCmaInfo().getCmaCode();
						sensorTree.setId("sensor" + s.getSensorId().toString());
						sensorTree.setPid(lineTree.getId()); // 上级ID为杆塔ID
 
						String sensorCode = s.getSensorCode() + "0000000000000000000000000";
						sensorTree.setName(String.format("%s(%s)-%s",
									t.getTowerCode(),
									s.getSensorType().getSensorTypeName(),
									String.valueOf(sensorCode.toCharArray(), 10, 7)
									));
						
						// ADD: 设置监测装置是否在线  用不同图标表示
						
						boolean  DeviceOnLine = this.getDeviceOnline(OnlineDevices,s.getSensorCode());
						if( DeviceOnLine)
						{
							sensorTree.setIcon("device-online.png");
						}
						else{
							sensorTree.setIcon("device-offline.png");
						}					
						//sensorTree.setIcon("device.gif");
						sensorTree.setNodeType(3);
						sensorTree.setTarget("");
					
						sensorTree.setTitle("");
	
						sensorTree.setUrl("checkSensorMenu.jspx?id=" + s.getSensorId().toString());
				
						treeInfo.add(sensorTree);
					}	
				 }
			  }
     	}
     	else if(treeType ==1){
     		TreeInfo firstNode = new TreeInfo();// 根结点
			
			firstNode.setId("top");
			firstNode.setPid("-1");
			firstNode.setName("所有分类");
			firstNode.setNodeType(0);
			treeInfo.add(firstNode);	
			
			//获取监测装置分类信息
			
			List<SensorType> sensorTypes = this.sensorTypeService.getAllSensorTypes();
			Iterator<SensorType> iterator = sensorTypes.iterator();
			
			
			/* 添加监测装置分类 */
			while (iterator.hasNext()) {
				SensorType t = iterator.next();
				TreeInfo lineTree = new TreeInfo();
				lineTree.setId("SensorType" + t.getSensorTypeId().toString());
				lineTree.setPid(firstNode.getId());// 上级id是 根结点ID “所有分类”
				
				lineTree.setName(t.getSensorTypeName());// 分类名称
				lineTree.setIcon("device.gif"); 			//线路图片
				lineTree.setNodeType(1); 				//节点类型
				lineTree.setOnline(0);
				lineTree.setTarget("");
				lineTree.setTitle(t.getSensorTypeName());				
				lineTree.setUrl("showGroupPage.jspx?sensorTypeId=" + t.getSensorTypeId());
				treeInfo.add(lineTree);
	            
				List<Sensor> sensor = this.sensorTypeService.getAllSensors(t);
				Iterator<Sensor> t1 = sensor.iterator();							
	  
				while (t1.hasNext()) {
					Sensor s = t1.next();
					TreeInfo sensorTree = new TreeInfo();
			        //String  cmaCode =  s.getCmaInfo().getCmaCode();
					sensorTree.setId("sensor" + s.getSensorId().toString());
					sensorTree.setPid(lineTree.getId()); // 上级ID为杆塔ID
					//sensorTree.setName(String.format("%s[%s]",cmaCode,s.getSensorCode()));
					String sensorCode = s.getSensorCode() + "0000000000000000000000000";
					sensorTree.setName(String.format("%s(%s)-%s", 
							s.getTower().getLine().getLineName() ,
							s.getTower().getTowerCode(),
							String.valueOf(sensorCode.toCharArray(), 10, 7)
							));
					// ADD: 设置监测装置是否在线  用不同图标表示					
					boolean  DeviceOnLine  = this.getDeviceOnline(OnlineDevices,s.getSensorCode());
					if(DeviceOnLine)
					{
						sensorTree.setIcon("device-online.png");
					}
					else{
						sensorTree.setIcon("device-offline.png");
					}
				
					//sensorTree.setIcon("device.gif");
					sensorTree.setNodeType(3);
					sensorTree.setTarget("");
				
					sensorTree.setTitle("");

					sensorTree.setUrl("checkSensorMenu.jspx?id=" + s.getSensorId().toString());
			
					treeInfo.add(sensorTree);
				}

			 
			  }
     	}
		return treeInfo;
		
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.login.service.LoginService#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public LoginInfo login(String userCode, String password) {
	    
		List<User> list = this.userDao.getAllByProperty("userCode", userCode);
		User user = null;

		/* 账户有效性检查 */
		if (list.size() == 0) { // “用户编号”不存在
			throw new BusinessException(LoginErrorCode.LOGIN_USERCODE_NOTEXISTS);
		} else {
			user = list.get(0);
			if (!user.getPassword().equals(password)) { // “密码”错误
				throw new BusinessException(LoginErrorCode.LOGIN_PASSWORD_INCORRECT);
			}
			if (user.getState() == User.STATE_DISABLE) { // 账户被停用
				throw new BusinessException(LoginErrorCode.LOGIN_STATE_DISABLE);
			}
			if (null != user.getExpiredTime() && new Date().after(user.getExpiredTime())) { // 账户已过期
				throw new BusinessException(LoginErrorCode.LOGIN_ACCOUNTHASEXPIRED);
			}
		}
		
		/* 需要限制的URLS（所有内置的URLS) */
		Set<String> limitUrls = new HashSet<String>();
		List<AuthResource> allAuths = this.authResourceDao.getAllCanView();
		
		
		for (AuthResource ar : allAuths) {
			limitUrls.add(ar.getUrl());//系统内所有的URLS
		}
		
		/* 当前登录用户所允许操作的所有URL地址 */
		Set<String> allowedUrls = new HashSet<String>();
		
		/* 当前登录用户具有的所有权限操作菜单（二级） */
		List<AuthResource> menuList = new LinkedList<AuthResource>();
		
		/* 系统资源*/
		List<AuthResource> sysAuthResuorces = new LinkedList<AuthResource>();
		
		/*导航资源*/
		List<AuthResource> dataTypeAuthResuorces = new LinkedList<AuthResource>();
		

		/* 当前登录用户所拥有的所有角色 */
		List<Role> roles = this.userService.getAllRoles(user);

		/* 用户所有可操作的资源ID列表 */
		List<String> authResourceIdList = new ArrayList<String>();

		/* 用户所有可操作的资源ID */
		//StringBuffer authResourceIdsSb = new StringBuffer();
		
		for (Role r : roles) {

			// 找出角色下所有的资源权限
			List<AuthResource> tempMenuList = r.getAuthResources();
			
			
			for (AuthResource ar : tempMenuList) {
				int resourceType = ar.getResourceType();
				int operationType = ar.getOperationType();
			
				//所有允许的URLS
				if (ar.getUrl() != null && ar.getUrl().trim().length() > 0) {
					allowedUrls.add(ar.getUrl());
				}

				//系统资源
				if (!menuList.contains(ar) && AuthResource.RESOURCETYPE_SYS == resourceType && operationType ==1 ) {
					sysAuthResuorces.add(ar);
				}
				
				//导航资源一级
				if (!menuList.contains(ar) && AuthResource.RESOURCETYPE_SENSOR_ONE_MENU == resourceType && operationType ==1 ) {
					dataTypeAuthResuorces.add(ar);
				}
				
				//导航资源二级
				if (!menuList.contains(ar) && AuthResource.RESOURCETYPE_SENSOR_TWO_MENU == resourceType && operationType ==1 ) {
					menuList.add(ar);
				}
				
			}
		}
	
		// 设置登录信息
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setAllowedUrls(allowedUrls);
		loginInfo.setAuthResourceIdList(authResourceIdList);
		//loginInfo.setAuthResourceIds(authResourceIdsSb.toString());
		loginInfo.setLimitUrls(limitUrls);
		loginInfo.setMenuList(sortMenu(menuList));
		loginInfo.setRoles(roles);
		
		loginInfo.setDataTypeAuthResuorces(sortMenu(dataTypeAuthResuorces));
		loginInfo.setSysAuthResuorces(sortMenu(sysAuthResuorces));
		loginInfo.setUser(user);
		
		
		//设置导航左边菜单资源
		List<AuthResource> operatorAuthResources = this.getOperatorAuthResource(sysAuthResuorces);
		if ( operatorAuthResources.size() > 0 ){
			loginInfo.setLeftMenuAuthResourceList(operatorAuthResources);
		}
		
	
		
		return loginInfo;
	}

	/**
	 * 对菜单进行升序排序
	 * 
	 * @param menuList
	 *            要进行排序的菜单列表
	 * @return 排完序后的菜单列表
	 */
	private List<AuthResource> sortMenu(List<AuthResource> menuList) {
		Collections.sort(menuList, new Comparator<AuthResource>() {
			public int compare(AuthResource o1, AuthResource o2) {
				AuthResource ar1 = (AuthResource) o1;
				AuthResource ar2 = (AuthResource) o2;
				if (ar1.getSortingNumber() > ar2.getSortingNumber()) {
					return 1;
				} else if (ar1.getSortingNumber() < ar2.getSortingNumber()) {
					return -1;
				}
				return 0;
			}
		});
		return menuList;
	}
	
	
	/**
	 * 获取一级菜单资源
	 * @param sysMenuList
	 * @return
	 */
	private  List<AuthResource> getOperatorAuthResource(List<AuthResource> sysMenuList) {
		List<AuthResource> operatorAuthResource = sysMenuList;
		Iterator<AuthResource> iterator1 = operatorAuthResource.iterator();
		List<AuthResource> listAuth = new ArrayList<AuthResource>();
		while (iterator1.hasNext()) {
			AuthResource r = iterator1.next();
			AuthResource parent = r.getParent();
			if (  parent == null ) {
				listAuth.add(r);
			}
		}
		return listAuth;
	}
	
}
