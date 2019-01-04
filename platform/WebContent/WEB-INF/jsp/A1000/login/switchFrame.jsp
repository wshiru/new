<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>

		<link
			href="${basePath }/resource/theme/${userTheme }/css/switchFrame.css"
			type="text/css" rel="stylesheet" />

		<script type="text/javascript">
	function Submit_onclick() {
		var frameSet = parent.document.getElementById("bottomFrameSet");
		if (frameSet) {
			if (frameSet.cols == "235,8,*") {
				frameSet.cols = "0,8,*";
				document.getElementById("ImgArrow").src = '${basePath }/resource/theme/${userTheme }/images/switchFrame/center_navpoint2.png';
				document.getElementById("ImgArrow").alt = "打开左侧导航栏";
			} else {
				frameSet.cols = "235,8,*";
				document.getElementById("ImgArrow").src = '${basePath }/resource/theme/${userTheme }/images/switchFrame/center_navpoint.png';
				document.getElementById("ImgArrow").alt = "隐藏左侧导航栏";
			}
		}
	}

	function MyLoad() {
		if (window.parent.location.href.indexOf("MainUrl") > 0) {
			window.top.hSwitchFrame.document.getElementById("ImgArrow").src = '${basePath }/resource/theme/${userTheme }/images/switchFrame/center_navpoint2.png';
		}
	}
</script>

	</head>

	<body >
		<table  width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" style="table-layout: fixed;">
			<tr>
				<td  class="switch_bg" width="8" valign="middle" 
					onclick="Submit_onclick()">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
						</tr>
						<tr>
							<td>							 
								<span class="navPoint" ><img
										src="${basePath}/resource/theme/${userTheme }/images/switchFrame/center_navpoint.png"
										name="img1" alt="隐藏左侧导航栏" id="ImgArrow">
								</span>								 
							</td>
						</tr>
						<tr>

						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
