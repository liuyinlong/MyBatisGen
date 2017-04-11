/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class PropertiesUtils {
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);
	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static Properties loadProperties(String[] resourcesPaths)
			throws IOException {
		Properties props = new Properties();

		String[] arr$ = resourcesPaths;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			String location = arr$[i$];

			logger.debug("Loading properties file from:" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is,
						"UTF-8"));
			} catch (IOException ex) {
				logger.info("Could not load properties from classpath:"
						+ location + ": " + ex.getMessage());
			} finally {
				if (is != null)
					is.close();
			}
		}

		return props;
	}
}
