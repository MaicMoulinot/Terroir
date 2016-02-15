package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.TestEnterprise;

/**
 * This Class is a Junit test case testing {@link DaoEnterpriseJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Enterprise} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class TestDaoEnterpriseJpa extends TestDaoGenericJpa<Enterprise> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoEnterpriseJpa();
		entity = TestEnterprise.generateEnterprise();
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
			EntityManager entityManager = UtilEntityManager.prepareEntityManager();
			dao.setEntityManager(entityManager);
			
			Long initialId = entity.getId();
			assertNull("Before persistence, id should be null", initialId);

			// FindAll
			//			assertNotNull("Before persistence, the list should not be null", dao.findAll());
			//			assertTrue("Before persistence, the list should be empty", dao.findAll().isEmpty());
			//			assertEquals("Before persistence, the list's size should be 0", 0, dao.findAll().size());
			
			// Create
			Long persistedId = dao.create(entity).getId();
			assertNotNull("After persistence, id should not be null", persistedId);
			// FindAll
			//			assertEquals("After persistence, the list's size should be 1", 1, dao.findAll().size());

			// FindById
			Enterprise persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getLegalName(), 
					persistedEntity.getLegalName());

			// Update
			String initialValue = persistedEntity.getLegalName();
			persistedEntity.setLegalName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getLegalName();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			dao.deleteById(persistedId);
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Delete
			dao.create(entity);
			assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
			dao.delete(entity);
			assertNull("After Delete, entity should be null", dao.find(entity.getId()));
		} catch (Exception exception) {
			assertNull(exception);
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
}