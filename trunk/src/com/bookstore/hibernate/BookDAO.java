package com.bookstore.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Book entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.hibernate.Book
 * @author MyEclipse Persistence Tools
 */

public class BookDAO extends HibernateDaoSupport {
	private static final Log LOG = LogFactory.getLog(BookDAO.class);

	// property constants
	public static final String ISBN = "isbn";

	public static final String TITLE = "title";

	public static final String IMAGE = "image";

	/**
	 * @see org.springframework.dao.support.DaoSupport#initDao()
	 */
	protected void initDao() {
		// do nothing
	}

	/**
	 * Save.
	 * 
	 * @param transientInstance the transient instance
	 */
	public void save(Book transientInstance) {
		LOG.debug("saving Book instance");
		try {
			getHibernateTemplate().save(transientInstance);
			LOG.debug("save successful");
		} catch (RuntimeException re) {
			LOG.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete.
	 * 
	 * @param persistentInstance the persistent instance
	 */
	public void delete(Book persistentInstance) {
		LOG.debug("deleting Book instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOG.debug("delete successful");
		} catch (RuntimeException re) {
			LOG.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Find by id.
	 * 
	 * @param id the id
	 * 
	 * @return the book
	 */
	public Book findById(java.lang.Integer id) {
		LOG.debug("getting Book instance with id: " + id);
		try {
			Book instance = (Book) getHibernateTemplate().get("com.bookstore.hibernate.Book", id);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/**
	 * Find by example.
	 * 
	 * @param instance the instance
	 * 
	 * @return the list< book>
	 */
	public List<Book> findByExample(Book instance) {
		LOG.debug("finding Book instance by example");
		try {
			List<Book> results = (List<Book>) getHibernateTemplate().findByExample(instance);
			LOG.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			LOG.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName the property name
	 * @param value the value
	 * 
	 * @return the list
	 */
	public List findByProperty(String propertyName, Object value) {
		LOG.debug("finding Book instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Book as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * Find by isbn.
	 * 
	 * @param isbn the isbn
	 * 
	 * @return the list< book>
	 */
	public List<Book> findByIsbn(Object isbn) {
		return findByProperty(ISBN, isbn);
	}

	/**
	 * Find by title.
	 * 
	 * @param title the title
	 * 
	 * @return the list< book>
	 */
	public List<Book> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	/**
	 * Find by image.
	 * 
	 * @param image the image
	 * 
	 * @return the list< book>
	 */
	public List<Book> findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List findAll() {
		LOG.debug("finding all Book instances");
		try {
			String queryString = "from Book";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * Find latest.
	 * 
	 * @return the list
	 */
	public List findLatest() {
		LOG.debug("finding all Book instances");
		try {
			String queryString = "from Book";
			Criteria criteria = this.getSession().createCriteria(Book.class);
			criteria.addOrder(Order.desc("createdDate"));
			criteria.setMaxResults(5);
			return criteria.list();
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * Merge.
	 * 
	 * @param detachedInstance the detached instance
	 * 
	 * @return the book
	 */
	public Book merge(Book detachedInstance) {
		LOG.debug("merging Book instance");
		try {
			Book result = (Book) getHibernateTemplate().merge(detachedInstance);
			LOG.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOG.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * Attach dirty.
	 * 
	 * @param instance the instance
	 */
	public void attachDirty(Book instance) {
		LOG.debug("attaching dirty Book instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			LOG.debug("attach successful");
		} catch (RuntimeException re) {
			LOG.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Attach clean.
	 * 
	 * @param instance the instance
	 */
	public void attachClean(Book instance) {
		LOG.debug("attaching clean Book instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			LOG.debug("attach successful");
		} catch (RuntimeException re) {
			LOG.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * Gets the from application context.
	 * 
	 * @param ctx the ctx
	 * 
	 * @return the from application context
	 */
	public static BookDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BookDAO) ctx.getBean("BookDAO");
	}
}
