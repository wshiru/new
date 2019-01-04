<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />

		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.blockUI.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common2.js"></script>

		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>

		<script type="text/javascript">

	function winFixSize() {

		var h = $("#realPlayer").width();
		$("#player").width(h - 220);
		//document.body.offsetHeight
		$("#player").height(document.body.offsetHeight - 16);
		//$("#VideoPlayer").height(document.body.offsetHeight);
	}
	window.onresize = winFixSize;
	$(document)
			.ready(
					function() {
						//$("#realPlayer").innerHtml = "<object width='352' height='288' classid='CLSID:0C615F36-0C1C-497B-B9E4-833B0D7AA8CA' />";
						//var myVideoPlayer = document.getElementById("VideoPlayer");
						try {
							/*
							var host = "<s:property value='#request.videoPlatform.platformHost' />";
							var port = <s:property value='#request.videoPlatform.platformPort' />;
							var username = "<s:property value='#request.videoPlatform.userName' />";
							var password = "<s:property value='#request.videoPlatform.password' />";
							*/
							


							
							var usedGateway = <s:property value='#request.videoPlatform.usedGateway' />;
							var usedDebug = <s:property value='#request.videoPlatform.usedDebug' />;
							
							var gatewayhost = "<s:property value='#request.videoPlatform.gatewayHost' />";
							var gatewayport = <s:property value='#request.videoPlatform.gatewayPort' />;
							
							var sensorCode = "<s:property value='#request.videoServer.sensor.sensorCode' />";
							var videoServerCode = "<s:property value='#request.videoServer.videoServerCode' />";
							var cameraCode = "<s:property value='#request.videoServer.cameraCode' />";
														
							/*												
							VideoPlayer.InitParams(host, port, username, password,usedGateway,
								gatewayhost , gatewayport , sensorCode, videoServerCode, cameraCode, usedDebug);
							*/			
							var host = "<s:property value='#request.videoServer.ip' />";
							var port = <s:property value='#request.videoServer.port' />;
							var username = "<s:property value='#request.videoServer.userName' />";
							var password = "<s:property value='#request.videoServer.password' />";
							var channelCount = <s:property value='#request.videoServer.cameraCount' />;							
							
							VideoPlayer.InitHKParams(gatewayhost , gatewayport , sensorCode, host, port, username, password,channelCount, usedDebug);							

						} catch (err) {
							alert("初始化失败:" + err.getMessage());
						}

					});
</script>
	</head>
	<body onload="winFixSize();" style="background-color: #cfcfcf;">

		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">视频实时监控</span>
		</div>
		<!-- end 当前位置 -->
		<!-- 
		<div
			style="height: 100%; width: 100%; margin: 0px; padding: 0px; background-color: #afcfcf;">
			<object id="VideoPlayer" name="VideoPlayer"
				classid="clsid:E48E8DDA-B807-4635-885D-D812C34EF09F" width="100%"
				height="100%" align=center hspace=0 vspace=0>
			</object>
		</div>		
		 -->
		<!-- start 信息区域 -->
		<div
			style="height: 100%; width: 100%; margin: 0px; padding: 0px; background-color: #afcfcf;">
			<object id="VideoPlayer" name="VideoPlayer"
				classid="clsid:6BCF6633-1B2B-4720-ACF7-F0E64C847344" width="100%"
				height="100%" align=center hspace=0 vspace=0>
			</object>
		</div>
		<!-- end 信息区域 -->

		<!-- <jsp:include page="/WEB-INF/jsp/A1000/settings/block.jsp" />
		start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>