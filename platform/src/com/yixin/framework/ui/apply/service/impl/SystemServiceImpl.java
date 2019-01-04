package com.yixin.framework.ui.apply.service.impl;

import com.yixin.framework.ui.apply.base.base;
import com.yixin.framework.ui.apply.html.impl.TreeStyle0HtmlImpl;
import com.yixin.framework.ui.apply.html.impl.TreeStyle1HtmlImpl;
import com.yixin.framework.ui.apply.service.ApplyService;


public class SystemServiceImpl  extends  base implements ApplyService {

	@Override
	public String getTreeViewHTML() {
		String  ret = null;
		
		String controlName = super.getUIStyle(UIContorl_Name.Tree_Style.getCode());
		
		//样式1
		if ( UIStyle.style0.getCode().equals(controlName) ){
			ret = new  TreeStyle0HtmlImpl().getTreeViewHTML();
		}
		
		//样式2
	    if ( UIStyle.style1.getCode().equals(controlName) ){
		    ret = new  TreeStyle1HtmlImpl().getTreeViewHTML();
	    }
    
		return  ret;
	}

	@Override
	public String getWarningViewHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWeatherViewHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMainLogoViewHTML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuViewHTML() {
		// TODO Auto-generated method stub
		return null;
	}

}
