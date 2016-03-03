package com.jomm.terroir.util.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;

/**
 * @author Maic
 *
 */
public class TestExceptionInvalidId {

	/**
	 * Test method for {@link ExceptionInvalidId#ExceptionInvalidId(boolean)}
	 * with boolean true.
	 */
	@Test
	public final void testConstructorWithBooleanTrue() {
		ExceptionInvalidId exception = new ExceptionInvalidId(true);
		assertTrue(exception.isIdShouldBeNull());
	}
	
	/**
	 * Test method for {@link ExceptionInvalidId#ExceptionInvalidId(boolean)}
	 * with boolean false.
	 */
	@Test
	public final void testConstructorWithBooleanFalse() {
		ExceptionInvalidId exception = new ExceptionInvalidId(false);
		assertFalse(exception.isIdShouldBeNull());
	}

	/**
	 * Test method for {@link ExceptionInvalidId#getMessage()} with id should be null.
	 */
	@Test
	public final void testGetMessageWithIdShouldBeNull() {
		ExceptionInvalidId exception = new ExceptionInvalidId(true);
		exception.resource = Resources.getResourceBundleError();
		assertEquals(TestResources.getResourceBundleError(Constants.ID_SHOULD_BE_NULL), 
				exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionInvalidId#getMessage()} with id should not be null.
	 */
	@Test
	public final void testGetMessageWithIdShouldNotBeNull() {
		ExceptionInvalidId exception = new ExceptionInvalidId(false);
		exception.resource = Resources.getResourceBundleError();
		assertEquals(TestResources.getResourceBundleError(Constants.ID_SHOULD_NOT_BE_NULL), 
				exception.getMessage());
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getMessage()</code> returns {@link Constants#ID_SHOULD_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldBeNull() {
		return createException(TestResources.getResourceBundleError(Constants.ID_SHOULD_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getMessage()</code> returns {@link Constants#ID_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldNotBeNull() {
		return createException(TestResources.getResourceBundleError(Constants.ID_SHOULD_NOT_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests.
	 * @param exceptionMessage String the message returned by method <code>getMessage()</code>.
	 * @return the {@link ExceptionInvalidId}.
	 */
	private static ExceptionInvalidId createException(String exceptionMessage) {
		ExceptionInvalidId exception = mock(ExceptionInvalidId.class);
		when(exception.getMessage()).thenReturn(exceptionMessage);
		return exception;
	}
}