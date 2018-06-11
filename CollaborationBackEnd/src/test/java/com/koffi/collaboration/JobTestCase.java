package com.koffi.collaboration;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.JobDAO;
import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;
import com.koffi.collaboration.serviceimpl.JobServiceImpl;

public class JobTestCase {
	
	private static AnnotationConfigApplicationContext context;
	@Autowired
	JobServiceImpl jobServiceImpl;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@Autowired
	private static Job job;
	
	
	@Autowired
	private static JobApplied jobApplied;
	
	@BeforeClass
	public static void init()
	{
context = new AnnotationConfigApplicationContext();
		
		context.scan("com.koffi");
		context.refresh();		
		jobDAO =(JobDAO) context.getBean("jobDAO");
		job = (Job)context.getBean("job");
	}
	
	@Test
	public void applyForAJobSuccessTestCase()
	{
		jobApplied.setUsername("Janine");
		jobApplied.setJob_id(104);
		Assert.assertEquals(true ,jobDAO.addJob(jobApplied));
	}
	
	@Test
	public void applyForAJobFailureTestCase()
	{
		jobApplied.setUsername("jaya@123");
		jobApplied.setJob_id(101);
		Assert.assertEquals(false ,jobServiceImpl.addJob(jobApplied));
	}
	
	@Test public void isJobAlreadyAppliedSuccessTestCase()
	{
		Assert.assertEquals(true ,jobServiceImpl.isJobAlreadyApplied("yogender@gmail.com", 103));
	}
	
	@Test public void isJobAlreadyAppliedFailureTestCase()
	{
		Assert.assertEquals(false ,jobServiceImpl.isJobAlreadyApplied("koffi@gmail.com", 102));
	}
	
}
