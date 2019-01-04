/*
 * Project platform
 *
 * Classname LoginAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-2 下午02:19:13
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorFunction;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.service.SensorFunctionService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.common.constant.BusinessConstants;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.monitor.service.OnlineDeviceStatusService;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.A1000.weather.model.LocalWeatherSampling;
import com.yixin.A1000.weather.service.LocalWeatherSamplingService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.login.model.LoginErrorCode;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.model.NavMenuInfo;
import com.yixin.framework.login.model.TreeInfo;
import com.yixin.framework.login.service.LoginService;
import com.yixin.framework.system.model.AuthResource;
import com.yixin.framework.system.model.User;
import com.yixin.framework.system.service.UserService;
import com.yixin.framework.util.SystemConfig;
import com.yixin.framework.util.SystemInfo;
import com.yixin.framework.util.WeatherReport;

/**
 * TODO(类的描述信息)
 * 
 * @version v1.0.0
 * @author 
 */
public class LoginAction extends ActionSupport implements ServletRequestAware {

	private static String theme = null;

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4290434775726895667L;

	/** 左侧菜单宽度 */
	private final static int NAV_MENU_WIDTH = 235; // by

	/** Session域中保存登录信息的属性名 */
	public static final String SESSION_LOGIN_INFO = "loginInfo";

	/** request域对象 */
	private HttpServletRequest request;

	/** 用户登录业务接口 */
	private LoginService loginService;

	/** 用户业务接口 */
	private UserService userService;

	/** 监测类型功能接口 **/
	private SensorFunctionService sensorFunctionService;

	/** 本地天气服务接口 **/
	private LocalWeatherSamplingService localWeatherSamplingService;
	
	private OnlineDeviceStatusService onlineDeviceStatusService;
	
	private static Integer onlineCount;
	private static Integer sensorCount;
	private static Date lastRefreshTime;

	/** 告警服务 */
	private WarningService warningService;

	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}
	
	public void setOnlineDeviceStatusService(OnlineDeviceStatusService onlineDeviceStatusService) {
		this.onlineDeviceStatusService = onlineDeviceStatusService;
	}	

	/** 用户编号 */
	private String userCode;

	/** 密码 */
	private String password;

	/** 监测装置接口 **/
	private SensorService sensorService;

	/**
	 * 获取 用户编号
	 * 
	 * @return 用户编号
	 */
	public String getUserCode() {
		return this.userCode;
	}

	/**
	 * 设置 用户编号
	 * 
	 * @param userCode
	 *            用户编号
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 获取 密码
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置 用户登录业务接口
	 * 
	 * @param loginService
	 *            用户登录业务接口
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
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

	/**
	 * 设置监测类型功能接口
	 * 
	 * @param sensorFunctionService
	 */
	public void setSensorFunctionService(
			SensorFunctionService sensorFunctionService) {
		this.sensorFunctionService = sensorFunctionService;
	}

	/**
	 * 设置本地天气服务口
	 * 
	 * @param localWeatherSamplingService
	 */
	public void setLocalWeatherSamplingService(
			LocalWeatherSamplingService localWeatherSamplingService) {
		this.localWeatherSamplingService = localWeatherSamplingService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/* 天气预报HTML脚本数据 */
	private String getWeatherInfoHtml() {
		//request.getServletContext().setAttribute(arg0, arg1)

		String path = this.request.getContextPath();
		String basePath = this.request.getScheme() + "://"
				+ this.request.getServerName() + ":"
				+ this.request.getServerPort() + path + "/";

		String weatherInfoHtml = this.localWeatherSamplingService
				.getLocalTodayWeatherHtml(basePath);
		// 如果数据库不存在访问 web Service 服务
		if (weatherInfoHtml == null) {
			WeatherReport weatherInfo = new WeatherReport();
			boolean ret = weatherInfo.getTodayeWeather();
			if (ret) {
				weatherInfoHtml = this.localWeatherSamplingService
						.getWebTodayWeatherHtml(basePath, weatherInfo);
				LocalWeatherSampling localWeather = this.localWeatherSamplingService
						.getTodayWeather(weatherInfo);
				// 保存天气数据至数据库中
				this.localWeatherSamplingService.addLocalWeather(localWeather);
			}
		}
		return weatherInfoHtml;
	}

	/**
	 * 生成中文星期数
	 * 
	 * @return 生成中文星期数
	 */
	private String getWeekString() {
		final Date date = new Date();
		final Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(5, -7);

		String wk = "";
		int weekday = 0;
		for (int i = 7; i > 0; i--) {
			c.add(5, 1);
			weekday = c.get(Calendar.DAY_OF_WEEK);
			switch (weekday) {
			case 2:
				wk = "星期一";
				break;
			case 3:
				wk = "星期二";
				break;
			case 4:
				wk = "星期三";
				break;
			case 5:
				wk = "星期四";
				break;
			case 6:
				wk = "星期五";
				break;
			case 7:
				wk = "星期六";
				break;
			case 1:
				wk = "星期日";
			}
		}
		return wk;
	}

	// basePath
	private String getBasePath() {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		return basePath;
	}

	private static String getTheme() {
		if (theme == null) {
			theme = new SystemInfo().getSystemTheme();
		}
		return theme;
	}

	private boolean checkAuthResourceContain(List<AuthResource> ars,
			AuthResource ar) {
		boolean ret = false;
		Iterator<AuthResource> it = ars.iterator();
		while (it.hasNext()) {
			AuthResource ar0 = it.next();
			if (ar0.getAuthResourceId().equals(ar.getAuthResourceId())) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	/**
	 * 跳转至登录页面
	 * 
	 * @return
	 */
	public String login() {
		return SUCCESS;
	}
	
	
	
	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String logon() {
		try {			
			PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			password = passwordEncoder.encodePassword(password, null);
			LoginInfo loginInfo = this.loginService.login(userCode, password);
			// String weatherInfoHtml = getWeatherInfoHtml();
			String weatherInfoHtml = ""; // 不取天气了.. 总是有点问题
			loginInfo.setWeatherInfoHtml(weatherInfoHtml);
			request.getSession(true)
					.setAttribute(SESSION_LOGIN_INFO, loginInfo);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginTimeInfo = " | 登录时间:" + sdf.format(new Date()) + " "
					+ getWeekString();
			request.getSession(true).setAttribute("loginTimeInfo",
					loginTimeInfo);
			User user = loginInfo.getUser();
			user.setLastLoginIp(request.getRemoteAddr());
			user.setLastLoginTime(new Date());
			this.userService.editUser(user);
			return SUCCESS;

		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case LoginErrorCode.LOGIN_USERCODE_NOTEXISTS:
				request.setAttribute("errorMessage", "用户不存在！");
				break;
			case LoginErrorCode.LOGIN_PASSWORD_INCORRECT:
				request.setAttribute("errorMessage", "密码错误！");
				break;
			case LoginErrorCode.LOGIN_STATE_DISABLE:
				request.setAttribute("errorMessage", "账户被停用，请与管理员联系！");
				break;
			case LoginErrorCode.LOGIN_ACCOUNTHASEXPIRED:
				request.setAttribute("errorMessage", "账户已过期，请与管理员联系！");
				break;
			default:
				request.setAttribute("errorMessage", "系统发生内部错误，请与管理员联系！");
			}
			return INPUT;
		}
	}

	/**
	 * 请求退出
	 * 
	 * @return
	 */
	public String logout() {
		request.getSession(true).invalidate();
		return SUCCESS;
	}

	/**
	 * 跳转至 mainFrame页面
	 * 
	 * @return
	 */
	public String mainFrame() {
		String pageNo = request.getParameter("pn");
		String pageSize = request.getParameter("ps");

		if (null == pageNo || pageNo.isEmpty()) {
			pageNo = "1";
		}

		if (null == pageSize || pageSize.isEmpty()) {
			pageSize = String.valueOf(SystemConfig.PAGE_SIZE);
		}

		int warningDays = BusinessConstants.LATEST_WARNING_SPAN;
		Date endTime = new Date();
		Date beginTime = new Date(endTime.getTime() - warningDays * 24 * 60
				* 60 * 1000);
		Page<Warning> page = warningService.getPageWarningsBySensor(null, null,
				null, beginTime, endTime, 0, Long.valueOf(pageNo),
				Long.valueOf(pageSize));

		request.setAttribute("pageData", page);
		return SUCCESS;
	}

	/**
	 * 状态处理
	 * 
	 * @return
	 */
	public String updateWarningState() {
		String id = request.getParameter("WarningId");
		if (id != null) {
			Warning warning = this.warningService.getWarning(id);
			if (warning != null) {
				warning.setOperationState(1);
				this.warningService.editWarning(warning);
				request.setAttribute("errorMessage", "处理成功!");
			}
		}
		return this.mainFrame();
	}

	/**
	 * 跳转至 mainMenuFrame页面
	 * 
	 * @return
	 */
	public String mainMenuFrame() {
		return SUCCESS;
	}

	/**
	 * 跳转至 menuFrame页面
	 * 
	 * @return
	 */
	public String menuFrame() {
		LoginInfo loginInfo = (LoginInfo) request.getSession(true)
				.getAttribute(SESSION_LOGIN_INFO);
		
		//取得类型
		Integer treeType = 1 ;
		
		String sTreeType = request.getParameter("treeType");
		if (!(sTreeType == null || "".equals(sTreeType))) {
			treeType = Integer.parseInt(sTreeType);
		}
		
		List<TreeInfo>  treeInfos = loginService.getTreeInfos(treeType); 		
		List<TreeInfo> treeInfoList = treeInfos;
		request.setAttribute("treeInfos", treeInfoList);

		return SUCCESS;
	}

	private synchronized String getOnlineDevice(){
		//显示所有设备
		Date now = new Date();
		//60秒查询一次
		if(lastRefreshTime == null || (now.getTime() - lastRefreshTime.getTime()) > 60*1000){			
			List<Sensor> allsSensors = this.sensorService.getAllSensors();
			sensorCount = allsSensors.size();
			try{
				List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService.getOnlineDeviceStatus();
				//onlineCount = onlineDevices.size();
				onlineCount = 0;
				
				Iterator<OnlineDeviceStatus> it = onlineDevices.iterator();
				String senserCodes="";
				while(it.hasNext()){
					OnlineDeviceStatus ods = it.next();
					if(ods.getSensor()!=null){
						senserCodes += ods.getSensor().getSensorCode() + ";";
					}					
				}
				Iterator<Sensor> iterator = allsSensors.iterator();
				while(iterator.hasNext()){
					Sensor sensor = iterator.next();
					if(senserCodes.contains(sensor.getSensorCode())){
						onlineCount ++;
					}					
				}
			}catch(Exception e){
				onlineCount = 0;
			}			
			lastRefreshTime = new Date();
		}
		double d = 0;
		if(sensorCount!=0){
			d = (float)onlineCount/sensorCount * 100;
		}		
		return String.format(" 监测装置总数： %d个， 在线：%d个，在线率：%.2f%%，", sensorCount,onlineCount, d);
		
		/*
		List<Sensor> allsSensors = this.sensorService.getAllSensors(); 
		try {
			List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService.getOnlineDeviceStatus();
			List<OnlineDeviceStatus> allOnlineDevices = new ArrayList<OnlineDeviceStatus>();
			if (null != allsSensors) {				
				List<Sensor> resultSensors = new ArrayList<Sensor>();
				for (Sensor sensor : allsSensors) {
					OnlineDeviceStatus  onlineDeviceStatus = new OnlineDeviceStatus();
					onlineDeviceStatus.setSensor(sensor);
					for(OnlineDeviceStatus ods:onlineDevices){
						if(ods.getSensor().getSensorCode().equals(sensor.getSensorCode())){
							onlineDeviceStatus.setLastCommTime(ods.getLastCommTime());
							onlineDeviceStatus.setLogonTime(ods.getLogonTime());
							onlineDeviceStatus.setIp(ods.getIp());
							onlineDeviceStatus.setPort(ods.getPort());
						}
					}					
					allOnlineDevices.add(onlineDeviceStatus);
				}			
			}
			request.setAttribute("allOnlineDevices", allOnlineDevices);
		} catch (Exception e) {					
		}
		*/
	}
	
	/**
	 * 跳转至 messageFrame页面
	 * 
	 * @return
	 */
	public String messageFrame() {
		String pageNo = "1";
		String pageSize = "10";
		int warningDays = BusinessConstants.LATEST_WARNING_SPAN;
		Date endTime = new Date();
		Date beginTime = new Date(endTime.getTime() - warningDays * 24 * 60
				* 60 * 1000);
		Page<Warning> page = warningService.getPageWarningsBySensor(null, null,
				null, beginTime, endTime, 0, Long.valueOf(pageNo),
				Long.valueOf(pageSize));
		request.setAttribute("pageData", page);		
		String onlineMessage = getOnlineDevice();
		request.setAttribute("onlineMessage", onlineMessage);	
		return SUCCESS;
	}

	/**
	 * 跳转至 switchFrame页面
	 * 
	 * @return
	 */
	public String switchFrame() {
		return SUCCESS;
	}

	/**
	 * 跳转至 titleFrame页面
	 * 
	 * @return
	 */
	public String titleFrame() {
		return SUCCESS;
	}

	/**
	 * 跳转至 barFrame页面
	 * 
	 * @return
	 */
	public String barFrame() {
		return SUCCESS;
	}
	

	/**
	 * 单击选择主页面 的系统菜单时，返回拥有权限的菜单
	 * 
	 * @return
	 */
	public String checkSysMenu() {

		String parentId = "";
		String sensorId = request.getParameter("id");
		if (!(sensorId == null || "".equals(sensorId))) {
			request.setAttribute("sensorId", sensorId);
			request.setAttribute("sensorMenu", 0); // 有传感器菜单
		}
		String checkMenu = sensorId = request.getParameter("checkMenu");
		if (sensorId == null || "".equals(sensorId)) {
			checkMenu= "0";
		}

		LoginInfo loginInfo = (LoginInfo) request.getSession(true)
				.getAttribute(SESSION_LOGIN_INFO);
		List<AuthResource> operatorAuthResource = loginInfo
				.getSysAuthResuorces();

		List<NavMenuInfo> list = new ArrayList<NavMenuInfo>();
		NavMenuInfo navMenuInfo = null;
		List<AuthResource> authResources = null;

		Iterator<AuthResource> iterator = operatorAuthResource.iterator();
		while (iterator.hasNext()) {
			AuthResource ar = iterator.next();
			AuthResource parent = ar.getParent();
			if (parent != null) {
				if (!parent.getAuthResourceId().equals(parentId)) {
					navMenuInfo = new NavMenuInfo();
					navMenuInfo.setMenuTypeName(parent.getName());
					authResources = new ArrayList<AuthResource>();
					navMenuInfo.setAuthResuorces(authResources);
					parentId = parent.getAuthResourceId();
					list.add(navMenuInfo);
				}
				authResources.add(ar);
			}
		}
		request.setAttribute("systemMenu", list);

		request.setAttribute("checkMenu", checkMenu); // 标识为选择了系统菜单或树数据
		if("0".equals(checkMenu)){
			request.setAttribute("defaultOpenSrc", "/mainFrame.jspx");
		}
		else{
			request.setAttribute("defaultOpenSrc", "");	
		}				
		return SUCCESS;
	}
	public String showGroupPage(){
		String id = request.getParameter("lineId");
		if(!(id ==null || "".equals(id))){
			request.setAttribute("defaultOpenSrc", "/lineGroup.jspx?id="+id);
			return SUCCESS;
		}
		id = request.getParameter("sensorTypeId");
		if(!(id ==null || "".equals(id))){
			request.setAttribute("defaultOpenSrc", "/sensorTypeGroup.jspx?id="+id);
			return SUCCESS;
		}
		request.setAttribute("defaultOpenSrc", "");
		return SUCCESS;
	}
	public String checkSensorMenu() {

		String sensorId = request.getParameter("id");
		
		Sensor sensor = this.sensorService.getSensor(sensorId);

		if (sensor == null) {
			request.setAttribute("errorMessage", "监测装置不存在,有可能已被删除,请与管理员联系!");
			return INPUT;
		}

		LoginInfo loginInfo = (LoginInfo) request.getSession(true)
				.getAttribute(SESSION_LOGIN_INFO);
		// 一类菜单（监测装置功能）
		List<AuthResource> dataTypeAuthResuorces = loginInfo
				.getDataTypeAuthResuorces();
		// 二级菜单
		List<AuthResource> twoMenuAuthResources = loginInfo.getMenuList();

		// 拥操作权限的资源列表
		List<AuthResource> sensorFunctionAuthResources = new ArrayList<AuthResource>();

		List<SensorFunction> sensorFunctionList = null;
		SensorType sensorType = sensor.getSensorType();
		if (sensorType != null) {
			sensorFunctionList = this.sensorFunctionService
					.getAllSensorFunctions(sensorType);
		}

		Iterator<SensorFunction> it = sensorFunctionList.iterator();
		// 过滤出拥有权限的监测类型资源
		while (it.hasNext()) {
			SensorFunction sensorFunction = it.next();
			AuthResource ar = sensorFunction.getAuthResource();
			if (checkAuthResourceContain(twoMenuAuthResources, ar)) {
				if (ar.getVisible() == 1) {
					sensorFunctionAuthResources.add(ar);
				}
			}
		}

		List<NavMenuInfo> list = new ArrayList<NavMenuInfo>();
		NavMenuInfo navMenuInfo = null;
		List<AuthResource> authResources = null;

		Iterator<AuthResource> iterator = dataTypeAuthResuorces.iterator();
		while (iterator.hasNext()) {
			AuthResource ar = iterator.next();

			navMenuInfo = new NavMenuInfo();
			navMenuInfo.setMenuTypeName(ar.getName());
			authResources = new ArrayList<AuthResource>();

			navMenuInfo.setAuthResuorces(authResources);
			Iterator<AuthResource> it1 = sensorFunctionAuthResources.iterator();
			while (it1.hasNext()) {
				AuthResource ar1 = it1.next();
				if (ar1.getParent().getAuthResourceId()
						.equals(ar.getAuthResourceId())) {
					authResources.add(ar1);
				}
			}
			if (authResources.size() > 0) {
				list.add(navMenuInfo);
			}
		}
		request.setAttribute("systemMenu", list);
		
		request.setAttribute("deviceInfo", String.format(
				"%s-%s %s<br/> 监测装置ID:%s", sensor.getTower().getLine()
						.getLineName(), sensor.getTower().getTowerCode(),
				sensor.getSensorType().getSensorTypeName(),
				sensor.getSensorCode()));

		request.setAttribute("sensorTypeName", sensor.getSensorType()
				.getSensorTypeName());

		request.setAttribute("sensorId", sensorId);
		request.setAttribute("sensorTypeId", sensor.getSensorType().getSensorTypeId());
		
		String checkMenu = request.getParameter("checkMenu");
		if(checkMenu != null){
			Integer ch = Integer.parseInt(checkMenu);
			request.setAttribute("checkMenu", 1); 
		}else{
			request.setAttribute("checkMenu", 0); // 标识为选择了系统菜单或树数据
		}
		
		
		request.setAttribute("sensorMenu", 1); // 是传感器菜单
		
		if("A1021".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/A1021/sampling/main.jspx?id="+sensor.getSensorId());
		}else if("A1001".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/weather/sampling/main.jspx?id="+sensor.getSensorId());
		}else if("A1003".equals( sensor.getSensorType().getSensorTypeCode())){
				request.setAttribute("defaultOpenSrc", "/towertilt/sampling/main.jspx?id="+sensor.getSensorId());
		}else if("A1005".equals( sensor.getSensorType().getSensorTypeCode())){
			//request.setAttribute("defaultOpenSrc", "");
			request.setAttribute("defaultOpenSrc", "/video/playback/main.jspx?id="+sensor.getSensorId());
		}else if("A1010".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/landslide/sampling/main.jspx?id="+sensor.getSensorId());
		}else if("A1011".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/contamination/sampling/main.jspx?id="+sensor.getSensorId());
		}else if("A1002".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/icethinckness/sampling/main.jspx?id="+sensor.getSensorId());			
		}else if("A1022".equals( sensor.getSensorType().getSensorTypeCode())){
			request.setAttribute("defaultOpenSrc", "/icethinckness/sampling/main.jspx?id="+sensor.getSensorId());			
		}
		
		return SUCCESS;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

}
