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
 * This class is a Junit test case testing {@link BeanRegistrationCustomer}.
 * @author Maic
 */
public class TestBeanRegistrationCustomer {

	private BeanRegistrationCustomer view;
	
	/**
	 * Test method for {@link BeanRegistrationCustomer#init()}.
	 */
	@Test
	public final void testInit() {
		view = new BeanRegistrationCustomer();
		assertNull(view.getAddress());
		view.init();
		assertNotNull(view.getAddress());
	}

	/**
	 * Test method for {@link BeanRegistrationCustomer#generateYearRange()}.
	 */
	@Test
	public final void testGenerateYearRange() {
		view = new BeanRegistrationCustomer();
		// Build year range
		StringBuilder yearRange = new StringBuilder();
		yearRange.append(LocalDate.now().getYear() -97);
		yearRange.append(":");
		yearRange.append(LocalDate.now().getYear() -17);
		// Compare with year range from ViewCustomer
		assertEquals(yearRange.toString(), view.generateYearRange());
	}

	/**
	 * Test method for {@link BeanRegistrationCustomer#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewCustomer();
		view.setId((long) 3333);
		Customer entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link BeanRegistrationCustomer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new BeanRegistrationCustomer();
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

	/**
	 * Compare a view and an entity.
	 * @param view {@link BeanRegistrationCustomer}.
	 * @param entity {@link Customer}.
	 */
	private void compareViewAndEntity(BeanRegistrationCustomer view, Customer entity) {
		assertEquals(view.getId(), entity.getId());
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getBirthDate(), entity.getBirthDate());
		assertEquals(view.getAddress(), entity.getAddress());
	}

	/**
	 * Generate a dummy {@link BeanRegistrationCustomer} usable for tests.
	 * @return {@link BeanRegistrationCustomer}.
	 */
	static BeanRegistrationCustomer generateDummyViewCustomer() {
		BeanRegistrationCustomer view  = new BeanRegistrationCustomer();
		TestBeanRegistrationUser.setDummyValues(view);
		view.setBirthDate(LocalDate.now());
		view.setAddress(TestAddress.generateAddress());
		return view;
	}
}