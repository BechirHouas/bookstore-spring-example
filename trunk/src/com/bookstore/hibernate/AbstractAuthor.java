package com.bookstore.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * AbstractAuthor entity provides the base persistence definition of the Author entity. @author MyEclipse Persistence
 * Tools
 */
@MappedSuperclass
public abstract class AbstractAuthor implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The books. */
	private Set<Book> books = new HashSet<Book>(0);

	// Constructors

	/**
	 * default constructor.
	 */
	public AbstractAuthor() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public AbstractAuthor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * full constructor.
	 * 
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param books the books
	 */
	public AbstractAuthor(String firstName, String lastName, Set<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	// Property accessors
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name
	 */
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name
	 */
	@Column(name = "lat_name", nullable = false)
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the books.
	 * 
	 * @return the books
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
	public Set<Book> getBooks() {
		return this.books;
	}

	/**
	 * Sets the books.
	 * 
	 * @param books the new books
	 */
	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
