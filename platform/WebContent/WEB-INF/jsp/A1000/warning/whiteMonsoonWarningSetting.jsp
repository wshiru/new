<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp" %>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
  </head>
  
  <body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;告警管理&nbsp;&gt;&nbsp;
				<span class="tab_position_b">相间风偏告警阀值设置</span>
			</div>
		</div>
		<!-- end 当前位置 -->
		
		<s:form namespace="/warning/whitemonsooncondition"  method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						风偏角：
					</td>
					<td class="table_td_left">
						<s:textfield name="windAngle" />（上限，单位: 度）
						<s:fielderror fieldName="windAngle" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						倾斜角：
					</td>
					<td class="table_td_left">
						<s:textfield name="angle" />（上限，单位: 度）
						<s:fielderror fieldName="angle" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						最小电气间隙参数：
					</td>
					<td class="table_td_left">
						<s:textfield name="minClearanceParams" />（上限，单位: mm）
						<s:fielderror fieldName="minClearanceParams" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td class="table_td2_footer">
						<s:submit value="保存" action="save" cssClass="btn6"  />
					</td>
				</tr>
			</table>
		</s:form>
		
		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
  </body>
</html>
