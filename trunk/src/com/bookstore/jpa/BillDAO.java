package com.bookstore.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Bill entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Bill
 * @author MyEclipse Persistence Tools
 */

public class BillDAO extends JpaDaoSupport implements IBillDAO {
	// property constants
	public static final String AMOUNT = "amount";
	public static final String REASON = "reason";

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
	 * BillDAO.save(entity);
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
	public void save(Bill entity) {
		logger.info("saving Bill instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Bill entity. This operation must be performed within the a database transaction context for
	 * the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * BillDAO.delete(entity);
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
	public void delete(Bill entity) {
		logger.info("deleting Bill instance");
		try {
			entity = getJpaTemplate().getReference(Bill.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

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
	 * entity = BillDAO.update(entity);
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
	public Bill update(Bill entity) {
		logger.info("updating Bill instance");
		try {
			Bill result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Bill findById(Integer id) {
		logger.info("finding Bill instance with id: " + id);
		try {
			Bill instance = getJpaTemplate().find(Bill.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Bill entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Bill property to query
	 * @param value
	 *            the property value to match
	 * @return List<Bill> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Bill> findByProperty(String propertyName, final Object value) {
		logger.info("finding Bill instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Bill model where model." + propertyName + "= :propertyValue";
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

	public List<Bill> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List<Bill> findByReason(Object reason) {
		return findByProperty(REASON, reason);
	}

	/**
	 * Find all Bill entities.
	 * 
	 * @return List<Bill> all Bill entities
	 */
	@SuppressWarnings("unchecked")
	public List<Bill> findAll() {
		logger.info("finding all Bill instances");
		try {
			final String queryString = "select model from Bill model";
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

	public static IBillDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IBillDAO) ctx.getBean("BillDAO");
	}
}