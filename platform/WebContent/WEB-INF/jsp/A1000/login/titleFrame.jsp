<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet"
			href="${basePath }/resource/theme/${userTheme }/css/titleFrame.css" />
		<s:set scope="page" name="themeImagesPath"
			value="%{#request.basePath + '/resource/theme/' + #request.userTheme + '/images/titleFrame'}" />
		

	</head>
	<body>
		<div class="topbg">
			<div class="top_title${projectType}"></div>			
			<div class="right w50">
				<div class="btntop">
					<div class="btn_r">
						<!-- 说明： parent.mainMenuFrame.hideMenu() ： 如果导航菜单没有收起时，先收起才能操作 -->
						<ul>
							<li>
								<div class="btn_a">

									<a href="checkSysMenu.jspx" target="mainMenuFrame"
										onclick="parent.mainMenuFrame.hideMenu();"> <img
											src="${themeImagesPath }/btn_home.png" width="32" height="32"
											border="0" />
										<div class="btn_caption">
											主页
										</div> </a>
									<!--  
									<a href="mainFrame.jspx" target="mainFrame"
										onclick="parent.mainMenuFrame.hideMenu();"> <img
											src="${themeImagesPath }/btn_home.png" width="32" height="32"
											border="0" />
										<div class="btn_caption">
											主页
										</div> </a>
										-->
								</div>

							</li>
							<li>
								<div class="btn_a">

									<a href="checkSysMenu.jspx?checkMenu=1" target="mainMenuFrame"
										onclick="parent.mainMenuFrame.hideMenu();"> <img
											src="${themeImagesPath }/btn_sysmenu.png" width="32"
											height="32" border="0" />
										<div class="btn_caption">
											系统菜单
										</div> </a>
									<!--  
									<a href="mainFrame.jspx" target="mainFrame"
										onclick="parent.mainMenuFrame.hideMenu();"> <img
											src="${themeImagesPath }/btn_home.png" width="32" height="32"
											border="0" />
										<div class="btn_caption">
											主页
										</div> </a>
										-->
								</div>

							</li>
							<li>
								<div class="btn_a">
									<a href="${basePath }/system/user/modPwd.jspx"
										target="mainFrame" onclick="parent.mainMenuFrame.hideMenu();">
										<img src="${themeImagesPath }/btn_password.png" width="32"
											height="32" border="0" />
										<div class="btn_caption">
											修改密码
										</div> </a>
								</div>
							</li>
							<li>
								<div class="btn_a">
									<a href="logout.jspx" target="_top"
										onclick="return confirm('确定要退出系统吗') "> <img
											src="${themeImagesPath }/btn_exit.png" width="32" height="32"
											border="0" />
										<div class="btn_caption">
											退出
										</div> </a>
								</div>
							</li>
						</ul>
					</div>
				</div>

			</div>
		</div>

	</body>
</html>