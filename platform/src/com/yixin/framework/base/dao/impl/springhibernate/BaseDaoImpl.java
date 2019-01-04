/*
 * Project platform
 *
 * Classname BaseDaoImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-24 下午05:58:07
 *
 * ModifiedBy 
 * ModifyAt 2011-6-10 下午
 * ModifyDescription 修改createHqlLikeProperties方法, '='改'like'和添加'%'
 * 
 * ModifiedBy 
 * ModifyAt 2011-6-13 上午 10:25
 * ModifyDescription 添加单一属性精确和模糊查找、多属性或查找方法实现
 * 
 * ModifiedBy 
 * ModifyAt 2011-6-20 晚上 20:42
 * ModifyDescription 修改checkDateProperty, createHql方法，使他支持单端查询
 * 
 * ModifiedBy 
 * ModifyAt 2011-6-20 上午 09:42
 * ModifyDescription 
 *     1、新增public List<T> getAll(final Collection<QueryProperty> queryProperties方法实现；
 *     2、新增public  Page<T> getPage(final Collection<QueryProperty> queryProperties, final long pageNo,	final long pageSize);方法实现；
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.dao.impl.springhibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 基DAO的spring+hibernate实现类
 * 
 * @version v1.0.0
 * @author 
 * @param <T>
 *            实体对象类型
 * @param <ID>
 *            实体的ID
 */
public class BaseDaoImpl<T, ID extends Serializable> extends HibernateDaoSupport implements BaseDao<T, ID> {

	/** 实体类 */
	private Class<T> entityClass;

	/**
	 * 构造方法
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#save(java.lang.Object)
	 */
	public void save(final T entity) {
		this.getHibernateTemplate().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#saveAll(java.util.Collection)
	 */
	public void saveAll(final Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#update(java.lang.Object)
	 */
	public T update(final T entity) {
	 	return this.getHibernateTemplate().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#updateAll(java.util.Collection)
	 */
	public void updateAll(final Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#delete(java.lang.Object)
	 */
	public void delete(final T entity) {
		final Object temp = this.getHibernateTemplate().merge(entity);
		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.delete(temp);
				return null;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#deleteAll(java.util.Collection)
	 */
	public void deleteAll(final Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
		this.getHibernateTemplate().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#findById(java.io.Serializable)
	 */
	public T findById(final ID id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAll()
	 */
	public List<T> getAll() {
		//return this.getAllByProperties(null);
		String queryString = "from " + this.entityClass.getName() ;		
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getAllByProperties(java.util.Map)
	 */
	public List<T> getAllByProperties(final Map<String, Object> map) {
		Map<String, Object> hql = this.createHqlByProperties(map);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAllLikeProperties(java.util
	 * .Map)
	 */
	public List<T> getAllLikeProperties(final Map<String, Object> map) {
		Map<String, Object> hql = this.createHqlLikeProperties(map);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}
	
	private List<T> formatRetval(List<Object> list , String[] names) throws InstantiationException, IllegalAccessException{
		List<T> result = new ArrayList<T>();
		for(Object row:list){
			Object[] values = (Object[])row;
			
			T e = this.entityClass.newInstance();				
			for(int i = 0 ;i<names.length;i++){
				Field field;
				
				String key = names[i];
				Object value = values[i];
				System.out.println(String.format("%s=%s",key,value.toString()));
				try {
					field = this.entityClass.getField("set"+key);					
					field.set(e, value);
				} catch (SecurityException e1) {
				} catch (NoSuchFieldException e1) {
				}		
			}
			result.add(e);
		}	
		return result;
	}	
	
	
	private List<Map<String, Object>> formatMapRetval(List<Object> list , String[] names){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(Object row:list){
			Object[] values = (Object[])row;
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i = 0 ;i<names.length;i++){
				String key = names[i];
				Object value = values[i];
				map.put(key, value);
			}
			result.add(map);
		}	
		return result;
	}		

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAllBySQL(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllBySQL(final String sql) {
		List<T> result =  (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return null != result ? result : Collections.EMPTY_LIST;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<T> getAllBySQL(final String sql ,final String[] names) throws InstantiationException, IllegalAccessException {
		List list = getAllBySQL(sql);
		return formatRetval(list,names);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllBySQLToMap(final String sql) {
		List<Map<String, Object>> result =  (List<Map<String, Object>>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
		return null != result ? result : Collections.EMPTY_LIST;
	}
	
	public List<Map<String, Object>> getAllBySQLToMap(final String sql, final String[] names) {
		List list = getAllBySQL(sql);
		return formatMapRetval(list,names);
	}	

	

	/**
	 * 根据queryProperties指定的属性及其比较方式创建HQL语句
	 * 
	 * @param queryProperties
	 * @return 创建的HQL语句，以及HQL语句中使用”?“代替的数据集。格式为Map&lt;String, Object&gt;<br />
	 *         queryString - HQL语句<br />
	 *         values - Object[]数组，即queryString语句中“?”所代表的真实数据
	 */
	private Map<String, Object> createHql(Collection<QueryProperty> queryProperties) {
		StringBuilder queryString = new StringBuilder("from " + this.entityClass.getName() + " where 1 = 1 ");
		List<Object> values = new ArrayList<Object>();
		String orderBy = "";
		for (QueryProperty queryProperty : queryProperties) {
			String propertyName = queryProperty.getName();
			Object value = queryProperty.getValue();
			StringQueryMode stringQueryMode = queryProperty.getStringQueryMode();
			NumberQueryMode numberQueryMode = queryProperty.getNumberQueryMode();
			ObjectQueryMode objectQueryMode = queryProperty.getObjectQueryMode();
			DateQueryMode dateQueryMode = queryProperty.getDateQueryMode();
			Date endTime = queryProperty.getEndTime();
			if (null != propertyName) {
				if (null != stringQueryMode) {
					switch (stringQueryMode) {
					case EQ:
						queryString.append(" and upper(" + propertyName + ") =? ");
						values.add(value.toString().toUpperCase());
						break;
					case LIKE:
						queryString.append(" and upper(" + propertyName + ") like ?");
						values.add("%" + value.toString().toUpperCase() + "%");
						break;
					case IS:
						queryString.append(" and upper(" + propertyName + ") is null");
						break;
					case ISNOT:
						queryString.append(" and upper(" + propertyName + ") is not null");
						break;
					}
				} else if (null != numberQueryMode) {
					switch (numberQueryMode) {
					case EQ:
						queryString.append(" and " + propertyName + " = ?");
						values.add(value);
						break;
					case NEQ:
						queryString.append(" and " + propertyName + " != ?");
						values.add(value);
						break;
					case GT:
						queryString.append(" and " + propertyName + " > ?");
						values.add(value);
						break;
					case LT:
						queryString.append(" and " + propertyName + " < ?");
						values.add(value);
						break;
					case GE:
						queryString.append(" and " + propertyName + " >= ?");
						values.add(value);
						break;
					case LE:
						queryString.append(" and " + propertyName + " <= ?");
						values.add(value);
						break;
					case IS:
						queryString.append(" and " + propertyName + " is null");
						break;
					case ISNOT:
						queryString.append(" and " + propertyName + " is not null");
						break;
					}
				} else if (null != objectQueryMode) {
					switch (objectQueryMode) {
					case EQ:
						queryString.append(" and " + propertyName + " = ?");
						values.add(value);
						break;
					case NEQ:
						queryString.append(" and " + propertyName + " != ?");
						values.add(value);
						break;
					}
				} else if (null != dateQueryMode) {
					switch (dateQueryMode) {
					case BETWEEN:
						queryString.append(" and " + propertyName + " between ? and ?");
						values.add(value);
						values.add(endTime);
						break;
					case NOTBETWEEN:
						queryString.append(" and " + propertyName + " not between ? and ?");
						values.add(value);
						values.add(endTime);
						break;
					case EQ:
						queryString.append(" and " + propertyName + " = ?");
						values.add(value);
						break;
					case NEQ:
						queryString.append(" and " + propertyName + " != ?");
						values.add(value);
						break;
					case GT:
						queryString.append(" and " + propertyName + " > ?");
						values.add(value);
						break;
					case LT:
						queryString.append(" and " + propertyName + " < ?");
						values.add(value);
						break;
					case GE:
						queryString.append(" and " + propertyName + " >e ?");
						values.add(value);
						break;
					case LE:
						queryString.append(" and " + propertyName + " <= ?");
						values.add(value);
						break;
					case IS:
						queryString.append(" and " + propertyName + " is null");
						break;
					case ISNOT:
						queryString.append(" and " + propertyName + " is not null");
						break;
					}
				}
				if(queryProperty.getDataOrder() == DataOrder.ASC){
					if("".equals(orderBy)){
						orderBy = " Order by " +propertyName + " ASC";
					}else{
						orderBy = orderBy + "," + propertyName + " ASC";
					}
				}else if(queryProperty.getDataOrder() == DataOrder.DESC){
					if("".equals(orderBy)){
						orderBy =  " Order by " +propertyName + " DESC";
					}else{
						orderBy = orderBy + " , " + propertyName + " DESC";
					}
				}
			}
		}
		if(!"".equals(orderBy)){
			queryString.append(orderBy);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryString", queryString.toString());
		map.put("values", values.toArray());
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAll(java.util.Collection)
	 */
	@Override
	public List<T> getAll(Collection<QueryProperty> queryProperties) {
		Map<String, Object> hql = this.createHql(queryProperties);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPage(long, long)
	 */
	public Page<T> getPage(final long pageNo, final long pageSize) {
		return this.getPageByProperties(null, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getPageByProperties(java.util.Map,
	 * long, long)
	 */
	public Page<T> getPageByProperties(final Map<String, Object> map, final long pageNo, final long pageSize) {
		Map<String, Object> hql = this.createHqlByProperties(map);
		return this.getPage(hql.get("queryString").toString(), (Object[]) hql.get("values"), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPageLikeProperties(java.util
	 * .Map, long, long)
	 */
	public Page<T> getPageLikeProperties(final Map<String, Object> map, final long pageNo, final long pageSize) {
		Map<String, Object> hql = this.createHqlLikeProperties(map);
		return this.getPage(hql.get("queryString").toString(), (Object[]) hql.get("values"), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPageBySQL(java.lang.String,
	 * long, long)
	 */
	@SuppressWarnings("unchecked")
	public Page<T> getPageBySQL(final String sql, final long pageNo, final long pageSize) {

		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				
				int startIndex = (int) ((pageNo - 1) * pageSize);				
				//if ( startIndex == 0 ){
				//	startIndex =1;
				//}
				query.setFirstResult(startIndex);
				query.setMaxResults((int) pageSize);
				return query.list();
			}
		});
		List<T> rc = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return new Page<T>(pageNo, pageSize, rc.size(), list);
	}
	

	@SuppressWarnings("unchecked")
	public Page<T> getPageBySQL(final String sql, final String[] names, final long pageNo, final long pageSize) throws InstantiationException,
			IllegalAccessException {
		List<Object> list = (List<Object>)  this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				
				int startIndex = (int) ((pageNo - 1) * pageSize);				
				query.setFirstResult(startIndex);
				query.setMaxResults((int) pageSize);		 
				//query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
		List<T> rc = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return new Page<T>(pageNo, pageSize, rc.size(), formatRetval(list,names));
	}

	@SuppressWarnings("unchecked")
	public Page<Map<String, Object>> getPageBySQLToMap(final String sql, final long pageNo, final long pageSize) {
		List<Map<String,Object>> list = (List<Map<String,Object>>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				
				int startIndex = (int) ((pageNo - 1) * pageSize);				
				query.setFirstResult(startIndex);
				query.setMaxResults((int) pageSize);		 
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				return query.list();
			}
		});
		List<T> rc = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return new Page<Map<String, Object>>(pageNo, pageSize, rc.size(), list);
	}
	
	@SuppressWarnings("unchecked")
	public Page<Map<String, Object>> getPageBySQLToMap(final String sql,  final String[] names, final long pageNo, final long pageSize) {
		List<Object> list = (List<Object>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);				
				int startIndex = (int) ((pageNo - 1) * pageSize);				
				query.setFirstResult(startIndex);
				query.setMaxResults((int) pageSize);		 
				return query.list();
			}
		});
		List<T> rc = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		}); 
		return new Page<Map<String, Object>>(pageNo, pageSize, rc.size(), this.formatMapRetval(list, names));
	}	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPage(java.util.Collection,
	 * long, long)
	 */
	@Override
	public Page<T> getPage(Collection<QueryProperty> queryProperties, long pageNo, long pageSize) {
		Map<String, Object> hql = this.createHql(queryProperties);
		return this.getPage(hql.get("queryString").toString(), (Object[]) hql.get("values"), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getCount()
	 */
	public int getCount() {
		return this.getCountByProperties(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getCountByProperties(java.util
	 * .Map)
	 */
	public int getCountByProperties(final Map<String, Object> map) {
		Map<String, Object> hql = this.createHqlByProperties(map);
		String queryString = "select count(*) " + hql.get("queryString").toString();
		Object[] values = (Object[]) hql.get("values");
		long count = (Long) this.getHibernateTemplate().find(queryString, values).iterator().next();
		return (int) count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getCountLikeProperties(java.util
	 * .Map)
	 */
	public int getCountLikeProperties(final Map<String, Object> map) {
		Map<String, Object> hql = this.createHqlLikeProperties(map);
		String queryString = "select count(*) " + hql.get("queryString").toString();
		Object[] values = (Object[]) hql.get("values");
		long count = (Long) this.getHibernateTemplate().find(queryString, values).iterator().next();
		return (int) count;
	}

	/**
	 * 根据指定属性键值对创建HQL语句，属性与值间使用"="进行比较，各属性间使用"and"连接。HQL语句包括“FROM...WHERE子句”。
	 * 
	 * @param map
	 *            属性键值对
	 * @return 创建的HQL语句，以及HQL语句中使用”?“代替的数据集。格式为Map&lt;String, Object&gt;<br />
	 *         queryString - HQL语句<br />
	 *         values - Object[]数组，即queryString语句中“?”所代表的真实数据
	 */
	protected Map<String, Object> createHqlByProperties(final Map<String, Object> map) {
		String queryString = "from " + this.entityClass.getName() + "  model where 1 = 1";
		Object[] values = new Object[0];
		if (null != map) {
			StringBuffer whereCondition = new StringBuffer();
			Iterator<String> iterator = map.keySet().iterator();
			values = new Object[map.keySet().size()];
			int i = 0;
			while (iterator.hasNext()) {
				String propertyName = iterator.next();
				if (map.get(propertyName) instanceof String) {
					whereCondition.append(" and upper(model." + propertyName + ") =? ");
					values[i] = map.get(propertyName).toString().toUpperCase();
				} else {
					whereCondition.append(" and model." + propertyName + "=? ");
					values[i] = map.get(propertyName);
				}
				i++;
			}
			queryString += whereCondition;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("queryString", queryString);
		result.put("values", values);
		return result;
	}

	/**
	 * 根据指定属性键值对创建HQL语句，属性与值间使用"like"进行比较，各属性间使用"and"连接。HQL语句包括“FROM...WHERE子句”。
	 * 
	 * @param map
	 *            属性键值对
	 * @return 创建的HQL语句，以及HQL语句中使用”?“代替的数据集。格式为Map&lt;String, Object&gt;<br />
	 *         queryString - HQL语句<br />
	 *         values - Object[]数组，即queryString语句中“?”所代表的真实数据
	 */
	protected Map<String, Object> createHqlLikeProperties(final Map<String, Object> map) {
		String queryString = "from " + this.entityClass.getName() + "  model where 1 = 1";
		Object[] values = new Object[0];
		if (null != map) {
			StringBuffer whereCondition = new StringBuffer();
			Iterator<String> iterator = map.keySet().iterator();
			values = new Object[map.keySet().size()];
			int i = 0;
			while (iterator.hasNext()) {
				String propertyName = iterator.next();
				if (map.get(propertyName) instanceof String) {
					whereCondition.append(" and upper(model." + propertyName + ") like ? ");
					values[i] = "%" + map.get(propertyName).toString().toUpperCase() + "%";
				} else {
					whereCondition.append(" and model." + propertyName + " = ? ");
					values[i] = map.get(propertyName);
				}
				i++;
			}
			queryString += whereCondition;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("queryString", queryString);
		result.put("values", values);
		return result;
	}

	/**
	 * 通过HQL语句查询所有的数据集。HQL语句包括“FROM...WHERE子句”
	 * 
	 * @param queryString
	 *            HQL语句
	 * @param values
	 *            HQL语句中用？表示代替数据数组
	 * @return 查询到的结果集
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getAll(final String queryString, final Object[] values) {		
		List<T> result = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);		
				for (int i = 0, len = values.length; i < len; i++) {
					query.setParameter(i, values[i]);
				}		
				return query.list();
			}
		});
		return null != result ? result : Collections.EMPTY_LIST;
	}
	
	/**
	 * 通过HQL语句查询所有的数据集。HQL语句包括“FROM...WHERE子句”
	 * 
	 * @param queryString
	 *            HQL语句
	 * @param values
	 *            HQL语句中用？表示代替数据数组
	 * @return 查询到的结果集
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getAll(final String queryString, final Object[] values, final int maxResult) {
		List<T> result = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);		
				for (int i = 0, len = values.length; i < len; i++) {
					query.setParameter(i, values[i]);
				}		
				query.setMaxResults((int) maxResult);
				return query.list();
			}
		});
		return null != result ? result : Collections.EMPTY_LIST;
	}	

	/**
	 * 通过HQL语句查询某一页的数据集。HQL语句包括“FROM...WHERE子句”
	 * 
	 * @param queryString
	 *            HQL语句
	 * @param values
	 *            HQL语句中用？表示代替数据数组
	 * @return 查询到的结果集
	 */
	@SuppressWarnings("unchecked")
	protected Page<T> getPage(final String queryString, final Object[] values, final long pageNo, final long pageSize) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(queryString);
				for (int i = 0, len = values.length; i < len; i++) {
					query.setParameter(i, values[i]);
				}
				int startIndex = (int) ((pageNo - 1) * pageSize);
				//if ( startIndex == 0 ){
				//	startIndex =1;
				//}
				query.setFirstResult(startIndex);
				query.setMaxResults((int) pageSize);
				return query.list();
			}
		});
		return new Page<T>(pageNo, pageSize, this.getCount(queryString, values), list);
	}

	/**
	 * 通过HQL语句取得记录数。HQL语句包括“FROM...WHERE子句”
	 * 
	 * @param queryString
	 *            HQL语句
	 * @param values
	 *            HQL语句中用？表示代替数据数组
	 * @return 记录数
	 */
	protected long getCount(final String queryString, final Object[] values) {
		return (Long) this.getHibernateTemplate().find("select count(*) " + queryString, values).iterator().next();
	}

	/**
	 * 检查日期属性过滤条件是否成立。成立返回true，否则返回false
	 * 
	 * @param dateProperty
	 *            要进行过滤的日期属性条件
	 * @return true - 使用该日期属性过滤条件；false - 不使用日期属性过滤条件。即返回(null != dateProperty
	 *         && null != dateProperty.getPropertyName() && null !=
	 *         dateProperty.getBeginTime() && null != dateProperty.getEndTime()
	 *         && dateProperty.getBeginTime().before(dateProperty.getEndTime()))
	 */
	protected boolean checkDateProperty(final DateProperty dateProperty) {
		if(null == dateProperty || null == dateProperty.getPropertyName())
			return false;
		if(null == dateProperty.getBeginTime() && null == dateProperty.getEndTime())
			return false;
		if(null != dateProperty.getBeginTime() && null != dateProperty.getEndTime() && 
				dateProperty.getBeginTime().after(dateProperty.getEndTime()))
			return false;
		return true;
//		return (null != dateProperty && null != dateProperty.getPropertyName() && null != dateProperty.getBeginTime()
//				&& null != dateProperty.getEndTime() && dateProperty.getBeginTime().before(dateProperty.getEndTime()));
	}

	/**
	 * 将要进行过滤的日期属性条件加入map的HQL语句及其实际数据集中，组成新的HQL语句并返回。
	 * 请在其他where条件组成后才调用此方法组成新的HQL语句，因为该HQL最后将加入排序功能
	 * 
	 * @param map
	 *            HQL语句键值对。格式为Map&lt;String, Object&gt;<br />
	 *            queryString - HQL语句，包括“FROM...WHERE子句”。<br />
	 *            values - Object[]数组，即queryString语句中“?”所代表的真实数据
	 * @param dateProperty
	 *            要进行过滤的日期属性条件
	 * @return 创建的HQL语句，以及HQL语句中使用”?“代替的数据集。格式为Map&lt;String, Object&gt;<br />
	 *         queryString - HQL语句<br />
	 *         values - Object[]数组，即queryString语句中“?”所代表的真实数据
	 */
	protected Map<String, Object> createHql(Map<String, Object> map, final DateProperty dateProperty) {
		StringBuffer sb = new StringBuffer(map.get("queryString").toString());
		Object[] values = (Object[]) map.get("values");
		//if (this.checkDateProperty(dateProperty)) {
			if (null == dateProperty.getBeginTime() && null == dateProperty.getEndTime()) {
		
			} else if(null == dateProperty.getBeginTime()){
				sb.append(" and model." + dateProperty.getPropertyName() + " < ? ");
				values = Arrays.copyOf(values, values.length + 1);
				values[values.length - 1] = dateProperty.getEndTime();
			} else if (null == dateProperty.getEndTime()) {
				sb.append(" and model." + dateProperty.getPropertyName() + " > ? ");
				values = Arrays.copyOf(values, values.length + 1);
				values[values.length - 1] = dateProperty.getBeginTime();				
			} else {
				sb.append(" and model." + dateProperty.getPropertyName() + " between ? and ? ");
			    values = Arrays.copyOf(values, values.length + 2);

			    Date dt1 = dateProperty.getBeginTime();
			    Date dt2 = dateProperty.getEndTime();

			    values[(values.length - 2)] = dt1;
			    values[(values.length - 1)] = dt2;
			       
				values[values.length - 2] = dt1;
				values[values.length - 1] = dt2;
			}
		//}
		sb.append("order by " + dateProperty.getPropertyName() + " " + dateProperty.getDataOrder());
		map.put("queryString", sb).toString();
		map.put("values", values);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAll(com.yixin.framework.base
	 * .model.DateProperty)
	 */
	public List<T> getAll(DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(null), dateProperty);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getAllByProperties(java.util.Map,
	 * com.yixin.framework.base.model.DateProperty)
	 */
	public List<T> getAllByProperties(final Map<String, Object> map, final DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(map), dateProperty);
		System.out.println(hql.get("queryString").toString());
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));		
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getAllByProperties(java.util.Map,
	 * com.yixin.framework.base.model.DateProperty)
	 */
	public List<T> getAllByProperties(final Map<String, Object> map, final DateProperty dateProperty , final int maxResult) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(map), dateProperty);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"),maxResult);		
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getAllLikeProperties(java.util
	 * .Map, com.yixin.framework.base.model.DateProperty)
	 */
	public List<T> getAllLikeProperties(final Map<String, Object> map, final DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlLikeProperties(map), dateProperty);
		return this.getAll(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPage(long, long,
	 * com.yixin.framework.base.model.DateProperty)
	 */
	public Page<T> getPage(final DateProperty dateProperty, final long pageNo, final long pageSize) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(null), dateProperty);
		return this.getPage(hql.get("queryString").toString(), (Object[]) hql.get("values"), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPageByProperties(long, long,
	 * java.util.Map, com.yixin.framework.base.model.DateProperty)
	 */
	public Page<T> getPageByProperties(final Map<String, Object> map, final DateProperty dateProperty,
			final long pageNo, final long pageSize) {
		
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(map), dateProperty);
		return this.getPage(hql.get("queryString").toString(),(Object[]) hql.get("values"), pageNo, pageSize);
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getPageLikeProperties(long,
	 * long, java.util.Map, com.yixin.framework.base.model.DateProperty)
	 */
	public Page<T> getPageLikeProperties(final Map<String, Object> map, final DateProperty dateProperty,
			final long pageNo, final long pageSize) {
		Map<String, Object> hql = this.createHql(this.createHqlLikeProperties(map), dateProperty);
		return this.getPage(hql.get("queryString").toString(), (Object[]) hql.get("values"), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getCount(com.yixin.framework
	 * .base.model.DateProperty)
	 */
	public int getCount(final DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(null), dateProperty);
		return (int) this.getCount(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.dao.BaseDao#getCountByProperties(java.util
	 * .Map, com.yixin.framework.base.model.DateProperty)
	 */
	public int getCountByProperties(final Map<String, Object> map, final DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlByProperties(map), dateProperty);
		return (int) this.getCount(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getCountLikeProperties(java.util
	 * .Map, com.yixin.framework.base.model.DateProperty)
	 */
	public int getCountLikeProperties(final Map<String, Object> map, final DateProperty dateProperty) {
		Map<String, Object> hql = this.createHql(this.createHqlLikeProperties(map), dateProperty);
		return (int) this.getCount(hql.get("queryString").toString(), (Object[]) hql.get("values"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getAllByProperty(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByProperty(String propName, Object value) {
		String queryString = "from " + this.entityClass.getName() + "  model where model." + propName + " = ?";
		List<T> result = this.getHibernateTemplate().find(queryString, value);
		return null != result ? result : Collections.EMPTY_LIST;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllLikeProperty(String propName, Object value) {
		String queryString = "from " + this.entityClass.getName() + "  model where model." + propName + " like % ? %";
		List<T> result = this.getHibernateTemplate().find(queryString, value);
		return null != result ? result : Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.framework.base.dao.BaseDao#getAllByOrProperties(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByOrProperties(Map<String, Object> map) {
		if (map.isEmpty())
			return this.getAll();
		Iterator<String> iterator = map.keySet().iterator();
		/* 创建HQL查询字符串 */
		StringBuilder builder = new StringBuilder("from " + this.entityClass.getName() + "  model where");
		for (int i = 0; iterator.hasNext(); i++) {
			String propertyName = iterator.next();
			if (i > 0)
				builder.append(" or model." + propertyName + " = ?");
			else {
				builder.append(" model." + propertyName + " = ?");
			}
		}
		List<T> result = this.getHibernateTemplate().find(builder.toString(), map.values().toArray());
		return null != result ? result : Collections.EMPTY_LIST;
	}

	
	

	//@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int ExecuteSQL(final String sql) {
	    Session session   = this.getHibernateTemplate().getSessionFactory().openSession();	        
		session.beginTransaction();
		//获取connection,执行静态SQL
		DataSource ds= SessionFactoryUtils.getDataSource(this.getHibernateTemplate().getSessionFactory());
		Connection conn;
		try {
			conn = ds.getConnection();
			Statement state = conn.createStatement();
			state.execute(sql);
			session.beginTransaction().commit();
			return  1;			
		} catch (SQLException e) {
			session.beginTransaction().rollback();
			return 0;
		}		
	}

	 


}