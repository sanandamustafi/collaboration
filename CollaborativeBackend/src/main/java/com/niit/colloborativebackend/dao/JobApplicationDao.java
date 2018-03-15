package com.niit.colloborativebackend.dao;

import java.util.List;

import com.niit.colloborativebackend.model.JobApplication;

public interface JobApplicationDao {
	public List<JobApplication> getAllJobApplication();
	public boolean saveJobApplication(JobApplication jobapplication);
	public boolean updateJobApplication(JobApplication jobapplication);
	public JobApplication getJobApplicationByJobApplicationId(int jobapplicationId);
	public List<JobApplication> getjobapplicationsByJobId(int jobId);
	public List<JobApplication>jobApplicationByUserId(String userId);
	public boolean isJobExist(String userId,int jobId);

}
