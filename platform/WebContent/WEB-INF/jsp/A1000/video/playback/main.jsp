<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<meta name="viewport" content="width=device-width">
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


		<script
			src="<%=basePath%>/resource/module/fotorama-4.5.1/jquery-1.10.2.min.js"></script>
		<!-- Fotorama -->
		<link href="<%=basePath%>/resource/module/fotorama-4.5.1/fotorama.css"
			rel="stylesheet" />
		<script src="<%=basePath%>/resource/module/fotorama-4.5.1/fotorama.js"></script>


	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">视频实时监控</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 700/467 -->

		<div style="float: left; padding: 0px; margin: 0px; width: 100%; height: 100;">
			<div
				style="left:20px;height: 0px; position: absolute; z-index: 2; margin: 30px;">
				<div class="bat" id="bat4">
					<a
						href="${basePath }/video/realtimeVideo/list.jspx?id=<s:property value="#request.id" />"
						target="mainFrame" onclick="showHideMenu(true);"
						onfocus="this.blur()"> <img
							src="${basePath }/resource/theme/${userTheme }/images/realVidel.png" />
						<div>
							实时视频监控
						</div> </a>
				</div>

				<div class="bat" id="bat4">
					<a
						href="${basePath }/video/playback/list.jspx?id=<s:property value="#request.id" />"
						target="mainFrame" onclick="showHideMenu(true);"
						onfocus="this.blur()"> <img
							src="${basePath }/resource/theme/${userTheme }/images/hisVideo.png" />
						<div>
							历史视频查询
						</div> </a>
				</div>
			</div>
			<!-- 
	 		 700/467
	 		 1000/440
	 		 -->
			<div style="padding: 0px; margin: 20px;">
			<div class="fotorama" data-width="100%" data-ratio="600/240"
				data-max-width="100%" data-nav="thumbs">
				<s:iterator value="#request.page.records" status="rowstatus" var="p">
					<a
						href="${basePath }/resource/videofile/<s:property
																		value="#p.fileName" />"><img
							width="160px" height="120px"
							src="${basePath }/resource/videofile/<s:property
																		value="#p.fileName" />" />
					</a>
				</s:iterator>
			</div>
			</div>

		</div>




		<!-- end 信息区域 -->

		<!-- end 信息区域 -->



		<!-- <jsp:include page="/WEB-INF/jsp/A1000/settings/block.jsp" />
		start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>