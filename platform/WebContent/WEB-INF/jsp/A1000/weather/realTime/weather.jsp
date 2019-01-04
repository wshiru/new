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
		<s:form action="addRealTimeTask" namespace="/weather/realTime"
			method="post" theme="simple">
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
						<s:date name="%{#request.realTimeDetail.lastData.samplingTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						入库时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#request.realTimeDetail.lastData.createTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				 -->

				<tr>
					<td class="td_gray150">
						告警状态
					</td>
					<td class="table_td_left">
						<s:if test="%{NULL == #request.weather }">
						</s:if>
						<s:elseif test="%{0 == #request.weather.AlarmFlag }">
							非告警数据
						</s:elseif>
						<s:else>
							<span class="cRed">告警数据</span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						10分钟平均风速
					</td>
					<td class="table_td_left">

						<s:if test="%{#request.weather.averageWindSpeed10min != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.averageWindSpeed10min" />
							</s:text>						
							
							m/s
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						10分钟平均风向
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.averageWindDirection10min != null}">
							<s:text name="global.format.decimal0">
								<s:param value="#request.weather.averageWindDirection10min" />
							</s:text>
							 °
						</s:if>
					</td>
				</tr>


				<tr>
					<td class="td_gray150">
						最大风速
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.maxWindSpeed != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.maxWindSpeed" />
							</s:text>						
							m/s
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						极大风速
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.extremeWindSpeed != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.extremeWindSpeed" />
							</s:text>						
							m/s
						</s:if>
					</td>
				</tr>


				<tr>
					<td class="td_gray150">
						标准风速
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.strandrdWindSpeed != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.strandrdWindSpeed" />
							</s:text>					
							m/s
						</s:if>
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="td_gray150">
						风速
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.strandrdWindSpeed != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.windSpeed" />
							</s:text>					
							m/s
						</s:if>
					</td>
				</tr>				

				 -->

				<tr>
					<td class="td_gray150">
						气温
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.temperature != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.temperature" />
							</s:text>						
							℃
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						湿度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.humidity != null}">
							<s:text name="global.format.decimal0">
								<s:param value="#request.weather.humidity" />
							</s:text>					
							%
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						气压
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.airPressure != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.airPressure" />
							</s:text>							
							hPa
						</s:if>
					</td>
				</tr>


				<tr>
					<td class="td_gray150">
						降雨量
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.dailyRainfall != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.dailyRainfall" />
							</s:text>							
							mm
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						降水强度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.precipitationIntensity != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.weather.precipitationIntensity" />
							</s:text>							
							mm/min
						</s:if>
					</td>
				</tr>


				<tr>
					<td class="td_gray150">
						光辐射强度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.weather.radiationIntensity != null}">
							<s:text name="global.format.decimal0">
								<s:param value="#request.weather.radiationIntensity" />
							</s:text>							
							W/m2
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td class="table_td2_footer">
						<s:submit value="召测" action="samplingWeather" cssClass="btn6"
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