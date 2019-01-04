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
			<span class="tab_position_b">图像视频采集情况</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/video/playback"
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
										<s:hidden name="id" />
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

											<td class="table_td_right td_whith110">
												文件类型：
											</td>
											<td class="table_td_left">
												<s:select name="fileType"
													list="%{#{'0':'视频', '1':'图片'}}" headerKey=""
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
									<s:submit value="查 询" cssClass="btn2" onclick="queryWait()"
										action="list" />
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
									图像视频
								</div>
							</td>
							<td class="first_line_center">
								<div class="div_right">

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
								<div>
									<s:iterator value="#request.page.records" status="rowstatus"
										var="p">
										<div
											style="background-color: #bfbfbf; width: 160px; float: left; display: inline; padding: 5px; margin: 15px;">
											<span style="width: 120px;"> <a
												href="${basePath }/resource/videofile/<s:property
																		value="#p.fileName" />"
												target="myimage"> <s:if test="%{#p.fileType eq 0}">
														<img style="width: 160px; height: 120px"
															src="${basePath }/resource/images/videofile.png"
															alt="<s:property
																		value="#v.name" />" />
														<s:date name="#p.createTime" format="MM-dd HH:mm:ss" />视频
																		</s:if> <s:else>
														<img style="width: 160px; height: 120px"
															src="${basePath }/resource/videofile/<s:property
																		value="#p.fileName" />"
															alt="<s:property
																		value="#v.name" />" />
														<s:date name="#p.createTime" format="MM-dd HH:mm:ss" />图片
																		</s:else> </a> </span>

										</div>
									</s:iterator>
								</div>

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


								<yixin:page url="/video/playback/list.jspx" page="${page}"
									id="videoRecordFileList_page">
									<yixin:hidden name="id" value="${requestScope['id']}" />
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="fileType"
										value="${requestScope['fileType']}" />
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