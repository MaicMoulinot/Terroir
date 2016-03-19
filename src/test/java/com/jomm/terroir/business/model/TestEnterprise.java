package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Enterprise}.
 * @author Maic
 */
public class TestEnterprise {

	private Enterprise enterprise;
	
	/**
	 * Instantiate the {@link Enterprise}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		enterprise = new Enterprise();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		enterprise = null; // Available for Garbage Collector
	}
	
	/**
	 * Test method for all {@link Enterprise}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		String test = "test";
		Long nb = (long) 0;
		
		// Id
		enterprise.setId(nb);
		assertEquals("Id should be " + nb, nb, enterprise.getId());
		
		// TradeName
		enterprise.setTradeName(test);
		assertEquals("TradeName should be " + test, test, enterprise.getTradeName());
		
		// LegalName
		enterprise.setLegalName(test);
		assertEquals("LegalName should be " + test, test, enterprise.getLegalName());
		
		// LegalIdentification
		enterprise.setLegalIdentification(test);
		assertEquals("LegalIdentification should be " + test, test, enterprise.getLegalIdentification());
		
		// Address
		Address address = new Address();
		address.setStreet(test);
		enterprise.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, enterprise.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		// CreationDate
		LocalDate localDate = LocalDate.now();
		enterprise.setCreationDate(localDate);
		assertEquals("CreationDate should be " + localDate, localDate, enterprise.getCreationDate());
		
		// NbEmployees
		int employees = 10;
		enterprise.setNbEmployees(employees);
		assertEquals("NbEmployees should be " + employees, employees, enterprise.getNbEmployees());
		
		// RegistrationDate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		enterprise.setRegistrationDate(zonedDate);
		assertEquals("RegistrationDate should be " + zonedDate, zonedDate, enterprise.getRegistrationDate());
		
		// List of sellers
		ArrayList<Seller> listSellers = new ArrayList<>();
		Seller seller = new Seller();
		listSellers.add(seller);
		enterprise.setSellers(listSellers);
		assertNotNull("List of sellers should not be null", enterprise.getSellers());
		assertFalse("List of sellers should not be empty", enterprise.getSellers().isEmpty());
		assertEquals("List of sellers size should be 1", 1, enterprise.getSellers().size());
		seller = null; // Available for Garbage Collector
		listSellers = null; // Available for Garbage Collector
		
		// List of sites
		ArrayList<Site> listSites = new ArrayList<>();
		Site site = new Site();
		listSites.add(site);
		enterprise.setSites(listSites);
		assertNotNull("List of sites should not be null", enterprise.getSites());
		assertFalse("List of sites should not be empty", enterprise.getSites().isEmpty());
		assertEquals("List of sites size should be 1", 1, enterprise.getSites().size());
		site = null; // Available for Garbage Collector
		listSites = null; // Available for Garbage Collector
	}
	
	
	/**
	 * Generate a simple {@link Enterprise} usable for tests.
	 * @return a {@link Enterprise}.
	 */
	public static Enterprise generateEnterpriseWithIdNull() {
		Enterprise enterprise = new Enterprise();
		enterprise.setAddress(TestAddress.generateAddress());
		enterprise.setCreationDate(LocalDate.now());
		enterprise.setLegalIdentification("LegalIdentification");
		enterprise.setLegalName("LegalName");
		enterprise.setSellers(new ArrayList<>());
		enterprise.setSites(new ArrayList<>());
		enterprise.setNbEmployees(10);
		enterprise.setTradeName("TradeName");		
		return enterprise;
	}
}