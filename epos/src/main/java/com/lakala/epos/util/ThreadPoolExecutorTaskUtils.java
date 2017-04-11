/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class ThreadPoolExecutorTaskUtils {
	private static ThreadPoolExecutor poolExecutor;
	private static ThreadPoolExecutorTaskUtils instance;
	private static int corePoolSize = 20;
	private static int maximumPoolSize = 50;
	private static long keepAliveTime = 1000L;
	private static int queueSize = 100;

	public ThreadPoolExecutorTaskUtils() {
		if (poolExecutor == null)
			poolExecutor = new ThreadPoolExecutor(corePoolSize,
					maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue(queueSize),
					new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public static synchronized ThreadPoolExecutorTaskUtils getInstance() {
		if (instance == null)
			instance = new ThreadPoolExecutorTaskUtils();

		return instance;
	}

	public static synchronized ThreadPoolExecutor getThreadPoolExecutor() {
		if (poolExecutor == null) {
			poolExecutor = new ThreadPoolExecutor(corePoolSize,
					maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue(queueSize),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}

		return poolExecutor;
	}

	public void executeTask(Runnable command) {
		poolExecutor.execute(command);
	}
}