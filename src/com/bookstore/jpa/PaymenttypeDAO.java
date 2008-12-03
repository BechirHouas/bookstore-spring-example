package com.bookstore.jpa;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for Paymenttype entities. Transaction control of
 * the save(), update() and delete() operations can directly support Spring container-managed transactions or they can
 * be augmented to handle user-managed Spring transactions. Each of these methods provides additional information for
 * how to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Paymenttype
 * @author MyEclipse Persistence Tools
 */

public class PaymenttypeDAO extends JpaDaoSupport implements IPaymenttypeDAO {
	// property constants
	public static final String NAME = "name";

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
	 * PaymenttypeDAO.save(entity);
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
	public void save(Paymenttype entity) {
		logger.info("saving Paymenttype instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Paymenttype entity. This operation must be performed within the a database transaction
	 * context for the entity's data to be permanently deleted from the persistence store, i.e., database. This method
	 * uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * PaymenttypeDAO.delete(entity);
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
	public void delete(Paymenttype entity) {
		logger.info("deleting Paymenttype instance");
		try {
			entity = getJpaTemplate().getReference(Paymenttype.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

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
	 * entity = PaymenttypeDAO.update(entity);
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
	public Paymenttype update(Paymenttype entity) {
		logger.info("updating Paymenttype instance");
		try {
			Paymenttype result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Paymenttype findById(Integer id) {
		logger.info("finding Paymenttype instance with id: " + id);
		try {
			Paymenttype instance = getJpaTemplate().find(Paymenttype.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Paymenttype entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Paymenttype property to query
	 * @param value
	 *            the property value to match
	 * @return List<Paymenttype> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Paymenttype> findByProperty(String propertyName, final Object value) {
		logger.info("finding Paymenttype instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Paymenttype model where model." + propertyName
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

	public List<Paymenttype> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/**
	 * Find all Paymenttype entities.
	 * 
	 * @return List<Paymenttype> all Paymenttype entities
	 */
	@SuppressWarnings("unchecked")
	public List<Paymenttype> findAll() {
		logger.info("finding all Paymenttype instances");
		try {
			final String queryString = "select model from Paymenttype model";
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

	public static IPaymenttypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IPaymenttypeDAO) ctx.getBean("PaymenttypeDAO");
	}
}