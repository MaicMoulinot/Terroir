package com.jomm.terroir.business.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
 * This class is a Junit test case testing the <code>validate()</code> method of {@link EmailValidator}.
 * It is annotated {@link RunWith} {@link MockitoJUnitRunner} to explicit usage of Mockito annotations.
 * @author Maic
 */
@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {
	
	@Mock(name = "service")
    private UserService service;
	
	@InjectMocks
	private EmailValidator validator;

	/**
	 * Test method for {@link EmailValidator#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidate() {
		// Set proper ResourceBundle for the validator
		ResourceBundle resource = ResourceBundle.getBundle(Resources.BUNDLE_ERROR, Locale.getDefault());
		validator.setResourceBundle(resource);
		
		// Email is null
		try {
			validator.validate(null, null, null);
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with email null");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(EmailValidator.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Email is not valid ("Email")
		try {
			validator.validate(null, null, "Email");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with email non valid");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(EmailValidator.EMAIL_UNVALID), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Email is valid ("email@email.com")
		try {
			validator.validate(null, null, "email@email.com");
			verify(service).isExistingEmail(anyString()); // validate that service.isExistingEmail() was called
		} catch (ValidatorException expectedException) {
			fail("ValidatorException was thrown and should not have");
		}
	}
}
