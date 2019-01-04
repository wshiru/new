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
		<s:form action="samplingLandslide" namespace="/landslide/realTime"
			method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						告警状态
					</td>
					<td class="table_td_left">
						<s:if test="%{NULL == #request.landslide }">

						</s:if>
						<s:elseif test="%{0 == #request.landslide.AlarmFlag }">
							非告警数据
						</s:elseif>
						<s:else>
							<span class="cRed">告警数据</span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						类型
					</td>
					<td class="table_td_left">
						<s:if test="%{#request.landslideParameter.xyType eq 1}">
							单轴
						</s:if>
						<s:elseif test="%{#request.landslideParameter.xyType eq 2}">
							双轴
						</s:elseif>
						<s:else>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						基岩深度
					</td>
					<td class="table_td_left">
						<s:property value="%{#request.landslide.allDepth }" />
						<s:if test="%{#request.landslide.allDepth != null}">
						米
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						监测点个数
					</td>
					<td class="table_td_left">
						<s:property value="%{#request.landslide.sampleNum }" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						监测点列表
					</td>
					<td class="table_td_left">
						<table class="list_table">
							<tr class="list_tr_head">
								<td width="120px">
									监测点号
								</td>
								<td width="120px">
									深度(米)
								</td>
								<td width="120px">
									X倾角(°)
								</td>
								<s:if test="%{#request.landslideParameter.xyType eq 2}">
									<td width="120px">
										Y倾角(°)
									</td>
								</s:if>

								<td width="120px">
									X方向位移(mm)
								</td>
								<s:if test="%{#request.landslideParameter.xyType eq 2}">
									<td width="120px">
										Y方向位移(mm)
									</td>
									<td width="120px">
										合位移(mm)
									</td>
								</s:if>
							</tr>
							<s:iterator value="#request.landslide.landslideSamplingDetails"
								status="rowstatus" var="p">
								<tr class="list_tr_datarow">
									<td class="list_tr_string">
										<s:property value="%{#p.pointNo }" />
									</td>
									<td class="list_tr_data">
										<s:property value="%{#p.relativeDepth }" />
									</td>
									<td class="list_tr_data">
										<s:property value="%{#p.angleX }" />
									</td>
									<s:if test="%{#request.landslideParameter.xyType eq 2}">
										<td class="list_tr_data">
											<s:property value="%{#p.angleY }" />
										</td>
									</s:if>
									<td class="list_tr_data">
										<s:text name="global.format.decimal2">
											<s:param value="#p.displacementX" />
										</s:text>
									</td>
									<s:if test="%{#request.landslideParameter.xyType eq 2}">
										<td class="list_tr_data">
											<s:text name="global.format.decimal2">
												<s:param value="#p.displacementY" />
											</s:text>
										</td>
										<td class="list_tr_data">
											<s:text name="global.format.decimal2">
												<s:param value="#p.displacement" />
											</s:text>
										</td>
									</s:if>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td class="table_td2_footer">
						<s:submit value="召测" action="samplingLandslide" cssClass="btn6"
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