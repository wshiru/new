<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<!-- 60秒后自动刷新一次页面 -->
		<meta http-equiv="refresh" content="60;url=messageFrame.jspx" />

		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.messager.js"></script>

		<link href="${basePath }/resource/theme/${userTheme }/css/main.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${basePath}/resource/theme/${userTheme }/css/messageFrame.css"
			rel="stylesheet" type="text/css" />

		<script type="text/javascript">
	$(document).ready(function() {
		$("#sItem li:not(:first)").css("display", "none");
		var B = $("#sItem li:last");
		var C = $("#sItem li:first");
		setInterval(function() {
			if (B.is(":visible")) {
				C.fadeIn(500).addClass("in");
				B.hide();
			} else {
				$("#sItem li:visible").addClass("in");
				$("#sItem li.in").next().fadeIn(500);
				$("li.in").hide().removeClass("in");
			}
		}, 8000); //每3秒钟切换一条，你可以根据需要更改 
	});
</script>

	</head>

	<body>
		<div class="weatherbg">
			<div class="font_name">
				<s:property
					value="%{#session[@com.yixin.framework.login.web.LoginAction@SESSION_LOGIN_INFO].user.userName}"
					escape="false" />
				<s:property value="%{#session.loginTimeInfo }" escape="false" />
			</div>
			<s:if test="%{#request.pageData.totalPageCount >0}">
				<ul id="sItem" class="font_warning">
					<li>
						<img src="${basePath }/resource/theme/${userTheme }/images/ts.gif" />
						<s:property value="#request.onlineMessage" /> 共
						<s:property value="#request.pageData.totalRecordCount" />
						条新报警记录
					</li>
					<s:iterator value="#request.pageData.records" var="p">
						<li>
							<img
								src="${basePath }/resource/theme/${userTheme }/images/ts.gif" />
							<span
								title="<s:date name="#p.samplingDate" format="MM月dd日HH:mm" /><s:property value="#p.sensor.tower.line.lineName" /><s:property value="#p.sensor.tower.towerCode" /><s:property value="#p.warningInfo" />">
								<s:date name="#p.samplingDate" format="MM月dd日HH:mm" /> <s:property
									value="#p.sensor.tower.line.lineName" /> <s:property
									value="#p.sensor.tower.towerCode" /> <s:property
									value="#p.warningInfo" /> </span>
							<s:url action="updateWarningState" namespace="/" var="Url">
								<s:param name="WarningId">
									<s:property value="#p.warningId" />
								</s:param>
							</s:url>
							<a href="${Url}" title="标识为【隐藏】"
								onclick="return confirm('隐藏后该 信息不再在告警信息栏显示，确定隐藏该信息吗?')">隐藏</a>
						</li>
					</s:iterator>
				</ul>
			</s:if>
			<s:else>				
				<div class="fontl_weather">
					<s:property value="#request.onlineMessage" /> 无报警信息
					<!-- 
				<s:property
					value="%{#session[@com.yixin.framework.login.web.LoginAction@SESSION_LOGIN_INFO].weatherInfoHtml}"
					escape="false" />
					 -->
				</div>
			</s:else>

		</div>
	</body>
</html>