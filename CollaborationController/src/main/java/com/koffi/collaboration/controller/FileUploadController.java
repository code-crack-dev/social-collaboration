package com.koffi.collaboration.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.koffi.collaboration.domain.FileUpload;
import com.koffi.collaboration.service.FileUploadService;

@RestController
public class FileUploadController {
	
private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private HttpSession session;

	@PostMapping(value="/Upload")
	public ModelAndView uploadFile(@RequestParam("uploadedFile") MultipartFile uploadFile)  throws Exception
	{
		if (uploadFile != null ) 
		{
			String username = session.getAttribute("username").toString();
			MultipartFile aFile = uploadFile;
			FileUpload fileUpload = new FileUpload();
			System.out.println(aFile);
			fileUpload.setFile_name(session.getAttribute("username").toString());
            fileUpload.setFile_data(aFile.getBytes());//image 
            fileUpload.setUser_name(username);//login details
            fileUploadService.save(fileUpload, username);
            
            FileUpload getFileUpload = fileUploadService.getFile(username);
         	String name = getFileUpload.getFile_name();
         	System.out.println("Name = "+name);
         	System.out.println(getFileUpload.getFile_data());
         	byte[] imagefiles = getFileUpload.getFile_data();  //image
         	try
         	{
         		//F:\JAVA\MY JAVA BATCHES\Ecilips oxygen\oxygen project\CollaborationFrontEndApp\WebContent\c_resources\images
         		//change the path according to your workspace and the name of your project
         		String path="G:/NIIT project2/CollaborationFrontEnd/WebContent/c_resources/images/"+username;
         		//String path="C:/collaborationProject/collaborationFrontEnd/WebContent/images/"+username;
         		File file=new File(path);
         		//file.mkdirs();
         		FileOutputStream fos = new FileOutputStream(file);
         		fos.write(imagefiles); // write the array of bytes in username file.
         		fos.close();
         		}catch(Exception e){
         		e.printStackTrace();
         	}
		}
		ModelAndView mv = new ModelAndView("backToHome");
		return mv;
	}

}
