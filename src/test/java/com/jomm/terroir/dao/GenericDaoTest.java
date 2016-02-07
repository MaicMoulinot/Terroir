package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.business.AbstractUser;
import com.jomm.terroir.business.Admin;
import com.jomm.terroir.business.Customer;
import com.jomm.terroir.business.Enterprise;
import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.Seller;
import com.jomm.terroir.business.Site;

/**
 * This Class is a Junit test case testing the methods of {@link GenericDao}.
 * Practically, it verifies that each DAO method properly calls the appropriate {@link EntityManager}'s method.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link GenericDao}.
 * @author Maic
 * @param <E> {@link javax.persistence.Entity} is the Entity's type, which extends {@link Serializable}.
 */
@RunWith(Parameterized.class)
public class GenericDaoTest<E extends Serializable> {
	
	private GenericDao<E> dao;
	private E entity;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link GenericDaoTest#childToTest()}.
	 * @param dao the concrete child of {@link GenericDao}.
	 * @param entity the {@link javax.persistence.Entity}.
	 */
    public GenericDaoTest(GenericDao<E> dao, E entity) {
    	this.entity = entity;
        this.dao = dao;
        // Mock the EntityManager
        this.dao.setEntityManager(Mockito.mock(EntityManager.class));
    }
	/**
	 * Test method for {@link GenericDao#GenericDao()}.
	 */
	@Test
	public final void testGenericDao() {
		assertEquals(dao.getEntityClass(), entity);
	}

	/**
	 * Test method for {@link GenericDao#create(Serializable)}.
	 */
	@Test
	public final void testCreate() {
		dao.create(entity);
		// validate that entityManager.persist() was called
		verify(dao.getEntityManager()).persist(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#update(Serializable)}.
	 */
	@Test
	public final void testUpdate() {
		dao.update(entity);
		// validate that entityManager.merge() was called
		verify(dao.getEntityManager()).merge(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#delete(Serializable)}.
	 */
	@Test
	public final void testDelete() {
		dao.delete(entity);
		// validate that entityManager.remove() was called
		verify(dao.getEntityManager()).remove(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#deleteById(Long)}.
	 */
	@Test
	public final void testDeleteById() {
		dao.deleteById((long) 0);
		// validate that entityManager.remove() was called
		verify(dao.getEntityManager()).remove(any(Serializable.class));
	}

	/**
	 * Test method for {@link GenericDao#find(java.lang.Long)}.
	 */
	@Test
	public final void testFind() {
		dao.find((long) 0);
		// validate that entityManager.find() was called
		verify(dao.getEntityManager()).find(dao.getEntityClass(), (long) 0);
	}

	/**
	 * Test method for {@link GenericDao#findAll()}.
	 */
	@Test
	public final void testFindAll() {
		dao.findAll();
		verify(dao.getEntityManager()).createNamedQuery(any(String.class)); // validate that entityManager.createNamedQuery() was called
	}

	/**
	 * Test method for {@link GenericDao#getEntityClass()} and {@link GenericDao#setEntityClass(E)}.
	 */
	@Test
	@SuppressWarnings("unchecked")
	public final void testGetterSetterEntityClass() {
		Class<E> initialEntityClass = dao.getEntityClass();
		Class<E> updatedEntityClass = (Class<E>) Admin.class; // A random Entity 
		if (Objects.equals(initialEntityClass, updatedEntityClass)) {
			updatedEntityClass = (Class<E>) Customer.class; // for the case Admin, pick an other random Entity
		}
		dao.setEntityClass(updatedEntityClass);
		assertNotEquals("EntityClass should be " + updatedEntityClass, initialEntityClass, dao.getEntityClass());
		dao.setEntityClass(initialEntityClass);
		assertEquals("EntityClass should be " + initialEntityClass, initialEntityClass, dao.getEntityClass());
	}
	
	/**
	 * Reference a list of all {@link GenericDao}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new AdminDaoJpa(), Admin.class},
			{new CustomerDaoJpa(), Customer.class},
			{new EnterpriseDaoJpa(), Enterprise.class},
			{new ProductDaoJpa(), Product.class},
			{new SellerDaoJpa(), Seller.class},
			{new SiteDaoJpa(), Site.class},
			{new UserDaoJpa(), AbstractUser.class}
			}
		);
	}
}