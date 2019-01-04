/*
 * Project platform
 *
 * Class LoginService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 上午11:27:38
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.service;

import java.util.List;

import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.model.TreeInfo;

/**
 * 用户登录业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LoginService {

	/**
	 * 用户登录
	 * 
	 * @param operatorCode
	 *            用户名
	 * @param password
	 *            密码
	 * @return 登录成功后用户信息
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                LoginErrorCode.LOGIN_USERCODE_NOTEXISTS- “用户编号”不存在<br />
	 *                LoginErrorCode.LOGIN_PASSWORD_INCORRECT- “密码”错误<br />
	 *                LoginErrorCode.LOGIN_STATE_DISABLE-账户被停用<br />
	 *                LoginErrorCode.LOGIN_ACCOUNTHASEXPIRED-账户已过期<br />
	 */
	public abstract LoginInfo login(String operatorCode, String password);
	/**
	 * 取得树列表信息
	 * @param treeType	0为按线路，1为按监测类型
	 * @return
	 */
	public abstract List<TreeInfo> getTreeInfos(Integer treeType);
}
