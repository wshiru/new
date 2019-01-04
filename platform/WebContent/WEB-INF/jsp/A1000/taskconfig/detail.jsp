<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link rel="stylesheet" href="${basePath }/resource/common/css/global.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;系统监控&nbsp;&gt;&nbsp;任务&nbsp;&gt;&nbsp;
				<span class="tab_position_b">详细信息</span>
			</div>
		</div>
		<!-- end 当前位置 -->
		
		<!-- start 信息区域 -->
		<s:form action="list" namespace="/taskConfig" method="post" theme="simple">	
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						任务信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						创建时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="%{#request.taskConfigDetail.taskConfig.createTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						命令类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@GETCONFIG.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							读配置交互
						</s:if>
						<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@SETCONFIG.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							写配置交互
						</s:elseif>
						<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@ACTIVATE.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							控制交互
						</s:elseif>
						<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@RESEND.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							数据重传
						</s:elseif>
						<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@DEACTIVATE.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							休眠
						</s:elseif>
						<s:elseif test="%{@com.yixin.A1000.setting.model.ProtocolCmdType@REALTIME.equalsIgnoreCase(#request.taskConfigDetail.taskConfig.cmdType) }">
							实时数据
						</s:elseif>
						<s:elseif test="%{#request.taskConfigDetail.taskConfig.upgradeFile != null }">
							远程升级
						</s:elseif>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						影响装置：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{null != #request.taskConfigDetail.taskConfig.cmaCode}">
							监测代理（<s:property value="#request.taskConfigDetail.taskConfig.cmaCode" />）
						</s:if>
						<s:elseif test="%{null != #request.taskConfigDetail.taskConfig.sensorCode}">
							监测装置（<s:property value="#request.taskConfigDetail.taskConfig.sensorCode" />）
						</s:elseif>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						状&nbsp;&nbsp;&nbsp;&nbsp;态：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{0 == #request.taskConfigDetail.taskConfig.state }">
							未下发
						</s:if>
						<s:elseif test="%{1 == #request.taskConfigDetail.taskConfig.state }">
							已下发
						</s:elseif>
						<s:elseif test="%{2 == #request.taskConfigDetail.taskConfig.state }">
							已取消
						</s:elseif>
						<s:elseif test="%{3 == #request.taskConfigDetail.taskConfig.state }">
							已执行
						</s:elseif>
						<s:else>
							未知
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						操作用户：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.taskConfigDetail.taskConfig.user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						任务内容：
					</td>
					<td colspan="3" class="table_td_left">
					         
					      <!--   参数下发  -->
					      <s:if test="%{#request.taskConfigDetail.paramDetails.size > 0}" >
					        <table align="center" cellspacing="1" class="tab">
					           	<tr>
					           		<td class="td_gray150">
										 编码
									</td>
									<td class="td_gray150">
										描述
									</td>
									<td class="td_gray150">
										参数值
									</td>
									
									<td class="td_gray150">
										单位
									</td>
									<td class="td_gray150">
										备注信息说明
									</td>
								</tr>
					           	
					       	   <s:iterator value="#request.taskConfigDetail.paramDetails" var="list">
								<tr>
									<td class="table_td_left">
										<s:property value="#list.name" />
									</td>
									<td class="table_td_left">
										<s:property value="#list.dsec" />
									</td>
									<td class="table_td_left">
										<s:property value="#list.value" />
									</td>
									<td class="table_td_left">
										<s:property value="#list.unit" />
									</td>
									<td class="table_td_left">
										<s:property value="#list.note" />
									</td>		
								</tr>
								
							  </s:iterator>
	   				     </table>
	   				    </s:if>
	   				    <s:elseif  test="%{#request.taskConfigDetail.upgradeFile != null}">
	   				         <table align="center" cellspacing="1" class="tab">
					           	<tr>
					           		<td class="td_gray150">
										 监测类型
									</td>
									<td class="table_td_left" >
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.sensorType.sensorTypeName}" />
									</td>
								</tr>
								<tr>
									<td class="td_gray150">
										文件路径
									</td>
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.path}" />
									</td>
								</tr>
							
								<tr>	
									<td class="td_gray150">
										文件名称
									</td>
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.fileName}" />
									</td>
								</tr>

								<tr>	
									<td class="td_gray150">
										文件后缀
									</td>
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.fileExtension}" />
									</td>
                                </tr>
                                
                                <tr> 
									<td class="td_gray150">
										版本号
									</td>
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.version}" />
									</td>
								</tr>
								
								<tr>	
									<td class="td_gray150">
										版本发布时间
									</td>
									<td class="table_td_left">
									   <s:date name="#request.taskConfigDetail.upgradeFile.releaseTime" format="yyyy-MM-dd HH:mm:ss" />
									 					   
									</td>
								</tr>
								
								<tr>	
									<td class="td_gray150">
										上传时间
									</td>
									<td class="table_td_left">
									   <s:date name="#request.taskConfigDetail.upgradeFile.uploadTime" format="yyyy-MM-dd HH:mm:ss" />
									    
									</td>
								</tr>
								<tr>	
									<td class="td_gray150">
										上传用户
									</td>
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.user.userName}" />
									</td>
								</tr>
								
								<tr>	
									<td class="td_gray150">
										描述
									</td>		
									<td class="table_td_left">
									    <s:property  value="%{#request.taskConfigDetail.upgradeFile.upgradeFileDesc}" />
									</td>																
								</tr>	        
					       </table>
	   				          
	   				    </s:elseif>
					    <s:else>
					                    无
					    </s:else>   
							
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
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