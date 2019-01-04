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
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/highLight.js"></script>
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;
			<span class="tab_position_b"> 角色管理
			</span>
		</div>		
		<!-- end 当前位置 -->
		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/system/role"
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
												角色名称：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryRoleName" maxlength="50" />
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
			<s:form id="infoForm" action="add" namespace="/system/role"
				method="post" theme="simple">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img
											src="${basePath }/resource/theme/${userTheme }/images/tab/role.png"
											width="16" height="16" />
										角色信息
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
											<td width="5%" class="first_line">
												序号
											</td>
											<td width="20%" class="first_line">
												角色名称
											</td>
											<td width="15%" class="first_line">
												角色类型
											</td>
											<td class="first_line">
												角色说明
											</td>
											<td width="30%" class="first_line">
												操作
											</td>
										</tr>
										<s:iterator value="#request.pageData.records"
											status="rowstatus">
											<tr>
												<td class="second_line">
													<s:property value="#rowstatus.index + 1" />
												</td>
												<td class="second_line">
													<s:property value="roleName" />
												</td>
												<td class="second_line">
													<s:if test="roleType==0">用户角色</s:if>
													<s:else>
														<span class="cRed">系统角色</span>
													</s:else>
												</td>
												<td class="second_line">
													<s:property value="roleDesc" />
												</td>
												<td class="second_line">
													<s:url id="modAuthUrl" action="modAuth"
														namespace="/system/role">
														<s:param name="role.roleId" value="roleId" />
														<s:param name="pn" value="#parameters.pn" />
													</s:url>
													<s:url id="editUrl" action="edit" namespace="/system/role">
														<s:param name="role.roleId" value="roleId" />
														<s:param name="pn" value="#parameters.pn" />
													</s:url>
													<s:url id="deleteUrl" action="delete"
														namespace="/system/role">
														<s:param name="role.roleId" value="roleId"></s:param>
													</s:url>
													<div class="content">
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_gears.gif"
															width="16" height="16" />
														<s:a href="%{modAuthUrl}">配置权限</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_edit.gif"
															width="16" height="16" />
														<s:a href="%{editUrl}">修改</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif"
															width="16" height="16" />
														<s:a href="%{deleteUrl}"
															onclick="return confirm('确定要删除该角色信息吗？');">删除</s:a>
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
								<yixin:page url="/system/role/list.jspx" page="${pageData}"
									id="role_page">
									<yixin:hidden name="queryRoleName"
										value="${requestScope['queryRoleName']}" />
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