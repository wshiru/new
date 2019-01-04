<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link href="${basePath }/resource/theme/${userTheme }/css/main.css"
			rel="stylesheet" type="text/css" />
		<link href="${basePath }/resource/theme/${userTheme }/css/tab.css"
			rel="stylesheet" type="text/css" />

		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/highLight.js"></script>

		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<!-- 60秒后自动刷新一次页面 -->
		<meta http-equiv="refresh" content="60;url=mainFrame.jspx" />

	</head>
	<body>
		<!-- start 当前位置  -->
		<div id="tab_position">
			<span class="tab_position_b"><s:property
					value="%{#request.stateMessage}" escape="false" /> </span>
		</div>
		<!-- end 当前位置  -->

		<!-- start 信息区域 -->
		<s:iterator value="#request.list" var="t" id="id" status="st">

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
										<s:url action="showGroupPage" namespace="/"
											var="sensorTypeUrl">
											<s:param name="sensorTypeId">
												<s:property value="key.sensorTypeId" />
											</s:param>
										</s:url>
										<a href="${sensorTypeUrl}" target="_parent"> <s:property value='key.sensorTypeName'
												escape="true" />
										</a>
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
											<td width="10%" class="first_line">
												安装位置
											</td>
											<td width="10%" class="first_line">
												监测编码
											</td>
											<td width="5%" class="first_line">
												在线状态
											</td>
											<td width="10%" class="first_line">
												报警状态
											</td>
											<!-- 
											<td width="60%" class="first_line">
												当前值
											</td>
											 -->
										</tr>
										<s:iterator value="#id.value" var="p">
											<tr>
												<td class="second_line">
													<s:url action="checkSensorMenu" namespace="/" var="Url">
														<s:param name="id">
															<s:property value="#p.sensor.sensorId" />
														</s:param>
													</s:url>
													<a href="${Url}" target="_parent"> <s:property
															value="%{#p.sensor.tower.line.lineName}" escape="false" />
														( <s:property value="%{#p.sensor.tower.towerCode}"
															escape="false" /> )</a>
												</td>
												<td class="second_line">
													<a href="${Url}" target="_parent"> <s:property
															value="%{#p.sensor.sensorCode}" escape="false" /> </a>

												</td>
												<td class="second_line">
													<s:if test="#p.state">
													在线
												</s:if>
													<s:else>
														<span class="cRed">不在线</span>
													</s:else>
												</td>
												<td class="second_line">
													<s:if test="%{#p.warning eq '正常'}">
													正常
												</s:if>
													<s:else>
														<span class="cRed"> <s:property
																value="%{#p.warning}" escape="false" /> </span>
													</s:else>
												</td>
												<!-- 
												<td class="second_line">
													<div style="text-align: left; width: 700px;">
														<s:iterator value="#p.dataItem" var="v">
															<div
																style="width: 210px; float: left; display: inline; padding: 0 10px 0 0px; margin: 0 10px 0 0px;">
																<span style="width: 120px;"> <s:property
																		value="#v.name" /> : </span>
																<span> <s:property value="#v.value" /> </span>
																<hr />
															</div>
														</s:iterator>
													</div>
												</td>
												 -->
											</tr>
										</s:iterator>
									</table>
								</td>
								<td class="second_line_right">
									&nbsp;
								</td>
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
									<div style="text-align: left;">
										&nbsp;
									</div>
								</td>
								<td class="third_line_right">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:iterator>
		<!-- end 信息区域 -->

		<!-- 页尾提示信息开始 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- 页尾提示信息结束 -->
	</body>
</html>

