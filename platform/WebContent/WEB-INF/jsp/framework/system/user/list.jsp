<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
			<span class="tab_position_b">用户管理</span>
		</div>

		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/system/user"
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
												用户编号：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryModel.userCode" maxlength="30" />
											</td>
											<td class="table_td_right td_whith110">
												用户名称：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryModel.userName" maxlength="50" />
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												状&nbsp;&nbsp;&nbsp;&nbsp;态：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.state"
													list="%{#{'1':'启用', '0':'停用'}}" headerKey="-1"
													headerValue="----请选择----" />
											</td>
											<td class="table_td_right">
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
			<s:form id="infoForm" action="list" namespace="/system/user"
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
										用户信息
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
												用户编号
											</td>
											<td width="15%" class="first_line">
												用户名称
											</td>
											<td width="10%" class="first_line">
												状态
											</td>
											<td width="15%" class="first_line">
												最后登录时间
											</td>
											<td width="15%" class="first_line">
												最后登录IP
											</td>
											<td class="first_line">
												操作
											</td>
										</tr>
										<s:iterator value="#request.page.records" var="user">
											<tr>
												<td class="second_line">
													<s:property value="#user.userCode" />
												</td>
												<td class="second_line">
													<s:property value="#user.userName" />
												</td>
												<td class="second_line">
													<s:if test="%{#user.state eq 1}">启用</s:if>
													<s:elseif test="%{#user.state eq 0}">
														<span class="cRed">停用</span>
													</s:elseif>
													<s:else>
														<span class="cRed">未知</span>
													</s:else>
												</td>
												<td class="second_line">
													<s:date name="#user.lastLoginTime"
														format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td class="second_line">
													<s:property value="#user.lastLoginIp" />
												</td>
												<td class="second_line">
													<s:url id="detailUrl" action="detail"
														namespace="/system/user">
														<s:param name="id">
															<s:property value="#user.userId" />
														</s:param>
													</s:url>
													<s:url id="editUrl" action="edit" namespace="/system/user">
														<s:param name="id">
															<s:property value="#user.userId" />
														</s:param>
													</s:url>
													<s:url id="editRolesUrl" action="editRoles"
														namespace="/system/user">
														<s:param name="id">
															<s:property value="#user.userId" />
														</s:param>
													</s:url>
													<s:url id="deleteUrl" action="delete"
														namespace="/system/user">
														<s:param name="id">
															<s:property value="#user.userId" />
														</s:param>
													</s:url>
													<div class="content">
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_detail.gif"
															width="16" height="16" />
														<s:a href="%{detailUrl}">详细</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_gears.gif"
															width="16" height="16" />
														<s:a href="%{editRolesUrl}">分配角色</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_edit.gif"
															width="16" height="16" />
														<s:a href="%{editUrl}">修改</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif"
															width="16" height="16" />
														<s:a href="%{deleteUrl}"
															onclick="return confirm('确定要删除该用户信息吗');">删除</s:a>
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
								<yixin:page url="/system/user/list.jspx" page="${page}"
									id="user_page">
									<yixin:hidden name="queryModel.userCode"
										value="${requestScope['queryModel.userCode']}" />
									<yixin:hidden name="queryModel.userName"
										value="${requestScope['queryModel.userName']}" />
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
