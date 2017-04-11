/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.resource;

import com.lakala.ca.dto.GetAnonymousInfoRequest;
import com.lakala.ca.dto.GetAnonymousInfoResponse;
import com.lakala.ca.dto.UserDTO;
import com.lakala.ca.service.IUserService;
import com.lakala.core.dto.ApplicationContext;
import com.lakala.epos.beans.AuthUser;
import com.lakala.epos.beans.MerchantVerify;
import com.lakala.epos.beans.PayItems;
import com.lakala.epos.beans.ResultData;
import com.lakala.epos.beans.RetStatus;
import com.lakala.epos.beans.SignCards;
import com.lakala.epos.beans.SignUsers;
import com.lakala.epos.service.EPOSService;
import com.lakala.epos.util.JaxbMapper;
import com.lakala.epos.util.JsonMapper;
import com.lakala.epos.util.RSAEncrypt;
import com.lakala.epos.util.ResultUtil;
import com.lakala.epos.util.SpringUtils;
import com.lakala.epos.util.StringUtils;
import com.lakala.epos.util.UUIDGenerator;
import com.lakala.service.MerchantService;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Path("/epos/")
@Component
public class EPOSResource {
	protected static Logger logger = LoggerFactory
			.getLogger(EPOSResource.class);
	private static JsonMapper binder = JsonMapper.nonEmptyMapper();

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private EPOSService eservice;
	private String busid = "M50005";
	private String chncode = "10000000020";
	private String instcode = "10000000020";
	private static RSAEncrypt rsaEncrypt = new RSAEncrypt();

	@Autowired
	IUserService userService;

	public EPOSResource() {
		this.merchantService = ((MerchantService) SpringUtils
				.getBean(MerchantService.class));
		this.userService = ((IUserService) SpringUtils
				.getBean(IUserService.class));
		this.eservice = ((EPOSService) SpringUtils.getBean(EPOSService.class));
	}

	@POST
	@Path("authUser")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String authUser(@FormParam("custid") String custid,
			@FormParam("mercid") String mercid) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==authUser()==请求数据：busid=" + this.busid + ",custid="
				+ custid + ",chncode=" + this.chncode + ",mercid=" + mercid
				+ ",instcode=" + this.instcode + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { custid, mercid })) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		String xml = this.merchantService.authenticateUser(this.busid, custid,
				this.chncode, mercid, this.instcode, sid);
		logger.info("\n==authUser()==XML返回数据：" + JaxbMapper.formatXml(xml));

		AuthUser response = (AuthUser) JaxbMapper.fromXml(xml, AuthUser.class);

		if (response == null) {
			Map result = ResultUtil.getResult("10101", "用户认证接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==authUser()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(response)));
		return binder.toJson(ResultUtil.getResult(response));
	}

	@POST
	@Path("queryUserSignList")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String queryUserSignList(@FormParam("mobile") String mobile,
			@FormParam("custid") String custid,
			@FormParam("mercid") String mercid,
			@FormParam("userToken") String userToken) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==queryUserSignList()==请求数据：busid=" + this.busid
				+ ",mobile=" + mobile + ",custid=" + custid + ",chncode="
				+ this.chncode + ",mercid=" + mercid + ",instcode="
				+ this.instcode + ",instcode=" + this.instcode + ",userToken="
				+ userToken + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { mobile, custid, mercid,
				userToken }))
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));

		ResultData response = null;
		try {
			response = this.eservice.checkUserToken(mobile, userToken);
		} catch (Exception e) {
			Map result = ResultUtil.getResult("10112", "输入的手机号与Token校验失败！");

			return binder.toJson(result);
		}

		String xml = null;
		SignUsers signUsers = null;
		if (StringUtils.equals(response.getRetStatus().getRetCode(), "0000")) {
			xml = this.merchantService.queryUserSignList(this.busid, mobile,
					custid, this.chncode, mercid, this.instcode, sid);
			logger.info("\n==queryUserSignList()==XML返回数据："
					+ JaxbMapper.formatXml(xml));

			signUsers = (SignUsers) JaxbMapper.fromXml(xml, SignUsers.class);
		}

		if (signUsers == null) {
			Map result = ResultUtil.getResult("10102", "查询用户签约列表接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==queryUserSignList()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(signUsers)));
		return binder.toJson(ResultUtil.getResult(signUsers));
	}

	@POST
	@Path("queryCardSignList")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String queryCardSignList(@FormParam("cardCode") String cardCode,
			@FormParam("custid") String custid,
			@FormParam("mercid") String mercid) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==queryCardSignList()==请求数据：busid=" + this.busid
				+ ",cardCode=" + cardCode + ",custid=" + custid + ",chncode="
				+ this.chncode + ",mercid=" + mercid + ",instcode="
				+ this.instcode + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { cardCode, custid, mercid })) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		String xml = this.merchantService.queryCardSignList(this.busid,
				cardCode, custid, this.chncode, mercid, this.instcode, sid);
		logger.info("\n==queryCardSignList()==XML返回数据："
				+ JaxbMapper.formatXml(xml));

		SignCards response = (SignCards) JaxbMapper.fromXml(xml,
				SignCards.class);

		if (response == null) {
			Map result = ResultUtil.getResult("10103", "查询卡签约列表接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==queryCardSignList()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(response)));
		return binder.toJson(ResultUtil.getResult(response));
	}

	@POST
	@Path("authMerchant")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String authMerchant(@FormParam("custid") String custid,
			@FormParam("mercid") String mercid) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==authMerchant()==请求数据：busid=" + this.busid + ",custid="
				+ custid + ",instcode=" + this.instcode + ",mercid=" + mercid
				+ ",sid=" + sid);

		if (StringUtils.isEmpty(mercid)) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		String xml = this.merchantService.authenticateMerchant(this.busid,
				custid, this.instcode, mercid, sid);

		logger.info("\n==authMerchant()==返回数据：" + JaxbMapper.formatXml(xml));
		MerchantVerify response = (MerchantVerify) JaxbMapper.fromXml(xml,
				MerchantVerify.class);

		if (response == null) {
			Map result = ResultUtil.getResult("10104", "商户验证接口接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==authMerchant()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(response)));
		return binder.toJson(ResultUtil.getResult(response));
	}

	@POST
	@Path("queryMerchantPayList")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String queryMerchantPayList(@FormParam("custid") String custid,
			@FormParam("mercid") String mercid) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==queryMerchantPayList()==请求数据：busid=" + this.busid
				+ ",custid=" + custid + ",instcode=" + this.instcode
				+ ",mercid=" + mercid + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { custid, mercid })) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		String xml = this.merchantService.queryMerchantPayList(this.busid,
				custid, this.instcode, mercid, sid);
		logger.info("\n==queryMerchantPayList()==返回数据："
				+ JaxbMapper.formatXml(xml));

		PayItems response = (PayItems) JaxbMapper.fromXml(xml, PayItems.class);

		if (response == null) {
			Map result = ResultUtil.getResult("10105", "支付选项查询接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==queryMerchantPayList()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(response)));
		return binder.toJson(ResultUtil.getResult(response));
	}

	@POST
	@Path("getAnonymousInfo")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String getAnonymousInfo(@FormParam("mobile") String mobile,
			@FormParam("realName") String realName) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==getAnonymousInfo()==请求数据：mobile=" + mobile
				+ ",realName=" + realName + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { mobile, realName })) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		GetAnonymousInfoRequest req = new GetAnonymousInfoRequest();

		req.setMobileNum(mobile);
		req.setRealName(realName);

		GetAnonymousInfoResponse resp = this.userService.getAnonymousInfo(req);

		if (resp == null) {
			Map result = ResultUtil.getResult("10106", "获取匿名用户ID接口异常！");

			return binder.toJson(result);
		}
		logger.info("\n==getAnonymousInfo()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(resp)));
		return binder.toJson(ResultUtil.getResult(resp));
	}

	@POST
	@Path("wlogin")
	@Consumes
	@Produces({ "application/json; charset=UTF-8" })
	public String wlogin(@FormParam("mobile") String mobile,
			@FormParam("password") String password) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==login()==请求数据：mobile=" + mobile + ",sid=" + sid);

		if (StringUtils.isEmpty(new String[] { mobile, password })) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		String entcrytPassword = null;
		try {
			if (rsaEncrypt.getPublicKey() == null) {
				rsaEncrypt.loadPublicKey(super.getClass().getResourceAsStream(
						"/rsa_public_key.pem"));
				logger.info("===wlogin()===加载公钥成功===");
			}
			byte[] encryptPwd = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(),
					password.getBytes());
			entcrytPassword = RSAEncrypt.byteArrayToString(encryptPwd);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		ResultData resp = null;
		try {
			resp = this.eservice.wlogin(mobile, entcrytPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (null == resp) {
			Map result = ResultUtil.getResult("10113", "获取登录用户信息接口异常！");
			return binder.toJson(result);
		}
		logger.info("\n==wlogin()==返回Json数据：" + binder.toJson(resp));
		return binder.toJson(resp);
	}

	@POST
	@Consumes
	@Path("verifyLoginName")
	@Produces({ "application/json; charset=UTF-8" })
	public String verifyLoginName(@FormParam("mobile") String mobile) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==verifyLoginName()==请求数据：mobile=" + mobile + ",mobile="
				+ mobile + ",sid=" + sid);

		if (StringUtils.isEmpty(mobile)) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		boolean resp = this.userService.verifyLoginName(mobile);

		logger.info("\n==verifyLoginName()==返回Json数据：" + resp);
		return binder.toJson(ResultUtil.getResult(Boolean.valueOf(resp)));
	}

	@POST
	@Consumes
	@Path("getByUid")
	@Produces({ "application/json; charset=UTF-8" })
	public String getByUid(@FormParam("userId") String userId) {
		String sid = UUIDGenerator.getUUID();
		logger.info("\n==getByUid()==请求数据：userId=" + userId + ",sid=" + sid);

		if (StringUtils.isEmpty(userId)) {
			return binder.toJson(ResultUtil.getResult("1007", "请求数据为空"));
		}

		ApplicationContext context = new ApplicationContext();
		context.setOperatorId(Long.valueOf(3629521710450999296L));
		context.setSystemCode("LKL_FAST_PAYMENT");
		UserDTO resp = this.userService.getByUid(Long.valueOf(userId), context);

		if (null != resp) {
			Map map = new HashMap();

			map.put("userId", String.valueOf(resp.getId()));
			map.put("mobile", String.valueOf(resp.getMobileNum()));
			logger.info("\n==getByUid()==返回Json数据：" + map);
			return binder.toJson(ResultUtil.getResult(map));
		}

		Map result = ResultUtil.getResult("10111", "该用户不存在！");

		logger.info("\n==login()==返回Json数据："
				+ binder.toJson(ResultUtil.getResult(result)));
		return binder.toJson(ResultUtil.getResult(result));
	}
}
