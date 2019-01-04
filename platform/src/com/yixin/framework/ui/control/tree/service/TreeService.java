package com.yixin.framework.ui.control.tree.service;

import java.util.List;

/**
 * 树目录业务服务接口
 */
import com.yixin.framework.ui.control.tree.model.TreeConfigParam;
import com.yixin.framework.ui.control.tree.model.TreeInfo;

public interface  TreeService {
  
	 /**
	  *  获取树目录数据
	  */
	 public  abstract List<TreeInfo> 	getTreeInfo();
	 	 
	 /** 获取配置信息**/
	 public  abstract TreeConfigParam   getConfigParams();

     /** 保存配置信息 **/
  	 public  abstract void   saveConfigParams(TreeConfigParam infos);
   
  	 
}
