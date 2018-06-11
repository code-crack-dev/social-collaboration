package com.koffi.collaboration.dao;

import java.util.List;

import com.koffi.collaboration.domain.ForumReply;

public interface ForumReplayDAO {
	
public boolean addReply(ForumReply reply);
	
	public boolean deleteReply(ForumReply reply);
	
	public ForumReply getReply(int id);
	
	public List<ForumReply> getForumReply(int forum_id);

}
