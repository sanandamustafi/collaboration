package com.niit.colloborativebackend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;
@Component
@Entity
public class JobApplication extends BaseDomain implements Serializable {
	@Id
	@SequenceGenerator(name="SEQ_JOB_APPLICATION", sequenceName="SEQ_JOBAPPLICATION_AUTO_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_JOB_APPLICATION")
	private int jobApplicationId;
	private String userId;
	private int jobId;
	private String jobapplicationStatus;
	public int getJobApplicationId() {
		return jobApplicationId;
	}
	public void setJobApplicationId(int jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobapplicationStatus() {
		return jobapplicationStatus;
	}
	public void setJobapplicationStatus(String jobapplicationStatus) {
		this.jobapplicationStatus = jobapplicationStatus;
	}
}
