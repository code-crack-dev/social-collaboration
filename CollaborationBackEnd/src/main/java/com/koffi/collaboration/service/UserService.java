package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.User;

public interface UserService {
	
	boolean saveUser(User user);

	boolean updateUser(User user);
	
	boolean deleteUser(String username);
	
	public User getByUsername(String username);

	public User getByemailId(String emailId);

	public List<User> getAllUser();
	
	public User validateUser(String username, String password);

}
