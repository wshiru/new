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
				<span class="tab_position_b">导线温度告警阀值设置</span>
			</div>
		</div>
		<!-- end 当前位置 -->
		
		<s:form namespace="/warning/wiretemperaturecondition"  method="post" theme="simple">
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						线温1：
					</td>
					<td class="table_td_left">
						<s:textfield name="temperature1" />（上限，单位：℃）
						<s:fielderror fieldName="temperature1" cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						线温2：
					</td>
					<td class="table_td_left">
						<s:textfield name="temperature2" />（上限，单位：℃）
						<s:fielderror fieldName="temperature2" cssClass="cRed" />
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
