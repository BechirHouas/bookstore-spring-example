package com.bookstore.jpa;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book", catalog = "bookstore")
public class Book extends AbstractBook implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** minimal constructor */
	public Book(Author author, String isbn, String title, Date copyrightDate, Calendar createdDate) {
		super(author, isbn, title, copyrightDate, createdDate);
	}

	/** full constructor */
	public Book(Author author, String isbn, String title, Date copyrightDate, String image, Calendar createdDate) {
		super(author, isbn, title, copyrightDate, image, createdDate);
	}

}
