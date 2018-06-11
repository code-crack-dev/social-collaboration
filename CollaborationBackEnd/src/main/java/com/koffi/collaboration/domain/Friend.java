package com.koffi.collaboration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "c_friend")
public class Friend extends BaseDomain {

	@Id
	private int id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String user_id;

	private String user_gender;
	private String user_mobile;
	private String user_address;

	@Column(nullable = false)
	private String friend_id;

	@Column(nullable = false)
	private String user_f_name;

	private String user_l_name;

	@Column(nullable = false)
	private char userIsOnline;

	@Column(nullable = false)
	private String friend_f_name;

	private String friend_l_name;

	private String friend_gender;

	private String friend_mobile;

	private String friend_address;

	@Column(nullable = false)
	private char friendisOnline;

	@Column(name="status", nullable=false)
	private char status;
	
	
	
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getUser_f_name() {
		return user_f_name;
	}

	public void setUser_f_name(String user_f_name) {
		this.user_f_name = user_f_name;
	}

	public String getUser_l_name() {
		return user_l_name;
	}

	public void setUser_l_name(String user_l_name) {
		this.user_l_name = user_l_name;
	}

	public char getUserIsOnline() {
		return userIsOnline;
	}

	public void setUserIsOnline(char userIsOnline) {
		this.userIsOnline = userIsOnline;
	}

	public String getFriend_f_name() {
		return friend_f_name;
	}

	public void setFriend_f_name(String friend_f_name) {
		this.friend_f_name = friend_f_name;
	}

	public String getFriend_l_name() {
		return friend_l_name;
	}

	public void setFriend_l_name(String friend_l_name) {
		this.friend_l_name = friend_l_name;
	}

	public String getFriend_gender() {
		return friend_gender;
	}

	public void setFriend_gender(String friend_gender) {
		this.friend_gender = friend_gender;
	}

	public String getFriend_mobile() {
		return friend_mobile;
	}

	public void setFriend_mobile(String friend_mobile) {
		this.friend_mobile = friend_mobile;
	}

	public String getFriend_address() {
		return friend_address;
	}

	public void setFriend_address(String friend_address) {
		this.friend_address = friend_address;
	}

	public char getFriendisOnline() {
		return friendisOnline;
	}

	public void setFriendisOnline(char friendisOnline) {
		this.friendisOnline = friendisOnline;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
