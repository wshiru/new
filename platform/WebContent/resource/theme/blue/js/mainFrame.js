function warnings_page_jumpPage(pn, ps, formId) {
	var formObj = document.getElementById(formId);
	formObj.pn.value = pn;
	formObj.ps.value = ps;
	formObj.submit();
	return false;
}
function warnings_page_jumpPage_functionFormat2Num(obj) {
	obj.value = obj.value.replace(/\D/g, '');
	var pn = obj.value;
	var varTotalPage = 0;
	var varGotoImg = document.getElementById('img_gotoPage');
	if (pn > varTotalPage) {
		varGotoImg.src = '../../../resource/theme/green/images/tab/go_gray.gif';
	} else {
		varGotoImg.src = '../../../resource/theme/green/images/tab/go.gif';
	}
}
function warnings_page_gotoPage(pn, ps, formId) {
	var varTotalPage = 0;
	var varGotoImg = document.getElementById('img_gotoPage');
	if (pn > varTotalPage) {
		varGotoImg.src = '../../../resource/theme/green/images/tab/go_gray.gif';
		return false;
	} else {
		varGotoImg.src = '../../../resource/theme/green/images/tab/go.gif';
		warnings_page_jumpPage(pn, ps, formId);
		return true;
	}
}