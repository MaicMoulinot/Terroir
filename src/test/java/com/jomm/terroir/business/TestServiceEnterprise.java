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

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.dao.DaoEnterprise;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceEnterprise}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceEnterprise}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceEnterprise {

	/** An implementation of {@link ServiceEnterprise}. */
	private ServiceEnterprise service;

	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceEnterprise}.
	 */
	public TestServiceEnterprise(ServiceEnterprise service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceEnterprise#create(Enterprise)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceEnterprise#create(Enterprise)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityIdNotNull() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 52);
		service.create(enterprise);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceEnterprise#create(Enterprise)} does not throw an {@link ExceptionService}
	 * when entity's state is correct, and properly generates the RegistrationDate.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateEnterpriseSetRegistrationDate() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		assertNull("RegistrationDate should not yet be initialized", enterprise.getRegistrationDate());
		ZonedDateTime now = ZonedDateTime.now();
		service.create(enterprise);
		ZonedDateTime entityDate = enterprise.getRegistrationDate();
		assertNotNull("RegistrationDate should be initialized", entityDate);
		DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
		assertEquals("RegistrationDate should be like ZonedDateTime.now()", now.format(formatter), 
				entityDate.format(formatter));
	}

	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityIdNull() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		service.update(enterprise);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithEntityIdNotNull() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 52);
		service.update(enterprise);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceEnterprise#delete(Enterprise)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityIdNull() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		service.delete(enterprise);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceEnterprise#delete(Enterprise)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithEntityIdNotNull() throws ExceptionService {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 52);
		service.delete(enterprise);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that list from {@link ServiceEnterprise#getAllEnterprises()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllEnterprises());
	}

	/**
	 * Reference a list of all {@link ServiceEnterprise}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedEnterpriseServiceImpl()}});
	}

	/**
	 * Construct a {@link ServiceEnterpriseImpl} with a mocked DAO.
	 * @return the {@link ServiceEnterpriseImpl}
	 */
	private static ServiceEnterpriseImpl generateMockedEnterpriseServiceImpl() {
		ServiceEnterpriseImpl impl = new ServiceEnterpriseImpl();
		impl.setTestDaoEnterprise(mock(DaoEnterprise.class));
		return impl;
	}
}