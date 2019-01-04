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
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<script type="text/javascript">
			function confirmSubmit() {
				return confirm("点击‘确定’将建立新任务，并取消’正在执行中‘的任务；否则请点击‘取消’");
			}
			function showAlert(){
				alert("当前监测代理的参数信息为空，您需要至少先进行一次招测。");
				return false;
			}
		</script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;参数配置查询&nbsp;&gt;&nbsp;
				<span class="tab_position_b">监测代理参数</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		
		<s:form action="readCmaParams" namespace="/setting/cmaParamsSetting" method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<div style="margin:5px;">
				当前监测代理为：<s:property value="%{#request.cma.cmaCode }" />
			</div>
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">参数名</td>
					<td class="table_td_left">上次同步值</td>
					<td class="table_td_left">描述</td>
				</tr>
				<s:if test="cmaParam == null">
					<tr>
						<td class="td_gray150"></td>
						<td colspan="2" class="table_td_left">（当前数据为空）</td>
					</tr>
				</s:if>
				<s:else>
					<s:iterator value="cmaParam.cmaParameterDetails">
						<tr>
							<td class="td_gray150"><s:property value="name"/></td>
							<td class="table_td_left">
								<s:textfield name="value"></s:textfield>
							</td>
							<td class="table_td_left"><s:property value="description"/></td>
						</tr>
						<s:hidden name="type" />
						<s:hidden name="unit" />
						<s:hidden name="range" />
						<s:hidden name="length" />
						<s:hidden name="note" />
					</s:iterator>
				</s:else>
				<tr>
					<td class="table_td1_footer">
					</td>
					<s:if test="%{#request.unfinishedTasks.taskConfig != null}">						
						<td colspan="2" class="table_td2_footer">
							<s:submit value="召测" action="readCmaParams" cssClass="btn6" onclick="return confirmSubmit()" />
							&nbsp;
							<s:if test="cmaParam == null">
								<s:submit value="下发" action="writeCmaParams" cssClass="btn6" onclick="return showAlert()"/>
							</s:if>
							<s:else>
								<s:submit value="下发" action="writeCmaParams" cssClass="btn6" onclick="return confirmSubmit()" />
							</s:else>
						</td>
					</s:if>
					<s:else>
						<td colspan="2" class="table_td2_footer">
							<s:submit value="召测" action="readCmaParams" cssClass="btn6" />
							&nbsp;
							<s:if test="cmaParam == null">
								<s:submit value="下发" action="writeCmaParams" cssClass="btn6" onclick="return showAlert()"/>
							</s:if>
							<s:else>
								<s:submit value="下发" action="writeCmaParams" cssClass="btn6" />
							</s:else>
						</td>
					</s:else>
				</tr>
			</table>
		</s:form>
		<s:if test="%{#request.unfinishedTasks.taskConfig != null}">
			<p style="color:blue; margin:5px;">
				【提示】当前有一个任务正在执行:		
				<s:if test="%{#request.unfinishedTasks.taskConfig.cmdType == 'GETCONFIG'}">
					任务类型：召测 
				</s:if> 
				<s:else>
					任务类型：下发 
				</s:else>
				创建时间：<s:date name="%{#request.unfinishedTasks.taskConfig.createTime }" format="yyyy-MM-dd HH:mm:ss" />
				创建用户：<s:property value="%{#request.unfinishedTasks.taskConfig.user.userCode }" />
				
			</p>
		</s:if>
		<!-- end 信息区域 -->

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>