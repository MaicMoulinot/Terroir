package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Resources.getValueFromKey;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

/**
 * This Class is a Junit test case testing {@link ExceptionService}.
 * @author Maic
 */
public class TestExceptionService {
	
	// Variables //-----------------------------------------------
	private ExceptionService exception;

	// Test methods //--------------------------------------------
	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ENTITY_SHOULD_NOT_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionEntityShouldNotBeNull() {
		exception = new ExceptionService(ENTITY_SHOULD_NOT_BE_NULL);
		assertEquals(getValueFromKey(ENTITY_SHOULD_NOT_BE_NULL), exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ID_SHOULD_NOT_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionIdShouldNotBeNull() {
		exception = new ExceptionService(ID_SHOULD_NOT_BE_NULL);
		assertEquals(getValueFromKey(ID_SHOULD_NOT_BE_NULL), exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ID_SHOULD_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionIdShouldBeNull() {
		exception = new ExceptionService(ID_SHOULD_BE_NULL);
		assertEquals(getValueFromKey(ID_SHOULD_BE_NULL), exception.getMessage());
	}
	
	// Static methods //------------------------------------------
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ID_SHOULD_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionIdShouldBeNull() {
		return createException(getValueFromKey(ID_SHOULD_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ID_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionIdShouldNotBeNull() {
		return createException(getValueFromKey(ID_SHOULD_NOT_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ENTITY_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionEntityShouldNotBeNull() {
		return createException(getValueFromKey(ENTITY_SHOULD_NOT_BE_NULL));
	}
	
	// Helpers //-------------------------------------------------
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests.
	 * @param exceptionMessage String the message returned by method {@code getMessage()}.
	 * @return the {@link ExceptionService}.
	 */
	private static ExceptionService createException(String exceptionMessage) {
		ExceptionService exception = mock(ExceptionService.class);
		when(exception.getMessage()).thenReturn(exceptionMessage);
		return exception;
	}
}