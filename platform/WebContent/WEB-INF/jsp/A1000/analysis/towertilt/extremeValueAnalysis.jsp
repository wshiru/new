<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp" %>
		<%@ include file="/WEB-INF/jsp/framework/prepareChart.jsp" %>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/main.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/uitab.css" />
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/highLight.js"></script>
		<script type="text/javascript" src="${basePath }/resource/module/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common//js/checkbox.js"></script>
	   	   	
	    <link rel="stylesheet" href="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.css" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/module/jquery/jquery-ui-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/resource/module/jquery/jquery.cookie.js"></script>
		
	    <style type="text/css">
		       .div_Hide {
				display: none;
			   }
			
			   .div_Show {
				display: block;
			   }
		</style>
	   	
	   	
	    <script type="text/javascript">
	      
	      	   $(function() {
				// Tabs
				$('#tabs').tabs({ cookie: { expires: 30 } });
			   });
	      
	      
	           function   AnalysisChanage(obj)
	           { 
	             	var  dayFx =  document.getElementById("dayFx");
	             	var  otherFx =  document.getElementById("otherFx");
	             	
	             	if ( obj.value ==  0 )
	             	{
	             	    dayFx.className = "div_Show";
	             	    otherFx.className = "div_Hide";
	             	    
	             	}
	             	else {
	             	    dayFx.className = "div_Hide";
	             	    otherFx.className = "div_Show";  	   
	             	}
	             	
		            var  StartYearFx =  document.getElementById("StartYearFx");
		            var  EndYearFx =  document.getElementById("EndYearFx");
		            
		            var  StartMonthFx =  document.getElementById("StartMonthFx");
		            var  EndMonthFx =  document.getElementById("EndMonthFx");
			   
			       
			        
			        if(  obj.value == 0 ){
			           StartYearFx.style.display = "none";
			           StartMonthFx.style.display = "none";
			           EndYearFx.style.display = "none";
			           EndMonthFx.style.display = "none";   
			        }
			        else if (  obj.value == 1 ){
			           StartYearFx.style.display = "";
			           StartMonthFx.style.display = "";
			           EndYearFx.style.display = "";
			           EndMonthFx.style.display = "";       
			        }
			        else if  (  obj.value == 2 ){
			           StartYearFx.style.display = "";
			           EndYearFx.style.display = ""; 
			           StartMonthFx.style.display = "none";		           
			           EndMonthFx.style.display = "none"; 
			        }
			        
	           }
	           
	    </script>
		
	</head>
	<body>
		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;高级分析&nbsp;&gt;&nbsp;
				<span class="tab_position_b">极值统计</span>
			</div>
			<div class="position_r">
			</div>
		</div>
		<!-- end 当前位置 -->
	
		
		<!-- start 查询区域 -->
		<s:form id="queryForm" action="extremeValueList" namespace="/analysis/towertilt" method="post" theme="simple">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="first_line_left"></td>
								<td class="first_line_center">
									<div class="tab_title">
										<img src="${basePath }/resource/theme/${userTheme }/images/tab/ico_search.gif" width="16" height="16" />
										查询条件
									</div>
								</td>
								<td class="first_line_right"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="second_line_left">
									&nbsp;
								</td>
								<td class="second_line_center">
									<table width="100%" cellspacing="1" class="tab">
										<s:hidden name="sensorId"/>
									
										<tr>
										    <td class="table_td_right td_whith110">
												数据类型：											
											</td>
											<td class="table_td_left">												
											     <s:select id="typeId"  name="DataTypeId"  list="%{#request.DataType}" listKey="%{#request.DataTypeId}" listValue="%{#request.DataTypeName}" onchange="AnalysisChanage(this)"  /> 
											</td>
											
											<td class="table_td_right td_whith110">
												采集时间：											
											</td>
											<td class="table_td_left">												
											   							    
											   	   <s:if test="%{#request.DataTypeId eq 0}" >        	        
											        <div id="dayFx"  class="div_Show" >
											             <input type="text" name="beginTime" value='<s:date name="beginTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
											 	                                至												
												          <input type="text" name="endTime" value='<s:date name="endTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
						                            </div>
						                          </s:if>
						                          <s:else>
						                            <div id="dayFx"  class="div_Hide" >
											             <input type="text" name="beginTime" value='<s:date name="beginTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
											 	                                至												
												          <input type="text" name="endTime" value='<s:date name="endTime" format="yyyy-MM-dd"/>' onclick="WdatePicker()" maxlength="15" onfocus="WdatePicker()" />
						                            </div>				           
						                          </s:else>
						                           
						                           
						                          <s:if test="%{#request.DataTypeId > 0}" >        	        										      
											         <div id="otherFx" class="div_Show" >
											       		 <s:select  id="StartYearFx"    name="StartYearId"  list="%{#request.Years}" listKey="%{#request.YearId}" listValue="%{#request.YearName}"  ></s:select> 				                                    
												    	 
												    	 <s:if test="%{#request.DataTypeId eq 1}" >        
												    	    <s:select id="StartMonthFx"   name="StartMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 									           
										         		 </s:if>
										         		 <s:else>
										         		    <s:select id="StartMonthFx"  style="display: none;"   name="StartMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 									           									         		 
										         		 </s:else>
										         		 										         		 
										         				 至							          								                
										            	 <s:select  id="EndYearFx"   name="EndYearId"  list="%{#request.Years}" listKey="%{#request.YearId}" listValue="%{#request.YearName}"  ></s:select> 										            										            
										             	 									             	 
										             	 <s:if test="%{#request.DataTypeId eq 1}" > 
										              	    <s:select  id="EndMonthFx"  name="EndMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 								                      										    
												         </s:if>
												         <s:else>
												            <s:select  id="EndMonthFx" style="display: none;"   name="EndMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 								                      										    											         
												         </s:else>
												          
												     </div> 
												   </s:if>
												   <s:else>
						                              <div id="otherFx" class="div_Hide" >
											      	     <s:select  id="StartYearFx"  style="display: none;"   name="StartYearId"  list="%{#request.Years}" listKey="%{#request.YearId}" listValue="%{#request.YearName}"  ></s:select> 				                                    
												    	 
												    	 <s:select id="StartMonthFx"   style="display: none;"    name="StartMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 									           
										         				 至							          								                
										            	 <s:select  id="EndYearFx"  style="display: none;" name="EndYearId"  list="%{#request.Years}" listKey="%{#request.YearId}" listValue="%{#request.YearName}"  ></s:select> 										            										            
										             	 
										                 <s:select  id="EndMonthFx"  style="display: none;"  name="EndMonthId"  list="%{#request.Months}" listKey="%{#request.MonthId}" listValue="%{#request.MonthName}"  ></s:select> 								                      										       
											      	   
											      	  </div> 
												           
						                          </s:else>
												   
											         											                   								        							    
											</td>
											
										</tr>
										
										<tr>
										    <td class="table_td_right td_whith110">
												数据项：											
											</td>
											<td colspan="3" class="table_td_left">
											    <s:select  name="FiledId"  list="%{#request.Fileds}" listKey="%{#request.FiledId}" listValue="%{#request.FiledName}" ></s:select>  							
								            </td>
										</tr>
										
									</table>
								</td>
								<td class="second_line_right"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="third_line_left"></td>
								<td class="third_line_center">
									<s:submit value="查 询" cssClass="btn2" action="extremeValueList" />
									&nbsp;&nbsp;
								
									<s:submit value="导出Excel" cssClass="btn4" action="extremeExportExcel" />
									&nbsp;&nbsp;
							
									<s:reset type="button" value="清 空" cssClass="btn2" onclick="clearForm(this.form);return false;" />
								</td>
								<td class="third_line_right">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
		<!-- end 查询区域 -->
		
		    
	   <div id="tabs" style="height:100%;">
			
		   <ul>
				<li>
					<a href="#tabs-4"><span>分析曲线</span> </a>
				</li>
				<li>
					<a href="#tabs-5"><span>采样数据</span> </a>
				</li>
		   </ul>

		  <div id="tabs-4">
				<table align="center" cellspacing="1" class="tab">
					<tr>
						<td colspan="4" class="table_td_left">
							     <s:url action="extremeValueChart" namespace="/analysis/towertilt" var="chartUrl">
										
										<s:param name="sensorId" value="sensorId" />
										<s:param name="beginTime">
											<s:date name="beginTime" format="yyyy-MM-dd" />
										</s:param>
										<s:param name="endTime">
											<s:date name="endTime" format="yyyy-MM-dd" />
										</s:param>						
										<s:param name="DataTypeId" value="DataTypeId" />										
										<s:param name="StartYearId" value="StartYearId" />			
										<s:param name="StartMonthId" value="StartMonthId" />
										<s:param name="EndYearId" value="EndYearId" />
										<s:param name="EndMonthId" value="EndMonthId" />	
										<s:param name="FiledId" value="FiledId" />	
																
									</s:url>
									
						          <img src="${chartUrl}" />
                         						   	  
   						</td>
					</tr>
				</table>
		   </div>
			
			    
           <div id="tabs-5">
           
        
           <!-- start 信息区域 -->
		   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="first_line_left"></td>
							<td class="first_line_center">
								<div class="tab_title">
								    &nbsp;
								</div> 
							</td>
							<td class="first_line_center">
							</td>
							<td class="first_line_right"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="second_line_left">
								&nbsp;
							</td>
							<td class="second_line_center">
								<table cellspacing="1" onmouseover="changeto()" onmouseout="changeback()" class="tab">
								
								    <tr>
												<td colspan="2" class="first_line">
													&nbsp;
												</td>
												
												<td colspan="3"  class="first_line">
												    <s:property  value="%{#request.DataFilledName}" />
												</td>
									        
									</tr>
											
									<tr>
										<td width="10%" class="first_line">
											序号
										</td>
										<td width="20%" class="first_line">
											采集时间
										</td>
									
										<td width="20%" class="first_line">
											最大值
										</td>
									
									    <td width="20%" class="first_line">
											最小值
										</td>
										<td  class="first_line">
											平均值
										</td>
																								
									</tr>
									
									<s:iterator value="#request.page.records"  status="rowstatus"  var ="p" >
										<tr>
											<td class="second_line">
												 <s:property value="#rowstatus.index + 1" /> 
											</td>
											<td class="second_line">
											    <s:property value="#p.samplingTime" />											
											</td>
											<td class="second_line">
												<s:property value="#p.maxValue" />
											</td>
											<td class="second_line">
												<s:property value="#p.minValue" />
											</td>
											<td class="second_line">
												<s:property value="#p.avgValue" />
											</td>
																					
										</tr>
									</s:iterator>
								</table>
							</td>
							<td class="second_line_right"></td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
				<td height="29">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="third_line_left"></td>
							<td class="third_line_center">
								<s:if test="endTime != null">
									<s:set var="qEndTime" scope="page">
										<s:date name="endTime" format="yyyy-MM-dd" />
									</s:set>
								</s:if>
								<s:if test="beginTime != null">
									<s:set var="qBeginTime" scope="page">
										<s:date name="beginTime" format="yyyy-MM-dd" />
									</s:set>	
								</s:if>
																						
								<yixin:page url="/analysis/towertilt/extremeValueList.jspx" page="${page}" id="towerTiltExtremeValueAnalysis_page">
									<yixin:hidden name="sensorId" value="${requestScope['sensorId']}" />
									<yixin:hidden name="beginTime" value="${pageScope.qBeginTime}" />
									<yixin:hidden name="endTime" value="${pageScope.qEndTime}" />
									<yixin:hidden name="DataTypeId" value="${requestScope['DataTypeId']}" />
									<yixin:hidden name="StartYearId" value="${requestScope['StartYearId']}" />
									<yixin:hidden name="StartMonthId" value="${requestScope['StartMonthId']}" />
									<yixin:hidden name="EndYearId" value="${requestScope['EndYearId']}" />
									<yixin:hidden name="EndMonthId" value="${requestScope['EndMonthId']}" />	
									<yixin:hidden name="FiledId" value="${requestScope['FiledId']}" />																	
								</yixin:page>
														 
							</td>
							<td class="third_line_right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		    </table>
	    <!-- end 信息区域 -->
           </div>
           
       </div>
       
   
      
		<!-- start 页尾提示信息 -->
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		<!-- end 页尾提示信息 -->
	</body>
</html>