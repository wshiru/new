<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link href="${basePath }/resource/theme/${userTheme }/css/main.css" rel="stylesheet" type="text/css"  />
		<link href="${basePath }/resource/theme/${userTheme }/css/barFrame.css" rel="stylesheet" type="text/css" />
    	<link href="${basePath }/resource/theme/${userTheme }/css/scrollmessage.css" rel="stylesheet" type="text/css" /><%--	 
    	
    	<script type='text/javascript'>
		      function  warningClick(tis){
		            
		      	 var obj =  parent.menuFrame.systemMonitor;
		         var url = parent.menuFrame.systemUrl + "&warning=1&sensorCode=" + tis.id + "&deviceCode=" + tis.name;
		         
		         if ( obj != null ){
		             parent.menuFrame.changimg(obj);
		             window.open(url,"mainMenuFrame"); 
		             return true;
		         } 
		         else {
		             alert('对不起，您没有告警信息查询权限!');
		             return false;
		         }
		            
		      }
		</script>
		
	--%></head>

	<body>
		<div class="down">
			<div class="version"></div>
			<div class="ver_wz">
				版本&nbsp;V2.0.0
			</div>
			
			<%--
			<div id="ico"></div>
			<div id="scrolllayer">
				<div id="scrollmessage">
					 	<script type='text/javascript'>
			      	       getLastWarningData();
			               var noWarningData  = true;
		 	               function getLastWarningData() {		
		 	                    var obj = document.getElementById("scrollmessage");
		 	                    var sdate = new Date();		
				                Warnings.getLastWarningData(sdate,function(data) {
			 	                  obj.innerHTML = data;
			 	                  var ico = document.getElementById("ico");
					              if ( data == "<ul><li><a>最近无告警</a></li></ul>" ){
					                ico.className="fontc_ico1";
					               }
					              else {
					                ico.className="fontc_ico";
					                noWarningData = false;
					               }
					           
					            }            	             
				              );
			               } 
			               var timer= setInterval("getLastWarningData();",10000);
			              
		                </script>		
				</div>
			</div>
			
			--%><!-- <script type="text/javascript"  src="${basePath }/resource/theme/${userTheme }/js/scrollmessage.js" ></script>
		    -->
		    
			<div class="copyright">
				深圳市深联创展科技开发有限公司Copyright © 2008-2013
			</div>
			<div class="copyright"></div>
		</div>
	</body>
</html>