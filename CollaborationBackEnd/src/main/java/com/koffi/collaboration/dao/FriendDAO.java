package com.koffi.collaboration.dao;

import java.util.List;

import com.koffi.collaboration.domain.Friend;

public interface FriendDAO {
	
	public List<Friend> getFriendList(String username);

	public boolean getUserFriend(String user_id, String friend_id);
	
	public boolean save(Friend friend);
	
	public boolean accept(String user_id, String friend_id);
	
	public boolean reject(String user_id, String friend_id);
	
	public boolean cancel(String user_id, String friend_id);
	
	public boolean removeFriend(String user_id, String friend_id);
	
	public List<Friend> showPendingRequests(String user_id);
	
	public List<Friend> viewSentRequests(String username);
	
	public boolean setUsersOnline(String username);
	
	public boolean setUsersOffline(String username);

}
