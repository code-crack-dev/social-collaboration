package com.koffi.collaboration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.koffi.collaboration.dao.JobDAO;
import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;
import com.koffi.collaboration.domain.User;
import com.koffi.collaboration.service.FriendService;
import com.koffi.collaboration.service.JobService;
import com.koffi.collaboration.service.UserService;

import util.Date_Time;

@RestController
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private User user;

	@Autowired
	private Job job;

	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobApplied jobApplied;

	@Autowired
	FriendService friendService;

	@Autowired
	HttpSession httpSession;

	// Insert user into database
	/*
	 * @RequestMapping(value = "/registerUser", method = RequestMethod.POST) public
	 * void save(@RequestBody User user) { userService.saveUser(user); }
	 */

	@PostMapping(value = "/addUser") // DONE
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setStatus('N');
		user.setIsOnline('N');

		// add more extra condition
		// if user already exist
		if (userService.getByUsername(user.getUsername()) != null) {
			user.setErrorCode("100");
			user.setErrorMessage("user already exist");
			return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
		} else {
			boolean value = userService.saveUser(user);
			if (value == true) {
				user.setErrorCode("200");
				user.setErrorMessage("User added Successfully");
			} else {
				user.setErrorCode("100");
				user.setErrorMessage("Add User Failed");
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	// Update user
	@PutMapping("/updateUser") // DONE
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		if (user != null) {
			boolean value = userService.saveUser(user);
			if (value == true) {
				user.setErrorCode("200");
				user.setErrorMessage("User updated Successfully");
			} else {
				user.setErrorCode("100");
				user.setErrorMessage("Add User Failed");
				return null;
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Get all the users
	@GetMapping("/getAllUserList") // DONE
	public ResponseEntity<List<User>> getUserList() throws NullPointerException {
		List<User> list = userService.getAllUser();
		if (list.isEmpty()) {
			user.setErrorCode("100");
			user.setErrorMessage("Users are not available");
		} else {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			for (User user : list) {
				user.setErrorCode("200");
				user.setErrorMessage("Success");
				// if(user.getDateReg() != null)
				// user.setDOB(dateFormat.format(user.getDOB()));
			}
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// Get user details by id (username)
	@GetMapping("/getUser-{username}") // DONE
	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		user = userService.getByUsername(username);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User " + username + " is not found.");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		user.setErrorCode("200");
		user.setErrorMessage("User " + username + " is found.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// delete user by user name
	@DeleteMapping("/deleteByUserName-{username}") // DONE
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
		user = userService.getByUsername(username);

		// check whether user exist or not
		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User " + username + " is not found.");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}

		// check whether user applied for any job or not
		if (!jobService.listJobApplied(username).isEmpty()) {
			user.setErrorMessage("We should not delete as " + username + " applied few jobs");
			return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);

		}

		if (user != null)
		// if(userService.deleteUser(username))
		{
			boolean value = userService.deleteUser(username);
			if (value == true) {
				user.setErrorCode("200");
				user.setErrorMessage("User deleted Successfully");
				// return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} else {
			user.setErrorCode("100");
			user.setErrorMessage("delete User Failed");
			// return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/login") // See later
	public ResponseEntity<User> validateUser(@RequestBody User user) {
		//System.out.println("Name : " + user.getUsername());
		//System.out.println("Password " + user.getPassword());
		User value = userService.validateUser(user.getUsername(), user.getPassword());
		//System.out.println(value.getStatus());
		//System.out.println("Status " + user.getStatus());
		//System.out.println(user.getPassword() + " " + user.getUsername() + "  ----- " + value.getPassword() + "  "
				//+ value.getUsername());
		if (user.getUsername().equals(value.getUsername()) == true) {
			if (user.getPassword().equals(value.getPassword()) == true) {
				//System.out.println("nulll.....AAAAAAAAAAAAAAAAAAAAAAAAA.......");

				user = userService.getByUsername(user.getUsername());
				user.setIsOnline('Y');
				Date_Time dt = new Date_Time();
				user.setLast_seen(dt.getDateTime());
				userService.saveUser(user);
				friendService.setUsersOnline(user.getUsername());
				httpSession.setAttribute("username", user.getUsername());
				httpSession.setAttribute("role", user.getRole());
				httpSession.setAttribute("isLoggedIn", "true");
				httpSession.setAttribute("status", user.getStatus());
				if (user.getDob() != null)
					user.setBirthDate(dt.toStringDate(user.getDob()));

				user.setErrorCode("200");
				user.setErrorMessage("Success");
				System.out.println("Name = " + httpSession.getAttribute("username").toString());
				System.out.println("Role = " + httpSession.getAttribute("role").toString());
				System.out.println(httpSession.getAttribute("status").toString());
			} else {
				// user = new User();
				value.setErrorCode("407");
				value.setErrorMessage("Wrong username or password.");
				System.out.println("nulll............");
			}
		} else {
			if (value.getStatus() == 'R') {
				System.out.println("nulll rrrrrrr............");
				// user = new User();
				value.setErrorCode("404");
				value.setErrorMessage("Registeration is rejected. Please Contact Admin");
			} else if (value.getStatus() == 'N') {
				System.out.println("nulll.....nnnnnnnnnnnnnnnnnnnnn.......");
				// user = new User();
				value.setErrorCode("404");
				value.setErrorMessage("Registeration approval is pending. Please try again later");
			} else {
				value.setErrorCode("408");
				value.setErrorMessage("Not a Registerd User");
				System.out.println("user null condition............");
			}
		}
		return new ResponseEntity<User>(value, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<User> logout() {
		log.info("isLoggedIN - " + httpSession.getAttribute("isLoggedIn"));
		if (httpSession.getAttribute("isLoggedIn") != null) {
			user = userService.getByUsername(httpSession.getAttribute("username").toString());
			user.setIsOnline('N');
			Date_Time dt = new Date_Time();
			user.setLast_seen(dt.getDateTime());
			userService.saveUser(user);
			friendService.setUsersOffline(httpSession.getAttribute("username").toString());
			user = new User();
			user.setErrorCode("200");
			user.setErrorMessage("You have logged out.");
			httpSession.invalidate();
		} else {
			user = new User();
			user.setErrorCode("500");
			user.setErrorMessage("You have not logged in");
			log.info(user.getErrorMessage());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
