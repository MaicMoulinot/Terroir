package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.jomm.terroir.business.model.Address;
import com.jomm.terroir.business.model.Customer;

/**
 * This class is a Junit test case testing the methods of {@link Customer}.
 * @author Maic
 */
public class TestCustomer {

	/**
	 * Test method for all {@link Customer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Customer customer = new Customer();
		
		// BirthDate
		LocalDate birthdate = LocalDate.now();
		customer.setBirthDate(birthdate);
		assertEquals("BirthDate should be " + birthdate, birthdate, customer.getBirthDate());
		
		// SignUpDate
		ZonedDateTime date = ZonedDateTime.now();
		customer.setSignUpDate(date);
		assertEquals("SignUpDate should be " + date, date, customer.getSignUpDate());
		
		// Address
		Address address = new Address();
		String test = "test";
		address.setStreet(test);
		customer.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, customer.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		customer = null; // Available for Garbage Collector
	}
	
	/**
	 * Generate a simple {@link Customer} usable for tests.
	 * @return a {@link Customer}.
	 */
	public static Customer generateCustomerWithIdNull() {
		Customer customer = new Customer();
		TestAbstractUser.setDummyValuesWithIdNull(customer);
		customer.setAddress(new Address());
		customer.setBirthDate(LocalDate.now());
		return customer;
	}
}