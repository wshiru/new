<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp" %>
		<%@ include file="/WEB-INF/jsp/framework/prepareChart.jsp" %>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/checkbox.js"></script>

	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;历史数据&nbsp;&gt;&nbsp;
				<span class="tab_position_b">相间风偏</span>
			</div>
			<div class="position_r">
			</div>
		</div>
		<!-- end 当前位置 -->
		
		<!-- start 查询区域 -->
		<s:form id="queryForm" action="list" namespace="/whitemonsoon/sampling" method="post" theme="simple">
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
										<s:hidden name="sensorId"/>
										<tr>
										    <td class="table_td_right td_whith110">
												采集时间：
											</td>
											<td class="table_td_left">									 
												<input type="text" name="beginTime" value='<s:date name="beginTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
												至												
												<input type="text" name="endTime" value='<s:date name="endTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
											</td>
										</tr>
										<tr>
										    <td class="table_td_right td_whith110">
												曲线数据：
											</td>
											<td colspan="2" class="table_td_left">
												<s:checkbox name="showWindAngle" /><span>风偏角</span>
												<s:checkbox name="showAngle" /><span>倾斜角</span>
												<s:checkbox name="showMinClearanceParams" /><span>最小电气间隙参数</span>
												&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="rDataFSelect" onclick="selectOrUnselect(this, this.parentNode)" value="true" />
												全选/全不选
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
									<img src="${basePath }/resource/theme/${userTheme }/images/tab/comments.gif" width="16" height="16" />
									相间风偏历史数据
								</div>
							</td>
							<td class="first_line_center">
							    <div class="div_right" >
									<s:url action="chart" namespace="/whitemonsoon/sampling" var="chartUrl">
										<s:param name="sensorId" value="sensorId" />
										<s:param name="beginTime">
											<s:date name="beginTime" format="yyyy-MM-dd" />
										</s:param>
										<s:param name="endTime">
											<s:date name="endTime" format="yyyy-MM-dd" />
										</s:param>
										<s:param name="showWindAngle" value="showWindAngle" />
										<s:param name="showAngle" value="showAngle" />
										<s:param name="showMinClearanceParams" value="showMinClearanceParams" />
									</s:url>
						           <table cellspacing="0" cellpadding="0">
						             <tr>
						                <td>  
       						   				<button class="btn4" type="button" onclick="openChart('${chartUrl}')">曲线分析</button>	
						                </td> 
						            </tr>
						           </table>	          
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
										<td width="10%" class="first_line">
											序号
										</td>
										<td class="first_line">
											采集时间
										</td>
										<td width="20%" class="first_line">
											风偏角(°)
										</td>
										<td width="20%" class="first_line">
											倾斜角(°)
										</td>
										<td width="20%" class="first_line">
											最小电气间隙参数(mm)
										</td>
									</tr>
									<s:iterator value="#request.pageData.records" status="rowstatus">
										<tr>
											<td class="second_line">
												<s:property value="#rowstatus.index + 1" />
											</td>
											<td class="second_line">
												<s:date name="samplingTime" format="yyyy-MM-dd HH:mm:ss"/>
											</td>
											<td class="second_line">
												<s:property value="windAngle" />
											</td>
											<td class="second_line">
												<s:property value="angle" />
											</td>
											<td class="second_line">
												<s:property value="minClearanceParams" />
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
							<td class="third_line_left">
							&nbsp;
							</td>
							<td class="third_line_center">
								<s:if test="endTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="endTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="beginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="beginTime" format="yyyy-MM-dd" />
									</s:set>	
								</s:if>
								<yixin:page url="/whitemonsoon/sampling/list.jspx" page="${pageData}" id="whiteMonsoonSampling_page">
									<yixin:hidden name="sensorId" value="${requestScope['sensorId']}" />
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="showWindAngle" value="${requestScope['showWindAngle']}" />
									<yixin:hidden name="showAngle" value="${requestScope['showAngle']}" />
									<yixin:hidden name="showMinClearanceParams" value="${requestScope['showMinClearanceParams']}" />
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