package com.jomm.terroir.business.validator;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jomm.terroir.business.UserService;
import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the <code>validate()</code> method of {@link UsernameValidator}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class UsernameValidatorTest {
	
	@Mock(name = "service")
    private UserService service;
	
	@InjectMocks
	private UsernameValidator validator;

	/**
	 * Test method for {@link UsernameValidator#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidate() {
		// Set proper ResourceBundle for the validator
		ResourceBundle resource = ResourceBundle.getBundle(Resources.BUNDLE_ERROR, Locale.getDefault());
		validator.setResourceBundle(resource);

		// Test with Username null and empty
		try {
			validator.validate(null, null, null);
			assertTrue(true); // Assert no ValidatorException was thrown
			validator.validate(null, null, "");
			assertTrue(true); // Assert no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			fail("ValidatorException was thrown and should not have with username null or empty");
		}
		
		// Test with Username's length < 6
		try {
			validator.validate(null, null, "user");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with lenght < 6");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(UsernameValidator.LENGTH_AT_LEAST_6_CHARACTERS), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Test with Username's length > 6
		try {
			validator.validate(null, null, "UserName");
			verify(service).isExistingUserName(anyString()); // validate that service.isExistingUserName() was called
		} catch (ValidatorException expectedException) {
			fail("ValidatorException was thrown and should not have");
		}
	}
}