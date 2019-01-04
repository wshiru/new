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
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript">
			function checkNewPWd(){
				var newPwdField = document.getElementsByName("newPwd")[0];
				var confirmPwdField = document.getElementsByName("confirmPwd")[0];
				if(newPwdField && confirmPwdField){
					if(confirmPwdField.value != newPwdField.value){
						alert("新密码前后输入不一致，请重新输入");
						return false;
					}
					return true;
				}			
			}
		</script>
	</head>

	<body>
		<!-- 当前位置 开始 -->
		<div id="tab_position"> 
			<span class="tab_position_b">修改密码</span>
		</div>
		<!-- 当前位置 结束 -->


		<!-- 信息区域 开始 -->
		<s:form namespace="/system/user" method="post" theme="simple">
			<s:hidden name="user.userId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						修改用户密码
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户名
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						输入旧密码
					</td>
					<td colspan="3" class="table_td_left">
						<s:password name="user.password" maxlength="30" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						输入新密码

					</td>
					<td colspan="3" class="table_td_left">
						<s:password name="newPwd" maxlength="30" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						新密码确认
					</td>
					<td colspan="3" class="table_td_left">
						<s:password name="confirmPwd" maxlength="30" />
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" onclick="return checkNewPWd();"
							action="modPwdSave" cssClass="btn4" />
							<!-- 
						<s:submit value="返回" action="backHome" cssClass="btn4" /> -->
					</td>
				</tr>
			</table>
		</s:form>
		<!-- 信息区域 结束 -->

		<!-- 页尾提示信息开始 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- 页尾提示信息结束 -->
	</body>
</html>
