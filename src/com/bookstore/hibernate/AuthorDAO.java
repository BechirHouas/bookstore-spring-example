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

public class AuthorDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AuthorDAO.class);

	// property constants
	public static final String FIRST_NAME = "firstName";

	public static final String LAT_NAME = "lastName";

	protected void initDao() {
		// do nothing
	}

	public void save(Author transientInstance) {
		log.debug("saving Author instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Author persistentInstance) {
		log.debug("deleting Author instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Author findById(java.lang.Integer id) {
		log.debug("getting Author instance with id: " + id);
		try {
			Author instance = (Author) getHibernateTemplate().get("com.bookstore.hibernate.Author", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Author> findByExample(Author instance) {
		log.debug("finding Author instance by example");
		try {
			List<Author> results = (List<Author>) getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Author instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Author as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Author> findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List<Author> findByLastName(Object lastName) {
		return findByProperty(LAT_NAME, lastName);
	}

	public List findAll() {
		log.debug("finding all Author instances");
		try {
			String queryString = "from Author";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Author merge(Author detachedInstance) {
		log.debug("merging Author instance");
		try {
			Author result = (Author) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Author instance) {
		log.debug("attaching dirty Author instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Author instance) {
		log.debug("attaching clean Author instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AuthorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AuthorDAO) ctx.getBean("AuthorDAO");
	}
}
