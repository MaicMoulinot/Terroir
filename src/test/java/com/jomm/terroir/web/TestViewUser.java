package com.jomm.terroir.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;

import com.jomm.terroir.util.Resources;

/**
 * This class is a Junit test case testing the methods of {@link ViewUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link ViewUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestViewUser {

	private ViewUser view;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestViewUser#childToTest()}.
	 * @param view the concrete child of {@link ViewUser}.
	 */
    public TestViewUser(ViewUser view) {
        this.view = view;
    }

	/**
	 * Test method for {@link ViewUser#passwordTooltip(javax.faces.event.ActionEvent)}.
	 */
	@Test
	public final void testPasswordTooltip() {
		// initialization
		view.facesContext = mock(FacesContext.class);
		view.resource = Resources.getResourceBundleMessage();
		// call
		view.passwordTooltip(mock(ActionEvent.class));
		// verify FacesContext.addMessage() was called
		ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<FacesMessage> messageCaptor = ArgumentCaptor.forClass(FacesMessage.class);
		verify(view.facesContext).addMessage(stringCaptor.capture(), messageCaptor.capture());
        // retrieve the captured String and check if it contains the expected value
        assertEquals("growl", stringCaptor.getValue());
        // retrieve the captured FacesMessage and check if it contains the expected values
        FacesMessage message = messageCaptor.getValue();
        assertEquals(FacesMessage.SEVERITY_INFO, message.getSeverity());
        assertEquals(view.resource.getString(ViewUser.PASSWORD_TITLE), message.getSummary());
        assertEquals(view.resource.getString(ViewUser.PASSWORD_RULES), message.getDetail());
	}

	/**
	 * Test method for {@link ViewUser}'s getters and setters.
	 */
	@Test
	public final void testGetterSetter() {
		Long nb = (long) 0;
		String test = "test";
		
		// Id
		view.setId(nb);
		assertEquals("Id should be " + nb, nb, view.getId());
		
		// FirstName
		view.setFirstName(test);
		assertEquals("FirstName should be " + test, test, view.getFirstName());
		
		// LastName
		view.setLastName(test);
		assertEquals("LastName should be " + test, test, view.getLastName());
		
		// UserName
		view.setUserName(test);
		assertEquals("UserName should be " + test, test, view.getUserName());
		
		// Email
		view.setEmail(test);
		assertEquals("Email should be " + test, test, view.getEmail());
		
		// Password
		view.setPassword(test);
		assertEquals("Password should be " + test, test, view.getPassword());
	}
	
	/**
	 * Reference a list of all {@link ViewUser}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new ViewCustomer()},
			{new ViewSeller()}
			}
		);
	}
}