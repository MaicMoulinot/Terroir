package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.USER_REGISTRED;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

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
import com.jomm.terroir.util.exception.ExceptionInvalidId;
import com.jomm.terroir.util.exception.ExceptionNullEntity;
import com.jomm.terroir.util.exception.TestExceptionInvalidId;
import com.jomm.terroir.util.exception.TestExceptionNullEntity;

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
	private static final char[] PASSWORD = { 'Z', 'm', '@', 't', 'g', 'e', 'Q', 3 };
	
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
		ExceptionNullEntity exception = TestExceptionNullEntity.createMockedException();
		when(view.userService.create(any(classUser))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
		assertEquals(TestResources.getResourceBundleError(USER_SHOULD_NOT_BE_NULL.getKey()), 
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
		when(view.userService.create(any(classUser))).thenThrow(exception);
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
		assertEquals(TestResources.getResourceBundleError(ID_SHOULD_BE_NULL.getKey()), 
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
		verify(view.userService).create(any(classUser));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
		assertEquals(view.resource.getString(USER_REGISTRED.getKey()), message.getSummary());
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
		verify(view.facesContext).addMessage(stringCaptor.capture(), messageCaptor.capture());
        // retrieve the captured String and check if it contains the expected value
        assertEquals(CLIENT_ID_GROWL.getId(), stringCaptor.getValue());
        // retrieve the captured FacesMessage and check if it contains the expected values
        FacesMessage message = messageCaptor.getValue();
        assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resource.getString(PASSWORD_TITLE.getKey()), message.getSummary());
        assertEquals(view.resource.getString(PASSWORD_RULES.getKey()), message.getDetail());
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
		view.setPassword(PASSWORD);
		assertEquals("Password should be " + PASSWORD.toString(), PASSWORD, view.getPassword());
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
		view.setPassword(PASSWORD);
	}
	
	/**
	 * Reference a list of all {@link ViewUser}'s concrete children to be used as parameter on constructor.
	 * @return {@code Iterable<Object[]>}.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomer(), Customer.class},
			{new ViewSeller(), Seller.class}
			}
		);
	}
}