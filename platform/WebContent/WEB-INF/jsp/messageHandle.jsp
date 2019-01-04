<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<script type="text/javascript"
	src="${basePath }/resource/module/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath }/resource/module/jquery/jquery.messager.js"></script>
<!-- start 消息处理 -->

<s:if test="#request.errorMessage != null">
	<script type="text/javascript">
	$(function() {
		var msg ="${errorMessage}";
		alert(msg);
	});
</script>
</s:if>
<s:if test="#request.succMessage != null">
	<script type="text/javascript">
	$(function() {
		var msg ="${succMessage}";		
		$.messager.lays(160, 70);
		$.messager.anim('fade', 'normal');		
		$.messager.show("提示信息", msg, 3000);
	});
</script>
</s:if>
<script type="text/javascript">
	$(function() {		 
		var pathDiv = parent.window.document.getElementById("pathDiv");
		var tab_position = document.getElementById("tab_position");
		if (tab_position == null || tab_position.innerHTML == "") {
			pathDiv.innerHTML = "";
		} else {
			pathDiv.innerHTML = "&nbsp;&gt;&nbsp;" + tab_position.innerHTML;
		}		
	});
</script>
