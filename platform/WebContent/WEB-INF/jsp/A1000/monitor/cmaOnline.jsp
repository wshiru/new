<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
		<title>在线监测代理（CMA）列表</title>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>

	</head>

	<body>

		<!-- 当前位置 开始 -->
		<div id="tab_position">
			系统监控&nbsp;&gt;&nbsp;
				<span class="tab_position_b">在线监测代理</span>
		</div>
		<!-- 当前位置 结束 -->

		<!-- 在线状态区域开始  -->

	     <s:form id="formCmaOnline" action="list" namespace="/monitor/cmaonline" method="post" theme="simple">
	       <table cellspacing="0"  class="transparentTab">         
				<tr>
				   <td colspan="4" class="td_title">
						监测代理在线率：<s:property value="onlineRate"/>%&nbsp;&nbsp;&nbsp;
						监测代理总数：<s:property value="totalCount"/>&nbsp;&nbsp;&nbsp;
						在线监测代理：<s:property value="onlineCount"/>&nbsp;&nbsp;&nbsp;
						<s:submit value="刷新" cssClass="btn2"/>&nbsp;&nbsp; 
						<s:radio name="isShowOnline" list="#{'true':'显示在线','false':'显示不在线'}"></s:radio>
				   </td>			  
				</tr>		
				<tr>	
					<td>
					 	<s:iterator value="#request.OnlineCmaList">	
							<div class="sequenceItem">					
				                	<img src="${basePath }/resource/theme/green/images/cma.png" title="型号：${cmaModel}&#13;描述：${cmaDesc}"/>
				                	 <br />
				                	<s:property value="cmaCode"/><br />
				                	（<s:property value="cmaName"/>）
				            </div>
		                </s:iterator>	                
					</td>	
				</tr>
		   </table>
		 </s:form>
		<!-- 在线状态区域结束  -->

		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>

