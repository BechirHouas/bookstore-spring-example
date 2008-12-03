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
 * A data access object (DAO) providing persistence and search support for Customer entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bookstore.jpa.Customer
 * @author MyEclipse Persistence Tools
 */

public class CustomerDAO extends JpaDaoSupport implements ICustomerDAO {
	// property constants
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String PHONE = "phone";

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
	 * CustomerDAO.save(entity);
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
	public void save(Customer entity) {
		logger.info("saving Customer instance");
		try {
			getJpaTemplate().persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Customer entity. This operation must be performed within the a database transaction context
	 * for the entity's data to be permanently deleted from the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager.getTransaction(new DefaultTransactionDefinition());
	 * CustomerDAO.delete(entity);
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
	public void delete(Customer entity) {
		logger.info("deleting Customer instance");
		try {
			entity = getJpaTemplate().getReference(Customer.class, entity.getId());
			getJpaTemplate().remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

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
	 * entity = CustomerDAO.update(entity);
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
	public Customer update(Customer entity) {
		logger.info("updating Customer instance");
		try {
			Customer result = getJpaTemplate().merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Customer findById(Integer id) {
		logger.info("finding Customer instance with id: " + id);
		try {
			Customer instance = getJpaTemplate().find(Customer.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	/**
	 * Find all Customer entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Customer property to query
	 * @param value
	 *            the property value to match
	 * @return List<Customer> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findByProperty(String propertyName, final Object value) {
		logger.info("finding Customer instance with property: " + propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Customer model where model." + propertyName
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

	public List<Customer> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Customer> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Customer> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	/**
	 * Find all Customer entities.
	 * 
	 * @return List<Customer> all Customer entities
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		logger.info("finding all Customer instances");
		try {
			final String queryString = "select model from Customer model";
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

	public static ICustomerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ICustomerDAO) ctx.getBean("CustomerDAO");
	}
}