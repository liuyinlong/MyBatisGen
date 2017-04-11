/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.service.impl;

import com.lakala.epos.beans.CardInfo;
import com.lakala.epos.beans.ResultData;
import com.lakala.epos.beans.RetCode;
import com.lakala.epos.service.EPOSService;
import com.lakala.epos.util.HttpClientUtil;
import com.lakala.epos.util.JsonMapper;
import com.lakala.epos.util.LKLConfig;
import com.lakala.epos.util.StringUtils;
import jodd.util.StringUtil;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
@Component
public class EPOSServiceImpl implements EPOSService {
	protected static Logger logger = LoggerFactory
			.getLogger(EPOSServiceImpl.class);
	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public RetCode isOpen(String param) {
		RetCode response = null;

		String url = LKLConfig.getValue("lkl.epos.isOpen");
		try {
			String resString = (String) HttpClientUtil.httpPostParam(url,
					"校验终端是否开通接口", param);

			if (!(StringUtils.isEmpty(resString)))
				response = (RetCode) binder.fromJson(resString, RetCode.class);
		} catch (Exception e) {
			logger.warn("===校验终端是否开通接口===" + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public CardInfo queryCardInfo(String mobile, String custid) {
		CardInfo response = null;

		String url = LKLConfig.getValue("lkl.epos.queryCardInfo");

		NameValuePair n1 = new NameValuePair("mobile", mobile);
		NameValuePair n2 = new NameValuePair("custid", custid);
		try {
			logger.info("===QUERY_CARDINFO===根据开通时手机获取签约卡列表接口");

			String resString = (String) HttpClientUtil.httpPostParam(url,
					"根据开通时手机获取签约卡列表接口", new NameValuePair[] { n1, n2 });

			if (!(StringUtils.isEmpty(resString)))
				response = (CardInfo) binder
						.fromJson(resString, CardInfo.class);
		} catch (Exception e) {
			logger.warn("===校验终端是否开通接口===" + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public ResultData wlogin(String loginName, String pwd) throws Exception {
		ResultData result = null;

		String url = LKLConfig.getValue("lkl.epos.wlogin");
		url = StringUtil.replaceFirst(url, "#loginName", loginName);
		url = url + "?vercode=6f5810706793febd2be3a5807d972d07&password=" + pwd;
		try {
			String resString = (String) HttpClientUtil.httpGetParam(url,
					"无卡支付的登录接口");

			if (!(StringUtils.isEmpty(resString)))
				result = (ResultData) binder.fromJson(resString,
						ResultData.class);
		} catch (Exception e) {
			logger.warn("===无卡支付的登录接口===" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public ResultData checkUserToken(String loginName, String userToken)
			throws Exception {
		ResultData result = null;

		String url = LKLConfig.getValue("lkl.epos.checkUserToken");
		url = StringUtil.replaceFirst(url, "#loginName", loginName);
		url = StringUtil.replaceFirst(url, "#userToken", userToken);

		url = url + "?vercode=6f5810706793febd2be3a5807d972d07";
		try {
			String resString = (String) HttpClientUtil.httpGetParam(url,
					"无卡支付的登录接口");

			if (!(StringUtils.isEmpty(resString)))
				result = (ResultData) binder.fromJson(resString,
						ResultData.class);
		} catch (Exception e) {
			logger.warn("===无卡支付的登录接口===" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
