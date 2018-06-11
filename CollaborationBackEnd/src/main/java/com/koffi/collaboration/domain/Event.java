package com.koffi.collaboration.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="c_event")
public class Event extends BaseDomain{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String event_name;
	
	@Lob
	private String event_description;
	
	@Column(name = "eventDate")
	private Date date;
	
	private String event_time;
	private String event_venue;
	
	@Column(name = "event_posted_time")
	private String event_posted_time;
	
	@Transient
	private String event_date;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEvent_time() {
		return event_time;
	}

	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}

	public String getEvent_venue() {
		return event_venue;
	}

	public void setEvent_venue(String event_venue) {
		this.event_venue = event_venue;
	}

	public String getEvent_posted_time() {
		return event_posted_time;
	}

	public void setEvent_posted_time(String event_posted_time) {
		this.event_posted_time = event_posted_time;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	
}
