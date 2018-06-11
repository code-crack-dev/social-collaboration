package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.JobApplied;

public interface JobAppliedService {

	public boolean applyNew(JobApplied jobApplied);

	public List<JobApplied> listByUser(String username);

	public List<JobApplied> listByCompany();

}
