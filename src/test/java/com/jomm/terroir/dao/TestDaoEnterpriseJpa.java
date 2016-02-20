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
	
	private static int LIST_INITIAL_SIZE = 2; // From UtilData.INSERT_BASIC_DATA

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoEnterpriseJpa();
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
			entity = TestEnterprise.generateEnterpriseWithIdNull();

			Long initialId = entity.getId();
			assertNull("Before persistence, id should be null", initialId);

			// FindAll
			assertNotNull("Before persistence, the list should not be null", dao.findAll());
			assertEquals("Before persistence, the list's size should be ", LIST_INITIAL_SIZE, dao.findAll().size());

			// Create
			UtilEntityManager.beginTransaction();
			Long persistedId = dao.create(entity).getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			// FindAll
			assertEquals("After persistence, the list's size should be ", LIST_INITIAL_SIZE+1, dao.findAll().size());

			// FindById
			Enterprise persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getLegalName(), 
					persistedEntity.getLegalName());
			assertNull("Entity with id=999999 should be null", dao.find((long) 999999));

			// Update
			String initialValue = persistedEntity.getLegalName();
			persistedEntity.setLegalName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getLegalName();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			dao.deleteById(persistedId);
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Delete
			UtilEntityManager.beginTransaction();
			dao.create(entity);
			assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
			dao.delete(entity);
			UtilEntityManager.commit();
			assertNull("After Delete, entity should be null", dao.find(entity.getId()));
			// FindAll
			assertEquals("After DeleteById, the list's size should be ", LIST_INITIAL_SIZE, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
}