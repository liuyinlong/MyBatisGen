/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import com.lakala.epos.util.ReflectUtil;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class Row {
	private String pan;
	private String acctname;
	private String bkmobile;
	private String certtype;
	private String certno;
	private String cvn2;
	private String vadate;
	private String lklagreno;
	private String agstat;
	private String bankname;
	private String bankcode;
	private String accttype;

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAcctname() {
		return this.acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

	public String getBkmobile() {
		return this.bkmobile;
	}

	public void setBkmobile(String bkmobile) {
		this.bkmobile = bkmobile;
	}

	public String getCerttype() {
		return this.certtype;
	}

	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}

	public String getCertno() {
		return this.certno;
	}

	public void setCertno(String certno) {
		this.certno = certno;
	}

	public String getCvn2() {
		return this.cvn2;
	}

	public void setCvn2(String cvn2) {
		this.cvn2 = cvn2;
	}

	public String getVadate() {
		return this.vadate;
	}

	public void setVadate(String vadate) {
		this.vadate = vadate;
	}

	public String getLklagreno() {
		return this.lklagreno;
	}

	public void setLklagreno(String lklagreno) {
		this.lklagreno = lklagreno;
	}

	public String getAgstat() {
		return this.agstat;
	}

	public void setAgstat(String agstat) {
		this.agstat = agstat;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getAccttype() {
		return this.accttype;
	}

	public void setAccttype(String accttype) {
		this.accttype = accttype;
	}

	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}
}
