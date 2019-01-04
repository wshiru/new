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

	function queryWait() {
		var queryDiv = document.getElementById("tabs");
		queryDiv.innerHTML = "";
	}
</script>

	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
				<span class="tab_position_b">微气象日数据统计</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/weather/analysis"
			method="post" theme="simple">
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
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												曲线数据：
											</td>
											<td colspan="2" class="table_td_left">
												<s:checkbox name="showMaxWindSpeed" />
												<span>最大风速</span>
												<s:checkbox name="showTemperature" />
												<span>气温</span>
												<s:checkbox name="showHumidity" />
												<span>湿度</span>
												<s:checkbox name="showAirPressure" />
												<span>气压</span>
												<s:checkbox name="showDailyRainfall" />
												<span>日降雨量</span>
												<s:checkbox name="showPrecipitationIntensity" />
												<span>降水强度</span>
												<s:checkbox name="showRadiationIntensity" />
												<span>光辐射度</span> &nbsp;&nbsp;&nbsp;&nbsp;
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
					<a href="#tabs-5"><span>数据列表</span> </a>
				</li>
			</ul>

			<div id="tabs-4">
				<table align="center" cellspacing="1" class="tab">
					<tr>
						<td colspan="4" class="table_td_left">
							<s:url action="chart" namespace="/weather/analysis"
								var="chartUrl">
								<s:param name="sensorId" value="sensorId" />
								<s:param name="beginTime">
									<s:date name="beginTime" format="yyyy-MM-dd" />
								</s:param>
								<s:param name="endTime">
									<s:date name="endTime" format="yyyy-MM-dd" />
								</s:param>
								<s:param name="showMaxWindSpeed" value="showMaxWindSpeed" />
								<s:param name="showTemperature" value="showTemperature" />
								<s:param name="showHumidity" value="showHumidity" />
								<s:param name="showAirPressure" value="showAirPressure" />
								<s:param name="showPrecipitationIntensity"
									value="showPrecipitationIntensity" />								
								<s:param name="showDailyRainfall" value="showDailyRainfall" />
								<s:param name="showRadiationIntensity"
									value="showRadiationIntensity" />
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
												<td width="6%" class="first_line">
													序号
												</td>
												<td width="10%" class="first_line">
													采集时间
												</td>

												<td width="8%" class="first_line">
													最大风速(m/s)
												</td>

												<td width="8%" class="first_line">
													最高气温(℃)
												</td>

												<td width="8%" class="first_line">
													最低气温(℃)
												</td>

												<td width="8%" class="first_line">
													最高湿度(%)
												</td>

												<td width="8%" class="first_line">
													最低湿度(%)
												</td>
												<td width="8%" class="first_line">
													最高气压(hPa)
												</td>
												<td width="8%" class="first_line">
													最低气压(hPa)
												</td>
												<td width="7%" class="first_line">
													日降雨量(mm)
												</td>
												<td class="first_line">
													最大降水强度(mm/min)
												</td>

												<td class="first_line">
													最大光辐射度 (W/m2)
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
														<s:property value="max_windspeed" />
													</td>

													<td class="second_line">
														<s:property value="max_temperature" />
													</td>

													<td class="second_line">
														<s:property value="min_temperature" />
													</td>

													<td class="second_line">
														<s:property value="max_humidity" />
													</td>

													<td class="second_line">
														<s:property value="min_humidity" />
													</td>


													<td class="second_line">
														<s:property value="max_airpressure" />
													</td>

													<td class="second_line">
														<s:property value="min_airpressure" />
													</td>
													<td class="second_line">
														<s:property value="dailyrainfall" />
													</td>

													<td class="second_line">
														<s:property value="max_precipitationintensity" />
													</td>

													<td class="second_line">
														<s:property value="max_radiationintensity" />
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
										<yixin:page url="/weather/analysis/list.jspx"
											page="${pageData}" id="weatherAnalysis_page">
											<yixin:hidden name="sensorId"
												value="${requestScope['sensorId']}" />
											<yixin:hidden name="beginTime"
												value="${pageScope.qBeginTime}" />
											<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />

											<yixin:hidden name="showMaxWindSpeed"
												value="${requestScope['showMaxWindSpeed']}" />
											<yixin:hidden name="showTemperature"
												value="${requestScope['showTemperature']}" />
											<yixin:hidden name="showHumidity"
												value="${requestScope['showHumidity']}" />

											<!--<yixin:hidden name="showWindSpeed" value="${requestScope['showWindSpeed']}" />
									<yixin:hidden name="showWindDirection" value="${requestScope['showWindDirection']}" />
									-->

											<yixin:hidden name="showAirPressure"
												value="${requestScope['showAirPressure']}" />
											<yixin:hidden name="showDailyRainfall"
												value="${requestScope['showDailyRainfall']}" />
											<yixin:hidden name="showRadiationIntensity"
												value="${requestScope['showRadiationIntensity']}" />
											<yixin:hidden name="showPrecipitationIntensity"
												value="${requestScope['showPrecipitationIntensity']}" />
											
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