<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/menuFrame.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/module/systree/systree.css" />
		<script type="text/javascript"
			src="${basePath }/resource/module/dtree2/dtree.js"></script>

		<script type="text/javascript">
	var checkid = '';
	//单击菜单置为选中状态

	function changimg(obj) {
		<s:iterator value="#session[@com.yixin.framework.login.web.LoginAction@SESSION_LOGIN_INFO].leftMenuAuthResourceList" var="info">
		var src = '${basePath }/resource/theme/${userTheme }/images/menuFrame/<s:property value="#info.icon"/>';
		document
				.getElementById('img<s:property value="#info.authResourceId"/>').src = src;
		var newsrc = src.slice(0, src.length - 5) + '2.png';
		if (obj.src == src) {
			obj.src = newsrc;
			checkid = obj.id;
		}
		</s:iterator>
	}

	function fixHeight() {
		var bodyHeight = document.body.offsetHeight;
		var div1Height = document.getElementById("nav_fix").offsetHeight;
		//var div2Height = document.getElementById("nav").offsetHeight;
		var div3 = document.getElementById("menu_trees");
		div3.style.height = (bodyHeight - div1Height) + "px";
	}

	//调整窗口大小时改变树的高度
	window.onresize = fixHeight;
</script>
	</head>

	<body onload="fixHeight();">
		<div id="nav_fix">
			<div class="nav_img">
				<div class="nav_text" id="nav_text">
					线路监控
				</div>
			</div>

			<div class="nav_treetype" sytle="">
				<a href="${basePath }/menuFrame.jspx?treeType=1" target=""><img
						src="${themeImagesPath }/menuFrame/device.gif" />按监测类型分组</a>
				<a href="${basePath }/menuFrame.jspx?treeType=0" target=""> <img
						src="${themeImagesPath }/menuFrame/line1.gif" /> 按线路分组 </a>
				<hr />
			</div>

		</div>
		<!-- 
		<img src="${basePath }/resource/theme/${userTheme }/images/menuFrame/line_tree.png"/>
		<s:property value="%{#request.leftMenuHtml}" escape="false" />
		<div class="menu_treestop"></div>
		<div class="menu_treesbtm">ssssss</div>
		树菜单 -->
		<div class="menu_trees" id="menu_trees">
			<script type="text/javascript">
	function iniImages() {
		if (checkid != '') {
			var src = document.getElementById(checkid).src;
			var newsrc = src.slice(0, src.length - 5) + '1.png';
			document.getElementById(checkid).src = newsrc;
			checkid = '';
		}
	}

	tree = new dTree('tree');
	tree.config.target = "mainMenuFrame";
	tree.config.imageDir = '${basePath }/resource/module/systree/img';

	tree.reSetImagePath();
	<s:iterator value="#request.treeInfos" var="tree">
	tree
			.add(
					'<s:property value="#tree.id" escape="false" />',
					'<s:property value="#tree.pid" escape="false" />',
					'<s:property value="#tree.name" escape="false" />',
					'<s:property value="#tree.url" escape="false" />',
					'<s:property value="#tree.title" escape="false" />',
					'<s:property value="#tree.target" escape="false" />',
					'${basePath}/resource/module/systree/img/<s:property value="#tree.icon" escape="false" />',
					'${basePath}/resource/module/systree/img/<s:property value="#tree.icon" escape="false" />');
	</s:iterator>
	document.write(tree);
	tree.openAll();

	//在线监测 --> 更新在线状态
</script>

		</div>

	</body>
</html>