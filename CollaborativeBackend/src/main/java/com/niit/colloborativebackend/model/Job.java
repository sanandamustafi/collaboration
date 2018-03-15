package com.niit.colloborativebackend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;
@Component
@Entity
public class Job extends BaseDomain implements Serializable  {
	@Id
	@SequenceGenerator(name="SEQ_JOB", sequenceName="SEQ_JOB_AUTO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_JOB")
	private int jobId;
	private String jobProfile;
	private String jobQualification;
	private String jobstatus;
	private Date jobpostdate;
	private String jobDescription;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobProfile() {
		return jobProfile;
	}
	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}
	public String getJobQualification() {
		return jobQualification;
	}
	public void setJobQualification(String jobQualification) {
		this.jobQualification = jobQualification;
	}
	public String getJobstatus() {
		return jobstatus;
	}
	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
	}
	public Date getJobpostdate() {
		return jobpostdate;
	}
	public void setJobpostDate(Date jobpostdate) {
		this.jobpostdate = jobpostdate;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
