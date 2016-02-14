package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.junit.Test;

import com.jomm.terroir.business.model.Address;
import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.Site;

/**
 * This class is a Junit test case testing the methods of {@link Enterprise}.
 * @author Maic
 */
public class TestEnterprise {

	/**
	 * Test method for all {@link Enterprise}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Enterprise enterprise = new Enterprise();
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
		
		// SignUpDate
		ZonedDateTime zonedDate = ZonedDateTime.now();
		enterprise.setSignUpDate(zonedDate);
		assertEquals("SignUpDate should be " + zonedDate, zonedDate, enterprise.getSignUpDate());
		
		// ListSellers
		ArrayList<Seller> listSellers = new ArrayList<Seller>();
		Seller seller = new Seller();
		listSellers.add(seller);
		enterprise.setListSellers(listSellers);
		assertNotNull("ListSellers should not be null", enterprise.getListSellers());
		assertFalse("ListSellers should not be empty", enterprise.getListSellers().isEmpty());
		assertEquals("ListSellers' size should be 1", 1, enterprise.getListSellers().size());
		seller = null; // Available for Garbage Collector
		listSellers = null; // Available for Garbage Collector
		
		// ListSites
		ArrayList<Site> listSites = new ArrayList<Site>();
		Site site = new Site();
		listSites.add(site);
		enterprise.setListSites(listSites);
		assertNotNull("ListSites should not be null", enterprise.getListSites());
		assertFalse("ListSites should not be empty", enterprise.getListSites().isEmpty());
		assertEquals("ListSites' size should be 1", 1, enterprise.getListSites().size());
		site = null; // Available for Garbage Collector
		listSites = null; // Available for Garbage Collector
		
		enterprise = null; // Available for Garbage Collector
	}
	
	
	/**
	 * Generate a simple {@link Enterprise} usable for tests.
	 * @return a {@link Enterprise}.
	 */
	public static Enterprise generateEnterprise() {
		Enterprise enterprise = new Enterprise();
		enterprise.setAddress(new Address());
		enterprise.setCreationDate(LocalDate.now());
		enterprise.setLegalIdentification("LegalIdentification");
		enterprise.setLegalName("LegalName");
		enterprise.setListSellers(new ArrayList<Seller>());
		enterprise.setListSites(new ArrayList<Site>());
		enterprise.setNbEmployees(10);
		enterprise.setSignUpDate(ZonedDateTime.now());
		enterprise.setTradeName("TradeName");		
		return enterprise;
	}
}