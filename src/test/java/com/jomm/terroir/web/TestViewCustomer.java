package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestCustomer;

/**
 * This class is a Junit test case testing {@link ViewCustomer}.
 * @author Maic
 */
public class TestViewCustomer {

	private ViewCustomer view;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = generateDummyViewCustomer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link ViewCustomer#generateYearRange()}.
	 */
	@Test
	public final void testGenerateYearRange() {
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
		// BirthDate
		LocalDate birthdate = LocalDate.now();
		view.setBirthDate(birthdate);
		assertEquals("BirthDate should be " + birthdate, birthdate, view.getBirthDate());

		// SignUpDate
		ZonedDateTime date = ZonedDateTime.now();
		view.setSignUpDate(date);
		assertEquals("SignUpDate should be " + date, date, view.getSignUpDate());
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
		assertEquals(view.getPassword(), entity.getPassword());
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getBirthDate(), entity.getBirthDate());
		assertEquals(view.getSignUpDate(), entity.getSignUpDate());
	}

	/**
	 * Generate a dummy {@link ViewCustomer} usable for tests.
	 * @return {@link ViewCustomer}.
	 */
	static ViewCustomer generateDummyViewCustomer() {
		ViewCustomer view  = new ViewCustomer();
		TestViewUser.setDummyValues(view);
		view.setBirthDate(LocalDate.now());
		return view;
	}
}