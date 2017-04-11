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
public class MerchantVerify implements Serializable {
	private static final long serialVersionUID = 1732260130181888241L;
	private String custid;
	private String retcode;
	private String errmsg;
	private String mername;
	private String merstate;
	private String termid;
	private String sid;

	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
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

	public String getMername() {
		return this.mername;
	}

	public void setMername(String mername) {
		this.mername = mername;
	}

	public String getMerstate() {
		return this.merstate;
	}

	public void setMerstate(String merstate) {
		this.merstate = merstate;
	}

	public String getTermid() {
		return this.termid;
	}

	public void setTermid(String termid) {
		this.termid = termid;
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