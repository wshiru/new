<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
			//线路、杆塔二级数据源
			dataSource = [
				["CategoryName","ParentId","Id"]
				
				<s:if test="%{#request.lines != null && #request.lines.size() > 0 }">
					,
				</s:if>
				
				<s:iterator value="%{#request.lines}" status="stat">
					['<s:property value="lineName" escape="false" />', '0' , '<s:property value="lineId" escape="false" />']
					<s:if test="!#stat.last">,</s:if>
				</s:iterator>
				
				<s:if test="%{#request.towers != null && #request.towers.size() > 0 }">
					,
				</s:if>
				
				<s:iterator value="%{#request.towers}" status="stat">
					['<s:property value="towerCode" escape="false" />', '<s:property value="line.lineId" escape="false" />' , '<s:property value="towerId" escape="false" />']
					<s:if test="!#stat.last">,</s:if>
				</s:iterator>
			];