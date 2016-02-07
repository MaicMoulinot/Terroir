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

import com.jomm.terroir.dao.EnterpriseDao;

/**
 * This class is a Junit test case testing the contract of {@link ProductService}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each implementation of {@link ProductService}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class EnterpriseServiceTest {
	
	/** A mocked list to simulate calls to <code>getAllEnterprises()</code>. */
	private ArrayList<Enterprise> mockedList;
	/** An implementation of {@link EnterpriseService}. */
	private EnterpriseService service;
	
	/**
	 * Constructor.
	 * As this class is running with <code>Parameterized.class</code>, the constructor will be initialized with
	 * all values contained in the list returned from <code>implementationToTest()</code>.
	 * @param service an implementation of {@link EnterpriseService}.
	 */
	public EnterpriseServiceTest(EnterpriseService service) {
		this.service = service;
	}
	
	/**
	 * Test contract for {@link EnterpriseService#create(Enterprise)}, {@link EnterpriseService#update(Enterprise)},
	 * {@link EnterpriseService#delete(Enterprise)}, and {@link EnterpriseService#getAllEnterprises()}.
	 */
	@Test
	public final void testContract() {
		String message = "service.getAllEnterprises()";
		mockedList = new ArrayList<Enterprise>();
		when(service.getAllEnterprises()).thenReturn(mockedList); // MOCK: service.getAllEnterprises() with mockedList
		
		// Before any persistence, the list is not null and is empty
		assertNotNull(message + " should not be null", service.getAllEnterprises());
		assertTrue(message + "  should be empty", service.getAllEnterprises().isEmpty());

		// Create
		Enterprise enterprise = EnterpriseTest.generateEnterprise();
		service.create(enterprise);
		mockedList.add(enterprise); // MOCK: simulate create into mockedList

		// After persistence, the list is not empty and its size is 1
		assertFalse(message + "  should not be empty", service.getAllEnterprises().isEmpty());
		assertEquals(message + " ' size should be 1", service.getAllEnterprises().size(), 1);

		// Update
		enterprise = mockedList.get(0);
		String initialLegalName = enterprise.getLegalName();
		enterprise.setLegalName("updatedLegalName");
		service.update(enterprise);
		mockedList.set(0, enterprise); // MOCK: simulate update into mockedList
		String updatedLegalName = service.getAllEnterprises().get(0).getLegalName();
		assertNotEquals("LegalNames should not match", initialLegalName, updatedLegalName);

		// Delete
		service.delete(enterprise);
		mockedList.remove(enterprise); // MOCK: simulate delete into mockedList
		
		// After delete, the list is back to empty
		assertTrue(message + " should be empty", service.getAllEnterprises().isEmpty());
	}
	
	/**
	 * Reference a list of all {@link EnterpriseService}'s implementation to be used as parameter on constructor.
	 * Each implementation will be tested with <code>testContract()</code>.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> implementationToTest() {
		return Arrays.asList(new Object[][] {
			{generateMockedEnterpriseServiceImpl()}
			}
		);
	}
	
	/**
	 * Construct a {@link EnterpriseServiceImpl} with a mocked DAO.
	 * @return the {@link EnterpriseServiceImpl}
	 */
	private static EnterpriseServiceImpl generateMockedEnterpriseServiceImpl() {
		EnterpriseServiceImpl impl = new EnterpriseServiceImpl();
		impl.setEnterpriseDao(Mockito.mock(EnterpriseDao.class));
		return impl;
	}
}