package com.bookstore.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bookstore.bookComparator.BookComparator;
import com.bookstore.bookComparator.BookCompareEnum;
import com.bookstore.hibernate.Book;
import com.bookstore.service.BookManager;

/**
 * The Class BookListController.
 */
public class BookListController extends ParameterizableViewController {

	/** The book manager. */
	private BookManager bookManager;

	
	/**
	 * Handle request internal.
	 * 
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the model and view
	 * 
	 * @throws Exception the exception
	 * 
	 * @see org.springframework.web.servlet.mvc.ParameterizableViewController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		// All or Latest
		List<Book> bookList = bookManager.getBooks();
		String title = "";
		String sort = request.getParameter("sort");
		BookComparator comparator = new BookComparator();
		BookCompareEnum type;
		
		if (sort == null || sort.equals("title")) {
			type = BookCompareEnum.Title;
			title = "By Title";
		} else if (sort.equals("author")) {
			type = BookCompareEnum.Author;
			title = "By Author";
		} else if (sort.equals("date")) {
			type = BookCompareEnum.Date;
			title = "By Date";
		} else if (sort.equals("isbn")) {
			type = BookCompareEnum.Isbn;
			title = "By ISBN";
		} else {
			// Trying to filter on a type not implemented
			// Default to title
			type = BookCompareEnum.Title;
			title = "Filter Not Implemented";			
		}
		
		// Sort by the type
		comparator.setBookType(type);
		Collections.sort(bookList, comparator);
		
		mav.addObject("bookList", bookList);
		mav.addObject("title", title);
		mav.setViewName(getViewName());
		return mav;
	}

	/**
	 * Gets the book manager.
	 * 
	 * @return the book manager
	 */
	public BookManager getBookManager() {
		return bookManager;
	}

	/**
	 * Sets the book manager.
	 * 
	 * @param bookManager the new book manager
	 */
	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}

}
