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


import com.niit.colloborativebackend.dao.JobApplicationDao;
import com.niit.colloborativebackend.model.JobApplication;



@RestController
public class JobApplicationController {
	@Autowired
	JobApplicationDao jobapplicationDao;
	@RequestMapping(value = "/jobapplication", method = RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> listJobApplications() {
		List<JobApplication> jobapplication = jobapplicationDao.getAllJobApplication();
		if(jobapplication.isEmpty()) {
			return new ResponseEntity<List<JobApplication>>(jobapplication,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<JobApplication>>(jobapplication,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jobapplication/", method = RequestMethod.POST)
	public ResponseEntity<JobApplication> createBlog(@RequestBody JobApplication jobapplication) {
		if(jobapplicationDao.getJobApplicationByJobApplicationId(jobapplication.getJobApplicationId()) == null) {
			jobapplicationDao.saveJobApplication(jobapplication);
			return new ResponseEntity<JobApplication>(jobapplication,HttpStatus.OK);
		}
		jobapplication.setErrormessage("JobApplication already exist with id : " +jobapplication.getJobApplicationId());
		return new ResponseEntity<JobApplication>(jobapplication,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jobapplication/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> updateJobApplication(@PathVariable("id") int id, @RequestBody JobApplication jobapplication) {
		if(jobapplicationDao.getJobApplicationByJobApplicationId(id) == null) {
			jobapplication = new JobApplication();
			jobapplication.setErrormessage("No JobApplication exist with id : " +jobapplication.getJobApplicationId());
			return new ResponseEntity<JobApplication>(jobapplication, HttpStatus.NOT_FOUND);
		}
		jobapplicationDao.updateJobApplication(jobapplication);
		return new ResponseEntity<JobApplication>(jobapplication, HttpStatus.OK);
	}
		
	
	@RequestMapping(value = "/jobapplication/{id}", method = RequestMethod.GET)
	public ResponseEntity<JobApplication> getJobApplication(@PathVariable("id") int id) {
		JobApplication jobapplication = jobapplicationDao.getJobApplicationByJobApplicationId(id);
		if(jobapplication == null) {
			jobapplication = new JobApplication();
			jobapplication.setErrormessage("No jobapplication exist with id : " + id);
			return new ResponseEntity<JobApplication>(jobapplication, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<JobApplication>(jobapplication, HttpStatus.OK);
	}	
		

	
		
	}


