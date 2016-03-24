package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EMAIL_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.ServiceUser;

/**
 * This class is a Junit test case testing the {@code validate()} method of {@link ValidatorEmail}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different emails to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorEmail {

	// Enumeration of different possible results for {@code validate()} method of {@link ValidatorEmail}.
	private enum ExpectedResult {
		/** The value is not a valid pattern, thus the validation should fail. */
		FAILURE,
		/** The value is a valid pattern, thus the validation should succeed. */
		SUCCESS,
		/** The value is null or empty, thus the validation should not fail. */
		NOTHING
	};

	// Attributes
	private ValidatorEmail validator;
	private ServiceUser service;
	private String email;
	private ExpectedResult expectedResult;

	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorEmail#valueToTest()}.
	 * @param email String the email to be tested.
	 * @param expectedResult Result the expected result of the validation.
	 */
	public TestValidatorEmail(String email, ExpectedResult expectedResult) {
		// Set the parameters for the test
		this.email = email;
		this.expectedResult = expectedResult;
		// Initialize the validator
		validator = new ValidatorEmail();
		service = mock(ServiceUser.class);
		validator.setServiceUser(service);
	}

	/**
	 * Test method {@link ValidatorEmail#validate(javax.faces.context.FacesContext, 
	 * javax.faces.component.UIComponent, java.lang.Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		try {
			validator.validate(null, null, email); // here a ValidatorException can occur
			switch (expectedResult) {
			case NOTHING: // This is expected
				assertTrue("ValidatorException is not thrown, which is expected with a pattern " + email, true);				
				break;
			case SUCCESS: // This is expected
				verify(service).isExistingEmail(anyString()); // validate that service.isExistingEmail() was called				
				break;
			case FAILURE: // This is NOT expected
				fail("ValidatorException was not thrown and should have with pattern " + email);				
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case NOTHING: // This is NOT expected
			case SUCCESS: // This is NOT expected
				fail("ValidatorException was thrown and should not have with a pattern " + email);			
				break;
			case FAILURE: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with pattern " + email, 
						getValueFromKey(EMAIL_NOT_MATCHING_PATTERN), 
						expectedException.getFacesMessage().getSummary());		
				break;
			}
		}
	}

	/**
	 * Reference a list of email, associated with its expected result, to be used as parameters on constructor.
	 * Each email will be tested with the test {@link TestValidatorEmail#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must not fail with null or empty values
			{null, ExpectedResult.NOTHING},
			{"", ExpectedResult.NOTHING},
			// The validation must succeed with correct values
			{"firstname@mail.com", ExpectedResult.SUCCESS},
			{"firstname@mail.fr", ExpectedResult.SUCCESS},
			{"fi.rstn%me_9+-@mail.com2.ca", ExpectedResult.SUCCESS},
			// The validation must fail with incorrect values
			// first part
			{"@mail.com", ExpectedResult.FAILURE},
			{"first$name@mail.com", ExpectedResult.FAILURE},
			// second part @
			{"firstnamemail.com", ExpectedResult.FAILURE},
			{"firstname@@mail.com", ExpectedResult.FAILURE},
			{"firstname@ma@il.com", ExpectedResult.FAILURE},
			// third part domain
			{"firstname@.com", ExpectedResult.FAILURE},
			{"firstname@ma_il.com", ExpectedResult.FAILURE},
			{"firstname@mail$.com", ExpectedResult.FAILURE},
			{"firstname@mailcom", ExpectedResult.FAILURE},
			// fourth part ccTLD, optional
			{"firstname@mail.c+om.au", ExpectedResult.FAILURE},
			// firth part ccTLD
			{"firstname@mail.", ExpectedResult.FAILURE},
			{"firstname@mail.c", ExpectedResult.FAILURE},
			{"firstname@mail.23", ExpectedResult.FAILURE}
		});
	}
}