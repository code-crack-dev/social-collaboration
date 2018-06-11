package com.koffi.collaboration.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.BlogDAO;
import com.koffi.collaboration.domain.Blog;
import com.koffi.collaboration.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDAO blogDAO;
	
	public boolean addBlog(Blog blog) {
		
		return blogDAO.addBlog(blog);
	}

	public boolean approveBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDAO.approveBlog(blog);
	}

	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDAO.updateBlog(blog);
	}

	public boolean deleteBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDAO.deleteBlog(blog);
	}

	public Blog getBlog(String title) {
		// TODO Auto-generated method stub
		return blogDAO.getBlog(title);
	}

	public List<Blog> getBlogByUser(String username) {
		// TODO Auto-generated method stub
		return blogDAO.getBlogByUser(username);
	}

	public List<Blog> getApprovedBlogs() {
		// TODO Auto-generated method stub
		return blogDAO.getApprovedBlogs();
	}

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		return blogDAO.getAllBlogs();
	}

}
