package com.koffi.collaboration.service;

import java.util.List;

import com.koffi.collaboration.domain.Blog;

public interface BlogService {

	public boolean addBlog(Blog blog);

	public boolean approveBlog(Blog blog);

	public boolean updateBlog(Blog blog);

	public boolean deleteBlog(Blog blog);

	public Blog getBlog(String blog_title);

	public List<Blog> getBlogByUser(String username);

	public List<Blog> getApprovedBlogs();

	public List<Blog> getAllBlogs();
}
