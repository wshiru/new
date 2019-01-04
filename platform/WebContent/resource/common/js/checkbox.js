
// ==================================================
// 功能说明：全选/全不选表单formObj下的所有checkbox
// 参数说明：chkObj - 全选/全不选的checkbox项
//         formObj - 要全选/全不选的的checkbox所在表单
// ==================================================
function selectOrUnselect(chkObj, formObj) {
	var ckbs = formObj.getElementsByTagName("input");
	if (chkObj.checked) {
		for (var i = 0, len = ckbs.length; i < len; i++) {
			if (ckbs[i].type == "checkbox") {
				ckbs[i].checked = true;
			}
		}
	} else {
		for (var i = 0, len = ckbs.length; i < len; i++) {
			if (ckbs[i].type == "checkbox") {
				ckbs[i].checked = false;
			}
		}
	}
}