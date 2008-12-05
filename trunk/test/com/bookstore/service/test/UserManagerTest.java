package com.bookstore.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.hibernate.User;
import com.bookstore.service.UserManager;

/**
 * The Class UserManagerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml" })
public class UserManagerTest {
	
	/** The user manager. */
	private UserManager userManager;

	/**
	 * Test get user by email and password.
	 */
	@Test
	public void testGetUserByEmailAndPassword() {
		User user = userManager.getUserByEmailAndPassword("bob@johnson.com", "pwd4bob");
		assertNotNull(user);
		user = userManager.getUserByEmailAndPassword("I am not a user", "blah");
		assertNull(user);
	}

	/**
	 * Sets the user manager.
	 * 
	 * @param userManager the new user manager
	 */
	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}


}
