package com.yixin.framework.login.model;

import java.io.Serializable;

/**
 * 系统树目录数据类
 * 
 * @author 梁立全

 * 2010 06 25
 */

public class TreeInfo implements Serializable {

	private static final long serialVersionUID = 7981209814367190875L;
	
	/*结点ID*/
	private String  id;
	/*父结点ID */
	private String  pid;
	/*树结点名称*/
	private String  name;
	/*URL链接地址 */	
	private String  url;
	/*采集单元是否在线：0：不在线 1: 在线 */
	private Integer online =0;
	/* 新页面打开的窗口位置*/
	private String  target="mainFrame";
	/* 鼠标移动至URL显示的名称*/
	private String  title;
	
	/* 图标名称*/
	private String  icon;
	/*节点类型：0：根结点  1  线路 2 ：杆塔 3 ：监测装置*/
	private Integer nodeType;
	
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
