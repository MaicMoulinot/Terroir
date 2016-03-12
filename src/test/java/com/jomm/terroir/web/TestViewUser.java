package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_USER;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_RULES;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.PASSWORD_TITLE;
import static com.jomm.terroir.util.Constants.View.CLIENT_ID_GROWL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.faces.application.FacesMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.AbstractUser;
import com.jomm.terroir.business.model.Customer;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService;
import com.jomm.terroir.util.exception.TestExceptionService;

/**
 * This class is a Junit test case testing the methods of {@link ViewUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link ViewUser} with its associated concrete child of {@link AbstractUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestViewUser {

	private ViewUser view;
	private AbstractUser user;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestViewUser#childToTest()}.
	 * @param view the concrete child of {@link ViewUser}.
	 * @param user the concrete child of {@link AbstractUser}.
	 */
    public TestViewUser(ViewUser view, AbstractUser user) {
        this.view = view;
        this.user = user;
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
		when(view.userService.create(any(user.getClass()))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractView.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getResourceBundleError(EXCEPTION.getKey()), 
				view.generateExceptionMessage(exception, view.getId(), user));
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
		when(view.userService.create(any(user.getClass()))).thenThrow(exception);
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractView.checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getResourceBundleError(EXCEPTION.getKey()), 
				view.generateExceptionMessage(exception, view.getId(), user));
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
		verify(view.userService).create(any(user.getClass()));
		// check if a FacesMessage was correctly thrown
		TestAbstractView.checkMessageWithParametrizedDetail(view, null, FacesMessage.SEVERITY_INFO, 
				TestResources.getResourceBundleMessage(CREATE_OK.getKey()), 
				TestResources.getResourceBundleMessage(CREATE_USER.getKey()));
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
		// check if a FacesMessage was correctly thrown
		TestAbstractView.checkMessageWithPlainDetail(view, CLIENT_ID_GROWL.getId(), FacesMessage.SEVERITY_INFO, 
				TestResources.getResourceBundleMessage(PASSWORD_TITLE.getKey()), 
				TestResources.getResourceBundleMessage(PASSWORD_RULES.getKey()));
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
	 * Set mocked {@link ServiceUser}, and a dummy {@link java.util.logging.Logger} into view.
	 * Call {@link TestAbstractView#setInjections(AbstractView)}.
	 */
	private void setInjections() {
		TestAbstractView.setInjections(view);
		view.userService = mock(ServiceUser.class);
		view.logger = TestResources.createLogger(view.getClass());
	}
	
	/**
	 * Generate a dummy {@link ViewUser} usable for tests.
	 * @param user the {@link AbstractUser} defining appropriate view.
	 * @return {@link ViewUser}.
	 */
	static ViewUser generateDummyViewUser(AbstractUser user) {
		ViewUser view = null;
		if (user != null) {
			if (user instanceof Seller) {
				view = TestViewSeller.generateDummyViewSeller();
			} else if (user instanceof Customer) {
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
	 * and the associated concrete child of {@link AbstractUser},
	 * to be used as parameters on constructor.
	 * Each iteration will be tested with all test methods.
	 * @return {@code Iterable<Object[]>} with both parameters.
	 */
	@Parameters
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomer(), new Customer()},
			{new ViewSeller(), new Seller()}
			}
		);
	}
}