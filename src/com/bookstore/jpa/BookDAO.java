package com.bookstore.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Book entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Book
 * @author MyEclipse Persistence Tools
 */

public class BookDAO extends JpaDaoSupport implements IBookDAO {
	// property constants
	public static final String ISBN = "isbn";

	public static final String TITLE = "title";

	public static final String IMAGE = "image";

	/**
	 * Perform an initial save of a previously unsaved Book entity. All subsequent persist actions of this entity should
	 * use the #update() method. This operation must be performed within the a database transaction context for the
	 * entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * BookDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to persist
	 * @throws RuntimeException when the operation fails
	 */
	public void save(Book entity) {
		logger.info("saving Book instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Book entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * BookDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to delete
	 * @throws RuntimeException when the operation fails
	 */
	public void delete(Book entity) {
		logger.info("deleting Book instance");
		try {
			entity = getJpaTemplate().getReference(Book.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Book entity and return it or a copy of it to the sender. A copy of the Book entity
	 * parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity.
	 * This operation must be performed within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = BookDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to update
	 * @return Book the persisted Book entity instance, may not be the same
	 * @throws RuntimeException if the operation fails
	 */
	public Book update(Book entity) {
		logger.info("updating Book instance");
		try {
			Book result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Book findById(Integer id) {
		logger.info("finding Book instance with id: " + id);
		try {
			Book instance = getJpaTemplate().find(Book.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Book entities with a specific property value.
	 * 
	 * @param propertyName the name of the Book property to query
	 * @param value the property value to match
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<Book> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding Book instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Book model where model." + propertyName + "= :propertyValue";
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

	public List<Book> findByIsbn(Object isbn, int... rowStartIdxAndCount) {
		return findByProperty(ISBN, isbn, rowStartIdxAndCount);
	}

	public List<Book> findByTitle(Object title, int... rowStartIdxAndCount) {
		return findByProperty(TITLE, title, rowStartIdxAndCount);
	}

	public List<Book> findByImage(Object image, int... rowStartIdxAndCount) {
		return findByProperty(IMAGE, image, rowStartIdxAndCount);
	}

	/**
	 * Find all Book entities.
	 * 
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Book> all Book entities
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all Book instances");
		try {
			final String queryString = "select model from Book model";
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
	
	/**
	 * Find all Book entities.
	 * 
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Book> latest Book entities
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findLatest(final int... rowStartIdxAndCount) {
		logger.info("finding all Book instances");
		try {
			final String queryString = "select model from Book model order by model.createdDate desc";
			return getJpaTemplate().executeFind(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryString);
					query.setMaxResults(3);
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

	public static IBookDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IBookDAO) ctx.getBean("BookDAO");
	}
}
