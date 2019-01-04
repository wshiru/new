function showHideMenu() {
	var key = document.getElementById("key");
	document.getElementById("div_subnavDetail").className = "";
	document.getElementById("div_mainDetail").className = "";

	if (key.innerHTML == "显示") {
		document.getElementById("div_subnavDetail").className = "div_mainMenu_Show";
		document.getElementById("div_mainDetail").className = "div_mainMenu_Hide";
		key.innerHTML = "隐藏";
	} else {
		document.getElementById("div_subnavDetail").className = "div_mainMenu_Hide";
		document.getElementById("div_mainDetail").className = "div_mainMenu_Show";
		key.innerHTML = "显示";
	}
}

function hideMenu() {
	var key = document.getElementById("key");
	document.getElementById("div_subnavDetail").className = "";
	document.getElementById("div_mainDetail").className = "";
	document.getElementById("div_subnavDetail").className = "div_mainMenu_Hide";
	document.getElementById("div_mainDetail").className = "div_mainMenu_Show";
	key.innerHTML = "显示";
}

function showMenu() {
	var key = document.getElementById("key");
	document.getElementById("div_subnavDetail").className = "";
	document.getElementById("div_mainDetail").className = "";
	document.getElementById("div_mainDetail").className = "div_mainMenu_Hide";
	document.getElementById("div_subnavDetail").className = "div_mainMenu_Show";
	key.innerHTML = "隐藏";
}

var mainFrameHeight = 0;
function init() {
	try {
		var bHeight = document.body.scrollHeight;
		var dHeight = document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		var iframeDiv = document.getElementById("div_mainDetail");
		var iframe = document.getElementById("mainFrame");
		var getHeight = height - 24;
		// 只有窗口高度被用户改变的情况下才改变
		if (mainFrameHeight != getHeight) {
			iframeDiv.style.height = getHeight + "px";
			iframe.style.height = getHeight + "px";
			mainFrameHeight = getHeight;
		}
	} catch (ex) {
	}
}