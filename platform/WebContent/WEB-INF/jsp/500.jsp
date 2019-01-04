<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>错误页面</title>
		<jsp:include page="/WEB-INF/jsp/meta.jsp" />
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<script type="text/javascript">
			function showErrorInfo() {
				var divObj = document.getElementById("errorInfo_zone");
				if (divObj.style.display == "block") {
					divObj.style.display = "none";
				} else {
					divObj.style.display = "block";
				}
			}
		</script>
	</head>

	<body>
	<%
		//Exception from JSP didn't log yet ,should log it here.
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		org.apache.commons.logging.LogFactory.getLog(requestUri).error(exception.getMessage(), exception);
	%>
		<p style="color: red; font-size:24px;">
			系统发生内部错误
		</p>
		<a href="javascript:return false;" onclick="showErrorInfo()">显示/隐藏详细信息</a>
		<div id="errorInfo_zone" style="display:none;">
			<pre><%exception.printStackTrace(new java.io.PrintWriter(out));%></pre>
		</div>
	</body>
</html>
