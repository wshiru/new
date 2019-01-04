<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/doubleSelect.js"></script>
		<script language="JavaScript" type="text/javascript">
	
</script>



	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			现场安装图片管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b">修改</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/archive/picture" method="post"
			enctype="multipart/form-data" theme="simple">
			<s:hidden name="id" value="%{#request.sensor.sensorId}" />
			<s:hidden name="sensor.sensorId" />
			<s:hidden name="picture.pictureId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写图片信息及选择图片上传
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>所在杆塔：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.tower.line.lineName" />
						-
						<s:property value="#request.sensor.tower.towerCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>所属监测类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.sensor.sensorType.sensorTypeName" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						图片类型:
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="picture.defaultShow" list="%{#{'1':'现场图片','2':'安装方位','0':'其它'}}" />
						<s:fielderror fieldName="picture.defaultShow" cssClass="cRed" />
					</td>				
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>标题：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="picture.caption" maxLength="50" onblur="" />
						（中英文或数字）
						<s:fielderror fieldName="picture.caption" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						原图片：
					</td>
					<td colspan="3" class="table_td_left">
						<img
							src="${basePath }<s:property value="#request.picture.fileName" />"
							width="400px" heigth="300px" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						选择新图片文件：
					</td>
					<td colspan="3" class="table_td_left">
						<s:file name="upload" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="picture.pictureDesc" cols="60" rows="6" />
						（250个字符）
						<s:fielderror fieldName="picture.pictureDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="editSave" cssClass="btn4" />
						&nbsp;
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