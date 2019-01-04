<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp" %>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>

	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			系统监控&nbsp;&gt;&nbsp;
			<span class="tab_position_b">告警查询</span>
		</div>
		<!-- end 当前位置 -->
		
		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/warning" method="post" theme="simple">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_search.gif" width="16" height="16" />
										查询条件
									</div>
								</td>
								<td class="first_line_right"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="second_line_left">
									&nbsp;
								</td>
								<td class="second_line_center">
									<table width="100%" cellspacing="1" class="tab">
										<tr>
											<td class="table_td_right td_whith110">
												告警类型：
											</td>
											<td class="table_td_left">
												<s:select name="warningQueryModel.warningDict.warningDictId" listKey="warningDictId"
															 listValue="name" list="#request.warningDictList" headerKey="" headerValue="请选择" >
												</s:select>
											</td>
										    <td class="table_td_right td_whith110">
												告警时间：
											</td>
											<td class="table_td_left" >									 
												<input type="text" name="warningQueryModel.beginTime" value='<s:date name="warningQueryModel.beginTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
												至												
												<input type="text" name="warningQueryModel.endTime" value='<s:date name="warningQueryModel.endTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
											</td>
										</tr>
										<tr>
											<!-- 
											<td class="table_td_right td_whith110">
												监测代理编码：
											</td>
											<td class="table_td_left">
												<s:textfield name="warningQueryModel.cmaCode" maxlength="30" />
											</td>
											 -->
											<td class="table_td_right td_whith110">
												所属线路：
											</td>
											<td class="table_td_left">
												<s:select name="warningQueryModel.lineId" listKey="lineId"
															 listValue="lineName" list="#request.linesList" headerKey="" headerValue="请选择" >
												</s:select>
											</td>
											<td class="table_td_right td_whith110">
												监测装置编码：
											</td>
											<td class="table_td_left">
												<s:textfield name="warningQueryModel.sensorCode" maxlength="30" />
											</td>											
										</tr>
									</table>
								</td>
								<td class="second_line_right"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="third_line_left"></td>
								<td class="third_line_center">
									<s:submit value="查 询" cssClass="btn2" />
									&nbsp;&nbsp;
									<s:reset type="button" value="清 空" cssClass="btn2" onclick="clearForm(this.form);return false;" />
								</td>
								<td class="third_line_right">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
		<!-- end 查询区域 -->
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
									<img src="${basePath }/resource/theme/${userTheme }/images/tab/tabs.gif" width="16" height="16" />
									告警信息
								</div>
							</td>
							<td class="first_line_center">
							</td>
							<td class="first_line_right"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="second_line_left">
								&nbsp;
							</td>
							<td class="second_line_center">
								<table cellspacing="1" onmouseover="changeto()" onmouseout="changeback()" class="tab">
									<tr>
										<td width="5%" class="first_line">
											序号
										</td>	
										<td width="15%" class="first_line">
											告警时间
										</td>
										<td width="15%" class="first_line">
											监测装置
										</td>							
										<td width="15%" class="first_line">
											告警类型
										</td>
										<td class="first_line">
											内容
										</td>
									</tr>
									<s:iterator value="#request.pageData.records" status="rowstatus">
										<tr>
											<td class="second_line">
												<s:property value="#rowstatus.index + 1" />
											</td>
											<td class="second_line">
												<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td class="second_line">
												(<s:property value="sensor.tower.line.lineName" />-
												<s:property value="sensor.tower.towerCode" />)
												<s:property value="sensor.sensorCode" />
											</td>
											<td class="second_line">
												<s:property value="warningDict.name" />
											</td>
											<td class="second_line" style="text-align:left;">
												<s:property value="warningInfo" />
											</td>	
										</tr>
									</s:iterator>
								</table>
							</td>
							<td class="second_line_right"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="third_line_left"></td>
							<td class="third_line_center">
								<s:if test="warningQueryModel.endTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="warningQueryModel.endTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="warningQueryModel.beginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="warningQueryModel.beginTime" format="yyyy-MM-dd" />
									</s:set>	
								</s:if>
								<yixin:page url="/warning/list.jspx" page="${pageData}" id="warning_page">
									<yixin:hidden name="warningQueryModel.warningDict.warningDictId" value="${requestScope['warningQueryModel.warningDict.warningDictId']}" />
									<yixin:hidden name="warningQueryModel.cmaCode" value="${requestScope['warningQueryModel.cmaCode']}" />
									<yixin:hidden name="warningQueryModel.sensorCode" value="${requestScope['warningQueryModel.sensorCode']}" />
									<yixin:hidden name="warningQueryModel.beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="warningQueryModel.endTime" value="${pageScope.qEndTime}" />
								</yixin:page>
							</td>
							<td class="third_line_right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- end 信息区域 -->

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>