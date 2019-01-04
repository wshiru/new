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
			src="${basePath }/resource/common//js/checkbox.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>



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

		<!-- end 查询区域 -->

		<s:url action="dayChart" namespace="/towertilt/analysis"
			var="chartUrl">
			<s:param name="sensorId" value="sensorId" />
			<s:param name="beginTime">
				<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
			<s:param name="endTime">
				<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>

			<s:param name="showInclination" value="showInclination" />
			<s:param name="showGradientAlongLines" value="showGradientAlongLines" />
			<s:param name="showLateralTilt" value="showLateralTilt" />
			<s:param name="showAngle_x" value="showAngle_x" />
			<s:param name="showAngle_y" value="showAngle_y" />

		</s:url>


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
										<td width="15%" class="first_line">
											类型
										</td>
										<td width="15%" class="first_line">
											时间
										</td>
										<td width="13%" class="first_line">
											倾斜度(mm/m)
										</td>
										<td width="13%" class="first_line">
											顺线倾斜度(mm/m)
										</td>
										<td width="15%" class="first_line">
											横向倾斜度(mm/m)
										</td>
										<td width="15%" class="first_line">
											顺线倾斜角(°)
										</td>
										<td class="first_line">
											横向倾斜角(°)
										</td>
									</tr>
									<s:iterator value="#request.realData.records"
										status="rowstatus" var="p">
										<tr>
											<td class="second_line">
												最新数值
											</td>
											<td class="second_line">
												<s:date name="#p.samplingTime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td class="second_line">
												<s:property value="#p.inclination" />
											</td>
											<td class="second_line">
												<s:property value="#p.gradientAlongLines" />
											</td>
											<td class="second_line">
												<s:property value="#p.lateralTilt" />
											</td>

											<td class="second_line">
												<s:if
													test="%{#request.towerTiltParameter.currentXState == NULL}">
													<s:property value="#p.angleX" />
												</s:if>
												<s:elseif
													test="%{#request.towerTiltParameter.currentXState == 0}">
													<s:property value="#p.angleX" />(正常)
												</s:elseif>
												<s:else>
													<span class="cRed"> <s:property value="#p.angleX" />
														(<s:property
															value="#request.towerTiltParameter.currentXState" />
														级预警)</span>
												</s:else>
											</td>

											<td class="second_line">
												<s:if
													test="%{#request.towerTiltParameter.currentYState == NULL}">
													<s:property value="#p.angleY" />
												</s:if>
												<s:elseif
													test="%{#request.towerTiltParameter.currentYState == 0}">
													<s:property value="#p.angleY" />(正常)
												</s:elseif>
												<s:else>
													<span class="cRed"> <s:property value="#p.angleY" />
														(<s:property
															value="#request.towerTiltParameter.currentYState" />
														级预警)</span>
												</s:else>
											</td>

										</tr>
									</s:iterator>
									<s:iterator value="#request.daliyData.records"
										status="rowstatus" var="p">
										<tr>
											<td class="second_line">
												上日数值
											</td>
											<td class="second_line">
												<s:date name="#p.samplingTime" format="yyyy-MM-dd" />
											</td>
											<td class="second_line">
												<s:property value="#p.inclination" />
											</td>
											<td class="second_line">
												<s:property value="#p.gradientAlongLines" />
											</td>
											<td class="second_line">
												<s:property value="#p.lateralTilt" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleX" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleY" />
											</td>

										</tr>
									</s:iterator>
									<s:iterator value="#request.monthData.records"
										status="rowstatus" var="p">
										<tr>
											<td class="second_line">
												上月数值
											</td>
											<td class="second_line">
												<s:date name="#p.samplingTime" format="yyyy-MM-dd" />
											</td>
											<td class="second_line">
												<s:property value="#p.inclination" />
											</td>
											<td class="second_line">
												<s:property value="#p.gradientAlongLines" />
											</td>
											<td class="second_line">
												<s:property value="#p.lateralTilt" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleX" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleY" />
											</td>

										</tr>
									</s:iterator>
									<s:iterator value="#request.yearData.records"
										status="rowstatus" var="p">
										<tr>
											<td class="second_line">
												年初数值
											</td>
											<td class="second_line">
												<s:date name="#p.samplingTime" format="yyyy-MM-dd" />
											</td>
											<td class="second_line">
												<s:property value="#p.inclination" />
											</td>
											<td class="second_line">
												<s:property value="#p.gradientAlongLines" />
											</td>
											<td class="second_line">
												<s:property value="#p.lateralTilt" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleX" />
											</td>

											<td class="second_line">
												<s:property value="#p.angleY" />
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
			<img src="${chartUrl}" />
		</div>
		-->
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

		<!-- end 曲线图片 -->
		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>