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
				<span class="tab_position_b">传感器参数配置</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="sensorParam" namespace="/landslide/parameter"
			method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>类型
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="landslideParameter.xyType" list="%{#{'1':'单轴','2':'双轴'}}" />
						<s:fielderror fieldName="landslideParameter.xyType"
							cssClass="cRed" />
					</td>
				</tr>				
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>基岩深度
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="landslideParameter.allDepth" maxlength="5" />
						(单位：米)
						<s:fielderror fieldName="landslideParameter.allDepth"
							cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						测点个数
					</td>
					<td colspan="3" class="table_td_left">
						<s:hidden name="landslideParameter.sampleNum" />
						<span class="cRed"><s:property
								value="#request.landslideParameter.sampleNum" /> </span>
						个&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:submit value="增加" action="addSensorCount" cssClass="btn2" />
						<s:submit value="减少" action="decSensorCount" cssClass="btn2" />
					</td>
				</tr>				
				<tr>
					<td class="td_gray150">
						监测点列表
					</td>
					<td class="table_td_left">
						<table class="list_table">
							<tr class="list_tr_head">
								<td width="60px">
									监测点号
								</td>
								<td width="120px">
									深度(米)
								</td>
								<td width="120px">
									一级位移报警(mm)
								</td>
								<td width="120px">
									二级位移报警(mm)
								</td>
								<td width="120px">
									三级位移报警(mm)
								</td>								
							</tr>
							<s:iterator
								value="#request.landslideParameter.landslideParameterDetails"
								var="d" status="stat">
								<tr>
									<td style="text-align:center">
										<input type="hidden"
											name="landslideParameter.landslideParameterDetails[<s:property value="%{#stat.index}" />].pointNo"
											value="<s:property value="%{#d.pointNo }" />" />
										<s:property value="%{#d.pointNo }" />
									</td>
									<td>
										<input type="text"
											name="landslideParameter.landslideParameterDetails[<s:property value="%{#stat.index}" />].relativeDepth"
											value="<s:property value="%{#d.relativeDepth }" />" />
									<td>
										<input type="text"
											name="landslideParameter.landslideParameterDetails[<s:property value="%{#stat.index}" />].alarm1"
											value="<s:property value="%{#d.alarm1 }" />" />
									</td>
									<td>
										<input type="text"
											name="landslideParameter.landslideParameterDetails[<s:property value="%{#stat.index}" />].alarm2"
											value="<s:property value="%{#d.alarm2 }" />" />
									</td>
									<td>
										<input type="text"
											name="landslideParameter.landslideParameterDetails[<s:property value="%{#stat.index}" />].alarm3"
											value="<s:property value="%{#d.alarm3 }" />" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>


				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="保存" action="saveSensorParam" cssClass="btn6" />
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