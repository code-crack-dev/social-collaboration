package com.koffi.collaboration;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.JobAppliedDAO;
import com.koffi.collaboration.domain.JobApplied;

public class JobAppliedTestCase {
	private static AnnotationConfigApplicationContext context ;
	@Autowired
	private static JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	private  static JobApplied jobApplied;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.koffi");
		context.refresh();
		jobAppliedDAO = (JobAppliedDAO)context.getBean("jobAppliedDAO");
		 jobApplied = (JobApplied)context.getBean("ja");
	}
	
	@Test
	public void   saveJobAppliedTestCase()
	{
		//JobApplied jobApplied = new JobApplied();
		jobApplied.setCompany("InfoSys");
		//jobApplied.setDate("22/04/2018");
		jobApplied.setLocation("NIIT");
		jobApplied.setPosition("Manager");
		jobApplied.setStatus('N');
		jobApplied.setJob_app_title("Manager");
		jobApplied.setUsername("Djaitai");
		Boolean status = jobAppliedDAO.applyNew(jobApplied);
		assertEquals("job applied",true,  status);
		
	}

}
