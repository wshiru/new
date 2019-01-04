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
			<span class="tab_position_b">现场安装图片列表</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/archive/picture"
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
											src="${basePath }/resource/theme/${userTheme }/images/tab/gears.gif"
											width="16" height="16" />
										监测装置信息
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
												<s:property value="#request.sensor.tower.line.lineName" />
												-
												<s:property value="#request.sensor.tower.towerCode" />
											</td>
											<td class="table_td_right td_whith110">
												所属监测类型：
											</td>
											<td class="table_td_left">
												<s:property
													value="#request.sensor.sensorType.sensorTypeName" />
											</td>
										</tr>
										<tr>

											<td class="table_td_right td_whith110">
												监测装置编码：
											</td>
											<td class="table_td_left">
												<s:property value="#request.sensor.sensorCode" />
											</td>


											<td class="table_td_right td_whith110">
												被监测装置编码：
											</td>
											<td class="table_td_left">
												<s:property value="#request.sensor.bySensorCode" />
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
									<s:hidden name="id" value="%{#request.sensor.sensorId}" />
									<s:submit value="刷新" cssClass="btn2" />
									&nbsp;&nbsp;



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
			<s:form id="infoForm" action="list" namespace="/archive/picture"
				method="post" theme="simple">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img
											src="${basePath }/resource/theme/${userTheme }/images/tab/311.gif"
											width="16" height="16" />
										现场安装图片
									</div>
								</td>
								<td class="first_line_center">
									<div class="div_right">
										<s:hidden name="sensor.sensorId" />
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
											<td width="10%" class="first_line">
												类型
											</td>
											<td width="15%" class="first_line">
												标题
											</td>
											<td width="20%" class="first_line">
												图片
											</td>
											<td width="30%" class="first_line">
												说明
											</td>
											<td width="10%" class="first_line">
												创建时间
											</td>
											<td width="15%" class="first_line">
												操作
											</td>
										</tr>
										<s:iterator value="#request.page.records" var="p">
											<tr>

												<td class="second_line">
													<s:if test="%{#p.defaultShow == 1}">
														现场图片
													</s:if>
													<s:elseif test="%{#p.defaultShow == 2}">
														安装方位
													</s:elseif>
													<s:else>
														其它
													</s:else>
												</td>
												<td class="second_line">
													<s:property value="#p.caption" />
												</td>
												<td class="second_line">
													<img src="${basePath }<s:property value="#p.fileName" />"
														width="120px" heigth="90px"
														alt="<s:property value="#p.caption" />" />
												</td>
												<td class="second_line">
													<s:property value="#p.pictureDesc" />
												</td>
												<td class="second_line">
													<s:date name="#p.createTime" format="yyyy-MM-dd hh:mm:ss" />
												</td>
												<td class="second_line">
													<s:url id="detailUrl" action="detail"
														namespace="/archive/picture">
														<s:param name="picture.pictureId">
															<s:property value="#p.pictureId" />
														</s:param>
														<s:param name="id">
															<s:property value="#request.sensor.sensorId" />
														</s:param>
													</s:url>
													<s:url id="editUrl" action="edit"
														namespace="/archive/picture">
														<s:param name="picture.pictureId">
															<s:property value="#p.pictureId" />
														</s:param>
														<s:param name="id">
															<s:property value="#request.sensor.sensorId" />
														</s:param>
													</s:url>
													<s:url id="deleteUrl" action="delete"
														namespace="/archive/picture">
														<s:param name="picture.pictureId">
															<s:property value="#p.pictureId" />
														</s:param>
														<s:param name="id">
															<s:property value="#request.sensor.sensorId" />
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
							<td class="third_line_left">
								&nbsp;
							</td>
							<td class="third_line_center">
								<yixin:page url="/archive/picture/list.jspx" page="${page}"
									id="sensor_page">
									<yixin:hidden name="sensor.sensorId"
										value="${requestScope['sensor.sensorId']}" />
									<yixin:hidden name="id"
										value="${requestScope['sensor.sensorId']}" />
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
