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

		<s:form action="editSave" namespace="/video/videoServer" method="post"
			theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<s:hidden name="videoServer.videoServerId"
				value="%{#request.videoServer.videoServerId }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写视频服务器信息
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
						<s:textfield name="videoServer.name" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoServer.name" cssClass="cRed" />
						<!-- 
						<button id="selbtn" name="selbtn">
							选择
						</button>
						 -->
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>IP地址
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.ip" maxLength="30" onblur=""
							style="width:350px" />
						<s:fielderror fieldName="videoServer.ip" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>端口
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.port" maxLength="5" onblur=""
							style="width:350px" />
						<s:fielderror fieldName="videoServer.port" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>用户名
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.userName" maxLength="30" onblur=""
							style="width:350px" />
						<s:fielderror fieldName="videoServer.userName" cssClass="cRed" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>密码
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.password" maxLength="30" onblur="" />
						<s:fielderror fieldName="videoServer.password" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>摄像机个数
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.cameraCount" maxLength="5"
							onblur="" style="width:350px" />
						<s:fielderror fieldName="videoServer.cameraCount" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>默认打开时间
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="videoServer.openTime" maxLength="5" onblur="" />
						（分钟）
						<s:fielderror fieldName="videoServer.openTime" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="保 存" action="editSave" cssClass="btn4" />
						<s:submit value="电源控制" action="openVideoPower" cssClass="btn6" />
						<object id="VideoPlayer" name="VideoPlayer"
							classid="clsid:E48E8DDA-B807-4635-885D-D812C34EF09F"
							style="display: none;">
						</object>
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