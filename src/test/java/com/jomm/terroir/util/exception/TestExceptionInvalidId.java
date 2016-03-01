package com.jomm.terroir.util.exception;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.TestResources;

/**
 * @author Maic
 *
 */
public class TestExceptionInvalidId {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jomm.terroir.util.exception.ExceptionInvalidId#ExceptionInvalidId(boolean)}.
	 */
	@Test
	public final void testExceptionInvalidId() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jomm.terroir.util.exception.ExceptionInvalidId#getMessage()}.
	 */
	@Test
	public final void testGetMessage() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.jomm.terroir.util.exception.ExceptionInvalidId#getLocalizedMessage()}.
	 */
	@Test
	public final void testGetLocalizedMessage() {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getLocalizedMessage()</code> returns {@link Constants#ID_SHOULD_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldBeNull() {
		return createException(TestResources.getResourceBundleError(Constants.ID_SHOULD_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests which
	 * method <code>getLocalizedMessage()</code> returns {@link Constants#ID_SHOULD_NOT_BE_NULL}.
	 * @return the {@link ExceptionInvalidId}.
	 */
	public static ExceptionInvalidId createMockedExceptionIdShouldNotBeNull() {
		return createException(TestResources.getResourceBundleError(Constants.ID_SHOULD_NOT_BE_NULL));
	}
	
	/**
	 * Generate a mocked {@link ExceptionInvalidId} usable for tests.
	 * @param exceptionMessage String the message returned by method <code>getLocalizedMessage()</code>.
	 * @return the {@link ExceptionInvalidId}.
	 */
	private static ExceptionInvalidId createException(String exceptionMessage) {
		ExceptionInvalidId exception = mock(ExceptionInvalidId.class);
		when(exception.getLocalizedMessage()).thenReturn(exceptionMessage);
		return exception;
	}
}