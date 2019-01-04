package com.yixin.framework.ui.control.tree.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;


/**
 * 公用基类
 * @author PC-ZCL
 *
 */

public class base {

	    //从属性文件获取树类型
	    public String getTreeType() {
	    	String type = "ZX_TREE";
		    Properties pro = new Properties();
			File file;

			try {
				file = new File(this.getClass().getResource("/").toURI().getPath() + "init-config.properties");
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					pro.load(fis);
					type =pro.getProperty("Tree_Type");
				} catch (FileNotFoundException ex) {
				} catch (IOException ex) {
				}

			} catch (URISyntaxException e) {

			}
			return type;
		}
		
	    
	    //树结构类型
		public enum   TreeType {
			
			ZX_TREE("ZX_TREE"),
		
			DZSH_TREE("DZSH_TREE");
			
			private  String code;
			
			private  TreeType(String code){
				this.code = code;
			}
			public String getCode() {
				return code;
			}
			
	    }
		
}
