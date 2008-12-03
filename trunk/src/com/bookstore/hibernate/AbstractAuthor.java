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

	private Integer id;

	private String firstName;

	private String latName;

	private Set<Book> books = new HashSet<Book>(0);

	// Constructors

	/** default constructor */
	public AbstractAuthor() {
	}

	/** minimal constructor */
	public AbstractAuthor(String firstName, String latName) {
		this.firstName = firstName;
		this.latName = latName;
	}

	/** full constructor */
	public AbstractAuthor(String firstName, String latName, Set<Book> books) {
		this.firstName = firstName;
		this.latName = latName;
		this.books = books;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lat_name", nullable = false)
	public String getLatName() {
		return this.latName;
	}

	public void setLatName(String latName) {
		this.latName = latName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
