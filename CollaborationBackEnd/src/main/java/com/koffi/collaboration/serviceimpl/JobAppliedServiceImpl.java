package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koffi.collaboration.dao.JobAppliedDAO;
import com.koffi.collaboration.domain.JobApplied;
import com.koffi.collaboration.service.JobAppliedService;

@Service
public class JobAppliedServiceImpl implements JobAppliedService{
	
	@Autowired
	JobAppliedDAO jobAppliedDAO;
	
	public boolean applyNew(JobApplied jobApplied) {
		// TODO Auto-generated method stub
		return jobAppliedDAO.applyNew(jobApplied);
	}

	public List<JobApplied> listByUser(String username) {
		// TODO Auto-generated method stub
		return jobAppliedDAO.listByUser(username);
	}

	public List<JobApplied> listByCompany() {
		// TODO Auto-generated method stub
		return jobAppliedDAO.listByCompany();
	}


}
