/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.constants;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class Constant {
	public static final String EPOS_BUSID = "M50005";
	public static final String EPOS_CHNCODE = "10000000020";
	public static final String EPOS_INSTCODE = "10000000020";
	public static final String LKL_RETURN_CODE_SUCCESS = "0000";
	public static final String LKL_RETURN_MSG_SUCCESS = "成功";
	public static final String APPLICATION_XML = "application/xml";
	public static final String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";
	public static final String JSON = "application/json";
	public static final String JSON_UTF_8 = "application/json; charset=UTF-8";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";
	public static final String FORM = "application/x-www-form-urlencoded";
	public static final String FORM_UTF_8 = "application/x-www-form-urlencoded; charset=UTF-8";
	public static final String VERCODE = "6f5810706793febd2be3a5807d972d07";
	public static final Long HTTP_CONNECTION_TIMEOUT = Long.valueOf(600000L);
	public static final Long HTTP_SO_TIMEOUT = Long.valueOf(600000L);
	public static final Long HTTP_CONNECTION_MANAGER_TIMEOUT = Long
			.valueOf(500000L);
	public static final String ERROR_CODE_1007 = "1007";
	public static final String ERROR_MSG_1007 = "请求数据为空";
	public static final String EPOS_AUTHUSER_ERROR_CODE = "10101";
	public static final String EPOS_QUERYUSERSIGNLIST_ERROR_CODE = "10102";
	public static final String EPOS_QUERYCARDSIGNLIST_ERROR_CODE = "10103";
	public static final String EPOS_AUTHMERCHANT_ERROR_CODE = "10104";
	public static final String EPOS_QUERYMERCHANTPAYLIST_ERROR_CODE = "10105";
	public static final String EPOS_GETANONYMOUSINFO_ERROR_CODE = "10106";
	public static final String EPOS_LOGIN_ERROR_CODE = "10107";
	public static final String EPOS_LOGIN_STATUS_ERROR_CODE = "10108";
	public static final String EPOS_LOGIN_STATUS_ACCESSDENY_ERROR_CODE = "10109";
	public static final String EPOS_LOGIN_STATUS_PASSWORD_ERROR_CODE = "10110";
	public static final String EPOS_LOGIN_STATUS_USER_NOTEXISTS_ERROR_CODE = "10111";
	public static final String EPOS_CHECKUSERTOKEN_ERROR_CODE = "10112";
	public static final String EPOS_WLOGIN_ERROR_CODE = "10113";
	public static final String EPOS_AUTHUSER_ERROR_MSG = "用户认证接口异常！";
	public static final String EPOS_QUERYUSERSIGNLIST_ERROR_MSG = "查询用户签约列表接口异常！";
	public static final String EPOS_QUERYCARDSIGNLIST_ERROR_MSG = "查询卡签约列表接口异常！";
	public static final String EPOS_AUTHMERCHANT_ERROR_MSG = "商户验证接口接口异常！";
	public static final String EPOS_QUERYMERCHANTPAYLIST_ERROR_MSG = "支付选项查询接口异常！";
	public static final String EPOS_GETANONYMOUSINFO_ERROR_MSG = "获取匿名用户ID接口异常！";
	public static final String EPOS_LOGIN_ERROR_MSG = "获取用户安全信息接口异常！";
	public static final String EPOS_WLOGIN_ERROR_MSG = "获取登录用户信息接口异常！";
	public static final String EPOS_LOGIN_STATUS_ERROR_MSG = "用户登录状态异常！";
	public static final String EPOS_LOGIN_STATUS_ACCESSDENY_ERROR_MSG = "访问拒绝，请确认登录状态！";
	public static final String EPOS_LOGIN_STATUS_PASSWORD_ERROR_MSG = "登录密码错误！";
	public static final String EPOS_LOGIN_USER_NOTEXISTS_PASSWORD_ERROR_MSG = "该用户不存在！";
	public static final String EPOS_CHECKUSERTOKEN_ERROR_MSG = "输入的手机号与Token校验失败！";
	public static final String IS_OPEN = "校验终端是否开通接口";
	public static final String QUERY_CARDINFO = "根据开通时手机获取签约卡列表接口";
	public static final String LOGIN = "无卡支付的登录接口";
}