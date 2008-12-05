package com.bookstore.hibernate;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "author", catalog = "bookstore")
public class Author extends AbstractAuthor implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * default constructor.
	 */
	public Author() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Author(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/**
	 * full constructor.
	 * 
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param books the books
	 */
	public Author(String firstName, String lastName, Set<Book> books) {
		super(firstName, lastName, books);
	}

}
