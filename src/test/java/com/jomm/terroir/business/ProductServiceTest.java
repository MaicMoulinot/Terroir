package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.dao.ProductDao;

/**
 * This class is a Junit test case testing the contract of {@link ProductService}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ProductService}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class ProductServiceTest {
	
	/** A mocked list to simulate calls to <code>getAllProducts()</code>. */
	private ArrayList<Product> mockedList;
	/** An implementation of {@link ProductService}. */
	private ProductService service;
	
	/**
	 * Constructor.
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link ProductService}.
	 */
	public ProductServiceTest(ProductService service) {
		this.service = service;
	}
	
	/**
	 * Test contract for {@link ProductService#create(Product)}, {@link ProductService#update(Product)},
	 * {@link ProductService#delete(Product)}, and {@link ProductService#getAllProducts()}.
	 */
	@Test
	public final void testContract() {
		String message = "service.getAllProducts()";
		mockedList = new ArrayList<Product>();
		when(service.getAllProducts()).thenReturn(mockedList); // MOCK: service.getAllProducts() with mockedList
		
		// Before any persistence, the list is not null and is empty
		assertNotNull(message + " should not be null", service.getAllProducts());
		assertTrue(message + " should be empty", service.getAllProducts().isEmpty());

		// Create
		Product product = ProductTest.generateProduct();
		service.create(product);
		mockedList.add(product); // MOCK: simulate create into mockedList

		// After persistence, the list is not empty and its size is 1
		assertFalse(message + " should not be empty", service.getAllProducts().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllProducts().size(), 1);

		// Update
		product = mockedList.get(0);
		String initialTitle = product.getTitle();
		product.setTitle("updatedTitle");
		service.update(product);
		mockedList.set(0, product); // MOCK: simulate update into mockedList
		String updatedTitle = service.getAllProducts().get(0).getTitle();
		assertNotEquals("Titles should not match", initialTitle, updatedTitle);

		// Delete
		service.delete(product);
		mockedList.remove(product); // MOCK: simulate delete into mockedList
		
		// After delete, the list is back to empty
		assertTrue(message + " should be empty", service.getAllProducts().isEmpty());
	}
	
	/**
	 * Reference a list of all {@link ProductService}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with <code>testContract()</code>.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedProductServiceImpl()}
			}
		);
	}
	
	/**
	 * Construct a {@link ProductServiceImpl} with a mocked DAO.
	 * @return the {@link ProductServiceImpl}
	 */
	private static ProductServiceImpl generateMockedProductServiceImpl() {
		ProductServiceImpl impl = new ProductServiceImpl();
		impl.setProductDao(Mockito.mock(ProductDao.class));
		return impl;
	}
}