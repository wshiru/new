<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/NumberUtil.js"></script>
	</head>
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;杆塔倾斜监测&nbsp;&gt;&nbsp;
				<span class="tab_position_b">实时数据抄读</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
		<s:form action="samplingTowerTilt" namespace="/towerTilt/realTime" method="post" theme="simple">
			<s:hidden name="id" value="%{#request.id }" />
			<table align="center" cellspacing="1" class="tab">
				<!-- 
				<tr>
					<td class="td_gray150">
						参数名
					</td>
					<td class="table_td_left">
						最后采样数据
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						采集时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#requesttowerTilt.samplingTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						入库时间
					</td>
					<td class="table_td_left">
						<s:date name="%{#requesttowerTilt.createTime }" format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				
				 -->
				
			     <tr>
					<td class="td_gray150">
					  	告警标识
					</td>
					<td class="table_td_left">
				
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						倾斜度
					</td>
					<td class="table_td_left">
						<s:property value="%{#requesttowerTilt.inclination }" />
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						顺线倾斜度
					</td>
					<td class="table_td_left">
						<s:property value="%{#requesttowerTilt.gradientAlongLines }" />
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						横向倾斜度
					</td>
					<td class="table_td_left">
						<s:property value="%{#requesttowerTilt.lateralTilt }" />
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						顺线倾斜角
					</td>
					<td class="table_td_left">
						<s:property value="%{#requesttowerTilt.angle_x }" />
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						横向倾斜角
					</td>
					<td class="table_td_left">
						<s:property value="%{#requesttowerTilt.angle_x }" />
					</td>
				</tr>
				<tr>
					<td class="table_td1_footer">
					</td>					
					<td class="table_td2_footer">
						<s:submit value="召测" action="samplingTowerTilt" cssClass="btn6" />
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