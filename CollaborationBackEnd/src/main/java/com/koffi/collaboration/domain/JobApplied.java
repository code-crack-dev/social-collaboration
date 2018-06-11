package com.koffi.collaboration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

@Component()
@Entity
@Table(name = "c_AppliedJob")
public class JobApplied extends BaseDomain {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	

	private String username;
	private String job_app_title;
	private String position;
	private String company;
	private String location;
	private String reason;
	private char status; // N->new job applied C -> call for interview S -> Selected A->Approved
	private int job_id;
	
	
	
	public String getJob_app_title() {
		return job_app_title;
	}

	public void setJob_app_title(String job_app_title) {
		this.job_app_title = job_app_title;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	@Column(name="date_time")
	private String applied_date;
	
	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplied_date() {
		return applied_date;
	}

	public void setApplied_date(String applied_date) {
		this.applied_date = applied_date;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
