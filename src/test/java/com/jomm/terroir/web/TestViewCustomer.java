package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.TestCustomer;
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Resources;

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
	 * Test method for {@link ViewCustomer#create()} when entity is null.
	 */
	@Test
	public final void testCreateWithNullPointerException() {
		// initialization
		setInjections();
		// call to create()
		when(view.userService.create(any(Customer.class))).thenThrow(new NullPointerException());
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewCustomer#create()} with id not null.
	 */
	@Test
	public final void testCreateWithIllegalArgumentException() {
		// initialization
		setInjections();
		// call to create()
		when(view.userService.create(any(Customer.class))).thenThrow(new IllegalArgumentException());
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(Constants.ID_SHOULD_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewCustomer#create()} with id null.
	 */
	@Test
	public final void testCreateWithEntityIdNull() {
		// initialization
		setInjections();
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage
        FacesMessage message = messageCaptor.getValue();
        // check if the captured FacesMessage contains the expected values
        assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resourceMessage.getString(Constants.USER_REGISTRED), message.getSummary());
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
		assertEquals(view.getPassword(), entity.getUserPassword());
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
		view.setFirstName("FirstName");
		view.setLastName("LastName");
		view.setUserName("UserName");
		view.setEmail("email@email.com");
		view.setPassword("Zqdvb35d@jhg");
		view.setBirthDate(LocalDate.now());
		return view;
	}
	
	/**
	 * Set mocked {@link javax.faces.context.FacesContext}, and mocked {@link ServiceUser} into view.
	 * Retrieve the {@link java.util.ResourceBundle}s from {@link Resources}.
	 */
	private void setInjections() {
		view.facesContext = mock(FacesContext.class);
		view.userService = mock(ServiceUser.class);
		view.resourceError = Resources.getResourceBundleError();
		view.resourceMessage = Resources.getResourceBundleMessage();
	}
}