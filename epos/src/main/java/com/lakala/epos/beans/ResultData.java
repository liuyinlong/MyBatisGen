/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import java.util.List;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class ResultData {
	private RetStatus retStatus;
	private Object retData;

	public ResultData() {
	}

	public ResultData(Object retDate, String sid) {
		this.retStatus = new RetStatus("0", "succeposs");
		this.retData = retDate;
	}

	public RetStatus getRetStatus() {
		return this.retStatus;
	}

	public void setRetStatus(RetStatus retStatus) {
		this.retStatus = retStatus;
	}

	public Object getRetData() {
		return this.retData;
	}

	public void setRetData(Object retData) {
		if (retData instanceof List) {
			String className = retData.getClass().getName();
			try {
				this.retData = Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			this.retData = retData;
		}
	}
}