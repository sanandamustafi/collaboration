package com.niit.colloborativebackend.dao;

import java.util.List;

import com.niit.colloborativebackend.model.Job;

public interface JobDao {
	public boolean saveJob(Job job);
	public Job getJobByJobId(int jobId);
	public List<Job>getAllJobs();
	

}
