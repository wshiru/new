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
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b"> <s:if
					test="role == null || role.roleId == null">新增</s:if> <s:else>修改</s:else>
			</span>
		</div>

		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="list" namespace="/system/role" method="post"
			theme="simple">
			<input type="hidden" name="pn" value="${param.pn}" />
			<s:hidden name="role.roleId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写角色信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>角色名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="role.roleName" maxLength="25" onblur="" />
						<s:fielderror fieldName="role.roleName" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>角色类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:radio name="role.roleType" list="#{'0':'用户角色', '1':'系统角色'}" />
						<s:fielderror fieldName="role.roleType" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;&nbsp;角色描述：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textarea name="role.roleDesc" cols="60" rows="5" />
						<s:fielderror fieldName="role.roleDesc" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="saveOrUpdate" cssClass="btn4" />
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
