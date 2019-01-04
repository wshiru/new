/*
 * Project platform
 *
 * Class LineAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 上午11:17:05
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.LineQueryModel;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.model.Dictionary;
import com.yixin.framework.system.service.DictionaryService;

/**
 * 线路管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class LineAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -6766475137619348077L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 线路业务接口 */
	private LineService lineService;

	/** 数据字典业务接口 */
	private DictionaryService dictionaryService;

	/** 线路信息。保存新增线路提交的数据，及保存到request域的线路信息等 */
	private Line line;

	/** 线路ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private LineQueryModel queryModel = new LineQueryModel();

	/**
	 * 获取 线路信息。保存新增线路提交的数据，及保存到request域的线路信息等
	 * 
	 * @return 线路信息
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * 设置 线路信息。保存新增线路提交的数据，及保存到request域的线路信息等
	 * 
	 * @param line
	 *            线路信息
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * 获取 线路ID，在修改、删除时用到
	 * 
	 * @return 线路ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 线路ID，在修改、删除时用到
	 * 
	 * @param id
	 *            线路ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public LineQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(LineQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 设置 线路业务接口
	 * 
	 * @param lineService
	 *            线路业务接口
	 */
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	/**
	 * 设置 数据字典业务接口
	 * 
	 * @param dictionaryService
	 *            数据字典业务接口
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 线路列表
	 * 
	 * @return 结果页面
	 */
	public String list() {
		String pageNoStr = request.getParameter("pn");
		String pageSizeStr = request.getParameter("ps");
		int pageNo = 1;
		int pageSize = 20;
		if (pageNoStr != null && !pageNoStr.equals("")) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		if (null == this.queryModel.getLineCode() || 0 == this.queryModel.getLineCode().trim().length()) {
			this.queryModel.setLineCode(null);
		}
		if (null == this.queryModel.getLineName() || 0 == this.queryModel.getLineName().trim().length()) {
			this.queryModel.setLineName(null);
		}
		if (null != this.queryModel.getVoltageLevel() && null == this.queryModel.getVoltageLevel().getDictionaryId()) {
			this.queryModel.setVoltageLevel(null);
		} else if (null != this.queryModel.getVoltageLevel()
				&& null != this.queryModel.getVoltageLevel().getDictionaryId()) {
			this.queryModel.setVoltageLevel(this.dictionaryService.getDictionary(this.queryModel.getVoltageLevel()
					.getDictionaryId()));
			this.queryModel.setVoltageLevelQueryMode(ObjectQueryMode.EQ);
		}
		this.queryModel.setLineCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setLineNameQueryMode(StringQueryMode.LIKE);
		Page<Line> page = this.lineService.getPageLines(this.queryModel, pageNo, pageSize);
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到新增线路页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
		return SUCCESS;
	}

	/**
	 * 保存新增线路信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
		if (null == this.line.getVoltageLevel() || null == this.line.getVoltageLevel().getDictionaryId()) {
			this.line.setVoltageLevel(null);
		} else {
			Dictionary vol = this.dictionaryService.getDictionary(this.line.getVoltageLevel().getDictionaryId());
			if (vol == null) {
				request.setAttribute("errorMessage", "线路电压不存在");
				this.line.setVoltageLevel(null);
				return INPUT;
			} else {
				this.line.setVoltageLevel(vol);
			}
		}
		try {
			this.lineService.addLine(line);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS:
				this.request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路编号已经存在");
				break;
			case ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS:
				this.request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路名称已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改线路页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
		this.line = this.lineService.getLine(this.id);
		if (null == this.line) {
			request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改线路信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
		Line lineObj = this.lineService.getLine(this.line.getLineId());
		if (null == lineObj) {
			request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路不存在，可能已经被删除");
			return INPUT;
		} else {
			lineObj.setLength(this.line.getLength());
			lineObj.setLineCode(this.line.getLineCode());
			lineObj.setLineDesc(this.line.getLineDesc());
			lineObj.setLineEnd(this.line.getLineEnd());
			lineObj.setLineName(this.line.getLineName());
			lineObj.setLineStart(this.line.getLineStart());
			if (null == this.line.getVoltageLevel() || null == this.line.getVoltageLevel().getDictionaryId()) {
				this.line.setVoltageLevel(null);
			} else {
				Dictionary vol = this.dictionaryService.getDictionary(this.line.getVoltageLevel().getDictionaryId());
				if (vol == null) {
					request.setAttribute("errorMessage", "电压等级不存在");
					this.line.setVoltageLevel(null);
					return INPUT;
				} else {
					this.line.setVoltageLevel(vol);
					lineObj.setVoltageLevel(vol);
				}
			}
			this.line = lineObj;
			try {
				this.lineService.editLine(this.line);
				request.setAttribute(LineAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS:
					this.request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路编号已经存在");
					break;
				case ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS:
					this.request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路名称已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到线路详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.line = this.lineService.getLine(this.id);
		if (null == this.line) {
			request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除线路
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.line = this.lineService.getLine(this.id);
		if (null == this.line) {
			request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "线路不存在，可能已经被删除");
			return INPUT;
		}
		try {
			this.lineService.deleteLine(this.line);
			request.setAttribute(LineAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS:
				request.setAttribute(LineAction.ERROR_MESSAGE_ATTR_NAME, "存在杆塔依赖于该线路，请先删除该线路下的杆塔信息");
				break;
			}
		}
		return this.list();
	}

	public void prepare() throws Exception {
	}

	public void prepareEditSave() throws Exception {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
	}

	public void prepareAddSave() throws Exception {
		request.setAttribute("voltageLevels", this.lineService.getAllVoltageLevels());
	}
}
