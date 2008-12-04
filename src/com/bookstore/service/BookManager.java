package com.bookstore.service;

import java.util.List;

import com.bookstore.hibernate.Book;
import com.bookstore.hibernate.BookDAO;

@SuppressWarnings("unchecked")
public class BookManager {

	private BookDAO bookDAO;
	
	public List<Book> getBooks() {
		return bookDAO.findAll();
	}
	
	public List<Book> getLatest() {
		return bookDAO.findLatest();
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
}
