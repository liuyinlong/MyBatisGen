/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class Tex {
	static int i = 2;

	public void print() {
		if (i > 0) {
			System.out.println(i);
			i -= 1;
			print();
		}
	}

	public static void main(String[] args) {
		Tex t = new Tex();

		t.print();
	}
}
