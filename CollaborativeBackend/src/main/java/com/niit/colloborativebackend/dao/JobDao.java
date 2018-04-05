package com.niit.colloborativebackend.dao;

import java.util.List;

import com.niit.colloborativebackend.model.Job;
import com.niit.colloborativebackend.model.JobApplication;

public interface JobDao {
public boolean save(Job job);
public boolean update(Job job);
public boolean delete(Job job);
public Job get(int id);
public List<Job> list();
public List<Job> getMyAppliedJobs(String userid);

public JobApplication get(String userid, String jobid);

public boolean updateJobApplication(JobApplication jobApplication);

public boolean applyForJob(JobApplication jobApplication);

public List<JobApplication> listJobApplications();

public List<Job> listVacantJobs();


	

}
