/*
 * Project platform
 *
 * Classname Page.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-24 下午05:56:53
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 页数据封装类。该类封装了页数据的首页、前一页、当前页、下一页、尾页、数据集、总记录数、总页数等信息。
 * 
 * @param <T>
 *            实体对象类型
 * @version v1.0.0
 * @author 
 */
public class Page<T> {

	/** 第一页页码 */
	private long firstPageNo = 0;

	/** 前一页页码 */
	private long previousPageNo = 0;

	/** 当前页码 */
	private long currentPageNo = 0;

	/** 下一页页码 */
	private long nextPageNo = 0;

	/** 最后一页页码 */
	private long lastPageNo = 0;

	/** 每页显示记录数 */
	private long pageSize = 20;

	/** 某页第一条记录的记录索引 */
	private long startIndex = 0;

	/** 某页最后一条记录的记录索引 */
	private long endIndex = 0;

	/** 总记录数 */
	private long totalRecordCount = 0;

	/** 总页数 */
	private long totalPageCount = 0;

	/** 当前页要显示的记录集 */
	private Collection<T> records = new ArrayList<T>();

	/** 可供选择的每页显示记录数。默认可选{ 10, 20, 30, 50, 100, 200, 500, 1000 } */
	private long[] pageSizeList = { 10, 20, 30, 50, 100, 200, 500, 1000 };

	/**
	 * 构造一个Page对象
	 * 
	 * @param currentPageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数
	 * @param totalRecordCount
	 *            总记录数
	 * @param records
	 *            当前页要显示的记录集
	 */
	public Page(long currentPageNo, long pageSize, long totalRecordCount, Collection<T> records) {

		/* 设置每页显示记录数 */
		if (pageSize <= 0) {
			this.pageSize = 20;
		} else {
			this.pageSize = pageSize;
		}

		/* 设置总记录数 */
		this.totalRecordCount = totalRecordCount;

		/* 设置总页数 */
		this.totalPageCount = this.totalRecordCount / this.pageSize;
		if (this.totalRecordCount % this.pageSize != 0) {
			this.totalPageCount += 1;
		}

		/* 设置第一页页码 */
		this.firstPageNo = 1;

		/* 设置最后一页页码 */
		this.lastPageNo = this.totalPageCount;

		/* 设置当前页数 */
		if (currentPageNo < 0) {
			this.currentPageNo = 0;
		} else {
			this.currentPageNo = currentPageNo;
		}

		/* 设置前一页页码 */
		if (this.currentPageNo > 1) {
			this.previousPageNo = this.currentPageNo - 1;
		} else {
			this.previousPageNo = this.currentPageNo;
		}

		/* 设置下一页页码 */
		if (this.currentPageNo >= this.totalPageCount) {
			this.nextPageNo = this.currentPageNo;
		} else {
			this.nextPageNo = this.currentPageNo + 1;
		}

		/* 设置当前页的起始记录号 */
		this.startIndex = (this.currentPageNo - 1) * this.pageSize + 1;

		/* 设置当前页的结束记录号 */
		if (this.currentPageNo == this.totalPageCount) {
			this.endIndex = this.startIndex + (this.totalRecordCount % this.pageSize) - 1;
		} else {
			this.endIndex = this.startIndex + this.pageSize - 1;
		}

		/* 设置当前页要显示的记录集 */
		if(null != records)
			this.records = records;
	}

	/**
	 * 构造一个Page对象
	 * 
	 * @param currentPageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数
	 * @param totalRecordCount
	 *            总记录数
	 * @param records
	 *            当前页要显示的记录集
	 * @param pageSizeList
	 *            可供选择的每页显示记录数
	 */
	public Page(long currentPageNo, long pageSize, long totalRecordCount, Collection<T> records, long[] pageSizeList) {
		this(currentPageNo, pageSize, totalRecordCount, records);
		this.pageSizeList = pageSizeList;
	}

	/**
	 * 获取 第一页页码
	 * 
	 * @return 第一页页码
	 */
	public long getFirstPageNo() {
		return this.firstPageNo;
	}

	/**
	 * 设置 第一页页码
	 * 
	 * @param firstPageNo
	 *            第一页页码
	 */
	public void setFirstPageNo(long firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	/**
	 * 获取 前一页页码
	 * 
	 * @return 前一页页码
	 */
	public long getPreviousPageNo() {
		return this.previousPageNo;
	}

	/**
	 * 设置 前一页页码
	 * 
	 * @param previousPageNo
	 *            前一页页码
	 */
	public void setPreviousPageNo(long previousPageNo) {
		this.previousPageNo = previousPageNo;
	}

	/**
	 * 获取 当前页码
	 * 
	 * @return 当前页码
	 */
	public long getCurrentPageNo() {
		return this.currentPageNo;
	}

	/**
	 * 设置 当前页码
	 * 
	 * @param currentPageNo
	 *            当前页码
	 */
	public void setCurrentPageNo(long currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * 获取 下一页页码
	 * 
	 * @return 下一页页码
	 */
	public long getNextPageNo() {
		return this.nextPageNo;
	}

	/**
	 * 设置 下一页页码
	 * 
	 * @param nextPageNo
	 *            下一页页码
	 */
	public void setNextPageNo(long nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	/**
	 * 获取 最后一页页码
	 * 
	 * @return 最后一页页码
	 */
	public long getLastPageNo() {
		return this.lastPageNo;
	}

	/**
	 * 设置 最后一页页码
	 * 
	 * @param lastPageNo
	 *            最后一页页码
	 */
	public void setLastPageNo(long lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	/**
	 * 获取 每页显示记录数
	 * 
	 * @return 每页显示记录数
	 */
	public long getPageSize() {
		return this.pageSize;
	}

	/**
	 * 设置 每页显示记录数
	 * 
	 * @param pageSize
	 *            每页显示记录数
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取 某页第一条记录的记录索引
	 * 
	 * @return 某页第一条记录的记录索引
	 */
	public long getStartIndex() {
		return this.startIndex;
	}

	/**
	 * 设置 某页第一条记录的记录索引
	 * 
	 * @param startIndex
	 *            某页第一条记录的记录索引
	 */
	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * 获取 某页最后一条记录的记录索引
	 * 
	 * @return 某页最后一条记录的记录索引
	 */
	public long getEndIndex() {
		return this.endIndex;
	}

	/**
	 * 设置 某页最后一条记录的记录索引
	 * 
	 * @param endIndex
	 *            某页最后一条记录的记录索引
	 */
	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * 获取 总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotalRecordCount() {
		return this.totalRecordCount;
	}

	/**
	 * 设置 总记录数
	 * 
	 * @param totalRecordCount
	 *            总记录数
	 */
	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	/**
	 * 获取 总页数
	 * 
	 * @return 总页数
	 */
	public long getTotalPageCount() {
		return this.totalPageCount;
	}

	/**
	 * 设置 总页数
	 * 
	 * @param totalPageCount
	 *            总页数
	 */
	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	/**
	 * 获取 当前页要显示的记录集
	 * 
	 * @return 当前页要显示的记录集
	 */
	public Collection<T> getRecords() {
		return this.records;
	}

	/**
	 * 设置 当前页要显示的记录集
	 * 
	 * @param records
	 *            当前页要显示的记录集
	 */
	public void setRecords(Collection<T> records) {
		this.records = records;
	}

	/**
	 * 获取 可供选择的每页显示记录数。默认可选{ 10, 20, 30, 50, 100, 200, 500, 1000 }
	 * 
	 * @return 可供选择的每页显示记录数。默认可选{ 10, 20, 30, 50, 100, 200, 500, 1000 }
	 */
	public long[] getPageSizeList() {
		return this.pageSizeList;
	}

	/**
	 * 设置 可供选择的每页显示记录数。默认可选{ 10, 20, 30, 50, 100, 200, 500, 1000 }
	 * 
	 * @param pageSizeList
	 *            可供选择的每页显示记录数。默认可选{ 10, 20, 30, 50, 100, 200, 500, 1000 }
	 */
	public void setPageSizeList(long[] pageSizeList) {
		this.pageSizeList = pageSizeList;
	}
}