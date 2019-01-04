package com.yixin.framework.ui.control.tree.operation.impl;


import java.util.List;

import com.yixin.framework.ui.control.tree.model.TreeInfo;
import com.yixin.framework.ui.control.tree.model.TreeUseServices;
import com.yixin.framework.ui.control.tree.operation.TreeOperation;


public class TreeOperationImpl  extends TreeOperation {

    private TreeUseServices treeUseServices;
	
	public TreeOperationImpl(TreeUseServices useServices) {
		this.treeUseServices = useServices;
	}

	
	@Override
	public List<TreeInfo> GetTreeInfos() {
		System.out.print("treeType: TreeOperationImpl");
		// TODO Auto-generated method stub
		return null;
	}
   
	
	public TreeUseServices getTreeUseServices() {
		return treeUseServices;
	}


	public void setTreeUseServices(TreeUseServices treeUseServices) {
		this.treeUseServices = treeUseServices;
	}
	
	
}
