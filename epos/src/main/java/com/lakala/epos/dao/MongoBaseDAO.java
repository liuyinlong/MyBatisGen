/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.dao;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.Key;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.github.jmkgreen.morphia.query.UpdateResults;
import com.lakala.epos.util.LKLConfig;
import com.lakala.epos.util.MongoDBConnection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
@Component
public class MongoBaseDAO<T> {
	private Datastore ds = null;
	private String mongoName = LKLConfig.getValue("mongo.name");

	public MongoBaseDAO() {
		this.ds = MongoDBConnection.getDataStore(this.mongoName);
		this.ds.ensureIndexes();
		this.ds.ensureCaps();
	}

	public void add(T t) {
		this.ds.save(t);
	}

	public void delete(T t) {
		this.ds.delete(t);
	}

	public void delete(Query<T> query) {
		this.ds.delete(query);
	}

	public Key<T> merge(T entity) {
		return this.ds.merge(entity);
	}

	public int update(T t, UpdateOperations<T> op) {
		return this.ds.update(t, op).getUpdatedCount();
	}

	public void update(Query<T> query, UpdateOperations<T> op) {
		this.ds.update(query, op);
	}

	public List<T> query(Query<T> query) {
		return query.asList();
	}

	public long count(Query<T> query) {
		return query.countAll();
	}

	public Datastore getDS() {
		return this.ds;
	}
}