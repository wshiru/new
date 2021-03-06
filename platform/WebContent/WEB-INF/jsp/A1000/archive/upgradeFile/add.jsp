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
		<script type="text/javascript">
			function checkForm(formObj) {
				if (formObj.updateFile.value == undefined || formObj.updateFile.value.length <= 0) {
					alert("请先选择要上传的文件");
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;升级文件管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">新增</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="addSave" namespace="/archive/upgradeFile" method="post" enctype="multipart/form-data" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写升级文件信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>监测类型：
					</td>
					<td class="table_td_left">
						<s:select name="upgradeFile.sensorType.sensorTypeId" list="%{#request.sensorTypes}" headerKey="" headerValue="--请选择--" listKey="sensorTypeId" listValue="sensorTypeName"></s:select>
						<s:fielderror fieldName="upgradeFile.sensorType.sensorTypeId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>版本号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="upgradeFile.version" maxLength="30" onblur="" />（如，1.0.2）
						<s:fielderror fieldName="upgradeFile.version" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>版本发布时间：
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="upgradeFile.releaseTime" maxlength="20" value='<s:date name="%{#request.upgradeFile.releaseTime }" format="yyyy-MM-dd HH:mm:ss"/>' onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
						<s:fielderror fieldName="upgradeFile.releaseTime" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="upgradeFile.upgradeFileDesc" cols="60" rows="6" />（250个字符）
						<s:fielderror fieldName="upgradeFile.upgradeFileDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						文件选择：
					</td>
					<td colspan="3" class="table_td_left">
						<s:file name="updateFile" />
						<s:fielderror fieldName="upgradeFile.upgradeFileDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="addSave" cssClass="btn4" onclick="return checkForm(this.form)" />
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