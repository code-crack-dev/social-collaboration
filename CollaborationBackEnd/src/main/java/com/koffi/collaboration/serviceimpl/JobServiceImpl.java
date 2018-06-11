package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.JobDAO;
import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;
import com.koffi.collaboration.service.JobService;

@Service
@Transactional
//@Repository()
public class JobServiceImpl  implements JobService{
	
	@Autowired
	JobDAO jobDAO;
	public boolean addJob(Job job) {
		
		return jobDAO.addJob(job);
	}

	public Job getJob(String name) {

		return jobDAO.getJob(name);
	}

	public List<Job> listJobs() {

		return jobDAO.listJobs();
	}

	public boolean deleteJob(int job_id) {

		return jobDAO.deleteJob(job_id);
	}

	public boolean invalidateJob(int job_id) {

		return jobDAO.invalidateJob(job_id);
	}

	public boolean updateJob(Job job) {
		// TODO Auto-generated method stub
		return jobDAO.updateJob(job);
	}

	public Job getJob(int job_id) {
		// TODO Auto-generated method stub
		return jobDAO.getJob(job_id);
	}

	public List<Job> listJob(char status)
	 {
		// TODO Auto-generated method stub
		return jobDAO.listJob(status);
	}

	public boolean addJob(JobApplied jobApplied) {
		// TODO Auto-generated method stub
		return jobDAO.addJob(jobApplied);
	}

	public boolean updateJob(JobApplied jobApplied) {
		// TODO Auto-generated method stub
		return jobDAO.updateJob(jobApplied);
	}

	public List<JobApplied> jobAppliedList(int job_id) {
		// TODO Auto-generated method stub
		return jobDAO.jobAppliedList(job_id);
	}

	public List<JobApplied> jobAppliedList(int job_id, char status) {
		// TODO Auto-generated method stub
		return jobDAO.jobAppliedList(job_id, status);
	}

	public boolean isJobOpened(int job_id) {
		// TODO Auto-generated method stub
		return jobDAO.isJobOpened(job_id);
	}

	public boolean isJobAlreadyApplied(String username, int job_id) {
		// TODO Auto-generated method stub
		return jobDAO.isJobAlreadyApplied(username, job_id);
	}

	public List<JobApplied> listJobApplied(String username) {
		// TODO Auto-generated method stub
		return  jobDAO.listJobApplied(username);
	}
	
	

}
