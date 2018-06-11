package com.koffi.collaboration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "c_blogcomment")
public class BlogComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "username", updatable = false, insertable = false )*/
	//@JoinColumn(name="username")
	private String username; // foreigner key
	//private User user;
	
	
/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "blog_id", updatable = false, insertable = false )*/
	//@JoinColumn(name="username")
	private String blog_id; // foreigner key
	//private Blog blog;
	
	
	private int rating;
	
	@Lob
	@Column(name="BLOG_COMMENT")
	private String comment;
	
	@Column(name="postedAt")
	private String postedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String user_name) {
		this.username = user_name;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(String postedAt) {
		this.postedAt = postedAt;
	}

}
