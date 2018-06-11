package com.koffi.collaboration.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.JobDAO;
import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.JobApplied;

@Repository(value="jobDAO")
@EnableTransactionManagement
@Transactional
public class JobDAOImpl implements JobDAO{
	
private static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	private int getMaxJobID()
	{
		int maxValue = 100;
		try {
			maxValue = (Integer)getCurrentSession().createQuery("select max(job_id) from Job").uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
	
	private int getMaxJobAppliedID()
	{
		int maxValue = 100;
		try {
			maxValue = (Integer)getCurrentSession().createQuery("select max(id) from JobApplied").uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
	
	//@Transactional
	public boolean addJob(Job job)
	{
		log.info("Job Add started");
		try
		{
			job.setJob_id(getMaxJobID()+1);
			//job.setDate_of_post(new Date()); See abbas after
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			log.info("Add Job Success");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Error adding Job");
			ex.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	//@Transactional
	public List<Job> listJobs()
	{
		log.info("List jobs method Started");
		try
		{
			String hql = "FROM Job ";// Review this method
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.info("List of Jobs retrieved");
			return query.list();
		}	
		catch(Exception ex)
		{
			log.error("Error occured");
			ex.printStackTrace();
			return null;
		}	
	}

	//@Transactional
	public boolean deleteJob(int job_id) 
	{
		log.info("Delete Job Method Started");
		try {
			Job job =  sessionFactory.getCurrentSession().get(Job.class, job_id);
			sessionFactory.getCurrentSession().delete(job);
			log.info("Delete job Success");
			return true;
		} 
		catch (HibernateException e) 
		{
			log.error("Error Deleting Blog");
			e.printStackTrace();
			return false;
		}
	}

	//@Transactional
	public boolean invalidateJob(int job_id)
	{
		log.info("Invalidate a Job Started");
		try
		{
			Job job =  sessionFactory.getCurrentSession().get(Job.class, job_id);
			Job saveJob = job;
			sessionFactory.getCurrentSession().delete(job);
			log.info("Processing Request");
			job.setJob_status('R');
			sessionFactory.getCurrentSession().save(saveJob);
			log.info("Job as been invalidated");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Error updating Job");
			ex.printStackTrace();
			return false;	
		}
	}

	//@Transactional
	public Job getJob(String name) 
	{
		log.info("Entered Get Job");
		try
		{			
			String hql = "FROM Job where job_title = '"+name+"'";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.info("List of Jobs retrieved");
			return (Job) query.uniqueResult();	
		}
		catch(Exception ex)
		{
			log.error("Error retreiving Job");
			ex.printStackTrace();
			return null;
		}
	}

	public boolean updateJob(Job job) {
		try {
			getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public Job getJob(int job_id) {
		
		return (Job) getCurrentSession().createCriteria(Job.class)
					.add(Restrictions.eq("job_id", job_id)).uniqueResult();
		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Job> listJob(char status)
	{
		return getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("status", status)).list();
	}

	public boolean addJob(JobApplied jobApplied) {
		try {
			if (!isJobOpened(jobApplied.getJob_id())) {
				return false;
			}
			// if you already applied, you can not apply again
			if (isJobAlreadyApplied(jobApplied.getUsername(), jobApplied.getJob_id())) {
				return false;
			}
			
			//if user does not exist, you can not apply
			
			if(userDAO.getByUsername(jobApplied.getUsername())==null)
			{
				return false;
			}
			
			//if the job does not exist, you can not apply
			if(getJob(jobApplied.getJob_id())==null)
			{
				return false;
			}

			jobApplied.setId(getMaxJobAppliedID() + 1);
			jobApplied.setStatus('N');
			//jobApplied.setApplied_date(new Date(System.currentTimeMillis())); ABBAS SEE
			getCurrentSession().save(jobApplied);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateJob(JobApplied jobApplied) {
		try {
			getCurrentSession().update(jobApplied);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<JobApplied> jobAppliedList(int job_id) {

		return getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("job_id", job_id)).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<JobApplied> jobAppliedList(int job_id, char status) {
		
		return getCurrentSession().createCriteria(JobApplied.class).add(Restrictions.eq("job_id", job_id))
				.add(Restrictions.eq("status", status)).list();
	}

	/**
	 * This method will return true, if the job with id exist and status is open.
	 * else return false.
	 */
	public boolean isJobOpened(int job_id) {
		Job job = (Job) getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("id", job_id)).uniqueResult();

		if (job != null && job.getJob_status() == 'N') {
			return true;
		}

		return false;

	}

	/*
		This method will return true if the job already applied with this username.
		else, return false
	 */

	public boolean isJobAlreadyApplied(String username, int job_id) {

		//select * from JobApplied where emailID = ? and jobID = ?
		JobApplied jobApplied = (JobApplied) getCurrentSession()
				.createCriteria(JobApplied.class)
				.add(Restrictions.eq("emailID", username))
				.add(Restrictions.eq("jobID", job_id)).uniqueResult();

		if (jobApplied == null) {
			return false;
		}
		return true;

	}

	public List<JobApplied> listJobApplied(String username) {
		return getCurrentSession().createCriteria(JobApplied.class).add(Restrictions.eq("username",username)).list();
	}

}
