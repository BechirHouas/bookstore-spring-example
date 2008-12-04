package com.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import com.bookstore.service.BookManager;

public class BookListController extends ParameterizableViewController {

	private BookManager bookManager;

	@Override
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		ModelAndView mav = new ModelAndView();
		
		// All or Latest
		Object attributeValue1 = null;
		Object attributeValue2 = null;
		String filter = request.getParameter("filter");
		
		if(filter!=null && filter.equals("latest")){
			attributeValue1 = bookManager.getLatest();
			attributeValue2 = "Latest";
		}
		else {
			attributeValue1 = bookManager.getBooks();
			attributeValue2 = "All";
		}
		
		mav.addObject("bookList", attributeValue1);
		mav.addObject("title", attributeValue2);
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
