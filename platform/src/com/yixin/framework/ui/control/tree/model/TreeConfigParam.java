package com.yixin.framework.ui.control.tree.model;


/**
 * 树结构相关配置参数类
 * @author PC-ZCL
 *
 */
public class TreeConfigParam {
 
	/** 是否启用 0: 停用  1:启用 **/
	private  Integer State = 0;
	
	
	/** 树结构类型 **/
	private  String treeType ;
	
	
	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	
	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}
	
}
