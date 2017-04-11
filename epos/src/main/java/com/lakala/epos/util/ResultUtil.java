/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import com.lakala.epos.beans.RetStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class ResultUtil {
	public static Map<String, Object> getResult(String retCode, String errMsg) {
		RetStatus retStatus = new RetStatus(retCode, errMsg);
		Map res = new HashMap();
		res.put("retStatus", retStatus);

		return res;
	}

	public static Map<String, Object> getResult(Object resData) {
		RetStatus retStatus = null;

		retStatus = new RetStatus("0000", "成功");

		Map res = new HashMap();
		res.put("retStatus", retStatus);
		res.put("retData", resData);

		return res;
	}
}
