<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

		<s:set var="currSize" value="#request.pageData.records.size()" />		    
		<script type="text/javascript">
			function openChart(url){
				if('${isSensorNullOrEmpty}' == true)
					alert("无法生成曲线图，当前包含多个监测装置的数据");
				else{
					var maxRecords = parseInt('${maxChartRecords}');
					var currSize = parseInt('${currSize}');
					if(maxRecords < currSize)
						alert("数据过大，无法生成曲线图，请缩小时间范围进行查询");
					else
						openNewWin(url);
				}						
			}
		</script> 
 