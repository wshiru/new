<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title>在线监测装置列表</title>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
	</head>

	<body>

		<!-- 当前位置 开始 -->
		<div id="tab_position">
			系统监控&nbsp;&gt;&nbsp;
			<span class="tab_position_b">在线率统计</span>
		</div>
		<!-- 当前位置 结束 -->


		<!-- start 查询区域 -->
		<s:form id="queryForm" action="onlineRateStatistics"
			namespace="/monitor/onlineDeviceStatus" method="post" theme="simple">

			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img
											src="${basePath }/resource/theme/${userTheme }/images/tab/ico_search.gif"
											width="16" height="16" />
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
												时间：
											</td>
											<td class="table_td_left">
												<input type="text" name="beginTime"
													value='<s:date name="beginTime" format="yyyy-MM-dd"/>'
													onclick="WdatePicker()" maxlength="15"
													onfocus="WdatePicker()" />
												至
												<input type="text" name="endTime"
													value='<s:date name="endTime" format="yyyy-MM-dd"/>'
													onclick="WdatePicker()" maxlength="15"
													onfocus="WdatePicker()" />
											</td>
											<td class="table_td_right td_whith110">
												监测装置类型：
											</td>
											<td class="table_td_left">
												<s:select name="sensorTypeId"
													list="%{#request.sensorTypes}" headerKey=""
													headerValue="--请选择--" listKey="sensorTypeId"
													listValue="sensorTypeName" value="%{#request.sensorTypeId}"></s:select>
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
									<s:reset type="button" value="清 空" cssClass="btn2"
										onclick="clearForm(this.form);return false;" />
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

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
									<img
										src="${basePath }/resource/theme/${userTheme }/images/tab/tabs.gif"
										width="16" height="16" />
									在线率统计
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
								<table cellspacing="1" class="tab" onmouseover="changeto()"
									onmouseout="changeback()">
									<tr>
										<td width="5%" class="first_line">
											序号
										</td>
										<td width="15%" class="first_line">
											安装位置
										</td>
										<td width="20%" class="first_line">
											监测装置编码
										</td>
										<td width="5%" class="first_line">
											在线天数
										</td>
										<td width="5%" class="first_line">
											统计天数
										</td>
										<td width="40%" class="first_line">
											在线率
										</td>
									</tr>
									<s:iterator value="#request.page.records" status="rowstatus"
										var="p">
										<tr>
											<td class="second_line">
												<s:property value="#rowstatus.index + 1" />
											</td>
											<td class="second_line">
												<s:property value="lineName" />
												-
												<s:property value="towerCode" />
											</td>
											<td class="second_line">
												<s:property value="sensorCode" />
											</td>
											<td class="second_line">
												<s:property value="count" />
											</td>
											<td class="second_line">
												<s:property value="days" />
											</td>
											<td class="second_line">
												<div
													style="text-align: left; display: inline-block; background-color: #efefef; width: 100%">
													<div style="position: absolute; z-index: 1;">
														<s:text name="global.format.decimal2">
															<s:param value="#p.rate" />
														</s:text>
														%
													</div>
													<div
														style="display:inline-block;background-color: #66CCFF;width:<s:text name="global.format.decimal2">
															<s:param value="#p.rate" />
														</s:text>%">
														&nbsp;
													</div>

												</div>
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
								<s:if test="endTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="endTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="beginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="beginTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<yixin:page
									url="/monitor/onlineDeviceStatus/onlineRateStatistics.jspx"
									page="${page}" id="onlineRateStatistics_page">
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />									
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="sensorTypeId" value="${requestScope['sensorTypeId']}" />
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

