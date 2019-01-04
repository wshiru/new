package com.yixin.framework.ui.apply.base;

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
	 
		public String getUIStyle(String Propertie) {
	    	String style = "style0";
		    Properties pro = new Properties();
			File file;

			try {
				file = new File(this.getClass().getResource("/").toURI().getPath() + "init-config.properties");
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					pro.load(fis);
					style =pro.getProperty(Propertie);
				} catch (FileNotFoundException ex) {
				} catch (IOException ex) {
				}

			} catch (URISyntaxException e) {

			}
			return style;
		}
		
	  
		public enum   UIStyle {
			
			style0("style0"),
		
			style1("style1");
			
			private  String code;
			
			private  UIStyle(String code){
				this.code = code;
			}
			
			public String getCode() {
				return code;
			}
			
	    }
		
		
        public enum   UIContorl_Name {
        	
        	Tree_Style("Tree_Style"),
        	Warning_Style("Warning_Style");
			
			private  String code;
			
			private  UIContorl_Name(String code){
				this.code = code;
			}
			
			public String getCode() {
				return code;
			}
			
	    }

		
}
