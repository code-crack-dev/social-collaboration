package com.koffi.collaboration;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.koffi.collaboration.dao.FriendDAO;
import com.koffi.collaboration.domain.Friend;

public class FriendTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	//@Autowired
	//private static Friend friend;
	
	@Autowired
	private static FriendDAO friendDAO;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		
		context.scan("com.koffi");
		context.refresh();
		
		friendDAO =(FriendDAO) context.getBean("friendDAO");
		//friend = (Friend)context.getBean("friend");
	}

	@Test
	public void saveFriendTestCase() {
		Friend friend = new Friend();
		friend.setFriend_address("TC Palya");
		friend.setFriend_f_name("Stephy");
		friend.setFriend_gender("Female");
		friend.setFriend_id("102");
		friend.setFriend_l_name("Goran");
		friend.setFriend_mobile("987400898");
		friend.setFriendisOnline('Y');
		friend.setUser_address("Citech");
		friend.setUser_f_name("Goran");
		friend.setUser_gender("Male");
		friend.setUsername("Manu");
		friend.setUserIsOnline('Y');
		friend.setUser_mobile("888454789");
		friend.setUser_l_name("Emmanuel");
		friend.setUser_id("102");
		friend.setStatus('P');
		Boolean status = friendDAO.save(friend); 
		assertEquals("Save friend",true, status);
	}

}
