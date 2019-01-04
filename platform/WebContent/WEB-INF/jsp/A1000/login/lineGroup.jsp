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
		<meta http-equiv="refresh" content="60;url=#" />

	</head>
	<body>
		<!-- start 当前位置  -->
		<div id="tab_position">
			<span class="tab_position_b"><s:property
					value="%{#request.line.lineName}" escape="false" /> </span>
		</div>
		<!-- end 当前位置  -->

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
										src="${basePath }/resource/theme/${userTheme }/images/tab/tabs.gif"
										width="16" height="16" />
									状态监测
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
											位置
										</td>
										<td width="10%" class="first_line">
											现场图片
										</td>
										<td width="10%" class="first_line">
											安装方位
										</td>
										<td width="50%" class="first_line">
											当前值
										</td>
										<td width="5%" class="first_line">
											在线状态
										</td>
										<td width="15%" class="first_line">
											报警状态
										</td>
									</tr>
									<s:iterator value="#request.list" var="p">
										<tr>
											<td class="second_line">
												<s:url action="checkSensorMenu" namespace="/" var="Url">
													<s:param name="id">
														<s:property value="#p.sensor.sensorId" />
													</s:param>
												</s:url>
												<a href="${Url}" target="_parent"> <s:property
														value="%{#p.sensor.tower.line.lineName}" escape="false" />
													<br /> <s:property value="%{#p.sensor.tower.towerCode}"
														escape="false" /> </a>
											</td>
											<td class="second_line">
												<a
													href="${basePath }<s:property value="#p.pictureFileName1" />"
													target="myimage"> <img
														src="${basePath }<s:property value="#p.pictureFileName1" />"
														width="120px" heigth="90px"
														alt="<s:property value="#p.caption" />" /> </a>
											</td>
											<td class="second_line">
												<a
													href="${basePath }<s:property value="#p.pictureFileName2" />"
													target="myimage"> <img
														src="${basePath }<s:property value="#p.pictureFileName2" />"
														width="120px" heigth="90px"
														alt="<s:property value="#p.caption" />" /> </a>
											</td>

											<td class="second_line">
												<div style="text-align: left; width: 550px;">
													<s:iterator value="#p.dataItem" var="v">
														<s:if test="%{#p.pictureData == false}">
															<div
																style="width: 160px; float: left; display: inline; padding: 0 10px 0 0px; margin: 0 10px 0 0px;">
																<span style="width: 120px;"> <s:property
																		value="#v.name" /> : </span>
																<span> <s:property value="#v.value" /> </span>
																<hr />
															</div>
														</s:if>														
														<s:else>
															<div
																style="width: 160px; float: left; display: inline; padding: 0 5px 0 5px; margin: 0 5px 0 5px;">
																<span style="width: 120px;"> <a
																	href="${basePath }/resource/videofile/<s:property
																		value="#v.url" />"
																	target="myimage"> <img
																			style="width: 160px; height: 120px"
																			src="${basePath }/resource/videofile/<s:property
																		value="#v.url" />"
																			alt="<s:property
																		value="#v.name" />" />
																</a> </span>

															</div>
														</s:else>
													</s:iterator>
												</div>
											</td>
											<td class="second_line">
												<s:if test="#p.state">
													是
												</s:if>
												<s:else>
													<span class="cRed">否</span>
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
									<s:property value="%{#request.stateMessage}" escape="false" />
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
		<!-- end 信息区域 -->
		<br/>
		<br/>
		<!-- 页尾提示信息开始 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- 页尾提示信息结束 -->
	</body>
</html>

