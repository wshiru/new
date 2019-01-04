/*
 * Project platform
 *
 * Class LineServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-22 下午12:06:55
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.archive.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.LineDao;
import com.yixin.A1000.archive.dao.TowerDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.LineQueryModel;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.common.util.DictCategoryKeys;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.dao.DictCategoryDao;
import com.yixin.framework.system.dao.DictionaryDao;
import com.yixin.framework.system.model.DictCategory;
import com.yixin.framework.system.model.Dictionary;

/**
 * 线路业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class LineServiceImpl implements LineService {

	/** 线路DAO接口对象 */
	private LineDao lineDao;

	/** 杆塔DAO接口对象 */
	private TowerDao towerDao;

	/** 数据字典DAO接口对象 */
	private DictionaryDao dictionaryDao;

	/** 数据字典类型DAO接口对象 */
	private DictCategoryDao dictCategoryDao;

	/**
	 * 设置 线路DAO接口对象
	 * 
	 * @param lineDao
	 *            线路DAO接口对象
	 */
	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	/**
	 * 设置 杆塔DAO接口对象
	 * 
	 * @param towerDao
	 *            杆塔DAO接口对象
	 */
	public void setTowerDao(TowerDao towerDao) {
		this.towerDao = towerDao;
	}

	/**
	 * 设置 数据字典DAO接口对象
	 * 
	 * @param dictionaryDao
	 *            数据字典DAO接口对象
	 */
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	/**
	 * 设置 数据字典类型DAO接口对象
	 * 
	 * @param dictCategoryDao
	 *            数据字典类型DAO接口对象
	 */
	public void setDictCategoryDao(DictCategoryDao dictCategoryDao) {
		this.dictCategoryDao = dictCategoryDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#addLine(com.yixin.ca2000
	 * .archive.model.Line)
	 */
	@Override
	public void addLine(Line line) {
		List<Line> list = this.lineDao.getAllByProperty("lineCode", line.getLineCode());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS);
		}
		list = this.lineDao.getAllByProperty("lineName", line.getLineName());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS);
		}
		this.lineDao.save(line);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#editLine(com.yixin.ca2000
	 * .archive.model.Line)
	 */
	@Override
	public void editLine(Line line) {
		List<Line> list = this.lineDao.getAllByProperty("lineCode", line.getLineCode());
		Iterator<Line> iterator = list.iterator();
		while (iterator.hasNext()) {
			Line l = iterator.next();
			if (!l.getLineId().equals(line.getLineId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS);
			}
		}
		list = this.lineDao.getAllByProperty("lineName", line.getLineName());
		iterator = list.iterator();
		while (iterator.hasNext()) {
			Line l = iterator.next();
			if (!l.getLineId().equals(line.getLineId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS);
			}
		}
		this.lineDao.update(line);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#deleteLine(com.yixin.ca2000
	 * .archive.model.Line)
	 */
	@Override
	public void deleteLine(Line line) {
		if (this.getAllTowers(line).size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS);
		}
		this.lineDao.delete(line);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#deleteLines(java.util.Collection
	 * )
	 */
	@Override
	public void deleteLines(Collection<Line> lines) {
		try {
			this.lineDao.deleteAll(lines);
		} catch (Exception ex) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#getLine(java.lang.String)
	 */
	@Override
	public Line getLine(String id) {
		return this.lineDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.LineService#getAllLines()
	 */
	@Override
	public List<Line> getAllLines() {
		return this.lineDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.LineService#getPageLines(long,
	 * long)
	 */
	@Override
	public Page<Line> getPageLines(long pageNo, long pageSize) {
		return this.lineDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#getAllLines(com.yixin.ca2000
	 * .archive.model.LineQueryModel)
	 */
	@Override
	public List<Line> getAllLines(LineQueryModel lineQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(lineQueryModel);
		return this.lineDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#getPageLines(com.yixin.ca2000
	 * .archive.model.LineQueryModel, long, long)
	 */
	@Override
	public Page<Line> getPageLines(LineQueryModel lineQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(lineQueryModel);
		return this.lineDao.getPage(queryProperties, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#getAllTowers(com.yixin.ca2000
	 * .archive.model.Line)
	 */
	@Override
	public List<Tower> getAllTowers(Line line) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("line", line);
		return this.towerDao.getAllByOrProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.LineService#getPageTowers(com.yixin.
	 * ca2000.archive.model.Line, long, long)
	 */
	@Override
	public Page<Tower> getPageTowers(Line line, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("line", line);
		return this.towerDao.getPageByProperties(map, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.LineService#getAllVoltageLevels()
	 */
	@Override
	public List<Dictionary> getAllVoltageLevels() {
		DictCategory dictCategory = this.dictCategoryDao.findById(DictCategoryKeys.LINE_VOLTAGELEVEL);
		if (null == dictCategory) {
			return new ArrayList<Dictionary>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictCategory", dictCategory);
		return this.dictionaryDao.getAllByProperties(map);
	}

	/**
	 * 根据lineQueryModel创建查询属性集
	 * 
	 * @param lineQueryModel
	 *            线路查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(LineQueryModel lineQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != lineQueryModel.getLength()) {
			QueryProperty qp = new QueryProperty("length", lineQueryModel.getLength(),
					lineQueryModel.getLengthQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getLineCode()) {
			QueryProperty qp = new QueryProperty("lineCode", lineQueryModel.getLineCode(),
					lineQueryModel.getLineCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getLineDesc()) {
			QueryProperty qp = new QueryProperty("lineDesc", lineQueryModel.getLineDesc(),
					lineQueryModel.getLineDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getLineEnd()) {
			QueryProperty qp = new QueryProperty("lineEnd", lineQueryModel.getLineEnd(),
					lineQueryModel.getLineEndQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getLineName()) {
			QueryProperty qp = new QueryProperty("lineName", lineQueryModel.getLineName(),
					lineQueryModel.getLineNameQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getLineStart()) {
			QueryProperty qp = new QueryProperty("lineStart", lineQueryModel.getLineStart(),
					lineQueryModel.getLineStartQueryMode());
			queryProperties.add(qp);
		}
		if (null != lineQueryModel.getVoltageLevel()) {
			QueryProperty qp = new QueryProperty("voltageLevel", lineQueryModel.getVoltageLevel(),
					lineQueryModel.getVoltageLevelQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}
}
