/*--------------------------------------------------|

  在 dTree2.05 的基础上进行优化
  2010 06 19 - 航天科工： zhanzhaolun    
  新增功能：
  增加参数： 在线状态 0：不在线 1：在线 使用不同的图标进行区分

  2.修改了CSS样式，去掉了根结点的显示



 this.id = id;     // 节点id

 this.pid = pid;     // 节点父id

 this.name = name;    // 节点显示名称;
 
 this.state = state; 	//是否在线

 this.url = url;     // 节点超链接地址;

 this.title = title;    // 节点Tips文本;

 this.target = target;   // 节点链接所打开的目标frame(_blank, _parent, _self, _top)

 this.icon = icon;    // 节点默认图标;

 this.iconOpen = iconOpen;  // 节点展开图标;

 this._io = open || false;  // 节点展开标识;

 this._is = true;    // 节点选中标识;

 this._ls = false;    // 同级最后节点标识;

 this._hc = false;    // 包含子节点标识;

 this._ai = 0;     // 节点在节点数组中的索引值，初始值为0

 this._p;      // 保存父节点对象;
|                                                  |

|--------------------------------------------------*/
//

/*
	@param actionUrl
	@param actionIcon 
	@param actionTitle 
	@param actionTarget 
	@return
*/
function NodeAction(actionUrl,actionIcon,actionTitle,actionTarget){
    this.actionUrl=actionUrl;
    this.actionIcon=actionIcon;
    this.actionTarget=actionTarget;
    this.actionTitle=actionTitle;
};

// Node object

function Node(id, pid, name, url, title, target, icon, iconOpen, open) {

	this.id = id;

	this.pid = pid;

	this.name = name;

	this.url = url;

	this.title = title;

	this.target = target;

	this.icon = icon;

	this.iconOpen = iconOpen;

	this._io = open || false;

	this._is = true;

	this._ls = false;

	this._hc = false;

	this._ai = 0;

	this._p;

};


/*

nodeActionList 
nodeActionList=new List();
List   java-like.util.js
state : 终端在线状态

*/

function Node(id, pid, name, url,state,title,target,icon, iconOpen, open,nodeActionList) {

    this.state =state;
    
	this.id = id;

	this.pid = pid;

	this.name = name;

	this.url = url;

	this.title = title;

	this.target = target;

	this.icon = icon;

	this.iconOpen = iconOpen;

	this._io = open || false;

	this._is = false;

	this._ls = false;

	this._hc = false;

	this._ai = 0;
    this.nodeActionList=nodeActionList;

	this._p;


};



// Tree object

function dTree(objName) {

	this.config = {
        //图片路锟斤拷
        imageDir                :null,
        target					: null,

		folderLinks			: true,

		useSelection		: true,

		useCookies			: true,

		useLines				: true,

		useIcons				: true,

		useStatusText		: true,

		closeSameLevel	: false,  // true同一级节点只能有一个处于展开状态;false反之

		inOrder					: false,

		check:false, 
		
		mycheckboxName: "mycheckbox" 
    }

	this.icon = {

		//root				:(this.config.imageDir!=null)?this.config.imageDir="/"+base.gif: 'img/base.gif',

		folder			: 'img/folder.gif',

		folderOpen	: 'img/folderopen.gif',

		node				: 'img/page.gif',

		empty				: 'img/empty.gif',

		line				: 'img/line.gif',

		join				: 'img/join.gif',

		joinBottom	: 'img/joinbottom.gif',

		plus				: 'img/plus.gif',

		plusBottom	: 'img/plusbottom.gif',

		minus				: 'img/minus.gif',

		minusBottom	: 'img/minusbottom.gif',

		nlPlus			: 'img/nolines_plus.gif',

		nlMinus			: 'img/nolines_minus.gif',
		iconUnCheckAll : 'img/iconUnCheckAll.gif',
		iconCheckAll :'img/iconCheckAll.gif',
		iconCheckGray : 'img/iconCheckGray.gif'
	};

	this.obj = objName;

	this.aNodes = [];

	this.aIndent = [];

	this.root = new Node(-1);

	this.selectedNode = null;

	this.selectedFound = false;

	this.completed = false;

};
// Tree object
dTree.prototype.reSetImagePath=function(){
 this.icon = {

		root				:(this.config.imageDir!=null)?this.config.imageDir+'/base.gif': 'img/base.gif',

		folder			: (this.config.imageDir!=null)?this.config.imageDir+'/folder.gif':'img/folder.gif',

		folderOpen	: (this.config.imageDir!=null)?this.config.imageDir+'/folderopen.gif':'img/folderopen.gif',

		node				: (this.config.imageDir!=null)?this.config.imageDir+'/page.gif':'img/page.gif',

		empty				: (this.config.imageDir!=null)?this.config.imageDir+'/empty.gif':'img/empty.gif',

		line				: (this.config.imageDir!=null)?this.config.imageDir+'/line.gif':'img/line.gif',

		join				: (this.config.imageDir!=null)?this.config.imageDir+'/join.gif':'img/join.gif',

		joinBottom	: (this.config.imageDir!=null)?this.config.imageDir+'/joinbottom.gif':'img/joinbottom.gif',

		plus				: (this.config.imageDir!=null)?this.config.imageDir+'/plus.gif':'img/plus.gif',

		plusBottom	: (this.config.imageDir!=null)?this.config.imageDir+'/plusbottom.gif':'img/plusbottom.gif',

		minus				: (this.config.imageDir!=null)?this.config.imageDir+'/minus.gif':'img/minus.gif',

		minusBottom	: (this.config.imageDir!=null)?this.config.imageDir+'/minusbottom.gif':'img/minusbottom.gif',

		nlPlus			: (this.config.imageDir!=null)?this.config.imageDir+'/nolines_plus.gif':'img/nolines_plus.gif',

		nlMinus			: (this.config.imageDir!=null)?this.config.imageDir+'/nolines_minus.gif':'img/nolines_minus.gif',
		//checkbox 
		iconUnCheckAll : (this.config.imageDir!=null)?this.config.imageDir+'/iconUnCheckAll.gif':'img/iconUnCheckAll.gif',
		iconCheckAll : (this.config.imageDir!=null)?this.config.imageDir+'/iconCheckAll.gif':'img/iconCheckAll.gif',
		iconCheckGray : (this.config.imageDir!=null)?this.config.imageDir+'/iconCheckGray.gif':'img/iconCheckGray.gif'
	};
}



// Adds a new node to the node array

dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open) {

	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open);

};
// Adds a new node to the node array

dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open,actionUrl,actionIcon) {
    this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open,actionUrl,actionIcon);

};



// Open/close all nodes

dTree.prototype.openAll = function() {

	this.oAll(true);

};

dTree.prototype.closeAll = function() {

	this.oAll(false);

};



// Outputs the tree to the page

dTree.prototype.toString = function() {

	var str = '<div id="dtree" class="dtree">\n';

	if (document.getElementById) {

		if (this.config.useCookies) this.selectedNode = this.getSelected();

		str += this.addNode(this.root);//this.root

	} else str += 'Browser not supported.';

	str += '</div>';

	if (!this.selectedFound) this.selectedNode = null;

	this.completed = true;

     

	return str;
       

};


// Creates the tree structure

dTree.prototype.addNode = function(pNode) {

	var str = '';

	var n=0;

	if (this.config.inOrder) n = pNode._ai;

	for (n; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == pNode.id) {

			var cn = this.aNodes[n];

			cn._p = pNode;

			cn._ai = n;

			this.setCS(cn);

			if (!cn.target && this.config.target) cn.target = this.config.target;

			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);

			if (!this.config.folderLinks && cn._hc) cn.url = null;

			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {

					cn._is = true;

					this.selectedNode = n;

					this.selectedFound = true;

			}

			str += this.node(cn, n);

			if (cn._ls) break;

		}

	}

	return str;

};



// Creates the node icon, url and text

/*dTree.prototype.node = function(node, nodeId) {
      // 节点前的线条或空白图标
	var str = '<div class="dTreeNode">' + this.indent(node, nodeId);

	if (this.config.useIcons) {
              // 根据节点类型和状态确定节点的默认图标
		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);

		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
		
		if(this.config.check==true){
			str+= '<img checked=0  src="'+this.icon.iconUnCheckAll+'" value='+node.id+' id="c'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.cc('+nodeId+')"/>';
			str+='<input type="checkbox" style="display:none" name="'+this.config.mycheckboxName+'"  value='+node.id+' id="ccc'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.changeCheckbox('+nodeId+')"/>';
		}
		
                if (this.root.id == node.pid) {

               	        node.icon = this.icon.root;

			node.iconOpen = this.icon.root;

		}
              
 
              var i =0;
              if (node.state){
                 if (node.state=='1'){    //state为1时设备在线
                   i = 1;
                 }
              }
              
              var  pic ='';
              if (this.config.imageDir !=null ){
                 pic =this.config.imageDir +'/online.gif';  
              }
              else {
                 pic = 'img/online.gif';
              }
              
              if (i == 1 ) {
                 str += '<img id="i' + this.obj + nodeId + '" src="' + pic + '" alt="" />';

              }else  {
		   str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';
              }
	}
	
	// 节点文本及动作方法(带超链接、不带超链接)
	if (node.url) {
              
		//str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
             // str +='<div class="mydiv" onmouseover="' + 'this.className=\'divmousemove\'" onmouseout="this.className=\'divmouseout\'">';  hidefocus="on" 
 

              str += '<a id="' + node.terminalid + '" name="' + node.plcid + '"  onclick="onclickNode(this)"  class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
           //   alert(str);
                
               

               // alert(str);

		if (node.title) str += ' title="' + node.title + '"';

		if (node.target) str += ' target="' + node.target + '"';

		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';

		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))

			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';

		str += '>';

	}

	else if ((!this.config.folderLinks || !node.url) && node._hc ){   //&& node.pid != this.root.id

		str += '<a href="#" onclick="javascript:' + this.obj + '.o(' + nodeId + ');" class="node">';
	}
	str += node.name;

    if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';

    
    if(node.nodeActionList){

        for(i=0;i<node.nodeActionList.size();i++){
            nodeActionObj= node.nodeActionList.get(i);
            str+="&nbsp;";
            
          
          //  if(nodeActionObj.actionUrl.indexOf("javascript:")==-1){
	//			str+='<a href="' +nodeActionObj.actionUrl+ '" ';
          //      str+=' target="' +((nodeActionObj.actionTarget) ? nodeActionObj.actionTarget : this.config.target)  + '" ';
          //  }else{
				str+='<a href="#" onclick="' +nodeActionObj.actionUrl+ '" ';
	//		}
            str+=' >';
            str += '<img id="i' + this.obj + nodeId + '" src="' + ((nodeActionObj.actionIcon) ? nodeActionObj.actionIcon : node.icon)
                    + '" alt="'+nodeActionObj.actionTitle+'" />';
            str+='</a>';
        }

    }


    str += '</div>';


    if (node._hc) {

		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';

		str += this.addNode(node);

		str += '</div>';

	}
	

    this.aIndent.pop();
    return str;


};
*/



// Creates the node icon, url and text

dTree.prototype.node = function(node, nodeId) {

	var str = '<div class="dTreeNode">' + this.indent(node, nodeId);

	if (this.config.useIcons) {

		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);

		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
		//鏂板姞鐨?checkbox
		if(this.config.check==true){
			str+= '<img checked=0  src="'+this.icon.iconUnCheckAll+'" value='+node.id+' id="c'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.cc('+nodeId+')"/>';
			str+='<input type="checkbox" style="display:none" name="'+this.config.mycheckboxName+'"  value='+node.id+' id="ccc'+  this.obj + nodeId + '" onclick="javascript:'+this.obj+'.changeCheckbox('+nodeId+')"/>';
		}
		if (this.root.id == node.pid) {

			node.icon = this.icon.root;
			node.iconOpen = this.icon.root;

		}
		
        
        /*var p = 0;
        if ( node.state ) {  
           if (node.state == 1){
              p =1;
           }   
        }
        
        if (p == 1){
             var  pic= this.config.imageDir +'/online.gif';   
             str += '<img id="i' + this.obj + nodeId + '" src="' + pic + '" alt="" />';   
        }   
        else {
		*/
		
	    str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io || node.state =='1') ? node.iconOpen : node.icon) + '" alt="" />';
	}
	
		
	if (node.url) {
     
                str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"'; 
        
                str += ' onclick="iniImages()" ';

                if (node.title) str += ' title="' + node.title + '"';

		if (node.target) str += ' target="' + node.target + '"';

		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';
                 
                

		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))

			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';

		str += '>';

	}

	else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id){

		str += '<a href="#" onclick="javascript:' + this.obj + '.o(' + nodeId + ');" class="node">';
	}
	if(node.title){
		str += node.title;
	}else{
		str += node.name;
	}

    if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';

    ////////////// 锟睫改匡拷始 ////////
    //锟斤拷锟?锟斤拷锟斤拷图锟斤拷锟絣锟斤拷
    if(node.nodeActionList){

        for(i=0;i<node.nodeActionList.size();i++){
            nodeActionObj= node.nodeActionList.get(i);
            str+="&nbsp;";
            
            //锟叫讹拷 actionUrl锟角凤拷为java锟脚憋拷锟斤拷锟斤拷锟轿猨ava锟脚憋拷锟斤拷锟杰癸拷锟斤拷target 锟斤拷锟斤拷
            if(nodeActionObj.actionUrl.indexOf("javascript:")==-1){
				str+='<a href="' +nodeActionObj.actionUrl+ '" ';
                str+=' target="' +((nodeActionObj.actionTarget) ? nodeActionObj.actionTarget : this.config.target)  + '" ';
            }else{
				str+='<a href="#" onclick="' +nodeActionObj.actionUrl+ '" ';
			}
            str+=' >';
            str += '<img id="i' + this.obj + nodeId + '" src="' + ((nodeActionObj.actionIcon) ? nodeActionObj.actionIcon : node.icon)
                    + '" alt="'+nodeActionObj.actionTitle+'" />';
            str+='</a>';
        }

    }

    /////////// 锟斤拷伽锟斤拷锟斤拷 /////////
    str += '</div>';


    if (node._hc) {

		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';

		str += this.addNode(node);

		str += '</div>';

	}
	

    this.aIndent.pop();
    return str;

};



// Adds the empty and line icons

dTree.prototype.indent = function(node, nodeId) {

	var str = '';

	//if (this.root.id != node.pid) {

		for (var n=0; n<this.aIndent.length; n++)

		str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';

		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);

               

		if (node._hc) {
                   
			str += '<a href="#" onclick="javascript: ' + this.obj + '.o(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';

			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;

			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );

			str += '" alt="" /></a>';

		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';

	//}

        
        

	return str;

};



// Checks if a node has any children and if it is the last sibling

dTree.prototype.setCS = function(node) {

	var lastId;

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id) node._hc = true;

		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;

	}

	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;

	var cn = this.aNodes[id];

	if (cn._hc && !this.config.folderLinks) return;

	if (this.selectedNode != id) {

		if (this.selectedNode || this.selectedNode==0) {

			eOld = document.getElementById("s" + this.obj + this.selectedNode);

			eOld.className = "node";

		}

		eNew = document.getElementById("s" + this.obj + id);

		eNew.className = "nodeSel";

		this.selectedNode = id;

		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);

	}

};



// Toggle Open or close

dTree.prototype.o = function(id) {

	var cn = this.aNodes[id];

	this.nodeStatus(!cn._io, id, cn._ls);

	cn._io = !cn._io;

	if (this.config.closeSameLevel) this.closeLevel(cn);

	if (this.config.useCookies) this.updateCookie();

};



// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc ) {     //&& this.aNodes[n].pid != this.root.id

			this.nodeStatus(status, n, this.aNodes[n]._ls)

			this.aNodes[n]._io = status;

		}

	}

	if (this.config.useCookies) this.updateCookie();

};

//在线终端

dTree.prototype.refreshOnline = function(name , state) {

	for (var n=0; n<this.aNodes.length; n++) {
		var id = n ;
		if(name == this.aNodes[id].name){
			//alert(name +"="+this.aNodes[id].state +' '+state);
			if(this.aNodes[id].state == state){
				return;
			}						
			var eJoin	= document.getElementById('j' + this.obj + id);
			if (this.config.useIcons) {
				var eIcon	= document.getElementById('i' + this.obj + id);
				eIcon.src = (state == '1') ? this.aNodes[id].iconOpen : this.aNodes[id].icon;
				this.aNodes[id].state = state;
			}
			break;
		}
	}	
};

// Opens the tree to a specific node

dTree.prototype.openTo = function(nId, bSelect, bFirst) {

	if (!bFirst) {

		for (var n=0; n<this.aNodes.length; n++) {

			if (this.aNodes[n].id == nId) {

				nId=n;

				break;

			}

		}

	}

	var cn=this.aNodes[nId];

	if (cn.pid==this.root.id || !cn._p) return;

	cn._io = true;

	cn._is = bSelect;

	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);

	if (this.completed && bSelect) this.s(cn._ai);

	else if (bSelect) this._sn=cn._ai;

	this.openTo(cn._p._ai, false, true);

};



// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

}



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

}



// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {

	eDiv	= document.getElementById('d' + this.obj + id);

	eJoin	= document.getElementById('j' + this.obj + id);

	if (this.config.useIcons) {

		eIcon	= document.getElementById('i' + this.obj + id);

		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;

	}

	eJoin.src = (this.config.useLines)?

	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):

	((status)?this.icon.nlMinus:this.icon.nlPlus);

	eDiv.style.display = (status) ? 'block': 'none';

};





// [Cookie] Clears a cookie

dTree.prototype.clearCookie = function() {

	var now = new Date();

	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);

	this.setCookie('co'+this.obj, 'cookieValue', yesterday);

	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);

};



// [Cookie] Sets value in a cookie

dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {

	document.cookie =

		escape(cookieName) + '=' + escape(cookieValue)

		+ (expires ? '; expires=' + expires.toGMTString() : '')

		+ (path ? '; path=' + path : '')

		+ (domain ? '; domain=' + domain : '')

		+ (secure ? '; secure' : '');

};



// [Cookie] Gets a value from a cookie

dTree.prototype.getCookie = function(cookieName) {

	var cookieValue = '';

	var posName = document.cookie.indexOf(escape(cookieName) + '=');

	if (posName != -1) {

		var posValue = posName + (escape(cookieName) + '=').length;

		var endPos = document.cookie.indexOf(';', posValue);

		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));

		else cookieValue = unescape(document.cookie.substring(posValue));

	}

	return (cookieValue);

};



// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};


/*
 dTree.prototype.changeCheckbox=function(nodeId){
  var cs = document.getElementById("ccc"+this.obj+nodeId).checked;
  var n,node = this.aNodes[nodeId];
  var len =this.aNodes.length;
  for (n=0; n<len; n++) {
	   if (this.aNodes[n].pid == node.id) {
	    document.getElementById("ccc"+this.obj+n).checked=cs;
	    this.changeCheckbox(n);  
	   }
  }
  if(cs==false)return;
  var pid=node.pid;
  var bSearch;
  do{
   bSearch=false;
   for(n=0;n<len;n++){
    if(this.aNodes[n].id==pid){
     document.getElementById("ccc"+this.obj+n).checked=true;
     pid=this.aNodes[n].pid;
     bSearch = true;    
     break;
    }
   }
  }while(bSearch==true);
 }
*/

dTree.prototype.cc=function(nodeId){
	
	var currentNode =document.getElementById("c"+this.obj+nodeId);
	var cs = currentNode.checked;
	
	if(cs<1){
		this.changeChildNodes(nodeId,1,this.icon.iconCheckAll);
	}else{
		this.changeChildNodes(nodeId,0,this.icon.iconUnCheckAll);
	}
	
	var n,node = this.aNodes[nodeId];
  	var len =this.aNodes.length;
  	var pid=node.pid;
	for(n=0;n<len;n++){
	    if(this.aNodes[n].id==pid){
		     this.changeParentNodes(n);
			 break;
	    }
   	}
}


dTree.prototype.changeChildNodes=function(nodeId,status,img){
	document.getElementById("c"+this.obj+nodeId).checked=status;
	document.getElementById("c"+this.obj+nodeId).src=img;
	if(status>0){
		document.getElementById("ccc"+this.obj+nodeId).checked=true;
	}else{
		document.getElementById("ccc"+this.obj+nodeId).checked=false;
	}
	var n,node = this.aNodes[nodeId];
  	var len =this.aNodes.length;
  	for (n=0; n<len; n++) {
	 	if (this.aNodes[n].pid == node.id) {
		    document.getElementById("c"+this.obj+n).checked=status;
		    document.getElementById("c"+this.obj+n).src=img;
		    if(status>0){
				document.getElementById("ccc"+this.obj+nodeId).checked=true;
			}else{
				document.getElementById("ccc"+this.obj+nodeId).checked=false;
			}	
		    this.changeChildNodes(n,status,img);  
	  	}
  	}
}


dTree.prototype.changeParentNodes=function(nodeId){
	this.changeParentNode(nodeId);
	var n,node = this.aNodes[nodeId];
  	var len =this.aNodes.length;
	var pid=node.pid;
  	var bSearch;
  	do{
   		bSearch=false;
   		for(n=0;n<len;n++){
	   		if(this.aNodes[n].id==pid){
		     	this.changeParentNode(n);
		     	pid=this.aNodes[n].pid;
		     	bSearch = true;    
		    	break;
	    	}
   		}
  	}while(bSearch==true);
}



dTree.prototype.changeParentNode=function(nodeId){
  	var n,node = this.aNodes[nodeId];
  	var len =this.aNodes.length;
  	var statuFlag;
  	var tempNum=0;
  	var childsCount=0;
  	for(n=0; n<len; n++){
	 	if (this.aNodes[n].pid == node.id) {
	 		childsCount++;
		    var tempNode =  document.getElementById("c"+this.obj+n);
		    if(tempNode.checked<0){
		    	tempNum=-1;
		    	break;
		    }else{
		    	if(tempNode.checked==1){
		    		tempNum+=1;
		    	}
		    }	
	  	}
  	}
  	if(tempNum==-1||(tempNum<childsCount&&tempNum>0)){
  		document.getElementById("c"+this.obj+nodeId).checked=-1;
  		document.getElementById("c"+this.obj+nodeId).src=this.icon.iconCheckGray;
  		document.getElementById("ccc"+this.obj+nodeId).checked=true;
  	}else if(tempNum==0){
  		document.getElementById("c"+this.obj+nodeId).checked=0;
  		document.getElementById("c"+this.obj+nodeId).src=this.icon.iconUnCheckAll;
  		document.getElementById("ccc"+this.obj+nodeId).checked=false;
  	}else if(tempNum==childsCount){
  		document.getElementById("c"+this.obj+nodeId).checked=1;
  		document.getElementById("c"+this.obj+nodeId).src=this.icon.iconCheckAll;
  		document.getElementById("ccc"+this.obj+nodeId).checked=true;
  	} 	
}


dTree.prototype.setCheck=function(nodeIds){
	var tempIds = this.getLeafNodes(nodeIds);   
	var n;
  	var len =this.aNodes.length;
  	for (n=0; n<len; n++) {
	 	var nodeObj = document.getElementById("c"+this.obj+n);	 	
	 	if(this.isInIds(nodeObj.value,tempIds)){
	 		nodeObj.click();		
	 	}
  	}
}



dTree.prototype.isInIds=function(id,ids){
	var idarray = ids.split(",");
	for(var i=0;i<idarray.length;i++){
		if(id==idarray[i]){
			return true;
		}
	}
	return false;
}


dTree.prototype.getLeafNodes=function(ids){
	var tempIds = ids.split(",");
	var returnIds="";
	for(var i=0;i<tempIds.length;i++){
		if(this.isLeafNode(tempIds[i])){
			returnIds+=","+tempIds[i];
		}
	}
	return returnIds;
}



dTree.prototype.isLeafNode=function(id){
	var len =this.aNodes.length;
  	for (n=0; n<len; n++) {	 		 	
	 	if(this.aNodes[n].pid==id){
	 		return false;	
	 	}
  	}
  	return true;
}


// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	}

};