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
	function queryWait() {
		var queryDiv = document.getElementById("tabs");
		queryDiv.innerHTML = "";
	}
</script>


	</head>

	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">最新数据</span>
		</div>
		<!-- end 当前位置 -->

		<s:url action="dayChart" namespace="/landslide/displacement"
			var="chartUrl">
			<s:param name="sensorId" value="sensorId" />
			<s:param name="beginTime">
				<s:date name="beginTime" format="yyyy-MM-dd" />
			</s:param>
			<s:param name="endTime">
				<s:date name="endTime" format="yyyy-MM-dd" />
			</s:param>
			<s:param name="chartType" value="2" />
			<s:param name="dataType" value="0" />

		</s:url>

		<!-- start 查询区域 -->

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
								基岩深度：
								<s:property value="#request.landslideParameter.allDepth" />
								米;&nbsp; &nbsp;
								<s:property value="#request.landslideParameter.sampleNum" />
								个
								<s:if test="#request.landslideParameter.xyType ==1">单轴</s:if>
								<s:else>双轴</s:else>
								测点
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
								<table cellspacing="1" class="tab">
									<tr>

										<td class="group_line">

											最新数据[
											<s:date name="#request.realData.samplingTime"
												format="yyyy-MM-dd HH:mm:ss" />
											]
										</td>

										<td class="group_line">
											上日数值[
											<s:date name="#request.dayData.samplingTime"
												format="yyyy-MM-dd" />
											]
										</td>



										<td class="first_line">
											上月数值[
											<s:date name="#request.monthData.samplingTime"
												format="yyyy-MM-dd" />
											]
										</td>


										<s:if test="%{#request.yearData != NULL}">
											<td class="first_line">
												年初数据[
												<s:date name="#request.yearData.samplingTime"
													format="yyyy-MM-dd" />
												]
											</td>
										</s:if>
									</tr>
									<tr>
										<td class="second_line">
											<table onmouseover="changeto()" onmouseout="changeback()"
												cellspacing="1" class="tab detailtab">
												<tr>
													<td class="first_line">
														监测点号
													</td>
													<td class="first_line">
														深度(米)
													</td>

													<td class="first_line">
														X倾角(°)
													</td>
													<s:if test="%{#request.xyType eq 2}">
														<td class="first_line">
															Y倾角(°)
														</td>
													</s:if>
													<td class="first_line">
														X位移(mm)
													</td>
													<s:if test="%{#request.xyType eq 2}">
														<td class="first_line">
															Y位移(mm)
														</td>
														<td class="first_line">
															合位移(mm)
														</td>
													</s:if>
													<td class="first_line">
														状态
													</td>
												</tr>

												<s:iterator
													value="#request.realData.landslideSamplingDetails"
													status="idx" var="d">
													<tr>
														<td class="second_line">
															<s:property value="#d.pointNo" />
														</td>
														<td class="second_line">
															<s:property value="#d.relativeDepth" />
														</td>
														<td class="second_line">
															<s:property value="#d.angleX" />


														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="second_line">
																<s:property value="#d.angleY" />
															</td>
														</s:if>
														<td class="second_line">
															<s:property value="#d.displacementX" />
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="second_line">
																<s:property value="#d.displacementY" />
															</td>
															<td class="second_line">
																<s:property value="#d.displacement" />
															</td>
														</s:if>
														<td class="second_line">
															<s:iterator
																value="#request.landslideParameter.landslideParameterDetails"
																status="idx" var="p">
																<s:if test="%{#d.pointNo == #p.pointNo}">
																	<s:if test="%{#p.currentState != 0}">
																		<span class="cRed"> 发生<s:property
																				value="#p.currentState" />级预警 </span>
																	</s:if>
																	<s:else>
																			正常
																		</s:else>
																</s:if>
															</s:iterator>
														</td>
													</tr>
												</s:iterator>
											</table>
										</td>
										<s:if test="%{#request.dayData != NULL}">
											<td class="second_line">

												<table onmouseover="changeto()" onmouseout="changeback()"
													cellspacing="1" class="tab detailtab">
													<tr>
														<td class="first_line">
															监测点号
														</td>
														<td class="first_line">
															深度(米)
														</td>

														<td class="first_line">
															X倾角(°)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y倾角(°)
															</td>
														</s:if>
														<td class="first_line">
															X位移(mm)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y位移(mm)
															</td>
															<td class="first_line">
																合位移(mm)
															</td>
														</s:if>
													</tr>

													<s:iterator
														value="#request.dayData.landslideSamplingDetails"
														status="idx" var="d">
														<tr>
															<td class="second_line">
																<s:property value="#d.pointNo" />
															</td>
															<td class="second_line">
																<s:property value="#d.relativeDepth" />
															</td>
															<td class="second_line">
																<s:property value="#d.angleX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.angleY" />
																</td>
															</s:if>
															<td class="second_line">
																<s:property value="#d.displacementX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.displacementY" />
																</td>
																<td class="second_line">
																	<s:property value="#d.displacement" />
																</td>
															</s:if>
														</tr>
													</s:iterator>
												</table>
											</td>
										</s:if>
										<s:else>
											<td class="second_line">
												无数据
											</td>
										</s:else>
										<s:if test="%{#request.monthData != NULL}">
											<td class="second_line">

												<table onmouseover="changeto()" onmouseout="changeback()"
													cellspacing="1" class="tab detailtab">
													<tr>
														<td class="first_line">
															监测点号
														</td>
														<td class="first_line">
															深度(米)
														</td>

														<td class="first_line">
															X倾角(°)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y倾角(°)
															</td>
														</s:if>
														<td class="first_line">
															X位移(mm)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y位移(mm)
															</td>
															<td class="first_line">
																合位移(mm)
															</td>
														</s:if>
													</tr>

													<s:iterator
														value="#request.monthData.landslideSamplingDetails"
														status="idx" var="d">
														<tr>
															<td class="second_line">
																<s:property value="#d.pointNo" />
															</td>
															<td class="second_line">
																<s:property value="#d.relativeDepth" />
															</td>
															<td class="second_line">
																<s:property value="#d.angleX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.angleY" />
																</td>
															</s:if>
															<td class="second_line">
																<s:property value="#d.displacementX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.displacementY" />
																</td>
																<td class="second_line">
																	<s:property value="#d.displacement" />
																</td>
															</s:if>
														</tr>
													</s:iterator>
												</table>
											</td>
										</s:if>
										<s:else>
											<td class="second_line">
												无数据
											</td>
										</s:else>
										<s:if test="%{#request.yearData != NULL}">
											<td class="second_line">

												<table onmouseover="changeto()" onmouseout="changeback()"
													id="detailtab<s:property value="#rowstatus.index " />"
													cellspacing="1" class="tab detailtab">
													<tr>
														<td class="first_line">
															监测点号
														</td>
														<td class="first_line">
															深度(米)
														</td>

														<td class="first_line">
															X倾角(°)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y倾角(°)
															</td>
														</s:if>
														<td class="first_line">
															X位移(mm)
														</td>
														<s:if test="%{#request.xyType eq 2}">
															<td class="first_line">
																Y位移(mm)
															</td>
															<td class="first_line">
																合位移(mm)
															</td>
														</s:if>
													</tr>

													<s:iterator
														value="#request.yearData.landslideSamplingDetails"
														status="idx" var="d">
														<tr>
															<td class="second_line">
																<s:property value="#d.pointNo" />
															</td>
															<td class="second_line">
																<s:property value="#d.relativeDepth" />
															</td>
															<td class="second_line">
																<s:property value="#d.angleX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.angleY" />
																</td>
															</s:if>
															<td class="second_line">
																<s:property value="#d.displacementX" />
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="second_line">
																	<s:property value="#d.displacementY" />
																</td>
																<td class="second_line">
																	<s:property value="#d.displacement" />
																</td>
															</s:if>
														</tr>
													</s:iterator>
												</table>
											</td>
										</s:if>


										<s:iterator value="#request.monthData.records"
											status="rowstatus" var="p">
											<tr>
												<td class="second_line">
													月数据
												</td>
												<td class="second_line">
													<s:date name="#p.samplingTime" format="yyyy-MM-dd HH:mm:ss" />
												</td>

												<td class="second_line">
													<table
														id="detailtab<s:property value="#rowstatus.index " />"
														cellspacing="1" class="tab detailtab">
														<tr>
															<td class="first_line">
																监测点号
															</td>
															<td class="first_line">
																深度(米)
															</td>

															<td class="first_line">
																X倾角(°)
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="first_line">
																	Y倾角(°)
																</td>
															</s:if>
															<td class="first_line">
																X位移(mm)
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="first_line">
																	Y位移(mm)
																</td>
																<td class="first_line">
																	合位移(mm)
																</td>
															</s:if>
														</tr>

														<s:iterator value="#p.landslideSamplingDetails"
															status="idx" var="d">
															<tr>
																<td class="second_line">
																	<s:property value="#d.pointNo" />
																</td>
																<td class="second_line">
																	<s:property value="#d.relativeDepth" />
																</td>
																<td class="second_line">
																	<s:property value="#d.angleX" />
																</td>
																<s:if test="%{#request.xyType eq 2}">
																	<td class="second_line">
																		<s:property value="#d.angleY" />
																	</td>
																</s:if>
																<td class="second_line">
																	<s:property value="#d.displacementX" />
																</td>
																<s:if test="%{#request.xyType eq 2}">
																	<td class="second_line">
																		<s:property value="#d.displacementY" />
																	</td>
																	<td class="second_line">
																		<s:property value="#d.displacement" />
																	</td>
																</s:if>
															</tr>
														</s:iterator>
													</table>
												</td>
											</tr>
										</s:iterator>
										<s:iterator value="#request.yearData.records"
											status="rowstatus" var="p">
											<tr>
												<td class="second_line">
													年数据
												</td>
												<td class="second_line">
													<s:date name="#p.samplingTime" format="yyyy-MM-dd HH:mm:ss" />
												</td>

												<td class="second_line">
													<table
														id="detailtab<s:property value="#rowstatus.index " />"
														cellspacing="1" class="tab detailtab">
														<tr>
															<td class="first_line">
																监测点号
															</td>
															<td class="first_line">
																深度(米)
															</td>

															<td class="first_line">
																X倾角(°)
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="first_line">
																	Y倾角(°)
																</td>
															</s:if>
															<td class="first_line">
																X位移(mm)
															</td>
															<s:if test="%{#request.xyType eq 2}">
																<td class="first_line">
																	Y位移(mm)
																</td>
																<td class="first_line">
																	合位移(mm)
																</td>
															</s:if>
														</tr>

														<s:iterator value="#p.landslideSamplingDetails"
															status="idx" var="d">
															<tr>
																<td class="second_line">
																	<s:property value="#d.pointNo" />
																</td>
																<td class="second_line">
																	<s:property value="#d.relativeDepth" />
																</td>
																<td class="second_line">
																	<s:property value="#d.angleX" />
																</td>
																<s:if test="%{#request.xyType eq 2}">
																	<td class="second_line">
																		<s:property value="#d.angleY" />
																	</td>
																</s:if>
																<td class="second_line">
																	<s:property value="#d.displacementX" />
																</td>
																<s:if test="%{#request.xyType eq 2}">
																	<td class="second_line">
																		<s:property value="#d.displacementY" />
																	</td>
																	<td class="second_line">
																		<s:property value="#d.displacement" />
																	</td>
																</s:if>
															</tr>
														</s:iterator>
													</table>
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
		</div> -->

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