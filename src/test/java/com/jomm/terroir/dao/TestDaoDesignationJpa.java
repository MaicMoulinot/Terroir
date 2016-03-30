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

import com.jomm.terroir.business.model.Category;
import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Image;
import com.jomm.terroir.business.model.TestDesignation;

/**
 * This Class is a Junit test case testing {@link DaoDesignationJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Designation} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoDesignationJpa extends TestDaoGenericJpa<Designation> {

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
			int listInitialSize = list.size();
			
			// Retrieve a Category from DataBase
			Category category = TestDaoCategoryJpa.findCategoryFromDataBase(entityManager);
			assertNotNull("Category should not be null", category);
			entity.setCategory(category);
			
			// Retrieve an Image from DataBase
			Image image = TestDaoImageJpa.findImageFromDataBaseFirstCall(entityManager);
			assertNotNull("Image should not be null", image);
			entity.setLogo(image);
			entity.setPicture(image);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", listInitialSize+1, dao.findAll().size());

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
			Image secondImage = TestDaoImageJpa.findImageFromDataBaseSecondCall(entityManager);
			assertNotNull("Image should not be null", secondImage);
			entity.setCategory(category);
			entity.setLogo(secondImage);
			entity.setPicture(secondImage);
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
	
	/**
	 * Test the {@link javax.persistence.ManyToMany} relationship between {@link Designation} 
	 * and {@link com.jomm.terroir.business.model.Label}.
	 */
	@Test
	public final void testManyToManyWithLabel() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			entity = findDesignationFromDataBase(UtilEntityManager.prepareEntityManager());
			assertNotNull("List of labels should not be null", entity.getLabels());
			assertEquals("List of labels size should be", 1, entity.getLabels().size());
			assertEquals("Label's id should be", EXISTING_LABEL_ID, entity.getLabels().get(0).getId().longValue());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Retrieve a {@link Designation} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Designation} with {@link UtilData#EXISTING_DESIGNATION_ID}.
	 */
	public static Designation findDesignationFromDataBase(EntityManager entityManager) {
		DaoDesignationJpa dao = new DaoDesignationJpa();
		dao.entityManager = entityManager;
		return dao.find(EXISTING_DESIGNATION_ID);
	}
}