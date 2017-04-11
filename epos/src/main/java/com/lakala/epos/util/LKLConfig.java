/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class LKLConfig {
	private static Properties props = new Properties();

	public static String getValue(String key) {
		return props.getProperty(key).trim();
	}

	public static void updatePropertiepos(String key, String value) {
		props.setProperty(key, value);
	}

	static {
		try {
			props = PropertiesUtils
					.loadProperties(new String[] { "application.properties" });
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
