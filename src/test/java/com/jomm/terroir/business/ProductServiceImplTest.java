package com.jomm.terroir.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.dao.ProductDao;

/**
 * This class is a Junit test case testing the methods of {@link ProductServiceImpl}.
 * Practically, it verifies that each Service method properly calls the appropriate DAO method.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock(name = "dao")
	private ProductDao dao;

	@InjectMocks
	private ProductServiceImpl service;
	
	/**
	 * Test method for {@link ProductServiceImpl#create(Product)}.
	 */
	@Test
	public final void testCreate() {
		service.create(ProductTest.generateProduct());
		verify(dao).create(any(Product.class)); // validate that dao.create() was called
	}

	/**
	 * Test method for {@link ProductServiceImpl#update(Product)}.
	 */
	@Test
	public final void testUpdate() {
		service.update(ProductTest.generateProduct());
		verify(dao).update(any(Product.class)); // validate that dao.update() was called
	}

	/**
	 * Test method for {@link ProductServiceImpl#delete(Product)}.
	 */
	@Test
	public final void testDelete() {
		service.delete(ProductTest.generateProduct());
		verify(dao).delete(any(Product.class)); // validate that dao.delete() was called
	}

	/**
	 * Test method for {@link ProductServiceImpl#getAllProducts()}.
	 */
	@Test
	public final void testGetAllProducts() {
		service.getAllProducts();
		verify(dao).findAll(); // validate that dao.findAll() was called
	}
}