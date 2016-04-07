package com.jomm.terroir.business;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.Stock;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.business.model.TestStock;
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.dao.DaoStock;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ServiceProductImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceProductImpl {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Injected Fields //-----------------------------------------
	@Mock(name = "dao")
	private DaoProduct dao;
	@Mock(name = "daoStock")
	private DaoStock daoStock;

	@InjectMocks
	private ServiceProductImpl service;
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link ServiceProductImpl#create(Product)}.
	 */
	@Test
	public final void testCreate() {
		try {
			service.create(TestProduct.generateProductWithIdNull());
			verify(dao).create(any(Product.class)); // validate that dao.create() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}

	/**
	 * Test method for {@link ServiceProductImpl#update(Product)}.
	 */
	@Test
	public final void testUpdate() {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		product.getStock().setId(ID);
		try {
			service.update(product);
			verify(dao).update(any(Product.class)); // validate that dao.update() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}
	
	/**
	 * Test method for {@link ServiceProductImpl#updateAvailability(Stock, Integer)}.
	 */
	@Test
	public final void testUpdateQuantity() {
		Stock stock = TestStock.generateStockWithIdNull();
		stock.setId(ID);
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		stock.setProduct(product);
		try {
			service.updateAvailability(stock, 23);
			verify(daoStock).update(any(Stock.class)); // validate that daoStock.update() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}

	/**
	 * Test method for {@link ServiceProductImpl#delete(Product)}.
	 */
	@Test
	public final void testDelete() {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId(ID);
		try {
			service.delete(product);
			verify(dao).delete(any(Product.class)); // validate that dao.delete() was called
		} catch (ExceptionService unexpectedException) {
			assertNull("An Exception was thrown and should not have", unexpectedException);
		}
	}

	/**
	 * Test method for {@link ServiceProductImpl#getAllProducts()}.
	 */
	@Test
	public final void testGetAllProducts() {
		service.getAllProducts();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
}