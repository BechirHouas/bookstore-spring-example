package com.bookstore.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		User user = userManager.getUserByEmailAndPassword("charley@brown.com", "pwd4charley");
		assertNotNull(user);
		user = userManager.getUserByEmailAndPassword("I am not a user", "blah");
		assertNull(user);
	}
	
	/**
	 * Test get users by example.
	 */
	@Test
	public void testGetUsersByExample() {
		User user = new User("Charley", "Brown", "charley@brown.com", null);
		List<User> users = userManager.getUsersByExample(user);
		assertNotNull(users);
		assertTrue(users.size() == 1);
	}
	
	/**
	 * Test create user.
	 */
	@Test
	public void testCreateUser() {
		User user = new User("Hello", "Kitty", "hello@kitty.com", "pwd4hello");
		user = userManager.createUser(user);
		List<User> users = userManager.getUsersByExample(user);
		assertNotNull(users);
		assertTrue(users.size() == 1);
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
