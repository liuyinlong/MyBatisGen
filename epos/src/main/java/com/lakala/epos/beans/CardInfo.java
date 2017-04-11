/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import com.lakala.epos.util.ReflectUtil;
import java.io.Serializable;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
public class CardInfo implements Serializable {
	private String pan;
	private String acctname;
	private String certno;
	private String cvn2;
	private String vadate;
	private String lklagreno;
	private String agstat;
	private String hasPwd;
	private String hasMess;
	private String bankname;
	private String bankImg;
	private String bankcode;
	private String custid;

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

	public String getHasPwd() {
		return this.hasPwd;
	}

	public void setHasPwd(String hasPwd) {
		this.hasPwd = hasPwd;
	}

	public String getHasMess() {
		return this.hasMess;
	}

	public void setHasMess(String hasMess) {
		this.hasMess = hasMess;
	}

	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankImg() {
		return this.bankImg;
	}

	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}
}