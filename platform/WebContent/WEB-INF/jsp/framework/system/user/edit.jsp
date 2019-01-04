<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link rel="stylesheet"
			href="${basePath }/resource/common/css/global.css" type="text/css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;用户管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b">修改</span>
		</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/system/user" method="post"
			theme="simple">
			<s:hidden name="user.userId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写用户信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>用户编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>状&nbsp;&nbsp;&nbsp;&nbsp;态：
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="user.state" list="%{#{'1':'启用','0':'停用'}}" />
						<s:fielderror fieldName="user.state" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="user.userName" maxLength="50" onblur="" />
						（中英文或数字）
						<s:fielderror fieldName="user.userName" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						联系电话：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="user.phone" maxlength="30" />
						（数字）
						<s:fielderror fieldName="user.phone" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						到期时间：
					</td>
					<td colspan="3" class="table_td_left">
						<input type="text" name="user.expiredTime"
							value='<s:date name="%{#request['user.expiredTime']}" format="yyyy-MM-dd HH:mm:ss"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							maxlength="19"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							readonly="readonly" />
						（格式：yyyy-MM-dd HH:mm:ss）
						<s:fielderror fieldName="user.expiredTime" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						备&nbsp;&nbsp;&nbsp;&nbsp;注：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="user.userDesc" cols="60" rows="6" />
						（250个字符）
						<s:fielderror fieldName="user.userDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="editSave" cssClass="btn4" />
						&nbsp;
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