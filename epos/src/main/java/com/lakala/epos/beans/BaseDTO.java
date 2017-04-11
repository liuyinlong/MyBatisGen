/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import com.lakala.epos.util.JaxbMapper;
import com.lakala.epos.util.ReflectUtil;
import java.io.PrintStream;
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
public class BaseDTO implements Serializable {
	private static final long serialVersionUID = 8260652621055017790L;
	private String mobile;
	private String custid;
	private String chncode;
	private String mercid;
	private String instcode;
	private String retcode;
	private String errmsg;
	private List<Row> rows;

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

	public String toString() {
		return ReflectUtil.fieldsToString(this);
	}

	public static void main(String[] args) {
		BaseDTO user = (BaseDTO) JaxbMapper
				.fromXml(
						"<body><mobile>18600012032</mobile><custid>LMUSTEST0002</custid><chncode>03</chncode><mercid>LMUSTEST0001</mercid><instcode>000000000002</instcode><retcode>00</retcode><errmsg>成功</errmsg><rows><row><pan>6228480010372989714</pan><acctname>唐希元</acctname><bkmobile>18600012032</bkmobile><certtype>01</certtype><certno>513822108201017337</certno><cvn2>888</cvn2><vadate>2014-10-11</vadate><lklagreno>LKLQPAYAGREE0002</lklagreno><agstat>1</agstat></row><row><pan>6225684221000195117</pan><acctname>唐希元</acctname><bkmobile>18600012032</bkmobile><certtype>01</certtype><certno>513822108201017337</certno><cvn2>442</cvn2><vadate>2014-10-11</vadate><lklagreno>LKLQPAYAGREE0001</lklagreno><agstat>1</agstat></row></rows></body>",
						BaseDTO.class);

		System.out.println(user.getCustid() + " === " + user.toString());
	}
}