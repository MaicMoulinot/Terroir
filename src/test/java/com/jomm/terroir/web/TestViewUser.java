package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.USER_REGISTRED;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ViewUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link ViewUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestViewUser {

	private ViewUser view;
	private Class<AbstractUser> classUser;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestViewUser#childToTest()}.
	 * @param view the concrete child of {@link ViewUser}.
	 * @param classUser the class of {@link AbstractUser}.
	 */
    public TestViewUser(ViewUser view, Class<AbstractUser> classUser) {
        this.view = view;
        this.classUser = classUser;
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
		ExceptionService exception = TestExceptionService.createMockedExceptionEntityShouldNotBeNull();
		when(view.userService.create(any(classUser))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ENTITY_SHOULD_NOT_BE_NULL.getKey()));
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
		ExceptionService exception = TestExceptionService.createMockedExceptionIdShouldBeNull();
		when(view.userService.create(any(classUser))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Customer.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		checkExceptionMessage(messageCaptor.getValue(), TestResources.getResourceBundleError(ID_SHOULD_BE_NULL.getKey()));
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
		verify(view.userService).create(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		assertEquals(TestResources.getResourceBundleMessage(USER_REGISTRED.getKey()), message.getSummary());
	}

	/**
	 * Test method for {@link ViewUser#passwordTooltip()}.
	 */
	@Test
	public final void testPasswordTooltip() {
		// initialization
		setInjections();
		// call
		view.passwordTooltip();
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.getFacesContext()).addMessage(stringCaptor.capture(), messageCaptor.capture());
        // retrieve the captured String and check if it contains the expected value
        assertEquals(CLIENT_ID_GROWL.getId(), stringCaptor.getValue());
        // retrieve the captured FacesMessage and check if it contains the expected values
        FacesMessage message = messageCaptor.getValue();
        assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(TestResources.getResourceBundleMessage(PASSWORD_TITLE.getKey()), message.getSummary());
        assertEquals(TestResources.getResourceBundleMessage(PASSWORD_RULES.getKey()), message.getDetail());
	}

	/**
	 * Test method for {@link ViewUser}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Long nb = (long) 0;
		String test = "test";
		
		// Id
		view.setId(nb);
		assertEquals("Id should be " + nb, nb, view.getId());
		
		// FirstName
		view.setFirstName(test);
		assertEquals("FirstName should be " + test, test, view.getFirstName());
		
		// LastName
		view.setLastName(test);
		assertEquals("LastName should be " + test, test, view.getLastName());
		
		// UserName
		view.setUserName(test);
		assertEquals("UserName should be " + test, test, view.getUserName());
		
		// Email
		view.setEmail(test);
		assertEquals("Email should be " + test, test, view.getEmail());
		
		// Password
		view.setPassword(test);
		assertEquals("Password should be " + test, test, view.getPassword());
	}
	
	/**
	 * Check if the thrown {@link FacesMessage} has correct values.
	 * @param message the {@link FacesMessage}.
	 * @param detail String the message's detail.
	 */
	private void checkExceptionMessage(FacesMessage message, String detail) {
		// check severity
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
		// check summary
        assertEquals(TestResources.getResourceBundleError(EXCEPTION.getKey()), message.getSummary());
        // check detail
		assertTrue(message.getDetail().contains(detail));
	}
	
	/**
	 * Check if the thrown {@link FacesMessage} has correct values.
	 * @param message the {@link FacesMessage}.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail.
	 */
	private void checkValidationMessage(FacesMessage message, String summary, String detail) {
		// check severity
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		// check summary
        assertEquals(summary, message.getSummary());
        // check detail
        String[] detailParts = detail.replace("''", "'").split(Pattern.quote(" {0} "));
        assertTrue(message.getDetail().startsWith(detailParts[0]));
        assertTrue(message.getDetail().endsWith(detailParts[1]));
	}
	
	/**
	 * Set mocked {@link FacesContext}, mocked {@link ServiceUser},
	 * and a dummy {@link java.util.logging.Logger} into view.
	 * Retrieve the {@link java.util.ResourceBundle} Message from {@link Resources}.
	 */
	private void setInjections() {
		view.setFacesContext(mock(FacesContext.class));
		view.userService = mock(ServiceUser.class);
		view.setResourceBundleError(Resources.getResourceBundleError());
		view.setResourceBundleMessage(Resources.getResourceBundleMessage());
		view.logger = TestResources.createLogger(view.getClass());
	}
	
	/**
	 * Generate a dummy {@link ViewUser} usable for tests.
	 * @param classUser the class of {@link AbstractUser}.
	 * @return {@link ViewUser}.
	 */
	static ViewUser generateDummyViewUser(Class<AbstractUser> classUser) {
		ViewUser view = null;
		if (classUser != null) {
			if (classUser.isAssignableFrom(Seller.class)) {
				view = TestViewSeller.generateDummyViewSeller();
			} else if (classUser.isAssignableFrom(Customer.class)) {
				view = TestViewCustomer.generateDummyViewCustomer();
			}
		}
		return view;
	}
	
	/**
	 * Set dummy values to a {@link ViewUser} usable for tests.
	 * @param view the {@link ViewUser} to be set.
	 */
	static void setDummyValues(ViewUser view) {
		view.setFirstName("FirstName");
		view.setLastName("LastName");
		view.setUserName("UserName");
		view.setEmail("email@email.com");
		view.setPassword("Z@bf0sdfZnd");
	}
	
	/**
	 * Reference a list of all {@link ViewUser}'s concrete children, 
	 * and the class of the associated child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomer(), Customer.class},
			{new ViewSeller(), Seller.class}
			}
		);
	}
}