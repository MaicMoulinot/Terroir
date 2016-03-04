package com.jomm.terroir.util.exception;

import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_BE_NULL;
import static com.jomm.terroir.util.Constants.ResourceBundleError.ID_SHOULD_NOT_BE_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;

/**
 * This Class is a Junit test case testing {@link ExceptionInvalidId}.
 * @author Maic
 */
public class TestExceptionInvalidId {
	
	private ExceptionInvalidId exception;

	/**
	 * Test method for {@link ExceptionInvalidId#ExceptionInvalidId(boolean)}
	 * with boolean true.
	 */
	@Test
	public final void testConstructorWithBooleanTrue() {
		exception = new ExceptionInvalidId(true);
		assertTrue(exception.isIdShouldBeNull());
	}
	
	/**
	 * Test method for {@link ExceptionInvalidId#ExceptionInvalidId(boolean)}
	 * with boolean false.
	 */
	@Test
	public final void testConstructorWithBooleanFalse() {
		exception = new ExceptionInvalidId(false);
		assertFalse(exception.isIdShouldBeNull());
	}

	/**
	 * Test method for {@link ExceptionInvalidId#getMessage()} with id should be null.
	 */
	@Test
	public final void testGetMessageWithIdShouldBeNull() {
		exception = new ExceptionInvalidId(true);
		exception.setResourceBundle(Resources.getResourceBundleError());
		assertEquals(TestResources.getResourceBundleError(ID_SHOULD_BE_NULL.getKey()), 
				exception.getMessage());
	}
	
	/**
	 * Test method for {@link ExceptionInvalidId#getMessage()} with id should not be null.
	 */
	@Test
	public final void testGetMessageWithIdShouldNotBeNull() {
		exception = new ExceptionInvalidId(false);
		exception.setResourceBundle(Resources.getResourceBundleError());
		assertEquals(TestResources.getResourceBundleError(ID_SHOULD_NOT_BE_NULL.getKey()), 
				exception.getMessage());
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getMessage()</code> returns {@link BundleErrorKey#ID_SHOULD_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldBeNull() {
		return createException(TestResources.getResourceBundleError(ID_SHOULD_BE_NULL.getKey()));
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getMessage()</code> returns {@link BundleErrorKey#ID_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldNotBeNull() {
		return createException(TestResources.getResourceBundleError(ID_SHOULD_NOT_BE_NULL.getKey()));
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