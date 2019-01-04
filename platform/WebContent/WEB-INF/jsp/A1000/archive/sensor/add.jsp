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
		<script type="text/javascript" src="${basePath }/resource/common/js/doubleSelect.js"></script>
		<script language="JavaScript" type="text/javascript">
			<%@ include file="/WEB-INF/jsp/A1000/archive/sensor/doubleSelect.jsp"%>
		</script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			档案管理&nbsp;&gt;&nbsp;监测装置管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">新增</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="addSave" namespace="/archive/sensor" method="post" theme="simple">
			<s:hidden name="sensor.sensorId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写监测装置信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>监测装置编码：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="sensor.sensorCode" maxLength="17" onblur="" />（英文或数字）
						<s:fielderror fieldName="sensor.sensorCode" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>被监测装置编码：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="sensor.bySensorCode" maxLength="17" onblur="" />（英文或数字）
						<s:fielderror fieldName="sensor.bySensorCode" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>所在杆塔：
					</td>
					<td colspan="3" class="table_td_left">
						<select id="lineSelect" name="sensor.tower.line.lineId"  onchange="ChangeSelect(this.value,'towerSelect','', dataSource, '--杆塔--' )" style="width:expression(this.offsetWidth>100?'auto':'100')"></select>
						<select id="towerSelect" name="sensor.tower.towerId" onchange="" style="width:expression(this.offsetWidth>100?'auto':'100')"></select>
						<script type="text/javascript">ChangeSelect('0','lineSelect',"",dataSource, "--线路--",true) </script>
						<script type="text/javascript">ChangeSelect('0','lineSelect','<s:property value="%{#request.sensor.tower.line.lineId}" escape="false" />',dataSource, "--线路--",true) </script>
						<script type="text/javascript">ChangeSelect('<s:property value="%{#request.sensor.tower.line.lineId}" escape="false" />','towerSelect','<s:property value="%{#request.sensor.tower.towerId}" escape="false" />',dataSource, "--杆塔--",true) </script>
						<s:fielderror fieldName="sensor.tower.line.lineId" cssClass="cRed" />
						<s:fielderror fieldName="sensor.tower.towerId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>所属监测类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="sensor.sensorType.sensorTypeId" list="%{#request.sensorTypes}" headerKey="" headerValue="--请选择--" listKey="sensorTypeId" listValue="sensorTypeName"></s:select>
						<s:fielderror fieldName="sensor.sensorType.sensorTypeId" cssClass="cRed" />
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>状&nbsp;&nbsp;&nbsp;&nbsp;态：
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="sensor.state" list="%{#{'1':'启用','0':'停用'}}" />
						<s:fielderror fieldName="sensor.state" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						生产厂商：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="sensor.manuFacturer" maxLength="50" onblur="" />（中英文或数字）
						<s:fielderror fieldName="sensor.manuFacturer" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						出厂日期：
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="sensor.productionDate" maxlength="20" value='<s:date name="%{#request.sensor.installDate}" format="yyyy-MM-dd"/>' onclick="WdatePicker()" onfocus="WdatePicker()" readonly="readonly" />
						<s:fielderror fieldName="sensor.productionDate" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						出厂编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="sensor.identificationNumber" maxLength="30" onblur="" />（英文或数字或“-”）
						<s:fielderror fieldName="sensor.identificationNumber" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						安装位置：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="sensor.installSite" maxLength="50" onblur="" />
						<s:fielderror fieldName="sensor.installSite" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						描&nbsp;&nbsp;&nbsp;&nbsp;述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="sensor.sensorDesc" cols="60" rows="6" />（250个字符）
						<s:fielderror fieldName="sensor.sensorDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="addSave" cssClass="btn4" />
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