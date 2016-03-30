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
import com.jomm.terroir.business.model.TestImage;

/**
 * This Class is a Junit test case testing {@link DaoImageJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Image} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoImageJpa extends TestDaoGenericJpa<Image> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoImageJpa();
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
			entity = TestImage.generateImageWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Image> list = dao.findAll();
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
			Image persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getTitle(), persistedEntity.getTitle());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getTitle();
			persistedEntity.setTitle("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getTitle();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestImage.generateImageWithIdNull();
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
	 * Retrieve the {@link Image} from database filled with basic test data 
	 * with its identifier = {@link UtilData#EXISTING_IMAGE_ID_FIRST_CALL}.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Image}.
	 */
	public static Image findImageFromDataBaseFirstCall(EntityManager entityManager) {
		return findImageFromDataBase(entityManager, EXISTING_IMAGE_ID_FIRST_CALL);
	}
	
	/**
	 * Retrieve the {@link Image} from database filled with basic test data 
	 * with its identifier = {@link UtilData#EXISTING_IMAGE_ID_SECOND_CALL}.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Image}.
	 */
	public static Image findImageFromDataBaseSecondCall(EntityManager entityManager) {
		return findImageFromDataBase(entityManager, EXISTING_IMAGE_ID_SECOND_CALL);
	}
	
	/**
	 * Retrieve an {@link Image} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @param id Long the Image's identifier.
	 * @return the {@link Image}.
	 */
	private static Image findImageFromDataBase(EntityManager entityManager, Long id) {
		DaoImageJpa dao = new DaoImageJpa();
		dao.entityManager = entityManager;
		return dao.find(id);
	}
}