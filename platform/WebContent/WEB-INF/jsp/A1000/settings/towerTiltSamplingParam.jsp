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

		<script type="text/javascript" src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/jquery/jquery.blockUI.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/common2.js"></script>

		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
				<span class="tab_position_b">终端采样间隔</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="towerTiltSamplingParam" namespace="/device/settings" method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>采集时间周期
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="samplingParam.mainTime" maxlength="5" onkeyup="this.value = NumberUtil.format(this.value, 5, 0)" />
						(单位：分钟。1到65535间的整数)
						<s:fielderror fieldName="samplingParam.mainTime" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>高速采样点数
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="samplingParam.sampleCount" maxlength="5" onkeyup="this.value = NumberUtil.format(this.value, 5, 0)" />
						(1到65535间的整数)
						<s:fielderror fieldName="samplingParam.sampleCount" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>高速采样频率
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="samplingParam.sampleFrequency" maxlength="5" onkeyup="this.value = NumberUtil.format(this.value, 5, 0)" />
						(单位：赫兹。1到65535间的整数)
						<s:fielderror fieldName="samplingParam.sampleFrequency" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="召测" action="upTowerTiltSamplingParam" cssClass="btn6" onclick="return showwaiting();" />
						&nbsp;
						<s:submit value="下发" action="downTowerTiltSamplingParam" cssClass="btn6" onclick="return showwaiting();" />
					</td>
				</tr>
			</table>
		</s:form>
		<!-- end 信息区域 -->
		
		<jsp:include page="/WEB-INF/jsp/A1000/settings/block.jsp" />
		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>