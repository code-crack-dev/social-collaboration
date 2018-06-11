package com.koffi.collaboration.dao;

import java.util.List;

import com.koffi.collaboration.domain.BlogComment;

public interface BlogCommentDAO {

	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(int id);
	
	public List<BlogComment> getBlogComments(String blog_id);
}