package com.yixin.framework.ui.control.tree.operation.impl;


import java.util.List;

import com.yixin.framework.ui.control.tree.model.TreeInfo;
import com.yixin.framework.ui.control.tree.model.TreeUseServices;
import com.yixin.framework.ui.control.tree.operation.TreeOperation;


/**
 * 地质灾害树目录实现类
 * @author PC-ZCL
 *
 */
public class TreeOperationDZSHImpl  extends TreeOperation {

	private TreeUseServices treeUseServices;
	
	public TreeOperationDZSHImpl(TreeUseServices useServices) {
		this.treeUseServices = useServices;
	}
	
	@Override
	public List<TreeInfo> GetTreeInfos() {
		System.out.print("treeType: TreeOperationDZSHImpl");
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
