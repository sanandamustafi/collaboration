package com.niit.colloborativebackend.model;

import java.io.Serializable;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class BaseDomain implements Serializable {
	@Transient
	public String errorcode;
	public String errormessage;
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

}
