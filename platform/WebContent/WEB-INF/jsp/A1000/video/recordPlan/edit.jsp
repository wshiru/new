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
			src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<script type="text/javascript">
	function hideDiv() {
		document.getElementById("div0").style.display = "none";
		document.getElementById("div1").style.display = "none";
		document.getElementById("div2").style.display = "none";
		document.getElementById("div3").style.display = "none";
		document.getElementById("div4").style.display = "none";
		document.getElementById("div5").style.display = "none";
		document.getElementById("div9").style.display = "none";
	}

	function otonchange(o) {
		var operatorTypeObj = document.getElementById("operatorType");
		var operatorType = operatorTypeObj.value;
		hideDiv();
		var name = "div" + operatorType;
		document.getElementById(name).style.display = "inline";
		return false;
	}
	function load() {
		hideDiv();
		otonchange(this);
	}
</script>
	</head>
	<body onload="load();">

		<!-- start 当前位置 -->
		<div id="tab_position">
			定时录像计划&nbsp;&gt;&nbsp;
			<span class="tab_position_b">修改</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/video/recordPlan" method="post"
			theme="simple">
			<s:hidden name="line.lineId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写计划信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>任务开始时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:hidden name="id" />					
						<s:hidden name="recordPlan.recordPlanId" />
						<input type="text" name="startTime"
							onclick="WdatePicker({dateFmt:'H:mm:ss'})" maxlength="15"
							value="<s:date name="#request.recordPlan.startTime" format="H:mm:ss" />"
							onfocus="WdatePicker({dateFmt:'H:mm:ss'})" onblur="" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>通道号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select id="channelNo" name="recordPlan.channelNo"
							list="%{#{'1':'通道1', '2':'通道2', '3':'通道3', '4':'通道4'}}"
							headerKey="" headerValue="----请选择----"
							/>
					</td>
				</tr>				
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>操作类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select id="operatorType" name="recordPlan.operatorType"
							list="%{#{'0':'打开电源', '1':'关闭电源', '2':'开始录像', '3':'截图', '4':'调用预置位', '5':'停止录像','9':'重启服务程序'}}"
							headerKey="" headerValue="----请选择----"
							onchange="otonchange(this);" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						参数：
					</td>
					<td colspan="3" class="table_td_left">
						<div id="div0">
							打开电源时间：
							<s:textfield name="recordPlan.openTime" maxLength="3"
								style="width:30px" />
							分钟
						</div>
						<div id="div1">
						</div>
						<div id="div2">
							录像时间：
							<s:textfield type="text" name="recordPlan.recordTime"
								maxLength="3" style="width:30px" />
							分钟
						</div>
						<div id="div3">

						</div>
						<div id="div4">
							调用预置位：
							<s:textfield type="text" name="recordPlan.presetting"
								maxLength="3" style="width:30px" />
						</div>
						<div id="div5">
						</div>
						<div id="div9">
						</div>						
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