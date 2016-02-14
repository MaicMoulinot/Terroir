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

import com.jomm.terroir.business.model.Site;
import com.jomm.terroir.business.model.TestSite;
import com.jomm.terroir.dao.DaoSite;

/**
 * This class is a Junit test case testing the contract of {@link ServiceSite}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ServiceSite}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestServiceSite {
	
	/** A mocked list to simulate calls to <code>getAllSites()</code>. */
	private ArrayList<Site> mockedList;
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
	 * Test contract for {@link ServiceSite#create(Site)}, {@link ServiceSite#update(Site)},
	 * {@link ServiceSite#delete(Site)}, and {@link ServiceSite#getAllSites()}.
	 */
	@Test
	public final void testContract() {
		String message = "service.getAllSites()";
		mockedList = new ArrayList<Site>();
		when(service.getAllSites()).thenReturn(mockedList); // MOCK: service.getAllSites() with mockedList
		
		// Before any persistence, the list is not null and is empty
		assertNotNull(message + " should not be null", service.getAllSites());
		assertTrue(message + " should be empty", service.getAllSites().isEmpty());

		// Create
		Site site = TestSite.generateSite();
		service.create(site);
		mockedList.add(site); // MOCK: simulate create into mockedList

		// After persistence, the list is not empty and its size is 1
		assertFalse(message + " should not be empty", service.getAllSites().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllSites().size(), 1);

		// Update
		site = mockedList.get(0);
		String initialSiteName = site.getSiteName();
		site.setSiteName("updatedSiteName");
		service.update(site);
		mockedList.set(0, site); // MOCK: simulate update into mockedList
		String updatedSiteName = service.getAllSites().get(0).getSiteName();
		assertNotEquals("SiteNames should not match", initialSiteName, updatedSiteName);

		// Delete
		service.delete(site);
		mockedList.remove(site); // MOCK: simulate delete into mockedList
		
		// After delete, the list is back to empty
		assertTrue(message + " should be empty", service.getAllSites().isEmpty());
	}
	
	/**
	 * Reference a list of all {@link ServiceSite}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with <code>testContract()</code>.
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