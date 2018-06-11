package com.koffi.collaboration.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.koffi.collaboration.domain.Blog;
import com.koffi.collaboration.service.BlogService;

import util.Date_Time;

@RestController
public class BlogController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private Blog blog;
    
    @Autowired
    BlogService blogService;
    
    @Autowired
    private HttpSession httpSession;
    
    @PostMapping("/addBlog")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog)
    {
    	logger.info("Blog received");
    	blog.setStatus(httpSession.getAttribute("unsername").toString());
    	Date_Time date_Time = new Date_Time();
    			String date = date_Time.getDateTime();
    	blog.setDate_time(date);
    	boolean value = blogService.addBlog(blog);
    	if(value == true)
    	{
    		logger.info("Blod added successfully");
    		blog.setErrorCode("100");
    		blog.setErrorMessage("Blog has been added");
    	}
    	else {
			logger.info("Blog has not been added");
			blog.setErrorCode("404");
			blog.setErrorMessage("Blogm is not added");
		}
    	
    	return new ResponseEntity<Blog>(blog , HttpStatus.OK);
    }
    
    
    
    
    
    
    

}
