package com.bookstore.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bookstore.hibernate.User
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class UserDAO extends HibernateDaoSupport {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(UserDAO.class);

	// property constants
	/** The Constant FIRST_NAME. */
	public static final String FIRST_NAME = "firstName";

	/** The Constant LAST_NAME. */
	public static final String LAST_NAME = "lastName";

	/** The Constant EMAIL. */
	public static final String EMAIL = "email";

	/** The Constant PASSWORD. */
	public static final String PASSWORD = "password";

	/**
	 * @see org.springframework.dao.support.DaoSupport#initDao()
	 */
	protected void initDao() {
		// do nothing
	}

	/**
	 * Save.
	 * 
	 * @param transientInstance
	 *            the transient instance
	 */
	public void save(User transientInstance) {
		LOG.debug("saving User instance");
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
	 * @param persistentInstance
	 *            the persistent instance
	 */
	public void delete(User persistentInstance) {
		LOG.debug("deleting User instance");
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
	 * @param id
	 *            the id
	 * 
	 * @return the user
	 */
	public User findById(java.lang.Integer id) {
		LOG.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get("com.bookstore.hibernate.User", id);
			return instance;
		} catch (RuntimeException re) {
			LOG.error("get failed", re);
			throw re;
		}
	}

	/**
	 * Find by example.
	 * 
	 * @param instance
	 *            the instance
	 * 
	 * @return the list< user>
	 */
	public List<User> findByExample(User instance) {
		LOG.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) getHibernateTemplate().findByExample(instance);
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
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * 
	 * @return the list
	 */
	public List findByProperty(String propertyName, Object value) {
		LOG.debug("finding User instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from User as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			LOG.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * Find by first name.
	 * 
	 * @param firstName
	 *            the first name
	 * 
	 * @return the list< user>
	 */
	public List<User> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	/**
	 * Find by last name.
	 * 
	 * @param lastName
	 *            the last name
	 * 
	 * @return the list< user>
	 */
	public List<User> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	/**
	 * Find by email.
	 * 
	 * @param email
	 *            the email
	 * 
	 * @return the list< user>
	 */
	public List<User> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	/**
	 * Find by password.
	 * 
	 * @param password
	 *            the password
	 * 
	 * @return the list< user>
	 */
	public List<User> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List findAll() {
		LOG.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOG.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * Merge.
	 * 
	 * @param detachedInstance
	 *            the detached instance
	 * 
	 * @return the user
	 */
	public User merge(User detachedInstance) {
		LOG.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
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
	 * @param instance
	 *            the instance
	 */
	public void attachDirty(User instance) {
		LOG.debug("attaching dirty User instance");
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
	 * @param instance
	 *            the instance
	 */
	public void attachClean(User instance) {
		LOG.debug("attaching clean User instance");
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
	 * @param ctx
	 *            the ctx
	 * 
	 * @return the from application context
	 */
	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
}
