package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.exception.ExceptionService;

/**
 * This class is a Junit test case testing the contract of {@link ServiceSite}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceSite}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceSite {
	
	// Constants //-----------------------------------------------
	private static final Long ID = 52L;
	
	// Variables //-----------------------------------------------
	/** An implementation of {@link ServiceSite}. */
	private ServiceSite service;
	
	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceSite}.
	 */
	public TestServiceSite(ServiceSite service) {
		this.service = service;
	}
	
	// Test methods //--------------------------------------------
	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithEntityNull() throws ExceptionService {
		service.create(null);
		fail("An ExceptionService should have been thrown");
	}

	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link ExceptionService}
	 * when entity's id is not null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testCreateWithIdNotNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId(ID);
		service.create(site);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#create(Site)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testCreateWithIdNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		service.create(site);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithEntityNull() throws ExceptionService {
		service.update(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testUpdateWithIdNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		service.update(site);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#update(Site)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testUpdateWithIdNotNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId(ID);
		service.update(site);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that {@link ServiceSite#delete(Site)} throws an {@link ExceptionService}
	 * when entity is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithEntityNull() throws ExceptionService {
		service.delete(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#delete(Site)} throws an {@link ExceptionService}
	 * when entity's id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testDeleteWithIdNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		service.delete(site);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#delete(Site)} does not throw an {@link ExceptionService}
	 * when entity's state is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testDeleteWithIdNotNull() throws ExceptionService {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId(ID);
		service.delete(site);
		assertTrue("ExceptionService should not be thrown", true);
	}

	/**
	 * Test that list from {@link ServiceSite#getAllSites()} is not null.
	 */
	@Test
	public final void testGetAllListIsNotNull() {
		assertNotNull(service.getAllSites());
	}
	
	/**
	 * Test that {@link ServiceSite#getSite(Long)} throws an {@link ExceptionService}
	 * when the id is null.
	 * @throws ExceptionService is expected.
	 */
	@Test(expected = ExceptionService.class)
	public final void testGetWithIdNull() throws ExceptionService {
		service.getSite(null);
		fail("An ExceptionService should have been thrown");
	}
	
	/**
	 * Test that {@link ServiceSite#getSite(Long)} does not throw an {@link ExceptionService}
	 * when the id is correct.
	 * @throws ExceptionService is not expected.
	 */
	@Test
	public final void testGetWithIdNotNull() throws ExceptionService {
		service.getSite(ID);
		assertTrue("ExceptionService should not be thrown", true);
	}
	
	// Static methods //------------------------------------------
	/**
	 * Reference a list of all {@link ServiceSite}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedSiteServiceImpl()}
			}
		);
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Construct a {@link ServiceSiteImpl} with a mocked DAO.
	 * @return the {@link ServiceSiteImpl}
	 */
	private static ServiceSiteImpl generateMockedSiteServiceImpl() {
		ServiceSiteImpl impl = new ServiceSiteImpl();
		impl.setTestDaoSite(mock(DaoSite.class));
		return impl;
	}
}