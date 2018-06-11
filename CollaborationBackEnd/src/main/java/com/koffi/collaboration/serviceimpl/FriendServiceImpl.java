package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.FriendDAO;
import com.koffi.collaboration.domain.Friend;
import com.koffi.collaboration.service.FriendService;

@Service
@Transactional
public class FriendServiceImpl implements FriendService{
	
	@Autowired
	FriendDAO friendDAO;
	
	public List<Friend> getFriendList(String username) {
		return friendDAO.getFriendList(username);
	}

	public boolean getFriend(String user_id, String friend_id) {
		return friendDAO.getUserFriend(user_id, friend_id);
	}

	public boolean save(Friend friend) {
		return friendDAO.save(friend);
	}

	public boolean accept(String user_id, String friend_id) {
		return friendDAO.accept(user_id, friend_id);
	}

	public boolean reject(String user_id, String friend_id) {
		// TODO Auto-generated method stub
		return friendDAO.reject(user_id, friend_id);
	}

	public boolean cancel(String user_id, String friend_id) {
		return friendDAO.cancel(user_id, friend_id);
	}

	public boolean removeFriend(String user_id, String friend_id) {
		return friendDAO.removeFriend(user_id, friend_id);
	}

	public List<Friend> showPendingRequests(String user_id) {
		return friendDAO.showPendingRequests(user_id);
	}

	public List<Friend> viewSentRequests(String username) {
		return friendDAO.viewSentRequests(username);
	}

	public boolean setUsersOnline(String username) {
		return friendDAO.setUsersOnline(username);
	}

	public boolean setUsersOffline(String username) {
		return friendDAO.setUsersOffline(username);
	}

}
