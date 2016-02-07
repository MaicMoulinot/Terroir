package com.jomm.terroir.business;

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
public class AbstractUserTest {
	
	private AbstractUser user;
	
	/**
	 * Constructor.
	 * Its parameter comes from all values from {@link AbstractUserTest#childToTest()}.
	 * @param class the concrete child of {@link AbstractUser}.
	 */
    public AbstractUserTest(AbstractUser user) {
        this.user = user;
    }
	
	/**
	 *  Test method for all {@link AbstractUser}'s getters and setters.
	 */
	@Test
	public final void testGettersSetters() {
		long nb = 0;
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
	}
	
	/**
	 * Reference a list of all {@link AbstractUser}'s concrete children to be used as parameter on constructor.
	 * @return <code>Iterable < Object[] > </code>.
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
