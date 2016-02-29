package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.jomm.terroir.business.ServiceUser;
import com.jomm.terroir.business.model.Enterprise;
import com.jomm.terroir.business.model.Seller;
import com.jomm.terroir.business.model.TestEnterprise;
import com.jomm.terroir.business.model.TestSeller;
import com.jomm.terroir.util.Constants;
import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing {@link ViewSeller}.
 * @author Maic
 */
public class TestViewSeller {
	
	private ViewSeller view;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = generateDummyViewSeller();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		view = null; // Available for Garbage Collector
	}

	/**
	 * Test method for {@link ViewSeller#create()} when entity is null.
	 */
	@Test
	public final void testCreateWithNullPointerException() {
		// initialization
		setInjections();
		// call to create()
		when(view.userService.create(any(Seller.class))).thenThrow(new NullPointerException());
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(Constants.USER_SHOULD_NOT_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSeller#create()} with id not null.
	 */
	@Test
	public final void testCreateWithIllegalArgumentException() {
		// initialization
		setInjections();
		// call to create()
		when(view.userService.create(any(Seller.class))).thenThrow(new IllegalArgumentException());
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage and check if it contains the expected values
		FacesMessage message = messageCaptor.getValue();
		assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
        assertEquals(view.resourceError.getString(Constants.ID_SHOULD_BE_NULL), message.getSummary());
	}
	
	/**
	 * Test method for {@link ViewSeller#create()} with id null.
	 */
	@Test
	public final void testCreateWithEntityIdNull() {
		// initialization
		setInjections();
		// call to create()
		view.create();
		// verify Service.create() was called
		verify(view.userService).create(any(Seller.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(any(), messageCaptor.capture());
        // retrieve the captured FacesMessage
        FacesMessage message = messageCaptor.getValue();
        // check if the captured FacesMessage contains the expected values
        assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resourceMessage.getString(Constants.USER_REGISTRED), message.getSummary());
	}

	/**
	 * Test method for {@link ViewSeller#convertIntoEntity()}.
	 */
	@Test
	public final void testConvertIntoEntity() {
		view.setId((long) 3333);
		Seller entity = view.convertIntoEntity();
		compareViewAndEntity(view, entity);
	}

	/**
	 * Test method for {@link ViewSeller#convertIntoView(Seller)}.
	 */
	@Test
	public final void testConvertIntoView() {
		Seller entity = TestSeller.generateSellerWithIdNull();
		entity.setId((long) 3333);
		ViewSeller view = ViewSeller.convertIntoView(entity);
		compareViewAndEntity(view, entity);
	}
	
	/**
	 * Test method for {@link ViewSeller}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {		
		// Enterprise
		Enterprise enterprise = TestEnterprise.generateEnterpriseWithIdNull();
		view.setEnterprise(enterprise);
		assertEquals("Enterprise should be " + enterprise, enterprise, view.getEnterprise());
	}	
	
	/**
	 * Compare a view and an entity.
	 * @param view {@link ViewSeller}.
	 * @param entity {@link Seller}.
	 */
	private void compareViewAndEntity(ViewSeller view, Seller entity) {
		assertEquals(view.getId(), entity.getId());
		assertEquals(view.getFirstName(), entity.getFirstName());
		assertEquals(view.getLastName(), entity.getLastName());
		assertEquals(view.getUserName(), entity.getUserName());
		assertEquals(view.getPassword(), entity.getUserPassword());
		assertEquals(view.getEmail(), entity.getEmail());
		assertEquals(view.getEnterprise(), entity.getEnterprise());
	}
	
	/**
	 * Generate a dummy {@link ViewSeller} usable for tests.
	 * @return {@link ViewSeller}.
	 */
	static ViewSeller generateDummyViewSeller() {
		ViewSeller view  = new ViewSeller();
		view.setFirstName("FirstName");
		view.setLastName("LastName");
		view.setUserName("UserName");
		view.setEmail("email@email.com");
		view.setPassword("Zqdvb35d@jhg");
		view.setEnterprise(TestEnterprise.generateEnterpriseWithIdNull());
		return view;
	}
	
	/**
	 * Set mocked {@link javax.faces.context.FacesContext}, and mocked {@link ServiceUser} into view.
	 * Retrieve the {@link java.util.ResourceBundle}s from {@link Resources}.
	 */
	private void setInjections() {
		view.facesContext = mock(FacesContext.class);
		view.userService = mock(ServiceUser.class);
		view.resourceError = Resources.getResourceBundleError();
		view.resourceMessage = Resources.getResourceBundleMessage();
	}
}