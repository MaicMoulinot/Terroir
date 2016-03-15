package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Constants.ResourceBundleMessage.CREATE_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.util.Resources;
import com.jomm.terroir.util.TestResources;

/**
 * This class is a Junit test case testing {@link BackingBean}.
 * @author Maic
 */
public class TestBackingBeanBean {

	private BackingBean view;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Instantiate a concrete child
		view = TestBeanRegistrationCustomer.generateDummyViewCustomer();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link BackingBean#addMessageException(String)}.
	 */
	@Test
	public final void testAddMessageException() {
		// set mocked FacesContext
		view.setFacesContext(mock(FacesContext.class));
		view.setResourceBundleError(Resources.getResourceBundleError());
		// call method
		String detail = "detail";
		view.addMessageException(detail);
		checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				TestResources.getValueFromResourceBundle(EXCEPTION), detail);
	}

	/**
	 * Test method for {@link BackingBean#addMessage(String, String)}.
	 */
	@Test
	public final void testAddMessageStringString() {
		// set mocked FacesContext
		view.setFacesContext(mock(FacesContext.class));
		// call method
		String summary = "summary";
		String detail = "detail";
		view.addMessage(summary, detail);
		checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_INFO, summary, detail);
	}

	/**
	 * Test method for {@link BackingBean#addMessage(String, String, String)}.
	 */
	@Test
	public final void testAddMessageStringStringString() {
		// set mocked FacesContext
		view.setFacesContext(mock(FacesContext.class));
		// call method
		String idClient = "idClient";
		String summary = "summary";
		String detail = "detail";
		view.addMessage(idClient, summary, detail);
		checkMessageWithPlainDetail(view, idClient, FacesMessage.SEVERITY_INFO, summary, detail);
	}

	/**
	 * Test method for {@link BackingBean#addMessage(String, FacesMessage.Severity, String, String)}.
	 */
	@Test
	public final void testAddMessageStringSeverityStringString() {
		// set mocked FacesContext
		view.setFacesContext(mock(FacesContext.class));
		// call method
		String idClient = "idClient";
		String summary = "summary";
		String detail = "detail";
		Severity severity = FacesMessage.SEVERITY_WARN;
		view.addMessage(idClient, severity, summary, detail);
		checkMessageWithPlainDetail(view, idClient, severity, summary, detail);
	}

	/**
	 * Test method for {@link BackingBean#generateExceptionMessage(Exception, com.jomm.terroir.business.model.AbstractEntity)}.
	 */
	@Test
	public final void testGenerateExceptionMessage() {
		Enterprise entity = new Enterprise();
		Long id = (long) 33;
		entity.setId(id);
		String messageException = "test";
		Exception exception = new Exception(messageException);
		assertEquals(messageException + " on [id=" + id + ", class=" + entity.getClass().getName() + "]", 
				view.generateExceptionMessage(exception, entity));
	}

	/**
	 * Test method for {@link BackingBean#getValueFromResourceBundle(String)}.
	 */
	@Test
	public final void testGetMessageFromResourceBundle() {
		view.setResourceBundleMessage(Resources.getResourceBundleMessage());
		assertEquals(TestResources.getValueFromResourceBundle(CREATE_OK), view.getValueFromResourceBundle(CREATE_OK));
	}

	/**
	 * Test method for {@link BackingBean#getValueFromResourceBundle(String)}.
	 */
	@Test
	public final void testGetErrorFromResourceBundle() {
		view.setResourceBundleError(Resources.getResourceBundleError());
		assertEquals(TestResources.getValueFromResourceBundle(EXCEPTION), view.getValueFromResourceBundle(EXCEPTION));
	}
	
	/**
	 * Test method for {@link BackingBean#setResourceBundleMessage(java.util.ResourceBundle)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testBeforeSetResourceBundleMessage() {
		// Without any call to the setter, ResourceBundleMessage is null.
		view.getValueFromResourceBundle(CREATE_OK);
	}
	
	/**
	 * Test method for {@link BackingBean#setResourceBundleMessage(java.util.ResourceBundle)}.
	 */
	@Test
	public final void testAfterSetResourceBundleMessage() {
		view.setResourceBundleMessage(Resources.getResourceBundleMessage());
		// After the call to the setter, ResourceBundleMessage is not null.
		view.getValueFromResourceBundle(CREATE_OK);
		assertTrue(true); // NullPointerException was not thrown
	}
	
	/**
	 * Test method for {@link BackingBean#setResourceBundleError(java.util.ResourceBundle)}.
	 */
	@Test(expected = NullPointerException.class)
	public final void testBeforeSetResourceBundleError() {
		// Without any call to the setter, ResourceBundleError is null.
		view.getValueFromResourceBundle(EXCEPTION);
	}
	
	/**
	 * Test method for {@link BackingBean#setResourceBundleError(java.util.ResourceBundle)}.
	 */
	@Test
	public final void testAfterSetResourceBundleError() {
		view.setResourceBundleError(Resources.getResourceBundleError());
		// After the call to the setter, ResourceBundleError is not null.
		view.getValueFromResourceBundle(EXCEPTION);
		assertTrue(true); // NullPointerException was not thrown
	}
	
	/**
	 * Test method for {@link BackingBean#getFacesContext()} and {@link BackingBean#setFacesContext(FacesContext)}.
	 */
	@Test
	public final void testFacesContextGetterSetter() {
		FacesContext facesContext = mock(FacesContext.class);
		view.setFacesContext(facesContext);
		assertEquals("FacesContext should be " + facesContext, facesContext, view.getFacesContext());
		facesContext = null; // Available for Garbage Collector
	}
	
	/**
	 * Verify if a {@link FacesMessage} was thrown with correct values.
	 * @param view {@link BackingBean} the view sending the {@link FacesMessage}.
	 * @param idClient String the identifier of the client.
	 * @param severity {@link Severity} the message's severity.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail, with no parameter.
	 */
	public static void checkMessageWithPlainDetail(BackingBean view, String idClient, Severity severity, 
			String summary, String detail) {
		FacesMessage message = retrieveAndCheckFacesMessage(view, idClient, severity, summary);
		assertEquals(detail, message.getDetail());
	}
	
	/**
	 * Verify if a {@link FacesMessage} was thrown with correct values.
	 * @param view {@link BackingBean} the view sending the {@link FacesMessage}.
	 * @param idClient String the identifier of the client.
	 * @param severity {@link Severity} the message's severity.
	 * @param summary String the message's summary.
	 * @param detail String the message's detail, with parameter "{0}".
	 */
	public static void checkMessageWithParametrizedDetail(BackingBean view, String idClient, Severity severity, 
			String summary, String detail) {
		FacesMessage message = retrieveAndCheckFacesMessage(view, idClient, severity, summary);
    	String[] detailParts = detail.replace("''", "'").split(Pattern.quote(" {0} "));
    	assertTrue(message.getDetail().startsWith(detailParts[0]));
    	assertTrue(message.getDetail().endsWith(detailParts[1]));
	}
	
	/**
	 * Verify if a {@link FacesMessage} was thrown with correct values.
	 * It does not check the message's detail.
	 * @param view {@link BackingBean} the view sending the {@link FacesMessage}.
	 * @param idClient String the identifier of the client.
	 * @param severity {@link Severity} the message's severity.
	 * @param summary String the message's summary.
	 */
	private static FacesMessage retrieveAndCheckFacesMessage(BackingBean view, String idClient, 
			Severity severity, String summary) {
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		ArgumentCaptor<String> idClientCaptor = ArgumentCaptor.forClass(String.class);
		verify(view.getFacesContext()).addMessage(idClientCaptor.capture(), messageCaptor.capture());
		// retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(severity, message.getSeverity());
		assertEquals(summary, message.getSummary());
		// retrieve the captured idClient and check if it is the expected value
		assertEquals(idClient, idClientCaptor.getValue());
		return message;
	}
	
	/**
	 * Set mocked {@link FacesContext} into view.
	 * Retrieve the {@link java.util.ResourceBundle}s qualified with {@link com.jomm.terroir.util.BundleError} 
	 * and {@link com.jomm.terroir.util.BundleMessage} from {@link Resources}.
	 * @param view {@link BackingBean} the view to be set.
	 */
	static void setInjections(BackingBean view) {
		view.setFacesContext(mock(FacesContext.class));
		view.setResourceBundleMessage(Resources.getResourceBundleMessage());
		view.setResourceBundleError(Resources.getResourceBundleError());
	}
}