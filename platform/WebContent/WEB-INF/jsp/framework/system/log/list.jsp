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
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>

	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;
			<span class="tab_position_b"> 日志管理
			</span>
		</div>	
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/system/log"
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
												记录时间：
											</td>
											<td class="table_td_left">
												<input type="text" name="queryBeginTime"
													value='<s:date name="queryBeginTime" format="yyyy-MM-dd"/>'
													onclick="WdatePicker()" maxlength="15"
													onfocus="WdatePicker()" />
												至
												<input type="text" name="queryEndTime"
													value='<s:date name="queryEndTime" format="yyyy-MM-dd"/>'
													onclick="WdatePicker()" maxlength="15"
													onfocus="WdatePicker()" />
											</td>
											<td class="table_td_right td_whith110">
												操作类型：
											</td>
											<td class="table_td_left">
												<s:select name="queryOperateType" headerKey=""
													headerValue="请选择"
													list="#{'10':'系统日志', '21':'新增档案', '22':'修改档案', '23':'删除档案', '30':'召测参数', '31':'下发参数', '40':'召测数据', '50':'告警设置'}" />
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												操作员编号：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryOperatorCode" maxlength="30" />
											</td>
											<td class="table_td_right td_whith110">
												操作员名称：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryOperatorName" maxlength="30" />
											</td>
										</tr>
										<tr>
											<td class="table_td_right td_whith110">
												IP地址：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryIpAddress"
													value="%{#request.queryIpAddress}" maxlength="15" />
											</td>
											<td class="table_td_right td_whith110">
												操作说明：
											</td>
											<td class="table_td_left">
												<s:textfield name="queryRemark" maxlength="30" />
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
									日志信息
								</div>
							</td>
							<td class="first_line_center">
								<div class="div_right">
									<script type="text/javascript">
	                                  function   checkDelTime() {   
                                      	  if (document.delForm.removeTime.value=="")   
           						      	  {   
	              						     alert("请先选择或输入指定日期!");      
	              					         return false;
	           						      }   
	          						      return (confirm('确定要删除当前指定日期之前的日志吗？'));       
          						      }
        						    </script>

									<s:form name="delForm" action="delete" namespace="/system/log"
										theme="simple" method="post" onsubmit="return checkDelTime();">
										<s:hidden name="queryBeginTime"
											value="%{#request.queryBeginTime}"></s:hidden>
										<s:hidden name="queryEndTime" value="%{#request.queryEndTime}"></s:hidden>
										<s:hidden name="queryOperatorCode"
											value="%{#request.queryOperatorCode}"></s:hidden>
										<s:hidden name="queryOperatorName"
											value="%{#request.queryOperatorName}"></s:hidden>
										<s:hidden name="queryOperateType"
											value="%{#request.queryOperateType}"></s:hidden>
										<s:hidden name="queryIpAddress"
											value="%{#request.queryIpAddress}"></s:hidden>
										<s:hidden name="queryRemark" value="%{#request.queryRemark}"></s:hidden>

										<table cellspacing="0" cellpadding="0">
											<tr>
												<td>
													清空指定日期之前的日志&nbsp;
												</td>
												<td>
													<input type="text" name="removeTime"
														value='<s:date name="%{#request.removeTime}" format="yyyy-MM-dd"/>'
														onclick="WdatePicker()" size="13" maxlength="15"
														onfocus="WdatePicker()" />
													&nbsp;
												</td>
												<td>
													<input type="submit" value="删 除" class="btn2" />
												</td>
											</tr>
										</table>
									</s:form>

								</div>
							</td>
							<td class="first_line_right"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<s:form name="infoForm" action="list" namespace="/system/log"
						theme="simple" method="post">
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
												操作时间
											</td>
											<td width="15%" class="first_line">
												操作员编号
											</td>
											<td width="10%" class="first_line">
												操作员名称
											</td>
											<td width="10%" class="first_line">
												操作类型
											</td>
											<td width="15%" class="first_line">
												IP地址
											</td>
											<td class="first_line">
												操作说明
											</td>
										</tr>
										<s:iterator value="#request.pageData.records"
											status="rowstatus">
											<tr>
												<td class="second_line">
													<s:property value="#rowstatus.index + 1" />
												</td>
												<td class="second_line">
													<s:date name="operateTime" format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td class="second_line">
													<s:property value="user.userCode" />
												</td>
												<td class="second_line">
													<s:property value="user.userName" />
												</td>
												<td class="second_line">
													<s:if test="operateType=='10'">系统日志</s:if>
													<s:elseif test="operateType=='21'">新增档案</s:elseif>
													<s:elseif test="operateType=='22'">修改档案</s:elseif>
													<s:elseif test="operateType=='23'">删除档案</s:elseif>
													<s:elseif test="operateType=='30'">召测参数</s:elseif>
													<s:elseif test="operateType=='31'">下发参数</s:elseif>
													<s:elseif test="operateType=='40'">召测数据</s:elseif>
													<s:elseif test="operateType=='50'">告警设置</s:elseif>
													<s:else>其它</s:else>
												</td>
												<td class="second_line">
													<s:property value="ipAddress" />
												</td>
												<td class="second_line">
													<s:if test="remark.length() > 30">
														<s:url id="detailUrl" action="detail"
															namespace="/system/log">
															<s:param name="id">
																<s:property value="logId" />
															</s:param>
														</s:url>
														<s:a href="%{detailUrl}">
															<s:property value="remark.substring(0,30)" />...</s:a>
													</s:if>
													<s:else>
														<s:property value="remark" />
													</s:else>
												</td>
											</tr>
										</s:iterator>
									</table>
								</td>
								<td class="second_line_right"></td>
							</tr>
						</table>
					</s:form>
				</td>
			</tr>
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="third_line_left"></td>
							<td class="third_line_center">
								<s:if test="queryEndTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="queryEndTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="queryBeginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="queryBeginTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<yixin:page url="/system/log/list.jspx" page="${pageData}"
									id="log_page">
									<yixin:hidden name="queryBeginTime"
										value="${pageScope.qBeginTime}" />
									<yixin:hidden name="queryEndTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="queryOperatorCode"
										value="${requestScope['queryOperatorCode']}" />
									<yixin:hidden name="queryOperatorName"
										value="${requestScope['queryOperatorName']}" />
									<yixin:hidden name="queryIpAddress"
										value="${requestScope['queryIpAddress']}" />
									<yixin:hidden name="queryOperateType"
										value="${requestScope['queryOperateType']}" />
									<yixin:hidden name="queryRemark"
										value="${requestScope['queryRemark']}" />

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