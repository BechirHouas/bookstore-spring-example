/**
 * 
 */
package com.bookstore.util.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bookstore.util.BookstoreUtil;


/**
 * The Class BookstoreUtilTest.
 * 
 * @author Chris.Thorn
 */
public class BookstoreUtilTest {
	
	/** The null str. */
	private String nullStr;
	
	/** The empty str. */
	private String emptyStr;
	
	/** The non empty str. */
	private String nonEmptyStr;
	
	/** The null list. */
	private List<String> nullList;
	
	/** The empty list. */
	private List<String> emptyList;
	
	/** The non empty list. */
	private List<String> nonEmptyList;
	
	/** The null object. */
	private Object nullObject;
	
	/** The non empty object. */
	private Object nonEmptyObject;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		nullStr = null;
		emptyStr = "";
		nonEmptyStr = "not empty";
		
		nullList = null;
		emptyList = new ArrayList<String>();
		nonEmptyList = new ArrayList<String>();
		nonEmptyList.add("not empty");
		
		nullObject = null;
		nonEmptyObject = new MyObject();
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isEmpty(java.lang.String)}.
	 */
	@Test
	public void testIsEmptyString() {
		assertTrue(BookstoreUtil.isEmpty(nullStr));
		assertTrue(BookstoreUtil.isEmpty(emptyStr));
		assertFalse(BookstoreUtil.isEmpty(nonEmptyStr));
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isNotEmpty(java.lang.String)}.
	 */
	@Test
	public void testIsNotEmptyString() {
		assertFalse(BookstoreUtil.isNotEmpty(nullStr));
		assertFalse(BookstoreUtil.isNotEmpty(emptyStr));
		assertTrue(BookstoreUtil.isNotEmpty(nonEmptyStr));
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isEmpty(java.lang.Object)}.
	 */
	@Test
	public void testIsEmptyObject() {
		assertTrue(BookstoreUtil.isEmpty(nullObject));
		assertFalse(BookstoreUtil.isEmpty(nonEmptyObject));
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isNotEmpty(java.lang.Object)}.
	 */
	@Test
	public void testIsNotEmptyObject() {
		assertFalse(BookstoreUtil.isNotEmpty(nullObject));
		assertTrue(BookstoreUtil.isNotEmpty(nonEmptyObject));
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isEmpty(com.lowagie.text.List)}.
	 */
	@Test
	public void testIsEmptyList() {
		assertTrue(BookstoreUtil.isEmpty(nullList));
		assertTrue(BookstoreUtil.isEmpty(emptyList));
		assertFalse(BookstoreUtil.isEmpty(nonEmptyList));
	}

	/**
	 * Test method for {@link com.bookstore.util.BookstoreUtil#isNotEmpty(com.lowagie.text.List)}.
	 */
	@Test
	public void testIsNotEmptyList() {
		assertFalse(BookstoreUtil.isNotEmpty(nullList));
		assertFalse(BookstoreUtil.isNotEmpty(emptyList));
		assertTrue(BookstoreUtil.isNotEmpty(nonEmptyList));
	}
	
	/**
	 * The Class MyObject.
	 */
	private class MyObject {
		
		/** The value. */
		private String value;

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value.
		 * 
		 * @param value the new value
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}

}
