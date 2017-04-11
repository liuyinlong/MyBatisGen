/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.util;

import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class HttpClientUtil {
	protected static Logger logger = LoggerFactory
			.getLogger(HttpClientUtil.class);
	private static HttpClient httpClient = null;

	public static synchronized HttpClient getHttpClient() {
		if ((httpClient == null) && (!(httpClient instanceof HttpClient))) {
			MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
			httpClient = new HttpClient(connectionManager);

			httpClient.getHttpConnectionManager().getParams()
					.setDefaultMaxConnectionsPerHost(300);

			httpClient.getHttpConnectionManager().getParams()
					.setMaxTotalConnections(500);
			httpClient.getParams().setContentCharset("UTF-8");
			httpClient.getParams().setVersion(HttpVersion.HTTP_1_1);

			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(90000);

			httpClient.getHttpConnectionManager().getParams()
					.setSoTimeout(120000);

			httpClient.getParams().setParameter(
					"http.connection-manager.timeout", Long.valueOf(30000L));

			httpClient.getParams().setParameter("http.method.retry-handler",
					new DefaultHttpMethodRetryHandler(2, true));
		}
		return httpClient;
	}

	public static int testHTTPConnection(String url)
  {
    logger.info("==into tepostHTTPConnection==");
    int status = 555;
    PostMethod postMethod = new PostMethod(url);
    HttpClient client = getHttpClient();
    try {
      status = client.executeMethod(postMethod);

      throw new HttpException("无卡支付Http通信异常！请尽快联系作业系统！！！");
    }
    catch (HttpException e) {
      logger.warn("【url=" + url + "】【httpStatus=" + status + "】无卡支付接口--HTTP连接出现异常！！！:" + e.getMessage());
    }
    catch (IOException e) {
      logger.warn("【url=" + url + "】【httpStatus=" + status + "】无卡支付接口--连接出现IO异常！！！:" + e.getMessage());
    }
    catch (Exception e) {
      logger.warn("【url=" + url + "】【httpStatus=" + status + "】无卡支付接口--测试通信中系统异常！！！：" + e.getMessage()); } finally {
      finally;
    }
    postMethod.releaseConnection();
    return status;
  }

	public static void main(String[] args) {
		int status = testHTTPConnection(LKLConfig.getValue("lkl.epos.isOpen"));
		System.out.println(status);
	}

	public static String httpPostRequest(PostMethod postMethod)
			throws ConnectTimeoutException, HttpException, IOException {
		String response = null;
		int status = 500;
		HttpClient client = getHttpClient();
		try {
			status = client.executeMethod(postMethod);
			logger.info(status + "--------状态------------");
			if (status != 200) {
				logger.warn("--------------http requepost Failed httpStatus"
						+ status + "-----------------");
				throw new Exception("http请求失败!");
			}
			if (status != 200)
				break label232;
			logger.info("-------------Http 通信成功-----------------");
			response = postMethod.getResponseBodyAsString();
			label232: logger.info("---------服务器返回值---------:" + response);
		} catch (Exception e) {
			logger.warn("-----------通信异常---------");

			logger.info("Http status=" + status + ",response:" + response);

			postMethod.releaseConnection();
		} finally {
			logger.info("Http status=" + status + ",response:" + response);

			postMethod.releaseConnection();
		}
		return response;
	}

	public static String httpGetRequest(GetMethod getMethod)
			throws ConnectTimeoutException, HttpException, IOException {
		String response = null;
		int status = 500;
		HttpClient client = getHttpClient();
		try {
			status = client.executeMethod(getMethod);
			logger.info(status + "--------状态------------");
			if (status != 200) {
				logger.warn("--------------http request Failed httpStatus"
						+ status + "-----------------");
				throw new Exception("http请求失败!");
			}
			if (status != 200)
				break label232;
			logger.info("-------------Http 通信成功-----------------");
			response = getMethod.getResponseBodyAsString();
			label232: logger.info("---------服务器返回值---------:" + response);
		} catch (Exception e) {
			logger.warn("-----------通信异常---------");

			logger.info("Http status=" + status + ",response:" + response);
			getMethod.releaseConnection();
		} finally {
			logger.info("Http status=" + status + ",response:" + response);
			getMethod.releaseConnection();
		}
		return response;
	}

	public static <T> T httpPostParam(String url, String interfaceName,
			String param) throws Exception {
		logger.info("===无卡支付-" + interfaceName + "==请求URL： " + url);
		String response = null;

		PostMethod postMethod = new PostMethod(url);
		postMethod.addParameter("param", param);
		response = httpPostRequest(postMethod);
		return response;
	}

	public static <T> T httpPostParam(String url, String interfaceName,
			NameValuePair[] param) throws Exception {
		logger.info("===无卡支付-" + interfaceName + "==请求URL： " + url);
		String response = null;

		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(param);
		response = httpPostRequest(postMethod);
		return response;
	}

	public static <T> T httpGetParam(String url, String interfaceName)
			throws Exception {
		logger.info("===无卡支付-" + interfaceName + "==请求URL： " + url);
		String response = null;

		GetMethod getMethod = new GetMethod(url);
		response = httpGetRequest(getMethod);
		return response;
	}

	public static String getRealIpAddr(HttpServletRequest request) {
		if (StringUtils
				.isEmpty(new String[] { request.getHeader("X-Real-IP") }))
			return request.getRemoteAddr();

		return request.getHeader("X-Real-IP");
	}
}
