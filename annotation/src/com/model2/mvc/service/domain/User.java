package com.model2.mvc.service.domain;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{

	/// Field
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String addr;
	private String email;
	private Date regDate;

	private String phone1;
	private String phone2;
	private String phone3;

	/// Constructor
	public User() {
	}

	/// Method
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		if (phone != null && phone != "") {
			String[] stra = phone.split("-");
			phone1 = stra[0];
			phone2 = stra[1];
			phone3 = stra[2];
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1
	 *            the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2
	 *            the phone2 to set
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return the phone3
	 */
	public String getPhone3() {
		return phone3;
	}

	/**
	 * @param phone3
	 *            the phone3 to set
	 */
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	@Override
	public String toString() {
		return "UserVO : [userId] " + userId + " [userName] " + userName + " [password] " + password + " [role] " + role
				+ " [ssn] " + ssn + " [phone] " + phone + " [email] " + email + " [regDate] " + regDate;
	}
}