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
			<span class="tab_position_b">最新数据</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->

		<s:url action="chart" namespace="/weather/sampling" var="chartUrl">
			<s:param name="sensorId" value="sensorId" />
			<s:param name="beginTime">
				<s:date name="beginTime" format="yyyy-MM-dd" />
			</s:param>
			<s:param name="endTime">
				<s:date name="endTime" format="yyyy-MM-dd" />
			</s:param>
			<s:param name="showWindSpeed" value="showWindSpeed" />
			<s:param name="showTemperature" value="showTemperature" />
			<s:param name="showHumidity" value="showHumidity" />
			<!--<s:param name="showWindSpeed" value="showWindSpeed" />
										<s:param name="showWindDirection" value="showWindDirection" />
										-->
			<s:param name="showPrecipitation_Intensity"
				value="showPrecipitation_Intensity" />

			<s:param name="showAirPressure" value="showAirPressure" />
			<s:param name="showDailyRainfall" value="showDailyRainfall" />
			<s:param name="showRadiationIntensity" value="showRadiationIntensity" />
			<s:param name="showPrecipitationIntensity"
				value="showPrecipitationIntensity" />
		</s:url>
		<!-- end 查询区域 -->

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
									<img
										src="${basePath }/resource/theme/${userTheme }/images/tab/comments.gif"
										width="16" height="16" />
									<s:property value="#request.sensor.sensorType.sensorTypeName" />
									:
									<s:property value="#request.sensor.sensorCode" />
								</div>
							</td>
							<td class="first_line_center">
								<div class="div_right">

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
								<table cellspacing="1" onmouseover="changeto()"
									onmouseout="changeback()" class="tab">
									<tr>
										<td width="10%" class="first_line">
											采集时间
										</td>

										<td width="8%" class="first_line">
											10分钟平均风速(m/s)
										</td>

										<td width="8%" class="first_line">
											10分钟平均风向(°)
										</td>

										<td width="8%" class="first_line">
											最大风速(m/s)
										</td>

										<td width="8%" class="first_line">
											极大风速(m/s)
										</td>

										<td width="8%" class="first_line">
											标准风速(m/s)
										</td>
										<!-- 
										<td width="8%" class="first_line">
											采样风速(m/s)
										</td> -->

										<td width="6%" class="first_line">
											气温(℃)
										</td>
										<td width="6%" class="first_line">
											湿度(%)
										</td>

										<!-- <td width="10%" class="first_line">
											风速(km/h)
										</td>
										-->
										<td width="6%" class="first_line">
											气压(hPa)
										</td>

										<td width="7%" class="first_line">
											降雨量(mm)
										</td>


										<td width="10%" class="first_line">
											降水强度(mm/min)
										</td>

										<td width="10%" class="first_line">
											光辐射度 (W/m2)
										</td>
									</tr>
									<s:iterator value="#request.pageData.records"
										status="rowstatus">
										<tr>
											<td class="second_line">
												<s:date name="samplingTime" format="yyyy-MM-dd HH:mm:ss" />
											</td>

											<td class="second_line">
												<s:property value="averageWindSpeed10min" />
											</td>

											<td class="second_line">
												<s:property value="averageWindDirection10min" />
												(
												<s:property value="windDirectionName" />
												)
											</td>

											<td class="second_line">
												<s:property value="maxWindSpeed" />
											</td>

											<td class="second_line">
												<s:property value="extremeWindSpeed" />
											</td>

											<td class="second_line">
												<s:property value="strandrdWindSpeed" />
											</td>
											<!-- 
											<td class="second_line">
												<s:property value="windSpeed" />
											</td>
											 -->											

											<td class="second_line">
												<s:property value="temperature" />
											</td>

											<td class="second_line">
												<s:property value="humidity" />
											</td>
											<td class="second_line">
												<s:property value="airPressure" />
											</td>

											<td class="second_line">
												<s:property value="dailyRainfall" />
											</td>

											<td class="second_line">
												<s:property value="precipitationIntensity" />
											</td>

											<td class="second_line">
												<s:property value="radiationIntensity" />
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
							<td class="third_line_left">
								&nbsp;
							</td>
							<td class="third_line_center">
								&nbsp;

								<!-- 
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
								<yixin:page url="/weather/sampling/list.jspx" page="${pageData}" id="weatherSampling_page">
									<yixin:hidden name="sensorId" value="${requestScope['sensorId']}" />
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="showTemperature" value="${requestScope['showTemperature']}" />
									<yixin:hidden name="showHumidity" value="${requestScope['showHumidity']}" />
									
									

								<yixin:hidden name="showAirPressure"
									value="${requestScope['showAirPressure']}" />
								<yixin:hidden name="showDailyRainfall"
									value="${requestScope['showDailyRainfall']}" />
								<yixin:hidden name="showRadiationIntensity"
									value="${requestScope['showRadiationIntensity']}" />
								<yixin:hidden name="showPrecipitation_Intensity"
									value="${requestScope['showPrecipitation_Intensity']}" />


								</yixin:page>
								-->

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
		<!-- start 曲线图片
		<div class="chartBox">
			<img src="openChart('${chartUrl}" />
		</div> -->
		<!-- end 曲线图片 -->

		<div id="tabs" style="height: 100%;">
			<ul>
				<li>
					<a href="#tabs-99"><span>数据分析曲线</span> </a>
				</li>
				<s:iterator value="#request.pictures" var="p" status="stat">
					<li>
						<a href="#tabs-<s:property value="#stat.index" />"><span>
								<s:property value="#p.caption" /> </span> </a>

					</li>
				</s:iterator>
			</ul>

			<div id="tabs-99">
				<table align="center" cellspacing="1" class="tab">
					<tr>
						<td colspan="4" class="table_td_left">
							<div class="chartBox">
								<img src="${chartUrl}" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<s:iterator value="#request.pictures" var="p" status="stat">
				<div id="tabs-<s:property value="#stat.index" />"">
					<table align="center" cellspacing="1" class="tab">
						<tr>
							<td colspan="4" class="table_td_left">
								<div class="chartBox">
									<a href="${basePath }<s:property value="#p.fileName" />"
										target="myimage"> <img
											src="${basePath }<s:property value="#p.fileName" />"
											width="640px" heigth="480px"
											alt="<s:property value="#p.caption" />" /> </a>
								</div>

							</td>
						</tr>
					</table>
				</div>
			</s:iterator>
		</div>

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>