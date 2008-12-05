package com.bookstore.bookComparator;

import java.util.Comparator;

import com.bookstore.hibernate.Author;
import com.bookstore.hibernate.Book;


/**
 * The Class BookComparator.
 * 
 * @author becky
 */
public class BookComparator implements Comparator<Book> {
	
	/** The book type. */
	private BookCompareEnum bookType;
	
	/** The is ascending. */
	private boolean isAscending = true;
	
	
	
	
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @param book1 the book1
	 * @param book2 the book2
	 * 
	 * @return the int
	 */
	public int compare(Book book1, Book book2) {
		int returnValue = 0;
		switch (bookType) {
		case Title:
			returnValue = titleCompare(book1, book2);
			break;
		case Author:
			returnValue = authorCompare(book1, book2);
			break;
		case Isbn:
			returnValue = isbnCompare(book1, book2);
			break;
		case Date:
			returnValue = dateCompare(book1, book2);
			break;
		default:
			returnValue = titleCompare(book1, book2);
			break;
		}
		return returnValue;
	}
	
	/**
	 * Isbn compare.
	 * 
	 * @param book1 the book1
	 * @param book2 the book2
	 * 
	 * @return the int
	 */
	private int isbnCompare(Book book1, Book book2) {
		int returnValue = 0;
		returnValue = book1.getIsbn().compareToIgnoreCase(book2.getIsbn());
		return direction(returnValue);
	}

	/**
	 * Title compare.
	 * 
	 * @param book1 the book1
	 * @param book2 the book2
	 * 
	 * @return the int
	 */
	private int titleCompare(Book book1, Book book2) {
		int returnValue = 0;
		returnValue = book1.getTitle().compareToIgnoreCase(book2.getTitle());
		return direction(returnValue);
	}
	
	/**
	 * Date compare.
	 * 
	 * @param book1 the book1
	 * @param book2 the book2
	 * 
	 * @return the int
	 */
	public int dateCompare(Book book1, Book book2) {
		int returnValue = 0;
		returnValue = book1.getCopyrightDate().compareTo(book2.getCopyrightDate());
		return direction(returnValue);
	}
	
	/**
	 * Author compare.
	 * 
	 * @param book1 the book1
	 * @param book2 the book2
	 * 
	 * @return the int
	 */
	public int authorCompare(Book book1, Book book2) {
		int returnValue = 0;
		Author author1 = book1.getAuthor();
		Author author2 = book2.getAuthor();
		
		// Compare Last Name
		returnValue = author1.getLastName().compareToIgnoreCase(author2.getLastName());
		
		// Compare First Name if necessary
		if (returnValue == 0) {
			returnValue = author1.getFirstName().compareToIgnoreCase(author2.getFirstName());
		}
			
		return direction(returnValue);
	}
	
	/**
	 * Direction.
	 * 
	 * @param returnValue the return value
	 * 
	 * @return the int
	 */
	private int direction(int returnValue) {
		if (!isAscending) {
			if (returnValue == -1) {
				returnValue = 1;
			} else if (returnValue == 1) {
				returnValue = -1;
			}
		}		
		return returnValue;
	}
	
	
	// Accessors
	/**
	 * Gets the book type.
	 * 
	 * @return the book type
	 */
	public BookCompareEnum getBookType() {
		return bookType;
	}
	
	/**
	 * Sets the book type.
	 * 
	 * @param type the new book type
	 */
	public void setBookType(BookCompareEnum type) {
		bookType = type;
	}
	
	/**
	 * Gets the checks if is ascending.
	 * 
	 * @return the checks if is ascending
	 */
	public Boolean getIsAscending() {
		return isAscending;
	}
	
	/**
	 * Sets the checks if is ascending.
	 * 
	 * @param ascending the new checks if is ascending
	 */
	public void setIsAscending(Boolean ascending) {
		isAscending = ascending;
	}

}
