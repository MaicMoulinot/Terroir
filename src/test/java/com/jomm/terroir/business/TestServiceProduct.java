package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Product;
import com.jomm.terroir.business.model.TestProduct;
import com.jomm.terroir.dao.DaoProduct;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

/**
 * This class is a Junit test case testing the contract of {@link ServiceProduct}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceProduct}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceProduct {
	
	/** An implementation of {@link ServiceProduct}. */
	private ServiceProduct service;
	
	/**
	 * Constructor.
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link ServiceProduct}.
	 */
	public TestServiceProduct(ServiceProduct service) {
		this.service = service;
	}
	
	/**
	 * Test that {@link ServiceProduct#create(Product)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testCreateWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.create(null);
	}

	/**
	 * Test that {@link ServiceProduct#create(Product)} throws an {@link ExceptionInvalidId}
	 * when entity's id is not null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testCreateWithEntityIdNotNull() throws ExceptionNullEntity, ExceptionInvalidId {
		Product product = TestProduct.generateProductWithIdNull();
		product.setId((long) 52);
		service.create(product);
	}
	
	/**
	 * Test that {@link ServiceProduct#create(Product)} generate properly the sign up date.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test
	public final void testCreateProductGenerateRegistrationDate() throws ExceptionNullEntity, ExceptionInvalidId {
		Product product = TestProduct.generateProductWithIdNull();
		assertNull("RegistrationDate should not yet be initialized", product.getRegistrationDate());
		ZonedDateTime now = ZonedDateTime.now();
		service.create(product);
		ZonedDateTime entityDate = product.getRegistrationDate();
		assertNotNull("RegistrationDate should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("RegistrationDate should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));
	}

	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionInvalidId}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testUpdateWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.update(null);
	}

	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionInvalidId}
	 * when entity's id is null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testUpdateWithEntityIdNull() throws ExceptionNullEntity, ExceptionInvalidId {
		Product product = TestProduct.generateProductWithIdNull();
		service.update(product);
	}

	/**
	 * Test that {@link ServiceProduct#delete(Product)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testDeleteWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.delete(null);
	}
	
	/**
	 * Test that {@link ServiceProduct#update(Product)} throws an {@link ExceptionInvalidId}
	 * when entity's id is null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testDeleteWithEntityIdNull() throws ExceptionNullEntity, ExceptionInvalidId {
		Product product = TestProduct.generateProductWithIdNull();
		service.delete(product);
	}

	/**
	 * Test that list from {@link ServiceProduct#getAllProducts()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllProducts());
	}
	
	/**
	 * Reference a list of all {@link ServiceProduct}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
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
	 * Construct a {@link ServiceProductImpl} with a mocked DAO.
	 * @return the {@link ServiceProductImpl}
	 */
	private static ServiceProductImpl generateMockedProductServiceImpl() {
		ServiceProductImpl impl = new ServiceProductImpl();
		impl.setDaoProduct(mock(DaoProduct.class));
		return impl;
	}
}