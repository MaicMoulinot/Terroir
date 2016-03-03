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
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestSeller;

/**
 * This Class is a Junit test case testing {@link DaoSellerJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Seller} as parameter, 
 * and implements <code>testBehavior()</code> and <code>testState()</code>.
 * @author Maic
 */
public class TestDaoSellerJpa extends TestDaoGenericJpa<Seller> {
	
	private static final int LIST_INITIAL_SIZE = 0; // From UtilData.INSERT_BASIC_DATA
	private static final long EXISTING_ENTERPRISE_ID = 111111; // From UtilData.INSERT_BASIC_DATA

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoSellerJpa();
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
			entity = TestSeller.generateSellerWithIdNull();

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Seller> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			assertEquals("Before persistence, the list's size should be", LIST_INITIAL_SIZE, list.size());
			
			// Retrieve an Enterprise from DataBase
			Enterprise enterprise = findEnterpriseFromDataBase(entityManager);
			assertNotNull("Enterprise should not be null", enterprise);
			entity.setEnterprise(enterprise);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", LIST_INITIAL_SIZE+1, dao.findAll().size());

			// FindById
			Seller persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getEmail(), persistedEntity.getEmail());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			String initialValue = persistedEntity.getEmail();
			persistedEntity.setEmail("UpdatedValue");
			String updatedValue = dao.update(persistedEntity).getEmail();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			assertNull("After DeleteById, persistedEntity should be null", dao.find(persistedId));

			// Create
			entity = TestSeller.generateSellerWithIdNull();
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
			assertEquals("After Delete, the list's size should be", LIST_INITIAL_SIZE, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	/**
	 * Private method to retrieve an {@link Enterprise} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Enterprise} with <code>id=ENTERPRISE_ID</code>.
	 */
	private Enterprise findEnterpriseFromDataBase(EntityManager entityManager) {
		DaoEnterpriseJpa dao = new DaoEnterpriseJpa();
		dao.entityManager = entityManager;
		return dao.find(EXISTING_ENTERPRISE_ID);
	}
}