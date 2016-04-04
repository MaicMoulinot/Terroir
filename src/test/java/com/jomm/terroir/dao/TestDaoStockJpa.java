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

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.business.model.TestStock;

/**
 * This Class is a Junit test case testing {@link DaoStockJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Stock} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoStockJpa extends TestDaoGenericJpa<Stock> {
	
	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoStockJpa();
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
			insertData(INSERT_PRODUCT);
			EntityManager entityManager = UtilEntityManager.prepareEntityManager();
			dao.entityManager = entityManager;
			
			// Construct Product and Stock
			entity = TestStock.generateStockWithIdNull();
			assertNull("Before persistence, id should be null", entity.getId());
			
			// FindAll
			List<Stock> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			int listInitialSize = list.size();
			
			// Retrieve a Product from DataBase
			Product product = TestDaoProductJpa.findProductFromDataBaseFirstCall(entityManager);
			assertNotNull("Product should not be null", product);
			entity.setProduct(product);
			product.setStock(entity);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", listInitialSize+1, dao.findAll().size());

			// FindById
			Stock persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getQuantity(), 
					persistedEntity.getQuantity());
			assertNull("Entity with id=999999 should be null", dao.find(NON_EXISTING_ENTITY_ID));

			// Update
			Integer initialValue = persistedEntity.getQuantity();
			persistedEntity.setQuantity(initialValue++);
			Integer updatedValue = dao.update(persistedEntity).getQuantity();
			assertNotEquals("Values should not match", initialValue, updatedValue);

			// DeleteById
			UtilEntityManager.beginTransaction();
			dao.deleteById(persistedId);
			UtilEntityManager.commit();
			// !!!
			// The delete operation is expected NOT TO work, 
			// as Stock is the owning side of the one-to-one relationship with Product
			// Product has to be deleted first
			// !!!
			assertNotNull("After DeleteById, persistedEntity should NOT be null", dao.find(persistedId));
			
			// Cascade
			assertNotNull("Without cascade delete, Product should not be null", 
					TestDaoProductJpa.findProductFromDataBaseFirstCall(entityManager));

			// Create
			entity = TestStock.generateStockWithIdNull();
			product = TestDaoProductJpa.findProductFromDataBaseSecondCall(entityManager);
			product.setStock(entity);
			entity.setProduct(product);
			assertNull("Before Create, id should be null", entity.getId());
			UtilEntityManager.beginTransaction();
			dao.create(entity);
			Long secondPersistedId = entity.getId();
			assertNotNull("After Create, id should not be null", secondPersistedId);
			
			// Delete
			assertNotNull("Before Delete, entity should not be null", dao.find(secondPersistedId));
			dao.delete(entity);
			UtilEntityManager.commit();
			// !!!
			// The delete operation is expected NOT TO work, 
			// as Stock is the owning side of the one-to-one relationship with Product
			// Product has to be deleted first
			// !!!
			assertNotNull("After Delete, entity should NOT be null", dao.find(secondPersistedId));
			
			// Cascade
			assertNotNull("Without cascade delete, Product should not be null", 
					TestDaoProductJpa.findProductFromDataBaseSecondCall(entityManager));
			
			// FindAll
			assertEquals("After Delete, the list's size should be", listInitialSize+2, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	// Static methods //------------------------------------------
	/**
	 * Retrieve a {@link Stock} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @param productId Long the Stock and Product's identifier.
	 * @return the {@link Stock}.
	 */
	public static Stock findStockFromDataBase(EntityManager entityManager, Long productId) {
		DaoStockJpa dao = new DaoStockJpa();
		dao.entityManager = entityManager;
		return dao.find(productId);
	}
}