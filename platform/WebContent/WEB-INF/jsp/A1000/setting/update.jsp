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
		<script type="text/javascript" src="${basePath }/resource/common/js/doubleSelect.js"></script>		
		<script language="JavaScript" type="text/javascript">
			<%@ include file="/WEB-INF/jsp/A1000/setting/doubleSelect.jsp"%>
		</script>
		<script type="text/javascript">
			function changeChoose(obj) {
				var cmaInfoSelectObj = document.getElementById("cmaInfoSelect");
				var sensorSelectObj = document.getElementById("sensorSelect");
				if (obj.value == 0) {
					sensorSelectObj.style.display = "none";
				} else if (obj.value == 1) {
					sensorSelectObj.style.display = "inline";
				}
			}
			function initChoose() {
				var obj = document.getElementsByName("deviceType");
				for (var i = 0, len = obj.length; i < len; i++) {
					if (obj[i].checked == true) {
						changeChoose(obj[i]);
						break;
					}
				}				
			}
		</script>
	</head>
	<body onload="initChoose()">

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;
				<span class="tab_position_b">远程升级</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->		
		<s:form action="updateSave" namespace="/update" method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>装置类型：
					</td>
					<td class="table_td_left">
						<s:radio id="deviceTypeRadio" name="deviceType" list="%{#{'0':'监测代理', '1':'监测装置'} }" onclick="changeChoose(this)" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>装置选择：
					</td>
					<td class="table_td_left">
						<select id="cmaInfoSelect" name="cmaInfoId"  onchange="ChangeSelect(this.value,'sensorSelect','', dataSource, '--监测装置--' )" style="width:expression(this.offsetWidth>100?'auto':'100')"></select>
						<select id="sensorSelect" name="sensorId" onchange="" style="width:expression(this.offsetWidth>100?'auto':'100')"></select>
						<script type="text/javascript">ChangeSelect('0','cmaInfoSelect',"",dataSource, "--监测代理--",true) </script>
						<script type="text/javascript">ChangeSelect('0','cmaInfoSelect','',dataSource, "--监测代理--",true) </script>
						<script type="text/javascript">ChangeSelect('','sensorSelect','',dataSource, "--监测装置--",true);</script>
						<s:fielderror fieldName="cmaInfoId" cssClass="cRed" />
						<s:fielderror fieldName="sensorId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>升级文件：
					</td>
					<td class="table_td_left">
						<s:select name="upgradeFile.upgradeFileId" list="%{#request.upgradeFiles}" headerKey="" headerValue="--请选择--" listKey="upgradeFileId" listValue="fileName + fileExtension + '[' + version + '][' + releaseTime + ']'"></s:select>
						<s:fielderror fieldName="upgradeFile.upgradeFileId" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td class="table_td_left">
						<s:checkbox name="isCancelUnfinishedUpdateTask" fieldValue="true" />同时取消之前未完成的任务<br />
						<s:if test="%{null != #request.unfinishedUpdateTasks }">							
							<table cellspacing="1" onmouseover="changeto()" onmouseout="changeback()" class="tab">
								<tr>
									<td width="20%" class="first_line">
										创建时间
									</td>
									<td width="20%" class="first_line">
										升级文件
									</td>
									<td width="15%" class="first_line">
										任务状态
									</td>
									<td class="first_line">
										查看
									</td>
								</tr>
								<s:iterator value="#request.unfinishedUpdateTasks" var="taskConfig">
									<tr>
										<td class="second_line">
											<s:date name="#taskConfig.createTime" format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<td class="second_line">
											<s:property value="#taskConfig.fileName" /><s:property value="#taskConfig.fileExtension" />[<s:property value="#taskConfig.version" />][<s:property value="#taskConfig.releaseTime" />]
										</td>
										<td class="second_line">
											<s:if test="%{0 == #taskConfig.state }">
												未下发
											</s:if>
											<s:elseif test="%{1 == #taskConfig.state }">
												已下发
											</s:elseif>
											<s:elseif test="%{2 == #taskConfig.state }">
												已取消
											</s:elseif>
											<s:elseif test="%{3 == #taskConfig.state }">
												已执行
											</s:elseif>
											<s:else>
												未知
											</s:else>
										</td>
										<td class="second_line">
											<s:url id="detailUrl" action="detail" namespace="/taskConfig">
												<s:param name="id">
													<s:property value="#taskConfig.taskConfigId" />
												</s:param>
											</s:url>
											<div class="content">
												<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_detail.gif" width="16" height="16" />
												<s:a href="%{detailUrl}">详细</s:a>
											</div>
										</td>
									</tr>
								</s:iterator>
							</table>
						</s:if>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
						&nbsp;
					</td>				
					<td colspan="2" class="table_td2_footer">
						<s:submit value="提交" action="updateSave" cssClass="btn6" />
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