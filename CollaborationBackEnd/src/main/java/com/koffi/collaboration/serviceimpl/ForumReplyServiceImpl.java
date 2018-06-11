package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.ForumReplayDAO;
import com.koffi.collaboration.domain.ForumReply;
import com.koffi.collaboration.service.ForumReplyService;

@Service
@Transactional
public class ForumReplyServiceImpl  implements ForumReplyService{
	
	@Autowired
	ForumReplayDAO forumReplayDAO;
	
	public boolean addReply(ForumReply reply) {
		// TODO Auto-generated method stub
		return forumReplayDAO.addReply(reply);
	}

	public boolean deleteReply(ForumReply reply) {
		// TODO Auto-generated method stub
		return forumReplayDAO.deleteReply(reply);
	}

	public ForumReply getReply(int id) {
		// TODO Auto-generated method stub
		return forumReplayDAO.getReply(id);
	}

	public List<ForumReply> getForumReply(int forum_id) {
		// TODO Auto-generated method stub
		return forumReplayDAO.getForumReply(forum_id);
	}
}
