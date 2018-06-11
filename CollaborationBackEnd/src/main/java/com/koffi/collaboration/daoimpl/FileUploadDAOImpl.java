package com.koffi.collaboration.daoimpl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.FileUploadDAO;
import com.koffi.collaboration.domain.FileUpload;

@Repository
//@Transactional
public class FileUploadDAOImpl implements FileUploadDAO{
	
private static final Logger log = LoggerFactory.getLogger(FileUploadDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public void save(FileUpload fileUpload, String username) 
	{
		try
		{
			String sql = "DELETE FROM FileUpload where user_name='"+username+"'";
			Query query =sessionFactory.getCurrentSession().createQuery(sql);
			query.executeUpdate();
			
			sessionFactory.getCurrentSession().save(fileUpload);
			log.info("File Uploaded");
		}	catch(Exception e)
		{
			log.error("Error uploading file");
			e.printStackTrace();
		}
	}


	public FileUpload getFile(String username) 
	{
		String sql = "from FileUpload where user_name = '"+username+"'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		//query.setParameter(4, username);
        @SuppressWarnings("deprecation")
		FileUpload fileUpload=(FileUpload)query.setMaxResults(1).uniqueResult();
		//session.close();
		return fileUpload;
	}	

}
