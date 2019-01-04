<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色菜单</title>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp" %>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/dtree2/dtree.js"></script>
		<link rel="stylesheet" href="${basePath }/resource/module/dtree2/dtree.css" type="text/css" />	    	
		
	</head>

	<body>

        <!-- 当前位置 开始 -->	
		<div id="tab_position">
			系统设置&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;
			<span class="tab_position_b"> 配置权限
			</span>
		</div>	        
		<!-- 当前位置 结束 -->
	
		<!-- 分配权限 开始 -->
		<s:form action="list" namespace="/system/role" method="post" theme="simple">
			<input type="hidden" name="pn" value="${param.pn}"/>
			<s:hidden name="role.roleId" />
			<table align="center" cellspacing="1" class="tab">
				<tr>
					<td colspan="4" class="td_title">
						当前角色信息
					</td>
				</tr>			
				<tr>
					<td class="td_gray150">
						角色名称
					</td>
					<td colspan="3" class="table_td_left">
					    <s:property value="role.roleName" />
					</td>	
				</tr>
				
				<tr>
					<td class="td_gray150">
						描述
					</td>
					<td colspan="3" class="table_td_left">
					  	<s:property value="role.roleDesc" />
					</td>	
				</tr>
						
				<tr>
					<td colspan="4" class="td_title">
						分配角色权限
					</td>
				</tr>
				
				<tr>
					<td class="td_gray150">
						权限选择:
					</td>
					<td  class="table_td_left">
					
		 	               <script type="text/javascript">
                                    tree = new dTree('tree');
	                                tree.config.target = "mainFrame";
	                               	tree.config.useCheckBox=true;
	                                tree.config.imageDir = '${basePath }/resource/module/dtree2/img';
	                                tree.reSetImagePath();
	                                tree.config.folderLinks = false;
	                                tree.config.closeSameLevel = false;//关闭其他
	                                tree.config.check=true;//显示复选框
	                                tree.config.mycheckboxName="authResourceIds";
	                           
	                                //根节点
   
   
   		                            <s:iterator value="#request.allAuths">										
										 tree.add('<s:property value="authResourceId" escape="false" />', 
												 '<s:if test="parent==null">-1</s:if><s:else><s:property value="parent.authResourceId" escape="false" /></s:else>',
												 '<s:property value="name" escape="false" />');
									</s:iterator>
						            
									document.write(tree);	   	                               	                            	
	                               	
	                                	
	                               	//设置默认选中的项目 
	                                tree.setCheck("<s:if test='%{#request.ownedAuthIds != null}'><s:property value='%{#request.ownedAuthIds}' escape='false' /></s:if>");
	
	     			                tree.openAll();
	     			               
	     			                
	     			       </script>
	     			  
					</td>
				</tr>
				<tr>
					<td class="td_gray150">
						&nbsp;
					</td>
					<td colspan="3" class="table_td_left">
						<s:submit value="保 存" action="updateAuth" cssClass="btn4" />
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

