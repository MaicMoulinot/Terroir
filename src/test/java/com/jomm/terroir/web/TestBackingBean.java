package com.jomm.terroir.web;

import static com.jomm.terroir.util.Constants.ResourceBundleError.EXCEPTION;
import static com.jomm.terroir.util.Resources.getValueFromKey;
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

/**
 * This class is a Junit test case testing {@link BackingBean}.
 * @author Maic
 */
public class TestBackingBean {
	
	// Variables //-----------------------------------------------
	private BackingBean view;

	// Test methods //--------------------------------------------
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
	 * Test method for {@link BackingBean#addFacesMessageException(String)}.
	 */
	@Test
	public final void testAddMessageException() {
		// set mocked FacesContext
		view.setTestFacesContext(mock(FacesContext.class));
		// call method
		String detail = "detail";
		view.addFacesMessageException(detail);
		checkMessageWithPlainDetail(view, null, FacesMessage.SEVERITY_ERROR, 
				getValueFromKey(EXCEPTION), detail);
	}

	/**
	 * Test method for {@link BackingBean#addFacesMessage(String, FacesMessage.Severity, String, String)}.
	 */
	@Test
	public final void testAddMessageStringSeverityStringString() {
		// set mocked FacesContext
		view.setTestFacesContext(mock(FacesContext.class));
		// call method
		String idClient = "idClient";
		String summary = "summary";
		String detail = "detail";
		Severity severity = FacesMessage.SEVERITY_WARN;
		view.addFacesMessage(idClient, severity, summary, detail);
		checkMessageWithPlainDetail(view, idClient, severity, summary, detail);
	}

	/**
	 * Test method for {@link BackingBean#generateReadableExceptionMessage(Exception, com.jomm.terroir.business.model.AbstractEntity)}.
	 */
	@Test
	public final void testGenerateExceptionMessage() {
		Enterprise entity = new Enterprise();
		Long id = 33L;
		entity.setId(id);
		String messageException = "test";
		Exception exception = new Exception(messageException);
		assertEquals(messageException + " on [id=" + id + ", class=" + entity.getClass().getName() + "]", 
				view.generateReadableExceptionMessage(exception, entity));
	}
	
	/**
	 * Test method for {@link BackingBean#getFacesContext()} and {@link BackingBean#setTestFacesContext(FacesContext)}.
	 */
	@Test
	public final void testFacesContextGetterSetter() {
		FacesContext facesContext = mock(FacesContext.class);
		view.setTestFacesContext(facesContext);
		assertEquals("FacesContext should be " + facesContext, facesContext, view.getFacesContext());
		facesContext = null; // Available for Garbage Collector
	}
	
	// Static methods //------------------------------------------
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
	 * @param detailBeginning String the beginning of the message's detail, with parameter "{0}".
	 * @param detailEnd String the end of the message's detail.
	 */
	public static void checkMessageWithParametrizedDetail(BackingBean view, String idClient, Severity severity, 
			String summary, String detailBeginning, String detailEnd) {
		FacesMessage message = retrieveAndCheckFacesMessage(view, idClient, severity, summary);
    	String[] detailParts = detailBeginning.split(Pattern.quote(" {0} "));    	
		assertTrue(message.getDetail().startsWith(detailParts[0]));
    	assertTrue(message.getDetail().endsWith(detailEnd));
	}
	
	// Helpers //-------------------------------------------------
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
	
	// Getters and Setters //-------------------------------------
	/**
	 * Set mocked {@link FacesContext} into view.
	 * @param view {@link BackingBean} the view to be set.
	 */
	static void setInjections(BackingBean view) {
		view.setTestFacesContext(mock(FacesContext.class));
	}
}