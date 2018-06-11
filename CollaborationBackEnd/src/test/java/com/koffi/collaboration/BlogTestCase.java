package com.koffi.collaboration;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.BlogDAO;
import com.koffi.collaboration.domain.Blog;

public class BlogTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static Blog blog;
	
	@Autowired
	private static BlogDAO blogDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.koffi");
		context.refresh();
		
		blogDAO =(BlogDAO) context.getBean("blogDAO");
		//blog = (Blog)context.getBean("blog");
	}

	@Test
	public void saveBlogTestCase() {
		Blog blog = new Blog();
		blog.setB_like(0);
		blog.setBlog_description("This is my first blog");
		blog.setBlog_title("My blog");
		blog.setDate_time("11/06/2018");
		blog.setRemark("Your blog is rejected");
		blog.setRejected("Yes");
		blog.setStatus("R");
		blog.setUsername("Ben");
		blog.setUnlike(0);	
		Boolean status = blogDAO.addBlog(blog); 
		assertEquals("Save blog",true, status);
	}

}
