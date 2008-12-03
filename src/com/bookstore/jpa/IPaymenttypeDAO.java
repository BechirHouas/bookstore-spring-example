package com.bookstore.jpa;

import java.util.List;
import java.util.Set;

/**
 * Interface for PaymenttypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPaymenttypeDAO {
	/**
	 * Perform an initial save of a previously unsaved Paymenttype entity. All subsequent persist actions of this entity
	 * should use the #update() method. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IPaymenttypeDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Paymenttype entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Paymenttype entity);

	/**
	 * Delete a persistent Paymenttype entity. This operation must be performed within the a database transaction
	 * context for the entity's data to be permanently deleted from the persistence store, i.e., database. This method
	 * uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IPaymenttypeDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Paymenttype entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Paymenttype entity);

	/**
	 * Persist a previously saved Paymenttype entity and return it or a copy of it to the sender. A copy of the
	 * Paymenttype entity parameter is returned when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = IPaymenttypeDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Paymenttype entity to update
	 * @return Paymenttype the persisted Paymenttype entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Paymenttype update(Paymenttype entity);

	public Paymenttype findById(Integer id);

	/**
	 * Find all Paymenttype entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Paymenttype property to query
	 * @param value
	 *            the property value to match
	 * @return List<Paymenttype> found by query
	 */
	public List<Paymenttype> findByProperty(String propertyName, Object value);

	public List<Paymenttype> findByName(Object name);

	/**
	 * Find all Paymenttype entities.
	 * 
	 * @return List<Paymenttype> all Paymenttype entities
	 */
	public List<Paymenttype> findAll();
}