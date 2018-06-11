package com.koffi.collaboration.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koffi.collaboration.dao.FileUploadDAO;
import com.koffi.collaboration.domain.FileUpload;
import com.koffi.collaboration.service.FileUploadService;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	FileUploadDAO fileUploadDAO;

	public void save(FileUpload fileUpload, String user_name) {
		fileUploadDAO.save(fileUpload, user_name);
	}

	public FileUpload getFile(String user_name) {
		return fileUploadDAO.getFile(user_name);
	}

}
