/*
 * Project platform
 *
 * Classname BaseDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-24 下午05:57:37
 *
 * ModifiedBy 
 * ModifyAt 2011-6-13 09:56
 * ModifyDescription 添加根据单一属性精确和模糊查找，添加多属性或查询
 * 
 * ModifiedBy 
 * ModifyAt 2011-6-20 上午 09:42
 * ModifyDescription 
 *     1、新增public List<T> getAll(final Collection<QueryProperty> queryProperties方法实现；
 *     2、新增public  Page<T> getPage(final Collection<QueryProperty> queryProperties, final long pageNo,	final long pageSize);方法实现；
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;

/**
 * 基DAO接口
 * 
 * @version v1.0.0
 * @author 
 * @param <T>
 *            实体对象类型
 * @param <ID>
 *            实体的ID
 */
public interface BaseDao<T, ID extends Serializable> {

	/**
	 * 保存单个对象
	 * 
	 * @param entity
	 *            要进行保存的对象
	 */
	public abstract void save(final T entity);

	/**
	 * 保存多个对象
	 * 
	 * @param entities
	 *            要进行保存的对象集
	 */
	public abstract void saveAll(final Collection<T> entities);

	/**
	 * 更新单个对象
	 * 
	 * @param entity
	 *            要进行更新的对象
	 */
	public abstract T update(final T entity);

	/**
	 * 更新多个对象
	 * 
	 * @param entities
	 *            要进行更新的对象集
	 */
	public abstract void updateAll(final Collection<T> entities);

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 *            要进行删除的对象
	 */
	public abstract void delete(final T entity);

	/**
	 * 删除多个对象
	 * 
	 * @param entities
	 *            要删除的对象集
	 */
	public abstract void deleteAll(final Collection<T> entities);

	/**
	 * 根据id取得对象
	 * 
	 * @param id
	 *            要进行查找的对象的id
	 * @return 返回查找到的对象。当找不到对象时返回null
	 */
	public abstract T findById(final ID id);

	/**
	 * 取得所有对象
	 * 
	 * @return 返回所有的对象
	 */
	public abstract List<T> getAll();

	/**
	 * 根据某一属性精确查找
	 * 
	 * @return
	 */
	public abstract List<T> getAllByProperty(String propName, Object value);

	/**
	 * 根据某一属性模糊查找
	 * 
	 * @return
	 */
	public abstract List<T> getAllLikeProperty(String propName, Object value);

	/**
	 * 多属性或查询
	 * 
	 * @param map
	 * @return
	 */
	public abstract List<T> getAllByOrProperties(final Map<String, Object> map);

	/**
	 * 根据属性键值对查找对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @return 返回查找到对象
	 */
	public abstract List<T> getAllByProperties(final Map<String, Object> map);

	/**
	 * 根据属性键值对查找对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"Like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @return 返回查找到对象
	 */
	public abstract List<T> getAllLikeProperties(final Map<String, Object> map);

	/**
	 * 通过SQL语句查询数据
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 返回查找到对象
	 */
	public abstract List<T> getAllBySQL(final String sql);
	
	public abstract List<T> getAllBySQL(final String sql, final String[] names)  throws InstantiationException, IllegalAccessException ;
	
	public abstract List<Map<String,Object>> getAllBySQLToMap(final String sql);	
	
	public abstract List<Map<String,Object>> getAllBySQLToMap(final String sql, final String[] names);
	
	

	/**
	 * 根据queryProperties指定的条件查找对象。
	 * 
	 * @param queryProperties
	 *            查找条件
	 * @return 返回查找到对象
	 */
	
	public abstract List<T> getAll(final Collection<QueryProperty> queryProperties);

	/**
	 * 查询第pageNo页的对象
	 * 
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPage(final long pageNo, final long pageSize);

	/**
	 * 根据属性键值对查找第pageNo页的对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPageByProperties(final Map<String, Object> map, final long pageNo, final long pageSize);

	/**
	 * 根据属性键值对查找第pageNo页的对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"Like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPageLikeProperties(final Map<String, Object> map, final long pageNo, final long pageSize);

	/**
	 * 通过SQL语句查询某一页数据
	 * 
	 * @param sql
	 *            SQL语句
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPageBySQL(final String sql, final long pageNo, final long pageSize);
	
	public abstract Page<T> getPageBySQL(final String sql, final String[] names,  final long pageNo, final long pageSize)  throws InstantiationException, IllegalAccessException ;
	
	public abstract Page<Map<String,Object>> getPageBySQLToMap(final String sql, final long pageNo, final long pageSize);
	
	public abstract Page<Map<String,Object>> getPageBySQLToMap(final String sql, final String[] names, final long pageNo, final long pageSize);
	
	/**
	 * 根据queryProperties指定的条件查找第pageNo对象。
	 * 
	 * @param queryProperties
	 *            查找条件
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPage(final Collection<QueryProperty> queryProperties, final long pageNo,
			final long pageSize);

	/**
	 * 返回记录总数
	 * 
	 * @return 记录数
	 */
	public abstract int getCount();

	/**
	 * 返回记录数。过滤条件为属性键值对内容。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @return 记录数
	 */
	public abstract int getCountByProperties(final Map<String, Object> map);

	/**
	 * 返回记录数。过滤条件为属性键值对内容。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @return 记录数
	 */
	public abstract int getCountLikeProperties(final Map<String, Object> map);

	/**
	 * 取得所有对象
	 * 
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 返回所有的对象
	 */
	public abstract List<T> getAll(final DateProperty dateProperty);

	/**
	 * 根据属性键值对查找对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 返回查找到对象
	 */
	public abstract List<T> getAllByProperties(final Map<String, Object> map, final DateProperty dateProperty);
	
	public abstract List<T> getAllByProperties(final Map<String, Object> map, final DateProperty dateProperty, final int maxResult);

	/**
	 * 根据属性键值对查找对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"Like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 返回查找到对象
	 */
	public abstract List<T> getAllLikeProperties(final Map<String, Object> map, final DateProperty dateProperty);

	/**
	 * 查询第pageNo页的对象
	 * 
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPage(final DateProperty dateProperty, final long pageNo, final long pageSize);

	/**
	 * 根据属性键值对查找第pageNo页的对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPageByProperties(final Map<String, Object> map, final DateProperty dateProperty,
			final long pageNo, final long pageSize);

	/**
	 * 根据属性键值对查找第pageNo页的对象。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"Like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @param pageNo
	 *            要获取对象所在的页数
	 * @param pageSize
	 *            要查找的记录数
	 * @return 返回查找到对象
	 */
	public abstract Page<T> getPageLikeProperties(final Map<String, Object> map, final DateProperty dateProperty,
			final long pageNo, final long pageSize);

	/**
	 * 返回记录总数
	 * 
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 记录数
	 */
	public abstract int getCount(final DateProperty dateProperty);

	/**
	 * 返回记录数。过滤条件为属性键值对内容。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"="进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 记录数
	 */
	public abstract int getCountByProperties(final Map<String, Object> map, final DateProperty dateProperty);

	/**
	 * 返回记录数。过滤条件为属性键值对内容。属性键值对类型为Map&lt;String,
	 * Object&gt;即“属性名称-属性值”。各属性间通过“and”连接，属性与值间使用"like"进行比较
	 * 
	 * @param map
	 *            属性名-属性值键值对
	 * @param dateProperty
	 *            要进行过滤的日期属性条件。将通过“dateProperty.propertyName between
	 *            dateProperty.beginTime and dateProperty.endTime”进行过滤
	 * @return 记录数
	 */
	public abstract int getCountLikeProperties(final Map<String, Object> map, final DateProperty dateProperty);
	
	
	/**
	 * 执行SQL 语句
	 * @param sql
	 * @return
	 */
	public abstract int  ExecuteSQL(final String sql);

	
	
}