/*
 * Project platform
 *
 * Classname HiddenTag.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 上午11:54:04
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * 隐藏域标签。用于PAGE标签内的隐藏域
 * 
 * @version v1.0.0
 * @author 
 */

public class HiddenTag extends TagSupport {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 6686249909866626552L;

	/** name属性 */
	private String name;

	/** value属性 */
	private Object value;

	/**
	 * 获取 name属性
	 * 
	 * @return name属性
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 name属性
	 * 
	 * @param name
	 *            name属性
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 value属性
	 * 
	 * @return value属性
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * 设置 value属性
	 * 
	 * @param value
	 *            value属性
	 */
	public void setValue(Object value) throws JspException {
		if (value != null) {
			this.value = ExpressionEvaluatorManager
					.evaluate("value", value.toString(), Object.class, this, pageContext);
		} else {
			this.value = "";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		Tag t = findAncestorWithClass(this, PageTag.class);
		PageTag parent = (PageTag) t;
		parent.getParams().put(name, value);
		return super.doStartTag();
	}
}
