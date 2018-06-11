package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.Friend;

public interface FriendService {
	
	public List<Friend> getFriendList(String user_name);

	public boolean getFriend(String user_id, String friend_id);
	
	public boolean save(Friend friend);
	public boolean accept(String user_id, String friend_id);
	
	public boolean reject(String user_id, String friend_id);
	public boolean cancel(String user_id, String friend_id);
	public boolean removeFriend(String user_id, String friend_id);
	
	public List<Friend> showPendingRequests(String user_id);
	public List<Friend> viewSentRequests(String username);
	
	public boolean setUsersOnline(String user_name);
	public boolean setUsersOffline(String user_name);

}
