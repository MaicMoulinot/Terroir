package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link AbstractUser}.
 * @author Maic
 */
public class AbstractUserTest {
	
	/**
	 *  Test method for all {@link AbstractUser}'s getters and setters.
	 */
	@Test
	public final void testGettersSetters() {
		AbstractUser user = new Admin(); // AbstractUser is abstract so need to instantiate a child
		int nb = 0;
		String test = "test";
		
		// Id
		user.setId(nb);
		assertEquals("Id should be " + nb, nb, user.getId());
		
		// FirstName
		user.setFirstName(test);
		assertEquals("FirstName should be " + test, test, user.getFirstName());
		
		// LastName
		user.setLastName(test);
		assertEquals("LastName should be " + test, test, user.getLastName());
		
		// UserName
		user.setUserName(test);
		assertEquals("UserName should be " + test, test, user.getUserName());
		
		// Email
		user.setEmail(test);
		assertEquals("Email should be " + test, test, user.getEmail());
		
		// UserPassword
		user.setUserPassword(test);
		assertEquals("UserPassword should be " + test, test, user.getUserPassword());
		
		user = null; // Available for Garbage Collector
	}
}
