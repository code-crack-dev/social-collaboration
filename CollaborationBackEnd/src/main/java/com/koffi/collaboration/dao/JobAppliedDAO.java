package com.koffi.collaboration.dao;

import java.util.List;

import com.koffi.collaboration.domain.JobApplied;

public interface JobAppliedDAO {

public boolean applyNew(JobApplied jobApplied);
	
	public List<JobApplied> listByUser(String username);
	
	public List<JobApplied> listByCompany();
}
