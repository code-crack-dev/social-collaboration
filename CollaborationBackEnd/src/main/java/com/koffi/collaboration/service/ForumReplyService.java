package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.ForumReply;

public interface ForumReplyService {
	
public boolean addReply(ForumReply reply);
	
	public boolean deleteReply(ForumReply reply);
	
	public ForumReply getReply(int id);
	
	public List<ForumReply> getForumReply(int forum_id);

}
