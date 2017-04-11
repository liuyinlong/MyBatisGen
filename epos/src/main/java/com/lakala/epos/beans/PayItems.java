/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import com.lakala.epos.util.ReflectUtil;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
@XmlRootElement(name = "body")
public class PayItems implements Serializable {
	private static final long serialVersionUID = -5857353349044897454L;
	private String mobile;
	private String custid;
	private String mercid;
	private String instcode;
	private String retcode;
	private String errmsg;
	private String sid;
	private String paytypelst;

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getMercid() {
		return this.mercid;
	}

	public void setMercid(String mercid) {
		this.mercid = mercid;
	}

	public String getInstcode() {
		return this.instcode;
	}

	public void setInstcode(String instcode) {
		this.instcode = instcode;
	}

	public String getRetcode() {
		return this.retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getErrmsg() {
		return this.errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getPaytypelst() {
		return this.paytypelst;
	}

	public void setPaytypelst(String paytypelst) {
		this.paytypelst = paytypelst;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}
}