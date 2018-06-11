package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.User;
import com.koffi.collaboration.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getAllUser() {

		return userDAO.getAllUser();
	}

	public User getByUsername(String username) {
		return userDAO.getByUsername(username);
	}
	
	public User getByemailId(String emailId){
		return userDAO.getByemailId(emailId);
	}

	public boolean saveUser(User user) {
		return userDAO.saveUser(user);
	}

	public boolean updateUser(User user) {
		 return userDAO.updateUser(user);
	}

	public User validateUser(String username, String password) {
		return userDAO.validateUser(username, password);
	}

	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}
}
