package com.koffi.collaboration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="c_blog")
@Component
public class Blog extends BaseDomain{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int blog_id;

	@Column(nullable = false)
	private String blog_title;
	
	@Column(nullable = false)
	@Lob
	private String blog_description;
	
	private String username; // foreigner key
	private String status;// not null. A->Accepted, N->new. R->reject
	//CREATE ONE MORE FIELD it should be nullable R->rejected/Accepted blog
	
	//ONE MORE METHOD // COMMENT THE BLOG// CAN OT ACHIEVE WITH BLIOG TABLE
	//CREATE BLOGCOMMENT TABLE

	private int b_like;
	private int unlike;
	private String remark;
	private String date_time;//Default SysteDate............

	@Lob
	private String rejected;

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public String getBlog_title() {
		return blog_title;
	}

	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}

	public String getBlog_description() {
		return blog_description;
	}

	public void setBlog_description(String blog_description) {
		this.blog_description = blog_description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public int getB_like() {
		return b_like;
	}

	public void setB_like(int b_like) {
		this.b_like = b_like;
	}

	public int getUnlike() {
		return unlike;
	}

	public void setUnlike(int unlike) {
		this.unlike = unlike;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getRejected() {
		return rejected;
	}

	public void setRejected(String rejected) {
		this.rejected = rejected;
	}

	
}
