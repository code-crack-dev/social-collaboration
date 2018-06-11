package com.koffi.collaboration.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Table(name = "c_Forum")
@Entity
public class Forum extends BaseDomain {

	@Id
	private int id;
	
	private String forum_id;
	
	@Lob
	private String content;
	
	private String username;
	
	private String date_time;
	
	@Lob
	private String rejected;
	
	private char status;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForum_id() {
		return forum_id;
	}

	public void setForum_id(String forum_id) {
		this.forum_id = forum_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRejected() {
		return rejected;
	}

	public void setRejected(String rejected) {
		this.rejected = rejected;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
}