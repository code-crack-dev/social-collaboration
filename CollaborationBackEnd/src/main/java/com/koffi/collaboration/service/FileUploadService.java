package com.koffi.collaboration.service;

import com.koffi.collaboration.domain.FileUpload;

public interface FileUploadService {
	
	public void save(FileUpload fileUpload, String user_name);
	public FileUpload getFile(String user_name);

}
