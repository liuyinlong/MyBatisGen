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
public class RetCode {
	private String succeposs;
	private String msg;
	private String mobile;
	private String prov;
	private String prov_name;
	private String city;
	private String city_name;
	private String area;
	private String area_name;
	private String pm_term_type;
	private String bankno;
	private String contact_name;

	public String getSucceposs() {
		return this.succeposs;
	}

	public void setSucceposs(String succeposs) {
		this.succeposs = succeposs;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProv() {
		return this.prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPm_term_type() {
		return this.pm_term_type;
	}

	public void setPm_term_type(String pm_term_type) {
		this.pm_term_type = pm_term_type;
	}

	public String getProv_name() {
		return this.prov_name;
	}

	public void setProv_name(String prov_name) {
		this.prov_name = prov_name;
	}

	public String getCity_name() {
		return this.city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getArea_name() {
		return this.area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}

	public String getBankno() {
		return this.bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getContact_name() {
		return this.contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
}
