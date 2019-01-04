<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/doubleSelect.js"></script>
		<script language="JavaScript" type="text/javascript">
	
<%@ include file="/WEB-INF/jsp/A1000/archive/sensor/doubleSelect.jsp"%>
	
</script>
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
				档案管理&nbsp;&gt;&nbsp;监测装置管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">监测装置列表</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/archive/sensor"
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
										<tr>
											<td class="table_td_right td_whith110">
												所在杆塔：
											</td>
											<td class="table_td_left">
												<select id="lineSelect" name="queryModel.tower.line.lineId"
													onchange="ChangeSelect(this.value,'towerSelect','', dataSource, '--杆塔--' )"
													style="width: expression(this.offsetWidth &amp; gt;"></select>
												<select id="towerSelect" name="queryModel.tower.towerId"
													onchange=""
													style="width: expression(this.offsetWidth &amp; gt;"></select>
												<script type="text/javascript">
	ChangeSelect('0', 'lineSelect', "", dataSource, "--线路--", true)
</script>
												<script type="text/javascript">
	ChangeSelect(
			'0',
			'lineSelect',
			'<s:property value="%{#request.queryModel.tower.line.lineId}" escape="false" />',
			dataSource, "--线路--", true)
</script>
												<script type="text/javascript">
	ChangeSelect(
			'<s:property value="%{#request.queryModel.tower.line.lineId}" escape="false" />',
			'towerSelect',
			'<s:property value="%{#request.queryModel.tower.towerId}" escape="false" />',
			dataSource, "--杆塔--", true)
</script>
											</td>
											<td class="table_td_right td_whith110">
												所属监测类型：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.sensorType.sensorTypeId"
													list="%{#request.sensorTypes}" headerKey=""
													headerValue="--请选择--" listKey="sensorTypeId"
													listValue="sensorTypeName"></s:select>
											</td>
										</tr>
										<tr>

											<td class="table_td_right td_whith110">
												监测装置编码：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryModel.sensorCode" maxlength="17" />
											</td>


											<td class="table_td_right td_whith110">
												被监测装置编码：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryModel.bySensorCode" maxlength="17" />
											</td>

										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												状态：
											</td>
											<td colspan="3" class="table_td_left">
												<s:select name="queryModel.state"
													list="%{#{'1':'启用', '0':'停用'}}" headerKey=""
													headerValue="----请选择----" />
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

		<!-- start 信息区域 -->

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<s:form id="infoForm" action="list" namespace="/archive/sensor"
				method="post" theme="simple">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img
											src="${basePath }/resource/theme/${userTheme }/images/tab/ico_user.gif"
											width="16" height="16" />
										监测装置信息
									</div>
								</td>
								<td class="first_line_center">
									<div class="div_right">
										<s:submit value="新 增" action="add" cssClass="btn2" />
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
												监测装置编码
											</td>
											<td width="15%" class="first_line">
												被监测装置编码
											</td>
											<td width="15%" class="first_line">
												所在杆塔
											</td>
											<td width="15%" class="first_line">
												所属监测类型
											</td>

											<td width="10%" class="first_line">
												状态
											</td>
											<td class="first_line">
												操作
											</td>
										</tr>
										<s:iterator value="#request.page.records" var="sensor">
											<tr>
												<td class="second_line">
													<s:property value="#sensor.sensorCode" />
												</td>
												<td class="second_line">
													<s:property value="#sensor.bySensorCode" />
												</td>
												<td class="second_line">
													（
													<s:property value="#sensor.tower.line.lineName" />
													）
													<s:property value="#sensor.tower.towerCode" />
												</td>
												<td class="second_line">
													<s:property value="#sensor.sensorType.sensorTypeName" />
												</td>

												<td class="second_line">
													<s:if test="%{#sensor.state eq 1}">启用</s:if>
													<s:elseif test="%{#sensor.state eq 0}">
														<span class="cRed">停用</span>
													</s:elseif>
													<s:else>
														<span class="cRed">未知</span>
													</s:else>
												</td>
												<td class="second_line">
													<s:url id="detailUrl" action="detail"
														namespace="/archive/sensor">
														<s:param name="id">
															<s:property value="#sensor.sensorId" />
														</s:param>
													</s:url>
													<s:url id="editUrl" action="edit"
														namespace="/archive/sensor">
														<s:param name="id">
															<s:property value="#sensor.sensorId" />
														</s:param>
													</s:url>
													<s:url id="deleteUrl" action="delete"
														namespace="/archive/sensor">
														<s:param name="id">
															<s:property value="#sensor.sensorId" />
														</s:param>
													</s:url>
												
													<div class="content">
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_detail.gif"
															width="16" height="16" />
														<s:a href="%{detailUrl}">详细</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_edit.gif"
															width="16" height="16" />
														<s:a href="%{editUrl}">修改</s:a>														
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif"
															width="16" height="16" />
														<s:a href="%{deleteUrl}"
															onclick="return confirm('确定要删除该监测装置信息吗');">删除</s:a>
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
			</s:form>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="third_line_left"></td>
							<td class="third_line_center">
								<yixin:page url="/archive/sensor/list.jspx" page="${page}"
									id="sensor_page">
									<yixin:hidden name="queryModel.tower.line.lineId"
										value="${requestScope['queryModel.tower.line.lineId']}" />
									<yixin:hidden name="queryModel.tower.towerId"
										value="${requestScope['queryModel.tower.towerId']}" />
									<yixin:hidden name="queryModel.sensorType.sensorTypeId"
										value="${requestScope['queryModel.sensorType.sensorTypeId']}" />
									<!--<yixin:hidden name="queryModel.cmaInfo.cmaInfoId" value="${requestScope['queryModel.cmaInfo.cmaInfoId']}" />
									-->
									<yixin:hidden name="queryModel.sensorCode"
										value="${requestScope['queryModel.sensorCode']}" />
									<yixin:hidden name="queryModel.bySensorCode"
										value="${requestScope['queryModel.bySensorCode']}" />
									<yixin:hidden name="queryModel.state"
										value="${requestScope['queryModel.state']}" />
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
