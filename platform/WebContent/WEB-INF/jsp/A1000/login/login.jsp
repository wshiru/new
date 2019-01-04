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
			href="${basePath }/resource/theme/${userTheme }/css/login.css" />
		<script type="text/javascript">
	if (top.location != self.location) {
		top.location = self.location;
	}
	window.onload = function() {
		var u = document.getElementById("userCode");
		u.focus();
	};
</script>
	</head>
	<body>
		<table align="center" class="loginbg">
			<tr>
				<td>

					<table cellpadding="0" cellspacing="0" class="formBg">
						<tr>

							<td align="center">
								<img
									src="${basePath }/resource/theme/${userTheme }/images/login/login_top${projectType}.png"
									 />
							</td>

						</tr>
						<tr>
							<td height="200" valign="top" align="center" 
								style="background-repeat: no-repeat">
								<table align="center" width="100%">
								<tr height="1px">&nbsp;
								</tr>
									<tr>
										<td height="125" align="center" valign="top">
											<s:form action="logon" theme="simple" namespace="/">
												<table align="center">
													<tr>
														<td height="30" class="login_msg">
															用&nbsp;户:
														</td>
														<td>
															<s:textfield id="userCode" name="userCode"
																cssStyle="width:140px;" maxlength="30" />
														</td>
													</tr>

													<tr>
														<td height="30" class="login_msg" align="center">
															密&nbsp;码:
														</td>
														<td>
															<s:password name="password" cssStyle="width:140px;"
																maxlength="30" />
														</td>
													</tr>

													<tr>
														<td height="35">
															&nbsp;
														</td>
														<td>
															<s:submit value="登  录" cssClass="login_btn"
																onfocus="this.blur()" />
															&nbsp;
															<s:reset value="清  空" cssClass="login_btn"
																onfocus="this.blur()" />
														</td>
													</tr>
												</table>
											</s:form>
										</td>
									</tr>
									<tr align="center">
										<td>
											<span class="copyright2">深圳市深联创展科技开发有限公司&nbsp;&nbsp;&nbsp;Copyright
												© 2008-2016</span>
											<br />
											<span class="copyright3">ShenZhen New Unite Innovate Develop CO.,Ltd.</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>

				</td>
			</tr>
		</table>


		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>

