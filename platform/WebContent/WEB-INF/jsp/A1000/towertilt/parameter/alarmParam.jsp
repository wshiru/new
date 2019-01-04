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
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />

		<link rel="stylesheet"
			href="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.css"
			type="text/css" />
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>/resource/module/jquery/jquery.cookie.js"></script>

		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.blockUI.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common2.js"></script>

		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<!-- start 页尾提示信息 -->


	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
				<span class="tab_position_b">终端报警阈值</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<div id="tabs" style="height: 100%;">
			<ul>
				<li>
					<a href="#tabs-4"><span>预警参数</span> </a>
				</li>
				<li>
					<a href="#tabs-5"><span>终端阈值</span> </a>
				</li>
			</ul>
			<div id="tabs-4">
				<s:form action="alarmParam" namespace="/towertilt/parameter"
					method="post" theme="simple">
					<s:hidden name="id" value="%{#request.id }" />
					<table align="center" cellspacing="1" class="tab">

						<tr>
							<td class="td_gray150">

							</td>
							<td class="table_td_left">
								顺线倾斜角（X方向）
							</td>
							<td class="table_td_left">
								横向倾斜角（Y方向 ）
							</td>
						</tr>
						<tr>
							<td class="td_gray150">
								一级预警
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleXAlarm1"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleXAlarm1"
									cssClass="cRed" />
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleYAlarm1"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleYAlarm1"
									cssClass="cRed" />
							</td>
						</tr>
						<tr>
							<td class="td_gray150">
								二级预警
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleXAlarm2"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleXAlarm2"
									cssClass="cRed" />
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleYAlarm2"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleYAlarm2"
									cssClass="cRed" />
							</td>
						</tr>
						<tr>
							<td class="td_gray150">
								三级预警
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleXAlarm3"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleXAlarm3"
									cssClass="cRed" />
							</td>
							<td class="table_td_left">
								<s:textfield name="towerTiltParameter.angleYAlarm3"
									maxlength="8" />
								°
								<s:fielderror fieldName="towerTiltParameter.angleYAlarm3"
									cssClass="cRed" />
							</td>
						</tr>
						</tr>
						<tr>
							<td class="table_td1_footer">
							</td>
							<td colspan="2" class="table_td2_footer">
								<s:submit value="保存" action="saveSensorParam" cssClass="btn6"
									onclick="return showwaiting();" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
			<div id="tabs-5">
				<s:form action="alarmParam" namespace="/towertilt/parameter"
					method="post" theme="simple">
					<s:hidden name="id" value="%{#request.id }" />
					<table align="center" cellspacing="1" class="tab">
						<s:if test="%{#request.params.size() == 0 }">
							<tr>
								<td class="td_gray150">
								</td>
								<td colspan="3" class="table_td_left">
									<span class="cRed">没有参数</span>
								</td>
							</tr>
						</s:if>
						<s:iterator value="params" var="arrayIndex" status="stat">
							<tr>
								<td class="td_gray150">
									<s:hidden name="params[%{#stat.index}].desc" />
									<s:property value="%{#request.params[#stat.index].desc}" />
								</td>
								<td colspan="3" class="table_td_left">
									<s:textfield name="params[%{#stat.index}].value" maxlength="15" />
									<s:hidden name="params[%{#stat.index}].name"
										value="params[%{#stat.index}].desc" />
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td class="table_td1_footer">
							</td>
							<td colspan="2" class="table_td2_footer">
								<s:submit value="召测" action="upAlarmParam" cssClass="btn6"
									onclick="return showwaiting();" />
								&nbsp;
								<s:submit value="下发" action="downAlarmParam" cssClass="btn6"
									onclick="return showwaiting();" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>

		<!-- end 信息区域 -->

		<jsp:include page="/WEB-INF/jsp/A1000/settings/block.jsp" />

		<script type="text/javascript">
	$(function() {
		// Tabs
		$('#tabs').tabs({
			cookie : {
				expires : 30
			}
		});
	});
</script>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>