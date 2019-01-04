/*
 * Project platform
 *
 * Class UpgradeFileAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-13 下午03:43:36
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.archive.model.UpgradeFileQueryModel;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.A1000.archive.service.UpgradeFileService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.SystemConfig;

/**
 * 升级文件管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class UpgradeFileAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 599560215767015429L;

	/** 写文件的BUFF大小 */
	private static Integer BUFFER_SIZE = 10 * 1024;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 升级文件业务接口 */
	private UpgradeFileService upgradeFileService;

	/** 监测类型业务逻辑接口 */
	private SensorTypeService sensorTypeService;

	/** 上传文件域对象 */
	private File updateFile;

	/** 上传的文件名 */
	private String fileName;

	/** 升级文件信息。保存新增升级文件提交的数据，及保存到request域的升级文件信息等 */
	private UpgradeFile upgradeFile;

	/** 升级文件ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private UpgradeFileQueryModel queryModel = new UpgradeFileQueryModel();

	/** 查询：开始时间 */
	private Date queryBeginTime;

	/** 查询：结束时间 */
	private Date queryEndTime;

	/**
	 * 获取 上传文件域对象
	 * 
	 * @return 上传文件域对象
	 */
	public File getUpdateFile() {
		return this.updateFile;
	}

	/**
	 * 设置 上传文件域对象
	 * 
	 * @param updateFile
	 *            上传文件域对象
	 */
	public void setUpdateFile(File updateFile) {
		this.updateFile = updateFile;
	}

	/**
	 * 获取 上传的文件名
	 * 
	 * @return 上传的文件名
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置 上传的文件名
	 * 
	 * @param fileName
	 *            上传的文件名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取 升级文件信息。保存新增升级文件提交的数据，及保存到request域的升级文件信息等
	 * 
	 * @return 升级文件信息。保存新增升级文件提交的数据，及保存到request域的升级文件信息等
	 */
	public UpgradeFile getUpgradeFile() {
		return this.upgradeFile;
	}

	/**
	 * 设置 升级文件信息。保存新增升级文件提交的数据，及保存到request域的升级文件信息等
	 * 
	 * @param upgradeFile
	 *            升级文件信息。保存新增升级文件提交的数据，及保存到request域的升级文件信息等
	 */
	public void setUpgradeFile(UpgradeFile upgradeFile) {
		this.upgradeFile = upgradeFile;
	}

	/**
	 * 获取 升级文件ID，在修改、删除时用到
	 * 
	 * @return 升级文件ID，在修改、删除时用到
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 升级文件ID，在修改、删除时用到
	 * 
	 * @param id
	 *            升级文件ID，在修改、删除时用到
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public UpgradeFileQueryModel getQueryModel() {
		return this.queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(UpgradeFileQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 获取 查询：开始时间
	 * 
	 * @return 查询：开始时间
	 */
	public Date getQueryBeginTime() {
		return this.queryBeginTime;
	}

	/**
	 * 设置 查询：开始时间
	 * 
	 * @param queryBeginTime
	 *            查询：开始时间
	 */
	public void setQueryBeginTime(Date queryBeginTime) {
		this.queryBeginTime = queryBeginTime;
	}

	/**
	 * 获取 查询：结束时间
	 * 
	 * @return 查询：结束时间
	 */
	public Date getQueryEndTime() {
		return this.queryEndTime;
	}

	/**
	 * 设置 查询：结束时间
	 * 
	 * @param queryEndTime
	 *            查询：结束时间
	 */
	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	/**
	 * 设置 升级文件业务接口
	 * 
	 * @param upgradeFileService
	 *            升级文件业务接口
	 */
	public void setUpgradeFileService(UpgradeFileService upgradeFileService) {
		this.upgradeFileService = upgradeFileService;
	}

	/**
	 * 设置 监测类型业务逻辑接口
	 * 
	 * @param sensorTypeService
	 *            监测类型业务逻辑接口
	 */
	public void setSensorTypeService(SensorTypeService sensorTypeService) {
		this.sensorTypeService = sensorTypeService;
	}

	/**
	 * 获取升级文件的文件名
	 * 
	 * @return
	 */
	public String getUpdateFileFileName() {
		return fileName;
	}

	/**
	 * 设置升级文件的文件名
	 * 
	 * @param fileName
	 */
	public void setUpdateFileFileName(String fileName) {
		this.fileName = fileName;
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
	 * 升级文件列表
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
		if (null != this.queryModel.getSensorType() && null == this.queryModel.getSensorType().getSensorTypeId()) {
			this.queryModel.setSensorType(null);
		} else if (null != this.queryModel.getSensorType() && null != this.queryModel.getSensorType().getSensorTypeId()) {
			this.queryModel.setSensorType(this.sensorTypeService.getSensorType(this.queryModel.getSensorType().getSensorTypeId()));
			this.queryModel.setSensorTypeQueryMode(ObjectQueryMode.EQ);
		}
		if (null == this.queryModel.getFileName() || 0 == this.queryModel.getFileName().trim().length()) {
			this.queryModel.setFileName(null);
		}
		if (null == this.queryModel.getFileExtension() || 0 == this.queryModel.getFileExtension().trim().length()) {
			this.queryModel.setFileExtension(null);
		}
		if (null == this.queryModel.getVersion() || 0 == this.queryModel.getVersion().trim().length()) {
			this.queryModel.setVersion(null);
		}

		Calendar d = Calendar.getInstance();
		d.add(Calendar.DAY_OF_MONTH, -30);
		Date beginTime = d.getTime();
		Date endTime = new Date();
		if (null == this.queryBeginTime && null == this.queryEndTime) {
			// 上面已经初始化
		} else if (null == this.queryBeginTime) {
			beginTime = this.queryEndTime;
			endTime = this.queryEndTime;
		} else if (null == this.queryEndTime || this.queryBeginTime.after(this.queryEndTime)) {
			beginTime = this.queryBeginTime;
			endTime = this.queryBeginTime;
		} else {
			beginTime = this.queryBeginTime;
			endTime = this.queryEndTime;
		}
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		begin.set(begin.get(Calendar.YEAR), begin.get(Calendar.MONTH), begin.get(Calendar.DATE), 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE), 23, 59, 59);
		this.queryBeginTime = begin.getTime();
		this.queryEndTime = end.getTime();
		this.queryBeginTime = begin.getTime();
		this.queryEndTime = end.getTime();
		this.queryModel.setReleaseTime(this.queryBeginTime);
		DateQueryMode createTimeQueryMode = DateQueryMode.BETWEEN.init(this.queryBeginTime, this.queryEndTime);
		this.queryModel.setReleaseTimeQueryMode(createTimeQueryMode);
		this.queryModel.setFileNameQueryMode(StringQueryMode.LIKE);
		this.queryModel.setFileExtensionQueryMode(StringQueryMode.LIKE);
		this.queryModel.setVersionQueryMode(StringQueryMode.LIKE);
		Page<UpgradeFile> page = this.upgradeFileService.getPageUpgradeFiles(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		this.initData();
		return SUCCESS;
	}

	/**
	 * 跳转到新增升级文件页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		this.initData();
		return SUCCESS;
	}

	/**
	 * 保存新增升级文件信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		this.initData();
		if (!this.checkFile()) {
			return INPUT;
		}
		if (null == this.upgradeFile.getSensorType() || null == this.upgradeFile.getSensorType().getSensorTypeId()) {
			request.setAttribute("errorMessage", "请先选择监测类型");
			return INPUT;
		}
		SensorType sensorType = this.sensorTypeService.getSensorType(this.upgradeFile.getSensorType().getSensorTypeId());
		if (null == sensorType) {
			request.setAttribute("errorMessage", "监测类型不存在");
			return INPUT;
		}
		File file = this.uploadFile();
		try {
			User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
			int index = fileName.lastIndexOf(".");
			if (index != fileName.length() || index != 0) { // 存在后缀
				this.upgradeFile.setFileExtension(this.getExtention(fileName));
			}
			this.upgradeFile.setFileName(file.getName());
			this.upgradeFile.setPath(SystemConfig.SYSTEM_UPGRADE_ROOT);
			this.upgradeFile.setUploadTime(new Date());
			this.upgradeFile.setUser(user);
			this.upgradeFileService.addUpgradeFile(this.upgradeFile);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS:
				this.request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "相同后缀与版本号的升级文件已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改升级文件页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.initData();
		this.upgradeFile = this.upgradeFileService.getUpgradeFile(this.id);
		if (null == this.upgradeFile) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改升级文件信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		this.initData();
		UpgradeFile upgradeFileObj = this.upgradeFileService.getUpgradeFile(this.upgradeFile.getUpgradeFileId());
		if (null == upgradeFileObj) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件不存在，可能已经被删除");
			return INPUT;
		} else {
			this.upgradeFile.setFileExtension(upgradeFileObj.getFileExtension());
			this.upgradeFile.setFileName(upgradeFileObj.getFileName());
			this.upgradeFile.setUser(upgradeFileObj.getUser());
			this.upgradeFile.setUploadTime(upgradeFileObj.getUploadTime());
			if (null == this.upgradeFile.getSensorType() || null == this.upgradeFile.getSensorType().getSensorTypeId()) {
				request.setAttribute("errorMessage", "请先选择监测类型");
				return INPUT;
			}
			SensorType sensorType = this.sensorTypeService.getSensorType(this.upgradeFile.getSensorType().getSensorTypeId());
			if (null == sensorType) {
				request.setAttribute("errorMessage", "监测类型不存在");
				return INPUT;
			}
			upgradeFileObj.setSensorType(sensorType);
			upgradeFileObj.setVersion(this.upgradeFile.getVersion());
			upgradeFileObj.setReleaseTime(this.upgradeFile.getReleaseTime());
			upgradeFileObj.setUpgradeFileDesc(this.upgradeFile.getUpgradeFileDesc());
			this.upgradeFile = upgradeFileObj;
			try {
				this.upgradeFileService.editUpgradeFile(this.upgradeFile);
				request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS:
					this.request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件编号已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到升级文件详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.upgradeFile = this.upgradeFileService.getUpgradeFile(this.id);
		if (null == this.upgradeFile) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除升级文件
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.upgradeFile = this.upgradeFileService.getUpgradeFile(this.id);
		if (null == this.upgradeFile) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件不存在，可能已经被删除");
			return INPUT;
		}
		try {
			this.upgradeFileService.deleteUpgradeFile(this.upgradeFile);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK:
				request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该文件已与升级任务关联，不允许进行删除");
				break;
			}
		}
		return this.list();
	}

	public void prepare() throws Exception {
	}

	public void prepareEditSave() throws Exception {
		this.initData();
	}

	public void prepareAddSave() throws Exception {
		this.initData();
	}

	/**
	 * 初始化监测类型等信息
	 */
	private void initData() {
		request.setAttribute("sensorTypes", this.sensorTypeService.getAllSensorTypes());
	}

	/**
	 * 保存上传的文件
	 * 
	 * @return
	 */
	private File uploadFile() {
		File file = null;
		if (this.hasExtention()) {
			file = new File(ContextKeys.WEB_PHYSICAL_ROOT_PATH + SystemConfig.SYSTEM_UPGRADE_ROOT + fileName + this.getExtention(fileName));
		} else {
			file = new File(ContextKeys.WEB_PHYSICAL_ROOT_PATH + SystemConfig.SYSTEM_UPGRADE_ROOT + fileName);
		}
		copy(updateFile, file);
		return file;
	}

	/**
	 * 复制文件
	 * 
	 * @param src
	 *            源文件
	 * @param dst
	 *            目的文件
	 */
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				if (!dst.getParentFile().exists()) {
					dst.getParentFile().mkdirs();
				}
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int len = -1;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param fileName
	 *            文件名。包括后缀
	 * @return 文件的扩展名。如“.bin”
	 */
	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return this.hasExtention() ? fileName.substring(pos) : null;
	}

	/**
	 * 检查文件是否为“.hex”或“.bin”类型
	 * 
	 * @return true - 为“.hex”或“.bin”类型；<br />
	 *         false - 其他类型
	 */
	private boolean checkFile() {
		if (this.fileName == null || this.fileName.trim().length() <= 0) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要上传的文件");
			return false;
		}
		// if (!".bin".equalsIgnoreCase(getExtention(fileName)) &&
		// !".hex".equalsIgnoreCase(getExtention(fileName))) {
		// request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "文件类型不正确");
		// return false;
		// }
		return true;
	}

	/**
	 * 判断上传的文件是否有后缀，如果有则返回true，否则返回false
	 * 
	 * @return true - 存在后缀； false - 不存在后缀
	 */
	private boolean hasExtention() {
		int index = fileName.lastIndexOf(".");
		if (-1 != index && index != fileName.length() - 1 && index != 0) { // 存在后缀
			return true;
		}
		return false;
	}
}