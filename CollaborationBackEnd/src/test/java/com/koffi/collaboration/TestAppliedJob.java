package com.koffi.collaboration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.config.HibernateConfig;
import com.koffi.collaboration.dao.JobAppliedDAO;
import com.koffi.collaboration.domain.JobApplied;

public class TestAppliedJob {

	public static void main(String [] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		JobAppliedDAO jobAppliedDAO = (JobAppliedDAO)context.getBean("jobAppliedDAO");
		JobApplied jobApplied = new JobApplied();
		jobApplied.setCompany("InfoSys");
		//jobApplied.setDate("22/04/2018");
		jobApplied.setLocation("NIIT");
		jobApplied.setPosition("Manager");
		jobApplied.setStatus('N');
		jobApplied.setJob_app_title("Manager");
		jobApplied.setUsername("Djaitai");
		jobAppliedDAO.applyNew(jobApplied);
	}

}
