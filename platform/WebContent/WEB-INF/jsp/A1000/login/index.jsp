<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
	</head>
	
	<frameset rows="80,*,25" frameborder="no" border="0" framespacing="0">		
		<frame src="titleFrame.jspx" name="titleFrame" frameborder="0" scrolling="no" noresize="noresize" id="titleFrame" />
		<frameset id="bottomFrameSet" cols="235,8,*" border="0" framespacing="0">
			<frame src="menuFrame.jspx" name="menuFrame" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" noresize="noresize" />
			<frame src="switchFrame.jspx" name="switchFrame" frameborder="0" noresize="noresize" id="switchFrame" />
			<frameset id="bottomFrameSet" rows="25,*" border="0" framespacing="0">
				<frame src="messageFrame.jspx" name="messageFrame" frameborder="0" scrolling="no" noresize="noresize" id="messageFrame" />
				<!--  
				<frame src="mainMenuFrame.jspx" name="mainMenuFrame" frameborder="0" scrolling="auto" id="mainMenuFrame" />
				-->				
				<frame src="checkSysMenu.jspx" name="mainMenuFrame" frameborder="0" scrolling="auto" id="mainMenuFrame" />
			</frameset>
		</frameset>
		<frame src="barFrame.jspx" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>