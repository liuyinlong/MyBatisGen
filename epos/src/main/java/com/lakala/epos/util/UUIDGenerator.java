/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;
import java.util.UUID;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class UUIDGenerator {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();

		return str.replace("-", "");
	}

	public static String[] getUUID(int number) {
		if (number < 1)
			return null;

		String[] ss = new String[number];
		for (int i = 0; i < number; ++i)
			ss[i] = getUUID();

		return ss;
	}

	public static void main(String[] args) {
		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; ++i)
			System.out.println("ss[" + i + "]=====" + ss[i]);
	}
}
