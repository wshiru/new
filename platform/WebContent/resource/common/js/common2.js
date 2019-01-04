function changeDisplayState(id, o) {
	var obj = document.getElementById(id);
	if (obj.className == "div_display_block") {
		obj.className = "div_display_none";
		o.className = "caption_collected";
	} else {
		obj.className = "div_display_block";
		o.className = "caption_expanded";
	}
}

function format2PointNum(obj) {
	//先把非数字的都替换掉，除了数字和.
	obj.value = obj.value.replace(/[^\d.]/g,"");
	//必须保证第一个为数字而不是.
	obj.value = obj.value.replace(/^\./g,"");
	//保证只有出现一个.而没有多个.
	obj.value = obj.value.replace(/\.{2,}/g,".");
	//保证.只出现一次，而不能出现两次以上

	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	
	// 限制为两位小数

	obj.value = obj.value.toString().replace(/^(\d+\.\d{2})\d*$/,"$1");	
	// 也可以用下面这句，要求IE5.5以上
	//obj.value = obj.value.toFixed(2);\
}

function format2Num(obj) {
	obj.value = obj.value.replace(/\D/g,'');
}


function submitForm(submitObj, namespace) {
	submitObj.form.action= namespace + "/" + submitObj.name.split(":")[1] + ".jspx";
}

/**
	说明：清空表单内容。将表单formObj内的text、password、file的值设空；checkbox、radio的选中取消；select项中有选项时，选择第一项

	参数：formObj - 表单对象	
*/
function clearForm(formObj) {
	var inputArray = formObj.getElementsByTagName("input");
	for (var i = 0, len = inputArray.length; i < len; i++) {
		var v = inputArray[i];
		if (v.type == "text" || v.type == "password") {						
			v.value = "";
		} else if (v.type == "checkbox" || v.type == "radio") {
			v.checked = false;
		} else if (v.type == "file") {
			v.select();
			document.execCommand("delete");
		}
	}
	inputArray = formObj.getElementsByTagName("select");
	for (var i = 0, len = inputArray.length; i < len; i++) {
		var v = inputArray[i];
		if (v.options.length > 0) {
			v.options[0].selected = true;
		}
	}
}



 var highlightcolor='#fffce0'; //#ceffe7 #fffce0
    	//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
 var clickcolor='#51b2f6';
			function  changeto(){
				source=event.srcElement;
				if  (source.tagName=="TR"||source.tagName=="TABLE")
				return;
				while(source.tagName!="TD")
				source=source.parentElement;
				source=source.parentElement;
				cs  =  source.children;
				//alert(cs.length);
				if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
				for(i=0;i<cs.length;i++){
					cs[i].style.backgroundColor=highlightcolor;
				}
			}
			
			function  changeback(){
				if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
					return
				if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
					//source.style.backgroundColor=originalcolor
					for(i=0;i<cs.length;i++){
						cs[i].style.backgroundColor="";
					}
			}
				
			function  clickto(){
				source=event.srcElement;
				if  (source.tagName=="TR"||source.tagName=="TABLE")
					return;
				while(source.tagName!="TD")
					source=source.parentElement;
				source=source.parentElement;
				cs  =  source.children;
				//alert(cs.length);
				if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
					for(i=0;i<cs.length;i++){
						cs[i].style.backgroundColor=clickcolor;
					}
				else
					for(i=0;i<cs.length;i++){
						cs[i].style.backgroundColor="";
					}
			}




var waittime = 0;
var timer;
function showwaiting() {
	$.blockUI({
			message:$("#waitInfo_zone"),
			timeout:45000,
			css: { 
				padding:        0, 
				margin:         0,				
				width:			"325px",
				cursor:         'hand' 
			},
			overlayCSS:{
				opacity:        0,
				cursor:		  	'wait'
			}
		});
	waittime = 0;
	$("#time_counter").text(waittime);
	timer = window.setTimeout("tran()", 1000);	
	return true;
}
function tran() {
	waittime ++;
	$("#time_counter").text(waittime);
	timer = window.setTimeout("tran()", 1000);
}
function cancelTimerCounter() {
	$.unblockUI();
	clearInterval(timer);
}
function closewaiting(){
	waittime = 0;
	cancelTimerCounter();
}		
			
	