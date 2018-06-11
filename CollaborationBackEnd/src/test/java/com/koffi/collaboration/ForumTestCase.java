package com.koffi.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.ForumDAO;
import com.koffi.collaboration.domain.Forum;

public class ForumTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Forum forum;
	
	@Autowired
	private static ForumDAO forumDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.koffi");
		context.refresh();
		
		forumDAO =(ForumDAO) context.getBean("forumDAO");
		forum = (Forum)context.getBean("forum");
	}

	@Test
	public void saveForumTestCase() {
		Forum forum = new Forum();
		forum.setDate_time("05-05-2018");
		forum.setContent("I would like to learn java in NIIT");
		forum.setForum_id("200");
		//forum.setId(100);
		forum.setRejected("Yes");
		forum.setStatus('A');
		forum.setUsername("Anil");
		
		Boolean status = forumDAO.addForum(forum); 
		assertEquals("Save forum",true, status);
	}

}
