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

import com.jomm.terroir.business.model.Label;
import com.jomm.terroir.business.model.TestLabel;

/**
 * This Class is a Junit test case testing {@link DaoLabelJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Label} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoLabelJpa extends TestDaoGenericJpa<Label> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoLabelJpa();
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
			dao.entityManager = UtilEntityManager.prepareEntityManager();
			entity = TestLabel.generateLabelWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Label> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			int listInitialSize = list.size();
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", listInitialSize+1, dao.findAll().size());

			// FindById
			Label persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getName(), persistedEntity.getName());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getName();
			persistedEntity.setName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getName();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestLabel.generateLabelWithIdNull();
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
			assertEquals("After Delete, the list's size should be", listInitialSize, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
}