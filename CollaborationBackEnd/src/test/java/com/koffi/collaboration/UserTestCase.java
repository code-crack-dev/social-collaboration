package com.koffi.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	//@Autowired
	//private static User user;
	
	@Autowired
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.koffi");
		context.refresh();
		
		userDAO =(UserDAO) context.getBean("userDAO");
		//user = (User)context.getBean("user");
	}

	@Test
	public void saveUserTestCase() {
		User user = new User();
		user.setDateReg(new Date());
		//user.setDOB("10/03/2000");
		user.setFirst_name("Mark");
		user.setGender("male");
		user.setIsOnline('Y');
		user.setLast_name("A.");
		user.setLast_seen("20/04/2018");
		user.setMail_id("kvvk@gmail.com");
		user.setPassword("111");
		user.setRole("Manager");
		user.setStatus('Y');
		user.setUsername("hvvhh");
		Boolean status = userDAO.saveUser(user); 
		assertEquals("Save user",true, status);
	}

}
