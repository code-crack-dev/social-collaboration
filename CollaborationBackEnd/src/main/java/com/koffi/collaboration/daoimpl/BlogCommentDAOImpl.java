package com.koffi.collaboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.BlogCommentDAO;
import com.koffi.collaboration.domain.BlogComment;

@Repository
//@Transactional
@EnableTransactionManagement
public class BlogCommentDAOImpl implements BlogCommentDAO {


	private static final Logger log = LoggerFactory.getLogger(BlogCommentDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	

	
	public boolean addBlogComment(BlogComment blogComment) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(blogComment);
			log.info("Saved Comment successfully");
			return true;
		}	catch(HibernateException ex)
		{
			log.error("Failed to add comment");
			ex.printStackTrace();
			return false;
		}
		
	}

	
	@SuppressWarnings("rawtypes")
	public boolean deleteComment(int id) 
	{
		log.info("Starting of delete Method");
		String hql_string = "FROM BlogComment where id = "+id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
		log.info("Comment Retrieved "+query);
		BlogComment bc = (BlogComment) query.uniqueResult();
		log.info("Comment -"+bc.getComment()+" is being deleted");
		try
		{
			sessionFactory.getCurrentSession().delete(bc);
			log.info("Comment Deleted");
			return true;
		}	catch(HibernateException e)
		{
			log.error("Comment has not deleted");
			e.printStackTrace();
			return false;
		}
		
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BlogComment> getBlogComments(String blog_id) 
	{
		try
		{
			log.info("Starting List Blog Comment");
			String sql = "From BlogComment where blog_id = '"+blog_id+"'";
			System.out.println(sql);
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			log.info("Comments Retrieved"+query);
				return query.list();
		}
		catch (HibernateException ex)
		{	
			log.error("List not retrieved");
			ex.printStackTrace();
			return null;
		}
	}
}