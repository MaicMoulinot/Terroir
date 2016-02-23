package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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
	
	private static final int LIST_INITIAL_SIZE = 2; // From UtilData.INSERT_BASIC_DATA
	private static final long NON_EXISTING_ENTITY_ID = 999999; // From UtilData.INSERT_BASIC_DATA

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
			dao.setEntityManager(UtilEntityManager.prepareEntityManager());
			entity = TestEnterprise.generateEnterpriseWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Enterprise> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			assertEquals("Before persistence, the list's size should be", LIST_INITIAL_SIZE, list.size());
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", LIST_INITIAL_SIZE+1, dao.findAll().size());

			// FindById
			Enterprise persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getLegalName(), 
					persistedEntity.getLegalName());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getLegalName();
			persistedEntity.setLegalName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getLegalName();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestEnterprise.generateEnterpriseWithIdNull();
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