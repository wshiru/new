package com.yixin.framework.ui.apply.service;


/**
 * UI应用服务类   
 * @author PC-ZCL
 *
 */
public interface  ApplyService {

	
	 //树HTML 返回NULL值不加载，表示停用不显示在界面上
	 public  abstract  String   getTreeViewHTML();
	 

	 //告警状态HTML
	 public  abstract  String   getWarningViewHTML();
	 
	
     //天气预报HTML
	 public  abstract  String   getWeatherViewHTML();
	 
	 
	 //公司Main LOGOHTML
	 public  abstract  String   getMainLogoViewHTML();
	
	 
	 // 主菜单
	 public  abstract  String   getMenuViewHTML();
	 
	 
	 
}
