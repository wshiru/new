<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;系统监控&nbsp;&gt;&nbsp;任务&nbsp;&gt;&nbsp;
				<span class="tab_position_b">任务列表</span>
			</div>
			<div class="position_r">
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/taskConfig" method="post" theme="simple">
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
												创建时间：
											</td>
											<td class="table_td_left">
												<input type="text" name="queryBeginTime" maxlength="20" value='<s:date name="%{#request.queryBeginTime }" format="yyyy-MM-dd"/>' onclick="WdatePicker()" onfocus="WdatePicker()" readonly="readonly" />
												至
												<input type="text" name="queryEndTime" maxlength="20" value='<s:date name="%{#request.queryEndTime }" format="yyyy-MM-dd"/>' onclick="WdatePicker()" onfocus="WdatePicker()" readonly="readonly" />
											</td>
											<td class="table_td_right td_whith110">
												命令类型：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.cmdType" list="%{#request.cmdTypes}" headerKey="" headerValue="--请选择--"></s:select>
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												状&nbsp;&nbsp;&nbsp;&nbsp;态：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.state" list="%{#{'0':'未下发','1':'已下发','2':'已取消','3':'已执行'} }" headerKey="-1" headerValue="--请选择--" ></s:select>
											</td>
											<td class="table_td_right td_whith110">
												操作用户：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.user.userId" list="%{#request.users}" headerKey="" headerValue="--请选择--" listKey="userId" listValue="userCode"></s:select>
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												监测代理：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.cmaCode" list="%{#request.cmaInfos}" headerKey="" headerValue="--请选择--" listKey="cmaCode" listValue="cmaCode"></s:select>
											</td>
											<td class="table_td_right">
												监测装置：
											</td>
											<td class="table_td_left">
												<s:select name="queryModel.sensorCode" list="%{#request.sensors}" headerKey="" headerValue="--请选择--" listKey="sensorCode" listValue="sensorCode"></s:select>
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
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
									<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_user.gif" width="16" height="16" />
									任务信息
								</div>
							</td>
							<td class="first_line_center">
								<div class="div_right">
									&nbsp;
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
										<td width="15%" class="first_line">
											创建时间
										</td>
										<td width="15%" class="first_line">
											命令类型
										</td>
										<td width="30%" class="first_line">
											影响装置
										</td>
										<td width="10%" class="first_line">
											状态
										</td>
										<td width="10%" class="first_line">
											操作用户
										</td>
										<td class="first_line">
											操作
										</td>
									</tr>
									<s:iterator value="#request.page.records" var="taskConfig">
										<tr>
											<td class="second_line">
												<s:date name="%{#taskConfig.createTime }" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td class="second_line">
												<s:if test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@GETCONFIG.equalsIgnoreCase(#taskConfig.cmdType) }">
													读配置交互
												</s:if>
												<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@SETCONFIG.equalsIgnoreCase(#taskConfig.cmdType) }">
													写配置交互
												</s:elseif>
												<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@ACTIVATE.equalsIgnoreCase(#taskConfig.cmdType) }">
													控制交互
												</s:elseif>
												<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@RESEND.equalsIgnoreCase(#taskConfig.cmdType) }">
													数据重传
												</s:elseif>
												<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@DEACTIVATE.equalsIgnoreCase(#taskConfig.cmdType) }">
													休眠
												</s:elseif>
												<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@REALTIME.equalsIgnoreCase(#taskConfig.cmdType) }">
													实时数据
												</s:elseif>
												<s:elseif test="%{#taskConfig.upgradeFile != null }">
													远程升级
												</s:elseif>
											</td>
											<td class="second_line">
												<s:if test="%{null != #taskConfig.cmaCode}">
													监测代理（<s:property value="#taskConfig.cmaCode" />）
												</s:if>
												<s:elseif test="%{null != #taskConfig.sensorCode}">
													监测装置（<s:property value="#taskConfig.sensorCode" />）
												</s:elseif>
											</td>
											<td class="second_line">
												<s:if test="%{0 == #taskConfig.state }">
													未下发
												</s:if>
												<s:elseif test="%{1 == #taskConfig.state }">
													已下发
												</s:elseif>
												<s:elseif test="%{2 == #taskConfig.state }">
													已取消
												</s:elseif>
												<s:elseif test="%{3 == #taskConfig.state }">
													已执行
												</s:elseif>
												<s:else>
													未知
												</s:else>
											</td>
											<td class="second_line">
												<s:property value="#taskConfig.user.userCode" />
											</td>
											<td class="second_line">
												<s:url id="detailUrl" action="detail" namespace="/taskConfig">
													<s:param name="id">
														<s:property value="#taskConfig.taskConfigId" />
													</s:param>
												</s:url>
												<s:url id="cancelUrl" action="cancel" namespace="/taskConfig">
													<s:param name="id">
														<s:property value="#taskConfig.taskConfigId" />
													</s:param>
												</s:url>
												<div class="content">
													<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_detail.gif" width="16" height="16" />
													<s:a href="%{detailUrl}">详细</s:a>
													<s:if test="%{0 == #taskConfig.state }">
														<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_delete.gif" width="16" height="16" />
														<s:a href="%{cancelUrl}" onclick="return confirm('确定要取消该任务信息吗');">取消</s:a>
													</s:if>
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
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="third_line_left"></td>
							<td class="third_line_center">
								<s:if test="queryBeginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="queryBeginTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="queryEndTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="queryEndTime" format="yyyy-MM-dd" />
									</s:set>	
								</s:if>
								<yixin:page url="/taskConfig/list.jspx" page="${page}" id="line_page">
									<yixin:hidden name="queryBeginTime" value="${pageScope['qBeginTime']}" />
									<yixin:hidden name="queryEndTime" value="${pageScope['qEndTime']}" />
									<yixin:hidden name="queryModel.cmdType" value="${requestScope['queryModel.cmdType']}" />
									<yixin:hidden name="queryModel.state" value="${requestScope['queryModel.state']}" />
									<yixin:hidden name="queryModel.user.userId" value="${requestScope['queryModel.user.userId']}" />
									<yixin:hidden name="queryModel.cmaCode" value="${requestScope['queryModel.cmaCode']}" />
									<yixin:hidden name="queryModel.sensorCode" value="${requestScope['queryModel.sensorCode']}" />
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
