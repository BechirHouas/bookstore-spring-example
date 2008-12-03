package com.bookstore.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Payment entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Payment
 * @author MyEclipse Persistence Tools
 */

public class PaymentDAO extends JpaDaoSupport implements IPaymentDAO {
	// property constants
	public static final String AMOUNT = "amount";

	/**
	 * Perform an initial save of a previously unsaved Payment entity. All subsequent persist actions of this entity
	 * should use the #update() method. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * PaymentDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Payment entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Payment entity) {
		logger.info("saving Payment instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Payment entity. This operation must be performed within the a database transaction context
	 * for the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * PaymentDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Payment entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Payment entity) {
		logger.info("deleting Payment instance");
		try {
			entity = getJpaTemplate().getReference(Payment.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Payment entity and return it or a copy of it to the sender. A copy of the Payment
	 * entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database transaction context for the entity's data to be
	 * permanently saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * entity = PaymentDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href = "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples< /a>
	 * @param entity
	 *            Payment entity to update
	 * @return Payment the persisted Payment entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Payment update(Payment entity) {
		logger.info("updating Payment instance");
		try {
			Payment result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Payment findById(Integer id) {
		logger.info("finding Payment instance with id: " + id);
		try {
			Payment instance = getJpaTemplate().find(Payment.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Payment entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Payment property to query
	 * @param value
	 *            the property value to match
	 * @return List<Payment> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Payment> findByProperty(String propertyName, final Object value) {
		logger.info("finding Payment instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Payment model where model." + propertyName
					+ "= :propertyValue";
			return getJpaTemplate().executeFind(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
				}
			});
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Payment> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	/**
	 * Find all Payment entities.
	 * 
	 * @return List<Payment> all Payment entities
	 */
	@SuppressWarnings("unchecked")
	public List<Payment> findAll() {
		logger.info("finding all Payment instances");
		try {
			final String queryString = "select model from Payment model";
			return getJpaTemplate().executeFind(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryString);
					return query.getResultList();
				}
			});
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	public static IPaymentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IPaymentDAO) ctx.getBean("PaymentDAO");
	}
}