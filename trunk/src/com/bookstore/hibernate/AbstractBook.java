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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The author. */
	private Author author;

	/** The isbn. */
	private String isbn;

	/** The title. */
	private String title;

	/** The copyright date. */
	private Date copyrightDate;

	/** The image. */
	private String image;

	/** The created date. */
	private Calendar createdDate;

	// Constructors

	/**
	 * default constructor.
	 */
	public AbstractBook() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param author the author
	 * @param isbn the isbn
	 * @param title the title
	 * @param copyrightDate the copyright date
	 * @param createdDate the created date
	 */
	public AbstractBook(Author author, String isbn, String title, Date copyrightDate, Calendar createdDate) {
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.copyrightDate = copyrightDate;
		this.createdDate = createdDate;
	}

	/**
	 * full constructor.
	 * 
	 * @param author the author
	 * @param isbn the isbn
	 * @param title the title
	 * @param copyrightDate the copyright date
	 * @param image the image
	 * @param createdDate the created date
	 */
	public AbstractBook(Author author, String isbn, String title, Date copyrightDate, String image, Calendar createdDate) {
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.copyrightDate = copyrightDate;
		this.image = image;
		this.createdDate = createdDate;
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
	 * Gets the author.
	 * 
	 * @return the author
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", nullable = false)
	public Author getAuthor() {
		return this.author;
	}

	/**
	 * Sets the author.
	 * 
	 * @param author the new author
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * Gets the isbn.
	 * 
	 * @return the isbn
	 */
	@Column(name = "isbn", nullable = false, length = 10)
	public String getIsbn() {
		return this.isbn;
	}

	/**
	 * Sets the isbn.
	 * 
	 * @param isbn the new isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	@Column(name = "title", nullable = false, length = 65535)
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the copyright date.
	 * 
	 * @return the copyright date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "copyright_date", nullable = false, length = 10)
	public Date getCopyrightDate() {
		return this.copyrightDate;
	}

	/**
	 * Sets the copyright date.
	 * 
	 * @param copyrightDate the new copyright date
	 */
	public void setCopyrightDate(Date copyrightDate) {
		this.copyrightDate = copyrightDate;
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	@Column(name = "image")
	public String getImage() {
		return this.image;
	}

	/**
	 * Sets the image.
	 * 
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the created date.
	 * 
	 * @return the created date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 19)
	public Calendar getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * Sets the created date.
	 * 
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

}
