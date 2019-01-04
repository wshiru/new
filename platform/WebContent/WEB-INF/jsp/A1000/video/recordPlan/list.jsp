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
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">定时录像计划</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/video/recordPlan"
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
												操作类型：
											</td>
											<td class="table_td_left">
												<s:hidden name="id" />
												<s:select name="operatorType"
													list="%{#{'0':'打开电源', '1':'关闭电源', '2':'开始录像', '3':'截图', '4':'调用预置位', '5':'停止录像','9':'重启服务程序'}}"
													headerKey="" headerValue="----请选择----" />
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
			<s:form id="infoForm" action="list" namespace="/video/recordPlan"
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
										计划任务
									</div>
								</td>
								<td class="first_line_center">
									<div class="div_right">
										<s:hidden name="id" />
										<s:submit value="新 增" action="add" cssClass="btn2" />
										<s:submit value="复制到所有设备" action="copyToAll" cssClass="btn6" />
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
											<td width="20%" class="first_line">
												任务开始时间
											</td>
											<td width="20%" class="first_line">
												通道号
											</td>											
											<td width="20%" class="first_line">
												操作类型
											</td>
											<td width="15%" class="first_line">
												参数
											</td>
											<td class="first_line">
												操作
											</td>
										</tr>
										<s:iterator value="#request.page.records" var="plan">
											<tr>
												<td class="second_line">
													<s:date name="#plan.startTime" format="H:mm:ss" />
												</td>
												<td class="second_line">
													<s:property value="#plan.channelNo" />
												</td>												
												<td class="second_line">
													<s:if test="%{#plan.operatorType eq 0}">
													打开电源
													</s:if>
													<s:elseif test="%{#plan.operatorType eq 1}">
													关闭电源
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 2}">
													开始录像
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 3}">
													截图
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 4}">
													调用预置位
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 5}">
													停止录像
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 9}">
													重启服务程序
													</s:elseif>													
													<s:else>
														未定义
													</s:else>

												</td>
												<td class="second_line">
													<s:if test="%{#plan.operatorType eq 0}">
													打开电源:<s:property value="#plan.openTime" />分钟
													</s:if>
													<s:elseif test="%{#plan.operatorType eq 1}">

													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 2}">
													开始录像:<s:property value="#plan.recordTime" />分钟
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 3}">

													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 4}">
													调用预置位:<s:property value="#plan.presetting" />
													</s:elseif>
													<s:elseif test="%{#plan.operatorType eq 5}">

													</s:elseif>
													<s:else>
														未定义
													</s:else>

												</td>
												<td class="second_line">
													<s:url id="editUrl" action="edit"
														namespace="/video/recordPlan">
														<s:param name="id">
															<s:property value="#plan.recordPlanId" />
														</s:param>
													</s:url>
													<s:url id="deleteUrl" action="delete"
														namespace="/video/recordPlan">
														<s:param name="id">
															<s:property value="#plan.recordPlanId" />
														</s:param>
													</s:url>
													<div class="content">
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_edit.gif"
															width="16" height="16" />
														<s:a href="%{editUrl}">修改</s:a>
														<img
															src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif"
															width="16" height="16" />
														<s:a href="%{deleteUrl}"
															onclick="return confirm('确定要删除该线路信息吗');">删除</s:a>
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
								<yixin:page url="/video/recordPlan/list.jspx" page="${page}"
									id="line_page">
									<yixin:hidden name="id" value="${requestScope['id']}" />
									<yixin:hidden name="queryModel.lineName"
										value="${requestScope['queryModel.lineName']}" />
									<yixin:hidden name="queryModel.voltageLevel.dictionaryId"
										value="${requestScope['queryModel.voltageLevel.dictionaryId']}" />
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
