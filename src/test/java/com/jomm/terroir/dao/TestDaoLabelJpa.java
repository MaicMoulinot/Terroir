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

import com.jomm.terroir.business.model.Image;
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
			EntityManager entityManager = UtilEntityManager.prepareEntityManager();
			dao.entityManager = entityManager;
			entity = TestLabel.generateLabelWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Label> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			int listInitialSize = list.size();
			
			// Retrieve an Image from DataBase
			Image image = TestDaoImageJpa.findImageFromDataBaseFirstCall(entityManager);
			assertNotNull("Image should not be null", image);
			entity.setLogo(image);
			
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
			
			// Cascade
			assertNull("With cascade delete, Image should be null", 
					TestDaoImageJpa.findImageFromDataBaseFirstCall(entityManager));

			// Create
			entity = TestLabel.generateLabelWithIdNull();
			Image secondImage = TestDaoImageJpa.findImageFromDataBaseSecondCall(entityManager);
			assertNotNull("Image should not be null", secondImage);
			entity.setLogo(secondImage);
			assertNull("Before Create, id should be null", entity.getId());
			UtilEntityManager.beginTransaction();
			dao.create(entity);
			assertNotNull("After Create, id should not be null", entity.getId());
			
			// Delete
			assertNotNull("Before Delete, entity should not be null", dao.find(entity.getId()));
			dao.delete(entity);
			UtilEntityManager.commit();
			assertNull("After Delete, entity should be null", dao.find(entity.getId()));
			
			// Cascade
			assertNull("With cascade delete, Image should be null", 
					TestDaoImageJpa.findImageFromDataBaseSecondCall(entityManager));
			
			// FindAll
			assertEquals("After Delete, the list's size should be", listInitialSize, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Test the {@link javax.persistence.ManyToMany} relationship between 
	 * {@link com.jomm.terroir.business.model.Designation} and {@link Label}.
	 */
	@Test
	public final void testManyToManyWithDesignation() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			entity = findLabelFromDataBase(UtilEntityManager.prepareEntityManager());
			assertNotNull("List of designations should not be null", entity.getDesignations());
			assertEquals("List of designations size should be", 1, entity.getDesignations().size());
			assertEquals("Designation's id should be", EXISTING_DESIGNATION_ID, 
					entity.getDesignations().get(0).getId().longValue());
			
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Retrieve a {@link Label} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Label} with {@link UtilData#EXISTING_LABEL_ID}.
	 */
	public static Label findLabelFromDataBase(EntityManager entityManager) {
		DaoLabelJpa dao = new DaoLabelJpa();
		dao.entityManager = entityManager;
		return dao.find(EXISTING_LABEL_ID);
	}
}