package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Image;
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;

/**
 * This Class is a Junit test case testing {@link DaoSiteJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Site} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoSiteJpa extends TestDaoGenericJpa<Site> {
	
	// Test methods //--------------------------------------------
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
			insertData(sequenceOf(INSERT_CATEGORIES, INSERT_LABEL, INSERT_DESIGNATION, 
					INSERT_ENTERPRISES, INSERT_IMAGES));
			
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
			
			// Retrieve an Image from DataBase
			assertNotNull(entity.getImages());
			assertEquals(0, entity.getImages().size());
			Image image = TestDaoImageJpa.findImageFromDataBaseFirstCall(entityManager);
			assertNotNull("Image should not be null", image);
			List<Image> images = new ArrayList<>();
			images.add(image);
			entity.setImages(images);
			assertEquals(1, entity.getImages().size());
			
			// Retrieve an Designation from DataBase
			assertNotNull(entity.getDesignations());
			assertEquals(0, entity.getDesignations().size());
			Designation designation = TestDaoDesignationJpa.findDesignationFromDataBase(entityManager);
			assertNotNull("Designation should not be null", designation);
			List<Designation> designations = new ArrayList<>();
			designations.add(designation);
			entity.setDesignations(designations);
			assertEquals(1, entity.getDesignations().size());
			
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
			
			// Cascade
			assertNull("With cascade delete, Image should be null", 
					TestDaoImageJpa.findImageFromDataBaseFirstCall(entityManager));
			assertNotNull("Without cascade delete, Enterprise should not be null", 
					TestDaoEnterpriseJpa.findEnterpriseFromDataBase(entityManager));
			assertNotNull("Without cascade delete, Designation should not be null", 
					TestDaoDesignationJpa.findDesignationFromDataBase(entityManager));

			// Create
			entity = TestSite.generateSiteWithIdNull();
			entity.setEnterprise(enterprise);
			Image secondImage = TestDaoImageJpa.findImageFromDataBaseSecondCall(entityManager);
			assertNotNull("Image should not be null", secondImage);
			images = new ArrayList<>();
			images.add(secondImage);
			entity.setImages(images);
			designations = new ArrayList<>();
			designations.add(designation);
			entity.setDesignations(designations);
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
			assertNotNull("Without cascade delete, Enterprise should not be null", 
					TestDaoEnterpriseJpa.findEnterpriseFromDataBase(entityManager));
			assertNotNull("Without cascade delete, Designation should not be null", 
					TestDaoDesignationJpa.findDesignationFromDataBase(entityManager));
			
			// FindAll
			assertEquals("After Delete, the list's size should be", listInitialSize, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Test the {@link javax.persistence.ManyToMany} relationship between {@link Site} 
	 * and {@link com.jomm.terroir.business.model.Image}, 
	 * and the {@link javax.persistence.ManyToMany} relationship between {@link Site} 
	 * and {@link com.jomm.terroir.business.model.Designation}.
	 */
	@Test
	public final void testManyToManyWithImageAndDesignation() {
		try {
			insertData(sequenceOf(INSERT_CATEGORIES, INSERT_LABEL, INSERT_DESIGNATION, 
					INSERT_ENTERPRISES, INSERT_SITES));
			// EntityManager is working with test-specific Persistence Unit
			entity = findSiteFromDataBase(UtilEntityManager.prepareEntityManager());
			// Image
			assertNotNull("List of images should not be null", entity.getImages());
			assertEquals("List of images size should be", 1, entity.getImages().size());
			assertEquals("Image's id should be", IMAGE_FOR_SITE_ID, entity.getImages().get(0).getId());
			// Designation
			assertNotNull("List of designations should not be null", entity.getDesignations());
			assertEquals("List of designations size should be", 1, entity.getDesignations().size());
			assertEquals("Designation's id should be", EXISTING_DESIGNATION_ID, entity.getDesignations().get(0).getId());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	// Static methods //------------------------------------------
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