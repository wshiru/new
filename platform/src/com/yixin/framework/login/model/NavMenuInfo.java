package com.yixin.framework.login.model;

import java.io.Serializable;
import java.util.List;

import com.yixin.framework.system.model.AuthResource;

public class NavMenuInfo  implements Serializable {

	private static final long serialVersionUID = 345345568456875L;
	
	private String menuTypeName;
	
	private List<AuthResource> authResuorces;

	public String getMenuTypeName() {
		return menuTypeName;
	}

	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}

	public List<AuthResource> getAuthResuorces() {
		return authResuorces;
	}

	public void setAuthResuorces(List<AuthResource> authResuorces) {
		this.authResuorces = authResuorces;
	}
	
	

}
