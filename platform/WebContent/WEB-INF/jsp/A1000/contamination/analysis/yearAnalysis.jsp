<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<%@ include file="/WEB-INF/jsp/framework/prepareChart.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common//js/checkbox.js"></script>

		<link rel="stylesheet"
			href="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery.cookie.js"></script>

		<script type="text/javascript">
	$(function() {
		// Tabs
		$('#tabs').tabs({
			cookie : {
				expires : 30
			}
		});
	});
</script>

	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">年数据分析</span>
		</div>
		<!-- end 当前位置 -->


		<!-- start 查询区域 -->
		<s:form id="queryForm" action="yearList"
			namespace="/contamination/analysis" method="post" theme="simple">
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
										<s:hidden name="sensorId" />
										<tr>
											<td class="table_td_right td_whith110">
												采集时间：
											</td>
											<td class="table_td_left">

												<s:select name="StartYearId" list="%{#request.Years}"
													listKey="%{#request.YearId}"
													listValue="%{#request.YearName}" />
												至
												<s:select name="EndYearId" list="%{#request.Years}"
													listKey="%{#request.YearId}"
													listValue="%{#request.YearName}" />
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												曲线数据：
											</td>
											<td colspan="2" class="table_td_left">
												<s:checkbox name="showEsdd" />
												<span>盐密</span>
												<s:checkbox name="showNsdd" />
												<span>灰密</span>
												<s:checkbox name="showDailyMaxTemperature" />
												<span>日最高温度</span>
												<s:checkbox name="showDailyMinTemperature" />
												<span>日最低温度</span>
												<s:checkbox name="showDailyMaxHumidity" />
												<span>日最大湿度</span>
												<s:checkbox name="showDailyMinHumidity" />
												<span>日最小湿度</span> &nbsp;&nbsp;&nbsp;&nbsp;
												<s:checkbox name="rDataFSelect"
													onclick="selectOrUnselect(this, this.parentNode)"
													value="true" />
												全选/全不选
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

									<s:submit value="导出Excel" cssClass="btn4"
										action="yearExportExcel" />
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


		<div id="tabs" style="height: 100%;">

			<ul>
				<li>
					<a href="#tabs-4"><span>分析曲线</span> </a>
				</li>
				<li>
					<a href="#tabs-5"><span>采样数据</span> </a>
				</li>
			</ul>

			<div id="tabs-4">
				<table align="center" cellspacing="1" class="tab">
					<tr>
						<td colspan="4" class="table_td_left">
							<s:url action="yearChart" namespace="/contamination/analysis"
								var="chartUrl">
								<s:param name="sensorId" value="sensorId" />
								<s:param name="beginTime">
									<s:date name="beginTime" format="yyyy-MM-dd" />
								</s:param>
								<s:param name="endTime">
									<s:date name="endTime" format="yyyy-MM-dd" />
								</s:param>
								<s:param name="showEsdd" value="showEsdd" />
								<s:param name="showNsdd" value="showNsdd" />
								<s:param name="showDailyMaxTemperature"
									value="showDailyMaxTemperature" />
								<s:param name="showDailyMinTemperature"
									value="showDailyMinTemperature" />
								<s:param name="showDailyMaxHumidity"
									value="showDailyMaxHumidity" />
								<s:param name="showDailyMinHumidity"
									value="showDailyMinHumidity" />
							</s:url>

							<img src="openChart('${chartUrl}" />

						</td>
					</tr>
				</table>
			</div>


			<div id="tabs-5">

				<!-- start 信息区域 -->
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="30">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="first_line_left"></td>
									<td class="first_line_center">
										<div class="tab_title">

											<!-- 		<img src="${basePath }/resource/theme/${userTheme }/images/tab/comments.gif" width="16" height="16" />
									采样明细   -->
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
										<table cellspacing="1" onmouseover="changeto()"
											onmouseout="changeback()" class="tab">
											<tr>
												<td width="8%" class="first_line">
													序号
												</td>
												<td width="15%" class="first_line">
													采集时间
												</td>
												<td width="13%" class="first_line">
													盐密(mg/cm2)
												</td>
												<td width="13%" class="first_line">
													灰密(mg/cm2)
												</td>
												<td width="10%" class="first_line">
													日最高温度(℃)
												</td>
												<td width="10%" class="first_line">
													日最低温度(℃)
												</td>
												<td width="10%" class="first_line">
													日最大湿度(％RH)
												</td>
												<td width="10%" class="first_line">
													日最小湿度(％RH)
												</td>
											</tr>
											<s:iterator value="#request.pageData.records"
												status="rowstatus" var="p">
												<tr>
													<td class="second_line">
														<s:property value="#rowstatus.index + 1" />
													</td>
													<td class="second_line">
														<s:date name="#p.samplingTime"
															format="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td class="second_line">
														<s:property value="#p.esdd" />
													</td>
													<td class="second_line">
														<s:property value="#p.nsdd" />
													</td>
													<td class="second_line">
														<s:property value="#p.dailyMaxTemperature" />
													</td>
													<td class="second_line">
														<s:property value="#p.dailyMinTemperature" />
													</td>
													<td class="second_line">
														<s:property value="#p.dailyMaxHumidity" />
													</td>
													<td class="second_line">
														<s:property value="#p.dailyMinHumidity" />
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
										<yixin:page url="/contamination/analysis/yearList.jspx"
											page="${page}" id="towerTiltYearAnalysis_page">
											<yixin:hidden name="sensorId"
												value="${requestScope['sensorId']}" />
											<yixin:hidden name="StartYearId"
												value="${requestScope['StartYearId']}" />
											<yixin:hidden name="EndYearId"
												value="${requestScope['EndYearId']}" />
											<yixin:hidden name="showEsdd"
												value="${requestScope['showEsdd']}" />
											<yixin:hidden name="showNsdd"
												value="${requestScope['showNsdd']}" />
											<yixin:hidden name="showDailyMaxTemperature"
												value="${requestScope['showDailyMaxTemperature']}" />
											<yixin:hidden name="showDailyMinTemperature"
												value="${requestScope['showDailyMinTemperature']}" />
											<yixin:hidden name="showDailyMaxHumidity"
												value="${requestScope['showDailyMaxHumidity']}" />
											<yixin:hidden name="showDailyMinHumidity"
												value="${requestScope['showDailyMinHumidity']}" />

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
			</div>

		</div>


		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>