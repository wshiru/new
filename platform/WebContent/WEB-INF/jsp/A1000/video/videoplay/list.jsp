<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
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
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>



		<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var host = "<s:property value='#request.videoPlatform.platformHost' />";
						var port = <s:property value='#request.videoPlatform.platformPort' />;
						var username = "<s:property value='#request.videoPlatform.userName' />";
						var password = "<s:property value='#request.videoPlatform.password' />";
						var videoServerCode = "<s:property value='#request.videoServer.videoServerCode' />";
						var cameraCode = "<s:property value='#request.videoServer.cameraCode' />";
						$("#selbtn").click(
								function() {
									alert('sdf');

									try {
										VideoPlayer.Init(host, port, username,
												password, videoServerCode,
												cameraCode);
									} catch (err) {
										alert("初始化失败:" + err.getMessage());
									}

									var aa = VideoPlayer.DeviceList;
									alert(aa);

									alert("init OK");
								});

					});
</script>

	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">视频服务器配置</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->

		<s:form action="list" namespace="/video/videoplay" method="post"
			theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<s:hidden name="loginID" value="%{#request.loginID}" />
			<s:hidden name="channelID"
				value="%{#request.videoServer.videoServerId }" />
			<s:hidden name="videoServer.videoServerId"
				value="%{#request.videoServer.videoServerId }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						视频服务器信息
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>视频服务器编号
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.videoServer.videoServerId" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>视频服务器名称
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.videoServer.name" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>视频服务器编码
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.videoServer.videoServerCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>摄像机编码
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.videoServer.cameraCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>流媒体访问地址
					</td>
					<td colspan="3" class="table_td_left">
						<a href="<s:property value="#request.videoServer.rtspUrl" />"
							target="newWin">url</a>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>打开电源时间
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.openTime" maxLength="5" onblur="" />
						（分钟）
						<s:fielderror fieldName="videoServer.openTime" cssClass="cRed" />
						<s:submit value="WS电源控制" action="powerControl" cssClass="btn6" />
						<s:submit value="WEB电源控制" action="openVideoPower" cssClass="btn6" />
					</td>
				</tr>
				<!-- -->

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>登录
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="userName" maxlength="30" value=""
							onblur="" />
						<input type="text" name="password" maxlength="30" onblur="" />

						<s:submit value="登录" action="login" cssClass="btn2" />
						<s:submit value="注销" action="logout" cssClass="btn2" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>云台控制
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="ptzCmd"
							list="%{#{'1':'云台上仰','2':'云台下俯','3':'云台左转','4':'云台右转','5':'云台上仰和左转','6':'云台上仰和右转','7':'云台下俯和左转','8':'云台下俯和右转','11':'焦距变大(倍率变大)','12':'焦距变小(倍率变小)','13':'焦点前调','14':'焦点后调','15':'光圈扩大','16':'光圈缩小','21':'接通灯光电源','22':'接通雨刷开关'}}"
							headerKey="1" headerValue="----请选择----" />
						<s:submit value="开始" action="ptzControlStart" cssClass="btn2" />
						<s:submit value="停止" action="ptzControlStop" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>预置位控制
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="presetIndex"
							list="%{#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8'}}"
							headerKey="1" headerValue="----请选择----"
							value="%{#request.presetIndex}" />
						<s:submit value="调用" action="ptzPresetCall" cssClass="btn2" />
						<s:submit value="设置" action="ptzPresetSet" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>拍照
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="ftpPath" maxlength="500" onblur="" 
							value="ftp://comtop:comtop@10.143.52.233:5555/test/4834775e-0b36-44af-91c9-43229a105b4a_1_2014-7-8 12-12-12.jpg"/>
						<s:submit value="拍照" action="photograph" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询告警
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="startTime1"
							value='<s:property value="#request.startTime1" />'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						至
						<input type="text" name="endTime1"
							value='<s:property value="#request.endTime1" />'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />

						<s:submit value="查询" action="queryAlarmEvent" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询电源状态
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="查询" action="queryPowerStatus" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询在线状态
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="查询" action="queryOnlineStatus" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询电池电压
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="查询" action="queryBatteryVoltage" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询实时播放URL
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="查询" action="queryRealPlayRtspUrl" cssClass="btn2" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询历史记录
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="startTime2"
							value='<s:property value="#request.startTime2" />'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						至
						<input type="text" name="endTime2"
							value='<s:property value="#request.endTime2" />'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							maxlength="20"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />

						<s:submit value="查询" action="queryRecordFile" cssClass="btn2" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>查询结果
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.retString" />
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