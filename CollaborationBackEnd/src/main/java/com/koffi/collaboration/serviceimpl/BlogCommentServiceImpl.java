package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.BlogCommentDAO;
import com.koffi.collaboration.domain.BlogComment;
import com.koffi.collaboration.service.BlogCommentService;

@Service
@Transactional
public class BlogCommentServiceImpl implements BlogCommentService{
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	public boolean addBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		return blogCommentDAO.addBlogComment(blogComment);
	}

	public boolean deleteComment(int id) {
		// TODO Auto-generated method stub
		return blogCommentDAO.deleteComment(id);
	}

	public List<BlogComment> getBlogComments(String blog_id) {
		// TODO Auto-generated method stub
		return blogCommentDAO.getBlogComments(blog_id);
	}

}
