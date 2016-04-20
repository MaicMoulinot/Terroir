package com.jomm.terroir.business.validator;

import static com.jomm.terroir.util.Constants.ResourceBundleError.LENGTH_BETWEEN_5_AND_15;
import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_NAME_NOT_MATCHING_PATTERN;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.jomm.terroir.business.ServiceUser;

/**
 * This class is a Junit test case testing the {@code validateChangedObject()} method of {@link ValidatorUsername}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different user names to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestValidatorUsername {
	
	// Constants //-----------------------------------------------
	/** 
	 * Enumeration of different possible results for {@code validateChangedObject()} 
	 * method of {@link ValidatorUsername}.
	 */
	private enum ExpectedResult {
		/** The value is not between 3 and 15 characters, thus the validation should fail. */
		FAILURE_LENGTH,
		/** The value is not a valid pattern, thus the validation should fail. */
		FAILURE_PATTERN,
		/** The value is a valid pattern, thus the validation should succeed. */
		SUCCESS,
		/** The value is null or empty, thus the validation should not fail. */
		NOTHING
	};

	// Variables //-----------------------------------------------
	private ValidatorUsername validator;
	private ServiceUser service;
	private String username;
	private ExpectedResult expectedResult;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestValidatorUsername#valueToTest()}.
	 * @param username String the user name to be tested.
	 * @param expectedResult Result the expected result of the validation.
	 */
	public TestValidatorUsername(String username, ExpectedResult expectedResult) {
		// Set the parameters for the test
		this.username = username;
		this.expectedResult = expectedResult;
		// Initialize the validator and mocked service
		service = mock(ServiceUser.class);
		validator = new ValidatorUsername();
		validator.setTestServiceUser(service);
	}

	// Test methods //--------------------------------------------
	/**
	 * Test method {@link ValidatorUsername#validateChangedObject(FacesContext, 
	 * UIComponent, Object)}.
	 */
	@Test
	public final void testValidateWithDifferentValues() {
		try {
			// here a ValidatorException can occur
			validator.validateChangedObject(mock(FacesContext.class), mock(UIComponent.class), username);
			switch (expectedResult) {
			case NOTHING: // This is expected
				assertTrue("ValidatorException is not thrown, which is expected with a pattern " + username, true);				
				break;
			case SUCCESS: // This is expected
				verify(service).isExistingUserName(anyString()); // validate that service.isExistingUserName() was called				
				break;
			case FAILURE_LENGTH: // This is NOT expected
			case FAILURE_PATTERN: // This is NOT expected
				fail("ValidatorException was not thrown and should have with pattern " + username);				
				break;
			}
		} catch (ValidatorException expectedException) {
			switch (expectedResult) {
			case NOTHING: // This is NOT expected
			case SUCCESS: // This is NOT expected
				fail("ValidatorException was thrown and should not have with a pattern " + username + ", " 
						+ expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_LENGTH: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with pattern " + username, 
						getValueFromKey(LENGTH_BETWEEN_5_AND_15), 
						expectedException.getFacesMessage().getSummary());		
				break;
			case FAILURE_PATTERN: // This is expected
				assertEquals("ValidatorException was thrown, which is expected with pattern " + username, 
						getValueFromKey(USER_NAME_NOT_MATCHING_PATTERN), 
						expectedException.getFacesMessage().getSummary());		
				break;
			}
		}
	}

	// Static methods //------------------------------------------
	/**
	 * Reference a list of user names, associated with its expected result, 
	 * to be used as parameters on constructor.
	 * Each username will be tested with the test 
	 * {@link TestValidatorUsername#testValidateWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			// The validation must not fail with null or empty values
			{null, ExpectedResult.NOTHING},
			{"", ExpectedResult.NOTHING},
			// The validation must succeed with correct values
			{"Us3rN@me", ExpectedResult.SUCCESS},
			{"azAZ09._@#$%-", ExpectedResult.SUCCESS},
			// The validation must fail with incorrect values
			// length
			{"us", ExpectedResult.FAILURE_LENGTH},
			{"usernameusernameusername", ExpectedResult.FAILURE_LENGTH},
			// invalid symbol
			{"user&name", ExpectedResult.FAILURE_PATTERN},
			{"user^name", ExpectedResult.FAILURE_PATTERN}
		});
	}
}