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
			档案管理&nbsp;&gt;&nbsp;线路管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">修改</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/archive/line" method="post" theme="simple">
			<s:hidden name="line.lineId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写线路信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>线路编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="line.lineCode" maxLength="30" onblur="" />（英文或数字）
						<s:fielderror fieldName="line.lineCode" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>线路名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="line.lineName" maxLength="50" onblur="" />（中英文或数字）
						<s:fielderror fieldName="line.lineName" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						电压等级：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="line.voltageLevel.dictionaryId" list="%{#request.voltageLevels}" headerKey="" headerValue="--请选择--" listKey="dictionaryId" listValue="name"></s:select>
						<s:fielderror fieldName="line.voltageLevel.dictionaryId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						长&nbsp;&nbsp;&nbsp;&nbsp;度：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="line.length" maxlength="8" onkeyup="this.value = NumberUtil.format(this.value, 10, 2)" />（数字，且最多两位小数）
						<s:fielderror fieldName="line.length" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						起&nbsp;&nbsp;&nbsp;&nbsp;点：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="line.lineStart" maxLength="50" size="60" />
						<s:fielderror fieldName="line.lineStart" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						终&nbsp;&nbsp;&nbsp;&nbsp;点：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="line.lineEnd" maxlength="50" size="60" />
						<s:fielderror fieldName="line.lineEnd" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="line.lineDesc" cols="60" rows="6" />（250个字符）
						<s:fielderror fieldName="line.lineDesc" cssClass="cRed" />
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