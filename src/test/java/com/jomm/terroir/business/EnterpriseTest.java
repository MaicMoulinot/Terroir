/**
 * 
 */
package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Enterprise}.
 * @author Maic
 */
public class EnterpriseTest {

	/**
	 * Test method for all {@link Enterprise}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Enterprise enterprise = new Enterprise();
		String test = "test";
		int nb = 0;
		
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
		enterprise.setNbEmployees(nb);
		assertEquals("NbEmployees should be " + nb, nb, enterprise.getNbEmployees());
		
		// SignUpDate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		enterprise.setSignUpDate(zonedDate);
		assertEquals("SignUpDate should be " + zonedDate, zonedDate, enterprise.getSignUpDate());
		
		// ListSellers
		List<Seller> listSellers = new ArrayList<Seller>();
		Seller seller = new Seller();
		listSellers.set(0, seller);
		enterprise.setListSellers(listSellers);
		assertNotNull("ListSellers should not be null", enterprise.getListSellers());
		assertFalse("ListSellers should not be empty", enterprise.getListSellers().isEmpty());
		assertEquals("ListSellers' size should be 1", 1, enterprise.getListSellers().size());
		seller = null; // Available for Garbage Collector
		listSellers = null; // Available for Garbage Collector
		
		// ListSites
		List<Site> listSites = new ArrayList<Site>();
		Site site = new Site();
		listSites.set(0, site);
		enterprise.setListSites(listSites);
		assertNotNull("ListSites should not be null", enterprise.getListSites());
		assertFalse("ListSites should not be empty", enterprise.getListSites().isEmpty());
		assertEquals("ListSites' size should be 1", 1, enterprise.getListSites().size());
		site = null; // Available for Garbage Collector
		listSites = null; // Available for Garbage Collector
		
		enterprise = null; // Available for Garbage Collector
	}
}
