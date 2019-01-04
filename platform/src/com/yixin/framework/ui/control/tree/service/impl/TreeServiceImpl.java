
package com.yixin.framework.ui.control.tree.service.impl;


import java.util.List;

import com.yixin.framework.ui.control.tree.base.base;
import com.yixin.framework.ui.control.tree.model.TreeConfigParam;
import com.yixin.framework.ui.control.tree.model.TreeInfo;
import com.yixin.framework.ui.control.tree.model.TreeUseServices;
import com.yixin.framework.ui.control.tree.operation.impl.TreeOperationDZSHImpl;
import com.yixin.framework.ui.control.tree.operation.impl.TreeOperationImpl;
import com.yixin.framework.ui.control.tree.service.TreeService;


public class TreeServiceImpl   extends base implements  TreeService  {
	  

	@Override
	public List<TreeInfo> getTreeInfo() {
		//ADDTO: 如果控件状态为停用 直接返回null 值
		
		List<TreeInfo> infos = null;
		//在线监测树
		if ( TreeType.ZX_TREE.getCode().equals(getTreeType()) ) {
		    infos = new TreeOperationImpl(new TreeUseServices()).GetTreeInfos();
		}
		//地质灾害树
		if ( TreeType.DZSH_TREE.getCode().equals(getTreeType()) ){
			infos  = new TreeOperationDZSHImpl(new TreeUseServices()).GetTreeInfos();
		}
		return  infos;
	}

	@Override
	public TreeConfigParam getConfigParams() {
		TreeConfigParam  treeConfigParams = new TreeConfigParam();
	    treeConfigParams.setTreeType(super.getTreeType());
	    return  treeConfigParams ;
	}

	@Override
	public void saveConfigParams(TreeConfigParam infos) {
		// TODO Auto-generated method stub
	}
	
}
