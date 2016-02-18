package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.InvalidEntityException;

/**
 * This class is a Junit test case testing the contract of {@link ServiceSite}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceSite}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceSite {
	
	/** An implementation of {@link ServiceSite}. */
	private ServiceSite service;

	/**
	 * Constructor.
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link ServiceSite}.
	 */
	public TestServiceSite(ServiceSite service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws InvalidEntityException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testCreateWithEntityNull() throws NullPointerException, InvalidEntityException {
		service.create(null);
	}

	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link InvalidEntityException}
	 * when entity's id is not null.
	 * @throws NullPointerException is not expected.
	 * @throws InvalidEntityException is expected.
	 */
	@Test(expected = InvalidEntityException.class)
	public final void testCreateWithEntityIdNotNull() throws NullPointerException, InvalidEntityException {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId((long) 52);
		service.create(site);
	}

	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link InvalidEntityException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws InvalidEntityException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testUpdateWithEntityNull() throws NullPointerException, InvalidEntityException {
		service.update(null);
	}

	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link InvalidEntityException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws InvalidEntityException is expected.
	 */
	@Test(expected = InvalidEntityException.class)
	public final void testUpdateWithEntityIdNull() throws NullPointerException, InvalidEntityException {
		Site site = TestSite.generateSiteWithIdNull();
		service.update(site);
	}

	/**
	 * Test that {@link ServiceSite#delete(Site)} throws an {@link NullPointerException}
	 * when entity is null.
	 * @throws NullPointerException is expected.
	 * @throws InvalidEntityException is not expected.
	 */
	@Test(expected = NullPointerException.class)
	public final void testDeleteWithEntityNull() throws NullPointerException, InvalidEntityException {
		service.delete(null);
	}
	
	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link InvalidEntityException}
	 * when entity's id is null.
	 * @throws NullPointerException is not expected.
	 * @throws InvalidEntityException is expected.
	 */
	@Test(expected = InvalidEntityException.class)
	public final void testDeleteWithEntityIdNull() throws NullPointerException, InvalidEntityException {
		Site site = TestSite.generateSiteWithIdNull();
		service.delete(site);
	}

	/**
	 * Test that list from {@link ServiceSite#getAllSites()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllSites());
	}
	
	/**
	 * Reference a list of all {@link ServiceSite}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedSiteServiceImpl()}
			}
		);
	}
	
	/**
	 * Construct a {@link ServiceSiteImpl} with a mocked DAO.
	 * @return the {@link ServiceSiteImpl}
	 */
	private static ServiceSiteImpl generateMockedSiteServiceImpl() {
		ServiceSiteImpl impl = new ServiceSiteImpl();
		impl.setSiteDao(Mockito.mock(DaoSite.class));
		return impl;
	}
}