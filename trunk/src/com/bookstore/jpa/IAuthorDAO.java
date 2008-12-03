package com.bookstore.jpa;

import java.util.List;

/**
 * Interface for AuthorDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IAuthorDAO {
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
	 * IAuthorDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to persist
	 * @throws RuntimeException when the operation fails
	 */
	public void save(Author entity);

	/**
	 * Delete a persistent Author entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IAuthorDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to delete
	 * @throws RuntimeException when the operation fails
	 */
	public void delete(Author entity);

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
	 * entity = IAuthorDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity Author entity to update
	 * @return Author the persisted Author entity instance, may not be the same
	 * @throws RuntimeException if the operation fails
	 */
	public Author update(Author entity);

	public Author findById(Integer id);

	/**
	 * Find all Author entities with a specific property value.
	 * 
	 * @param propertyName the name of the Author property to query
	 * @param value the property value to match
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Author> found by query
	 */
	public List<Author> findByProperty(String propertyName, Object value, int... rowStartIdxAndCount);

	public List<Author> findByFirstName(Object firstName, int... rowStartIdxAndCount);

	public List<Author> findByLatName(Object latName, int... rowStartIdxAndCount);

	/**
	 * Find all Author entities.
	 * 
	 * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query
	 *            result-set to begin collecting the results. rowStartIdxAndCount[1] specifies the the maximum count of
	 *            results to return.
	 * @return List<Author> all Author entities
	 */
	public List<Author> findAll(int... rowStartIdxAndCount);
}
