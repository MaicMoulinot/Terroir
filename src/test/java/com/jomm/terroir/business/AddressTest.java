package com.jomm.terroir.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class is a Junit test case testing the methods of {@link Address}.
 * @author Maic
 */
public class AddressTest {
	
	/**
	 * Test method for all {@link Address}'s getters and setters.
	 */
	@Test
	public final void testGettersSetters() {
		Address address = new Address();
		String test = "test";
		
		// Street
		address.setStreet(test);
		assertEquals("Street should be " + test, test, address.getStreet());
		
		// Complement
		address.setComplement(test);
		assertEquals("Complement should be " + test, test, address.getComplement());
		
		// PostCode
		address.setPostCode(test);
		assertEquals("PostCode should be " + test, test, address.getPostCode());
		
		// Town
		address.setTown(test);
		assertEquals("Town should be " + test, test, address.getTown());
		
		// Country
		address.setCountry(test);
		assertEquals("Country should be " + test, test, address.getCountry());
		
		// Coordinates
		address.setCoordinates(test);
		assertEquals("Coordinates should be " + test, test, address.getCoordinates());
	}
}