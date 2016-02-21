package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.dao.DaoEnterprise;

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
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link ServiceEnterprise}.
	 */
	public TestServiceEnterprise(ServiceEnterprise service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceEnterprise#create(Enterprise)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testCreateWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.create(null);
	}

	/**
	 * Test that {@link ServiceEnterprise#create(Enterprise)} throws an {@link IllegalArgumentException}
	 * when entity's id is not null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateWithEntityIdNotNull() throws NullPointerException, IllegalArgumentException {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		enterprise.setId((long) 52);
		service.create(enterprise);
	}

	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link IllegalArgumentException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testUpdateWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.update(null);
	}

	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link IllegalArgumentException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateWithEntityIdNull() throws NullPointerException, IllegalArgumentException {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		service.update(enterprise);
	}

	/**
	 * Test that {@link ServiceEnterprise#delete(Enterprise)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws IllegalArgumentException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testDeleteWithEntityNull() throws NullPointerException, IllegalArgumentException {
		service.delete(null);
	}
	
	/**
	 * Test that {@link ServiceEnterprise#update(Enterprise)} throws an {@link IllegalArgumentException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws IllegalArgumentException is expected.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testDeleteWithEntityIdNull() throws NullPointerException, IllegalArgumentException {
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		service.delete(enterprise);
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
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
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
		impl.setEnterpriseDao(Mockito.mock(DaoEnterprise.class));
		return impl;
	}
}