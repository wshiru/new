/*
 * Project platform
 *
 * Class LoginErrorCode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 上午11:31:46
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.model;

/**
 * 用户登录模块业务错误代码
 * 
 * @version v1.0.0
 * @author 
 */
public class LoginErrorCode {

	/* ------------------------------------------------------- */
	/* 用户登录：120000000-129999999 */
	/* ------------------------------------------------------- */
	/** “用户编号”不存在 */
	public static final int LOGIN_USERCODE_NOTEXISTS = 120000000;

	/** “密码”错误 */
	public static final int LOGIN_PASSWORD_INCORRECT = 120000001;

	/** 账户被停用 */
	public static final int LOGIN_STATE_DISABLE = 120000002;
	
	/** 账户已过期 */
	public static final int LOGIN_ACCOUNTHASEXPIRED = 120000003;
	
}
