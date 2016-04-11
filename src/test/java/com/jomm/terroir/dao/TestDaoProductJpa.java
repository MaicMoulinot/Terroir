package com.jomm.terroir.dao;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.model.Designation;
import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.business.model.TestStock;

/**
 * This Class is a Junit test case testing {@link DaoProductJpa}.
 * It extends {@link TestDaoGenericJpa} with {@link Product} as parameter, 
 * and implements {@code testBehavior()} and {@code testState()}.
 * @author Maic
 */
public class TestDaoProductJpa extends TestDaoGenericJpa<Product> {
	
	// Test methods //--------------------------------------------
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dao = new DaoProductJpa();
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
			
			// Construct Product and Stock
			insertData(sequenceOf(INSERT_CATEGORIES, INSERT_LABEL, INSERT_DESIGNATION, 
					INSERT_ENTERPRISES, INSERT_SITES));
			entity = TestProduct.generateProductWithIdNull();
			Stock stock = TestStock.generateStockWithIdNull();
			entity.addStock(stock);
			stock.setProduct(entity);

			assertNull("Before persistence, id should be null", entity.getId());

			// FindAll
			List<Product> list = dao.findAll();
			assertNotNull("Before persistence, the list should not be null", list);
			int listInitialSize = list.size();
			
			// Retrieve a Site from DataBase
			Site site = TestDaoSiteJpa.findSiteFromDataBase(entityManager);
			assertNotNull("Site should not be null", site);
			entity.setSite(site);
			
			// Retrieve a Designation from DataBase
			Designation designation = TestDaoDesignationJpa.findDesignationFromDataBase(entityManager);
			assertNotNull("Designation should not be null", designation);
			entity.setDesignation(designation);
			
			// Create
			UtilEntityManager.beginTransaction();
			entity = dao.create(entity);
			Long persistedId = entity.getId();
			UtilEntityManager.commit();
			assertNotNull("After persistence, id should not be null", persistedId);
			
			// FindAll
			assertEquals("After persistence, the list's size should be", listInitialSize+1, dao.findAll().size());

			// FindById
			Product persistedEntity = dao.find(persistedId);
			assertNotNull("After persistence, entity should not be null", persistedEntity);
			assertEquals("After persistence, properties should be equal", entity.getTitle(), 
					persistedEntity.getTitle());
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
			
			// Cascade
			assertNotNull("Without cascade delete, Site should not be null", 
					TestDaoSiteJpa.findSiteFromDataBase(entityManager));
			assertNotNull("Without cascade delete, Category should not be null", 
					TestDaoCategoryJpa.findCategoryFromDataBase(entityManager));
			assertNull("With cascade delete, Stock should be null", 
					TestDaoStockJpa.findStockFromDataBase(entityManager, persistedId));

			// Create
			entity = TestProduct.generateProductWithIdNull();
			stock = TestStock.generateStockWithIdNull();
			entity.addStock(stock);
			stock.setProduct(entity);
			entity.setSite(site);
			entity.setDesignation(designation);
			assertNull("Before Create, id should be null", entity.getId());
			UtilEntityManager.beginTransaction();
			dao.create(entity);
			Long secondPersistedId = entity.getId();
			assertNotNull("After Create, id should not be null", secondPersistedId);
			
			// Delete
			assertNotNull("Before Delete, entity should not be null", dao.find(secondPersistedId));
			dao.delete(entity);
			UtilEntityManager.commit();
			assertNull("After Delete, entity should be null", dao.find(secondPersistedId));
			
			// Cascade
			assertNotNull("Without cascade delete, Site should not be null", 
					TestDaoSiteJpa.findSiteFromDataBase(entityManager));
			assertNotNull("Without cascade delete, Category should not be null", 
					TestDaoCategoryJpa.findCategoryFromDataBase(entityManager));
			assertNull("With cascade delete, Stock should be null", 
					TestDaoStockJpa.findStockFromDataBase(entityManager, secondPersistedId));
			
			// FindAll
			assertEquals("After Delete, the list's size should be", listInitialSize, dao.findAll().size());
		} finally {
			UtilEntityManager.closeEntityManager();
		}
	}
	
	// Static methods //------------------------------------------
	/**
	 * Retrieve the {@link Product} from database filled with basic test data 
	 * with its identifier = {@link UtilData#EXISTING_PRODUCT_ID_FIRST_CALL}.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Product}.
	 */
	public static Product findProductFromDataBaseFirstCall(EntityManager entityManager) {
		return findProductFromDataBase(entityManager, EXISTING_PRODUCT_ID_FIRST_CALL);
	}
	
	/**
	 * Retrieve the {@link Product} from database filled with basic test data 
	 * with its identifier = {@link UtilData#EXISTING_PRODUCT_ID_SECOND_CALL}.
	 * @param entityManager the {@link EntityManager}.
	 * @return the {@link Product}.
	 */
	public static Product findProductFromDataBaseSecondCall(EntityManager entityManager) {
		return findProductFromDataBase(entityManager, EXISTING_PRODUCT_ID_SECOND_CALL);
	}
	
	/**
	 * Retrieve an {@link Product} from database filled with basic test data.
	 * @param entityManager the {@link EntityManager}.
	 * @param id Long the Product's identifier.
	 * @return the {@link Product}.
	 */
	private static Product findProductFromDataBase(EntityManager entityManager, Long id) {
		DaoProductJpa dao = new DaoProductJpa();
		dao.entityManager = entityManager;
		return dao.find(id);
	}
}