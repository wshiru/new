/*
 * Project platform
 *
 * Classname PageTag.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 上午11:53:19
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.yixin.framework.base.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * PAGE标签类
 * 
 * @version v1.0.0
 * @author 
 */
public class PageTag extends TagSupport {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -402356260143574182L;

	/** 标签ID */
	private String id;

	/** page类对象 */
	private Page<Object> page;

	/** 跳转的URL地址 */
	private String url;

	/** 通过get传递的参数键值对 */
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * 获取 标签ID
	 * 
	 * @return 标签ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 标签ID
	 * 
	 * @param id
	 *            标签ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 page类对象
	 * 
	 * @return page类对象
	 */
	public Page<Object> getPage() {
		return this.page;
	}

	/**
	 * 设置 page类对象
	 * 
	 * @param page
	 *            page类对象
	 */
	public void setPage(Page<Object> page) {
		this.page = page;
	}

	/**
	 * 获取 跳转的URL地址
	 * 
	 * @return 跳转的URL地址
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置 跳转的URL地址
	 * 
	 * @param url
	 *            跳转的URL地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取 通过get传递的参数键值对
	 * 
	 * @return 通过get传递的参数键值对
	 */
	public Map<String, Object> getParams() {
		return this.params;
	}

	/**
	 * 设置 通过get传递的参数键值对
	 * 
	 * @param params
	 *            通过get传递的参数键值对
	 */
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		String functionName = this.id + "_jumpPage";
		String functionFormat2Num = functionName + "_functionFormat2Num";
		String gotoPageFunctionName = this.id + "_gotoPage";
		// String checkGotoPageFunctionName = this.id + "_checkGotoPage";
		StringBuffer sb = new StringBuffer();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		String pageStyle = (String) request.getSession().getServletContext().getAttribute("theme");// 版面风格目录名称
		if (pageStyle == null || pageStyle.trim().length() <= 0) {
			pageStyle = "green";
		}
		if (this.page != null) {
			sb.append("<script type=\"text/javascript\">\r\n");
			sb.append("	function " + functionName + "(pn, ps, formId){\r\n");
			sb.append("		var formObj = document.getElementById(formId);\r\n");
			sb.append("		formObj.pn.value = pn;\r\n");
			sb.append("		formObj.ps.value = ps;\r\n");
			sb.append("		formObj.submit();\r\n");
			sb.append("		return false;\r\n");
			sb.append("}\r\n");
			sb.append("	function " + functionFormat2Num + "(obj){\r\n");
			sb.append("		obj.value=obj.value.replace(/\\D/g,'');\r\n");
			sb.append("		var pn = obj.value;\r\n");
			sb.append("     var varTotalPage = " + this.page.getTotalPageCount() + ";\r\n");
			sb.append("     var varGotoImg = document.getElementById('img_gotoPage');\r\n");
			sb.append("		if (pn > varTotalPage) {\r\n");
			sb.append("     	varGotoImg.src='" + basePath + "resource/theme/" + pageStyle
					+ "/images/page/go_gray.gif';\r\n");
			sb.append("     } else {\r\n");
			sb.append("         varGotoImg.src='" + basePath + "resource/theme/" + pageStyle
					+ "/images/page/go.gif';\r\n");
			sb.append("     }\r\n");
			sb.append("}\r\n");
			sb.append("	function " + gotoPageFunctionName + "(pn, ps, formId){\r\n");
			sb.append("     var varTotalPage = " + this.page.getTotalPageCount() + ";\r\n");
			sb.append("     var varGotoImg = document.getElementById('img_gotoPage');\r\n");
			sb.append("		if (pn > varTotalPage) {\r\n");
			sb.append("     	varGotoImg.src='" + basePath + "resource/theme/" + pageStyle
					+ "/images/page/go_gray.gif';\r\n");
			sb.append("     	return false;\r\n");
			sb.append("     } else {\r\n");
			sb.append("         varGotoImg.src='" + basePath + "resource/theme/" + pageStyle
					+ "/images/page/go.gif';\r\n");
			sb.append("         " + functionName + "(pn, ps, formId);\r\n");
			sb.append("         return true;\r\n");
			sb.append("     }\r\n");
			sb.append("}\r\n");
			sb.append("</script>\r\n");

			/* 添加Form表单 */
			if (this.url.startsWith("/")) {
				sb.append("<form id=\"" + this.id + "\" name=\"" + this.id + "_Name\" action=\"" + basePath + this.url
						+ "\" method=\"post\">\r\n");
			} else {
				sb.append("<form id=\"" + this.id + "\" name=\"" + this.id + "_Name\" action=\"" + this.url
						+ "\" method=\"post\">\r\n");
			}

			/* 默认设置当前页码pn为1,页每页显示记录数ps为10 */
			sb.append("	<input type=\"hidden\" name=\"ps\" value=\"10\" />\r\n");
			sb.append("	<input type=\"hidden\" name=\"pn\" value=\"1\" />\r\n");
			Iterator<String> iterator = this.params.keySet().iterator();
			while (iterator.hasNext()) {
				String name = iterator.next();
				String value = (String) params.get(name);
				sb.append("	<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\" />\r\n");
			}
			sb.append("<div class='left'>\r\n");
			sb.append("    共有<strong> " + this.page.getTotalRecordCount() + "</strong> 条记录，");
			sb.append("    当前第<strong> " + this.page.getCurrentPageNo() + "</strong>页，");
			sb.append("    共<strong> " + this.page.getTotalPageCount() + "</strong>页");
			sb.append("</div>");
			if (this.page.getCurrentPageNo() <= this.page.getFirstPageNo()
					&& this.page.getCurrentPageNo() == this.page.getTotalPageCount()
					|| this.page.getTotalPageCount() == 0) {
				/* 当前记录只在一页时，设置上一页、首页、下一页、尾页为灰色 */
				sb.append("<div class='right'>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/first_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/back_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/next_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/last_gray.gif' align='absmiddle' /></a>");
				sb.append("    转到第 <input name='gotoPn_' type='text' style='height:11px;width:32px;' size='8' maxlength='5'  onkeyup='"
						+ functionFormat2Num + "(this)'  value='" + this.page.getCurrentPageNo() + "' /> 页");
				sb.append("    <a href='#' onclick=\" return " + gotoPageFunctionName + "(document.forms['" + this.id
						+ "']['gotoPn_'].value==''?1: document.forms['" + this.id + "']['gotoPn_'].value, "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img id='img_gotoPage' src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/go.gif' align='absmiddle' /></a>");
				sb.append("</div>");
			} else if (this.page.getCurrentPageNo() <= this.page.getFirstPageNo()
					&& this.page.getCurrentPageNo() < this.page.getTotalPageCount()) {
				// 当前为首页时，设置上一页、首页为灰色
				sb.append("<div class='right'>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/first_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/back_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getNextPageNo() + ", "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/next.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getLastPageNo() + ", "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/last.gif' align='absmiddle' /></a>");
				sb.append("    转到第 <input name='gotoPn_' type='text' style='height:11px;width:32px;' size='8' maxlength='5'  onkeyup='"
						+ functionFormat2Num + "(this)'  value='" + this.page.getCurrentPageNo() + "' /> 页");
				sb.append("    <a href='#' onclick=\" return " + gotoPageFunctionName + "(document.forms['" + this.id
						+ "']['gotoPn_'].value==''?1: document.forms['" + this.id + "']['gotoPn_'].value, "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img id='img_gotoPage' src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/go.gif' align='absmiddle' /></a>");
				sb.append("</div>");
			} else if (this.page.getCurrentPageNo() >= this.page.getLastPageNo()) {
				// 当前为尾页时，设置下一页、尾页为灰色
				sb.append("<div class='right'>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(1, " + this.page.getPageSize()
						+ ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/" + pageStyle
						+ "/images/page/first.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getPreviousPageNo()
						+ ", " + this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/back.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/next_gray.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return false;\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/last_gray.gif' align='absmiddle' /></a>");
				sb.append("    转到第 <input name='gotoPn_' type='text' style='height:11px;width:32px;' size='8' maxlength='5'  onkeyup='"
						+ functionFormat2Num + "(this)'  value='" + this.page.getCurrentPageNo() + "' /> 页");
				sb.append("    <a href='#' onclick=\" return " + gotoPageFunctionName + "(document.forms['" + this.id
						+ "']['gotoPn_'].value==''?1: document.forms['" + this.id + "']['gotoPn_'].value, "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img id='img_gotoPage' src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/go.gif' align='absmiddle' /></a>");
				sb.append("</div>");
			} else {
				sb.append("<div class='right'>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(1, " + this.page.getPageSize()
						+ ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/" + pageStyle
						+ "/images/page/first.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getPreviousPageNo()
						+ ", " + this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/back.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getNextPageNo() + ", "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/next.gif' align='absmiddle' /></a>");
				sb.append("    <a href='#' onclick=\" return " + functionName + "(" + this.page.getLastPageNo() + ", "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img src='" + basePath + "resource/theme/"
						+ pageStyle + "/images/page/last.gif' align='absmiddle' /></a>");
				sb.append("    转到第 <input name='gotoPn_' type='text' style='height:11px;width:32px;' size='8' maxlength='5'  onkeyup='"
						+ functionFormat2Num + "(this)'  value='" + this.page.getCurrentPageNo() + "' /> 页");
				sb.append("    <a href='#' onclick=\" return " + gotoPageFunctionName + "(document.forms['" + this.id
						+ "']['gotoPn_'].value==''?1: document.forms['" + this.id + "']['gotoPn_'].value, "
						+ this.page.getPageSize() + ", '" + this.id + "')\"><img id='img_gotoPage' src='" + basePath
						+ "resource/theme/" + pageStyle + "/images/page/go.gif' align='absmiddle' /></a>");
				sb.append("</div>");
			}
			sb.append("</form>");
		}
		sb.append("");
		try {
			pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
