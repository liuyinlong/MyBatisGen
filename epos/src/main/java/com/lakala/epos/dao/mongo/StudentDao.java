/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.dao.mongo;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.lakala.epos.beans.mongo.Student;
import com.lakala.epos.dao.MongoBaseDAO;
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
public class StudentDao extends MongoBaseDAO<Student> {
	public List<Student> findAll(int start, int end) {
		Query query = getDS().createQuery(Student.class);
		query.skip(start).limit(end).order("createDate");
		return query.asList();
	}
}