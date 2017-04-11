/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class MyHandler implements RejectedExecutionHandler {
	private static int rejectedCount = 1;

	public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
		System.out.println("拒绝的任务" + (rejectedCount++));
		arg0.run();
		System.out.println("完成任务数：" + arg1.getCompletedTaskCount());
		System.out.println("总任务数：" + arg1.getTaskCount());
	}
}
