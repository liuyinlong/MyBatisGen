/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.service;

import com.lakala.epos.beans.CardInfo;
import com.lakala.epos.beans.ResultData;
import com.lakala.epos.beans.RetCode;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public interface EPOSService {
	public abstract RetCode isOpen(String paramString);

	public abstract CardInfo queryCardInfo(String paramString1,
			String paramString2);

	public abstract ResultData wlogin(String paramString1, String paramString2)
			throws Exception;

	public abstract ResultData checkUserToken(String paramString1,
			String paramString2) throws Exception;
}