/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
public class SignUsers implements Serializable {
	private static final long serialVersionUID = 8030081824043406881L;
	private String mobile;
	private String custid;
	private String chncode;
	private String mercid;
	private String instcode;
	private String retcode;
	private String errmsg;
	private List<Row> rows;
	private String sid;

	@XmlElement(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@XmlElement(name = "custid")
	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	@XmlElement(name = "chncode")
	public String getChncode() {
		return this.chncode;
	}

	public void setChncode(String chncode) {
		this.chncode = chncode;
	}

	@XmlElement(name = "mercid")
	public String getMercid() {
		return this.mercid;
	}

	public void setMercid(String mercid) {
		this.mercid = mercid;
	}

	@XmlElement(name = "instcode")
	public String getInstcode() {
		return this.instcode;
	}

	public void setInstcode(String instcode) {
		this.instcode = instcode;
	}

	@XmlElement(name = "retcode")
	public String getRetcode() {
		return this.retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	@XmlElement(name = "errmsg")
	public String getErrmsg() {
		return this.errmsg;
	}

	@XmlElementWrapper(name = "rows")
	@XmlElement(name = "row")
	public List<Row> getRows() {
		return this.rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
