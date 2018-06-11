package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.ForumDAO;
import com.koffi.collaboration.domain.Forum;
import com.koffi.collaboration.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	ForumDAO forumDAO;
	
	public boolean addForum(Forum forum) {
		// TODO Auto-generated method stub
		return forumDAO.addForum(forum);
	}

	public boolean deleteForum(int id) {
		// TODO Auto-generated method stub
		return forumDAO.deleteForum(id);
	}

	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		return forumDAO.updateForum(forum);
	}

	public Forum getForum(int id) {
		// TODO Auto-generated method stub
		return forumDAO.getForum(id);
	}

	public List<Forum> getUserForums(String username) {
		// TODO Auto-generated method stub
		return forumDAO.getUserForums(username);
	}

	public List<Forum> getForumList() {
		// TODO Auto-generated method stub
		return forumDAO.getForumList();
	}

	public List<Forum> approvedForums() {
		// TODO Auto-generated method stub
		return forumDAO.approvedForums();
	}

}
