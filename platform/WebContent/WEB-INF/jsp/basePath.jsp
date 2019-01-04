<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	
	String theme = (String)application.getAttribute("theme");
	if (theme == null || theme.trim().length() <= 0) { 
	   theme = "green";
	}
	Integer projectType = (Integer)application.getAttribute("projectType");
	if (projectType == null ) { 
	   projectType = 0;
	}
	
	String projectName = (String)application.getAttribute("projectName");
	if (projectName == null || projectName.trim().length() <= 0) { 
	   projectName = "输电线路运行状态在线监测系统";
	}
	
	request.setAttribute("projectType", projectType);
	request.setAttribute("projectName", projectName);
	request.setAttribute("userTheme", theme);
	request.setAttribute("basePath", basePath);	
	request.setAttribute("themeImagesPath", basePath + "/resource/theme/" + theme + "/images/");
%>