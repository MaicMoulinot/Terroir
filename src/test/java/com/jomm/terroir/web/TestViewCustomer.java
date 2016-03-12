package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.jomm.terroir.business.model.Address;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestAddress;
import com.jomm.terroir.business.model.TestCustomer;

/**
 * This class is a Junit test case testing {@link ViewCustomer}.
 * @author Maic
 */
public class TestViewCustomer {

	private ViewCustomer view;
	
	/**
	 * Test method for {@link ViewCustomer#init()}.
	 */
	@Test
	public final void testInit() {
		view = new ViewCustomer();
		assertNull(view.getAddress());
		view.init();
		assertNotNull(view.getAddress());
	}

	/**
	 * Test method for {@link ViewCustomer#generateYearRange()}.
	 */
	@Test
	public final void testGenerateYearRange() {
		view = new ViewCustomer();
		// Build year range
		StringBuilder yearRange = new StringBuilder();
		yearRange.append(LocalDate.now().getYear() -97);
		yearRange.append(":");
		yearRange.append(LocalDate.now().getYear() -17);
		// Compare with year range from ViewCustomer
		assertEquals(yearRange.toString(), view.generateYearRange());
	}

	/**
	 * Test method for {@link ViewCustomer#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view = generateDummyViewCustomer();
		view.setId((long) 3333);
		Customer entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link ViewCustomer#convertIntoView(Customer)}.
	 */
	@Test
	public final void testConvertIntoView() {
		Customer entity = TestCustomer.generateCustomerWithIdNull();
		entity.setId((long) 3333);
		ViewCustomer view = ViewCustomer.convertIntoView(entity);
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link ViewCustomer}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		view = new ViewCustomer();
		// BirthDate
		LocalDate birthdate = LocalDate.now();
		view.setBirthDate(birthdate);
		assertEquals("BirthDate should be " + birthdate, birthdate, view.getBirthDate());

		// SignUpDate
		ZonedDateTime date = ZonedDateTime.now();
		view.setSignUpDate(date);
		assertEquals("SignUpDate should be " + date, date, view.getSignUpDate());
		
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
	 * @param view {@link ViewCustomer}.
	 * @param entity {@link Customer}.
	 */
	private void compareViewAndEntity(ViewCustomer view, Customer entity) {
		assertEquals(view.getId(), entity.getId());
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), String.valueOf(entity.getPassword()));
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getBirthDate(), entity.getBirthDate());
		assertEquals(view.getSignUpDate(), entity.getSignUpDate());
		assertEquals(view.getAddress(), entity.getAddress());
	}

	/**
	 * Generate a dummy {@link ViewCustomer} usable for tests.
	 * @return {@link ViewCustomer}.
	 */
	static ViewCustomer generateDummyViewCustomer() {
		ViewCustomer view  = new ViewCustomer();
		TestViewUser.setDummyValues(view);
		view.setBirthDate(LocalDate.now());
		view.setAddress(TestAddress.generateAddress());
		return view;
	}
}