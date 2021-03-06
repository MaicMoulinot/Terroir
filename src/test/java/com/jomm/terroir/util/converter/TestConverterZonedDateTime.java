package com.jomm.terroir.util.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is a Junit test case testing the {@code getAsObject()} and {@code getAsString()} 
 * methods of {@link ConverterZonedDateTime}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are different values to be tested, and their expected results.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestConverterZonedDateTime {
	
	// Constants //-----------------------------------------------
	/** Enumeration of different possible results for {@code getAsObject()} method of {@link ConverterZonedDateTime}. */
	private enum GetAsObjectExpectedResult {
		/** The value is null, thus the result should be null. */
		FAILURE_NULL,
		/** The value is not a valid pattern, thus the result should be null. */
		FAILURE_PATTERN,
		/** The value is a valid pattern, thus the conversion should succeed. */
		SUCCESS
	};

	/** Enumeration of different possible results for {@code getAsString()} method of {@link ConverterZonedDateTime}. */
	private enum GetAsStringExpectedResult {
		/** The value is null, thus the result should be null. */
		FAILURE_NULL,
		/** The value is not a ZonedDateTime, thus the result should be null. */
		FAILURE_CLASS_CAST,
		/** The value is a ZonedDateTime, thus the conversion should succeed. */
		SUCCESS
	};

	// Variables //-----------------------------------------------
	private ConverterZonedDateTime converter;
	private String stringValue;
	private GetAsObjectExpectedResult getAsObjectExpectedResult;
	private Object objectValue;
	private GetAsStringExpectedResult getAsStringExpectedResult;

	// Constructors //--------------------------------------------
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@code valueToTest()}.
	 * @param stringValue String the value for {@code testGetAsObjectWithDifferentValues()}.
	 * @param getAsObjectExpectedResult the {@code stringValue}'s expected result in test.
	 * @param objectValue the value to test in {@code testGetAsStringWithDifferentValues()}.
	 * @param getAsStringExpectedResult the {@code objectValue}'s expected result in test.
	 */
	public TestConverterZonedDateTime(String stringValue, GetAsObjectExpectedResult getAsObjectExpectedResult, 
			Object objectValue, GetAsStringExpectedResult getAsStringExpectedResult) {
		// Set the parameters for the test
		this.stringValue = stringValue;
		this.getAsObjectExpectedResult = getAsObjectExpectedResult;
		this.objectValue = objectValue;
		this.getAsStringExpectedResult = getAsStringExpectedResult;
		// Initialize the converter and mock the logger
		converter = new ConverterZonedDateTime();
		converter.setTestLogger(mock(Logger.class));
	}

	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link ConverterZonedDateTime#getAsObject(FacesContext, UIComponent, String)}.
	 */
	@Test
	public final void testGetAsObjectWithDifferentValues() {
		Object result = converter.getAsObject(mock(FacesContext.class), mock(UIComponent.class), stringValue);
		switch (getAsObjectExpectedResult) {
		case FAILURE_NULL: // Expected result is null
		case FAILURE_PATTERN: // Expected result is null
			assertNull("Result should be null with value=" + stringValue, result);				
			break;
		case SUCCESS: // Expected result is a ZonedDateTime
			assertEquals("Conversion should succeed with value=" + stringValue, ZonedDateTime.class, result.getClass());				
			break;
		}
	}

	/**
	 * Test method for {@link ConverterZonedDateTime#getAsString(FacesContext, UIComponent, Object)}.
	 */
	@Test
	public final void testGetAsStringWithDifferentValues() {
		String result = converter.getAsString(mock(FacesContext.class), mock(UIComponent.class), objectValue);
		switch (getAsStringExpectedResult) {
		case FAILURE_NULL: // Expected result is null
		case FAILURE_CLASS_CAST: // Expected result is null
			assertNull("Result should be null with value=" 
					+ (objectValue == null ? objectValue : objectValue.getClass()), result);				
			break;
		case SUCCESS: // Expected result is a String
			ZonedDateTime dateValue = (ZonedDateTime) objectValue;
			String expectedResult = dateValue.format(ConverterZonedDateTime.getTestFormatter());
			assertEquals("Conversion should succeed with value=" 
					+ (objectValue == null ? objectValue : objectValue.getClass()), 
					expectedResult, result);				
			break;
		}
	}

	// Static methods //------------------------------------------
	/**
	 * Reference values, associated with their expected result to the test, 
	 * to be used as parameters on constructor.
	 * Each value will be tested with the tests {@code testGetAsObjectWithDifferentValues()} or 
	 * {@code testGetAsStringWithDifferentValues()}.
	 * @return {@code Iterable<Object[]>} with the parameters.
	 */
	@Parameters
	public static Iterable<Object[]> valueToTest() {
		return Arrays.asList(new Object[][] {
			{null, GetAsObjectExpectedResult.FAILURE_NULL, null, GetAsStringExpectedResult.FAILURE_NULL},
			{"23-12-2016 23:52:36 GMT", GetAsObjectExpectedResult.FAILURE_PATTERN, 
				LocalDate.now(), GetAsStringExpectedResult.FAILURE_CLASS_CAST},
			{"27/02/2016 23:52:36 GMT", GetAsObjectExpectedResult.SUCCESS, 
				ZonedDateTime.now(), GetAsStringExpectedResult.SUCCESS}
		});
	}
}