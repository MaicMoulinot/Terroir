package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Customer}.
 * @author Maic
 */
public class TestCustomer {
	
	// Variables //-----------------------------------------------
	private Customer customer;
	
	// Test methods //--------------------------------------------
	/**
	 * Instantiate the {@link Customer}.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customer = new Customer();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		customer = null; // Available for Garbage Collector
	}

	/**
	 * Test method for all {@link Customer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		// BirthDate
		LocalDate birthdate = LocalDate.now();
		customer.setBirthDate(birthdate);
		assertEquals("BirthDate should be " + birthdate, birthdate, customer.getBirthDate());
		
		// RegistrationDate
		ZonedDateTime date = ZonedDateTime.now();
		customer.setRegistrationDate(date);
		assertEquals("SignUpDate should be " + date, date, customer.getRegistrationDate());
		
		// Address
		Address address = new Address();
		String test = "test";
		address.setStreet(test);
		customer.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, customer.getAddress().getStreet());
		address = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a simple {@link Customer} usable for tests.
	 * @return a {@link Customer}.
	 */
	public static Customer generateCustomerWithIdNull() {
		Customer customer = new Customer();
		TestAbstractUser.setDummyValuesWithIdNull(customer);
		customer.setAddress(TestAddress.generateAddress());
		customer.setBirthDate(LocalDate.now());
		return customer;
	}
}