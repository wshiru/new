<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/highLight.js"></script>
		<meta http-equiv="refresh" content="60;" />
	</head>
	<body>		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
									<img src="${basePath }/resource/theme/${userTheme }/images/tab/tabs.gif" width="16" height="16" />
									最近<s:property value="warningDays"/>天告警
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
										<td width="15%" class="first_line">
											告警时间
										</td>						
										<td width="11%" class="first_line">
											告警类型
										</td>	
										<td width="16%" class="first_line">
											监测装置编码
										</td>	
										<td width="15%" class="first_line">
											所属杆塔
										</td>	
										<td width="10%" class="first_line">
											所属线路
										</td>
										<td class="first_line">
											内容
										</td>
									</tr>
									<s:iterator value="#request.pageData.records">
										<tr>
											<td class="second_line">
												<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td class="second_line">
												<s:property value="warningDict.name" />
											</td>
											<td class="second_line">
												<s:property value="sensor.sensorCode" />
											</td>
											<td class="second_line">
												<s:property value="sensor.tower.towerCode" />
											</td>
											<td class="second_line">
												<s:property value="sensor.tower.line.lineName" />
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
								<yixin:page url="/mainpage/list.jspx" page="${pageData}" id="warning_page">
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