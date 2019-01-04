
if(engine == null) var engine={};

engine.getXmlHttp = function () {
	var xmlhttp;
	if (window.XMLHttpRequest){
	  xmlhttp=new XMLHttpRequest();
	}
	else{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

function get(url,string){
	alert(url);
}

engine.get = function(url,string){	
	var xmlhttp = this.getXmlHttp();	
	xmlhttp.onreadystatechange = function () {
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    	alert("success");
	    }
	} 	
	xmlhttp.open("GET",url,string,true);
	xmlhttp.send();
	return;	
}

