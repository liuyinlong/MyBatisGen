/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jodd.util.StringUtil;
import org.apache.log4j.Logger;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class StringUtils extends StringUtil {
	private static Logger logger = Logger.getLogger(StringUtils.class);

	public static List<Long> stringToList(String ids) {
		return stringToList(ids, ",");
	}

	public static List<Long> stringToList(String ids, String separator) {
		if (isEmpty(ids))
			return new ArrayList();

		List idList = new ArrayList();
		String[] ss = ids.split(separator);
		try {
			String[] arr$ = ss;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; ++i$) {
				String s = arr$[i$];
				idList.add(Long.valueOf(s));
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException(new StringBuilder()
					.append("字符串不能转成Long型，请检查参数格式：").append(ids).toString());
		}
		return idList;
	}

	public static boolean isEmpty(String[] param) {
		String[] arr$ = param;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			String str = arr$[i$];
			if (isEmpty(str))
				return true;
		}

		return false;
	}

	public static String trimHtmlTags(String HTMLStr) {
		if (isEmpty(HTMLStr))
			return "";

		String htmlStr = HTMLStr;
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			String regEx_html = "<[^>]+>";
			Pattern p_script = Pattern.compile(regEx_script, 2);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll("");
			Pattern p_style = Pattern.compile(regEx_style, 2);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll("");
			Pattern p_html = Pattern.compile(regEx_html, 2);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll("");
			textStr = htmlStr.replaceAll(" ", "");
			textStr = htmlStr.replaceAll("<", "<");
			textStr = htmlStr.replaceAll(">", ">");
			textStr = htmlStr.replaceAll("®", "®");
			textStr = htmlStr.replaceAll("&", "&");
			textStr = htmlStr.replaceAll("&[A-Za-z ]+([;])?", "");
		} catch (Exception e) {
			logger.info("去除html标签发生异常.");
		}

		return textStr;
	}

	public static String trimSpecialTags(String HTMLStr) {
		if (isEmpty(HTMLStr))
			return "";

		String htmlStr = HTMLStr;
		htmlStr = htmlStr.replaceAll("<", "&lt;");
		htmlStr = htmlStr.replaceAll(">", "&gt;");
		htmlStr = htmlStr.replaceAll("&", "&amp;");
		htmlStr = htmlStr.replaceAll("'", "&apos;");
		htmlStr = htmlStr.replaceAll("\"", "&quot;");
		return htmlStr;
	}

	public static String arrayToString(Object[] array) {
		if ((array == null) || (array.length == 0))
			return null;

		StringBuilder sb = new StringBuilder();
		Object[] arr$ = array;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			Object o = arr$[i$];
			sb.append(o).append(",");
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	public static boolean checkNumber(String string) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(string);
		return (isNum.matches());
	}

	public static String changeUtf8ToUnicode(byte[] aByte) {
		StringBuffer sUnicodeStringBuffer = new StringBuffer();
		int sLength = aByte.length;

		for (int i = 0; i < sLength; ++i) {
			int sInt_2;
			int sInt_1 = aByte[i] & 0xFF;
			switch (sInt_1 >> 4) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				sUnicodeStringBuffer.append((char) aByte[i]);
				break;
			case 12:
			case 13:
				if (i + 1 < sLength) {
					sInt_2 = (char) aByte[(i + 1)];
					if ((sInt_2 & 0xC0) == 128) {
						sUnicodeStringBuffer
								.append((char) ((sInt_1 & 0x1F) << 6 | sInt_2 & 0x3F));
						++i;
					}
				}
				break;
			case 14:
				if (i + 2 < sLength) {
					sInt_2 = aByte[(i + 1)];
					int sInt_3 = aByte[(i + 2)];
					if (((sInt_2 & 0xC0) == 128) || ((sInt_3 & 0xC0) == 128)) {
						sUnicodeStringBuffer
								.append((char) ((sInt_1 & 0xF) << 12
										| (sInt_2 & 0x3F) << 6 | (sInt_3 & 0x3F) << 0));

						i += 2;
					}
				}
			case 8:
			case 9:
			case 10:
			case 11:
			}
		}
		return sUnicodeStringBuffer.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "1,2,3,4,5,";
		System.out.println(URLEncoder.encode("中", "UNICODE"));
		System.out.println(StringUtil.replaceLast(str, ",", ""));
	}
}
