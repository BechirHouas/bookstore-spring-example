package com.bookstore.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Author entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Author
 * @author MyEclipse Persistence Tools
 */

public class AuthorDAO extends JpaDaoSupport implements IAuthorDAO {
	// property constants
	public static final String FIRST_NAME = "firstName";

	public static final String LAT_NAME = "latName";

	/**
	 * Perform an initial save of a previously unsaved Author entity. All subsequent persist actions of this entity
	 * should use the #update() method. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * AuthorDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to persist
	 * @throws RuntimeException when the operation fails
	 */
	public void save(Author entity) {
		logger.info("saving Author instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Author entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * AuthorDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to delete
	 * @throws RuntimeException when the operation fails
	 */
	public void delete(Author entity) {
		logger.info("deleting Author instance");
		try {
			entity = getJpaTemplate().getReference(Author.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Author entity and return it or a copy of it to the sender. A copy of the Author entity
	 * parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity.
	 * This operation must be performed within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = AuthorDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to update
	 * @return Author the persisted Author entity instance, may not be the same
	 * @throws RuntimeException if the operation fails
	 */
	public Author update(Author entity) {
		logger.info("updating Author instance");
		try {
			Author result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Author findById(Integer id) {
		logger.info("finding Author instance with id: " + id);
		try {
			Author instance = getJpaTemplate().find(Author.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Author entities with a specific property value.
	 * 
	 * @param propertyName the name of the Author property to query
	 * @param value the property value to match
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<Author> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Author> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding Author instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Author model where model." + propertyName
				+ "= :propertyValue";
			return getJpaTemplate().executeFind(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryString);
					query.setParameter("propertyValue", value);
					if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
						int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
						if (rowStartIdx > 0) {
							query.setFirstResult(rowStartIdx);
						}

						if (rowStartIdxAndCount.length > 1) {
							int rowCount = Math.max(0, rowStartIdxAndCount[1]);
							if (rowCount > 0) {
								query.setMaxResults(rowCount);
							}
						}
					}
					return query.getResultList();
				}
			});
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Author> findByFirstName(Object firstName, int... rowStartIdxAndCount) {
		return findByProperty(FIRST_NAME, firstName, rowStartIdxAndCount);
	}

	public List<Author> findByLatName(Object latName, int... rowStartIdxAndCount) {
		return findByProperty(LAT_NAME, latName, rowStartIdxAndCount);
	}

	/**
	 * Find all Author entities.
	 * 
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Author> all Author entities
	 */
	@SuppressWarnings("unchecked")
	public List<Author> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all Author instances");
		try {
			final String queryString = "select model from Author model";
			return getJpaTemplate().executeFind(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryString);
					if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
						int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
						if (rowStartIdx > 0) {
							query.setFirstResult(rowStartIdx);
						}

						if (rowStartIdxAndCount.length > 1) {
							int rowCount = Math.max(0, rowStartIdxAndCount[1]);
							if (rowCount > 0) {
								query.setMaxResults(rowCount);
							}
						}
					}
					return query.getResultList();
				}
			});
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static IAuthorDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IAuthorDAO) ctx.getBean("AuthorDAO");
	}
}
