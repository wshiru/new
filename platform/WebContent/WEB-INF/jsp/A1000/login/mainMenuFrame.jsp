<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>主窗口导航菜单页面</title>
		<link
			href="${basePath}/resource/theme/${userTheme }/css/mainMenuFrame.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.messager.js"></script>

		<script type="text/javascript">
	var mainFrameHeight = 0;
	function showMenuImg() {
		if (key.innerHTML == "显示菜单") {
			document.getElementById("showMenuImg").src = "${basePath}/resource/theme/${userTheme }/images/mainMenuFrame/navtopico.png";
		} else {
			document.getElementById("showMenuImg").src = "${basePath}/resource/theme/${userTheme }/images/mainMenuFrame/navtopico1.png";
		}
	}
	function showHideMenu(manual) {
		var key = document.getElementById("key");
		document.getElementById("div_subnavDetail").className = "";
		document.getElementById("div_mainDetail").className = "";
		document.getElementById("pathDiv").className = "";
		if (key.innerHTML == "显示菜单") {
			//document.getElementById("div_subnavDetail").className = "div_mainMenu_Show";
			//document.getElementById("div_mainDetail").className = "div_mainMenu_Hide";
			document.getElementById("pathDiv").className = "div_mainMenu_Hide";
			key.innerHTML = "隐藏菜单";
			$("#div_subnavDetail").animate({
				height : '100%',
				width : '100%',
				opacity : '1'
			}, "slow")
			$("#div_subnavDetail").fadeIn(3);
			$("#div_mainDetail").fadeOut(300);

		} else {
			//document.getElementById("div_subnavDetail").className = "div_mainMenu_Hide";
			if (!manual) {
				var ifm = document.getElementById("mainFrame");
				var iDoc = ifm.contentDocument || ifm.contentWindow.document;
				iDoc.body.innerHTML = "加载中...";
			}
			//document.getElementById("div_mainDetail").className = "div_mainMenu_Show";

			document.getElementById("pathDiv").className = "div_mainMenu_Show";
			key.innerHTML = "显示菜单";

			$("#div_subnavDetail").animate({
				height : '10px',
				width : '100%',
				opacity : '1'
			}, "slow")
			$("#div_subnavDetail").fadeOut(3);
			$("#div_mainDetail").fadeIn(300);

		}
		showMenuImg();
	}

	function hideMenu() {
 
		var key = document.getElementById("key");
		document.getElementById("div_subnavDetail").className = "";
		document.getElementById("div_mainDetail").className = "";
		document.getElementById("div_subnavDetail").className = "div_mainMenu_Hide";
		document.getElementById("div_mainDetail").className = "div_mainMenu_Show";
		document.getElementById("pathDiv").className = "div_mainMenu_Show";
		key.innerHTML = "显示菜单";
		//$("#div_subnavDetail").fadeOut(30);
		//$("#div_mainDetail").fadeIn(300);
		showMenuImg();
	}

	function showMenu() {
		var key = document.getElementById("key");
		document.getElementById("div_subnavDetail").className = "";
		document.getElementById("div_mainDetail").className = "";
		document.getElementById("div_mainDetail").className = "div_mainMenu_Hide";
		document.getElementById("div_subnavDetail").className = "div_mainMenu_Show";
		document.getElementById("pathDiv").className = "div_mainMenu_Show";
		key.innerHTML = "隐藏菜单";
		showMenuImg();
		//$("#div_subnavDetail").fadeIn(3000);
	}

	function init() {
		try {
			var bHeight = document.body.scrollHeight;
			var dHeight = document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			var iframeDiv = document.getElementById("div_mainDetail");
			var iframe = document.getElementById("mainFrame");
			var getHeight = height - 24;
			//只有窗口高度被用户改变的情况下才改变
			if (mainFrameHeight != getHeight) {
				iframeDiv.style.height = getHeight + "px";
				iframe.style.height = getHeight + "px";
				mainFrameHeight = getHeight;
			}
		} catch (ex) {
		}
	}
</script>
		<style type="text/css">
.menu3 {
	float: left;
	width: 125px;
	height: 100px;
	margin: 5px 0 0 0px !important; *+
	margin: 5px 0 0 0px;
	text-align: center;
	font-weight: bold;
	color: #003f45;
	font-size: 12px;
	line-height: 20px; *+
	line-height: 22px;
}
</style>
	</head>

	<body onload="init()">
		<div id="div_mainMenu">
			<div class="navtop">
				<span class="navtop_home"><a
					href="${basePath}/checkSysMenu.jspx?sensorId=<s:property
						value="%{#request.sensorId}"/>"><img
							src="${basePath}/resource/theme/${userTheme }/images/mainMenuFrame/btn_home.png"
							alt="" />
						<div>
							主页
						</div> </a> </span>

				<s:if test="#request.sensorMenu eq 1">
					<span class="navtop_devicetype"><a
						href="${basePath}/showGroupPage.jspx?sensorTypeId=<s:property
						value="%{#request.sensorTypeId}"/>&checkMenu=1">
							<div>
								&nbsp;&gt;&nbsp;
								<s:property value="%{#request.sensorTypeName}" escape="false" />
							</div> </a> </span>
				</s:if>
				<span id="pathDiv"></span>

				<s:if test="%{#request.systemMenu neq NULL}">
					<span class="navtop_l"> <img id="showMenuImg"
							src="${basePath}/resource/theme/${userTheme }/images/mainMenuFrame/navtopico.png"
							alt="" /><a id="key" href="#"
						onclick="return showHideMenu(true);">显示菜单</a> </span>
				</s:if>
			</div>

			<div id="div_subnavDetail" border="0">
				<table border="0"
					style="border-spacing: 0px; left: 0px; width: 100%; height: auto;">
					<s:iterator value="#request.systemMenu" status="rowstatus" var="p">
						<tr  style="border-spacing: 0px;">
							<td class="menu2_box">
								<div class="menu2_title">
									<s:property value="#p.menuTypeName" />
								</div>
							</td>
							<td style="width:auto">
								<div class="menu2">
									<s:iterator value="#p.authResuorces" status="rowstatus" var="a">
										<div class="bat" id="bat4">
											<a
												href="${basePath}/<s:property value="#a.url" />?id=<s:property value="%{#request.sensorId}" />"
												target="mainFrame" onclick="showHideMenu(true);"
												onfocus="this.blur()"> <img
													src="${basePath}/resource/theme/${userTheme }/images/<s:property value="#a.icon" />" />
												<div> <s:property value="#a.name" /> </div></a>
										</div>
									</s:iterator>
								</div>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>



			<div id="div_mainDetail">
				<s:if
					test="%{#request.defaultOpenSrc == NULL || #request.defaultOpenSrc eq ''}">
					<iframe src="" id="mainFrame" name="mainFrame" width="100%"
						height="100%" frameborder="0" scrolling="auto"></iframe>
				</s:if>
				<s:else>
					<iframe
						src="${basePath}<s:property
						value="%{#request.defaultOpenSrc}" escape="false" />"
						id="mainFrame" name="mainFrame" width="100%" height="100%"
						frameborder="0" scrolling="auto"></iframe>
				</s:else>
			</div>
			<br class="clear_b" />
		</div>
		<s:if test="%{#request.checkMenu eq 1}">
			<script type="text/javascript">
	showMenu();
</script>
		</s:if>
		<s:else>
			<script type="text/javascript">
	document.getElementById("div_mainDetail").className = "div_mainMenu_Hide";
	document.getElementById("div_subnavDetail").className = "div_mainMenu_Show";
	hideMenu();
</script>
		</s:else>

		<script type="text/javascript">
	//编辑区自适应
	var timer = setInterval("init();", 300);
</script>

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->

	</body>

</html>

