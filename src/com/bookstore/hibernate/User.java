package com.bookstore.hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "bookstore")
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default constructor.
	 */
	public User() {
	}

	/**
	 * full constructor.
	 * 
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 */
	public User(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}

}
