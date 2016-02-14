package com.jomm.terroir.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jomm.terroir.business.Product;
import com.jomm.terroir.business.ProductTest;
import com.jomm.terroir.business.Site;
import com.jomm.terroir.util.PersistenceTest;

/**
 * This Class is a Junit test case testing {@link ProductDaoJpa}.
 * It extends {@link GenericDaoTest} with {@link Product} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class ProductDaoJpaTest extends GenericDaoTest<Product> {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new ProductDaoJpa();
		entity = ProductTest.generateProduct();
	}

	@Override
	@Test
	public final void testBehavior() {
		// EntityManager is not working, it is mocked
		dao.setEntityManager(Mockito.mock(EntityManager.class));
		dbSetupTracker.skipNextLaunch();
		super.testBehavior();
	}

	@Override
	@Test
	public final void testState() {
		try {
			// EntityManager is working with test-specific Persistence Unit
			EntityManager entityManager = PersistenceTest.prepareEntityManager();
			dao.setEntityManager(entityManager);

			Long initialId = entity.getId();
			assertNull("Before persistence, id should be null", initialId);

			// FindAll
			//			assertNotNull("Before persistence, the list should not be null", dao.findAll());
			//			assertTrue("Before persistence, the list should be empty", dao.findAll().isEmpty());
			//			assertEquals("Before persistence, the list's size should be 0", 0, dao.findAll().size());
			
			// Retrieve a Site from DataBase
			Site site = findSiteFromDataBase(entityManager);
			assertNotNull("Site should not be null", site);
			entity.setSite(site);
			
			// Create
			Long persistedId = dao.create(entity).getId();
			assertNotNull("After persistence, id should not be null", persistedId);
			// FindAll
			//			assertEquals("After persistence, the list's size should be 1", 1, dao.findAll().size());

			// FindById
			Product persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getDescription(), 
					persistedEntity.getDescription());

			// Update
			String initialValue = persistedEntity.getDescription();
			persistedEntity.setDescription("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getDescription();
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
			PersistenceTest.closeEntityManager();
		}
	}
	
	/**
	 * Private method to retrieve an {@link Site} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Site} with <code>id=1</code>.
	 */
	private Site findSiteFromDataBase(EntityManager entityManager) {
		SiteDaoJpa dao = new SiteDaoJpa();
		dao.setEntityManager(entityManager);
		return dao.find((long) 3);
	}
}