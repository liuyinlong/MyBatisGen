/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.service.impl;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.FieldEnd;
import com.github.jmkgreen.morphia.query.Query;
import com.github.jmkgreen.morphia.query.UpdateOperations;
import com.lakala.epos.beans.mongo.Student;
import com.lakala.epos.dao.mongo.StudentDao;
import com.lakala.epos.exception.ServiceException;
import com.lakala.epos.service.StudentService;
import com.mongodb.WriteConcern;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentServiceImpl implements StudentService {
	protected static Logger logger = LoggerFactory
			.getLogger(StudentServiceImpl.class);

	@Autowired
	StudentDao dao;

	public void insert(Student student) throws ServiceException {
		this.dao.add(student);
		logger.info("===保存成功！===");
	}

	public void delete(ObjectId id) throws ServiceException {
		logger.info("===delete()===");
		Student student = new Student();

		this.dao.delete(student);
		logger.info("===删除成功！===");
	}

	public void merge(Student student) throws ServiceException {
		this.dao.getDS().merge(student);
		logger.info("===更新成功！===");
	}

	public void update(Student student) throws ServiceException {
		Query query = this.dao.getDS().createQuery(Student.class);
		query.field("loginName").equal(student.getLoginName());

		UpdateOperations up = this.dao.getDS().createUpdateOperations(
				Student.class);

		up.set("name", student.getName()).set("updateDate",
				student.getUpdateDate());

		this.dao.getDS().update(query, up, false, WriteConcern.SAFE);
		logger.info("===更新成功！===");
	}

	public void updateBetch(Student student) throws ServiceException {
		Query query = this.dao.getDS().createQuery(Student.class);
		query.field("loginName").contains(student.getLoginName());

		System.out.println("====符合条件的记录数为：" + query.asList().size());
		UpdateOperations up = this.dao.getDS().createUpdateOperations(
				Student.class);

		up.set("name", student.getName()).set("updateDate",
				student.getUpdateDate());

		this.dao.getDS().update(query, up, false, WriteConcern.SAFE);
		logger.info("===更新成功！===");
	}

	public Student query(ObjectId id) throws ServiceException {
		Student student = (Student) this.dao.getDS().get(Student.class, id);
		logger.info("===user===" + student.toString());

		return student;
	}

	public List<Student> query(Student student) throws ServiceException {
		List studentList = new ArrayList();

		Query query = this.dao.getDS().createQuery(Student.class);
		query.field("name").contains(student.getName());
		studentList = this.dao.query(query);
		logger.info("===返回成功！===");
		return studentList;
	}

	public List<Student> findAll(int start, int end) throws ServiceException {
		return this.dao.findAll(start, end);
	}
}
