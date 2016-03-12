package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ENTITY_SHOULD_NOT_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.jomm.terroir.util.TestResources;
import com.jomm.terroir.util.exception.ExceptionService.TypeException;

/**
 * This Class is a Junit test case testing {@link ExceptionService}.
 * @author Maic
 */
public class TestExceptionService {
	
	private ExceptionService exception;

	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ENTITY_SHOULD_NOT_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionEntityShouldNotBeNull() {
		exception = new ExceptionService(TypeException.ENTITY_NULL);
		assertEquals(TestResources.getValueFromResourceBundle(ENTITY_SHOULD_NOT_BE_NULL), exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ID_SHOULD_NOT_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionIdShouldNotBeNull() {
		exception = new ExceptionService(TypeException.ID_NULL);
		assertEquals(TestResources.getValueFromResourceBundle(ID_SHOULD_NOT_BE_NULL), exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionService#ExceptionService(TypeException)}
	 * with TypeException is {@code ID_SHOULD_BE_NULL}.
	 */
	@Test
	public final void testConstructorWithTypeExceptionIdShouldBeNull() {
		exception = new ExceptionService(TypeException.ID_NOT_NULL);
		assertEquals(TestResources.getValueFromResourceBundle(ID_SHOULD_BE_NULL), exception.getMessage());
	}
	
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ID_SHOULD_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionIdShouldBeNull() {
		return createException(TestResources.getValueFromResourceBundle(ID_SHOULD_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ID_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionIdShouldNotBeNull() {
		return createException(TestResources.getValueFromResourceBundle(ID_SHOULD_NOT_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionService} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#ENTITY_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionService}.
	 */
	public static ExceptionService createMockedExceptionEntityShouldNotBeNull() {
		return createException(TestResources.getValueFromResourceBundle(ENTITY_SHOULD_NOT_BE_NULL));
	}
	
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