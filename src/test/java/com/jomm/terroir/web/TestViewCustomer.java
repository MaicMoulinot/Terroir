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
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;
import com.jomm.terroir.util.exception.TestExceptionInvalidId;
import com.jomm.terroir.util.exception.TestExceptionNullEntity;

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
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithEntityNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionNullEntity exception = TestExceptionNullEntity.createMockedException();
		when(view.userService.create(any(Customer.class))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
		assertEquals(TestResources.getResourceBundleError(Constants.USER_SHOULD_NOT_BE_NULL), 
				message.getSummary());
	}

	/**
	 * Test method for {@link ViewCustomer#create()} with id not null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithIdNotNull() throws Exception {
		// initialization
		setInjections();
		// Simulate an exception thrown by service
		ExceptionInvalidId exception = TestExceptionInvalidId.createMockedExceptionIdShouldBeNull();
		when(view.userService.create(any(Customer.class))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
		assertEquals(TestResources.getResourceBundleError(Constants.ID_SHOULD_BE_NULL), 
				message.getSummary());
	}

	/**
	 * Test method for {@link ViewCustomer#create()} with id null.
	 * @throws Exception should not be thrown.
	 */
	@Test
	public final void testCreateWithIdNull() throws Exception {
		// initialization
		setInjections();
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		assertEquals(view.resource.getString(Constants.USER_REGISTRED), message.getSummary());
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
	 * Set mocked {@link FacesContext}, mocked {@link ServiceUser},
	 * and a dummy {@link java.util.logging.Logger} into view.
	 * Retrieve the {@link java.util.ResourceBundle} Message from {@link Resources}.
	 */
	private void setInjections() {
		view.facesContext = mock(FacesContext.class);
		view.userService = mock(ServiceUser.class);
		view.resource = Resources.getResourceBundleMessage();
		view.setLogger(TestResources.createLogger(this.getClass()));
	}
}