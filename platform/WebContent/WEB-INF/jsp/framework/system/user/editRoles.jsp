<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色菜单</title>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript"
			src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript"
			src="${basePath }/resource/module/dtree2/dtree.js"></script>
		<link rel="stylesheet"
			href="${basePath }/resource/module/dtree2/dtree.css" type="text/css" />
	</head>

	<body>

		<!-- 当前位置 开始 -->
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;用户管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b">角色分配</span>
		</div>
		</div>
		<!-- 当前位置 结束 -->

		<!-- 分配权限 开始 -->
		<s:form action="list" namespace="/system/user" method="post"
			theme="simple">
			<input type="hidden" name="pn" value="${param.pn}" />
			<s:hidden name="user.userId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						当前用户信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户编号
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="user.userCode" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						用户名称
					</td>
					<td colspan="3" class="table_td_left">
						<s:property value="user.userName" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						状 态
					</td>
					<td colspan="3" class="table_td_left">
						<s:if test="user.state == 1">启用</s:if>
						<s:else>
							<span class="cRed">停用</span>
						</s:else>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="td_title">
						分配角色
					</td>
				</tr>

				<tr>
					<td class="td_gray150">
						角色选择:
					</td>
					<td class="table_td_left">

						<script type="text/javascript">
	tree = new dTree('tree');
	tree.config.target = "mainFrame";
	tree.config.useCheckBox = true;
	tree.config.imageDir = '${basePath }/resource/module/dtree2/img';
	tree.reSetImagePath();
	tree.config.folderLinks = false;
	tree.config.closeSameLevel = false;//关闭其他
	tree.config.check = true;//显示复选框
	tree.config.mycheckboxName = "roleIds";

	var isOpen;
	//根节点

	<s:iterator value="#request.allRoles">
	tree.add('<s:property value="roleId" escape="false" />', '-1',
			'<s:property value="roleName" escape="false" />');
	</s:iterator>
	document.write(tree);
	//设置默认选中的项目 
	tree.openAll();
	tree
			.setCheck("<s:if test='%{#request.ownedRoleIds != null}'><s:property value='%{#request.ownedRoleIds}' escape='false' /></s:if>");
</script>

					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="editRolesSave" cssClass="btn4" />
						&nbsp;
						<s:submit value="返 回" action="list" cssClass="btn4" />
					</td>
				</tr>
			</table>

		</s:form>

		<!-- 分配权限 结束 -->


		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->

	</body>
</html>

