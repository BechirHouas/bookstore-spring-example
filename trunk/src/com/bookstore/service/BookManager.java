package com.bookstore.service;

import java.util.List;

import org.springframework.orm.jpa.JpaTemplate;

import com.bookstore.jpa.BookDAO;

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
