package com.koffi.collaboration.dao;

import com.koffi.collaboration.domain.FileUpload;

public interface FileUploadDAO {
	
	public void save(FileUpload fileUpload, String username);
	public FileUpload getFile(String user_name);
}
