package com.koffi.collaboration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.koffi.collaboration.domain.Friend;
import com.koffi.collaboration.domain.User;
import com.koffi.collaboration.service.FriendService;
import com.koffi.collaboration.service.UserService;

@RestController
public class FriendController {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	Friend friend;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession httpSession;
	
	private boolean isUserExist(String username)
	{
		if(userService.getByUsername(username)==null)
			return false;
		else
			return true;
	}
	
	@GetMapping("/sendRequest-{id}")
	public ResponseEntity<Friend> addFriend(@PathVariable("id") String friendID)
	{
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		
		boolean check = isUserExist(friendID);
		if(check)
		{
			logger.info("Adding Friend");
			User user = userService.getByUsername(httpSession.getAttribute("username").toString());
			User friendData = userService.getByUsername(friendID);
			friend.setUser_id(httpSession.getAttribute("username").toString());
			friend.setFriend_id(friendID);
			friend.setStatus('P');
			friend.setUser_f_name(user.getFirst_name());
			friend.setUser_l_name(user.getLast_name());
			friend.setFriend_f_name(friendData.getFirst_name());
			friend.setFriend_l_name(friendData.getLast_name());
			friend.setUserIsOnline(user.getIsOnline());
			friend.setFriendisOnline(friendData.getIsOnline());
			boolean value = friendService.save(friend); // Adding friend here
			if(value == true)
			{
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request sent");
				logger.info("Friend Request sent");
			}
			else
			{
				friend.setErrorCode("400");
				friend.setErrorMessage("Friend Request not sent");
				logger.error("Friend Requset not sent");
			}
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		
		else
		{
			logger.info("FriendID not in database");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Friend Not Found");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}
	
	@GetMapping("/myFriends")
	public ResponseEntity<List<Friend>> getMyFriends()
	{
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			return null;
		}
		else
		{
			List<Friend> list = friendService.getFriendList(httpSession.getAttribute("username").toString());
			logger.info("List retrieved");
			if(list.isEmpty() || list == null)
			{
				logger.info("List is Empty");
				return null;
			}
			else
				return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping("/acceptRequest-{id}")
	public ResponseEntity<Friend> acceptFriend(@PathVariable("id") String friendID)
	{
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			logger.info("Accepting Request");
			String userID = httpSession.getAttribute("username").toString();
			boolean value = friendService.accept(userID, friendID);
			if(value)
			{
				friend.setUser_id(userID);
				friend.setFriend_id(friendID);
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Accepted");
				logger.info("Request Accepted");
			}
			else
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Friend Request Not Accepted");				
				logger.error("Request not accepted");
			}
			return new ResponseEntity<Friend> (friend, HttpStatus.OK);
		}
	}
	
	@GetMapping("/rejectFriend-{id}")
	public ResponseEntity<Friend> rejectFriend(@PathVariable("id") String friendID)
	{
		friend = new Friend();
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			try
			{
				String userID = httpSession.getAttribute("username").toString();
				friend.setUser_id(userID);
				friend.setFriend_id(friendID);
				friendService.reject(userID, friendID);
				logger.info("Friend Request Rejected");
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Rejected Success");
			}	catch(Exception ex)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Could not reject Request");
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@GetMapping("/cancelRequest-{id}")
	public ResponseEntity<Friend> cancelRequest(@PathVariable("id") String friendID)
	{
		friend = new Friend();
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			try
			{
				String userID = httpSession.getAttribute("username").toString();
				friend.setUser_id(userID);
				friend.setFriend_id(friendID);
				friendService.cancel(userID, friendID);
				logger.info("Friend Request Cancelled");
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Cancelled Success");
			}	catch(Exception ex)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Could not cancel Request");
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@GetMapping("/viewPendingRequest")
	public ResponseEntity<List<Friend>> getPendingRequest()
	{
		System.out.println("enter in  view request............");
		List<Friend> list = new ArrayList<Friend>();
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			System.out.println("friend nullllllllllllllllllllllll");
			return null;
		}
		else
		{
			System.out.println("friend frist");
			logger.info("Getting Pending Request for "+httpSession.getAttribute("username").toString());
			String userID = httpSession.getAttribute("username").toString();
			list = friendService.showPendingRequests(userID);
			logger.info("Pending Requests recieved");
			if(list.isEmpty() || list==null)
			{
				friend.setUser_id(null);
				friend.setErrorMessage("200");
				friend.setErrorMessage("You have no Pending Request");
				return null;
			}
			System.out.println("else.............................");
			
		}
		return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
	}

	@GetMapping("/viewSentRequest")
	public ResponseEntity<List<Friend>> getSentRequests()
	{
		List<Friend> list = new ArrayList<Friend>();
		friend = new Friend();
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			return null;
		}
		else
		{
			logger.info("Getting Sent Request for "+httpSession.getAttribute("username").toString());
			String userID = httpSession.getAttribute("username").toString();
			list = friendService.viewSentRequests(userID);
			logger.info("Sent Requests recieved");
			if(list.isEmpty() || list==null)
			{
				friend.setUser_id(userID);
				friend.setErrorMessage("200");
				friend.setErrorMessage("You have no Pending Request");
				return null;
			}
		}
		return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/removeFriend-{id}")
	public ResponseEntity<Friend> removeFriend(@PathVariable("id") String friendID)
	{
		logger.info("Entering remove friend");
		if(httpSession.getAttribute("username")==null)
		{
			logger.info("NOT LOGGED IN");
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			return null;
		}	
		else
		{
			String userID = httpSession.getAttribute("username").toString();
			logger.info("Removing friend "+userID+" and "+friendID);
			boolean value = friendService.removeFriend(userID, friendID);
			if(value)
			{
				friend = new Friend();
				friend.setErrorMessage("200");
				friend.setErrorMessage("Friend has been removed");
				logger.info("Success Removing Friend");
				return new ResponseEntity<Friend>(friend, HttpStatus.OK);
			}
		}
		return null;
	}

}
