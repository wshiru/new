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

		<script type="text/javascript" src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/jquery/jquery.blockUI.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/common2.js"></script>

		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
		
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>		
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">视频平台配置</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/video/videoPlatform" method="post" theme="simple">
			<s:hidden name="videoPlatform.videoPlatformId" value="%{#request.videoPlatform.videoPlatformId }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写视频平台信息
					</td>
				</tr>			
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>平台名称
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.name" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoPlatform.name" cssClass="cRed" />						
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>平台地址
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.platformHost" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoPlatform.platformHost" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>平台端口
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.platformPort" maxLength="5" onblur="" />
						<s:fielderror fieldName="videoPlatform.platformPort" cssClass="cRed" />					
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>用户名
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.userName" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoPlatform.userName" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						密码
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.password" maxLength="30" onblur="" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						通过网闸
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="videoPlatform.usedGateway" list="%{#{'1':'是','0':'否'}}" />						
					</td>
				</tr>				
				
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>网关地址
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.gatewayHost" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoPlatform.gatewayHost" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>网关端口
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoPlatform.gatewayPort" maxLength="5" onblur="" />
						<s:fielderror fieldName="videoPlatform.gatewayPort" cssClass="cRed" />					
					</td>
				</tr>		
				<tr>
					<td class="td_gray150">
						使用调试模式
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="videoPlatform.usedDebug" list="%{#{'1':'启用','0':'不启用'}}" />						
					</td>
				</tr>									
				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="保 存" action="editSave" cssClass="btn4" /> <a href="${basePath }/resource/module/setupOCX.exe">下载实时播放插件</a><a href="${basePath }/resource/module/MediaPlayerPlugin.rar">下载播放器播放插件</a>
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