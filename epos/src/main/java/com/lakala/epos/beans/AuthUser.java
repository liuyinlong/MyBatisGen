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
public class AuthUser implements Serializable {
	private static final long serialVersionUID = -2864100396208991585L;
	private String custid;
	private String retcode;
	private String errmsg;
	private String custname;
	private String custstate;
	private String isidentified;
	private String ispaypw;
	private String paypwhold;
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

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCuststate() {
		return this.custstate;
	}

	public void setCuststate(String custstate) {
		this.custstate = custstate;
	}

	public String getIsidentified() {
		return this.isidentified;
	}

	public void setIsidentified(String isidentified) {
		this.isidentified = isidentified;
	}

	public String getIspaypw() {
		return this.ispaypw;
	}

	public void setIspaypw(String ispaypw) {
		this.ispaypw = ispaypw;
	}

	public String getPaypwhold() {
		return this.paypwhold;
	}

	public void setPaypwhold(String paypwhold) {
		this.paypwhold = paypwhold;
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
