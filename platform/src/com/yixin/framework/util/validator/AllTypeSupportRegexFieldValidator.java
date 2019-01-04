/*
 * Project platform
 *
 * Class AllTypeSupportRegexFieldValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-27 上午10:37:02
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util.validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

/**
 * 重写com.opensymphony.xwork2.validator.validators.RegexFieldValidator类，
 * 使其在Integer，Double等类型时，也支持正则表达式的校验
 * 
 * @version v1.0.0
 * @author 
 * @see com.opensymphony.xwork2.validator.validators.RegexFieldValidator
 */
public class AllTypeSupportRegexFieldValidator extends FieldValidatorSupport {
	private String expression;
	private boolean caseSensitive = true;
	private boolean trim = true;

	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		Object v = this.getFieldValue(fieldName, object);
		// if there is no value - don't do comparison
		// if a value is required, a required validator should be added to the
		// field
		if (v == null || expression == null) {
			return;
		}

		// convert to string, so it can also work on integer or double, and so
		// on
		// 下面这句是增加的
		String value = String.valueOf(v);
		try {
			if (v instanceof Double) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof Float) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof Long) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof Integer) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof Short) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof Byte) {
				BigDecimal b = new BigDecimal(value);
				value = b.toPlainString();
			} else if (v instanceof BigDecimal) {
				value = ((BigDecimal) v).toPlainString();
			} else if (v instanceof BigInteger) {
				DecimalFormat df = new DecimalFormat("#");
				value = df.format((BigInteger) v);
			}
		} catch (Exception ex) {
			return;
		}

		// XW-375 - must be a string
		if (!(value instanceof String)) {
			return;
		}

		// string must not be empty
		String str = ((String) value).trim();
		if (str.length() == 0) {
			return;
		}

		// match against expression
		Pattern pattern;
		if (isCaseSensitive()) {
			pattern = Pattern.compile(expression);
		} else {
			pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		}

		String compare = (String) value;
		if (trim) {
			compare = compare.trim();
		}
		Matcher matcher = pattern.matcher(compare);

		if (!matcher.matches()) {
			addFieldError(fieldName, object);
		}
	}

	/**
	 * @return Returns the regular expression to be matched.
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Sets the regular expression to be matched.
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * @return Returns whether the expression should be matched against in a
	 *         case-sensitive way. Default is <code>true</code>.
	 */
	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Sets whether the expression should be matched against in a case-sensitive
	 * way. Default is <code>true</code>.
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return Returns whether the expression should be trimed before matching.
	 *         Default is <code>true</code>.
	 */
	public boolean isTrimed() {
		return trim;
	}

	/**
	 * Sets whether the expression should be trimed before matching. Default is
	 * <code>true</code>.
	 */
	public void setTrim(boolean trim) {
		this.trim = trim;
	}
}
