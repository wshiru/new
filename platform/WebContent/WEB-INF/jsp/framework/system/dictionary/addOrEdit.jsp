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
			系统设置&nbsp;&gt;&nbsp;字典管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b"> <s:if test="dictionary==null||dictionary.dictionaryId==null">新增</s:if>
			<s:else>修改</s:else></span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="list" namespace="/system/dictionary" method="post"
			theme="simple">
			<input type="hidden" name="pn" value="${param.pn}" />
			<s:hidden name="dictionary.dictionaryId" />
			<s:hidden name="dictionary.state" />
			<s:hidden name="dictCategory.dictCategoryId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						填写字典信息
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>字典编码：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="dictionary.code" maxLength="50" onblur="" />
						<s:fielderror fieldName="dictionary.code" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>字典名称：
					</td>
					<td colspan="3" class="table_td_left">
						<s:textfield name="dictionary.name" maxLength="100" onblur="" />
						<s:fielderror fieldName="dictionary.name" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						<span class="cRed">*</span>字典类型：
					</td>
					<td colspan="3" class="table_td_left">
						<s:select name="dictionary.dictCategory.dictCategoryId"
							listKey="dictCategoryId" listValue="name"
							list="#request.dictCategoryList" />
						<s:fielderror fieldName="dictionary.dictCategory.dictCategoryId"
							cssClass="cRed" />
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
