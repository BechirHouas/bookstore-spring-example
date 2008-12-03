package com.bookstore.jpa;

import java.util.List;

/**
 * Interface for BillDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IBillDAO {
	/**
	 * Perform an initial save of a previously unsaved Bill entity. All subsequent persist actions of this entity should
	 * use the #update() method. This operation must be performed within the a database transaction context for the
	 * entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IBillDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Bill entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Bill entity);

	/**
	 * Delete a persistent Bill entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * IBillDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Bill entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Bill entity);

	/**
	 * Persist a previously saved Bill entity and return it or a copy of it to the sender. A copy of the Bill entity
	 * parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity.
	 * This operation must be performed within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = IBillDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Bill entity to update
	 * @return Bill the persisted Bill entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Bill update(Bill entity);

	public Bill findById(Integer id);

	/**
	 * Find all Bill entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Bill property to query
	 * @param value
	 *            the property value to match
	 * @return List<Bill> found by query
	 */
	public List<Bill> findByProperty(String propertyName, Object value);

	public List<Bill> findByAmount(Object amount);

	public List<Bill> findByReason(Object reason);

	/**
	 * Find all Bill entities.
	 * 
	 * @return List<Bill> all Bill entities
	 */
	public List<Bill> findAll();
}