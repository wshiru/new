<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link rel="stylesheet" href="${basePath }/resource/common/css/global.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;监测装置管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">详细信息</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="list" namespace="/archive/sensor" method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						监测装置信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						监测装置编码：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.sensorCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						被监测装置编码：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.bySensorCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						所在杆塔：
					</td>
					<td colspan="3" class="table_td_left">
						（<s:property value="#request.sensor.tower.line.lineName" />）<s:property value="#request.sensor.tower.towerCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						所属监测类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.sensorType.sensorTypeName" />
					</td>
				</tr>
				
				<!--  <tr>
					<td class="td_gray150">
						采集监测代理：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.cmaInfo.cmaCode" />
					</td>
				</tr>
				 -->
				 
				<tr>
					<td class="td_gray150">
						状&nbsp;&nbsp;&nbsp;&nbsp;态：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{#request.sensor.state eq 1}">启用</s:if>
						<s:elseif test="%{#request.sensor.state eq 0}">
							<span class="cRed">停用</span>
						</s:elseif>
						<s:else>
							<span class="cRed">未知</span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						生产厂商：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.manuFacturer" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						出厂日期：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="#request.sensor.productionDate" format="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						出厂编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.identificationNumber" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						安装位置：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.installSite" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.sensorDesc" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="返 回" action="list" cssClass="btn4" />
					</td>
				</tr>
			</table>
		</s:form>
		<!-- end 信息区域 -->

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>