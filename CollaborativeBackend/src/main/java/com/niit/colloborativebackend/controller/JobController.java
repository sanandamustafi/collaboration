package com.niit.colloborativebackend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.niit.colloborativebackend.dao.JobDao;
import com.niit.colloborativebackend.model.Job;
import com.niit.colloborativebackend.model.JobApplication;
import com.niit.colloborativebackend.model.UserDetails;


@RestController
public class JobController {
	@Autowired
	JobDao jobDao;
	@GetMapping(value = "/jobs")
	public ResponseEntity<List<Job>> listJobs() {
		
		List<Job> job = jobDao.list();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	@GetMapping(value = "/jobApplications")
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		
		
		List<JobApplication> jobApplications = jobDao.listJobApplications();
		
		
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "/job/")
	public ResponseEntity<Job> saveJob(@RequestBody Job job, HttpSession session) {
		
		
		{
			
			
			job.setJobDate(new Date());
			job.setStatus("v");
			jobDao.save(job);
			
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			
		}		
	}
	
	
	@PutMapping(value = "/updateJob/{id}")   // in URL we give/updateJob/1
	public ResponseEntity<Job> updateJob(@PathVariable("id") int id, @RequestBody Job job) {
		
		if (jobDao.get(id) == null) {
			job = new Job();
			job.setErrormessage("No job exist with id : " + job.getjobId());
			
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.update(job);
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
					
	 
	@DeleteMapping(value = "/deleteJob/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable("id") int id) {
		
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrormessage("No job exist with id : " + id);
			
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		jobDao.delete(job);
		
		return new ResponseEntity<Job>(HttpStatus.OK);		
	}
	@GetMapping(value = "/getJob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		
		Job job = jobDao.get(id);
		if(job == null) {
			job = new Job();
			job.setErrormessage("No job exist with id : " + id);
			
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getMyAppliedJobs")
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		
		UserDetails loggedInUser = (UserDetails) httpSession.getAttribute("loggedInUser");
		String loggedInUserId = loggedInUser.getUserId();
		
		@SuppressWarnings("unchecked")
		List<Job> jobs = (List<Job>) jobDao.getMyAppliedJobs(loggedInUserId);
		
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	
	@PutMapping(value = "/callForInterview/{userId}/{jobId}")
	public ResponseEntity<Job> callForInterview(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("C");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			job.setErrorcode("404");
			job.setErrormessage("Not able to change job application status 'call for interview'...");
			
		}
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/rejectJobApplication/{userId}/{jobId}")
	public ResponseEntity<Job> rejectJobApplication(@PathVariable("userId") String userId,
			@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication) {
		
		jobApplication = jobDao.get(userId, jobId);
		jobApplication.setStatus("R");
		Job job = new Job();
		if (jobDao.updateJobApplication(jobApplication) == false) {
			
			job.setErrorcode("404");
			job.setErrormessage("Not able to reject the application...");
			
		}
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
		
	}
	/**
	 * http://localhost:8081/Binder/listVacantJobs //working
	 * 
	 * @return
	 */
	@GetMapping(value = "/listVacantJobs")
	public ResponseEntity<List<Job>> listVacantJobs() {
		
		List<Job> vacantJobs = jobDao.listVacantJobs();
		if (vacantJobs.isEmpty()) {
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Job>>(vacantJobs, HttpStatus.OK);
	}

	
	@PostMapping(value = "/jobApplied")
	public ResponseEntity<Job> applyForJob(@RequestBody Job job, HttpSession httpSession) {		
		
		JobApplication jobApplication = new JobApplication();
		UserDetails loggedInUser = (UserDetails) httpSession.getAttribute("loggedInUser");
		jobApplication.setUserid(loggedInUser.getUserId());
		jobApplication.setJobid(job.getjobId());
		jobApplication.setStatus("A"); // A = Applied ||R = Rejected ||C = Call for Interview 

		jobDao.applyForJob(jobApplication);

		
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
}


		
	

