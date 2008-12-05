package com.bookstore.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AbstractBook entity provides the base persistence definition of the Book entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractBook implements java.io.Serializable {

	// Fields
	
	private Integer id;

	private Author author;

	private String isbn;

	private String title;

	private Date copyrightDate;

	private String image;

	private Calendar createdDate;

	// Constructors

	/** default constructor */
	public AbstractBook() {
	}

	/** minimal constructor */
	public AbstractBook(Author author, String isbn, String title, Date copyrightDate, Calendar createdDate) {
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.copyrightDate = copyrightDate;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public AbstractBook(Author author, String isbn, String title, Date copyrightDate, String image, Calendar createdDate) {
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.copyrightDate = copyrightDate;
		this.image = image;
		this.createdDate = createdDate;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", nullable = false)
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Column(name = "isbn", nullable = false, length = 10)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "title", nullable = false, length = 65535)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "copyright_date", nullable = false, length = 10)
	public Date getCopyrightDate() {
		return this.copyrightDate;
	}

	public void setCopyrightDate(Date copyrightDate) {
		this.copyrightDate = copyrightDate;
	}

	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 19)
	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

}
