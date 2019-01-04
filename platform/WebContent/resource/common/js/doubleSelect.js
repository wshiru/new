//ChangeSelect(上一级的值,下一级Select控件的ID值,下一级Select控件要选中的值(即value而非text),数据源数组名,默认显示字符(如:请选择...如果不写的话会用默认值填充))，第一级的上级值为0
function ChangeSelect(ParentValue, NextId, NextSelectedValue, ArrObj, DefaultStr,NoOnChange) {
	StrObj = eval(document.getElementById(NextId));
	StrObj.length = 0;

	//判断它是二级数据源，还是三级
	if (ArrObj.length > 0) {
		if (ArrObj[0].length == 2) {
			ArrNum = 0;
		} else {
			ArrNum = 2;
		}
	}

			

	//显示所有列表
	for (i = 0; i < ArrObj.length; i = i + 1) {
		if (i == 0) {
			if (DefaultStr == undefined )
				DefaultStr="--请选择--";
			StrObj.options[StrObj.length] = new Option(DefaultStr, "");
		}
		if (ArrObj[i][1] == ParentValue) {
			StrObj.options[StrObj.length] = new Option(ArrObj[i][0], ArrObj[i][ArrNum]);
		}
	}

			

	//选中列表内某一项
	for (i = 0; i < StrObj.length; i = i + 1) {
		if (StrObj.options[i].value == NextSelectedValue) {
			StrObj.options[i].selected = true;
		}
	}
		
 	
	//激发下一级的onchange事件以实现多级级联
	
	
		if(!(NoOnChange == true)){
			StrObj.onchange();
		}
	 

}

