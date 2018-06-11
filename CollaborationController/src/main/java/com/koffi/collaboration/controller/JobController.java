package com.koffi.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.User;
import com.koffi.collaboration.service.JobService;


@RestController
public class JobController {
	
	Logger logger = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobService jobService;
	
	 @Autowired
	 Job job;
	 
	 @Autowired
	 HttpSession httpSession;
	 
	 @PostMapping("/addJob") //DONE
	 public ResponseEntity<Job> addJob(@RequestBody Job job)
	 {
		job.setJob_status('P');
		//Date_Time date_Time = new Date_Time();
		//job.setDate_of_post(date_Time.getDateTime());
		//job.setUsername(httpSession.getAttribute("username").toString());
		 boolean value = jobService.addJob(job);
		 if(value == true)
			{
				job.setErrorCode("200");
				job.setErrorMessage("Job added Successfully");
			}
			else
			{
				job.setErrorCode("404");
				job.setErrorMessage("Job has not been added");
			}
		 return new ResponseEntity<Job>(job, HttpStatus.OK);
	 }
	 
	 
	 @PutMapping("/updateJob")//DONE
		public ResponseEntity<Job> updateJob(@RequestBody Job job)
		{
			if(job != null)
			{
				boolean value = jobService.updateJob(job);
				if (value == true) 
				{
					job.setErrorCode("200");
					job.setErrorMessage("Job updated Successfully");
				} 
				else 
				{
					job.setErrorCode("100");
					job.setErrorMessage("Job User Failed");
					return null;
				}
			}
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
	 
	 @GetMapping("/getJob") //see later
	 public ResponseEntity<Job> getJob(@RequestBody Job job)
	 {
		 job = jobService.getJob(job.getJob_title());
		 job.setErrorCode("200");
		 job.setErrorMessage("Job is retrived");
		 return new ResponseEntity<Job>(job, HttpStatus.OK);
	 }
	 
	 //Get all jobs
	@GetMapping("/getAllJobs") //DONE
	public ResponseEntity<List<Job>> getJobList()
	{
		List<Job> jobs = jobService.listJobs();
		job.setErrorCode("200");
		job.setErrorMessage("Job successfully retrieved");
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	@GetMapping("/getJobByID-{job_id}") //DONE
	public ResponseEntity<Job> getJobListByID(@PathVariable("job_id") int job_id )
	{
		job = jobService.getJob(job_id);
		if (job == null) {
			job= new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Job " + job_id + " is not found.");
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		job.setErrorCode("200");
		job.setErrorMessage("Job " + job_id + " is found.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	/*@GetMapping("/getJobByStatus-{job_status}") //DONE
	public ResponseEntity<Job> getJobStatus(@PathVariable("job_status") char job_status) {
		job = jobService.listJob(job_status);

		if (job == null) {
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("User " + job_status + " is not found.");
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
		}
		job.setErrorCode("200");
		job.setErrorMessage("User " + job_status + " is found.");
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}*/
	
	
	@DeleteMapping("/deleteJob-{job_id}") //DONE
	public ResponseEntity<Job> deleteJob(@PathVariable ("job_id") int id)
	{
		boolean value = jobService.deleteJob(id);
		if(value==true)
		{
			job.setErrorCode("200");
			job.setErrorMessage("Job has been deleted");
		}
		else
		{
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Job has not been deleted");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	
	@GetMapping("/invalidate-{job_id}")
	public ResponseEntity<Job> invalidateJob(@PathVariable ("id") int job_id)
	{
		boolean value = jobService.invalidateJob(job_id);
		if(value==true)
		{
			job.setErrorCode("200");
			job.setErrorMessage("Job has been Invalidated");
		}
		else
		{
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Job has been Validated");
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}	

	 
}
