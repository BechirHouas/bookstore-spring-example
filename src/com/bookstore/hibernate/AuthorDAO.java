package com.bookstore.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Author entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.hibernate.Author
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class AuthorDAO extends HibernateDaoSupport {
	private static final Log LOG = LogFactory.getLog(AuthorDAO.class);

	// property constants
	public static final String FIRST_NAME = "firstName";

	public static final String LAT_NAME = "lastName";

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
	public void save(Author transientInstance) {
		LOG.debug("saving Author instance");
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
	public void delete(Author persistentInstance) {
		LOG.debug("deleting Author instance");
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
	 * @return the author
	 */
	public Author findById(java.lang.Integer id) {
		LOG.debug("getting Author instance with id: " + id);
		try {
			Author instance = (Author) getHibernateTemplate().get("com.bookstore.hibernate.Author", id);
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
	 * @return the list< author>
	 */
	public List<Author> findByExample(Author instance) {
		LOG.debug("finding Author instance by example");
		try {
			List<Author> results = (List<Author>) getHibernateTemplate().findByExample(instance);
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
		LOG.debug("finding Author instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Author as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * Find by first name.
	 * 
	 * @param firstName the first name
	 * 
	 * @return the list< author>
	 */
	public List<Author> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	/**
	 * Find by last name.
	 * 
	 * @param lastName the last name
	 * 
	 * @return the list< author>
	 */
	public List<Author> findByLastName(Object lastName) {
		return findByProperty(LAT_NAME, lastName);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List findAll() {
		LOG.debug("finding all Author instances");
		try {
			String queryString = "from Author";
			return getHibernateTemplate().find(queryString);
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
	 * @return the author
	 */
	public Author merge(Author detachedInstance) {
		LOG.debug("merging Author instance");
		try {
			Author result = (Author) getHibernateTemplate().merge(detachedInstance);
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
	public void attachDirty(Author instance) {
		LOG.debug("attaching dirty Author instance");
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
	public void attachClean(Author instance) {
		LOG.debug("attaching clean Author instance");
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
	public static AuthorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AuthorDAO) ctx.getBean("AuthorDAO");
	}
}
