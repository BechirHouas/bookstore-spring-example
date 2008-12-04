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

	/** default constructor */
	public Author() {
	}

	/** minimal constructor */
	public Author(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/** full constructor */
	public Author(String firstName, String lastName, Set<Book> books) {
		super(firstName, lastName, books);
	}

}
