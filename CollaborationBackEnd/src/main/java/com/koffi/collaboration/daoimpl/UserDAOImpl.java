package com.koffi.collaboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.UserDAO;
import com.koffi.collaboration.domain.Job;
import com.koffi.collaboration.domain.User;



@Repository(value="userDAO")
@Transactional
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO{
	
	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	private User user;

	@Autowired
	private SessionFactory sessionFactory;

	
	
	
	public boolean saveUser(User user) {
		log.debug("starting save method in daoimpl");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateUser(User user) {
		log.debug("starting update method in daoimpl");
		log.debug("ISONLINE VALUE IS [BEFORE UPDATE]" + user.getIsOnline());
		User existingUser;
		try {
			Session session = sessionFactory.openSession();
			existingUser = (User) session.get(User.class, user.getUsername());
			existingUser.setIsOnline(user.getIsOnline());
			session.update(existingUser);
			session.flush();
			session.close();
			log.debug("ISONLINE VALUE IS [AFTER UPDATE] " + existingUser.getIsOnline());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User getByUsername(String username) {
		log.debug("Starting of Method Get User "+ username);
		try
		{
			User user =  sessionFactory.getCurrentSession().get(User.class, username);
			user.setErrorCode("200");
			user.setErrorMessage("User found");
			return user;
		}
		catch(Exception ex)
		{
			User user = new User();
			ex.printStackTrace();
			user.setErrorCode("404");
			user.setErrorMessage("User Not Found");
			return null;
		}
	}

	public User getByemailId(String emailId) {
		log.debug("Starting getby emailid method");
		String hql = "from User where emailId='" + emailId + "'";
		log.debug("------getByemailID query : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		return (User) query.uniqueResult();
	}

	public List<User> getAllUser() {
		log.debug("Starting getAllUsers DaoImpl");
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		log.debug("Ending UserDao getAllUser");
		return query.list();
	}

	public User validateUser(String username, String password) 
	{
		log.info("Validate User Method Started");
		try
		{
			User user =  sessionFactory.getCurrentSession().get(User.class, username);
			if(user.getPassword().equals(password))
			{
				user.setErrorCode("200");
				user.setErrorMessage("User Found");
				log.info("Valid User");
				return user;
			}
			else
			{
				user.setErrorCode("100");
				user.setErrorMessage("Password is incorrect");
				log.info("Invalid password");
				return user;
			}
		} catch(Exception ex)
		{
			User user = new User();
			user.setErrorCode("100");
			user.setErrorMessage("Username not found");
			log.error("Username Not found in database");
			return user;
		}
	}

	public boolean deleteUser(String username) {
		log.info("Delete user Method Started");
		try {
			User user =  sessionFactory.getCurrentSession().get(User.class, username);
			sessionFactory.getCurrentSession().delete(user);
			log.info("Delete user Success");
			return true;
		} 
		catch (HibernateException e) 
		{
			log.error("Error Deleting user");
			e.printStackTrace();
			return false;
		}
	}
}
