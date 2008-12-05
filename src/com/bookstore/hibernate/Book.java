package com.bookstore.hibernate;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default constructor */
	public Book() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param author the author
	 * @param isbn the isbn
	 * @param title the title
	 * @param copyrightDate the copyright date
	 * @param createdDate the created date
	 */
	public Book(Author author, String isbn, String title, Date copyrightDate, Calendar createdDate) {
		super(author, isbn, title, copyrightDate, createdDate);
	}

	/**
	 * full constructor.
	 * 
	 * @param author the author
	 * @param isbn the isbn
	 * @param title the title
	 * @param copyrightDate the copyright date
	 * @param image the image
	 * @param createdDate the created date
	 */
	public Book(Author author, String isbn, String title, Date copyrightDate, String image, Calendar createdDate) {
		super(author, isbn, title, copyrightDate, image, createdDate);
	}

}
