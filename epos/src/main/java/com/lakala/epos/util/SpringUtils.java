/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class SpringUtils {
	static ClassPathXmlApplicationContext context = null;

	public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public static void setContext(ClassPathXmlApplicationContext context) {
		context = context;
	}

	public static void start() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			context.start();
		}
	}

	public static <T> T getBean(Class<T> type) {
		return context.getBean(type);
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	static {
		if (context == null)
			context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
	}
}
