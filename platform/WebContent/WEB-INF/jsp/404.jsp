<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>错误页面</title>
		<jsp:include page="/WEB-INF/jsp/meta.jsp" />
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<link rel="stylesheet" href="resource/css/global.css" type="text/css" />
		<link rel="stylesheet" href="resource/css/main_zone.css" type="text/css" />
		<script type="text/javascript" src="resource/js/common.js"></script>
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
		<p style="color: red;">
			页面未找到
		</p>
	</body>
</html>