package com.koffi.collaboration.dao;

import java.util.List;

import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;

public interface JobDAO {
	
	//Create new job
	public boolean addJob(Job job);
		
	public boolean updateJob(Job job); 
	
	public Job getJob(String username);
	
	//admin will not delete the job
		//once the job is closed, admin will change
		//status - F/C		
		//fetch a particular job
	public Job getJob(int job_id);
	
	//get all job
	public List<Job> listJobs();
	
	//fetch job based on status
	public List<Job> listJob(char status);
	//public Job listJob(char status);
	
	//Apply for particular job
	public boolean addJob(JobApplied jobApplied);
	
	//Admin can reject/accept/call for interview
	public boolean updateJob(JobApplied jobApplied);
	
	//Admin wants to know list of user those applied for particular job
	public List<JobApplied> jobAppliedList(int job_id);
	
	//Admin wants to fetch all details of particular job based on job status
	public List<JobApplied> jobAppliedList(int job_id, char status);
	
	public boolean deleteJob(int job_id);
	
	public boolean invalidateJob(int job_id);
	
	public boolean isJobAlreadyApplied(String username, int job_id);
	
	public boolean isJobOpened(int job_id); 
	
	//Get All the job applied by a particular user
	public List<JobApplied> listJobApplied(String username);
}
