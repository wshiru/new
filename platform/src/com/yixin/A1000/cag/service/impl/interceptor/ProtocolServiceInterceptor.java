/*
 * Project platform
 *
 * Class ProtocolServiceInterceptor.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-17 上午11:35:26
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service.impl.interceptor;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import com.yixin.A1000.cag.model.ErrorCode;
import com.yixin.A1000.cag.model.ErrorExtendParam;
import com.yixin.A1000.cag.model.ValidateResult;

/**
 * 捕捉ProtocolService中未处理的异常，如果捕捉到，则将返回错误代码设置为：99xxxxx
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolServiceInterceptor {

	/** 日志记录器 */
	private static final Logger logger = Logger.getLogger(ProtocolServiceInterceptor.class);

	/**
	 * 捕捉所有异常。如果发生未捕捉的异常，则将返回错误代码设置为：99xxxxx
	 * 
	 * @param pjp
	 * @return
	 */
	public String doException(ProceedingJoinPoint pjp) {
		String result = "";
		try {
			result = (String) pjp.proceed();
		} catch (Throwable ex) {
			result = this.getFailedValidateReplyXML(ValidateResult.getFailedValidateResult(ErrorCode.ERROR_99xxxxx,
					ErrorExtendParam.CMA_ID, ""));
			logger.error("捕捉到未处理异常。将返回错误代码：99xxxxx。。详细信息如下：", ex);
		}
		return result;
	}

	/**
	 * 返回校验失败回复的XML。当校验成功时返回null。
	 * 
	 * @param validateResult
	 *            保存校验结果的
	 * @return 校验失败回复的XML。当校验成功时返回null。
	 */
	private String getFailedValidateReplyXML(ValidateResult validateResult) {
		if (0 == validateResult.getCode()) {
			return null;
		}
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(validateResult.getCode()));
		Element errorNode = resultNode.addElement("error");
		errorNode.addAttribute("errorcode", validateResult.getErrorCode().getCode());
		Element attrNode = errorNode.addElement("attr");
		if (null != validateResult.getErrorExtendParam()) {
			attrNode.addAttribute("name", validateResult.getErrorExtendParam().getCode());
		} else {
			attrNode.addAttribute("name", "");
		}
		if (null != validateResult.getErrorExtendParam()) {
			attrNode.addAttribute("value", validateResult.getValue());
		} else {
			attrNode.addAttribute("value", "");
		}
		return doc.asXML();
	}
}
