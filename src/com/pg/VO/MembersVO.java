package com.pg.VO;

import java.sql.Date;

public class MembersVO {
	private String id;
	private String mName;
	private String email;
	private String pwd;
	private String phone;
	private String birth;
	private Date regDate;
	private String pCode;
	private String pNAddress;
	private String pOAddress;
	private String pDAddress;
	

	public MembersVO(){}


	public MembersVO(String id, String mName, String email, String pwd,
			String phone, String birth, String pCode,
			String pNAddress, String pOAddress, String pDAddress) {
		super();
		this.id = id;
		this.mName = mName;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.birth = birth;
		this.pCode = pCode;
		this.pNAddress = pNAddress;
		this.pOAddress = pOAddress;
		this.pDAddress = pDAddress;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public String getpCode() {
		return pCode;
	}


	public void setpCode(String pCode) {
		this.pCode = pCode;
	}


	public String getpNAddress() {
		return pNAddress;
	}


	public void setpNAddress(String pNAddress) {
		this.pNAddress = pNAddress;
	}


	public String getpOAddress() {
		return pOAddress;
	}


	public void setpOAddress(String pOAddress) {
		this.pOAddress = pOAddress;
	}


	public String getpDAddress() {
		return pDAddress;
	}


	public void setpDAddress(String pDAddress) {
		this.pDAddress = pDAddress;
	}

	
}

