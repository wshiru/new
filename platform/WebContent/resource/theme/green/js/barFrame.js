var scrollElem = document.getElementById("scrolllayer");
var leftElem = document.getElementById("scrollmessage");
var preTop = 0;
var currentTop = 0;
var stoptime = 0;

try {
	scrollElem.scrollTop = 0;
	scrollElem.style.height = 17;
	scrollElem.style.width = 650;
	scrollElem.style.overflow = 'hidden';
	scrollElem.noWrap = true;
	scrollElem.appendChild(leftElem.cloneNode(true));
	setInterval("scrollUp();", 3);

} catch (e) {

}

function scrollUp() {
	if (noWarningData) {
		return;
	}

	currentTop += 1;
	if (currentTop == 18) {
		stoptime += 1;
		currentTop -= 1;
		if (stoptime == 450) {
			currentTop = 0;
			stoptime = 0;
		}
	} else {
		preTop = scrollElem.scrollTop;
		scrollElem.scrollTop += 1;
		if (preTop == scrollElem.scrollTop) {
			currentTop = 0;
			stoptime = 0;
			preTop = 0;
			scrollElem.scrollTop = 0;
		}
	}

}