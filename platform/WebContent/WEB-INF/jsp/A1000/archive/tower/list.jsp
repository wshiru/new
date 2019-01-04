<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/highLight.js"></script>
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;杆塔管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">杆塔列表</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/archive/tower" method="post" theme="simple">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_search.gif" width="16" height="16" />
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
												所属线路：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.line.lineId" list="%{#request.lines}" headerKey="" headerValue="--请选择--" listKey="lineId" listValue="lineName"></s:select>
											</td>
											<td class="table_td_right td_whith110">
												杆塔类型：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.towerType.towerTypeId" list="%{#request.towerTypes}" headerKey="" headerValue="--请选择--" listKey="towerTypeId" listValue="towerTypeName"></s:select>
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												杆塔编号：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryModel.towerCode" maxlength="50" />
											</td>
											<td class="table_td_right td_whith110">
												&nbsp;
											</td>
											<td class="table_td_left">
												&nbsp;
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
									<s:reset type="button" value="清 空" cssClass="btn2" onclick="clearForm(this.form);return false;" />
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
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<s:form id="infoForm" action="list" namespace="/archive/tower" method="post" theme="simple">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
									<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_user.gif" width="16" height="16" />
									杆塔信息
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
								<table cellspacing="1" onmouseover="changeto()" onmouseout="changeback()" class="tab">
									<tr>
										<td width="20%" class="first_line">
											杆塔编号
										</td>
										<td width="20%" class="first_line">
											所属线路
										</td>
										<td width="20%" class="first_line">
											杆塔类型
										</td>
										<td width="5%" class="first_line">
											经度
										</td>
										<td width="5%" class="first_line">
											纬度
										</td>
										<td class="first_line">
											操作
										</td>
									</tr>
									<s:iterator value="#request.page.records" var="tower">
										<tr>
											<td class="second_line">
												<s:property value="#tower.towerCode" />
											</td>
											<td class="second_line">
												<s:property value="#tower.line.lineName" />
											</td>
											<td class="second_line">
												<s:property value="#tower.towerType.towerTypeName" />
											</td>
											<td class="second_line">
												<s:property value="#tower.longitude" />
											</td>
											<td class="second_line">
												<s:property value="#tower.latitude" />
											</td>
											<td class="second_line">
												<s:url id="detailUrl" action="detail" namespace="/archive/tower">
													<s:param name="id">
														<s:property value="#tower.towerId" />
													</s:param>
												</s:url>
												<s:url id="editUrl" action="edit" namespace="/archive/tower">
													<s:param name="id">
														<s:property value="#tower.towerId" />
													</s:param>
												</s:url>
												<s:url id="deleteUrl" action="delete" namespace="/archive/tower">
													<s:param name="id">
														<s:property value="#tower.towerId" />
													</s:param>
												</s:url>
												<div class="content">
													<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_detail.gif" width="16" height="16" />
													<s:a href="%{detailUrl}">详细</s:a>
													<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_edit.gif" width="16" height="16" />
													<s:a href="%{editUrl}">修改</s:a>
													<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif" width="16" height="16" />
													<s:a href="%{deleteUrl}" onclick="return confirm('确定要删除该杆塔信息吗');">删除</s:a>
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
								<yixin:page url="/archive/tower/list.jspx" page="${page}" id="tower_page">
									<yixin:hidden name="queryModel.line.lineId" value="${requestScope['queryModel.line.lineId']}" />
									<yixin:hidden name="queryModel.towerType.towerTypeId" value="${requestScope['queryModel.towerType.towerTypeId']}" />
									<yixin:hidden name="queryModel.towerCode" value="${requestScope['queryModel.towerCode']}" />						
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
