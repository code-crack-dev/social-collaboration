package com.koffi.collaboration.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.ForumReplayDAO;
import com.koffi.collaboration.domain.ForumReply;

@Repository
//@Transactional
public class ForumReplyDAOImpl implements ForumReplayDAO{
	
	private static final Logger log = LoggerFactory.getLogger(ForumReplyDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public boolean addReply(ForumReply reply) 
	{
		log.info("Entering add reply");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(reply);
			log.info("Reply added");
			return true;
		}	catch(Exception ex)
			{
				log.info("Error adding reply");
				ex.printStackTrace();
				return false;
			}
	}

	@Transactional
	public boolean deleteReply(ForumReply reply)
	{
		log.info("Entering delete Reply");
		try
		{
			sessionFactory.getCurrentSession().delete(reply);
			log.info("Reply deleted");
			return true;
		}	catch(Exception ex)
			{
				log.info("Error deleting reply");
				ex.printStackTrace();
				return false;
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public List<ForumReply> getForumReply(int forum_id)
	{
		log.info("Getting list of replies for "+forum_id);
		try
		{
			String sql = "FROM ForumReply where forum_id = "+forum_id;
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			if(query != null)
			{
				return query.list();
			}
		}	catch(Exception ex)
		{
			log.error("Error retrieving list");
			ex.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public ForumReply getReply(int id)
	{
		log.info("Getting reply for "+id);
		try
		{
			String sql = "FROM ForumReply where id = '"+id+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			ForumReply reply = (ForumReply) query.uniqueResult();
			if(query != null)
			{
				return reply;
			}
		}	catch(Exception ex)
		{
			log.error("Error retrieving list");
			ex.printStackTrace();
		}
		return null;	
	}
}
