package com.koffi.collaboration;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.config.HibernateConfig;
import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.User;

public class TestUser {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");

		User user = new User();
		user.setUsername("Djaitai1");
		user.setFirst_name("Djaitai");
		user.setGender("male");
		user.setIsOnline('N');
		user.setLast_name("Claude");
		user.setMail_id("djaitai11225@gmail.com");
		user.setPassword("123");
		user.setRole("admin");
		user.setStatus('N');
		user.setDateReg(new Date());
		//user.setDOB("05/10/1995");
		user.setLast_seen("22/04/2018");
		userDAO.saveUser(user);
		System.out.println("DOEM");

	}
}
