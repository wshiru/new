<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/basePath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/title.jsp"%>
		<%@ include file="/WEB-INF/jsp/meta.jsp"%>
		<%@ include file="/WEB-INF/jsp/messageHandle.jsp"%>
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/common/css/global.css" />
		<link type="text/css" rel="stylesheet" href="${basePath }/resource/theme/${userTheme }/css/tab.css" />
		<script type="text/javascript" src="${basePath }/resource/module/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${basePath }/resource/common/js/common.js"></script>
	
		<script type="text/javascript">
			function confirmSubmit(obj) {
			    if ( 'p2' ==obj.id ){
		           var select = false;
			       var k = '<s:property value="#request.deviceParameterdetails.size" escape="false" />';
		           for ( i= 0; i < k ; i++){
			          if ( '1' == document.getElementById('select-'+i).value ) {
			             select = true;  
			             break;
			          }
			       }
			       
			       if ( false == select ){
			          alert('请选择要下发的参数!');
			          return false;
			       }
			    }
			    
				return confirm("点击‘确定’将建立新任务，并取消’正在执行中‘的任务；否则请点击‘取消’");
			}
			
			
			function changeSelection() {
				var k = '<s:property value="#request.deviceParameterdetails.size" escape="false" />';
		        
				var isChecked = $("#datainfoForm  input[name='idsTitle']").attr("checked");
				
		        var i = 0;
				if (isChecked) {
					$("#datainfoForm input[name='ids']").attr("checked",'true');//全选	
					//设置所有hidden值
				    for ( i= 0; i < k ; i++){
			          document.getElementById('select-'+i).checked = true;
			          document.getElementById('select-'+i).value ='1';		    
				    }		
				} else if (undefined == isChecked || false == isChecked) {
					$("#datainfoForm input[name='ids']").removeAttr("checked");//取消全选
					for ( i= 0; i < k ; i++){
			          document.getElementById('select-'+i).checked = false;
			          document.getElementById('select-'+i).value ='0';
				    }	
				}
							
			}
				
		    function onCheckClick(obj) {
		        //设置单个hidden值	   
		        var id = obj.id.substr(6,obj.id.length);
		        obj.value = '0';   
			    if (true == obj.checked){
			    	obj.value ='1';
			    }  
			    document.getElementById('select-' + id).value = obj.value;
			    document.getElementById('select-' + id).checked = obj.checked;
			}
			
		</script>

	</head>
	
	<body>

		<!-- start 当前位置 -->
		<div id="tab_position">
			<div class="position_l">
			</div>
			<div class="position_c">
				当前位置:首页&nbsp;&gt;&nbsp;参数设置&nbsp;&gt;&nbsp;
				<span class="tab_position_b">监测代理参数</span>
			</div>
		</div>
		<!-- end 当前位置 -->

		<!-- start 信息区域 -->
         <table cellspacing="0"  class="transparentTab">         
		  <tr>
		      <td colspan="4" class="td_title">
		                            监测代理编码:<s:property value="%{#request.cmaCode}" /> &nbsp;&nbsp;
		          <s:if test="%{#request.deviceParameterdetails.size > 0 }">						
			                          数据召测时间:<s:property value="updateParamDate" />
				  </s:if>                        
		      </td>
	      </tr>
	    </table>
      
		<s:form  id="datainfoForm"  action="readCmaParam"  namespace="/setting/cmaParamsSetting" method="post" theme="simple" >
			<s:hidden name="id" value="%{#request.id}" />
			<s:hidden name="updateParamDate" />
		
			<table align="center" cellspacing="1" class="tab">
			    <tr>
			       <td class="first_line" >
			       	   <s:checkbox name="idsTitle" onclick="changeSelection()"  />
			       </td>
			       <td class="first_line" >
			       	   编码
			       </td>
			      
			       <td class="first_line" >
			       	   描述
			       </td>   
			       
			       <td class="first_line" >
			       	   参数值
			       </td>
			       <td class="first_line" >
			       	   数据类型
			       </td>
			       <td class="first_line" >
			                        长度
			       </td>
			       <td class="first_line" >
			       	  单位
			       </td>
			       <td class="first_line" >
			       	   范围值
			       </td>
			       <td class="first_line" >
			       	   备注信息说明
			       </td>
			      
			    </tr>
			    
			    <s:iterator value="#request.deviceParameterdetails" var="arrayIndex"  status="stat" >
			       <tr>
			           <td class="second_line" width="6%" >
			                  	
			                <s:if test="%{#arrayIndex.state eq 1}">
			                     <input id="check-<s:property value='%{#stat.index}' />" type="checkbox" name="ids"  checked="checked"  value="1"  onclick="onCheckClick(this)" />        
			                </s:if>
			                <s:else>
			                     <input id="check-<s:property value='%{#stat.index}' />" type="checkbox" name="ids"  value="0" onclick="onCheckClick(this)"  />        
			                </s:else>
			                
			                <s:hidden id="select-%{#stat.index}"  name="deviceParameterdetails[%{#stat.index}].state"   />	
			                  
			           </td> 
			           
			           <td class="second_line"  width="13%" >
			              <s:property  value="#arrayIndex.name" />
			              <s:hidden name="deviceParameterdetails[%{#stat.index}].name"   />	
			           </td>
			           
			           <td class="second_line" width="10%" >
			              <s:property value="#arrayIndex.dsec"  />
			              <s:hidden name="deviceParameterdetails[%{#stat.index}].dsec"      />		        
			           </td>
			       
			           <td class="second_line"  width="20%" >
			              <s:textfield  name="deviceParameterdetails[%{#stat.index}].value"  size="27" />	
			                   
			           </td>
			           
			           <td class="second_line" width="6%" >
			             <s:property  value="#arrayIndex.dataType" />
			              <s:hidden name="deviceParameterdetails[%{#stat.index}].dataType"  />		           
			           </td>
			           
			           <td class="second_line"  width="4%" >
			             <s:property  value="#arrayIndex.dataLength" />
			               <s:hidden name="deviceParameterdetails[%{#stat.index}].dataLength"  />	
			                      
			           </td>
			           
			           <td class="second_line"  width="4%" >
			             <s:property  value="#arrayIndex.unit" />
			              <s:hidden name="deviceParameterdetails[%{#stat.index}].unit"    />	
			      		              
			           </td>
			           
			       
			           <td class="second_line">
			              <s:property  value="#arrayIndex.rangeValue" />
			                <s:hidden name="deviceParameterdetails[%{#stat.index}].rangeValue" />	
			           </td>
			           
			           <td class="second_line">
			              <s:property  value="#arrayIndex.note" />
			               <s:hidden name="deviceParameterdetails[%{#stat.index}].note"   />				
			           </td>	     		              
			           		           
			       </tr> 
			    </s:iterator>
					
			
				<tr>
	                 	<td class="table_td1_footer"></td>
						<td colspan="8" class="table_td2_footer">
							<s:submit id ="p1" value="召测"  action="readCmaParam"  cssClass="btn6" onclick="return confirmSubmit(this)"  />
					        <s:if test="%{#request.deviceParameterdetails.size > 0 }">						
							    &nbsp;&nbsp;&nbsp;
							    <s:submit id="p2" value="下发"  action="writeCmaParam" cssClass="btn6" onclick="return confirmSubmit(this)" />
			                </s:if>
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