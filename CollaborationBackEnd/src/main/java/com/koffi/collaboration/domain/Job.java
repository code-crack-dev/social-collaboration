package com.koffi.collaboration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "c_job")
public class Job extends BaseDomain{
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int job_id;
	
	@Column(name = "job_title", nullable = false)
	private String job_title;
	
	private String job_type;
	private String job_level;
	
	
	private String username;
	private char job_status;
	
	//@NotNull
	private String job_location;
	
	//@NotNull
	private double salary;
	
	private String company;
	private String position;
	
	//@NotNull
	private int job_vacancy;
	
	//@NotNull
	private String qualification;

	@Lob
	private String job_description;

	private String date_of_post;
	
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public String getDate_of_post() {
		return date_of_post;
	}
	public void setDate_of_post(String date_of_post) {
		this.date_of_post = date_of_post;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getJob_level() {
		return job_level;
	}
	public void setJob_level(String job_level) {
		this.job_level = job_level;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char getJob_status() {
		return job_status;
	}
	public void setJob_status(char job_status) {
		this.job_status = job_status;
	}
	/*public Date getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(Date schedule_time) {
		this.schedule_time = schedule_time;
	}*/
	/*public Date getStarting_time() {
		return starting_time;
	}
	public void setStarting_time(Date starting_time) {
		this.starting_time = starting_time;
	}
	public Date getEnding_time() {
		return ending_time;
	}
	public void setEnding_time(Date ending_time) {
		this.ending_time = ending_time;
	}*/
	public String getJob_location() {
		return job_location;
	}
	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getJob_vacancy() {
		return job_vacancy;
	}
	public void setJob_vacancy(int job_vacancy) {
		this.job_vacancy = job_vacancy;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}	
}
