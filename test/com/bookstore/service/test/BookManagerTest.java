package com.bookstore.service.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.hibernate.Book;
import com.bookstore.service.BookManager;

/**
 * The Class that tests BookManager.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml" })
public class BookManagerTest {

	/** The book manager. */
	private BookManager bookManager;

	
	// Tests
	/**
	 * Test get books.
	 */
	@Test
	public void testGetBooks() {
		List<Book> bookList = bookManager.getBooks();
		assertNotNull(bookList);
	}
	
	/**
	 * Test get latest.
	 */
	@Test
	public void testGetLatest() {
		List<Book> bookList = bookManager.getLatest();
		assertNotNull(bookList);
	}
	
	// Accessors
	/**
	 * Sets the book manager.
	 * 
	 * @param theManager the new book manager
	 */
	@Autowired
	public void setBookManager(BookManager theManager) {
		bookManager = theManager;
	}

}
