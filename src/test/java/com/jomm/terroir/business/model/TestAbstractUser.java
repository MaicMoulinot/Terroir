package com.jomm.terroir.business.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class is a Junit test case testing the methods of {@link AbstractUser}.
 * It is annotated {@link RunWith} {@link Parameterized} to allow the test case to run with different parameters.
 * Here, the parameters are each child of {@link AbstractUser}.
 * @author Maic
 */
@RunWith(Parameterized.class)
public class TestAbstractUser {
	
	private AbstractUser user;
	private static final char[] PASSWORD = { 'Z', 'm', '@', 't', 'g', 'e', 'Q', 3 };
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link TestAbstractUser#childToTest()}.
	 * @param user the concrete child of {@link AbstractUser}.
	 */
    public TestAbstractUser(AbstractUser user) {
        this.user = user;
    }
	
	/**
	 *  Test method for all {@link AbstractUser}'s getters and setters.
	 */
	@Test
	public final void testGettersSetters() {
		Long nb = (long) 0;
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
		
		// Password
		user.setPassword(PASSWORD);
		assertEquals("Password should be " + PASSWORD.toString(), PASSWORD, user.getPassword());
	}
	
	/**
	 * Generate a simple {@link AbstractUser} usable for tests.
	 * @return a {@link AbstractUser}.
	 */
	public static AbstractUser generateAbstractUserWithIdNull() {
		// AbstractUser is abstract so need to instantiate a child (not so) randomly chosen
		AbstractUser user = new Admin();
		setDummyValuesWithIdNull(user);
		return user;
	}
	
	/**
	 * Set values into the {@link AbstractUser} usable for tests.
	 * @param user the {@link AbstractUser}
	 */
	public static void setDummyValuesWithIdNull(AbstractUser user) {
		user.setEmail("Email");
		user.setFirstName("FirstName");
		user.setLastName("LastName");
		user.setUserName("UserName");
		user.setPassword(PASSWORD);
	}
	
	/**
	 * Reference a list of all {@link AbstractUser}'s concrete children to be used as parameter on constructor.
	 * @return {@code Iterable<Object[]>}.
	 */
	@Parameters(name= "{index}: {0}")
	public static Iterable<Object[]> childToTest() {
		return Arrays.asList(new Object[][] {
			{new Admin()},
			{new Customer()},
			{new Seller()}
			}
		);
	}
}
