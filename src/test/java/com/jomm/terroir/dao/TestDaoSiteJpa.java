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
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;

/**
 * This Class is a Junit test case testing {@link DaoSiteJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Site} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class TestDaoSiteJpa extends TestDaoGenericJpa<Site> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoSiteJpa();
		entity = TestSite.generateSite();
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
			EntityManager entityManager = UtilDao.prepareEntityManager();
			dao.setEntityManager(entityManager);
			
			Long initialId = entity.getId();
			assertNull("Before persistence, id should be null", initialId);

			// FindAll
			//			assertNotNull("Before persistence, the list should not be null", dao.findAll());
			//			assertTrue("Before persistence, the list should be empty", dao.findAll().isEmpty());
			//			assertEquals("Before persistence, the list's size should be 0", 0, dao.findAll().size());

			// Retrieve an Enterprise from DataBase
			Enterprise enterprise = findEnterpriseFromDataBase(entityManager);
			assertNotNull("Enterprise should not be null", enterprise);
			entity.setEnterprise(enterprise);
			
			// Create
			Long persistedId = dao.create(entity).getId();
			assertNotNull("After persistence, id should not be null", persistedId);
			// FindAll
			//			assertEquals("After persistence, the list's size should be 1", 1, dao.findAll().size());

			// FindById
			Site persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getSiteName(), 
					persistedEntity.getSiteName());

			// Update
			String initialValue = persistedEntity.getSiteName();
			persistedEntity.setSiteName("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getSiteName();
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
			UtilDao.closeEntityManager();
		}
	}
	
	/**
	 * Private method to retrieve an {@link Enterprise} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Enterprise} with <code>id=1</code>.
	 */
	private Enterprise findEnterpriseFromDataBase(EntityManager entityManager) {
		DaoEnterpriseJpa dao = new DaoEnterpriseJpa();
		dao.setEntityManager(entityManager);
		return dao.find((long) 1);
	}
}