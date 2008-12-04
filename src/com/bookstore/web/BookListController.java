package com.bookstore.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bookstore.hibernate.Book;
import com.bookstore.service.BookManager;

public class BookListController extends ParameterizableViewController {

	private BookManager bookManager;

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// All or Latest
		List<Book> bookList = null;
		String title = null;
		String filter = request.getParameter("filter");
		
		if(filter!=null && filter.equals("latest")){
			bookList = bookManager.getLatest();
			title = "Latest";
		}
		else {
			bookList = bookManager.getBooks();
			title = "All";
		}
		mav.addObject("bookList", bookList);
		mav.addObject("title", title);
		mav.setViewName(getViewName());
		return mav;
	}

	public BookManager getBookManager() {
		return bookManager;
	}

	public void setBookManager(BookManager bookManager) {
		this.bookManager = bookManager;
	}

}
