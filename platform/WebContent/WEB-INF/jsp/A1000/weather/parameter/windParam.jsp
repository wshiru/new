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
			href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/tab.css" />


		<script type="text/javascript"
			src="${basePath }/resource/common/js/NumberUtil.js"></script>
		<!-- start 页尾提示信息 -->


	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<span class="tab_position_b">风速转换参数</span>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->

		<s:form action="saveWindParam" namespace="/weather/parameter"
			method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td class="td_gray150">
						安装高度
					</td>
					<td class="table_td_left">
						<s:textfield name="weatherParameter.mountingHeight" maxlength="8" />
						(米)
						<s:fielderror fieldName="weatherParameter.mountingHeight"
							cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						设计高度
					</td>
					<td class="table_td_left">
						<s:textfield name="weatherParameter.designHeight" maxlength="8" />
						(米)
						<s:fielderror fieldName="weatherParameter.designHeight"
							cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						地面状况系数
					</td>
					<td class="table_td_left">
						<s:textfield name="weatherParameter.windCoefficient" maxlength="8" />
						<s:fielderror fieldName="weatherParameter.windCoefficient"
							cssClass="cRed" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">

					</td>
					<td class="table_td_left">
						地面状况系数取值范围：0.01～0.2之间：
						<br />
						<ol>
							<li>
								&nbsp;&nbsp;&nbsp;&nbsp;1、地型平缓的沙滩和植被低矮的海岸、海岛为0.09～0.1；
							</li>
							<li>
								&nbsp;&nbsp;&nbsp;&nbsp;2、丘陵和值被较高的海岸、海岛为0.11～0.13；
							</li>
							<li>
								&nbsp;&nbsp;&nbsp;&nbsp;3、离海岸较远的田野、房屋稀少的乡村、城市郊区为0.14～0.16；
							</li>
							<li>
								&nbsp;&nbsp;&nbsp;&nbsp;4、离海岸较远的城镇、城市市区为0.17～0.2。
							</li>
						</ol>
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>
					<td colspan="2" class="table_td2_footer">
						<s:submit value="保存" action="saveWindParam" cssClass="btn6"
							onclick="return showwaiting();" />
					</td>
				</tr>
			</table>
		</s:form>


		<!-- end 信息区域 -->


		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>