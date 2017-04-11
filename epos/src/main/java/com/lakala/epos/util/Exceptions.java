/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */

public class Exceptions {
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException)
			return ((RuntimeException) e);

		return new RuntimeException(e);
	}

	public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public static boolean isCausedBy(Exception ex,
			Class<? extends Exception>[] causeExceptionClassepos) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			Class[] arr$ = causeExceptionClassepos;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; ++i$) {
				Class causeClass = arr$[i$];
				if (causeClass.isInstance(cause))
					return true;
			}

			cause = cause.getCause();
		}
		return false;
	}
}
