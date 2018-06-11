package com.koffi.collaboration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "c_profile_pics")
@Component
public class FileUpload {
	@Id
	@GeneratedValue
	@Column(name = "file_id")
	private int id;
	
	@Column(name = "file_name")
	private String file_name;
	
	@Lob   //to store some image files, Large Object
	@Column(name = "file_data")
	
	private byte[] file_data;
	
    private String user_name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public byte[] getFile_data() {
		return file_data;
	}

	public void setFile_data(byte[] file_data) {
		this.file_data = file_data;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}	
	
}