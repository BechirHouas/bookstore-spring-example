package com.bookstore.service;

import java.util.List;

import com.bookstore.hibernate.BookDAO;

public class BookManager {

	private BookDAO bookDAO;
	
	public List getBooks() {
		return bookDAO.findAll();
	}
	
	public List getLatest() {
		return bookDAO.findLatest();
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
}
