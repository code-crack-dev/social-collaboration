package com.koffi.collaboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.EventDAO;
import com.koffi.collaboration.domain.Event;

@Repository
@Transactional
public class EventDAOImpl implements EventDAO{

private static final Logger log = LoggerFactory.getLogger(EventDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public boolean addEvent(Event event) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(event);
			log.info("Event has been saved");
			return true;
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.error("Error saving Event");
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes" })

	public boolean deleteEvent(int id) 
	{
		log.info("Entering Delete Event");
		try 
		{
			String sql = "FROM Event where id = '"+id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Event event = (Event) query.uniqueResult();
			if(event == null)
			{
				log.warn("Event Not Found");
				return false;
			}
			
			sessionFactory.getCurrentSession().delete(event);
			log.info("Success delete Event");
			return true;
		}
		catch (HibernateException e) 
		{
			log.error("Error Deleting Event");
			e.printStackTrace();
			return false;
		}
	}
	
	public Event getEvent(int id)
	{
		try
		{
			String sql = "FROM Event where id = '"+id+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Event event = (Event) query.uniqueResult();
			log.info("Event has been retrieved");
			return event;
		}
		catch(HibernateException ex)
		{
			log.error("Event not retrieved");
			ex.printStackTrace();
			return null;	
		}
	}

	
	public List<Event> listEvent() 
	{
		try
		{
			String sql = "FROM Event";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			List<Event> events = query.list();
			log.info("Event list has been retrieved");
			return events;
		}
		catch(HibernateException ex)
		{
			log.error("Event list has some error");
			ex.printStackTrace();
			return null;	
		}
	}
}
