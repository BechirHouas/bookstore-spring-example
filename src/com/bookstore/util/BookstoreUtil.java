/**
 * 
 */
package com.bookstore.util;

import java.util.List;


/**
 * The Class BookstoreUtil.
 * 
 * @author Chris.Thorn
 */
public class BookstoreUtil {
	
	/**
	 * Checks if the given String is empty.
	 * 
	 * @param str the str
	 * 
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		if (str != null && !str.trim().equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the given String is empty.
	 * 
	 * @param str the str
	 * 
	 * @return true, if is empty
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * Checks if the given Object is empty.
	 * 
	 * @param obj the obj
	 * 
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object obj) {
		if (obj != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the given Object is not empty.
	 * 
	 * @param obj the obj
	 * 
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * Checks if the given List is empty.
	 * 
	 * @param list the list
	 * 
	 * @return true, if is empty
	 */
	public static boolean isEmpty(List list) {
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the given List is not empty.
	 * 
	 * @param list the list
	 * 
	 * @return true, if is empty
	 */
	public static boolean isNotEmpty(List list) {
		return !isEmpty(list);
	}
}
