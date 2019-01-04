<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<script type="text/javascript">
			function confirmSubmit() {
				return confirm("点击‘确定’将建立新任务，并取消’正在执行‘的任务；否则请点击‘取消’");
			}
		</script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;实时数据&nbsp;&gt;&nbsp;
				<span class="tab_position_b">相间风偏抄读</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="addRealTimeTask" namespace="/whiteMonsoon/realTime" method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
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
				<tr>
					<td class="td_gray150">
						风偏角
					</td>
					<td class="table_td_left">
						<s:property value="%{#request.realTimeDetail.lastData.windAngle }" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						倾斜角
					</td>
					<td class="table_td_left">
						<s:property value="%{#request.realTimeDetail.lastData.angle }" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						最小电气间隙参数
					</td>
					<td class="table_td_left">
						<s:property value="%{#request.realTimeDetail.lastData.minClearanceParams }" />
					</td>
				</tr>
				<s:if test="%{#request.unfinishedTask != null}">				
					<tr>
						<td class="td_gray150">
							正在执行
						</td>
						<s:if test="%{#request.realTimeDetail.unfinishedTask != null}">
							<td class="table_td_left cRed">
								(创建时间：<s:date name="%{#request.realTimeDetail.unfinishedTask.createTime }" format="yyyy-MM-dd HH:mm:ss" />)<br />
								(创建用户：<s:property value="%{#request.realTimeDetail.unfinishedTask.user.userCode }" />)
							</td>
						</s:if>
					</tr>
				</s:if>
				<tr>
					<td class="table_td1_footer">
					</td>					
					<td class="table_td2_footer">
						<s:if test="%{#request.realTimeDetail.unfinishedTask != null}">
							<s:submit value="召测" action="addRealTimeTask" cssClass="btn6" onclick="return confirmSubmit()" />
						</s:if>
						<s:else>
							<s:submit value="召测" action="addRealTimeTask" cssClass="btn6" />
						</s:else>
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