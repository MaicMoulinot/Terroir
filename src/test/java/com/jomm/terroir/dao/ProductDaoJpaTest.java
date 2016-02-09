package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.ProductTest;

/**
 * This Class is a Junit test case testing {@link ProductDaoJpa}.
 * It extends {@link GenericDaoTest} with {@link Product} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class ProductDaoJpaTest extends GenericDaoTest<Product> {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new ProductDaoJpa();
		entity = ProductTest.generateProduct();
	}
	
	@Override
	@Test
	public final void testBehavior() {
		// EntityManager is not working, it is mocked
		dao.setEntityManager(Mockito.mock(EntityManager.class));
		
		super.testBehavior();
	}
	
	@Override
	@Test
	public final void testState() {
		// EntityManager is working with "testPU"
		EntityManagerFactory truc = Persistence.createEntityManagerFactory("testPU");
		EntityManager entityManager = truc.createEntityManager();
		dao.setEntityManager(entityManager);
		
		Long initialId = entity.getId();
		assertNull("Before persistence, id should be null", initialId);
		
		// FindAll
		assertNotNull("Before persistence, the list should not be null", dao.findAll());
		assertTrue("Before persistence, the list should be empty", dao.findAll().isEmpty());
		assertEquals("Before persistence, the list's size should be 0", 0, dao.findAll().size());
		
		// Create
		Long persistedId = dao.create(entity).getId();
		assertNotNull("After persistence, id should not be null", persistedId);
		//TODO assertEquals("After persistence, the list's size should be 1", 1, dao.findAll().size());
		
		// FindById
		Product persistedEntity = dao.find(persistedId);
		assertNotNull("After persistence, entity should not be null", persistedEntity);
		assertEquals("After persistence, properties should be equal", entity.getDescription(), 
				persistedEntity.getDescription());
		
		// Update
		String initialValue = persistedEntity.getDescription();
		persistedEntity.setDescription("UpdatedValue");
		String updatedValue = dao.update(persistedEntity).getDescription();
		assertNotEquals("Values should not match", initialValue, updatedValue);

		// DeleteById
		dao.deleteById(persistedId);
		assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));
		
		// Delete
		entity = ProductTest.generateProduct();
		dao.create(entity);
		assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
		dao.delete(entity);
		assertNull("After Delete, entity should be null", dao.find(entity.getId()));
		
		entityManager.close();
		truc.close();
		entityManager = null;
		truc = null;
	}
}