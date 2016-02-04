package com.jomm.terroir.business.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIInput;
import javax.faces.validator.ValidatorException;

import org.junit.Test;

import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the <code>validate()</code> method of {@link PasswordValidator}.
 * @author Maic
 */
public class PasswordValidatorTest {

	/**
	 * Test method for {@link PasswordValidator#validate(javax.faces.context.FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testValidate() {
		// Set proper ResourceBundle for the validator
		ResourceBundle resource = ResourceBundle.getBundle(Resources.BUNDLE_ERROR, Locale.getDefault());
		PasswordValidator validator = new PasswordValidator();
		validator.setResourceBundle(resource);
		
		// Create UIComponent and UIInput
		UIComponent component = new UIComponentBase() {
			@Override
			public String getFamily() {
				return "TestComponent";
			}
		};
		UIInput uiInput = new UIInput();
		
		// Test with Password1 is null
		try {
			uiInput.setValue(null);
			component.getAttributes().put(PasswordValidator.PASSWORD_PARAMETER, uiInput);
			validator.validate(null, component, "Password2");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password1 null");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(PasswordValidator.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Test with Password2 is null
		try {
			uiInput.setValue("Password1");
			validator.validate(null, component, null);
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password2 null");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(PasswordValidator.FIELD_MANDATORY), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Test with Password1 and Password2 don't match
		try {
			uiInput.setValue("Password1");
			validator.validate(null, component, "Password2");
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have with password1 and password2 different");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(PasswordValidator.PASSWORDS_DONT_MATCH), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Test with Password don't match pattern
		try {
			String password = "password";
			uiInput.setValue(password);
			validator.validate(null, component, password);
			// Should throw a ValidatorException. If not fail the test
			fail("ValidatorException was not thrown and should have password not matching the pattern");
		} catch (ValidatorException expectedException) {
			assertEquals(resource.getString(PasswordValidator.PASSWORD_TOO_SIMPLE), 
					expectedException.getFacesMessage().getSummary());
		}
		
		// Test with Password match pattern
		try {
			String password = "Za3@hGm450@";
			uiInput.setValue(password);
			validator.validate(null, component, password);
			assertTrue(true); // Just validate no ValidatorException was thrown
		} catch (ValidatorException expectedException) {
			fail("ValidatorException was thrown and should have with password matching the pattern");
		}
	}
}