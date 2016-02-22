package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestCustomer;

/**
 * This Class is a Junit test case testing {@link DaoCustomerJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Customer} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class TestDaoCustomerJpa extends TestDaoGenericJpa<Customer> {
	
	private static final int LIST_INITIAL_SIZE = 0; // From UtilData.INSERT_BASIC_DATA
	private static final long NON_EXISTING_ENTITY_ID = 999999; // From UtilData.INSERT_BASIC_DATA

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoCustomerJpa();
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
		try {
			// EntityManager is working with test-specific Persistence Unit
			dao.setEntityManager(UtilEntityManager.prepareEntityManager());
			entity = TestCustomer.generateCustomerWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			assertNotNull("Before persistence, the list should not be null", dao.findAll());
			assertEquals("Before persistence, the list's size should be", LIST_INITIAL_SIZE, dao.findAll().size());
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", LIST_INITIAL_SIZE+1, dao.findAll().size());

			// FindById
			Customer persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getEmail(), persistedEntity.getEmail());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getEmail();
			persistedEntity.setEmail("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getEmail();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestCustomer.generateCustomerWithIdNull();
			assertNull("Before Create, id should be null", entity.getId());
			UtilEntityManager.beginTransaction();
			dao.create(entity);
			assertNotNull("After Create, id should not be null", entity.getId());
			
			// Delete
			assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
			dao.delete(entity);
			UtilEntityManager.commit();
			assertNull("After Delete, entity should be null", dao.find(entity.getId()));
			
			// FindAll
			assertEquals("After Delete, the list's size should be", LIST_INITIAL_SIZE, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
}