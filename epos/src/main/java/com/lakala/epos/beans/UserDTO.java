/*
 * Copyright 2013-2014 the original author or authors.
 */

package com.lakala.epos.beans;

import com.lakala.ca.dto.AddressDTO;
import com.lakala.ca.dto.IdCardInfoDTO;
import com.lakala.ca.dto.PasswordSecurityLevel;
import com.lakala.ca.dto.PhoneNumDTO;
import com.lakala.core.dto.Gender;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * TODO 类描述
 * <p>
 * Created on 2017年4月6日
 * <p>
 * 
 * @author liuyinlong<liuyinlong@lakala.com>
 * @since 2017年4月6日
 */
@XmlType(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO implements Serializable {
	private static final long serialVersionUID = -5910557133050597598L;

	@XmlElement(name = "id")
	private long id;

	@Size(min = 6, max = 20)
	@XmlElement(name = "loginName")
	private String loginName;

	@Size(max = 64)
	@XmlElement(name = "password")
	private String password;

	@Size(max = 64)
	@XmlElement(name = "passwordSecurityLevel")
	private PasswordSecurityLevel passwordSecurityLevel;

	@XmlElement(name = "gender")
	private Gender gender;

	@XmlElement(name = "idCardInfo")
	private IdCardInfoDTO idCardInfo;

	@Size(max = 50)
	@XmlElement(name = "email")
	private String email;

	@Size(min = 11, max = 11)
	@XmlElement(name = "mobileNum")
	private String mobileNum;

	@XmlElement(name = "phoneNum")
	private PhoneNumDTO phoneNum;

	@XmlElement(name = "birthday")
	private Date birthday;

	@XmlElement(name = "address")
	private AddressDTO address;

	@Size(max = 20)
	@XmlElement(name = "realName")
	private String realName;

	@XmlElement(name = "enabled")
	private boolean enabled;

	@XmlElement(name = "createTime")
	private Date createTime;

	@XmlElement(name = "isIdCardIdAvailable")
	private boolean isIdCardIdAvailable;

	@XmlElement(name = "lastLoginDate")
	private Date lastLoginDate;

	@XmlElement(name = "lastLoginIp")
	private String lastLoginIp;

	@XmlElement(name = "exMobileNum")
	@Size(min = 11, max = 11)
	private String exMobileNum;

	@XmlElement(name = "exEmail")
	@Size(max = 50)
	private String exEmail;
	private String vipLevelCode;
	private String vipLevelName;

	@XmlElement(name = "customerType")
	private String customerType;

	public boolean isIdCardIdAvailable() {
		return this.isIdCardIdAvailable;
	}

	public void setIdCardIdAvailable(boolean isIdCardIdAvailable) {
		this.isIdCardIdAvailable = isIdCardIdAvailable;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public PhoneNumDTO getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(PhoneNumDTO phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public AddressDTO getAddress() {
		return this.address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordSecurityLevel getPasswordSecurityLevel() {
		return this.passwordSecurityLevel;
	}

	public void setPasswordSecurityLevel(
			PasswordSecurityLevel passwordSecurityLevel) {
		this.passwordSecurityLevel = passwordSecurityLevel;
	}

	public IdCardInfoDTO getIdCardInfo() {
		return this.idCardInfo;
	}

	public void setIdCardInfo(IdCardInfoDTO idCardInfo) {
		this.idCardInfo = idCardInfo;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public boolean getIdCardIdAvailable() {
		return this.isIdCardIdAvailable;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getExMobileNum() {
		return this.exMobileNum;
	}

	public void setExMobileNum(String exMobileNum) {
		this.exMobileNum = exMobileNum;
	}

	public String getExEmail() {
		return this.exEmail;
	}

	public void setExEmail(String exEmail) {
		this.exEmail = exEmail;
	}

	public boolean isEmailVerified() {
		return ((this.email == null) || (this.email.trim().length() == 0));
	}

	public boolean isMobileVerified() {
		return ((this.mobileNum == null) || (this.mobileNum.trim().length() == 0));
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getVipLevelCode() {
		return this.vipLevelCode;
	}

	public void setVipLevelCode(String vipLevelCode) {
		this.vipLevelCode = vipLevelCode;
	}

	public String getVipLevelName() {
		return this.vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}
}
