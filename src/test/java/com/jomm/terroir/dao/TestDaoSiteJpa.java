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

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;

/**
 * This Class is a Junit test case testing {@link DaoSiteJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Site} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoSiteJpa extends TestDaoGenericJpa<Site> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoSiteJpa();
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
			entity = TestSite.generateSiteWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Site> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			int listInitialSize = list.size();
			
			// Retrieve an Enterprise from DataBase
			Enterprise enterprise = TestDaoEnterpriseJpa.findEnterpriseFromDataBase(entityManager);
			assertNotNull("Enterprise should not be null", enterprise);
			entity.setEnterprise(enterprise);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", listInitialSize+1, dao.findAll().size());

			// FindById
			Site persistedEntity = dao.find(persistedId);
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
			entity = TestSite.generateSiteWithIdNull();
			entity.setEnterprise(enterprise);
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
	 * Test the {@link javax.persistence.ManyToMany} relationship between {@link Site} 
	 * and {@link com.jomm.terroir.business.model.Image}.
	 */
	@Test
	public final void testManyToManyWithImage() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			entity = findSiteFromDataBase(UtilEntityManager.prepareEntityManager());
			assertNotNull("List of images should not be null", entity.getImages());
			assertEquals("List of images size should be", 1, entity.getImages().size());
			assertEquals("Image's id should be", EXISTING_IMAGE_ID, entity.getImages().get(0).getId().longValue());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Retrieve a {@link Site} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Site} with {@link UtilData#EXISTING_SITE_ID}.
	 */
	public static Site findSiteFromDataBase(EntityManager entityManager) {
		DaoSiteJpa dao = new DaoSiteJpa();
		dao.entityManager = entityManager;
		return dao.find(EXISTING_SITE_ID);
	}
}