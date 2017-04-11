/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.service;

import com.lakala.epos.beans.mongo.Student;
import com.lakala.epos.exception.ServiceException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public interface StudentService {
	public abstract void insert(Student paramStudent) throws ServiceException;

	public abstract void delete(ObjectId paramObjectId) throws ServiceException;

	public abstract void update(Student paramStudent) throws ServiceException;

	public abstract void updateBetch(Student paramStudent)
			throws ServiceException;

	public abstract void merge(Student paramStudent) throws ServiceException;

	public abstract Student query(ObjectId paramObjectId)
			throws ServiceException;

	public abstract List<Student> query(Student paramStudent)
			throws ServiceException;

	public abstract List<Student> findAll(int paramInt1, int paramInt2)
			throws ServiceException;
}
