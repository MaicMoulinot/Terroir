package com.jomm.terroir.business;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.dao.DaoSite;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;

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
	 * As this class is running with {@code Parameterized.class}, the constructor will be initialized with
	 * all values contained in the list returned from {@code implementationToTest()}.
	 * @param service an implementation of {@link ServiceSite}.
	 */
	public TestServiceSite(ServiceSite service) {
		this.service = service;
	}

	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testCreateWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.create(null);
	}

	/**
	 * Test that {@link ServiceSite#create(Site)} throws an {@link ExceptionInvalidId}
	 * when entity's id is not null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testCreateWithEntityIdNotNull() throws ExceptionNullEntity, ExceptionInvalidId {
		Site site = TestSite.generateSiteWithIdNull();
		site.setId((long) 52);
		service.create(site);
	}

	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link ExceptionInvalidId}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testUpdateWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.update(null);
	}

	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link ExceptionInvalidId}
	 * when entity's id is null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testUpdateWithEntityIdNull() throws ExceptionNullEntity, ExceptionInvalidId {
		Site site = TestSite.generateSiteWithIdNull();
		service.update(site);
	}

	/**
	 * Test that {@link ServiceSite#delete(Site)} throws an {@link ExceptionNullEntity}
	 * when entity is null.
	 * @throws ExceptionNullEntity is expected.
	 * @throws ExceptionInvalidId is not expected.
	 */
	@Test(expected = ExceptionNullEntity.class)
	public final void testDeleteWithEntityNull() throws ExceptionNullEntity, ExceptionInvalidId {
		service.delete(null);
	}
	
	/**
	 * Test that {@link ServiceSite#update(Site)} throws an {@link ExceptionInvalidId}
	 * when entity's id is null.
	 * @throws ExceptionNullEntity is not expected.
	 * @throws ExceptionInvalidId is expected.
	 */
	@Test(expected = ExceptionInvalidId.class)
	public final void testDeleteWithEntityIdNull() throws ExceptionNullEntity, ExceptionInvalidId {
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
	 * @return {@code Iterable<Object[]>} with the parameter.
	 */
	@Parameters
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
		impl.setDaoSite(mock(DaoSite.class));
		return impl;
	}
}