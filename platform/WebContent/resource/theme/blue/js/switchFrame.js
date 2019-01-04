function Submit_onclick() {
	if (parent.bottomFrameSet.cols == "185,8,*") {
		parent.bottomFrameSet.cols = "0,8,*";
		document.getElementById("ImgArrow").src = "../images/switchFrame/center_navpoint2.png";
		document.getElementById("ImgArrow").alt = "打开左侧导航栏";
	} else {
		parent.bottomFrameSet.cols = "185,8,*";
		document.getElementById("ImgArrow").src = "../images/switchFrame/center_navpoint.png";
		document.getElementById("ImgArrow").alt = "隐藏左侧导航栏";
	}
}
function MyLoad() {
	if (window.parent.location.href.indexOf("MainUrl") > 0) {
		window.top.hSwitchFrame.document.getElementById("ImgArrow").src = "../images/switchFrame/center_navpoint2.png";
	}
}