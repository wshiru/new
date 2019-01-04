/**
 * 说明：清空表单内容。将表单formObj内的text、password、file的值设空；checkbox、radio的选中取消；select项中有选项时，选择第一项
 * 
 * 参数：formObj - 表单对象
 */
function clearForm(formObj) {
	var inputArray = formObj.getElementsByTagName("input");
	for ( var i = 0, len = inputArray.length; i < len; i++) {
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
	for ( var i = 0, len = inputArray.length; i < len; i++) {
		var v = inputArray[i];
		if (v.options.length > 0) {
			v.options[0].selected = true;
		}
	}
}

/**
 * 打开新窗口
 * @param url
 * 			新窗口页面地址
 * @param width
 * 			宽度
 * @param height
 * 			高度
 * @param top
 * 			上部距离
 * @param left
 * 			左边距离
 */
function openNewWin(url, width, height, top, left){
	width = width || 1050;
	height = height || 560;
	top = top || 150;
	left = left || 100;
	//_blank
	window.open("_blank", "openNewWin", "height=" + height + ",width="+ width +",top=" + top + 
			",left="+ left + ",toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
	
	window.open(url, "openNewWin", "height=" + height + ",width="+ width +",top=" + top + 
					",left="+ left + ",toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
}