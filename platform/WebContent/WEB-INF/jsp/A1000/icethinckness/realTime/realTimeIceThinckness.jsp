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
		<s:form action="samplingIceThinckness" namespace="/icethinckness/realTime"
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
						<s:date name="%{#request.iceThinckness.samplingTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						入库时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#request.iceThinckness.createTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				
				 -->

				<tr>
					<td class="td_gray150">
						告警状态
					</td>
					<td class="table_td_left">
						<s:if test="%{NULL == #request.iceThinckness }">

						</s:if>
						<s:elseif test="%{0 == #request.iceThinckness.AlarmFlag }">
							非告警数据
						</s:elseif>

						<s:else>
							<span class="cRed">告警数据</span>
						</s:else>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						等值覆冰厚度
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.iceThinckness.equalIceThickness != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.iceThinckness.equalIceThickness" />
							</s:text>	
							mm
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						综合悬挂载荷
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.iceThinckness.tension != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.iceThinckness.tension" />
							</s:text>						
							N
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						不均衡张力差
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.iceThinckness.tensionDifference != null}">
							<s:text name="global.format.decimal1">
								<s:param value="#request.iceThinckness.tensionDifference" />
							</s:text>							
							N
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						绝缘子串风偏角
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.iceThinckness.windageYawAngle != null}">
							<s:text name="global.format.decimal2">
								<s:param value="#request.iceThinckness.windageYawAngle" />
							</s:text>							
							°
						</s:if>
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						绝缘子串偏斜角
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.iceThinckness.deflectionAngle != null}">
							<s:text name="global.format.decimal2">
								<s:param value="#request.iceThinckness.deflectionAngle" />
							</s:text>							
							°
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td class="table_td2_footer">
						<s:submit value="召测" action="samplingIceThinckness" cssClass="btn6"
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