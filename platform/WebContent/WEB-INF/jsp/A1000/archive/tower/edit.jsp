<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;杆塔管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">修改</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/archive/tower" method="post" theme="simple">
			<s:hidden name="tower.towerId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写杆塔信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>杆塔编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="tower.towerCode" maxLength="17" onblur="" />（英文或数字）
						<s:fielderror fieldName="tower.towerCode" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>所属线路：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="tower.line.lineId" list="%{#request.lines}" headerKey="" headerValue="--请选择--" listKey="lineId" listValue="lineName"></s:select>
						<s:fielderror fieldName="tower.line.lineId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>杆塔类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="tower.towerType.towerTypeId" list="%{#request.towerTypes}" headerKey="" headerValue="--请选择--" listKey="towerTypeId" listValue="towerTypeName"></s:select>
						<s:fielderror fieldName="tower.towerType.towerTypeId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						杆塔高度：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="tower.towerHeight" maxLength="11" onblur="" onkeyup="this.value = NumberUtil.format(this.value, 10, 2)" />（数字，且最多两位小数）
						<s:fielderror fieldName="tower.towerHeight" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						经&nbsp;&nbsp;&nbsp;&nbsp;度：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="tower.longitude" maxLength="11" onblur="" onkeyup="this.value = NumberUtil.format(this.value, 10, 2)" />（数字，且最多两位小数）
						<s:fielderror fieldName="tower.longitude" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						纬&nbsp;&nbsp;&nbsp;&nbsp;度：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="tower.latitude" maxLength="11" onblur="" onkeyup="this.value = NumberUtil.format(this.value, 10, 2)" />（数字，且最多两位小数）
						<s:fielderror fieldName="tower.latitude" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						所在地址：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="tower.address" maxLength="250" size="60" />（250个字符）
						<s:fielderror fieldName="tower.address" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="tower.towerDesc" cols="60" rows="6" />（250个字符）
						<s:fielderror fieldName="tower.towerDesc" cssClass="cRed" />
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