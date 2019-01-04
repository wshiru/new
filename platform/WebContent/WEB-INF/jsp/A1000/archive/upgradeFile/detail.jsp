<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link rel="stylesheet" href="${basePath }/resource/common/css/global.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;升级文件管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">详细信息</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="list" namespace="/archive/upgradeFile" method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						升级文件信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						监测类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.sensorType.sensorTypeName" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						文件名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.fileName" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						文件后缀：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.fileExtension" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						版本号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.version" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						版本发布时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="#request.upgradeFile.releaseTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						上传时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="#request.upgradeFile.uploadTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						上传用户：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.upgradeFile.upgradeFileDesc" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="返 回" action="list" cssClass="btn4" />
					</td>
				</tr>
			</table>
		</s:form>
		<!-- end 信息区域 -->

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>