var icon = '';
var checkid = '';
var systemMonitor = null;
var systemUrl = '';

function changimg(obj) {
	var i = 0;

	if (obj.id == 'img' + i) {
		checkid = obj.id;
		icon = 'menu_xtsz1.png';
	}
	document.getElementById("img" + i).src = '../../../resource/theme/green/images/menuFrame/menu_xtsz1.png';
	i = i + 1;

	if (obj.id == 'img' + i) {
		checkid = obj.id;
		icon = 'menu_tagl1.png';
	}
	document.getElementById("img" + i).src = '../../../resource/theme/green/images/menuFrame/menu_tagl1.png';
	i = i + 1;

	if (obj.id == 'img' + i) {
		checkid = obj.id;
		icon = 'menu_xtjk1.png';
	}
	document.getElementById("img" + i).src = '../../../resource/theme/green/images/menuFrame/menu_xtjk1.png';
	i = i + 1;

	if (i > 1) {
		var downsrc = icon.slice(0, icon.length - 5) + '2.png';
		obj.src = '../../../resource/theme/green/images/menuFrame/' + downsrc;

	}
}

function fixHeight() {
	var bodyHeight = document.body.offsetHeight;

	var div1Height = document.getElementById("nav_text").offsetHeight;
	var div2Height = document.getElementById("nav").offsetHeight;
	var div3 = document.getElementById("menu_trees");
	div3.style.height = bodyHeight - div2Height - div1Height;

}