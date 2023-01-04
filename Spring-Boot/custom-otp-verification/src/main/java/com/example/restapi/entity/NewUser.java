package com.example.restapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="newuser")
public class NewUser {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
	private int Id;
	@Column
	private String name;
	@Column
	private String mail;
	public String getAccunt_sta() {
		return accunt_sta;
	}
	public void setAccunt_sta(String accunt_sta) {
		this.accunt_sta = accunt_sta;
	}
	@Column
	private Long mobile;
	@Column
	private String mobileVerifyStatus="UnVerified";
	@Column
	private String mailVerifyStatus="Unverified"; 
	@Column(name="active_sta")
	private String accunt_sta="Intial";
	
	public String getMobileVerifyStatus() {
		return mobileVerifyStatus;
	}
	public void setMobileVerifyStatus(String mobileVerifyStatus) {
		this.mobileVerifyStatus = mobileVerifyStatus;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getMailVerifyStatus() {
		return mailVerifyStatus;
	}
	public void setMailVerifyStatus(String mailVerifyStatus) {
		this.mailVerifyStatus = mailVerifyStatus;
	}
	public NewUser(String name, String mail, Long mobile) {
		super();
		this.name = name;
		this.mail = mail;
		this.mobile = mobile;
	}
	public NewUser() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public NewUser orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
