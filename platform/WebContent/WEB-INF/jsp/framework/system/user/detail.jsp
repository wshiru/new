<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link rel="stylesheet" href="${basePath }/resource/common/css/global.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;用户管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">详细信息</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="editSave" namespace="/system/user" method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						用户信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户编号：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						状&nbsp;&nbsp;&nbsp;&nbsp;态：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{#request.user.state eq 1}">启用</s:if>
						<s:elseif test="%{#request.user.state eq 0}">
							<span class="cRed">停用</span>
						</s:elseif>
						<s:else>
							<span class="cRed">未知</span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.userName" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						联系电话：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.phone" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						到期时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="%{#request.user.expiredTime.after(new java.util.Date()) }">
							<s:date name="#request.user.expiredTime" format="yyyy-MM-dd HH:mm:ss" />
						</s:if>
						<s:else>
							<span class="cRed"><s:date name="#request.user.expiredTime" format="yyyy-MM-dd HH:mm:ss" /> </span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						最后登录时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="#request.user.lastLoginTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						最后登录IP：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.lastLoginIp" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						创建时间：
					</td>
					<td colspan="3" class="table_td_left">
						<s:date name="#request.user.createTime" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						备&nbsp;&nbsp;&nbsp;&nbsp;注：
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="#request.user.userDesc" />
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