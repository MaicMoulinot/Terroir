package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.business.model.TestStock;
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.dao.DaoStock;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceProduct}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceProduct}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceProduct {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	private static final Integer QUANTITY = 200;
	
	// Variables //-----------------------------------------------
	/** An implementation of {@link ServiceProduct}. */
	private ServiceProduct service;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceProduct}.
	 */
	public TestServiceProduct(ServiceProduct service) {
		this.service = service;
	}
	
	// Test methods //--------------------------------------------
	/**
	 * Test that {@link ServiceProduct#create(Product)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceProduct#create(Product)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithIdNotNull() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		service.create(product);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#create(Product)} does not throw an {@link ExceptionService}
	 * when entity's state is correct, and properly generates the LastUpdate in the stock.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateSetLastUpdateStock() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		assertNull("LastUpdate should not yet be initialized", product.getStock().getLastUpdate());
		ZonedDateTime now = ZonedDateTime.now();
		service.create(product);
		ZonedDateTime entityDate = product.getStock().getLastUpdate();
		assertNotNull("LastUpdate should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("LastUpdate should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));
	}

	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithIdNull() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		service.update(product);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#update(Product)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithIdNotNull() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		product.getStock().setId(ID);
		service.update(product);
		assertEquals("Update does not change the identifier", ID, product.getId());
	}
	
	/**
	 * Test that {@link ServiceProduct#updateQuantity(Stock, Integer)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateQuantityWithEntityNull() throws ExceptionService {
		service.updateQuantity(null, QUANTITY);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#updateQuantity(Stock, Integer)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateQuantityWithIdNull() throws ExceptionService {
		Stock stock = TestStock.generateStockWithIdNull();
		service.updateQuantity(stock, QUANTITY);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#updateQuantity(Stock, Integer)} does not throw an {@link ExceptionService}
	 * when entity's state is correct, and properly generates the LastUpdate in the stock.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateQuantityWithIdNotNullSetLastUpdate() throws ExceptionService {
		Stock stock = TestStock.generateStockWithIdNull();
		stock.setId(ID);
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		stock.setProduct(product);
		assertNull("LastUpdate should not yet be initialized", stock.getLastUpdate());
		ZonedDateTime now = ZonedDateTime.now();
		service.updateQuantity(stock, QUANTITY);
		ZonedDateTime entityDate = stock.getLastUpdate();
		assertNotNull("LastUpdate should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("LastUpdate should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));		
	}

	/**
	 * Test that {@link ServiceProduct#delete(Product)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithIdNull() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		service.delete(product);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceProduct#delete(Product)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws ExceptionService {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		service.delete(product);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that list from {@link ServiceProduct#getAllProducts()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllProducts());
	}
	
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all {@link ServiceProduct}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedProductServiceImpl()}
			}
		);
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Construct a {@link ServiceProductImpl} with a mocked DAO.
	 * @return the {@link ServiceProductImpl}
	 */
	private static ServiceProductImpl generateMockedProductServiceImpl() {
		ServiceProductImpl impl = new ServiceProductImpl();
		impl.setTestDaoProduct(mock(DaoProduct.class));
		impl.setTestDaoStock(mock(DaoStock.class));
		return impl;
	}
}