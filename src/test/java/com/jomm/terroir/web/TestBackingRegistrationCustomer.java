package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.Test;

import com.jomm.terroir.business.model.Address;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestAddress;

/**
 * This class is a Junit test case testing {@link BackingRegistrationCustomer}.
 * @author Maic
 */
public class TestBackingRegistrationCustomer {
	
	// Variables //-----------------------------------------------
	private BackingRegistrationCustomer view;
	
	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link BackingRegistrationCustomer#init()}.
	 */
	@Test
	public final void testInit() {
		view = new BackingRegistrationCustomer();
		assertNull(view.getAddress());
		view.init();
		assertNotNull(view.getAddress());
	}

	/**
	 * Test method for {@link BackingRegistrationCustomer#generateYearRange()}.
	 */
	@Test
	public final void testGenerateYearRange() {
		view = new BackingRegistrationCustomer();
		// Build year range
		StringBuilder yearRange = new StringBuilder();
		yearRange.append(LocalDate.now().getYear() -97);
		yearRange.append(":");
		yearRange.append(LocalDate.now().getYear() -17);
		// Compare with year range from ViewCustomer
		assertEquals(yearRange.toString(), view.generateYearRange());
	}

	/**
	 * Test method for {@link BackingRegistrationCustomer#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewCustomer();
		Customer entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link BackingRegistrationCustomer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new BackingRegistrationCustomer();
		// BirthDate
		LocalDate birthdate = LocalDate.now();
		view.setBirthDate(birthdate);
		assertEquals("BirthDate should be " + birthdate, birthdate, view.getBirthDate());
		
		// Address
		Address address = new Address();
		String test = "test";
		address.setStreet(test);
		view.setAddress(address);
		assertNotNull("Address should not be null", address);
		assertEquals("Address.Street should be " + test, test, view.getAddress().getStreet());
		address = null; // Available for Garbage Collector
		
		view = null; // Available for Garbage Collector
	}	

	// Helpers //-------------------------------------------------
	/**
	 * Compare a view and an entity.
	 * @param view {@link BackingRegistrationCustomer}.
	 * @param entity {@link Customer}.
	 */
	private void compareViewAndEntity(BackingRegistrationCustomer view, Customer entity) {
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getBirthDate(), entity.getBirthDate());
		assertEquals(view.getAddress(), entity.getAddress());
	}

	// Static methods //------------------------------------------
	/**
	 * Generate a dummy {@link BackingRegistrationCustomer} usable for tests.
	 * @return {@link BackingRegistrationCustomer}.
	 */
	static BackingRegistrationCustomer generateDummyViewCustomer() {
		BackingRegistrationCustomer view  = new BackingRegistrationCustomer();
		TestBackingRegistrationUser.setDummyValues(view);
		view.setBirthDate(LocalDate.now());
		view.setAddress(TestAddress.generateAddress());
		return view;
	}
}