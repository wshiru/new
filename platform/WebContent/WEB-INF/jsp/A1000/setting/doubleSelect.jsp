<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
			//线路、杆塔二级数据源
			dataSource = [
				["CategoryName","ParentId","Id"]
				
				<s:if test="%{#request.cmaInfos != null && #request.cmaInfos.size() > 0 }">
					,
				</s:if>
				
				<s:iterator value="%{#request.cmaInfos}" status="stat">
					['<s:property value="cmaCode" escape="false" />', '0' , '<s:property value="cmaInfoId" escape="false" />']
					<s:if test="!#stat.last">,</s:if>
				</s:iterator>
				
				<s:if test="%{#request.sensors != null && #request.sensors.size() > 0 }">
					,
				</s:if>
				
				<s:iterator value="%{#request.sensors}" status="stat">
					['<s:property value="sensorCode" escape="false" />', '<s:property value="cmaInfo.cmaInfoId" escape="false" />' , '<s:property value="sensorId" escape="false" />']
					<s:if test="!#stat.last">,</s:if>
				</s:iterator>
			];