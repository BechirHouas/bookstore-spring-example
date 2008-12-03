package com.bookstore.jpa;

import java.util.List;
import java.util.Set;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICustomerDAO {
	/**
	 * Perform an initial save of a previously unsaved Customer entity. All subsequent persist actions of this entity
	 * should use the #update() method. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * ICustomerDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Customer entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Customer entity);

	/**
	 * Delete a persistent Customer entity. This operation must be performed within the a database transaction context
	 * for the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * ICustomerDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Customer entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Customer entity);

	/**
	 * Persist a previously saved Customer entity and return it or a copy of it to the sender. A copy of the Customer
	 * entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = ICustomerDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Customer entity to update
	 * @return Customer the persisted Customer entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Customer update(Customer entity);

	public Customer findById(Integer id);

	/**
	 * Find all Customer entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Customer property to query
	 * @param value
	 *            the property value to match
	 * @return List<Customer> found by query
	 */
	public List<Customer> findByProperty(String propertyName, Object value);

	public List<Customer> findByName(Object name);

	public List<Customer> findByAddress(Object address);

	public List<Customer> findByPhone(Object phone);

	/**
	 * Find all Customer entities.
	 * 
	 * @return List<Customer> all Customer entities
	 */
	public List<Customer> findAll();
}