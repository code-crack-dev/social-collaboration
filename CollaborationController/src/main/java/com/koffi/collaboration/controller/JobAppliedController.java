package com.koffi.collaboration.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;
import com.koffi.collaboration.service.JobAppliedService;

import util.Date_Time;

@RestController
public class JobAppliedController {
	
	Logger logger = LoggerFactory.getLogger(JobAppliedController.class);
	
	@Autowired
	private JobApplied jobApplied;
	
	@ Autowired
	JobAppliedService jobAppliedService;
	
	@Autowired
	HttpSession httpSession;
	
	@PostMapping("/applyJob")
	public ResponseEntity<JobApplied> applyJob(@RequestBody Job job)
	{
		logger.info("Apply Job initiated");
		
		jobApplied.setCompany(job.getCompany());
		Date_Time dt = new Date_Time();
		jobApplied.setApplied_date(dt.getDateTime());
		jobApplied.setLocation(job.getJob_location());
		jobApplied.setPosition(job.getPosition());
		jobApplied.setStatus('A');
		jobApplied.setJob_app_title(job.getJob_title());
		//jobApplied.setUsername(httpSession.getAttribute("username").toString());

		boolean value = jobAppliedService.applyNew(jobApplied);
		if(value)
		{
			logger.info("Job has been Applied for");
			jobApplied.setErrorCode("200");
			jobApplied.setErrorMessage("Job has been Applied");
		}
		else
		{
			logger.info("Apply job has got some error");
			jobApplied = new JobApplied();
			jobApplied.setErrorCode("400");
			jobApplied.setErrorMessage("Job has not been Added");
		}
		return new ResponseEntity<JobApplied> (jobApplied, HttpStatus.OK);
	}
	
	 
}
