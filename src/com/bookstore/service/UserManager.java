package com.bookstore.service;

import java.util.List;

import com.bookstore.hibernate.User;
import com.bookstore.hibernate.UserDAO;
import com.bookstore.util.BookstoreUtil;

/**
 * The Class UserManager.
 */
public class UserManager {

	/** The user dao. */
	private UserDAO userDAO;

	/**
	 * Checks if is valid user.
	 * 
	 * @param email the email
	 * @param password the password
	 * 
	 * @return true, if user is valid
	 */
	public User getUserByEmailAndPassword(final String email, final String password) {
		if (BookstoreUtil.isNotEmpty(email) && BookstoreUtil.isNotEmpty(password)) {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			List<User> users = userDAO.findByExample(user);
			if (BookstoreUtil.isNotEmpty(users)) {
				return users.get(0);
			}
		}
		return null;
	}

	/**
	 * Gets the user dao.
	 * 
	 * @return the user dao
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Sets the user dao.
	 * 
	 * @param userDAO the new user dao
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
