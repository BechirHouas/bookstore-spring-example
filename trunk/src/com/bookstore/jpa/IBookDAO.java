package com.bookstore.jpa;

import java.util.List;

/**
 * Interface for BookDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBookDAO {
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
	 * IBookDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to persist
	 * @throws RuntimeException when the operation fails
	 */
	public void save(Book entity);

	/**
	 * Delete a persistent Book entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IBookDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to delete
	 * @throws RuntimeException when the operation fails
	 */
	public void delete(Book entity);

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
	 * entity = IBookDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Book entity to update
	 * @return Book the persisted Book entity instance, may not be the same
	 * @throws RuntimeException if the operation fails
	 */
	public Book update(Book entity);

	public Book findById(Integer id);

	/**
	 * Find all Book entities with a specific property value.
	 * 
	 * @param propertyName the name of the Book property to query
	 * @param value the property value to match
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Book> found by query
	 */
	public List<Book> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);

	public List<Book> findByIsbn(Object isbn, int... rowStartIdxAndCount);

	public List<Book> findByTitle(Object title, int... rowStartIdxAndCount);

	public List<Book> findByImage(Object image, int... rowStartIdxAndCount);

	/**
	 * Find all Book entities.
	 * 
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Book> all Book entities
	 */
	public List<Book> findAll(int... rowStartIdxAndCount);
}
