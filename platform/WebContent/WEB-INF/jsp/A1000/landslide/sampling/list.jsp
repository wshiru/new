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
			<span class="tab_position_b">历史采样数据</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/landslide/sampling"
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
												显示监测点：
											</td>
											<td colspan="2" class="table_td_left">
												<s:select name="showSampleNum"
													list="#request.showSampleNums" headerKey=""
													headerValue="--请选择--" listKey="key" listValue="value"
													value="#request.showSampleNum"></s:select>
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
									<s:submit value="查 询" cssClass="btn2" onclick="queryWait()"
										action="list" />
									&nbsp;&nbsp;
									<s:submit value="导出Excel" cssClass="btn4"
										action="timeExportExcel" />
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
									地质滑坡监测数据
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
										<td width="5%" class="first_line">
											序号
										</td>
										<td width="18%" class="first_line">
											采集时间
										</td>

										<td width="13%" class="first_line">
											基岩深度(米)
										</td>

										<td width="12%" class="first_line">
											监测点数
										</td>

										<td class="first_line">
											明细
										</td>
									</tr>

									<s:iterator value="#request.pageData.records"
										status="rowstatus" var="p">
										<tr>
											<td class="second_line">
												<s:property value="#rowstatus.index + 1" />
											</td>
											<td class="second_line">
												<s:date name="#p.samplingTime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td class="second_line">
												<s:property value="#p.allDepth" />
											</td>
											<td class="second_line">
												<s:property value="#p.sampleNum" />
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
							<td class="third_line_left"></td>
							<td class="third_line_center">

								<s:if test="beginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="beginTime" format="yyyy-MM-dd HH:mm:ss" />
									</s:set>
								</s:if>

								<s:if test="endTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss" />
									</s:set>
								</s:if>


								<yixin:page url="/landslide/sampling/list.jspx"
									page="${pageData}" id="towerTiltQuerySamplingList_page">
									<yixin:hidden name="sensorId"
										value="${requestScope['sensorId']}" />
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="showSampleNum"
										value="${requestScope['showSampleNum']}" />
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