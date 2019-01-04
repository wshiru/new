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

		<s:iterator value="#request.list" var="t"  status="st">

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
												<s:property value="#t.sensorType.sensorTypeId" />
											</s:param>
										</s:url>
										<a href="${sensorTypeUrl}" target="_parent"> <s:property
												value='#t.sensorType.sensorTypeName' escape="true" /> </a>
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
									<div
										style="float: left; display: inline; background-color: #ffffff; width: 100%; height: 100%; margin: 0px; padding: 0px;">
										<s:iterator value="#t.groupPage" var="p">
											<div
												style="float: left; display: inline; width: 130px; height: 100px; margin: 8px 0px 20px 4px; padding: 2px;">
												<s:url action="checkSensorMenu" namespace="/" var="Url">
													<s:param name="id">
														<s:property value="#p.sensor.sensorId" />
													</s:param>
												</s:url>
												<a href="${Url}" target="_parent"> <img
														src="${basePath }<s:property value="#p.pictureFileName1" />"
														width="120px" heigth="90px"
														alt="<s:property value="#p.caption" />" /> </a>
												<br />
												<a href="${Url}" target="_parent"><s:property
														value="%{#p.sensor.tower.line.lineName}" escape="false" />
													( <s:property value="%{#p.sensor.tower.towerCode}"
														escape="false" /> ) <br /> <s:if
														test="%{#p.state == false}">
														<span class="cRed">设备不在线</span>
													</s:if> <s:elseif test="%{#p.warning != '正常'}">
														<span class="cRed"> <s:property
																value="%{#p.warning}" escape="false" /> </span>
													</s:elseif> <s:else>
													正常
												</s:else> </a>
											</div>
										</s:iterator>
									</div>
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
										<s:property value="#t.message" />
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
		<br/>
		<br/>
		<!-- 页尾提示信息开始 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- 页尾提示信息结束 -->
	</body>
</html>

