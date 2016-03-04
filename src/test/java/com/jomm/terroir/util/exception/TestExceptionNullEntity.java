package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.USER_SHOULD_NOT_BE_NULL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;

/**
 * This Class is a Junit test case testing {@link ExceptionNullEntity}.
 * @author Maic
 */
public class TestExceptionNullEntity {
	 
	 private ExceptionNullEntity exception;

	/**
	 * Test method for {@link ExceptionNullEntity#getMessage()}.
	 */
	@Test
	public final void testGetMessage() {
		exception = new ExceptionNullEntity();
		exception.setResourceBundle(Resources.getResourceBundleError());
		assertEquals(TestResources.getResourceBundleError(USER_SHOULD_NOT_BE_NULL.getKey()), 
				exception.getMessage());
	}
	
	/**
	 * Generate a mocked {@link ExceptionNullEntity} usable for tests which
	 * method {@code getMessage()} returns 
	 * {@link com.jomm.terroir.util.Constants.ResourceBundleError#USER_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionNullEntity}.
	 */
	public static ExceptionNullEntity createMockedException() {
		return createException(TestResources.getResourceBundleError(USER_SHOULD_NOT_BE_NULL.getKey()));
	}
	
	/**
	 * Generate a mocked {@link ExceptionNullEntity} usable for tests.
	 * @param exceptionMessage String the message returned by method {@code getMessage()}.
	 * @return the {@link ExceptionNullEntity}.
	 */
	private static ExceptionNullEntity createException(String exceptionMessage) {
		ExceptionNullEntity exception = mock(ExceptionNullEntity.class);
		when(exception.getMessage()).thenReturn(exceptionMessage);
		return exception;
	}
}