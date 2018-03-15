package com.niit.colloborativebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.niit.colloborativebackend.dao.JobDao;
import com.niit.colloborativebackend.model.Job;


@RestController
public class JobController {
	@Autowired
	JobDao jobDao;
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> listJobs() {
		List<Job>job = jobDao.getAllJobs();
		if(job.isEmpty()) {
			return new ResponseEntity<List<Job>>(job,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(job,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/job/", method = RequestMethod.POST)
	public ResponseEntity<Job> createBlog(@RequestBody Job job) {
		if(jobDao.getJobByJobId(job.getJobId()) == null) {
			jobDao.saveJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		job.setErrormessage("Job already exist with id : " +job.getJobId());
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
		
	
	@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
	public ResponseEntity<Job> getJob(@PathVariable("id") int id) {
		Job job = jobDao.getJobByJobId(id);
		if(job == null) {
			job = new Job();
			job.setErrormessage("No Job exist with id : " + id);
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}	
		
		
		
	}

