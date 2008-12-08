package com.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.hibernate.Book;
import com.bookstore.hibernate.BookDAO;


/**
 * The Class BookManager.
 */
@SuppressWarnings("unchecked")
public class BookManager {

	/** The book dao. */
	@Autowired
	private BookDAO bookDAO;
	
	/**
	 * Gets the books.
	 * 
	 * @return the books
	 */
	public List<Book> getBooks() {
		return bookDAO.findAll();
	}
	
	/**
	 * Gets the latest.
	 * 
	 * @return the latest
	 */
	public List<Book> getLatest() {
		return bookDAO.findLatest();
	}

	/**
	 * Gets the book dao.
	 * 
	 * @return the book dao
	 */
	public BookDAO getBookDAO() {
		return bookDAO;
	}

	/**
	 * Sets the book dao.
	 * 
	 * @param bookDAO the new book dao
	 */
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
}
