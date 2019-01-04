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
			<span class="tab_position_b">实时数据抄读</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="samplingContamination"
			namespace="/contamination/realTime" method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<!-- 
				<tr>
					<td class="td_gray150">
						参数名
					</td>
					<td class="table_td_left">
						最后采样数据
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						采集时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#request.contamination.samplingTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						入库时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#request.contamination.createTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				
				 -->

				<tr>
					<td class="td_gray150">
						告警状态
					</td>
					<td class="table_td_left">
						<s:if test="%{NULL == #request.contamination }">

						</s:if>
						<s:elseif test="%{0 == #request.contamination.AlarmFlag }">
							非告警数据
						</s:elseif>

						<s:else>
							<span class="cRed">告警数据</span>
						</s:else>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						盐密
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.esdd != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.contamination.esdd" />
							</s:text>	
							 mg/cm2
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						灰密
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.nsdd != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.contamination.nsdd" />
							</s:text>						
							 mg/cm2
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						日最高温度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.dailyMaxTemperature != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.contamination.dailyMaxTemperature" />
							</s:text>							
							℃
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						日最低温度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.dailyMinTemperature != null}">
							<s:text name="global.format.decimal2">
								<s:param value="#request.contamination.dailyMinTemperature" />
							</s:text>							
							℃
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						日最大湿度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.dailyMaxHumidity != null}">
							<s:text name="global.format.decimal2">
								<s:param value="#request.contamination.dailyMaxHumidity" />
							</s:text>							
							％RH
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						日最小湿度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.contamination.dailyMinHumidity != null}">
							<s:text name="global.format.decimal2">
								<s:param value="#request.contamination.dailyMinHumidity" />
							</s:text>							
							％RH
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td class="table_td2_footer">
						<s:submit value="召测" action="samplingContamination"
							cssClass="btn6" onclick="return showwaiting();" />
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