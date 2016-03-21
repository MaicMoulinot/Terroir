package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.business.model.TestDesignation;

/**
 * This Class is a Junit test case testing {@link DaoDesignationJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Designation} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoDesignationJpa extends TestDaoGenericJpa<Designation> {
	
	private static final int LIST_INITIAL_SIZE = 0; // From UtilData.INSERT_BASIC_DATA
	private static final long EXISTING_LABEL_ID = 111111; // From UtilData.INSERT_BASIC_DATA

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoDesignationJpa();
	}

	@Override
	@Test
	public final void testBehavior() {
		// EntityManager is not working, it is mocked
		dao.entityManager = mock(EntityManager.class);

		super.testBehavior();
	}

	@Override
	@Test
	public final void testState() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			EntityManager entityManager = UtilEntityManager.prepareEntityManager();
			dao.entityManager = entityManager;
			entity = TestDesignation.generateDesignationWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Designation> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			assertEquals("Before persistence, the list's size should be", LIST_INITIAL_SIZE, list.size());
			
			// Retrieve a label from DataBase
			Label label = findLabelFromDataBase(entityManager);
			assertNotNull("Enterprise should not be null", label);
			entity.setLabel(label);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", LIST_INITIAL_SIZE+1, dao.findAll().size());

			// FindById
			Designation persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getRegisteredName(), 
					persistedEntity.getRegisteredName());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getRegisteredName();
			persistedEntity.setRegisteredName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getRegisteredName();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestDesignation.generateDesignationWithIdNull();
			entity.setLabel(label);
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
	
	/**
	 * Private method to retrieve an {@link Label} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Label} with {@link TestDaoDesignationJpa#EXISTING_LABEL_ID}.
	 */
	private Label findLabelFromDataBase(EntityManager entityManager) {
		DaoLabelJpa dao = new DaoLabelJpa();
		dao.entityManager = entityManager;
		return dao.find(EXISTING_LABEL_ID);
	}
}