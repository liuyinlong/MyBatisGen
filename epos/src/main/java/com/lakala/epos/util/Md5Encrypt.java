/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class Md5Encrypt {
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String md5(String text) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}

		try {
			msgDigest.update(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(
					"System doesn't support your  EncodingException.");
		}

		byte[] bytes = msgDigest.digest();

		String md5Str = new String(encodeHex(bytes));

		return md5Str;
	}

	public static char[] encodeHex(byte[] data) {
		int l = data.length;

		char[] out = new char[l << 1];

		int i = 0;
		for (int j = 0; i < l; ++i) {
			out[(j++)] = DIGITS[((0xF0 & data[i]) >>> 4)];
			out[(j++)] = DIGITS[(0xF & data[i])];
		}

		return out;
	}

	public static void main(String[] args) {
		System.out
				.println(md5("20060301mmt_zdsj12345652315ff0e7d04a93ea59be2020001234562013091220134494949949422013449494994942000000020000088txy"));
	}
}
