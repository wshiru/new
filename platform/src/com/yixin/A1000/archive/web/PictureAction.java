/*
 * Project platform
 *
 * Class SensorTypeAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-24 上午08:34:26
 *
 * ModifiedBy 
 * ModifyAt 2011-07-12 14：20
 * ModifyDescription 添加功能配置
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.SystemConfig;

/**
 * 监测装置图片管理管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author
 */
public class PictureAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -639318231231245599L;

	/** 写文件的BUFF大小 */
	private static Integer BUFFER_SIZE = 10 * 1024;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 监测装置信息，保存到request域提交数据用 */
	private Sensor sensor;

	/** 图片信息，保存到request域提交数据用 */
	private Picture picture;

	/** 监测装置图片管理ID，在修改、删除时用到 */
	private String id;

	/** 上传的文件 */
	private File upload;

	/** 上传的文件名 */
	private String fileName;

	/** 文件类型 **/
	private String uploadContentType;

	/** 监测装置服务 */
	private SensorService sensorService;

	/** 监测装置图片管理服务 */
	private PictureService pictureService;

	/**
	 * 获取 监测装置图片ID，在修改、删除时用到
	 * 
	 * @return 监测装置图片管理ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 监测装置图片ID，在修改、删除时用到
	 * 
	 * @param id
	 *            监测装置图片管理ID
	 */
	public void setId(String id) {
		this.id = id;
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
	 * 监测装置图片管理列表
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

		/*
		 * if(sensor == null){
		 * request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
		 * "提交数据错误"); return INPUT; } if(sensor.getSensorId() == null){
		 * request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
		 * "提交数据错误"); return INPUT; }
		 */

		String id = request.getParameter("id");
		if (id != null && "".equals(id)) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"提交数据错误");
			return INPUT;
		}
		sensor = this.sensorService.getSensor(id);

		if (sensor == null) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"监测装置可能已被删除。");
			return INPUT;
		}

		Page<Picture> page = this.pictureService.getPagePictures(sensor,
				pageNo, pageSize);
		sensor = sensorService.getSensor(sensor.getSensorId());
		request.setAttribute("page", page);

		return SUCCESS;
	}

	/**
	 * 跳转到新增监测装置图片管理页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		if (sensor == null) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"提交数据错误");
			return INPUT;
		}
		if (sensor.getSensorId() == null) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"提交数据错误");
			return INPUT;
		}
		sensor = sensorService.getSensor(sensor.getSensorId());
		return SUCCESS;
	}

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				if (!dst.getParentFile().exists()) {
					dst.getParentFile().mkdirs();
				}
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
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
	 * 保存新增监测装置图片管理信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		try {
			if (sensor == null) {
				request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
						"提交数据错误");
				return INPUT;
			}
			if (sensor.getSensorId() == null) {
				request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
						"提交数据错误");
				return INPUT;
			}
			sensor = sensorService.getSensor(sensor.getSensorId());
			if (picture.getCaption() == null || "".equals(picture.getCaption())) {
				request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
						"请输入标题！");
				return INPUT;
			}
			if (this.upload == null) {
				request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
						"请选择要上传的图片！");
				return INPUT;
			}

			sensor = sensorService.getSensor(sensor.getSensorId());
			picture.setSensor(sensor);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat fd = new SimpleDateFormat("yyyyMMddhhmmss");
			String s1 = "\\resource\\images\\upload\\"
					+ fd.format(calendar.getTime());
			if ("image/png".equals(uploadContentType)) {
				s1 += ".png";
			} else if ("image/jpeg".equals(uploadContentType)) {
				s1 += ".jpeg";
			} else if ("image/bmp".equals(uploadContentType)) {
				s1 += ".bmp";
			}
			String f = s1.replace("\\", "/");
			picture.setFileName(f);

			// 保存文件
			// File file = null;
			// if (this.hasExtention()) {
			// file = new File(ContextKeys.WEB_PHYSICAL_ROOT_PATH +
			// SystemConfig.SYSTEM_UPGRADE_ROOT + fileName +
			// this.getExtention(fileName));
			// } else {
			s1 = ContextKeys.WEB_PHYSICAL_ROOT_PATH + s1;
			File file = new File(s1);
			copy(upload, file);

			picture.setCreateTime(new Date());
			this.pictureService.addPicture(picture);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			// case
			// ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS:
			// this.request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
			// "监测装置图片管理编号已经存在");
			// break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改监测装置图片管理页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {

		this.picture = this.pictureService.getPicture(this.picture.getPictureId());
		if (null == this.picture) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"该图片信息已不存在，可能已经被删除");
			return INPUT;
		}
		sensor = picture.getSensor();
		return SUCCESS;
	}

	/**
	 * 保存修改监测装置图片管理信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		Picture pictureObj = this.pictureService.getPicture(picture
				.getPictureId());
		if (null == pictureObj) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"该图片信息已不存在，可能已经被删除");
			return INPUT;
		} else {
			pictureObj.setCaption(this.picture.getCaption());
			pictureObj.setPictureDesc(this.picture.getPictureDesc());
			pictureObj.setDefaultShow(this.picture.getDefaultShow());

			//有选择图片上传，覆盖原来的图片
			if (this.upload != null) {
				String s1 = pictureObj.getFileName().replace("/", "\\");
				
				s1 = ContextKeys.WEB_PHYSICAL_ROOT_PATH + s1;
				File file = new File(s1);
				if(!file.delete()){
					request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"删除失败");
				}
				file = new File(s1);
 				copy(upload, file);				
			}
			this.picture = pictureObj;
			try {
				this.pictureService.editPicture(this.picture);
				
				request.setAttribute(PictureAction.SUCCESS_MESSAGE_ATTR_NAME,
						"操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				// case
				// ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS:
				// this.request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
				// "监测装置图片管理编号已经存在");
				// break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到监测装置图片管理详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {

		this.picture = this.pictureService.getPicture(picture.getPictureId());
		if (null == this.picture) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"该图片信息已不存在，可能已经被删除");
			return INPUT;
		}
		sensor = picture.getSensor();
		return SUCCESS;
	}

	/**
	 * 删除监测装置图片管理
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.picture = this.pictureService.getPicture(picture.getPictureId());
		if (null == this.picture) {
			request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
					"该图片信息已不存在，可能已经被删除");
			return INPUT;
		}
		sensor = picture.getSensor();
		try {

			this.pictureService.deletePicture(this.picture);
			request.setAttribute(PictureAction.SUCCESS_MESSAGE_ATTR_NAME,
					"操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			// case ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS:
			// request.setAttribute(PictureAction.ERROR_MESSAGE_ATTR_NAME,
			// "存在监测装置依赖于该监测装置图片管理，请先删除该监测装置图片管理下的监测装置");
			// break;
			}
		}
		return this.list();
	}

	/**
	 * @param sensor
	 *            the sensor to set
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * @param sensorService
	 *            the sensorService to set
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * @return the sensorService
	 */
	public SensorService getSensorService() {
		return sensorService;
	}

	/**
	 * @param pictureService
	 *            the pictureService to set
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	/**
	 * @return the pictureService
	 */
	public PictureService getPictureService() {
		return pictureService;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
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

	/**
	 * @param uploadContentType
	 *            the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}
}