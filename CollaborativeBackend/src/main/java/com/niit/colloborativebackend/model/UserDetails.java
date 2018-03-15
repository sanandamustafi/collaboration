package com.niit.colloborativebackend.model;

import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Component
@Entity
public class UserDetails extends BaseDomain implements Serializable {
	
	@Id
	
	private String userId;
	private String userPassword;
	private String userName;
	private String userRole;
	private String userGender;
	private String userEmail;
	private String userContactNo;
	private String userAddress;
	private String userDOB;
	private String userStatus;
	private String userIsOnline;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserContactno() {
		return userContactNo;
	}
	public void setUserContactno(String userContactNo) {
		this.userContactNo = userContactNo;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserIsOnline() {
		return userIsOnline;
	}
	public void setUserIsOnline(String userIsOnline) {
		this.userIsOnline = userIsOnline;
	}
	

}
