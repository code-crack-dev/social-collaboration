package com.koffi.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koffi.collaboration.domain.Forum;
import com.koffi.collaboration.service.ForumService;

import util.Date_Time;

@RestController
public class ForumController {
	
	private static final Logger logger = LoggerFactory.getLogger(ForumController.class);
	
	@Autowired
	private ForumService forumService;
	
	@Autowired 
	private Forum forum;
	
	@Autowired
	HttpSession httpSession;
	
	@PostMapping("/addForum")
	ResponseEntity<Forum> addForum(@RequestBody Forum forum)
	{
		if(httpSession.getAttribute("username")== null)
		{
			logger.info("You are not logged in");
			return null;
		}
		
		else
		{
			logger.info("Add to Forum");
			forum.setUsername(httpSession.getAttribute("username").toString());
			Date_Time date_Time = new Date_Time();
			//forum.setDate_time(date_Time.toStringDate(new Date()));
			forum.setStatus('P');
			boolean value = forumService.addForum(forum);
			if(value)
			{
				forum.setErrorCode("200");
				forum.setErrorMessage("Forum is added succefuly");
			}
			else
			{
				forum.setErrorCode("404");
				forum.setErrorMessage("Forum not added");
				logger.error("Add Forum error");
				return null;
			}
			logger.info("Add forum success");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping("/viewApprovedForums")
	public ResponseEntity<List<Forum>> getApprovedForums()
	{
		List<Forum> list = forumService.approvedForums();
		if(list.isEmpty() || list == null)
		{
			logger.error("No Approved Forums Found");
			return null;
		}
		else
		{
			logger.info("Forums have been retrieved");
			return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/getForum-{id}")
	public ResponseEntity<Forum> getForum(@PathVariable ("id") int id)
	{
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			return null;
		}
		else
		{
			forum = forumService.getForum(id);
			if(forum==null)
			{
				logger.error("Forum not found");
				return null;
			}
			else
			{
				logger.info("Forum has been found");
				return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			}
		}
	}
	
	
	
	@PostMapping("/updateForum")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum)
	{
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			return null;
		}
		else
		{
			logger.info("Updating Forum");
			boolean value = forumService.updateForum(forum);
			if(value)
			{
				forum.setErrorCode("200");
				forum.setErrorMessage("Forum updated");
			}
			else
			{
				forum.setErrorCode("404");
				forum.setErrorMessage("Forum not updated");
				logger.error("Updating Forum Error");
				return null;
			}
			logger.info("Update Forum Success");
			return new ResponseEntity<Forum>(forum, HttpStatus.OK);
		}	
	}
	
	
	/*@GetMapping("/viewMyForums")
	public ResponseEntity<List<Forum>> getMyForums()
	{
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			return null;
		}
		else
		{
			String username = session.getAttribute("username").toString();
			List<Forum> list = forumService.getUserForums(username);
			if(list.isEmpty() || list == null)
			{
				log.error("No Forums Found");
				return null;
			}
			else
			{
				log.info("Forums have been retrieved");
				return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
			}
		}
	}*/

	@GetMapping("/viewAllForums")
	public ResponseEntity<List<Forum>> getAllForums()
	{
		List<Forum> list = forumService.getForumList();
		if(list.isEmpty() || list == null)
		{
			logger.error("No Forums Found");
			return null;
		}
		else
		{
			logger.info("Forums list retrieved");
			return new ResponseEntity<List<Forum>>(list, HttpStatus.OK);
		}
	}
	
	

}
