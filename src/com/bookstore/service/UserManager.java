package com.bookstore.service;

import java.util.List;

import com.bookstore.hibernate.User;
import com.bookstore.hibernate.UserDAO;
import com.bookstore.util.BookstoreUtil;
import com.mindrot.jbcrypt.BCrypt;

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
			List<User> users = userDAO.findByEmail(email);
			if (BookstoreUtil.isNotEmpty(users)) {
				User user = users.get(0);
				if (BCrypt.checkpw(password, user.getPassword())) {
					return user;
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets the users by example.
	 * 
	 * @param user the user
	 * 
	 * @return the users by example
	 */
	public List<User> getUsersByExample(User user) {
		return userDAO.findByExample(user);
	}
	
	/**
	 * Creates the user.  The password will be encrypted using jBCrypt.
	 * 
	 * @param user the user
	 * 
	 * @return the user
	 */
	public User createUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword()));
		userDAO.attachDirty(user);
		return user;
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
