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
			src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/jquery/jquery.blockUI.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common2.js"></script>

		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/common/js/NumberUtil.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
				<span class="tab_position_b">上传历史数据</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="requestUploadData" namespace="/device/settings"
			method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>开始时间
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="requestHistoryData.beginTime"
							value='<s:date name="requestHistoryData.beginTime" format="yyyy-MM-dd HH:mm:ss"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>结束时间
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="requestHistoryData.endTime"
							value='<s:date name="requestHistoryData.endTime" format="yyyy-MM-dd HH:mm:ss"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>

				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="上传数据" action="requestUploadData" cssClass="btn6"
							onclick="return showwaiting();" />
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